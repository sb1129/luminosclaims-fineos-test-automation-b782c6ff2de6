package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.integration.db.DbCall;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

/**
 * Base class for calling lookup tables.
 */
abstract class DbLookup {
    private final String lookupName;
    private final DbCall dbCall;

    DbLookup(final DbCall.Database database, final String lookupName) {
        this.lookupName = requireNonNull(lookupName);
        this.dbCall = new DbCall(database);
    }

    /**
     * @param params the lookup values in order
     * @return a String or number
     */
    Object getLookupValue(final Object... params) {
        String sql = formatSql(params);
        List<Map<String, Object>> rows = dbCall.executeQuery(sql);
        return rows.stream().findFirst()
            .map(this::findAnswer)
            .orElseThrow(IllegalArgumentException::new);
    }

    private String formatSql(final Object... params) {
        return format("SELECT AnswerNum, AnswerStr "
                + "FROM VGetLookupData WHERE LookupName = ''{0}'' "
                + "AND {1} BETWEEN LookupEffectiveFromDate AND LookupEffectiveToDate "
                + "AND {2}",
            lookupName,
            "GETDATE()",
            formatParams(params)
        );
    }

    private String formatParams(final Object... params) {
        return IntStream.range(0, params.length)
            .mapToObj(i -> toSql(params[i], i + 1))
            .collect(joining(" AND "));
    }


    private Object findAnswer(final Map<String, Object> row) {
        Object answerStr = row.get("AnswerStr");
        if (answerStr != null) {
            return answerStr;
        } else {
            return row.get("AnswerNum");
        }
    }

    private String toSql(final Object param, final int position) {
        if (param == null) {
            // just move onto the next param
            return "1 = 1";
        }
        if (param instanceof String) {
            return "MinStr_" + position + " = '" + param + "'";
        }
        String num = param.toString();
        if (param instanceof BigDecimal) {
            num = ((BigDecimal) param).toPlainString();
        }
        return format("(MinNum_{1} IS NOT NULL "
                + "AND MinNum_{1} >= {0} "
                + "AND MaxNum_{1} IS NOT NULL "
                + "AND MaxNum_{1} <= {0})",
            num, position);
    }
}

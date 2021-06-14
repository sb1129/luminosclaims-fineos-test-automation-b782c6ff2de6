package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.integration.db.DbCall;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class DelegateAuthority {
    private final DbCall dbCall;
    private Map<String, String> substitutes;

    protected DelegateAuthority(final DbCall.Database database) {
        this.dbCall = new DbCall(database);
    }

    protected abstract String getFile();

    String getSQLStringFromFile() throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(getFile()), StandardCharsets.UTF_8.name());

    }

    String formatSql(final Object... params) throws IOException {
        substitutes = new HashMap<>();
        substitutes.put("sPaymentbenefit", String.valueOf(params[0]));
        substitutes.put("sDAType", String.valueOf(params[1]));
        StrSubstitutor strSubstitutor = new StrSubstitutor(substitutes);
        return strSubstitutor.replace(getSQLStringFromFile(), substitutes, "&", "&");
    }

    String formatCaseNumberSql(final Object... params) throws IOException {
        substitutes = new HashMap<>();
        substitutes.put("caseAliasValue", String.valueOf(params[0]));
        StrSubstitutor strSubstitutor = new StrSubstitutor(substitutes);
        return strSubstitutor.replace(getSQLStringFromFile(), substitutes, "&", "&");
    }
     public Object getRow(final Object... params) throws IOException {
        String sql = formatSql(params);
        List<Map<String, Object>> rows = dbCall.executeQuery(sql);
        return rows.stream().findFirst().orElse(null);
    }

    public List<Map<String, Object>> getRows(final Object... params) throws IOException {
        String sql = formatSql(params);
        List<Map<String, Object>> rows = dbCall.executeQuery(sql);
        return rows;
    }

    public List<String> getLevels(final Object... params) throws IOException {
        List<String> levelList = new ArrayList<String>();
        String sql = formatSql(params);
        List<Map<String, Object>> rows = dbCall.executeQuery(sql);
        for (Map<String, Object> row : rows) {
            Object val = row.get("Level");
            if (val != null && !levelList.contains(val.toString())) {
                levelList.add(val.toString());
            }
        }
        return levelList;
    }

    public String getCaseNumber(final Object... params) throws IOException {
        String sql = formatCaseNumberSql(params);
        List<Map<String, Object>> rows = dbCall.executeQuery(sql);
         return String.valueOf(rows.get(0).get("CaseNumber").toString());
    }
}

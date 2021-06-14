package com.gb.fineos.integration.db;

import com.gb.fineos.factory.PropertiesFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

/**
 * Generic way to call a known database with a (read-only) query.
 */
public class DbCall {
    private final String url;
    private final String user;
    private final String password;

    public DbCall(final Database database) {
        this.url = requireNonNull(getUrl(database));
        this.user = requireNonNull(getUserName(database));
        this.password = requireNonNull(getPassword(database));
    }

    private String getUrl(final Database database) {
        return PropertiesFactory.getInstance().getProperties().getProperty("db." + database.getName() + ".url");
    }

      private String getUserName(final Database database) {
        return PropertiesFactory.getInstance().getProperties().getProperty("db." + database.getName() + ".username");
    }
       private String getPassword(final Database database) {
        return PropertiesFactory.getInstance().getProperties().getProperty("db." + database.getName() + ".password");
    }

    public List<Map<String, Object>> executeQuery(final String sqlQuery) {
        try {
            return execute(sqlQuery, true);
        } catch (SQLException e) {
            throw new UncheckedSqlException(format("Cannot execute [{0}] against {1}", sqlQuery, url), e);
        }
    }

    private List<Map<String, Object>> execute(final String sqlQuery, @SuppressWarnings("SameParameterValue")
        final boolean readOnly) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            con.setReadOnly(readOnly);
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sqlQuery)) {
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new LinkedHashMap<>(columns);
                    for (int i = 1; i <= columns; ++i) {
                        row.put(md.getColumnName(i), rs.getObject(i));
                    }
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    public enum Database {
        FINEOSAPP("fineosapp"),
        FINEOSSEC("fineossec");

        private final String name;

        Database(final String name) {
            this.name = name;
        }

        private String getName() {
            return name;
        }
    }

}

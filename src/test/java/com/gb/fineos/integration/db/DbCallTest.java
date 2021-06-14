package com.gb.fineos.integration.db;

import com.gb.fineos.factory.PropertiesFactory;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.IObjectFactory;
import org.testng.annotations.Ignore;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.gb.fineos.integration.db.DbCall.Database.FINEOSAPP;
import static com.gb.fineos.integration.db.DbCall.Database.FINEOSSEC;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.testng.Assert.assertEquals;

@PrepareForTest({PropertiesFactory.class, DriverManager.class})
public class DbCallTest {
    private static final String TEST_USERNAME = "fakeUsername";
    private static final String TEST_PW = "fakePassword";
    private static final String TEST_URL = "jdbc:sqlserver://localhost:1433;databaseName=FINEOSAPP";

    @Mock
    private PropertiesFactory propertiesFactory;
    @Mock
    private Properties properties;
    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultset;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }

    @Ignore("This test will connect to a real database associated with your env profile")
    @Test
    public void integrationTestSelect() {
        DbCall dbCall = new DbCall(FINEOSAPP);
        List<Map<String, Object>> rows = dbCall.executeQuery("select GETDATE() as dt");
        assertEquals(1, rows.size());
        assertEquals("dt", String.join(",", rows.get(0).keySet()));
        assertEquals("java.sql.Timestamp", rows.get(0).get("dt").getClass().getCanonicalName());
    }

    @SuppressWarnings("squid:S2068")
    @Ignore("DriverManager.getConnection does not get mocked correctly") // TODO fix
    @Test
    public void testExecuteQuery() throws SQLException {
        mockStatic(PropertiesFactory.class);
        expect(PropertiesFactory.getInstance()).andReturn(propertiesFactory);
        expect(propertiesFactory.getProperties()).andReturn(properties);
        expect(properties.getProperty("db.fineossec.url")).andReturn(TEST_URL);
        mockStatic(DriverManager.class);
        expect(DriverManager.getConnection(TEST_URL, TEST_USERNAME, TEST_PW)).andReturn(connection);

        connection.setReadOnly(true);
        expectLastCall();
        expect(connection.createStatement()).andReturn(statement);
        expect(statement.executeQuery(anyString())).andReturn(resultset);
        ResultSetMetaData metadata = createMock(ResultSetMetaData.class);
        expect(metadata.getColumnCount()).andReturn(1);
        String columnName = "aNumber";
        expect(metadata.getColumnName(1)).andReturn(columnName);
        expect(resultset.getObject(1)).andReturn(1);
        expect(resultset.getMetaData()).andReturn(metadata);
        replayAll(PropertiesFactory.class, DriverManager.class);

        DbCall dbCall = new DbCall(FINEOSSEC);
        List<Map<String, Object>> rows = dbCall.executeQuery("select 1 as aNumber");
        assertEquals(1, rows.size());
        Map<String, Object> firstRow = rows.get(0);
        assertEquals(columnName, String.join(",", firstRow.keySet()));
        assertEquals("java.lang.Integer", firstRow.get(columnName).getClass().getCanonicalName());
        verifyAll();
    }
}

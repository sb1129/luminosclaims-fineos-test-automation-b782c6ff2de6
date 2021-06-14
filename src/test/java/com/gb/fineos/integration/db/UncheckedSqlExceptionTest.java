package com.gb.fineos.integration.db;

import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.*;

public class UncheckedSqlExceptionTest {

    private static final String MESSAGE = "message";
    private static final SQLException CAUSE = new SQLException("cause");

    @Test
    public void construct() {
        UncheckedSqlException exception = new UncheckedSqlException(MESSAGE, CAUSE);
        assertEquals(MESSAGE, exception.getMessage());
        assertEquals(CAUSE, exception.getCause());
    }
}

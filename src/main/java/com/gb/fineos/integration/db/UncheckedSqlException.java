package com.gb.fineos.integration.db;

import java.sql.SQLException;

final class UncheckedSqlException extends RuntimeException {
    UncheckedSqlException(final String message, final SQLException cause) {
        super(message, cause);
    }
}

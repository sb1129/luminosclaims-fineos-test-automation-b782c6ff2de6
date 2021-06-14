package com.gb.fineos.provider;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.List;

import static java.util.Objects.requireNonNull;

public final class CSVDataProvider {

    private CSVDataProvider() {
        // Do nothing
    }

    /**
     * Loads test data from a Comma Separated Values (CSV) text file.
     *
     * It is possible to enable this provider to run in parallel by adding <code>parallel = true</code>
     * to the <code>@DataProvider</code> tag. At present there are issues with multiple rows of test data using
     * the same user. It may be a session based issue but has yet to be resolved.
     *
     * <b>For now running in parallel has been disabled.</b>
     *
     * @param m Test method being executed
     * @return test data
     */
    @DataProvider(name = "csvDataProvider")
    public static Object[][] getData(final Method m) throws IOException {
        final String csv = m.getDeclaringClass().getName().replace('.', '/') + "_" + m.getName() + ".csv";

        final CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withCommentMarker('#')
            .parse(new InputStreamReader(requireNonNull(CSVDataProvider.class.getClassLoader().getResourceAsStream(csv), "Cannot find " + csv)));

        final List<CSVRecord>  csvRecords = csvParser.getRecords();
        final Object[][] results = new Object[csvRecords.size()][1];

        for (int i = 0; i < csvRecords.size(); i++) {
            results[i][0] = csvRecords.get(i).toMap();
        }

        return results;
    }
}

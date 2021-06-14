package com.gb.fineos.domain;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestCaseTest {

    @Test
    public void testGetters() {
        final String name = "test";
        final String description = "description";
        final Map<String, String> data = new HashMap<>();

        final TestCase testCase = new TestCase(name, description, data, tc -> {}, tc -> {}, tc -> {});

        assertEquals(data, testCase.getData());
    }

    @Test
    public void testStart_RunMode_Y() {
        final AtomicBoolean executeRun = new AtomicBoolean();
        final Map<String, String> data = new HashMap<>();
        data.put("Runmode", "Y");

        final TestCase testCase = new TestCase("test", "description", data, tc -> executeRun.set(true), tc -> {}, tc -> {});

        testCase.start();

        assertTrue(executeRun.get());
    }

    @Test
    public void testStart_RunMode_N() {
        final AtomicBoolean executeRun = new AtomicBoolean();
        final Map<String, String> data = new HashMap<>();
        data.put("Runmode", "N");

        final TestCase testCase = new TestCase("test", "description", data, tc -> executeRun.set(true), tc -> {}, tc -> {});

        testCase.start();

        assertFalse(executeRun.get());
    }

    @Test
    public void testExecute() {
        final AtomicBoolean executeRun = new AtomicBoolean();

        final TestCase testCase = new TestCase("test", "description", new HashMap<>(), tc -> {}, tc -> executeRun.set(true), tc -> {});

        testCase.execute();

        assertTrue(executeRun.get());
    }

    @Test
    public void testEnd() {
        final AtomicBoolean executeRun = new AtomicBoolean();
        final Map<String, String> data = new HashMap<>();
        data.put("Runmode", "Y");

        final TestCase testCase = new TestCase("test", "description", data, tc -> {}, tc -> {}, tc -> executeRun.set(true));

        testCase.start();
        testCase.end();

        assertTrue(executeRun.get());
    }

}

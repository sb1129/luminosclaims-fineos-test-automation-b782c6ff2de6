package com.gb.fineos.page.sharedpages.casemanager;

import com.gb.fineos.domain.TestCase;
import com.gb.fineos.domain.TestCaseContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class CaseSearchPageRequestTest {

    @Test
    public void testRequestGetters() {
        final Map<String, String> data = new HashMap<>();
        data.put("CaseNumber", "iCare31_GL_N01");

        final TestCaseContext context = new TestCase("CaseSearchPageRequestTest", "description", data, null, null, null);

        final CaseSearchPage.CaseSearchPageRequest request = new CaseSearchPage.CaseSearchPageRequest(context);

        assertEquals(request.getCaseNumber(), "iCare31_GL_N01");
    }
}

package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.integration.db.DbCall;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.testng.Assert.assertEquals;

@PrepareForTest({DbCall.class})
public class TaxConstantsLookupTest {
    private static final String LOOKUP_TAX_CONSTANTS = "SELECT AnswerNum, AnswerStr FROM VGetLookupData "
        + "WHERE LookupName = 'GB.Payments.Tax.TaxConstantsLookup' "
        + "AND GETDATE() BETWEEN LookupEffectiveFromDate AND LookupEffectiveToDate AND ";
    private static final String ANSWER_STR = "AnswerStr";
    private TaxConstantsLookup lookup;
    private DbCall dbCall;
    private Map<String, Object> row;

    @BeforeMethod
    public void setUp() {
        row = new LinkedHashMap<>();
        dbCall = createMock(DbCall.class);
        lookup = new TaxConstantsLookup("username", "password");
        Whitebox.setInternalState(lookup, dbCall);
    }

    @Test
    public void testGetGstRate() {
        expect(dbCall.executeQuery(LOOKUP_TAX_CONSTANTS + "MinStr_1 = 'GSTRate'")).andReturn(singletonList(row));
        row.put(ANSWER_STR, "10");

        replayAll();
        assertEquals(lookup.getGstRate(), "10");
        verifyAll();
    }

    @Test
    public void testGetRecoveryTaxValidationMessage() {
        expect(dbCall.executeQuery(LOOKUP_TAX_CONSTANTS + "MinStr_1 = 'RecoveryTaxValidationMessage'")).andReturn(singletonList(row));
        row.put(ANSWER_STR, "GST must not exceed 10% of the line item recovered amount");

        replayAll();
        assertEquals(lookup.getRecoveryTaxValidationMessage(), "GST must not exceed 10% of the line item recovered amount");
        verifyAll();
    }

    @Test
    public void testGetTaxApplicableEnumSubset() {
        expect(dbCall.executeQuery(LOOKUP_TAX_CONSTANTS + "MinStr_1 = 'TaxApplicableEnumSubset'")).andReturn(singletonList(row));
        row.put(ANSWER_STR, "TaxAppliedRecoveries");

        replayAll();
        assertEquals(lookup.getTaxApplicableEnumSubset(), "TaxAppliedRecoveries");
        verifyAll();
    }
}

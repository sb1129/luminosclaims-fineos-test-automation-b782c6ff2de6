package com.gb.fineos.integration.db.lookup;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentBean {
    @JsonProperty("SubCaseNumber")
    public String SubCaseNumber;
    @JsonProperty("PolicyVersion")
    public String PolicyVersion;
    @JsonProperty("Amount")
    public String Amount;
    @JsonProperty("Role")
    public String Role;
    @JsonProperty("MinVal")
    public String MinVal;
    @JsonProperty("MaxVal")
    public String MaxVal;
    @JsonProperty("DAType")
    public String DAType;
    @JsonProperty("Level")
    public String Level;



}

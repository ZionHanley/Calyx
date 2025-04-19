package com.calyx.datamodels;

public class modelZipcode {
    String Zipcode;
    String Zone;
    String Zonetitle;

    public modelZipcode(String zipcode, String zone, String zonetitle) {
        Zipcode = zipcode;
        Zone = zone;
        Zonetitle = zonetitle;
    }

    public String getCode() {
        return Zipcode;
    }

    public void setCode(String x) {
        Zipcode = x;
    }

    public String getZone() {
        return Zone;
    }

    public String getZoneTitle() {
        return Zonetitle;
    }
}
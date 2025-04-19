package com.calyx.datamodels;


public class modelComPlants {

    String Common;
    String Scientific;
    String Companions;
    String Soil_Ben;
    String Other_Ben;
    String Row;
    String Space;
    String Depth;
    String Water_W;
    String Water_F;

    public modelComPlants(String common, String scientific, String companions, String soil_Ben, String other_Ben, String row, String space, String depth, String water_W, String water_F) {
        Common = common;
        Scientific = scientific;
        Companions = companions;
        Soil_Ben = soil_Ben;
        Other_Ben = other_Ben;
        Row = row;
        Space = space;
        Depth = depth;
        Water_W = water_W;
        Water_F = water_F;
    }

    public String getName() {
        return Common;
    }

    public String getSciName() {
        return Scientific;
    }

    public String getCompanions() {
        return Companions;
    }

    public String getSoil_Ben() {
        return Soil_Ben;
    }

    public String getOther_Ben() {
        return Other_Ben;
    }

    public String getRow() {
        return Row;
    }

    public String getSpace() {
        return Space;
    }
    
    public String getDepth() {
        return Depth;
    }

    public String getWater_W() {
        return Water_W;
    }

    public String getWater_F() {
        return Water_F;
    }
}

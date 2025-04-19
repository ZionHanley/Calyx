package com.calyx.datamodels;

public class modelCrops {

    String Name;
    String SciName;
    String Continent;
    String Seasonality;
    String Germination;
    String GrowthTime;
    String NPKRatio;
    String OptimalPH;
    String Row;
    String Space;
    String Depth;
    String Water_W;
    String Water_F;

    public modelCrops(String name, String sciName, String continent, String seasonality, String germination, String growthTime, String NPK, String optimalPH, String row,String space, String depth, String water_W, String water_F) {
        Name = name;
        SciName = sciName;
        Continent = continent;
        Seasonality = seasonality;
        Germination = germination;
        GrowthTime = growthTime;
        NPKRatio = NPK;
        OptimalPH = optimalPH;
        Row = row;
        Space = space;
        Depth = depth;
        Water_W = water_W;
        Water_F = water_F;
    }

    public String getName() {
        return Name;
    }

    public String getSciName() {
        return SciName;
    }

    public String getContinent() {
        return Continent;
    }

    public String getSeasonality() {
        return Seasonality;
    }

    public String getGermination() {
        return Germination;
    }

    public String getGrowthTime() {
        return GrowthTime;
    }

    public String getNPKRatio() {
        return NPKRatio;
    }
    
    public String getOptimalPH() {
        return OptimalPH;
    }

    public String getRow() {
        return Row;
    }

    public String getDepth() {
        return Depth;
    }

    public String getSpace() {
        return Space;
    }

    public String getWater_W() {
        return Water_W;
    }

    public String getWater_F() {
        return Water_F;
    }
}
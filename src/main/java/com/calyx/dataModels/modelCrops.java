package com.calyx.dataModels;

public class modelCrops {

    String Name;
    String SciName;
    String OptimalPP;
    String OptimalPH;
    String Regionality;
    String Germination;
    String NPKRatio;
    String PlantingDp;
    String PlantingSp;

    modelCrops(String name, String sciName, String optimalPP, String optimalPH, String regionality, String germination, String NPK, String plantingDp, String plantingSp) {
        Name = name;
        SciName = sciName;
        OptimalPP = optimalPP;
        OptimalPH = optimalPH;
        Regionality = regionality;
        Germination = germination;
        NPKRatio = NPK;
        PlantingDp = plantingDp;
        PlantingSp = plantingSp;
    }
}
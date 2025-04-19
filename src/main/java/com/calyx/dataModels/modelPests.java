package com.calyx.datamodels;

public class modelPests {
    String Plant;
    String CommonPests;
    String Type;
    String DamageSymptoms;
    String OrganicControl;
    String ChemicalControl;
    String Region;

    public modelPests(String plant, String commonPests, String type, String damageSymptoms, String organicControl, String chemicalControl, String region) {
        Plant = plant;
        CommonPests = commonPests;
        Type = type;
        DamageSymptoms = damageSymptoms;
        OrganicControl = organicControl;
        ChemicalControl = chemicalControl;
        Region = region;
    }

    public String getName() {
        return Plant;
    }

    public String getCommonPests() {
        return CommonPests;
    }
    
    public String getType() {
        return Type;
    }
    
    public String getDamageSymptoms() {
        return DamageSymptoms;
    }

    public String getOrganicControl() {
        return OrganicControl;
    }
       
    public String getChemicalControl() {
        return ChemicalControl;
    }
    
    public String getRegion() {
        return Region;
    }
}

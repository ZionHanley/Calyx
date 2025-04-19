package com.calyx.daofiles;

import com.calyx.datamodels.modelCrops;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class daoCrops {
    private static final String CROPS_CSV = "Crops.csv";
    private static final String CSV_DELIMITER = ",";

    public List<modelCrops> getAllCrops() throws IOException {
        return loadCropsFromCSV();
    }

    public List<modelCrops> searchCrops(String columnName, String searchValue) throws IOException {
        List<modelCrops> allCrops = loadCropsFromCSV();
        
        Predicate<modelCrops> predicate = switch (columnName.toLowerCase()) {
            case "name" -> crop -> crop.getName().equalsIgnoreCase(searchValue);
            case "sciname" -> crop -> crop.getSciName().equalsIgnoreCase(searchValue);
            case "continent" -> crop -> crop.getContinent().equalsIgnoreCase(searchValue);
            case "seasonality" -> crop -> crop.getSeasonality().equalsIgnoreCase(searchValue);
            case "germination" -> crop -> crop.getGermination().equalsIgnoreCase(searchValue);
            case "growthtime" -> crop -> crop.getGrowthTime().equalsIgnoreCase(searchValue);
            case "npkratio" -> crop -> crop.getNPKRatio().equalsIgnoreCase(searchValue);
            case "optimalph" -> crop -> crop.getOptimalPH().equalsIgnoreCase(searchValue);
            case "row" -> crop -> crop.getRow().equalsIgnoreCase(searchValue);
            case "space" -> crop -> crop.getSpace().equalsIgnoreCase(searchValue);
            case "depth" -> crop -> crop.getDepth().equalsIgnoreCase(searchValue);
            case "water_w" -> crop -> crop.getWater_W().equalsIgnoreCase(searchValue);
            case "water_f" -> crop -> crop.getWater_F().equalsIgnoreCase(searchValue);
            default -> throw new IllegalArgumentException("Invalid column name: " + columnName);
        };

        return allCrops.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<modelCrops> searchCropsContaining(String columnName, String partialValue) throws IOException {
        List<modelCrops> allCrops = loadCropsFromCSV();
        
        Predicate<modelCrops> predicate = switch (columnName.toLowerCase()) {
            case "name" -> crop -> crop.getName().toLowerCase().contains(partialValue.toLowerCase());
            case "sciname" -> crop -> crop.getSciName().toLowerCase().contains(partialValue.toLowerCase());
            case "continent" -> crop -> crop.getContinent().toLowerCase().contains(partialValue.toLowerCase());
            case "seasonality" -> crop -> crop.getSeasonality().toLowerCase().contains(partialValue.toLowerCase());
            case "germination" -> crop -> crop.getGermination().toLowerCase().contains(partialValue.toLowerCase());
            case "growthtime" -> crop -> crop.getGrowthTime().toLowerCase().contains(partialValue.toLowerCase());
            case "npkratio" -> crop -> crop.getNPKRatio().toLowerCase().contains(partialValue.toLowerCase());
            case "optimalph" -> crop -> crop.getOptimalPH().toLowerCase().contains(partialValue.toLowerCase());
            case "row" -> crop -> crop.getRow().toLowerCase().contains(partialValue.toLowerCase());
            case "space" -> crop -> crop.getSpace().toLowerCase().contains(partialValue.toLowerCase());
            case "depth" -> crop -> crop.getDepth().toLowerCase().contains(partialValue.toLowerCase());
            case "water_w" -> crop -> crop.getWater_W().toLowerCase().contains(partialValue.toLowerCase());
            case "water_f" -> crop -> crop.getWater_F().toLowerCase().contains(partialValue.toLowerCase());
            default -> throw new IllegalArgumentException("Invalid column name: " + columnName);
        };

        return allCrops.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private List<modelCrops> loadCropsFromCSV() throws IOException {
        List<modelCrops> crops = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(CROPS_CSV))) {
            String line;
            // Skip header if present
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(CSV_DELIMITER);
                if (values.length >= 13) {
                    modelCrops crop = new modelCrops( values[0].trim(), values[1].trim(), values[2].trim(), values[3].trim(),
                                                      values[4].trim(), values[5].trim(), values[6].trim(), values[7].trim(),
                                                      values[8].trim(), values[9].trim(), values[10].trim(), values[11].trim(),
                                                      values[12].trim());
                    crops.add(crop);
                }
            }
        }
        return crops;
    }
}
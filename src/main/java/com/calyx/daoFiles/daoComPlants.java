package com.calyx.daoFiles;

import com.calyx.dataModels.modelComPlants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class daoComPlants {
    private static final String COMPLANTS_CSV = "Companion_Plants.csv";
    private static final String CSV_DELIMITER = ",";

    public List<modelComPlants> getAll() throws IOException {
        return loadCSV();
    }

    public List<modelComPlants> searchColumn(String columnName, String searchValue) throws IOException {
        List<modelComPlants> allcomplants = loadCSV();
        
        Predicate<modelComPlants> predicate = switch (columnName.toLowerCase()) {
            case "name" -> complant -> complant.getName().equalsIgnoreCase(searchValue);
            case "sciname" -> complant -> complant.getSciName().equalsIgnoreCase(searchValue);
            case "companions" -> complant -> complant.getCompanions().equalsIgnoreCase(searchValue);
            case "soil_ben" -> complant -> complant.getSoil_Ben().equalsIgnoreCase(searchValue);
            case "other_ben" -> complant -> complant.getOther_Ben().equalsIgnoreCase(searchValue);
            case "row" -> complant -> complant.getRow().equalsIgnoreCase(searchValue);
            case "space" -> complant -> complant.getSpace().equalsIgnoreCase(searchValue);
            case "depth" -> complant -> complant.getDepth().equalsIgnoreCase(searchValue);
            case "water_w" -> complant -> complant.getWater_W().equalsIgnoreCase(searchValue);
            case "water_f" -> complant -> complant.getWater_F().equalsIgnoreCase(searchValue);
            default -> throw new IllegalArgumentException("Invalid column name: " + columnName);
        };

        return allcomplants.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<modelComPlants> searchContaining(String columnName, String partialValue) throws IOException {
        List<modelComPlants> allcomplants = loadCSV();
        
        Predicate<modelComPlants> predicate = switch (columnName.toLowerCase()) {
            case "name" -> complant -> complant.getName().toLowerCase().contains(partialValue.toLowerCase());
            case "sciname" -> complant -> complant.getSciName().toLowerCase().contains(partialValue.toLowerCase());
            case "companions" -> complant -> complant.getCompanions().toLowerCase().contains(partialValue.toLowerCase());
            case "soil_ben" -> complant -> complant.getSoil_Ben().toLowerCase().contains(partialValue.toLowerCase());
            case "other_ben" -> complant -> complant.getOther_Ben().toLowerCase().contains(partialValue.toLowerCase());
            case "row" -> complant -> complant.getRow().toLowerCase().contains(partialValue.toLowerCase());
            case "space" -> complant -> complant.getSpace().toLowerCase().contains(partialValue.toLowerCase());
            case "depth" -> complant -> complant.getDepth().toLowerCase().contains(partialValue.toLowerCase());
            case "water_w" -> complant -> complant.getWater_W().toLowerCase().contains(partialValue.toLowerCase());
            case "water_f" -> complant -> complant.getWater_F().toLowerCase().contains(partialValue.toLowerCase());
            default -> throw new IllegalArgumentException("Invalid column name: " + columnName);
        };

        return allcomplants.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private List<modelComPlants> loadCSV() throws IOException {
        List<modelComPlants> complants = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(COMPLANTS_CSV))) {
            String line;
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(CSV_DELIMITER);
                if (values.length >= 9) {
                    modelComPlants complant = new modelComPlants( values[0].trim(), values[1].trim(), values[2].trim(), values[3].trim(),
                                                      values[4].trim(), values[5].trim(), values[6].trim(), values[7].trim(),
                                                      values[8].trim(), values[9].trim());
                    complants.add(complant);
                }
            }
        }
        return complants;
    }
}
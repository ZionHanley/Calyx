package com.calyx.datamodels;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modelUserSoil {

    private static final String USER = "D:/Java Projects/Calyx/data/User Data/userSoil.csv";
    private static final String TEMPLATE = "D:/Java Projects/Calyx/data/templates/userSoil.csv";
    private static final DateTimeFormatter TimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = Logger.getLogger(modelUserSoil.class.getName());
    String NPK;
    String PH;
    
    public modelUserSoil(String PH, String N, String P, String K) {
        this.PH = PH;
        this.NPK = N + "-" + P + "-" + K;
    }

    public void checkExists() {
        File dataFile = new File(USER);
        if (!dataFile.exists()) {
            try {
                Files.copy(Paths.get(TEMPLATE), Paths.get(USER));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error copying userSoil template: {0}", e.getMessage());
            }
            logdata();
        }
    }

    private void logdata() {
        try (FileWriter writer = new FileWriter(USER, true)) {
            String time = LocalDateTime.now().format(TimeFormat);
            writer.write(time + " - PH: " + PH + ", NPK: " + NPK + "\n");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while logging userSoil data: {0}", e.getMessage());
        }
    }
}

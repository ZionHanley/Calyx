package com.calyx.datamodels;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modelUserAccount {

    private static final String USER = "D:/Java Projects/Calyx/data/User Data/userAccount.csv";
    private static final String TEMPLATE = "D:/Java Projects/Calyx/data/templates/userAccount.csv";
    private static final Logger logger = Logger.getLogger(modelUserAccount.class.getName());

    private static int ID = 101;
    private final String username;
    private final String password;
    private final String zipcode;

    public modelUserAccount(String username, String password, String zipcode) {
        ID++;
        this.username = username;
        this.password = password;
        this.zipcode = zipcode;
    }

    public void checkExists() {
        File dataFile = new File(USER);
        if (!dataFile.exists()) {
            try {
                Files.copy(Paths.get(TEMPLATE), Paths.get(USER));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error copying userAccounts template: {0}", e.getMessage());
            }
            logdata();
        }
    }

    private void logdata() {
        try (FileWriter writer = new FileWriter(USER, true)) {
            writer.write(ID + "," + username + "," + password + "," + zipcode + "\n");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while logging account data: {0}", e.getMessage());
        }
    }
}

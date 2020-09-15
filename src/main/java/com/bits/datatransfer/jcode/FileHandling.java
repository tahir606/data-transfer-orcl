package com.bits.datatransfer.jcode;

import com.bits.datatransfer.settings.Settings;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileHandling {

    private static final String PATH = "./";
    private static final String SETTINGS_TXT = PATH + "settings.txt";

    public FileHandling() {
        System.out.println("in Constructor");
    }

    public Settings getSettings() {
        try {
            InputStream input = new FileInputStream(SETTINGS_TXT);
            String settingsString = readFromInputStream(input);

            String[] split = settingsString.split(",");

            return new Settings(split[0], split[1], split[2]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveSettings(Settings settings) {

        String str = settings.getIp() + "," + settings.getDbUser() + "," + settings.getDbPass();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(SETTINGS_TXT));
            writer.write(str);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}

package com.bits.datatransfer.jcode;

import com.bits.datatransfer.settings.ExportSettings;
import com.bits.datatransfer.settings.ImportSettings;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileHandlingImpl implements FileHandling {

    private static final String PATH = "./config/settings/";
    private static final String SETTINGS_TXT = PATH + "settings.txt";
    private static final String EXPORT_TXT = PATH + "export.txt";
    private static final String TABLES_TXT = PATH + "tables.txt";

    public FileHandlingImpl() {
    }

    @Override
    public ImportSettings getImportSettings() {
        try {
            InputStream input = new FileInputStream(SETTINGS_TXT);
            String settingsString = readFromInputStream(input);

            String[] split = settingsString.split(",");

            return new ImportSettings(split[0], split[1], split[2]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void saveImportSettings(ImportSettings importSettings) {

        String str = importSettings.getIp() + "," + importSettings.getDbUser() + "," + importSettings.getDbPass();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(SETTINGS_TXT));
            writer.write(str);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveExportSettings(ExportSettings exportSettings) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(EXPORT_TXT));
            writer.write(exportSettings.getIp());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TABLES_TXT));
            writer.write(exportSettings.getTableNamesByComma());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ExportSettings getExportSettings() {

        ExportSettings exportSettings = null;

        try {
            InputStream input = new FileInputStream(EXPORT_TXT);
            String exportString = readFromInputStream(input);

            exportSettings = new ExportSettings(exportString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream input = new FileInputStream(TABLES_TXT);
            String tableNamesString = readFromInputStream(input);

            exportSettings.setTableNames(tableNamesString.split(","));
            exportSettings.setTableNamesByComma(tableNamesString);

            return exportSettings;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String readFromInputStream(InputStream inputStream)
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

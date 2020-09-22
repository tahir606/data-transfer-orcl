package com.bits.datatransfer.transfercontrols;

import com.bits.datatransfer.jcode.FileHandlingImpl;
import com.bits.datatransfer.jcode.OracleHandlingImpl;
import com.bits.datatransfer.settings.ExportSettings;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.bits.datatransfer.transfercontrols.APIConstants.*;

@Service
public class ExportHandler {

    private OkHttpClient client;

    private final OracleHandlingImpl oracleHandling;
    private final FileHandlingImpl fileHandling;

    private String apiLink;

    public ExportHandler(OracleHandlingImpl oracleHandling, FileHandlingImpl fileHandling) {
        client = new OkHttpClient();
        this.oracleHandling = oracleHandling;
        this.fileHandling = fileHandling;

        apiLink = "http://" + fileHandling.getExportSettings().getIp().trim() + ":" + PORT;
    }

    // Sends Data as JSON from Local Database
    // To Specified API which will store it in their Local Database
    public String exportData() {

        ExportSettings exportSettings = fileHandling.getExportSettings();

        if (exportSettings.getTableNames().length < 1)
            return null;

        for (String table : exportSettings.getTableNames()) {
            String jsonString = "{\"" + TABLE_KEY + "\":\"" + table.trim() + "\", " +
                    "\"" + DATA_KEY + "\":[";


            List<Map<String, Object>> rows = oracleHandling.getNotImportedTableData(table);

            if (rows.isEmpty())
                return null;

            for (Map<String, Object> row : rows) {
                jsonString = jsonString + "{";
                for (String column : row.keySet()) {
                    if (column.equals(ISIMPORTED))
                        continue;
                    System.out.println(column);
                    System.out.println(row.get(column).toString());
                    jsonString = jsonString + "\"" + column + "\":\"" + row.get(column).toString() + "\",";
                }
                jsonString = jsonString.substring(0, jsonString.length() - 1) + "},";
            }

            jsonString = jsonString.substring(0, jsonString.length() - 1) + "]}";

            System.out.println("JSON String: " + jsonString);

            System.out.println(apiLink + EXPORT);

            RequestBody body = RequestBody.create(jsonString, JSON);
            Request request = new Request.Builder()
                    .url(apiLink + APIConstants.EXPORT)
                    .post(body)
                    .build();

            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            System.out.println(response);

                            if (response.code() == 200) {
                                oracleHandling.markTableDataImported(table);
                            }
                        }
                    });
        }

        return "Exported";

    }
}

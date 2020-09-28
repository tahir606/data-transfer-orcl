package com.bits.datatransfer.transfercontrols;

import com.bits.datatransfer.LoadDatabase;
import com.bits.datatransfer.jcode.OracleHandlingImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static com.bits.datatransfer.transfercontrols.APIConstants.DATA_KEY;
import static com.bits.datatransfer.transfercontrols.APIConstants.TABLE_KEY;

@RestController
@RequestMapping("/import")
public class ImportController {

    private static final Logger log = LoggerFactory.getLogger(ImportController.class);

    private final OracleHandlingImpl oracleHandling;

    public ImportController(OracleHandlingImpl oracleHandling) {
        this.oracleHandling = oracleHandling;
    }

    @GetMapping("/ready")
    public boolean isAPIReady() {

        if (oracleHandling.isConnectionOpen())
            return true;

        return false;
    }

    // Accepts Data as JSON from External Databases
    // And Stores it in the Local Database
    @PostMapping
    public int importData(@RequestBody Map<String, Object> payload) {

        //Specified Keys not found in request
        if (payload.get(TABLE_KEY) == null || payload.get(DATA_KEY) == null)
            throw new InvalidRequestException("Keys not found.");

        String tableName = payload.get(TABLE_KEY).toString();

        if (!oracleHandling.verifyTableName(tableName)) {
            throw new InvalidRequestException("Table does not exist.");
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            String dataJson = mapper.writeValueAsString(payload.get(DATA_KEY));

            log.info(dataJson);

            List<HashMap<String, Object>> objectList = mapper.readValue(dataJson,
                    new TypeReference<List<HashMap<String, Object>>>() {
                    });

            Set<String> columns = objectList.get(0).keySet();

            if (!oracleHandling.verifyColumnNames(tableName, columns))
                throw new InvalidRequestException("Column names don't exist.");


            return oracleHandling.importData(tableName, columns, objectList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidRequestException("");
        }
    }

}

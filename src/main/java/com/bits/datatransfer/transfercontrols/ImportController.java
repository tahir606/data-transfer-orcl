package com.bits.datatransfer.transfercontrols;

import com.bits.datatransfer.jcode.OracleHandlingImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static com.bits.datatransfer.transfercontrols.APIConstants.DATA_KEY;
import static com.bits.datatransfer.transfercontrols.APIConstants.TABLE_KEY;

@RestController
@RequestMapping("/import")
public class ImportController {

    private final OracleHandlingImpl oracleHandling;

    public ImportController(OracleHandlingImpl oracleHandling) {
        this.oracleHandling = oracleHandling;
    }

    // Accepts Data as JSON from External Databases
    // And Stores it in the Local Database
    @PostMapping
    public void importData(@RequestBody Map<String, Object> payload) {

        //Table Name not found in request
        if (payload.get(TABLE_KEY) == null || payload.get(DATA_KEY) == null)
            throw new InvalidRequestException();

        String tableName = payload.get(TABLE_KEY).toString();

        if (!oracleHandling.verifyTableName(tableName)) {
            throw new TableNotFoundException();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            String dataJson = mapper.writeValueAsString(payload.get(DATA_KEY));

            System.out.println(dataJson);

            List<HashMap<String, Object>> objectList = mapper.readValue(dataJson,
                    new TypeReference<List<HashMap<String, Object>>>() {
                    });

            Set<String> columns = objectList.get(0).keySet();

            if (!oracleHandling.verifyColumnNames(tableName, columns))
                throw new InvalidRequestException();


            oracleHandling.insertDynamicQuery(tableName, columns, objectList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidRequestException();
        }
    }

}

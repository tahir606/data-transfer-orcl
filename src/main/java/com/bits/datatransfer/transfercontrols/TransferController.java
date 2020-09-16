package com.bits.datatransfer.transfercontrols;

import com.bits.datatransfer.dummy.DummyRepository;
import com.bits.datatransfer.jcode.OracleHandlingImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final OracleHandlingImpl oracleHandling;

    private final static String TABLE_KEY = "tableName";

    public TransferController(OracleHandlingImpl oracleHandling) {
        this.oracleHandling = oracleHandling;
    }

    // Accepts Data as JSON from External Databases
    // And Stores it in the Local Database
    @PostMapping
    public void importData(@RequestBody Map<String, Object> payload) {
        System.out.println(payload.get(TABLE_KEY));
        oracleHandling.insertQuery();
    }

    // Sends Data as JSON from Local Database
    // To Specified External Databases
    @GetMapping
    public void exportData() {

    }
}

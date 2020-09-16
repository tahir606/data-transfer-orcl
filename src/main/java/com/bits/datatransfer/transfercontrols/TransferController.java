package com.bits.datatransfer.transfercontrols;

import com.bits.datatransfer.dummy.DummyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final DummyRepository dummyRepository;

    private final static String TABLE_KEY = "tableName";

    public TransferController(DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    // Accepts Data as JSON from External Databases
    // And Stores it in the Local Database
    @PostMapping
    public void importData(@RequestBody Map<String, Object> payload) {
        System.out.println(payload.get(TABLE_KEY));
    }

    // Sends Data as JSON from Local Database
    // To Specified External Databases
    @GetMapping
    public void exportData() {

    }
}

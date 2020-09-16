package com.bits.datatransfer.transfercontrols;

import com.bits.datatransfer.dummy.DummyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final DummyRepository dummyRepository;

    public TransferController(DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    // Accepts Data as JSON from External Databases
    // And Stores it in the Local Database
    @PostMapping
    public void importData() {

    }

    // Sends Data as JSON from Local Database
    // To Specified External Databases
    @GetMapping
    public void exportData() {

    }
}

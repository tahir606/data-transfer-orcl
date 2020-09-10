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

    @GetMapping
    public void receiveData() {

    }

    @PostMapping
    public void sendData() {

    }
}

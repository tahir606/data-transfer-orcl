package com.bits.datatransfer.settings;

import com.bits.datatransfer.jcode.FileHandlingImpl;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final FileHandlingImpl fileHandling;

    public SettingsController(FileHandlingImpl fileHandling) {
        this.fileHandling = fileHandling;
    }

    @PostMapping
    EntityModel<ImportSettings> saveSettings(@RequestBody ImportSettings importSettings) {
        fileHandling.saveImportSettings(importSettings);
        return EntityModel.of(fileHandling.getImportSettings(),
                linkTo(methodOn(SettingsController.class).saveSettings(null)).withSelfRel());
    }

    @GetMapping
    EntityModel<ImportSettings> getSettings() {
        return EntityModel.of(fileHandling.getImportSettings(),
                linkTo(methodOn(SettingsController.class).getSettings()).withSelfRel());
    }


    @PostMapping("/export")
    ResponseEntity<?> saveExportSettings(@RequestBody Map<String, Object> payload) {

        String apiLink = payload.get("apiLink").toString();
        String tableNames = payload.get("tableNames").toString();

        System.out.println(apiLink);
        System.out.println(tableNames);

        fileHandling.saveExportSettings(new ExportSettings(apiLink, tableNames));

        return ResponseEntity.ok(null);
    }
}

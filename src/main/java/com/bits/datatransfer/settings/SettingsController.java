package com.bits.datatransfer.settings;

import com.bits.datatransfer.jcode.FileHandling;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final FileHandling fileHandling;

    public SettingsController(FileHandling fileHandling) {
        this.fileHandling = fileHandling;
    }

    @PostMapping
    public EntityModel<Settings> saveSettings(@RequestBody Settings settings) {
        fileHandling.saveSettings(settings);
        return EntityModel.of(fileHandling.getSettings(),
                linkTo(methodOn(SettingsController.class).saveSettings(null)).withSelfRel());
    }

    @GetMapping
    public EntityModel<Settings> getSettings() {
        return EntityModel.of(fileHandling.getSettings(),
                linkTo(methodOn(SettingsController.class).getSettings()).withSelfRel());
    }
}

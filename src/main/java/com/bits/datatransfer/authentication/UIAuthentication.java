package com.bits.datatransfer.authentication;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class UIAuthentication {

    public UIAuthentication() {
    }

    @PostMapping
    public String login(@RequestBody String json) {
        return "{\"token\":\"1234\",\"user\":\"admin\"}";
    }

    @GetMapping
    public String verify(@RequestParam String token) {
        if (token.equals("1234"))
            return "{\"token\":\"1234\",\"user\":\"admin\"}";
        return "{}";
    }

}

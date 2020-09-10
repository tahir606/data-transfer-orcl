package com.bits.datatransfer;

import com.bits.datatransfer.dummy.Dummy;
import com.bits.datatransfer.dummy.DummyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DummyRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Dummy("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Dummy("Frodo Baggins", "thief")));
        };
    }

}

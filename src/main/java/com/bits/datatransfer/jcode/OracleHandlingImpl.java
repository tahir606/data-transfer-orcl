package com.bits.datatransfer.jcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OracleHandlingImpl implements OracleHandling {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insertQuery() {
        System.out.println("Reading movie records...");
        System.out.printf("%-30.30s  %-30.30s%n", "Title", "Description");
        jdbcTemplate.query("SELECT * FROM dummy_table", (rs)-> {
            System.out.printf("%-30.30s  %-30.30s  %-30.30s%n", rs.getString("id"), rs.getString("key"), rs.getString("value"));
        });
    }
}

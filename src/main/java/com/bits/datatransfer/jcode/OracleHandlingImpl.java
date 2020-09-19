package com.bits.datatransfer.jcode;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.bits.datatransfer.transfercontrols.APIConstants.ISIMPORTED;

@Service
public class OracleHandlingImpl implements OracleHandling {

    JdbcTemplate jdbcTemplate;

    public OracleHandlingImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Dynamically Generates Insert Query
    // Based on Table Names and Column Names Provided
    @Override
    public void insertDynamicQuery(String tableName, Set<String> columns, List<HashMap<String, Object>> rows) {

        String query = "INSERT ALL ";

        // Creates a string of column names for example "(key,value)"
        String columnNames = "(";
        for (String column : columns) {
            columnNames = columnNames + column + ",";
        }
        columnNames = columnNames.substring(0, columnNames.length() - 1) + ")";

        for (HashMap<String, Object> row : rows) {
            query = query + " INTO " + tableName + " " + columnNames + " VALUES (";
            for (String column : columns) {
                query = query + "'" + row.get(column).toString() + "',";
            }
            query = query.substring(0, query.length() - 1) + ") ";
        }

        query = query + " SELECT * FROM DUAL ";

        System.out.println(query);

        int response = jdbcTemplate.update(query);
        System.out.println("Response: " + response);
    }

    @Override
    public boolean verifyTableName(String tableName) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT TNAME from TAB where TNAME = ?", tableName.toUpperCase());  //Table Names must always be in Uppercase

        return !result.isEmpty();
    }

    @Override
    public boolean verifyColumnNames(String tableName, Set<String> columns) {

        for (String column : columns) {
            List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT COLUMN_NAME AS FOUND " +
                    "FROM USER_TAB_COLS " +
                    "WHERE TABLE_NAME = ? " +
                    "AND COLUMN_NAME = ? ", tableName.toUpperCase(), column.toUpperCase());

            if (result.isEmpty())
                return false;
        }

        return true;
    }

    @Override
    public List<Map<String, Object>> getNotImportedTableData(String tableName) {

        String query = "SELECT * FROM " + tableName +
                " WHERE " + ISIMPORTED + " IS NULL ";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        return result;
    }

    @Override
    public void markTableDataImported(String tableName) {

        String query = "UPDATE " + tableName +
                " SET " + ISIMPORTED + " = 1 " +
                " WHERE " + ISIMPORTED + " IS NULL ";

        int response = jdbcTemplate.update(query);
        System.out.println(response);

    }
}

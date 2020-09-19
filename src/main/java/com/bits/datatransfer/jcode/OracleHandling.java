package com.bits.datatransfer.jcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OracleHandling {

    void insertDynamicQuery(String tableName, Set<String> columns, List<HashMap<String, Object>> data);

    // Check if Table Exists
    boolean verifyTableName(String tableName);

    //Check if Column Name Exists in Table
    boolean verifyColumnNames(String tableName, Set<String> columns);

    List<Map<String, Object>> getNotImportedTableData(String tableName);

    void markTableDataImported(String tableName);
}

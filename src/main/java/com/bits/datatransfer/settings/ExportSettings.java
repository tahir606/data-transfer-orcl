package com.bits.datatransfer.settings;

public class ExportSettings extends Settings {

    private String[] tableNames;
    private String tableNamesByComma;

    public ExportSettings(String ip) {
        super(ip);
    }

    public ExportSettings(String ip, String[] tableNames) {
        super(ip);
        this.tableNames = tableNames;
    }

    public ExportSettings(String ip, String tableNamesByComma) {
        super(ip);
        this.tableNamesByComma = tableNamesByComma;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public String getTableNamesByComma() {
        return tableNamesByComma;
    }

    public void setTableNamesByComma(String tableNamesByComma) {
        this.tableNamesByComma = tableNamesByComma;
    }

}

package com.bits.datatransfer.settings;

public class ImportSettings extends Settings {

    private String dbUser;
    private String dbPass;

    public ImportSettings(String ip, String dbUser, String dbPass) {
        super(ip);
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "ip='" + super.getIp() + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPass='" + dbPass + '\'' +
                '}';
    }
}

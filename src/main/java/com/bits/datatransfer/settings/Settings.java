package com.bits.datatransfer.settings;

public class Settings {

    private String ip;
    private String dbUser;
    private String dbPass;

    public Settings() {
    }

    public Settings(String ip, String dbUser, String dbPass) {
        this.ip = ip;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
                "ip='" + ip + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPass='" + dbPass + '\'' +
                '}';
    }
}

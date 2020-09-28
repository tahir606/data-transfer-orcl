package com.bits.datatransfer.transfercontrols;

import okhttp3.MediaType;

public class APIConstants {

//    public static final int PORT = 8081;

    //MEDIA_TYPES
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //ENDPOINTS
    public static final String ISAPIREADY = "/ready";
    public static final String IMPORT = "/import";

    public final static String TABLE_KEY = "tableName",
            DATA_KEY = "data",
            ISIMPORTED = "ISIMP";

}

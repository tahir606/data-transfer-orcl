package com.bits.datatransfer.transfercontrols;

import okhttp3.MediaType;

public class APIConstants {

    public static boolean API_LINK_STATUS = false;

    public static final int PORT = 8081;

    //API_LINK_ADDRESS
//    public static String API_LINK = "http://" + "192.168.100.28" + ":" + PORT;

    //MEDIA_TYPES
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //ENDPOINTS
    public static String EXPORT = "/import";

    public final static String TABLE_KEY = "tableName",
            DATA_KEY = "data",
            ISIMPORTED = "ISIMP";

}

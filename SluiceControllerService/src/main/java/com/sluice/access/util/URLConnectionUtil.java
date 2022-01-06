package com.sluice.access.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;

/**
 * @author : missy
 * @date : 2022-01-06 22:34
 */
public class URLConnectionUtil {

    private URLConnectionUtil() {
    }

    public static String doGet(String urlString, String outputEnc) throws Exception {
        URL url = new URL(urlString);

        URLConnection connection = url.openConnection();

        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        connection.connect();

        String string;
        try (InputStream inputStream = connection.getInputStream()) {
            string = IOUtils.toString(inputStream, outputEnc);
        }

        return string;
    }
}

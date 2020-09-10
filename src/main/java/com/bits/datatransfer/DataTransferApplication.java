package com.bits.datatransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

@SpringBootApplication
public class DataTransferApplication {

    private static Resource resource = new ClassPathResource("static/settings.txt");

    public static void main(String[] args) {
        SpringApplication.run(DataTransferApplication.class, args);

        try {
            InputStream input = resource.getInputStream();
//            File file = resource.getFile();

            System.out.println("Text read: " + readFromInputStream(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}

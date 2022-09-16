package com.sigma.oilstation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesUpdate {

    public static void updateOilProperties(Long limit) throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/application-prod.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("src/main/resources/application-prod.properties");
        props.setProperty("oil.limit.prod", String.valueOf(limit));
        props.store(out, null);
        out.close();
    }

}

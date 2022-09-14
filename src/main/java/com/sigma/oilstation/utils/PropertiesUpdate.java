package com.sigma.oilstation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



public class PropertiesUpdate {
    public void updateOilProperties(String limit) throws IOException {
        FileInputStream in = new FileInputStream("application-prod.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("application-prod.properties");
        props.setProperty("oil.limit", limit);
        props.store(out, null);
        out.close();
    }

    public void updateDebtProperties(String limit) throws IOException {
        FileInputStream in = new FileInputStream("application-prod.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("application-prod.properties");
        props.setProperty("debt.limit", limit);
        props.store(out, null);
        out.close();
    }
}

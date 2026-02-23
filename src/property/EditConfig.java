package property;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

/**
 * Created by ASUP-9 on 28.07.2017.
 */
public class EditConfig {
    private Properties props;
    private String password;
    private String DB_IP;
    private String DB_PORT;
    private String DB_USER;
    private String DB_PASS;
    private String DB_NAME;

    public EditConfig(String filepath) {
        props = new Properties();
        File file = new File(filepath);
        try {
            props.load(new FileInputStream(file));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        password = props.getProperty("PASSWORD");
        DB_IP = props.getProperty("DB_IP");
        DB_PORT = props.getProperty("DB_PORT");
        DB_USER = props.getProperty("DB_USER");
        DB_PASS = props.getProperty("DB_PASS");
        DB_NAME = props.getProperty("DB_NAME");
    }

    public void updateProperty(String nameProperty, String valueProperty){
        props.put(nameProperty, valueProperty);
        Set<String> propsSet = props.stringPropertyNames();
        Object[] namesProps = propsSet.toArray();
        for (Object namesProp : namesProps) {
            if (!Objects.equals(namesProp.toString(), nameProperty)) {
                try {
                    String newString = props.getProperty(namesProp.toString()).length() > 0 ? new String(props.getProperty(namesProp.toString()).getBytes("utf-16"), Charset.forName("windows-1251")).substring(2).replaceAll("['\u0000']", "") : "";
                    props.put(namesProp, newString);
                } catch (UnsupportedEncodingException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }

        try {
            FileOutputStream out = new FileOutputStream("config.ini");
            Writer writer = new OutputStreamWriter(out, "windows-1251");
            props.store(writer, "/* properties updated */");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String getPassword() {
        return password;
    }

    public String getDB_IP() {
        return DB_IP;
    }

    public String getDB_PORT() {
        return DB_PORT;
    }

    public String getDB_USER() {
        return DB_USER;
    }

    public String getDB_PASS() {
        return DB_PASS;
    }

    public String getDB_NAME() {
        return DB_NAME;
    }
}

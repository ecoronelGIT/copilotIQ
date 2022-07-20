package com.copilotiq;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Application {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("missing property file parameter");
            return;
        }
        File propertyFile = new File(args[0]);
        if (!propertyFile.isFile()) {
            System.out.println("missing property file parameter");
            return;
        }
        Application app = new Application();
        app.run(propertyFile);
    }

    private void run(File propertyFile) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(propertyFile));
        PropertiesParser parser = new PropertiesParser(props);
        parser.parse();

        printProperties(props);
    }

    private void printProperties(Properties props) {
        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            System.out.println(key+"="+value);
        }
    }
}

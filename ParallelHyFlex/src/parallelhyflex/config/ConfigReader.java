package parallelhyflex.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import parallelhyflex.communication.Communication;

/**
 *
 * @author kommusoft
 */
public class ConfigReader {
    
    private static final ConfigReader instance = new ConfigReader();
    private static final Logger LOG = Logger.getLogger(ConfigReader.class.getName());
    
    public static ConfigReader getInstance() {
        return instance;
    }
    private final ArrayList<ConfigReadable> configs = new ArrayList<>();
    
    private ConfigReader() {
        this.configs.add(new ParHyFlexConfigReadable());
        this.configs.add(new ParAdapHHConfigReadable());
    }
    
    public void readFromStream(InputStream is) {
        Scanner sc = new Scanner(is);
        sc.useDelimiter(" ");
        while (sc.hasNextInt()) {
            try {
            int id = sc.nextInt();
            String val = sc.nextLine().trim();
            configOne(id, val);
            }
            catch(Exception e) {
                Communication.log(e);
                Communication.log(sc.nextLine());
            }
        }
    }
    
    public void readFromFile(String filename) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            this.readFromStream(fis);
        }
    }
    
    private void configOne(int id, String val) {
        for (ConfigReadable config : configs) {
            if (config.canHandle(id)) {
                if (config.handle(id, val)) {
                    return;
                } else {
                    Communication.log("Failed to handle config id");
                }
            }
        }
        Communication.log("Failed to handle config id");
    }
}
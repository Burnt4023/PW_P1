package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Properties properties = new Properties();

    // Carga el archivo de propiedades al iniciar la clase
    static {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("properties.txt"));
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            System.err.println("[ERROR] No se pudo cargar el archivo de configuración.");
            e.printStackTrace();
        }
    }

    // Método para obtener valores de propiedades
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getDataDirectory() {
        return getProperty("data_directory");
    }

    public static String getUsersFilePath() {
        return getProperty("users_file");
    }

    public static String getPistasFilePath() {
        return getProperty("pistas_file");
    }

    public static String getMaterialsFilePath() {
        return getProperty("materials_file");
    }
}

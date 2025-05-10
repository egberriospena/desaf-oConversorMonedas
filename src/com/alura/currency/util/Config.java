package com.alura.currency.util;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class Config {
    private static final Properties props = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Archivo no encontrado: " + CONFIG_FILE +
                        "\nAsegúrate de que esté en src/resources/");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar " + CONFIG_FILE +
                    "\nCausa: " + e.getMessage(), e);
        }
    }

    public static String getApiKey() {
        String key = props.getProperty("api.key");
        if (key == null || key.trim().isEmpty()) {
            throw new RuntimeException(
                    "API key no configurada. Agrega esta línea a " + CONFIG_FILE + ":\napi.key=TU_CLAVE"
            );
        }
        return key;
    }
}
package com.ilhamyp.api.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigManager {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("src/test/resources")
            .ignoreIfMissing()
            .load();

    public static String get(String key) {
        String value = System.getenv(key);
        return value != null ? value : dotenv.get(key);
    }
}
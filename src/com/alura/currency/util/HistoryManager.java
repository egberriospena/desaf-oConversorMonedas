package com.alura.currency.util;

import com.alura.currency.model.ConversionRecord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Administra el historial de conversiones.
 */
public class HistoryManager {

    private static final String FILE_NAME = "conversion_history.json";
    private final List<ConversionRecord> history = new ArrayList<>();
    private final Gson gson;

    public HistoryManager() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        loadHistory();
    }

    public void addRecord(ConversionRecord record) {
        history.add(record);
    }

    public List<ConversionRecord> getHistory() {
        return history.stream()
                .sorted(Comparator.comparing(ConversionRecord::getTimestamp))
                .toList();
    }

    public void saveHistory() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(history, writer);
        } catch (Exception e) {
            System.err.println("❌ Error al guardar historial: " + e.getMessage());
        }
    }

    private void loadHistory() {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                try (FileReader reader = new FileReader(file)) {
                    Type listType = new TypeToken<ArrayList<ConversionRecord>>() {}.getType();
                    List<ConversionRecord> loaded = gson.fromJson(reader, listType);
                    if (loaded != null) {
                        history.addAll(loaded);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("⚠️ No se pudo cargar historial anterior: " + e.getMessage());
        }
    }
}
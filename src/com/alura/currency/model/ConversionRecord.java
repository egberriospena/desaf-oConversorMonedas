package com.alura.currency.model;

import java.time.LocalDateTime;

/**
 * Representa una conversión realizada por el usuario.
 */
public class ConversionRecord {
    private LocalDateTime timestamp;
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double result;

    public ConversionRecord() {
    }

    public ConversionRecord(LocalDateTime timestamp, String fromCurrency, String toCurrency, double amount, double result) {
        this.timestamp = timestamp;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public double getResult() {
        return result;
    }
}
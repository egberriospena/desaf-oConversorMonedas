package com.alura.currency.model;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

/**
 * Clase que representa la estructura del JSON recibido desde la API de tasas de cambio.
 */
public class ExchangeRate {
    @SerializedName("result")
    private String result;

    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("time_last_update_utc")
    private String timeLastUpdateUtc;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    /**
     * Devuelve el estado de la solicitud (por ejemplo, "success").
     */
    public String getResult() {
        return result;
    }

    /**
     * Devuelve el código de la moneda base utilizada para la conversión.
     */
    public String getBaseCode() {
        return baseCode;
    }

    /**
     * Devuelve la fecha y hora de la última actualización, en formato UTC.
     */
    public String getTimeLastUpdateUtc() {
        return timeLastUpdateUtc;
    }

    /**
     * Devuelve el mapa de tasas de conversión, donde cada clave es el código de moneda y el valor es el tipo de cambio.
     */
    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    /**
     * Devuelve el tipo de cambio para una moneda específica.
     *
     * @param currencyCode Código ISO de la moneda deseada (por ejemplo, "EUR").
     * @return Valor numérico del tipo de cambio, o null si no existe.
     */
    public Double getRateFor(String currencyCode) {
        return conversionRates != null ? conversionRates.get(currencyCode) : null;
    }
}
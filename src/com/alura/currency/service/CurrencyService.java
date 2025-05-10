package com.alura.currency.service;

import com.alura.currency.model.ExchangeRate;
import com.alura.currency.util.Config;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Clase encargada de consumir la API ExchangeRate y obtener tasas de conversión.
 */
public class CurrencyService {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final int TIMEOUT_SECONDS = 10;
    private final HttpClient httpClient;
    private final String apiKey;

    /**
     * Constructor que inicializa el cliente HTTP y la API key.
     */
    public CurrencyService() {
        this.apiKey = Config.getApiKey();
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Obtiene las tasas de cambio actualizadas para una moneda base.
     *
     * @param baseCurrency Código de la moneda base (por ejemplo "USD").
     * @return Objeto ExchangeRate con las tasas de cambio.
     * @throws RuntimeException si hay error en la solicitud o el JSON es inválido.
     */
    public ExchangeRate getLatestRates(String baseCurrency) {
        String url = BASE_URL + apiKey + "/latest/" + baseCurrency;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Error en la API: Código " + response.statusCode());
            }

            // Convertir JSON a objeto ExchangeRate
            return new Gson().fromJson(response.body(), ExchangeRate.class);

        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Error al interpretar la respuesta JSON", e);
        } catch (Exception e) {
            throw new RuntimeException("Error en la solicitud HTTP", e);
        }
    }
}
package com.alura.currency;

import com.alura.currency.model.ConversionRecord;
import com.alura.currency.model.ExchangeRate;
import com.alura.currency.service.CurrencyService;
import com.alura.currency.util.HistoryManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Clase principal que ejecuta el conversor de monedas y administra el historial.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CurrencyService service = new CurrencyService();
    private static final HistoryManager historyManager = new HistoryManager();

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            System.out.print("Elija una opción válida: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> convertir("USD", "ARS");
                case "2" -> convertir("ARS", "USD");
                case "3" -> convertir("USD", "BRL");
                case "4" -> convertir("BRL", "USD");
                case "5" -> convertir("USD", "CLP");
                case "6" -> convertir("CLP", "USD");
                case "7" -> convertir("USD", "EUR");
                case "8" -> convertir("USD", "CNY");
                case "9" -> mostrarHistorial();
                case "10" -> salir();
                default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }
    }

    /**
     * Muestra el menú interactivo.
     */
    private static void mostrarMenu() {
        System.out.println("\n" + "*".repeat(50));
        System.out.println("✨  Conversor de monedas Alura");
        System.out.println("*".repeat(50));
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar => Peso chileno");
        System.out.println("6) Peso chileno => Dólar");
        System.out.println("7) Dólar => Euro");
        System.out.println("8) Dólar => Yuan chino");
        System.out.println("9) Ver historial");
        System.out.println("10) Salir");
        System.out.println("*".repeat(50));
    }

    /**
     * Ejecuta una conversión entre dos monedas y la registra en el historial.
     */
    private static void convertir(String from, String to) {
        try {
            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad = Double.parseDouble(scanner.nextLine());

            ExchangeRate tasa = service.getLatestRates(from);
            Double valor = tasa.getRateFor(to);

            if (valor == null) {
                System.out.println("❌ Moneda no soportada: " + to);
                return;
            }

            double resultado = cantidad * valor;

            System.out.printf("✅ %.2f %s = %.2f %s\n", cantidad, from, resultado, to);

            ConversionRecord record = new ConversionRecord(
                    LocalDateTime.now(),
                    from,
                    to,
                    cantidad,
                    resultado
            );
            historyManager.addRecord(record);

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Ingrese un valor numérico válido.");
        } catch (Exception e) {
            System.out.println("❌ Error al realizar la conversión: " + e.getMessage());
        }
    }

    /**
     * Muestra todas las conversiones almacenadas ordenadas por fecha.
     */
    private static void mostrarHistorial() {
        var historial = historyManager.getHistory();

        if (historial.isEmpty()) {
            System.out.println("📂 No hay conversiones registradas.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("\n📜 Historial de conversiones:");
        historial.forEach(record -> System.out.printf(
                "[%s] %.2f %s → %.2f %s\n",
                record.getTimestamp().format(formatter),  // ✅ Fecha legible sin nanosegundos
                record.getAmount(),
                record.getFromCurrency(),
                record.getResult(),
                record.getToCurrency()
        ));
    }

    /**
     * Finaliza el programa y guarda el historial.
     */
    private static void salir() {
        historyManager.saveHistory();
        System.out.println("📁 Historial guardado correctamente.");
        System.out.println("👋 ¡Gracias por usar el Conversor de monedas Alura!");
        System.exit(0);
    }
}
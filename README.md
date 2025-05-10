# 💱 Conversor de Monedas Alura

Este es un proyecto de consola desarrollado en **Java** para aplicar conceptos de programación orientada a objetos, consumo de APIs REST, manipulación de JSON y persistencia local.  
Permite convertir entre distintas monedas, visualizar un historial de conversiones y guardar los registros en formato JSON.

---

## 🧩 Componentes del sistema

### 🏛️ Estructura de paquetes

```
com.alura.currency
├── model            → Clases de dominio (ExchangeRate, ConversionRecord)
├── service          → Lógica para consumir la API de tipos de cambio
├── util             → Utilidades: configuración, historial, adaptadores Gson
└── Main.java        → Interfaz de usuario por consola
```

### 📦 Clases clave

| Clase                     | Rol                                                                 |
|--------------------------|----------------------------------------------------------------------|
| `Main`                   | Menú principal de interacción con el usuario                         |
| `CurrencyService`        | Realiza peticiones HTTP a la API ExchangeRate                        |
| `ExchangeRate`           | Mapea la respuesta JSON con tasas de cambio                          |
| `ConversionRecord`       | Representa una conversión (fecha, monedas, monto, resultado)         |
| `HistoryManager`         | Administra carga y guardado del historial en `conversion_history.json` |
| `LocalDateTimeAdapter`   | Adaptador personalizado para serializar fechas con Gson              |

---

## ⚙️ Requisitos

- Java 17 o superior
- IntelliJ IDEA o cualquier otro IDE compatible con Java
- Biblioteca [Gson](https://github.com/google/gson) (puede agregarse como `.jar` en `/libs` o vía Maven)
- Conexión a Internet (para consumir la API)

---

## 🚀 Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/currency-converter-java.git
   cd currency-converter-java
   ```

2. Abre el proyecto en IntelliJ

3. Asegúrate de tener el archivo de configuración `config.properties` en:
   ```
   src/main/resources/config.properties
   ```

   Con este contenido:
   ```properties
   api.key=TU_API_KEY_DE_EXCHANGERATE
   ```

4. Ejecuta la clase `Main`

5. Interactúa con el menú para realizar conversiones entre:

   - Dólar estadounidense (USD)
   - Euro (EUR)
   - Yuan chino (CNY)
   - Peso argentino (ARS)
   - Real brasileño (BRL)
   - Peso chileno (CLP)

6. Al salir, el sistema guardará el historial de conversiones en `conversion_history.json`.

---

## 📊 Insights técnicos destacados

- ✔️ Uso de `HttpClient` para consumo de APIs REST
- ✔️ Mapeo automático de JSON a POJOs usando `Gson`
- ✔️ Persistencia local de historial en formato JSON
- ✔️ Uso de `LocalDateTime` y adaptador personalizado para compatibilidad con Gson
- ✔️ Separación clara de responsabilidades por paquetes
- ✔️ Formato de impresión legible de fechas (`yyyy-MM-dd HH:mm:ss`)
- ✔️ Historial persistente entre ejecuciones, ordenado cronológicamente


---

## 📜 Licencia

Este proyecto es de uso libre para fines educativos. Puedes adaptarlo y mejorarlo citando su origen.

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        //Definicion de la URL
        String APIkey = "828bb8c3ebd4a4034f1abb63";
        String url_str = "https://v6.exchangerate-api.com/v6/" + APIkey + "/latest/USD";
        try {
            // Request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url_str)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
            var opcion = 0;
            while (true) {
                System.out.println("¿Desea realizar un cambio de su moneda? 1.- SI 2.- NO");
                opcion = Integer.parseInt(lectura.nextLine());
                if (opcion == 2) {
                    System.out.println("Gracias por visitarnos!!!");
                    break;
                }
                //Ingreso de valores de monedas
                System.out.println("Ingresa la cantidad, tu moneda y la moneda a la que quieres transformar la cantidad. (En ese orden)");
                System.out.println("Algunas alternativas de las monedas son: ARS(Peso Argentino), BOB(Boliviano), CLP(Peso Chileno), PEN(Nuevo Sol Peruano), MXN(Peso Mexicano), etc");
                var cantidad = lectura.nextLine();
                var valor1 = lectura.nextLine();
                var valor2 = lectura.nextLine();

                // Buscar el valor asociado
                float valorCambio1 = conversionRates.get(valor1.toUpperCase()).getAsFloat();
                float valorCambio2 = conversionRates.get(valor2.toUpperCase()).getAsFloat();

                // Crear objetos Moneda con esos valores
                Moneda moneda1 = new Moneda("Moneda 1", valor1, valorCambio1);
                Moneda moneda2 = new Moneda("Moneda 2", valor2, valorCambio2);

                // Mostrar los objetos Moneda
                System.out.println(moneda1);
                System.out.println(moneda2);
                System.out.println("Tu cantidad de: " + cantidad +
                        " de " + moneda1.getAbreviatura() +
                        ", es: " + moneda1.CambioMoneda(Float.parseFloat(cantidad), moneda1 , moneda2) +
                        " de " + moneda2.getAbreviatura());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}
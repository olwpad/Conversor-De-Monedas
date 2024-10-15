package org.example;

import org.json.JSONObject;

import java.util.Locale;
import java.util.Scanner;

public class Service {
    private final Scanner scanner;

    public Service() {
        this.scanner = new Scanner(System.in);
    }

    public void convertidorMoneda(JSONObject rates) {
        double resultado = 0.0;

        while (true) {
            try {
                System.out.println("Bienvenido al convertidor de monedas");
                System.out.println("Si no conoces las monedas disponibles, ingresa \"1\" para ver el cartel de monedas famosas. Si ya conoces las monedas, puedes presionar \"Enter\" para continuar." );
                String input = scanner.nextLine();
                if(input.equals("1")){
                    cartelMoneda();
                }
                String monedaOrigen = obtenerEntrada("Ingrese la moneda de origen (ej. USD, EUR): ");
                String monedaDestino = obtenerEntrada("Ingrese la moneda de destino (ej. COP, JPY): ");
                double valorOrigen = obtenerValorMoneda(rates, monedaOrigen);
                double valorDestino = obtenerValorMoneda(rates, monedaDestino);

                double cantidad = obtenerCantidad();
                resultado = calcularConversion(cantidad, valorOrigen, valorDestino);

                System.out.printf("El resultado es: %.2f %s%n", resultado, monedaDestino);

                if (!deseaContinuar()) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
        mensajeDespedida();
    }

    private String obtenerEntrada(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine().toUpperCase(Locale.ROOT);
    }

    private double obtenerValorMoneda(JSONObject rates, String moneda) {
        if (!rates.has(moneda)) {
            throw new IllegalArgumentException("La moneda " + moneda + " no es válida.");
        }
        return rates.getDouble(moneda);
    }

    private double obtenerCantidad() {
        System.out.print("Ingrese la cantidad a convertir: ");
        String input = scanner.nextLine();
        return Double.parseDouble(input);
    }

    private double calcularConversion(double cantidad, double valorOrigen, double valorDestino) {
        return cantidad * valorDestino / valorOrigen;
    }

    private boolean deseaContinuar() {
        System.out.print("¿Desea hacer otra conversión? 1: sí, 0: no: ");
        String input = scanner.nextLine();
        return Integer.parseInt(input) == 1;
    }

    private void cartelMoneda() {
        System.out.println("==========================================");
        System.out.println("       Cartel de Monedas Famosas        ");
        System.out.println("==========================================");
        System.out.printf("%-5s  %-25s%n", "Código", "Descripción");
        System.out.println("------------------------------------------");
        System.out.printf("%-5s  %-25s%n", "COP", "Peso colombiano");
        System.out.printf("%-5s  %-25s%n", "MXN", "Peso mexicano");
        System.out.printf("%-5s  %-25s%n", "ARS", "Peso argentino");
        System.out.printf("%-5s  %-25s%n", "BRL", "Real brasileño");
        System.out.printf("%-5s  %-25s%n", "CLP", "Peso chileno");
        System.out.printf("%-5s  %-25s%n", "PEN", "Sol peruano");
        System.out.printf("%-5s  %-25s%n", "UYU", "Peso uruguayo");
        System.out.printf("%-5s  %-25s%n", "PYG", "Guaraní paraguayo");
        System.out.printf("%-5s  %-25s%n", "VEF", "Bolívar venezolano");
        System.out.printf("%-5s  %-25s%n", "USD", "Dólar estadounidense");
        System.out.printf("%-5s  %-25s%n", "EUR", "Euro");
        System.out.printf("%-5s  %-25s%n", "JPY", "Yen japonés");
        System.out.printf("%-5s  %-25s%n", "GBP", "Libra esterlina");
        System.out.printf("%-5s  %-25s%n", "AUD", "Dólar australiano");
        System.out.printf("%-5s  %-25s%n", "CAD", "Dólar canadiense");
        System.out.printf("%-5s  %-25s%n", "CHF", "Franco suizo");
        System.out.printf("%-5s  %-25s%n", "CNY", "Yuan chino");
        System.out.printf("%-5s  %-25s%n", "SEK", "Corona sueca");
        System.out.printf("%-5s  %-25s%n", "NZD", "Dólar neozelandés");
        System.out.printf("%-5s  %-25s%n", "KRW", "Won surcoreano");
        System.out.printf("%-5s  %-25s%n", "NOK", "Corona noruega");
        System.out.printf("%-5s  %-25s%n", "RUB", "Rublo ruso");
        System.out.printf("%-5s  %-25s%n", "INR", "Rupia india");
        System.out.printf("%-5s  %-25s%n", "ZAR", "Rand sudafricano");
        System.out.printf("%-5s  %-25s%n", "HKD", "Dólar de Hong Kong");
        System.out.printf("%-5s  %-25s%n", "SGD", "Dólar de Singapur");
        System.out.printf("%-5s  %-25s%n", "IDR", "Rupia indonesia");
        System.out.printf("%-5s  %-25s%n", "THB", "Baht tailandés");
        System.out.printf("%-5s  %-25s%n", "MYR", "Ringgit malayo");
        System.out.println("==========================================");
    }

    private void  mensajeDespedida(){
        System.out.println("Gracias por usar nuestro convertidor de monedas");
    }

}

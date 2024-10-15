package org.example;
import java.util.Optional;
import org.json.JSONObject;

public class ExchangeRate {
    public static void main(String[] args) {
        Api api = new Api();
        JSONObject rates = null;
        Optional<JSONObject> ratesOptional = api.getExchangeRates();
        if (ratesOptional.isPresent()) {
            rates = ratesOptional.get();
            Service service = new Service();
            service.convertidorMoneda(rates);
        } else {
            System.out.println("No se pudo obtener las tasas de cambio. Intente de nuevo más tarde.");
        }
    }
}

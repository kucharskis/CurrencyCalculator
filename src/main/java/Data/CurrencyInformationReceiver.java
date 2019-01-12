package Data;

import Utilities.ConnectionChecker;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class CurrencyInformationReceiver implements DataReceiver<ExchangeRate, LocalDate> {

    private static final List<String> currencies = Arrays.asList("THB", "USD", "AUD", "HKD", "CAD", "NZD", "SGD", "EUR", "HUF", "CHF", "GBP", "UAH", "JPY",
            "CZK", "DKK", "ISK", "NOK", "SEK", "HRK", "RON", "BGN", "TRY", "ILS", "CLP", "PHP", "MXN", "ZAR", "BRL", "MYR", "RUB", "IDR", "INR", "KRW",
            "CNY", "XDR");

    @Override
    public ExchangeRate getData(String currency, LocalDate date) throws UnirestException {
        try {
            checkCurrency(currency);
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            HttpResponse<ExchangeRate> exchangeRateResponse = Unirest.get("http://api.nbp.pl/api/exchangerates/rates/c/{currency}/{date}/?format=json")
                    .routeParam("currency", currency)
                    .routeParam("date", formatter.print(date))
                    .asObject(ExchangeRate.class);
            return exchangeRateResponse.getBody();
        } catch (UnirestException ex) {
            if (ConnectionChecker.netIsAvailable()) {
                return getData(currency, date.minusDays(1)); //because rate is unavailable in weekend days and rate during weekend is the same as at friday
            } else {
                return new ExchangeRate();
            }
        }
    }

    @Override
    public ExchangeRate getData(String currency) throws UnirestException {
        checkCurrency(currency);
        HttpResponse<ExchangeRate> exchangeRateResponse = Unirest.get("https://api.nbp.pl/api/exchangerates/rates/c/{currency}/?format=json")
                .routeParam("currency", currency)
                .asObject(ExchangeRate.class);
        return exchangeRateResponse.getBody();
    }

    private void checkCurrency(String currency){
        if (!currencies.contains(currency)) {
            System.out.println(format("%s is invalid currency's name", currency));
            System.exit(0);
        }
    }
}

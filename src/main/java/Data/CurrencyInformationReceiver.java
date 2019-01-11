package Data;

import Utilities.ConnectionChecker;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CurrencyInformationReceiver implements DataReceiver<ExchangeRate, LocalDate> {

    @Override
    public ExchangeRate getData(String type, LocalDate date) throws UnirestException {
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            HttpResponse<ExchangeRate> exchangeRateResponse = Unirest.get("http://api.nbp.pl/api/exchangerates/rates/c/{type}/{date}/?format=json")
                    .routeParam("type", type)
                    .routeParam("date", formatter.print(date))
                    .asObject(ExchangeRate.class);
            return exchangeRateResponse.getBody();
        } catch (UnirestException ex) {
            if (ConnectionChecker.netIsAvailable()) {
                return getData(type, date.minusDays(1)); //because rate is unavailable in weekend days and rate during weekend is the same as at friday
            } else {
                return new ExchangeRate();
            }
        }
    }

    @Override
    public ExchangeRate getData(String type) throws UnirestException {
        HttpResponse<ExchangeRate> exchangeRateResponse = Unirest.get("https://api.nbp.pl/api/exchangerates/rates/c/{type}/?format=json")
                .routeParam("type", type)
                .asObject(ExchangeRate.class);
        return exchangeRateResponse.getBody();
    }
}

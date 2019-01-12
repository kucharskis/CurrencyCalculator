import Data.CurrencyInformationReceiver;
import Data.DataReceiver;
import Data.ExchangeRate;
import Printers.Printers;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import picocli.CommandLine.*;

import java.util.Arrays;
import java.util.List;

public class CommandLineApp {

    @Option(names = {"-m", "--Money"})
    double money = 100;

    @Option(names = {"-e", "--Earn"})
    boolean earnOption;

    @Option(names = {"-c", "--Currencies"})
    List<String> currencies = Arrays.asList("USD", "EUR", "GBP", "CHF");

    @Option(names = {"-da", "--DaysAgo"})
    int numberOfDays = 0;

    @Option(names = {"-ma", "--MonthsAgo"})
    int numberOfMonths = 0;

    @Option(names = {"-d", "--Date"})
    String date;

    public void run() throws UnirestException {
        UnirestStarter.UnirestStart();
        DataReceiver currencyInformationReceiver = new CurrencyInformationReceiver();

        if (!earnOption) {
            for (String currency : currencies) {
                System.out.println(Printers.printHowManyOtherCurrency(money, (ExchangeRate) currencyInformationReceiver.getData(currency.toUpperCase(),getLocalDate())));
            }
        } else {
            for (String currency : currencies) {
                System.out.println(Printers.printEarnedMoney(money, (ExchangeRate) currencyInformationReceiver.getData(currency.toUpperCase()),
                        (ExchangeRate) currencyInformationReceiver.getData(currency.toUpperCase(), getLocalDate()), currency));
            }
        }
    }

    private LocalDate getLocalDate() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        try {
            return formatter.parseLocalDate(date);
        } catch (Exception e) {
            if (date != null) {
                System.out.println("Invalid date.");
                System.exit(1);
            }
            return LocalDate.now().minusMonths(numberOfMonths).minusDays(numberOfDays);
        }
    }
}

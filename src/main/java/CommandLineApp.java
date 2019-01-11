import Data.CurrencyInformationReceiver;
import Data.DataReceiver;
import Data.ExchangeRate;
import Printers.Printers;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.joda.time.LocalDate;
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
    int numberOfMonths = 1;

    public void run() throws UnirestException {
        UnirestStarter.UnirestStart();
        DataReceiver currencyInformationReceiver = new CurrencyInformationReceiver();

        if (!earnOption) {
            for (String currency : currencies) {
                System.out.println(Printers.printHowManyOtherCurrency(money, (ExchangeRate) currencyInformationReceiver.getData(currency)));
            }
        } else {
            for (String currency : currencies) {
                System.out.println(Printers.printEarnedMoney(money, (ExchangeRate) currencyInformationReceiver.getData(currency.toLowerCase()),
                        (ExchangeRate) currencyInformationReceiver.getData(currency.toLowerCase(), LocalDate.now().minusMonths(numberOfMonths).minusDays(numberOfDays)),
                        currency, numberOfMonths, numberOfDays));
            }
        }
    }
}

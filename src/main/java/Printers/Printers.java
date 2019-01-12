package Printers;

import Data.ExchangeRate;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;

import static java.lang.String.format;

public class Printers {

    public static String printHowManyOtherCurrency(double money, ExchangeRate exchangeRate) {
        return format("%s ZL: %s %s. ASK rate: %s for day: %s.", money, CalculatorCurrency.howManyWithRate(exchangeRate, new BigDecimal(money)),
                exchangeRate.getCode().toUpperCase(), exchangeRate.getRates()[0].getAsk(), exchangeRate.getRates()[0].getEffectiveDate());
    }

    public static String printEarnedMoney(double money, ExchangeRate exchangeRateNow, ExchangeRate exchangeRateTimeAgo, String currency) {
        String statement;
        BigDecimal earnedMoney = CalculatorCurrency.moneyYouEarned(exchangeRateNow, exchangeRateTimeAgo, new BigDecimal(money));
        if (earnedMoney.signum() == 1) {
            statement = "earned";
        } else {
            statement = "lost";
        }

        earnedMoney = earnedMoney.abs();

        return format("Using %s ZL you %s %s ZL, buying %s. Time: (%s - %s). ASK: %s, BID: %s.", money, statement,
                earnedMoney, currency.toUpperCase(), exchangeRateTimeAgo.getRates()[0].getEffectiveDate(),
                exchangeRateNow.getRates()[0].getEffectiveDate(), exchangeRateTimeAgo.getRates()[0].getAsk(),
                exchangeRateNow.getRates()[0].getBid());
    }
}

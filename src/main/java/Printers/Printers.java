package Printers;

import Data.ExchangeRate;

import java.math.BigDecimal;

import static java.lang.String.format;

public class Printers {

    public static String printHowManyOtherCurrency(double money, ExchangeRate exchangeRate) {
        return format("%s ZL: %s %s. ASK rate: %s for day: %s.", money, CalculatorCurrency.howManyWithRate(exchangeRate, new BigDecimal(money)),
                exchangeRate.getCode().toUpperCase(), exchangeRate.getRates()[0].getAsk(), exchangeRate.getRates()[0].getEffectiveDate());
    }

    public static String printEarnedMoney(double money, ExchangeRate exchangeRateNow, ExchangeRate exchangeRateTimeAgo, String currency,
                                          int numberOfMonths, int numberOfDays) {
        String statement;
        String daysStatement = "";
        BigDecimal earnedMoney = CalculatorCurrency.moneyYouEarned(exchangeRateNow, exchangeRateTimeAgo, new BigDecimal(money));
        if (earnedMoney.signum() == 1) {
            statement = "earned";
        } else {
            statement = "lost";
        }

        earnedMoney = earnedMoney.abs();

        if (numberOfDays != 0) {
            daysStatement = format("and %s days ", numberOfDays);
        }
        return format("Using %s ZL you %s %s ZL, buying %s. Time: %s months %s(%s - %s).", money, statement,
                earnedMoney, currency.toUpperCase(), numberOfMonths, daysStatement,
                exchangeRateTimeAgo.getRates()[0].getEffectiveDate(), exchangeRateNow.getRates()[0].getEffectiveDate());

    }
}

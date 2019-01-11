package Printers;

import Data.ExchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorCurrency {

    public static BigDecimal howManyWithRate(ExchangeRate exchangeRate, BigDecimal money) {
        return money.divide(new BigDecimal(exchangeRate.getRates()[0].getAsk()), 2, RoundingMode.HALF_DOWN);
    }

    public static BigDecimal moneyMultiplyRate(ExchangeRate exchangeRate, BigDecimal money) {
        return money.multiply(new BigDecimal(exchangeRate.getRates()[0].getBid()));
    }

    public static BigDecimal moneyYouEarned(ExchangeRate exchangeRateNow, ExchangeRate exchangeRateTimeAgo, BigDecimal money){
        return (moneyMultiplyRate(exchangeRateNow, howManyWithRate(exchangeRateTimeAgo, money)).subtract(money)).setScale(2, RoundingMode.HALF_DOWN);
    }
}

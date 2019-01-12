import Data.ExchangeRate;
import Data.Rate;
import Printers.CalculatorCurrency;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CalculatorCurrencyTest {

    private static ExchangeRate exchangeRate1 = new ExchangeRate();
    private static ExchangeRate exchangeRate2 = new ExchangeRate();

    static {
        Rate rate1 = new Rate();
        rate1.setAsk("5.0");
        rate1.setBid("4.0");
        Rate[] rates1 = {rate1};
        exchangeRate1.setRates(rates1);

        Rate rate2 = new Rate();
        rate2.setAsk("10.0");
        rate2.setBid("9.0");
        Rate[] rates2 = {rate2};
        exchangeRate2.setRates(rates2);
    }

    @Test
    @Parameters(method = "parametersToTesthowManyWithRate")
    public void howManyWithRate(ExchangeRate exchangeRate, double money, BigDecimal expected) {
        assertEquals(expected, CalculatorCurrency.howManyWithRate(exchangeRate, new BigDecimal(money)));
    }

    private Object[] parametersToTesthowManyWithRate() {
        return new Object[]{
                new Object[]{exchangeRate1, 15.0, new BigDecimal(3).setScale(2)},
                new Object[]{exchangeRate2, 20.0, new BigDecimal(2).setScale(2)}
        };
    }
}
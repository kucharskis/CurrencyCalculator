package Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Arrays;

public class ExchangeRate {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "table",
            "currency",
            "code",
            "rates"
    })

    @JsonProperty("table")
    private String table;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rates")
    private Rate[] rates;

    public Rate[] getRates() {
        return rates;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Data.ExchangeRate{" +
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + Arrays.toString(rates) +
                '}';
    }
}



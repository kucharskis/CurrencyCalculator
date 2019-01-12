package Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Rate {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "no",
            "effectiveDate",
            "bid",
            "ask"
    })

    @JsonProperty("no")
    private String no;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("bid")
    private String bid;
    @JsonProperty("ask")
    private String ask;

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getBid() {
        return bid;
    }

    public String getAsk() {
        return ask;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    @Override
    public String toString() {
        return "Data.Rate{" +
                "no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", bid='" + bid + '\'' +
                ", ask='" + ask + '\'' +
                '}';
    }
}

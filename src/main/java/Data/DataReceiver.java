package Data;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface DataReceiver<T,R> {

    public T getData(String type1, R type2) throws UnirestException;
    public T getData(String type) throws UnirestException;
}

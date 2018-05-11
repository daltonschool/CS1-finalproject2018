import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.xpath.SourceTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.*;

public class Stock {

    String Stock = "";
    double value;


    static Stock fetch(String ticker) throws UnirestException {

        // These code snippets use an open-source library. https://stock-quote-api.p.mashape.com/stock
        HttpResponse<String> response = Unirest.get("http://stocks.lattelib.com/MODApis/Api/v2/Quote?symbol=" + ticker)
                .asString();

        System.out.println(response.getBody());
        Document doc = Jsoup.parse(response.getBody());

        //If you want to include more information about the stock, you can run the line of code below to see the all of the information available.
//        System.out.println(doc.body());

        Stock stock_info = new Stock();

        stock_info.Stock=doc.body().getElementsByTag("name").text();
        stock_info.value=Double.parseDouble(doc.body().getElementsByTag("lastprice").text());

        return stock_info;
    }

}


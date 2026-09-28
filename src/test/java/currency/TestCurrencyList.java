package currency;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class TestCurrencyList {
    static Response apiResponse;
    static Map<String, String> currencyList;
    static ArrayList<String> currencyListKeys = new ArrayList<>();

    @BeforeClass
    public void getCurrencyList() throws URISyntaxException {
        apiResponse = given().urlEncodingEnabled(false).get(new URI("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies.json"));
        currencyList = apiResponse.jsonPath().getMap("$");
        currencyListKeys.addAll(currencyList.keySet());

        assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void testCurrencyListLength() {
        int listLength = currencyList.size();
        assertTrue(listLength >= 20);

        System.out.println("API returns " + listLength + " elements.");
    }

    @Test
    public void testGbpAndUsd() {
        assertTrue(currencyList.containsKey("gbp"));
        assertTrue(currencyList.containsKey("usd"));
        assertEquals(currencyList.get("gbp"), "British Pound");
        assertEquals(currencyList.get("usd"), "US Dollar");
    }
}
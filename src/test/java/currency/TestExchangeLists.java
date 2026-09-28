package currency;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static currency.TestCurrencyList.currencyListKeys;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class TestExchangeLists {
    static int exchangeListLength;

    @Test
    public void testExchangeLists() {
        for (int i = 0; i < currencyListKeys.size(); i++) {
            Response apiResponse = given().urlEncodingEnabled(false).get("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/" + currencyListKeys.get(i) + ".json");
            if (i == 0) {
                exchangeListLength = apiResponse.jsonPath().getMap(currencyListKeys.get(i)).size();
            }
            else {
                assertEquals(apiResponse.jsonPath().getMap(currencyListKeys.get(i)).size(), exchangeListLength);
            }
        }
    }
}
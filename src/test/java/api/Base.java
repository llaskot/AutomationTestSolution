package api;

import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.DataProvider;

import java.util.List;

import static api.Constants.ApiBodyTestConstants.*;


public class Base {
    ApiBase base = new ApiBase();

    @DataProvider(name = "Request")
    public Object[][] request() {
        List<String> listOfJson = base.dataToListOfJson(base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC,SHEET_NAME));
        String[][] arrayOfJson = new String[listOfJson.size()][1];
        for(int i = 0; i< listOfJson.size(); i++){
            arrayOfJson[i][0] = listOfJson.get(i);
        }
        return arrayOfJson;
    }

    @DataProvider(name = "Request2")
    public Object[][] request2() {
        List<JsonObject> listOfJson = base.dataToListOfJson2(base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC,SHEET_NAME));
        String[][] arrayOfJson = new String[listOfJson.size()][1];
        for(int i = 0; i< listOfJson.size(); i++){
            arrayOfJson[i][0] = listOfJson.get(i).toString();
        }
        return arrayOfJson;
    }

    static RequestSpecification requestPostEvents(String body) {
        return new RequestSpecBuilder()
                .setBaseUri(DOMAIN_URL)
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }


    static ResponseSpecification responseEvents() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }


}

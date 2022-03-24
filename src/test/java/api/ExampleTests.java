package api;

import com.google.gson.JsonElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;



import static api.Constants.ApiBodyTestConstants.PATH_TO_EXCEL_DOC;
import static api.Constants.ApiBodyTestConstants.SHEET_NAME;
import static io.restassured.RestAssured.given;

public class ExampleTests extends Base{
    ApiBase base = new ApiBase();

    @Test
    public void exampleTest1() {

        List<Map<String, String>> myList = base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC, SHEET_NAME);
        System.out.println(myList);
        System.out.println(myList.get(0).get("shop_name"));
        System.out.println(myList.get(2).get("сity"));
    }

    @Test
    public void exampleTest2() {

        List<String> listOfJson = base.dataToListOfJson(base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC, SHEET_NAME));
        System.out.println("Data for body requests  "+listOfJson);
        System.out.println("body # 2 : "+listOfJson.get(1));
        System.out.println("body # 3 : "+listOfJson.get(2));

    }

    @Test(dataProvider = "Request")
    public void exampleTest3 (String request){
        System.out.println(request);
    }



    @Test
    public void exampleTest1_2() {

        List<JsonElement> listOfJson = base.dataToListOfJson2(base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC, SHEET_NAME));
        System.out.println("Data for body requests  " + listOfJson);
        System.out.println("body # 2 : " + listOfJson.get(1));
        System.out.println("body # 3 : " + listOfJson.get(2));
    }

    @Test(dataProvider = "Request2")
    public void exampleTest1_3 (String request){
        given()
                .spec(requestPostEvents(request))
                .when()
                .log().body()
                .post();



    }
}

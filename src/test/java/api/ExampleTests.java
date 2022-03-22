package api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static api.Constants.ApiBodyConstants.PATH_TO_EXCEL_DOC;
import static api.Constants.ApiBodyConstants.SHEET_NAME;

public class ExampleTests extends Base{
    ApiBase base = new ApiBase();

    @Test
    public void exampleTest1() {

        List<Map> myList = base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC, SHEET_NAME);
        System.out.println(myList);
        System.out.println(myList.get(0).get("param1"));
        System.out.println(myList.get(2).get("param2"));
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

}

package api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static api.Constants.ApiBodyConstants.PATH_TO_EXCEL_DOC;
import static api.Constants.ApiBodyConstants.SHEET_NAME;

public class ExampleTests {
    ApiBase base = new ApiBase();

    @Test
    public void exampleTest1() {
        System.out.println(base.fromExcelToListOfMaps("src/main/resources/Data.xlsx", "example1"));
        List<Map> myList = base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC, SHEET_NAME);
        System.out.println(myList.get(0).get("column1"));
        System.out.println(myList.get(2).get("column2"));
    }

    @Test
    public void exampleTest2() {
        base.dataToListOfJson(base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC, SHEET_NAME));
    }

}

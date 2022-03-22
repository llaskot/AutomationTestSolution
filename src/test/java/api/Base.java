package api;

import org.testng.annotations.DataProvider;

import java.util.List;

import static api.Constants.ApiBodyConstants.*;


public class Base {
    ApiBase base = new ApiBase();
    @DataProvider(name = "Request")
    public Object[][] request() {
        List<String> listOfJson = base.dataToListOfJson(base.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC,SHEET_NAME));
        Object[][] arrayOfJson = new Object[listOfJson.size()][1];
        for(int i = 0; i< listOfJson.size(); i++){
            arrayOfJson[i][0] = listOfJson.get(i);
        }
        return arrayOfJson;
    }



}

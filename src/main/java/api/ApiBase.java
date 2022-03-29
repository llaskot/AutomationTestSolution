package api;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.*;

import static api.ApiConstants.ApiBodyConstants.*;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;


public class ApiBase {

    public List fromExcelToListOfMaps (String docPath, String sheet)  {
        Fillo fillo = new Fillo();
        Connection connection;
        List<Map<String, String>> listOfMaps = new ArrayList<>();
        try {
           /* getting all the data from excel file */
            connection = fillo.getConnection(docPath);
            Recordset recordset = connection.executeQuery("Select * From "+sheet+" ");

         /* getting column names*/
            ArrayList<String> keys = recordset.getFieldNames();
            int size = keys.size();

              /* creating array of maps*/
            while (recordset.next()) {
                Map<String, String> values = new LinkedMap<>();
                for (int i = 0; i  <size; i++) {

                    String key = keys.get(i);
                    String value = recordset.getField(key);
                    values.put(key, value);

                }
                listOfMaps.add(values);

            }
            recordset.close();
            connection.close();
        } catch (FilloException e) {
            e.printStackTrace();
        }
        return listOfMaps;

    }


    public List<String> dataToListOfJson(List<Map<String, String>> listOfMaps){
        Gson gson = new Gson();
        List<String> listOfJson = new ArrayList<>();
        for (int i=0; i<listOfMaps.size(); i++) {
            /*
            * getting each map collection
            * */
            Map<String,String> row = listOfMaps.get(i);
            /*
            * deleting empty values
            */
            row.entrySet().removeIf(y -> (y.getValue().equals("")));

            /*deleting empty values in a longer way*/

//            for (Map.Entry<String, String> entry: row.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                if(value.equals("")){
//                    row.remove(key);
//                }
//            }
            /*
            * Turn map without empty fields to json
            */
            String requestBody = gson.toJson(row);
            listOfJson.add(requestBody);
        }
        return listOfJson;
    }



    public List<JsonObject> dataToListOfJson2(List<Map<String, String>> listOfMaps){
        Gson gson = new Gson();
        List<JsonObject> listOfJson = new ArrayList<>();

        for (int i=0; i<listOfMaps.size(); i++) {
            /*
             * getting each map collection
             * */
            Map<String,String> row = listOfMaps.get(i);
            JsonObject jsElemMap = new JsonObject();

            /*
             * deleting keys that have empty values
             */
            row.entrySet().removeIf(y -> (y.getValue().equals("")));

            /*iteration map to change value classes and add them to json object */

            for (Map.Entry<String, String> entry: row.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.indexOf("**")>0) {
                    int x =  key.indexOf("**");
                    String keyWord = toLowerCase(key.substring(0, x));

                    /*work with Integer*/
                    if (keyWord.equals(INT_VAL)){
                        JsonElement a = gson.toJsonTree(Integer.parseInt(value));
                        jsElemMap.add(key.substring(x+2), a);
                    }
                    /*work with Float*/
                     else if (keyWord.equals(FLOAT_VAL)){
                        value = value.replace(",", ".");
                        JsonElement a = gson.toJsonTree(Float.parseFloat(value));
                        jsElemMap.add(key.substring(x+2), a);
                    }
                    /*work with Boolean*/
                     else if (keyWord.equals(BOOLEAN_VAL)){
                        JsonElement a = gson.toJsonTree(Boolean.parseBoolean(value));
                        jsElemMap.add(key.substring(x+2), a);
                    }
                     
                     

                     
                     /*work with array*/
                     else if (keyWord.equals(ARRAY_VAL)&& excelSheetNames(PATH_TO_EXCEL_DOC).contains(key.substring(x+2))){
                        List arr = arrayVal(PATH_TO_EXCEL_DOC, key.substring(x+2), Integer.parseInt(value));
                        JsonElement a = gson.toJsonTree(arr);
                        jsElemMap.add(key.substring(x+2), a);
                    }
                     
                     
                     
                     
                    /*work with JsonObjects */
                    /*If Excel file contains sheet which name equals keyWord */
                     else if (excelSheetNames(PATH_TO_EXCEL_DOC).contains(keyWord)){
                         /*get this sheet as list of json*/
                        ApiBase apiBase = new ApiBase();
                        List aa = apiBase.dataToListOfJson2(apiBase.fromExcelToListOfMaps(PATH_TO_EXCEL_DOC,keyWord));
                        /*and add one of json as value of current key*/
                        jsElemMap.add(key.substring(x+2), gson.toJsonTree(aa.get(Integer.parseInt(value))));
                     }
                }else{
                    /*If there is no keyWord*/
                    jsElemMap.add(key, gson.toJsonTree(value) );
                }

            }
            /* remove all keys with keyword */
            row.entrySet().removeIf(y -> (y.getKey().contains("**")));

            /*
             * Turn map without empty fields to json
             */

            listOfJson.add(jsElemMap);
        }

        return listOfJson;
    }




    public List arrayVal(String pathToExcel, String sheetName, int rowNumber){


        List<JsonObject> x = dataToListOfJson2(fromExcelToListOfMaps(pathToExcel, sheetName));
        JsonObject dataForArray = x.get(rowNumber);
        List<Object> arr= new LinkedList<>();
        for(String key : dataForArray.keySet()){
            arr.add(dataForArray.get(key));
        }
        return arr;
    }
    
    
    
    /*getting names of sheets to list*/
    public List<String> excelSheetNames(String filePath){
        Fillo fillo = new Fillo();
        Connection connection =null;
        List<String> names = new ArrayList<>();
        try {
            connection = fillo.getConnection(filePath);
            names = connection.getMetaData().getTableNames();
            connection.close();

        } catch (FilloException filloException) {
            connection.close();
            filloException.printStackTrace();
        }
        return names;
    }



}

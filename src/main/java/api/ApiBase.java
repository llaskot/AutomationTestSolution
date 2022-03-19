package api;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.google.gson.Gson;

import java.security.Key;
import java.util.*;

public class ApiBase {

    public List fromExcelToListOfMaps (String docPath, String sheet)  {
        Fillo fillo = new Fillo();
        Connection connection;
        List<Map<String, String>> listOfMaps = new ArrayList<>();
        try {
//            getting all the data from excel file
            connection = fillo.getConnection(docPath);
            Recordset recordset = connection.executeQuery("Select * From "+sheet+" ");
            System.out.println("Rows quantity = "+recordset.getCount());

//          getting column names
            ArrayList<String> keys = recordset.getFieldNames();
            int size = keys.size();

//              creating array of maps
            while (recordset.next()) {
                Map<String, String> values = new HashMap<>();
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
//        System.out.println(listOfMaps);


        return listOfMaps;

    }


    public List<String> dataToListOfJson(List<Map<String, String>> listOfMaps){
        Gson gson = new Gson();
        List<String> listOfJson = new ArrayList<>();
        for (int i=0; i<listOfMaps.size(); i++) {
//            System.out.println("Map from excel "+listOfMaps.get(i));
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
//            System.out.println(requestBody);
        }
        return listOfJson;
    }


}

# AutomationTestSolution

# Some complite solutions
 
# method api.ApiBase#fromExcelToListOfMaps(String : path to Excel file, String : sheet name)

turns any Excel sheet to List of Maps<String, String> using Fillo (example api.ExampleTests#exampleTest1)

# method api.ApiBase#dataToListOfJson(List of Maps<String, String>)

Turns list of Maps to list of Json deleting empty values (only String values) (example api.ExampleTests#exampleTest2 ) That can be used as a body in API requests POST, PUT including DATAPROVIDER (example api.ExampleTests#exampleTest3 api.Base#request)

-excel column name becomes a parameter name

-values in this column becomes values of this parameter (type: String)

# method method api.ApiBase#dataToListOfJson2(List of Maps<String, String>)

turns list of Maps to list of Json deleting empty values
Can be different types of values in this JSON: string, int, double, boolean, JSON object.

To point out which type to use for this parameter should add the keyword before a column name and separate it with **

KeyWords:
1. string - no keyWord
2. boolean - Bool
3. float - flo
4. int - Int



For example: if you need parameter 'age' type 'int'  you should name the column name: Int**age

![image](https://user-images.githubusercontent.com/83096193/159807597-1b528486-6505-493e-9aad-145b34102baa.png)

Result

[{"param7":3,"param8":true,"param3":"volue1.3","param6":0.98}, {"param7":3,"param3":"volue2.3","param6":5.0}, {"param7":25,"param8":false,"param3":"volue3.3","param6":999.0001}]

5. Json Object - keyWord must be equals to sheet name that be used for this Json object

volue type in this column has to be int. It points out on line nuber in the second sheet whitch will be used in Json Object. Numbering starts from 0.

For example:

![image](https://user-images.githubusercontent.com/83096193/159946295-233bb086-8e4d-4e98-b7ff-6e637c9b04c6.png)
![image](https://user-images.githubusercontent.com/83096193/159946547-79dfa878-1543-4a8e-89a3-cdb18909f64d.png)


result   (num ** num column is not used on the sheet 'data' and just added to show lines numbering )

[{"user":{"active":true,"name":"Masha","age":35}}, {"user":{"active":false,"name":"Kolja","age":19}}, {"user":{"active":true,"name":"Vasja","age":22}}]

6. Array - arr

   When keyword arr is added, a sheet with the parameter name has to be added also. The row from this sheet will be used to get the values of this array.
   The value type in the arr** smt column has to be Integer. It points off the line number in the second sheet.Numbering starts from 0.
   All columns in the second sheet must have the same keywords (if that is necessary to point out it). The names of columns don't matter, but they have to be different from each other.
   ![image](https://user-images.githubusercontent.com/83096193/160657683-0be872ab-9fbb-4029-a522-bcd95f513377.png)
   
   result:
   ![image](https://user-images.githubusercontent.com/83096193/160658314-a16f8e08-eba4-4122-b769-58bb3f2ebb10.png)


   

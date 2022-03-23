# AutomationTestSolution

# Some complite solutions
 
# method api.ApiBase#fromExcelToListOfMaps(String : path to Excel file, String : sheet name) turns any Excel sheet to List of Maps<String, String> using Fillo (example api.ExampleTests#exampleTest1)

# method api.ApiBase#dataToListOfJson(List of Maps<String, String>)
Turns list of Maps to list of Json deleting empty values (only String values) (example api.ExampleTests#exampleTest2 ) That can be used as a body in API requests POST, PUT including DATAPROVIDER (example api.ExampleTests#exampleTest3 api.Base#request)

-excel column name becomes a parameter name

-values in this column becomes values of this parameter (type: String)

# method method api.ApiBase#dataToListOfJson2(List of Maps<String, String>) turns list of Maps to list of Json deleting empty values
Can be different types of values in this JSON: string, int, double, boolean, JSON object
to point out which type to use for this parameter should add the keyword before a column name and divide them by  ** 

KeyWords:
1. string - no keyWord
2. boolean - Bool
3. double - Doub
4. int - Int
For example: if you need parameter 'age' type 'int'  you should name the column name: Int**age

![image](https://user-images.githubusercontent.com/83096193/159807597-1b528486-6505-493e-9aad-145b34102baa.png)

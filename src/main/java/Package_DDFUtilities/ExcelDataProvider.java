package Package_DDFUtilities;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider 
{

	@DataProvider(name = "getData")
	public static Object getData() throws Exception 
	{
		String filePath = Project_Constants.currentDirectory+"/src/main/java/Package_DDF_TestData/TestData1.xlsx";
		ExcelUtilities.setExcelFile(filePath, "RegistrationPage");
		
		int totalRowsCount = ExcelUtilities.getRowCount("RegistrationPage");
		int totalColCount = ExcelUtilities.getColCount("RegistrationPage");
		
		System.out.println("Total Rows : "+totalRowsCount);
		System.out.println("Total Columns : "+totalColCount);
		
		Object testData[][] = new Object[totalRowsCount-1][totalColCount];
		
		for(int i=1; i<totalRowsCount; i++)
		{
			for(int j=0; j<totalColCount; j++)
			{
				testData[i-1][j]=ExcelUtilities.getCellData(i, j);
				System.out.print(testData[i-1][j]+" | ");
			}
			System.out.println();
		}
		return testData;
	}
	
}

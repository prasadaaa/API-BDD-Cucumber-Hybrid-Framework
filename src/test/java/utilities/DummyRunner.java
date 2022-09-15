package utilities;

public class DummyRunner
{
	public static void main(String[] args) throws Exception
	{
		String x=PropertiesFileUtility.getValueInPropertiesFile("qauri");
		System.out.println(x);
		String[] y=TextFilesUtility.getValuesOfLine("src\\test\\resources\\sources\\testdata.txt",3);
		System.out.println(y[0]);
		System.out.println(y[1]);
		ExcelFilesUtility obj=new ExcelFilesUtility("src\\test\\resources\\sources\\testdata.xlsx");
		obj.openSheet("Sheet1");
		String z=obj.getCellValue(3,3);
		//obj.setCellValue(3, 3,"\"5\"");
		System.out.println(z);
		obj.saveAndCloseExcel();
		

	}

}

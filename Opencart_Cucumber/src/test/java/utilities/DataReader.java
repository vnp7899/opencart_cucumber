package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	public static HashMap<String,String> storevalues=new HashMap<String,String>();
	
	public static List<HashMap<String,String>> data(String filepath,String sheetname) throws IOException
	{
		List<HashMap<String, String>> mydata = new ArrayList<>();
		try {
		FileInputStream fs=new FileInputStream(filepath);
		XSSFWorkbook workbook=new XSSFWorkbook(fs);
		XSSFSheet sheet=workbook.getSheet(sheetname);
		XSSFRow HeaderRow=sheet.getRow(0);
		for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
		{
			XSSFRow currentRow=sheet.getRow(i);
			HashMap<String,String> currentHash=new HashMap<String,String>();
			for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
			{
				Cell currentCell=currentRow.getCell(j);
				switch(currentCell.getCellType())
				{
				  case STRING:currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
					break;
				}
			}
			mydata.add(currentHash);
		}
		fs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mydata;
	}

}

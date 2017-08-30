import java.io.File;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class TableLoader {
	
	PApplet p;	
	Table table;	
	String fileFolderName = "pos";	
	int fileCount = 0;	
	File folder;
	File[] listOfFiles;
	TestData[] testData;
	
	
	TableLoader(PApplet p){
		this.p = p;
		PApplet.println("Start table reader....");
		PApplet.println("Opening: "+fileFolderName);
		
		try{
			folder = new File(fileFolderName);
			listOfFiles = folder.listFiles();
			testData = new TestData[listOfFiles.length-1];
			for(File t: listOfFiles){
				if(t.getName().contains("DS_Store")){				
				}else{
				PApplet.println(t.getPath());
				openAndStoreInfo(t.getPath());
				}
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
			p.exit();
		}
	}
	
	private void openAndStoreInfo(String fileName){
		
		table = p.loadTable(fileName, "header");
		TableRow row = table.getRow(0);
		
		int[] temp = new int[] {row.getInt("X"),row.getInt("Y"),row.getInt("Z")};
		testData[fileCount] = new TestData(temp);
		
		for(int i =1; i<table.getRowCount(); i++){
			row = table.getRow(i);
			float[] tempF = new float[]{row.getFloat("Time"),row.getFloat("X"),row.getFloat("Y"),row.getFloat("Z"),row.getFloat("PWMValue"),};
			testData[fileCount].addPosData(tempF);
		}	
		fileCount++; 	
	}
	
	public TestData[] getTestData(){
		return testData;
	}
	
	
	

}

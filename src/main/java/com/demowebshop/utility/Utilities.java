package com.demowebshop.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	
	public static String captureScreenShot(WebDriver driver,String testName) throws IOException {
		String ScreenshotName=System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\"+ testName + "_" + getTimeStamp()+".png";
				
		TakesScreenshot ts= (TakesScreenshot) driver;
		File sourceFile=ts.getScreenshotAs(OutputType.FILE);
		File destinationFile= new File(ScreenshotName);
		sourceFile.renameTo(destinationFile);
		return ScreenshotName;
		
		/*
		 * FileUtils.copyFile(sourceFile, destinationFile); return ScreenshotName;
		 */
		
	}
	
	/*public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	} */
	
	public static String getTimeStamp() {
		Date d = new Date();
		return d.toString().replace(":","_").replace(" ", "_");
	}
	
	
	public static String generateDynamicEmail() {
		return "abhishek_"+getTimeStamp()+"@gmail.com";
	}
	
}

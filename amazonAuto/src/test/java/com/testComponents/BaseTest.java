package com.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;

public class BaseTest {

	public static WebDriver driver;
	
	public static Properties getPropertyObject() throws IOException {
		Properties prop = new Properties();
		String filePath = System.getProperty("user.dir")
				+"\\src\\main\\java\\com\\resources\\GlobalData.properties";
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		return prop;
	}
	
	public static WebDriver initializeDriver() throws IOException {
		
		Properties prop = getPropertyObject();
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser")
				:prop.getProperty("browser");
		
		if(browserName.contains("edge")) {
			driver = new EdgeDriver();
		} else if(browserName.contains("chrome")) {
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
//		driver.manage().window().setSize(new Dimension(1920, 1080));
		
		return driver;
	}
	
	public static void launchApp() throws IOException {
		driver = initializeDriver();
		goToUrl();	
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileHandler.copy(src, des);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	public static void goToUrl() throws IOException {
		Properties prop = getPropertyObject();
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
//		driver.quit();
	}
}

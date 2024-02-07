package vjacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import vjacademy.pageObjects.LandingPage;

public class BaseTests {
	public WebDriver driver;
	public LandingPage landingPageObj;
	public WebDriver inititalizeDriver() throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis=new FileInputStream("E:\\udemy\\Selenium_Java\\WS\\SeleniumFramework2\\src\\main\\java\\vjacademy\\resources\\Global.properties");
		prop.load(fis);
		String browsername=prop.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApp() throws IOException
	{
		driver=inititalizeDriver();
		landingPageObj=new LandingPage(driver);
		landingPageObj.gotoURL();
		return landingPageObj;
	}
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.quit();
	}
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
		
	}

}

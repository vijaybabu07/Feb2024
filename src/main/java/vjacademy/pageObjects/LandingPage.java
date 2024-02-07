package vjacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vjacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPwd;
	
	@FindBy(id="login")
	WebElement submit;
	
	//div[@aria-label='Incorrect email or password.']
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMsg;
	
	public void gotoURL()
	{
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
	}
	
	public void loginApp(String email, String pwd)
	{
		userEmail.sendKeys(email);
		userPwd.sendKeys(pwd);
		submit.click();
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
		
		
	}
	
}

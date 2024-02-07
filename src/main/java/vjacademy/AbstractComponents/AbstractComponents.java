package vjacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;

	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait ww=new WebDriverWait(driver, Duration.ofSeconds(5));
		ww.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(By loading) throws InterruptedException
	{
		Thread.sleep(5000);
		/*WebDriverWait ww=new WebDriverWait(driver, Duration.ofSeconds(5));
		ww.until(ExpectedConditions.invisibilityOfElementLocated(loading)); */
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait ww=new WebDriverWait(driver, Duration.ofSeconds(5));
		ww.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void goToCartPage()
	{
		cartHeader.click();
	}
	
	public void goToOrdersPage()
	{
		orderHeader.click();
	}

}

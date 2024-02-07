package vjacademy.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vjacademy.TestComponents.BaseTests;
import vjacademy.pageObjects.CartPage;
import vjacademy.pageObjects.ProductsCatalogue;

public class ErrorValidationTests extends BaseTests {

	
	@Test(groups= {"ErrorTests"})	
	public void LoginErrorValidation() throws InterruptedException, IOException {
		
		
		landingPageObj.loginApp("vjcrypto1010@gmail.com", "Hunt4@josdsdb");
		Assert.assertEquals("Incorrect emafil or password.", landingPageObj.getErrorMessage());
		
	}
	@Test
	public void ProductErrorValidation() throws InterruptedException
	{
		String pname="IPHONE";
		/*WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();*/
		//LandingPage landingPageObj=launchApp();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		/*LandingPage landingPageObj=new LandingPage(driver);*/
		//landingPageObj.gotoURL();
		landingPageObj.loginApp("vijay@gmail.com", "Hunt4@job");
		
		ProductsCatalogue pc=new ProductsCatalogue(driver);
		List<WebElement> products=pc.getProductList();
		List<WebElement> addToCart=pc.getaddToCart();
		pc.addToCart(pname);
		pc.goToCartPage();
		
		CartPage cp=new CartPage(driver);
		boolean match=cp.verifyProductDisplay(pname);
		Assert.assertTrue(match);
	}

}

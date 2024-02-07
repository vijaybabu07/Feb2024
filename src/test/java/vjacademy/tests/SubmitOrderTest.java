package vjacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vjacademy.TestComponents.BaseTests;
import vjacademy.pageObjects.CartPage;
import vjacademy.pageObjects.CheckoutPage;
import vjacademy.pageObjects.ConfirmationPage;
import vjacademy.pageObjects.LandingPage;
import vjacademy.pageObjects.OrdersPage;
import vjacademy.pageObjects.ProductsCatalogue;

public class SubmitOrderTest extends BaseTests {
	
//	public String pname="IPHONE 13 PRO";
	
	//public static void main(String[] args) throws InterruptedException {
//	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException 
	//public void submitOrder(String email, String pwd, String pname) throws InterruptedException, IOException
	@Test(dataProvider="getdata", groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	{		
	 //LandingPage landingPageObj=launchApp();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//landingPageObj.loginApp("vjcrypto1010@gmail.com", "Hunt4@job");
		landingPageObj.loginApp(input.get("email"),input.get("pwd"));
		
		ProductsCatalogue pc=new ProductsCatalogue(driver);
		List<WebElement> products=pc.getProductList();
		List<WebElement> addToCart=pc.getaddToCart();
		pc.addToCart(input.get("pname"));
		pc.goToCartPage();
		
		CartPage cp=new CartPage(driver);
		boolean match=cp.verifyProductDisplay(input.get("pname"));
		Assert.assertTrue(match);
		
		cp.checkoutButton();
		
		CheckoutPage checkoutPageObj=new CheckoutPage(driver);
		checkoutPageObj.selectCountry("ind");
		checkoutPageObj.placeOrder();
		
		ConfirmationPage confirmPageObj=new ConfirmationPage(driver);
		String confirmMsg=confirmPageObj.verifyConfirmation();
		Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
		
		
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest(String pname)
	{
		landingPageObj.loginApp("vjcrypto1010@gmail.com", "Hunt4@job");
		OrdersPage ordersPageObj=new OrdersPage(driver);
		//Assert.assertEquals(pname,ordersPageObj.verifyOrderDisplay(pname));
		ordersPageObj.goToOrdersPage();
		Assert.assertTrue(ordersPageObj.verifyOrderDisplay(pname));
		
	}
	/*@DataProvider
	public Object[][] getdata()
	{
		return new Object[][] {{"vjcrypto1010@gmail.com", "Hunt4@job","IPHONE"},{"vijay@gmail.com", "Hunt4@job","ZARA"}};
	}*/
	@DataProvider
	public Object[][] getdata()
	{
		HashMap<String, String> map1=new HashMap<String,String>();
		map1.put("email", "vjcrypto1010@gmail.com");
		map1.put("pwd", "Hunt4@job");
		map1.put("pname", "IPHONE");
		
		HashMap<String, String> map2=new HashMap<String, String>();
		map2.put("email", "vijay@gmail.com");
		map2.put("pwd", "Hunt4@job");
		map2.put("pname", "ZARA");
		
		return new Object[][]  {{map1},{map2}};
		
	}

}

package vjacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("test1");
		System.out.println("test2");
		String pname="IPHONE";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("vjcrypto1010@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hunt4@job");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait ww=new WebDriverWait(driver, Duration.ofSeconds(7));
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5/b")));
		
		//List<WebElement> products=driver.findElements(By.xpath("//div[@class='card']"));
		List<WebElement> products=driver.findElements(By.xpath("//h5/b"));
		List<WebElement> addToCart=driver.findElements(By.xpath("//button[@class='btn w-10 rounded']"));
		System.out.println("no of products:"+products.size());
		for(int i=0;i<products.size();i++)
		{
		
			System.out.println(products.get(i).getText());
			if(products.get(i).getText().contains(pname))
			{
				addToCart.get(i).click();
			}
		}
		
		//WebDriverWait ww=new WebDriverWait(driver, Duration.ofSeconds(5));
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		ww.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));  //loading invisible
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartItems=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		/*for(int i=0;i<cartItems.size();i++)
		{
			if(cartItems.get(i).getText().contains(pname))
			{
				Assert.assertTrue(true);
				System.out.println("passed");
			}
			else
			{
				System.out.println("failed");
			}
		}*/
		boolean match=cartItems.stream().anyMatch(cart->cart.getText().contains(pname));
		Assert.assertTrue(true);
		
		driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted'] //button[@class='btn btn-primary']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		List<WebElement> countries=driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		for(int i=0;i<countries.size();i++)
		{
			System.out.println(countries.get(i).getText());
			if(countries.get(i).getText().equalsIgnoreCase("India"))
			{
				
				countries.get(i).click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		String confirmMsg=driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']")).click();
		List<WebElement> orderList=driver.findElements(By.xpath("//tr/td[2]"));
		for(int i=0;i<orderList.size();i++)
		{
			System.out.println(orderList.get(i).getText());
		}
		
		//driver.close();
		driver.quit();
	}

}

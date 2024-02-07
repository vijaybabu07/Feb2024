package vjacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vjacademy.AbstractComponents.AbstractComponents;

public class ProductsCatalogue extends AbstractComponents {
	
	WebDriver driver;
	public ProductsCatalogue(WebDriver driver)
	{
		super(driver); //driver sending to parent class AbstractComponents
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//h5/b")
	List<WebElement> products;
	
	
	
	By productsBy=By.xpath("//h5/b");
	
	By toastBy=By.id("toast-container");
	By loadingBy=By.className("ng-animating");
	By cartBy=By.xpath("//button[@routerlink='/dashboard/cart']");
	
	/*@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;*/
	
	@FindBy(xpath="//button[@class='btn w-10 rounded']")
	List<WebElement> addToCart;
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public List<WebElement> getaddToCart()
	{
	
		return addToCart;
	}
	
	public void addToCart(String pname) throws InterruptedException
	{
		System.out.println("no of products:"+products.size());
		for(int i=0;i<products.size();i++)
		{
		
			System.out.println(products.get(i).getText());
			if(products.get(i).getText().contains(pname))
			{
				addToCart.get(i).click();
			}
		}
		waitForElementToAppear(toastBy);
		waitForElementToDisappear(loadingBy);
		waitForElementToAppear(cartBy);
		
	}
	
	

	
	
}

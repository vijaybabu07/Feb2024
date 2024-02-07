package vjacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vjacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartItems;
	
	@FindBy(xpath="//div[@class='subtotal cf ng-star-inserted'] //button[@class='btn btn-primary']")
	WebElement checkoutbutton;
	
	public boolean verifyProductDisplay(String pname)
	{
		boolean match=cartItems.stream().anyMatch(cart->cart.getText().contains(pname));
		return match;
	}
	
	public void checkoutButton()
	{
		checkoutbutton.click();
	}
	
	
		
}

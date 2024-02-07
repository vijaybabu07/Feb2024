package vjacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vjacademy.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	
	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		super(driver); //driver sending to parent class AbstractComponents
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderList;	
	
	public boolean verifyOrderDisplay(String pname)
	{
		for(int i=0;i<orderList.size();i++)
		{
			System.out.println(orderList.get(i).getText());
		}
		boolean match=orderList.stream().anyMatch(order->order.getText().contains(pname));
		return match;
	}
	

	
	
}

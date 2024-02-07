package vjacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vjacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver); // driver sending to parent class AbstractComponents
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountry;

	/*
	 * @FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']")
	 * WebElement countryList;
	 */

	By countryListBy = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");

	@FindBy(xpath = "//span[@class='ng-star-inserted']")
	List<WebElement> countries;

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrderButton;

	public void selectCountry(String country) {
		selectCountry.sendKeys(country);
		waitForElementToAppear(countryListBy);

		for (int i = 0; i < countries.size(); i++) {
			System.out.println(countries.get(i).getText());
			if (countries.get(i).getText().equalsIgnoreCase("India")) {

				countries.get(i).click();
				break;
			}
		}

	}
	
	public void placeOrder()
	{
		placeOrderButton.click();
	}

}

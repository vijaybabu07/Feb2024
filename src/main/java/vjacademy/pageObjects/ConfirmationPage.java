package vjacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vjacademy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver); // driver sending to parent class AbstractComponents
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//h1[@class='hero-primary']")
	WebElement confirmText;

	public String verifyConfirmation() {
		
		String confirmMsg=confirmText.getText();
		return confirmMsg;
	}
	
	

}

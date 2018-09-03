package com.testingUsingMaven.selenuim;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InitLoginPage {
	WebDriver driver;
//	By user = By.id("ap_email");
//	By continueEmail = By.className("a-button-inner");
//	By pwd1 = By.id("ap_password");
//	By submit1 = By.id("signInSubmit");
	
	@FindBy(id="ap_email") WebElement username;
	@FindBy(id="ap_password") WebElement pwd;
	@FindBy(className="a-button-inner") WebElement continueEmail;
	@FindBy(id="signInSubmit") WebElement submit;
	 

	public InitLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String usernme) {		
		username.clear();
		username.sendKeys(usernme);
//		driver.findElement(user).sendKeys(usernme);
	}

	public void setPwd(String password) {
		pwd.clear();		
		pwd.sendKeys(password);
//		driver.findElement(pwd1).sendKeys(password);		
	}

	public void clickContinue() {
		continueEmail.click();
//		driver.findElement(continueEmail).click();
	}

	public void clickSubmit() {
		submit.click();
//		driver.findElement(submit).click();
	}

}

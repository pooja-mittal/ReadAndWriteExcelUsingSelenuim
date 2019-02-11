package com.testingUsingMaven.selenuim;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TCForLoginPage {
	WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Pooja\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-infobars");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		// driver.get("https://www.shoppersstop.com/#login");
		driver.get(
				"https://www.amazon.in/ap/signin?openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&&openid.pape.max_auth_age=0");
	}

	@Test(priority = 1)
	public void emailIsBlank() {
		InitLoginPage loginDetail = new InitLoginPage(driver);
		loginDetail.setUserName("");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		loginDetail.clickContinue();
		Assert.assertEquals(driver.findElement(By.className("a-alert-content")).getText(), "");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// loginDetail.setPwd("admin");
		// loginDetail.clickSubmit();
		// //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// System.out.println("message is" +
		// driver.findElement(By.className("a-alert-content")).toString());
	}

	@Test(priority = 2)
	public void emailIsInvalid() {
		InitLoginPage loginDetail = new InitLoginPage(driver);
		loginDetail.setUserName("admin");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		loginDetail.clickContinue();
		Assert.assertEquals(driver.findElement(By.className("a-alert-heading")).getText(), "There was a problem");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// loginDetail.setPwd("admin");
		// loginDetail.clickSubmit();
		// //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Assert.assertEquals(driver.findElement(By.id("nav-your-amazon-text")).getText(),"Pooja's
		// Amazon.in");
	}

	@Test(priority = 3)
	public void validEmailPwdBlank() {
		InitLoginPage loginDetail = new InitLoginPage(driver);
		loginDetail.setUserName("enter email");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		loginDetail.clickContinue();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		loginDetail.setPwd("");
		Assert.assertEquals(driver.findElement(By.className("a-alert-content")).getText(), "");
		loginDetail.clickSubmit();
		// //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// System.out.println("message is" +
		// driver.findElement(By.className("a-alert-content")).toString());
	}

	@Test(priority = 4, dependsOnMethods = { "validEmailPwdBlank" })
	public void validEmailPwdInvalid() {
		InitLoginPage loginDetail = new InitLoginPage(driver);
		// loginDetail.setUserName("enter email");
		// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// loginDetail.clickContinue();
		// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		loginDetail.setPwd("admin");
		loginDetail.clickSubmit();
		Assert.assertEquals(driver.findElement(By.className("a-alert-heading")).getText(), "There was a problem");
		// //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Assert.assertEquals(driver.findElement(By.id("nav-your-amazon-text")).getText(),"Pooja's
		// Amazon.in");
	}

	@Test(priority = 5)
	public void bothAreValid() {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		InitLoginPage loginDetail = new InitLoginPage(driver);
		// loginDetail.setUserName("enter email");
		// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// loginDetail.clickContinue();
		// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		loginDetail.setPwd("pwd");
		loginDetail.clickSubmit();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.className("nav-logo-link")).getText(), "Amazon");
		// System.out.println("message " +
		// driver.findElement(By.className("nav-logo-link")).getText());
	}

	@AfterTest
	public void close() {
		driver.close();
		driver.quit();
	}
}

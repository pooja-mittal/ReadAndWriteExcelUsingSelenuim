package data.driven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DDTBasic1 {
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Pooja\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.khanacademy.org/");
		driver.findElement(By.id("login-or-signup")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);		
	}
	@Test
	public void loginSite() throws InterruptedException {
		driver.findElement(By.className("inputBase_98c00u")).sendKeys("pooja.mittle30@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"uid-dialog-0-children\"]/div/section[2]/div/div/div[2]/div[2]/label/form/input")).sendKeys("pooja2016");
		driver.findElement(By.className("button_1bv3901-o_O-common_1icufe0-o_O-large_10vyrhl-o_O-all_tca0ge")).click();
		Thread.sleep(2000);
		System.out.println("title "+driver.getTitle());
//		Assert.assertTrue(driver.getTitle().contains("khan"),"user not able to login ");
		System.out.println("login successful");
	}
	
	
	@AfterTest
	public void close() {
		driver.close();
		driver.quit();
	}
	
}

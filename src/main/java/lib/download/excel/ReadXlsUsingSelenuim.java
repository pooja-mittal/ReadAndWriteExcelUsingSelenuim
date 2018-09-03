package lib.download.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(lib.download.excel.TestNgListener.class)
public class ReadXlsUsingSelenuim {
	WebDriver driver;
	int count = 0;
	// @BeforeMethod
	// public void setup() {
	//
	// }

	@Test(dataProvider = "PassData")
	public void loginSite(String userName, String password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Pooja\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.khanacademy.org/");
		driver.findElement(By.id("login-or-signup")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.className("inputBase_98c00u")).sendKeys(userName);
		driver.findElement(
				By.xpath("//*[@id=\"uid-dialog-0-children\"]/div/section[2]/div/div/div[2]/div[2]/label/form/input"))
				.sendKeys(password);
		driver.findElement(By.className("button_1bv3901-o_O-common_1icufe0-o_O-large_10vyrhl-o_O-all_tca0ge")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.className("nicknameDisplay_1i9m8bl")).getText().contains("pooja"),
				"user not able to login ");

		System.out.println("login successful");
	}

	// @AfterMethod
	// public void tearDown() {
	// driver.quit();
	//
	// }
	// public void close() {
	// driver.close();
	// driver.quit();
	// }
	@DataProvider(name = "PassData")
	public String[][] getData() {
		ReadXls readData = new ReadXls("C:\\Users\\Pooja\\eclipse-workspace\\selenuim\\testXLS\\userCredential.xlsx");
		int rowCount = readData.totalRows(0);
		System.out.println("total rows " + rowCount);
		String[][] data = new String[rowCount][2];
		for (int i = 0; i < rowCount; i++) {
			data[i][0] = readData.getData(0, i, 0);
			data[i][1] = readData.getData(0, i, 1);
		}

		return data;
	}

	@AfterMethod
	public void getStatus(ITestResult result) {
		driver.quit();
		int status = result.getStatus();
		switch (status) {
		case ITestResult.SUCCESS:
			File file = new File("C:\\Users\\Pooja\\eclipse-workspace\\selenuim\\testXLS\\userCredential.xlsx");

			try {
				FileInputStream fis = new FileInputStream(file);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet1 = workbook.getSheetAt(0);
				sheet1.getRow(count).createCell(2).setCellValue("pass");
				FileOutputStream fos = new FileOutputStream(file);
				workbook.write(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
			break;
		case ITestResult.FAILURE:
			File file1 = new File("C:\\Users\\Pooja\\eclipse-workspace\\selenuim\\testXLS\\userCredential.xlsx");

			try {
				FileInputStream fis = new FileInputStream(file1);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet1 = workbook.getSheetAt(0);
				sheet1.getRow(count).createCell(2).setCellValue("fail");
				FileOutputStream fos = new FileOutputStream(file1);
				workbook.write(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
			break;
		}

	}

}

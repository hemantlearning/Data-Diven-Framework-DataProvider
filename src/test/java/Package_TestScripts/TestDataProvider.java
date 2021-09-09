package Package_TestScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Package_DDFUtilities.ExcelDataProvider;
import Package_DDFUtilities.ExcelUtilities;
import Package_DDFUtilities.Project_Constants;

public class TestDataProvider extends ExcelDataProvider{
	
		WebDriver driver;
		
		
		@BeforeMethod
		public void launchApp() throws Exception {
			System.setProperty("webdriver.chrome.driver", Project_Constants.currentDirectory+"\\src\\main\\java\\Package_DDF_Drivers\\chromedriver.exe");
			
			driver=new ChromeDriver();
			driver.navigate().to(Project_Constants.URL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			WebElement SingUpButton = driver.findElement(By.xpath("//a[contains(text(),'Sign up')][1]"));
			
			Actions a = new Actions(driver);
			a.moveToElement(SingUpButton);
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", SingUpButton);
			
			String dirPath = Project_Constants.Path_TestData+Project_Constants.File_TestData;
			ExcelUtilities.setExcelFile(dirPath, "RegistrationPage");
			
		}
		
		
		
		@Test(dataProvider = "getData")
		public void RegistrationDataCheck(String Username, String Email, String FirstName, String LastName,	String ProgLang) throws Exception
		{
			
			//driver.findElement(By.id("UserName")).clear();
			driver.findElement(By.id("UserName")).sendKeys(Username);
			//driver.findElement(By.id("UserEmail")).clear();
			driver.findElement(By.id("UserEmail")).sendKeys(Email);
			//driver.findElement(By.id("UserFirstName")).clear();
			driver.findElement(By.id("UserFirstName")).sendKeys(FirstName);
			//driver.findElement(By.id("UserLastName")).clear();
			driver.findElement(By.id("UserLastName")).sendKeys(LastName);
			//driver.findElement(By.id("UserProgrammingSkill")).clear();
			driver.findElement(By.id("UserProgrammingSkill")).sendKeys(ProgLang);
			Thread.sleep(10000);
	
		}
		
		@AfterMethod
		public void closeApp() {
			driver.close();
		}

}

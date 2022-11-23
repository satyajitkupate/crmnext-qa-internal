package executetests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sn.config.BaseDriver;
import com.sn.pageobjects.DashboardTQ;
import com.sn.pageobjects.LoginPageTQ;


public class LoginTQ_TC1 {

	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\skt8403\\Documents\\Selenium Automation\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.demoqa.com/books");
		
		// Creating object of Login page
		LoginPageTQ loginTq = new LoginPageTQ(driver);
		loginTq.clickLogin();
		
		//Creating object of Dashboard
		DashboardTQ dashboard = new DashboardTQ(driver);
		
		//Enter username & password
		loginTq.enterUsername("Sammy");
		loginTq.enterPassword("Sammy123");
		
		
		// Click on Login button
		loginTq.clickLogin();
		Thread.sleep(3000);
			
		//Capture the page heading and print on console
		System.out.println("The page heading is------" +dashboard.getHeading());
		dashboard.clickLogout();
	}

}

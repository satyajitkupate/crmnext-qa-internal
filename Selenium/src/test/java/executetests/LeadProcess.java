package executetests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.sn.utils.GenericHelper;
import com.sn.config.BaseDriver;

public class LeadProcess extends BaseDriver {
	
	GenericHelper helper;
	@FindBy(xpath="//img[@title='User']")
	WebElement profileIcon;
	@FindBy(xpath="//a[@title='Leads']")
	WebElement tabLeads;
	@FindBy(xpath="//a[@title='New']")
	WebElement eleNewBtn;
	@FindBy(xpath="//button[@aria-label='Lead Source, --None--']")
	WebElement eleleadSource;
	
	@FindBy(xpath="//button[@aria-label='Lead Status, New']")
	WebElement eleleadStatus;
	
	@FindBy(xpath="//button[@name='salutation']")
	WebElement elesalutation;
	@FindBy(xpath="//input[@name='firstName']")
	WebElement elefirstName;
	@FindBy(xpath="//input[@name='lastName']")
	WebElement elelastName;
	@FindBy(xpath="//input[@name='Company']")
	WebElement eleCompany;
	@FindBy(xpath="//input[@name='Email']")
	WebElement eleEmail;
	
	@FindBy(xpath="//button[@aria-label='Channel Type, --None--']")
	WebElement eleChannelType;
	
	@FindBy(xpath="//button[@aria-label='Customer Type, --None--']")
	WebElement eleCustomerType;
	@FindBy(xpath="//button[@aria-label='Lead Type, --None--']")
	WebElement eleLeadType;
	@FindBy(xpath="//button[@aria-label='Lead Source Team, --None--']")
	WebElement eleLeadSourceTeam;

	@FindBy(xpath="//button[@name='SaveEdit']")
	WebElement eleSave;
	
	@FindBy(xpath="//button[@name='Lead.Accept_Reject']")
	WebElement eleAcceptOrReject;
	
	@FindBy(xpath="//span[@class='slds-form-element__label']//span[contains(text(),'Accept')]")
	WebElement eleAcceptRadioBtn;
	
	@FindBy(xpath="//button[text()='Next']")
	WebElement eleNextBtn;
	@FindBy(xpath="//button[text()='Finish']")
	WebElement eleFinishBtn;
	
	@FindBy(xpath="//div[text()='Lead']")
	WebElement eleLeadlabel;

	@FindBy(css=".title.slds-text-heading--medium.slds-media_center.slds-has-flexi-truncate")
	WebElement headerleadpage;
	
	//@Test(priority=2)
	public void selectSFDropdownValue(WebElement dropdownObject, String Value) throws InterruptedException
	{
		jsScrollToView(dropdownObject);
		Thread.sleep(1000);
		dropdownObject.click();
		Thread.sleep(3000);
		jsScrollToView(driver.findElement(By.xpath("//span[@title='"+Value+"']")));
		driver.findElement(By.xpath("//span[@title='"+Value+"']")).click();
	}
	
	
	public void createLead(String leadSource,  String salutation,String firstName, String lastName, String company, String email, String channelType, String customerType, String leadType, String leadSourceTeam) throws InterruptedException
	{
		
		PageFactory.initElements(BaseDriver.driver, this);
		driver.manage().window().maximize();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		  waitForElementToBeClickable(driver, 10, tabLeads);
	     executor.executeScript("arguments[0].click();", tabLeads);
	   			    
		waitForElementVisibility(driver, 10, eleNewBtn);
		 
		 
		eleNewBtn.click();
		
		waitForElementVisibility(driver, 10, eleleadSource);
		selectSFDropdownValue(eleleadSource, leadSource);
		selectSFDropdownValue(elesalutation, salutation);
		elefirstName.sendKeys(firstName);
		elelastName.sendKeys(lastName);
		eleCompany.sendKeys(company);
		eleEmail.sendKeys(email);
		
		selectSFDropdownValue(eleChannelType, channelType);
		selectSFDropdownValue(eleCustomerType, customerType);
		selectSFDropdownValue(eleLeadType, leadType);
		selectSFDropdownValue(eleLeadSourceTeam, leadSourceTeam);

		eleSave.click();
		report.log(Status.PASS, "Lead Created Successfully");
		
	}
	
	@Test(priority=1)
	public void loginToSalesforce() throws InterruptedException
	{
		PageFactory.initElements(BaseDriver.driver, this);
		driver.get("https://sn-rsm--qa.my.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys("satyajit.kupate@sn-rsm.com.qa");
		Thread.sleep(4000);
		driver.findElement(By.id("password")).sendKeys("Satyajit@24");
		Thread.sleep(5000);
		driver.findElement(By.id("Login")).click();		
		waitForElementVisibility(driver, 30, tabLeads);
		report.log(Status.PASS, "Login Successful");
	}
	
	@Test(dataProvider="LeadData", priority=2)
	public void createNewLead(String leadSource,  String salutation,String firstName, String lastName, String company, String email, String channelType, String customerType, String leadType, String leadSourceTeam) throws InterruptedException
	{
				createLead(leadSource, salutation, firstName, lastName, company, email, channelType, customerType, leadType, leadSourceTeam);
				helper = new GenericHelper();
				
				helper.isDisplayed(eleAcceptOrReject);
				eleAcceptOrReject.click();
	}
	
	//@Test(priority=3, enabled=false)
	@Test(priority=3,enabled=true)
	public void acceptLead() throws InterruptedException
	{
		Thread.sleep(3000);
		eleAcceptRadioBtn.click();
		eleNextBtn.click();
		// test commit on 23 Nov 11:30 PM
	}
	
	
	

	 
}
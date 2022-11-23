package com.sn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardTQ
{
	WebDriver driver;
	
	//Constructor that will be automatically called as soon as the object of the class is created
	public DashboardTQ(WebDriver driver) {
		this.driver=driver;
	}
	
	//Locators for the page title and the logout button
	By heading = By.xpath("//div[@class=\"main-header\"]");
	By logoutBtn= By.id("submit");
	
	//Method to capture the page heading
	public String getHeading()
	{
		String head=driver.findElement(heading).getText();
		return head;
	}
	
	// Method to click on Logout Button
	public void clickLogout()
	{
		driver.findElement(logoutBtn).click();
	}

}

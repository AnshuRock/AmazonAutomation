package com.pageComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput.ScrollOrigin;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="twotabsearchtextbox")
	WebElement sendSearchItem;
	
	@FindBy(id="nav-search-submit-button")
	WebElement searchBtn;
	
	@FindBy(id="p_36/range-slider_slider-item_lower-bound-slider")
	WebElement rangeLeft;
	
	@FindBy(id="p_36/range-slider_slider-item_upper-bound-slider")
	WebElement rangeRight;
	
	@FindBy(css="input[type='submit']")
	WebElement goBtn;
	
	@FindBy(id="searchDropdownBox")
	WebElement allCategories;
	
	@FindBy(css="button[name='submit.addToCart']")
	List<WebElement> addToCart;
	
	@FindBy(id="a-popover-2")
	WebElement popOverWindow;
	
	@FindBy(css="i[class='a-icon a-icon-close']")
	WebElement popOverClose;
	
	@FindBy(css="div[role='spinbutton']")
	List<WebElement> num;
	
	@FindBy(css="span[data-a-selector='decrement-icon']")
	WebElement decrease;
	
	//*[@id="p_72/1318476031"]/span/div/a/span
	
	@FindBy(xpath="//*[@id=\"p_72/1318476031\"]/span/div/a/i")
	WebElement ratings;
	
	public void searchSomeItem(String text, String category) {
		waitForTheWebElementToAppear(sendSearchItem);
		
		Select categories = new Select(allCategories);
		categories.selectByVisibleText(category);
		
		sendSearchItem.sendKeys(text);
		searchBtn.click();
	}
	
	public void priceRangeSelection() throws InterruptedException {
		
		waitForTheWebElementToAppear(ratings);
		ratings.click();
		waitForTheWebElementToAppear(rangeLeft);
//		Actions a = new Actions(driver);
		
//		a.clickAndHold(rangeLeft).moveByOffset(58, 0).release().perform();
//		waitForTheWebElementToAppear(rangeRight);
//		a.clickAndHold(rangeRight).moveByOffset(1000, 0).release().perform();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        // Set the min and max price directly via JavaScript (this is an example, adjust the element locators accordingly)
//        js.executeScript("document.getElementById('p_36/range-slider_slider-item_lower-bound-slider').value='58';");
//        Thread.sleep(3000);
//        js.executeScript("document.getElementById('p_36/range-slider_slider-item_upper-bound-slider').value='81300';");
		
		for (int i = 1; i <= 2 ; i++) {
            rangeLeft.sendKeys(Keys.ARROW_RIGHT);
        }
//		Thread.sleep(3000);
		
		for (int i = 1; i <= 30; i++) {
            rangeRight.sendKeys(Keys.ARROW_LEFT);
        }
		
		goBtn.click();
		
		
		Thread.sleep(2000);
	}
	
	public void addToCart() throws InterruptedException {
		
	
		waitForTheWebElementToAppear(By.className("a-button-inner"));
		int totalProducts = addToCart.size();
		
		
		for (int i = 0; i < totalProducts; i++) {
			if (addToCart.get(i).isEnabled()) {
				
			addToCart.get(i).click();
			Thread.sleep(500);
			}
		}
		
		
	}
	
}

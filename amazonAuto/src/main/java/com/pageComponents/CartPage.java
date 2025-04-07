package com.pageComponents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

WebDriver driver;
	
	public CartPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='sc-badge-price sc-apex-cart-price']")
	List<WebElement> price;
	
	@FindBy(id="nav-cart-text-container")
	WebElement cart;
	
	@FindBy(css="div[role='spinbutton']")
	WebElement indicator;
	
	@FindBy(id="sc-subtotal-amount-buybox")
	WebElement subtotal;
	
	@FindBy(id="nav-cart-count")
	WebElement totalProducts;
	
	@FindBy(xpath="//div[@class='sc-item-content-group']/div/span/span/div/button/following-sibling::div/span[2]")
	List<WebElement> num;
	
	@FindBy(xpath="//li[@class='sc-item-product-title-cont']")
	List<WebElement> phoneText;
	
	public int getTotal() {
		waitForTheWebElementToAppear(cart);
		cart.click();
		waitForTheWebElementToAppear(indicator);
		int sum = 0;
		for (int i = 0; i < price.size(); i++) {
            String str = price.get(i).getText();
            String numberString = str.replaceAll("[^0-9.,]", "").replace(",", ""); 
            String integerParts[] = numberString.split("\\.");
            String integerPart = integerParts[0];
            int priceValue = Integer.parseInt(integerPart);
            int actualPrice = Integer.parseInt(num.get(i).getText()) * priceValue;
			sum += actualPrice;
		}
		return sum;
	}
	
	public int subTotal() {
		String amount = subtotal.getText();
        String numberString = amount.replaceAll("[^0-9.,]", "").replace(",", ""); 
        String integerParts[] = numberString.split("\\.");
        return Integer.parseInt(integerParts[0]);
	}
	
	public int cartCount() throws InterruptedException {
		Thread.sleep(2000);
		return Integer.parseInt(totalProducts.getText());
	}
	
	public int totalCount() {
		int count = 0;
		for (int i = 0; i < num.size(); i++) {
			count += Integer.parseInt(num.get(i).getText());
		}
		return count;
	}
	
	public void saveItems() {
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < phoneText.size(); i++) {
			map.put(phoneText.get(i).getText(), price.get(i).getText());
		}
		
		for (Map.Entry<String, String> phones: map.entrySet()) {
			  System.out.println(phones.getKey());
			}
		}
}

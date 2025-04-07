package com.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import com.pageComponents.CartPage;
import com.pageComponents.LandingPage;

import com.testComponents.BaseTest;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitonImpl extends BaseTest {

	public LandingPage landingPage;
	int totalProductCount = 0;
	public CartPage cp;
	
	@Given("I landed on amazon site")
	public void I_landed_on_amazon_site() throws IOException {
		try {
		launchApp();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@When("searched the samung phone in all category")
	public void searched_the_samung_phone_in_all_category() {
		try {

			landingPage = new LandingPage(driver);
			landingPage.searchSomeItem("Samsung Phones", "All Categories");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("selected some price range and clicked on go button")
	public void selected_some_price_range_and_cliked_on_go_button () throws InterruptedException {
		try {
			landingPage.priceRangeSelection();
//		ScreenshotUtil.takeScreenshot(driver, "learning and career page");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("added the available items to the cart")
	public void added_the_available_items_to_the_cart() throws InterruptedException {
		try {
			landingPage.addToCart();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("validated the total number of items in the cart")
	public void validated_the_total_number_of_items_in_the_cart_n_toalAmount() throws InterruptedException {
		try {
		cp = new CartPage(driver);
		Assert.assertEquals(cp.totalCount(), cp.cartCount());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("validated the total payable amount")
	public void validated_the_total_payable_Amount() throws InterruptedException {
		try {
		cp = new CartPage(driver);
		int totalBillAmount = cp.getTotal();
		Assert.assertEquals(totalBillAmount, cp.subTotal());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("extract the all phone texts")
	public void extract_the_all_phone_texts() {
		try {
			cp = new CartPage(driver);
		    cp.saveItems();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

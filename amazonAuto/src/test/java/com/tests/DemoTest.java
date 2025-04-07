package com.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageComponents.CartPage;
import com.pageComponents.LandingPage;
import com.testComponents.BaseTest;

public class DemoTest extends BaseTest{

	@Test
	public void searchItems() throws IOException, InterruptedException {
		
		try {
		launchApp();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.searchSomeItem("Samsung Phones", "All Categories");
		landingPage.priceRangeSelection();
		landingPage.addToCart();
		CartPage cp = new CartPage(driver);
		int totalBillAmount = cp.getTotal();
		System.out.println(totalBillAmount);
		Assert.assertEquals(totalBillAmount, cp.subTotal());
		Assert.assertEquals(cp.totalCount(), cp.cartCount());
		cp.saveItems();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

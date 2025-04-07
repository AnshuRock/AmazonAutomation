package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.testComponents.BaseTest;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class StepHooks extends BaseTest {
	
//	@After
//	public void hooks(Scenario scenario) {
//		if (scenario.isFailed() || !scenario.isFailed()) {
//            try {
//			TakesScreenshot ts = (TakesScreenshot) driver;
//
//			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
//			scenario.attach(src, "image/png", "screenshot");
//			}catch(WebDriverException e) { 
//				e.getMessage();
//		    } 
//			
//		}
//	}
	
//	public static WebDriver driver;
	
//	@Before
//	public static void launchApp() throws IOException {
//		driver = initializeDriver();
////		LandingPage landingPage= new LandingPage(driver);
////		landingPage.goToUrl();	
//	}

    @AfterStep
    public void takeScreenshotAfterEveryStep(Scenario scenario) {
        
            // Capture screenshot if the scenario fails or pass
//            takeScreenshot(driver, scenario.getName());
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", screenshot.toString());
            
        
    }
    
    @After
    public void tearDown() {
    	driver.quit();
    }
}

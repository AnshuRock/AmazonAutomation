package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",
                 glue={"com.stepDefinition", "hooks"},
                 monochrome=true,
                 plugin= {"html:target/cucmber.html",
                		 "rerun:target/rerun.txt",
                		 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
                 )
public class TestRunner extends AbstractTestNGCucumberTests{

}

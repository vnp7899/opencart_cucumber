package stepDefinitions;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class RegistrationSteps {
	
	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	AccountRegistrationPage accreg;

@Given("the user navigates to registration page")
public void the_user_navigates_to_registration_page() {
	hp=new HomePage(BaseClass.getDriver());
	hp.clickMyAccount();
	hp.clickRegister();
    
}

@When("user eneters all the necessary information intobelow fields")
public void user_eneters_all_the_necessary_information_intobelow_fields(DataTable dataTable) {
   
	Map<String,String> dataMap= dataTable.asMap(String.class,String.class);
   
	accreg=new AccountRegistrationPage(BaseClass.getDriver());
    accreg.setFirstName(dataMap.get("firstname"));
    accreg.setLastName(dataMap.get("lastname"));
    accreg.setEmail(BaseClass.randomeAlphaNumeric()+"@gmail.com");
    accreg.setTelephone(dataMap.get("telephone"));
    accreg.setPassword(dataMap.get("password"));
    accreg.setConfirmPassword(dataMap.get("password"));
    
}

@When("user selects the privacy policy")
public void user_selects_the_privacy_policy() {
	accreg.setPrivacyPolicy();
}

@When("user clicks on continue button")
public void user_clicks_on_continue_button() {
    accreg.clickContinue();
}

@Then("the user account should get created successfully")
public void the_user_account_should_get_created_successfully() {
    String message=accreg.getConfirmationMsg();
    Assert.assertEquals(message, "Your Account Has Been Created!");
}


}

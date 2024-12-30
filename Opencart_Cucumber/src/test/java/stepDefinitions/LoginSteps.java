package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;



import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {
	
	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage myacc;
	
	List<HashMap<String,String>> datamap; //For Data driven
	
	@Given("the user navigates to login page")
	public void the_user_navigates_to_login_page() {
		
	   BaseClass.getLogger().info("***Go to MyAccount >Ckick on login***");
	   hp=new HomePage(BaseClass.getDriver());
	   hp.clickMyAccount();
	   hp.clickLogin();
	}

	@When("user eneters email and password as {string},{string}")
	public void user_eneters_email_and_password_as(String email, String pwd) {
	    BaseClass.getLogger().info("Enter the valid email and password");
	    lp=new LoginPage(BaseClass.getDriver());
	    lp.setEmail(email);
	    lp.setPassword(pwd);
	}

	@When("clicks on login button")
	public void clicks_on_login_button() {
	    lp.clickLogin();
	    BaseClass.getLogger().info("Clicked on login button");
	}

	@Then("user should be redirected to MyAccount Page")
	public void user_should_be_redirected_to_my_account_page() {
	    myacc=new MyAccountPage(BaseClass.getDriver());
	    boolean accpage=myacc.isMyAccountPageExists();
	    Assert.assertEquals(accpage, true);
	}

	//*******   Data Driven test **************
    @Then("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows) throws IOException
    {
        datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");

        int index=Integer.parseInt(rows)-1;
        String email= datamap.get(index).get("username");
        String pwd= datamap.get(index).get("password");
        String exp_res= datamap.get(index).get("res");

        lp=new LoginPage(BaseClass.getDriver());
        lp.setEmail(email);
        lp.setPassword(pwd);

        lp.clickLogin();
        myacc=new MyAccountPage(BaseClass.getDriver());
        try
        {
            boolean targetpage=myacc.isMyAccountPageExists();
            System.out.println("target page: "+ targetpage);
            if(exp_res.equals("Valid"))
            {
                if(targetpage==true)
                {
                    MyAccountPage myaccpage=new MyAccountPage(BaseClass.getDriver());
                    myaccpage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp_res.equals("Invalid"))
            {
                if(targetpage==true)
                {
                	myacc.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }


        }
        catch(Exception e)
        {

            Assert.assertTrue(false);
        }
      }
 
}

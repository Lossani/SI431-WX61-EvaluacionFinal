package com.gamingworld.app.gamingworld.StepsDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertNotNull;

public class RegisterUserSteps {

    WebDriver driver = null;

    String linkFrontEnd = "https://gworld.xempre.com/";

    @Given("that the user is at the register section")
    public void that_the_user_is_at_the_register_section() throws InterruptedException {
        System.out.println("Inside Step - browser is open");
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is : "+projectPath);
        System.out.println("Project path is : "+projectPath+"/src/test/resources/Drivers/chromedriver.exe ");
        System.setProperty("webdriver.chrome.driver",projectPath+"/src/test/resources/Drivers/chromedriver.exe ");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.navigate().to(linkFrontEnd+"/sign-up");

    }

    @When("the user fill the form with the data requested")
    public void the_user_fill_the_form_with_the_data_requested() throws InterruptedException {
        driver.findElement(By.id("name")).sendKeys("Leonardo");
        Thread.sleep(1000);
        driver.findElement(By.id("lastName")).sendKeys("Molina");
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("leotest@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("LEONARDOGOD");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("1234");
        Thread.sleep(1000);
        driver.findElement(By.id("confirmPassword")).sendKeys("1234");
        Thread.sleep(1000);

    }
    @When("press the button Cancel")
    public void press_the_button_Cancel() {
        driver.findElement(By.id("cancelRegistration")).sendKeys(Keys.ENTER); }

    @When("press the button Register")
    public void press_the_button_Register() {
        driver.findElement(By.id("register")).sendKeys(Keys.ENTER); }

    @Then("a message will show that the registration was successful and the user will be redirected to the login")
    public void a_message_will_show_that_the_registration_was_successful_and_the_user_will_be_redirected_to_the_login() throws InterruptedException{
        Thread.sleep(3000);
        WebElement confirmMessage = driver.findElement(By.tagName("app-confirm-user-registration"));

        assertNotNull(confirmMessage);

        driver.navigate().to(linkFrontEnd+"/login");
        driver.close();
        driver.quit();
    }
    @Then("no data will be stored and the user will be redirected to the login")
    public void the_system_will_store_the_data_and_it_will_be_redirected_to_the_login_view() throws InterruptedException{
        Thread.sleep(3000);
        driver.navigate().to(linkFrontEnd+"/login");
        driver.close();
        driver.quit();
    }
}

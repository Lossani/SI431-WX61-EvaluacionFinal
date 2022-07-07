package com.gamingworld.app.gamingworld.StepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class NewsSteps {

    WebDriver driver = null;
    String linkFrontEnd = "https://gworld.xempre.com/";

    @Given("that the user is at the news section")
    public void that_the_user_is_at_the_tournament_section() throws InterruptedException {
        System.out.println("Inside Step - browser is open");
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is : " + projectPath);
        System.out.println("Project path is : " + projectPath + "/src/test/resources/Drivers/chromedriver.exe ");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/Drivers/chromedriver.exe ");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.navigate().to(linkFrontEnd);
        Thread.sleep(5000);
    }

    @When("the user applies a filter Videogames Steam Twitch")
    public void the_user_applies_a_filter_Videogames_Steam_Twitch() throws InterruptedException {
        driver.findElement(By.id("filterGamesNewsButton")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.id("filterSteamNewsButton")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.id("filterTwitchNewsButton")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    @Then("only the news referring to this filter will be displayed")
    public void only_the_news_referring_to_this_filter_will_be_displayed() throws InterruptedException {
        Thread.sleep(3000);
        assertTrue(driver.findElement(By.id("visibleNew01")).isDisplayed());
        driver.close();
        driver.quit();
    }
}

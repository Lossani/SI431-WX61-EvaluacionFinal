package com.gamingworld.app.gamingworld.StepsDefinitions;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class TournamentSteps {
    WebDriver driver = null;
    String linkFrontEnd = "https://gworld.xempre.com/";

    @Given("that the user is at the tournament section")
    public void that_the_user_is_at_the_tournament_section() throws InterruptedException {
        System.out.println("Inside Step - browser is open");
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is : " + projectPath);
        System.out.println("Project path is : " + projectPath + "/src/test/resources/Drivers/chromedriver.exe ");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/Drivers/chromedriver.exe ");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.navigate().to(linkFrontEnd + "/login");
        Thread.sleep(1000);
        driver.findElement(By.id("usernameLogin")).sendKeys("LEONARDOGOD");
        driver.findElement(By.id("passwordLogin")).sendKeys("1234");
        Thread.sleep(1000);
        driver.findElement(By.id("enterLogin")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.navigate().to(linkFrontEnd + "/tournaments");
        Thread.sleep(3000);

    }

    @When("the user press the slider Post a tournament")
    public void the_user_press_the_slider_Post_a_tournament() throws InterruptedException {
        driver.findElement(By.id("mat-expansion-panel-header-0")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    @When("fill the form with the data requested")
    public void fill_the_form_with_the_data_requested() throws InterruptedException {
        driver.findElement(By.id("tournamentTitle")).sendKeys("Torneo test");
        Thread.sleep(1000);
        driver.findElement(By.id("tournamentImageURL")).sendKeys("https://i.blogs.es/69d86e/200921-dota2/1366_2000.jpeg");
        Thread.sleep(1000);
        driver.findElement(By.id("tournamentDescription")).sendKeys("Torneo de prueba para los test de aceptacion comprobando");
        Thread.sleep(1000);
        driver.findElement(By.id("tournamentPrize")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.id("tournamentPrize")).sendKeys("100");
        Thread.sleep(1000);
        driver.findElement(By.id("tournamentDate")).sendKeys("05052022");
        driver.findElement(By.id("tournamentDate")).sendKeys(Keys.ARROW_RIGHT);
        driver.findElement(By.id("tournamentDate")).sendKeys("1715");
        Thread.sleep(1000);
        driver.findElement(By.id("mat-input-7")).sendKeys("Dota 2");
        Thread.sleep(5000);
        driver.findElement(By.id("mat-input-7")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        Thread.sleep(1000);
        driver.findElement(By.id("tournamentCapacity")).sendKeys("10");
        Thread.sleep(1000);
    }

    @When("press the button Save")
    public void press_the_button_save() throws InterruptedException {
        driver.findElement(By.id("saveTournament")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }
    @When("press the button cancel")
    public void press_the_button_cancel() throws InterruptedException {
        driver.findElement(By.id("cancelTournament")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.id("mat-expansion-panel-header-0")).sendKeys(Keys.ENTER);
    }

    @When("the user is loading the page")
    public void the_user_is_loading_the_page() throws InterruptedException {
        Thread.sleep(3000);
    }
    @Then("the application shows the most recent tournaments")
    public void the_application_shows_the_most_recent_tournaments() throws InterruptedException {
        WebElement tournaments = driver.findElement(By.id("tournamentsList"));
        assertNotNull(tournaments);
        assertNotEquals(tournaments.getAttribute("innerHTML"), "");
        driver.close();
        driver.quit();
    }

    @Then("the tournament will be created successfully")
    public void the_tournament_will_be_created_successfully() throws InterruptedException {
        List<WebElement> tournamentCards = driver.findElements(By.className("tournament-card"));

        assertNotNull(tournamentCards);
        assertNotEquals(tournamentCards.size(), 0);

        WebElement latestTournament = tournamentCards.get(0);
        assertFalse(latestTournament.getAttribute("innerHTML").isEmpty());

        assertEquals(latestTournament.findElement(By.tagName("mat-card-title")).getText(), "Torneo test");

        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }
    @Then("the data will not be stored and the tournament will not be created")
    public void the_data_will_not_be_stored_and_the_tournament_will_not_be_created() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }

    @When("the user press Solo or Teams tournament filter")
    public void the_user_press_Solo_or_Teams_tournament_filter() throws InterruptedException {
        driver.findElement(By.id("soloTournamentFilterButton")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.id("teamTournamentFilterButton")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    @Then("only the tournaments referring to this filter will be displayed")
    public void only_the_tournaments_referring_to_this_filter_will_be_displayed() throws InterruptedException {
        List<WebElement> tournamentCards = driver.findElements(By.className("tournament-card"));

        assertNotNull(tournamentCards);
        assertNotEquals(tournamentCards.size(), 0);

        String type = tournamentCards.get(0).getAttribute("data-tournament-type");

        for (WebElement tournament: tournamentCards ) {
            String tournamentType = tournament.getAttribute("data-tournament-type");
            assertEquals(tournamentType, type);
        }

        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }
}






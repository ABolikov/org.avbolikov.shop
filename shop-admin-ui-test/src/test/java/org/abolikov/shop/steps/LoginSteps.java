package org.abolikov.shop.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.abolikov.shop.DriverInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenFirefoxBrowser() {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginHtmlPage() {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }

    @When("^Open dropdown menu$")
    public void openDropDownMenu() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        webElement.click();
        Thread.sleep(2000);
    }

    @And("^Click login button$")
    public void clickLoginButton() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.className("dropdown-item"));
        webElement.click();
        Thread.sleep(2000);
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("username"));
        webElement.sendKeys(username);
        Thread.sleep(2000);
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys(password);
        Thread.sleep(2000);
    }

    @And("^I click on login button$")
    public void iClickOnLoginButton() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.tagName("button"));
        webElement.click();
        Thread.sleep(2000);
    }

    @Then("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText()).isEqualTo(name);
    }

    @Given("^any user logged in$")
    public void userLoggedIn() {
        webDriver.findElement(By.id("logged-in-username"));
    }

    @When("^click logout button$")
    public void clickLogoutButton() {
        WebElement webElement = webDriver.findElement(By.tagName("button"));
        webElement.click();
    }

    @Then("Check logout")
    public void checkLogout() {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText()).isEqualTo("");
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }


}

package org.abolikov.shop.steps;

import com.google.common.collect.ArrayListMultimap;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.abolikov.shop.DriverInitializer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Then("^Check logout$")
    public void checkLogout() {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText()).isEqualTo("");
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }


    @Then("^Check menu admin$")
    public void checkMenuAdmin() {
        ArrayList<String> check = new ArrayList<>();
        check.add("Пользователи");
        check.add("Роли пользователей");
        check.add("Продукты");
        check.add("Категории продуктов");
        check.add("Бренды");
        List<WebElement> webElements = webDriver.findElement(By.className("list-unstyled")).findElements(By.tagName("li"));
        webElements.forEach(webElement -> Assert.assertTrue("Не найден элемент " + webElement.getText(),
                check.contains(webElement.getText())));
    }

    @And("^check active menu$")
    public void checkActiveMenu() {
        List<WebElement> webElements = webDriver.findElement(By.className("list-unstyled")).findElements(By.tagName("li"));
        int size = webElements.size();
        for (int i = 0; i <= size - 1; i++) {
            if (i > 0)
                webElements = webDriver.findElement(By.className("list-unstyled")).findElements(By.tagName("li"));
            String name = webElements.get(i).getText();
            webElements.get(i).click();
            WebElement element = webDriver.findElement(By.className("list-unstyled")).findElement(By.className("active"));
            Assert.assertEquals("Пункт меню " + name + " не выделен как активный", name, element.getText());
        }
    }

    @Then("^I click menu category$")
    public void iClickMenuCategory() {
        List<WebElement> webElements = webDriver.findElement(By.className("list-unstyled")).findElements(By.tagName("li"));
        webElements.get(3).click();
        Assert.assertTrue("Не выполнен переход на страницу \"Категории продуктов\"",
                webDriver.getCurrentUrl().contains("admin/categories"));
    }

    @And("^I click button add new category$")
    public void iClickButtonAddNewCategory() throws InterruptedException {
        Thread.sleep(2000);
        WebElement element = webDriver.findElement(By.linkText("/admin/categories/add"));
        element.click();
        Assert.assertTrue("Не выполнен переход на страницу добавления категории \"Категории продуктов\"",
                webDriver.getCurrentUrl().contains("admin/categories/add"));
    }


}

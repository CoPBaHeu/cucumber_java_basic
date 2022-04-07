package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TaskSteps {
    private WebDriver driver;
    private static WebDriverWait wait;

    public TaskSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I (?:am on|open) people with job page$")
    public void iAmOnPeopleWithJobPage() throws Throwable {
        driver.get("https://kristinek.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    @And("^I click add person$")
    public void iClickAddPerson() throws Throwable {
        driver.findElement(By.xpath("//*[contains(text(), 'Add person')]")).click();
    }

    @When("^I enter new person:$")
    public void iEnterNewPerson(Map<String, String> valuesToEnter) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(valuesToEnter.get("name"));
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(valuesToEnter.get("job"));
    }

    @And("^I click add$")
    public void iClickAdd() throws Throwable {
        driver.findElement(By.id("modal_button")).click();
    }

    @Then("^I check that I am on people with job page$")
    public void iCheckThatIAmOnPeopleWithJobPage() throws Throwable {
        String startPage = "https://kristinek.github.io/site/tasks/list_of_people_with_jobs.html";
        assertEquals(startPage, driver.getCurrentUrl());
    }

    @And("^I see person \"([^\"]*)\" added$")
    public void iSeePersonAdded(String inputName) throws Throwable {

        WebElement mainList = driver.findElement(By.id("listOfPeople"));
        List<WebElement> persons = mainList.findElements(By.tagName("li"));
        assertTrue(persons.get(persons.size() - 1).getText().contains(inputName));
    }


    @Then("^I check that I am on edit page$")
    public void iCheckThatIAmOnEditPage() throws Throwable {
        String startPage = "https://kristinek.github.io/site/tasks/enter_a_new_person_with_a_job.html";
        assertTrue(driver.getCurrentUrl().contains(startPage));
    }

    @And("^I put \"([^\"]*)\" to name field$")
    public void iPutToNameField(String inputName) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(inputName);
    }

    @And("^I click clear button$")
    public void iClickClearButton() throws Throwable {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    @Then("^I check that fields are empty$")
    public void iCheckThatFieldsAreEmpty() throws Throwable {
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("job")).getText());
    }


    @And("^I reset original list$")
    public void iResetOriginalList() throws Throwable {
        driver.findElement(By.xpath("//*[contains(text(), 'Reset List')]")).click();
        WebElement mainList = driver.findElement(By.id("listOfPeople"));
        List<WebElement> persons = mainList.findElements(By.tagName("li"));
        assertEquals(3,persons.size());
    }

    @When("^I click delete \"([^\"]*)\" person$")
    public void iClickDeletePerson(String inputName) throws Throwable {
        WebElement mainList = driver.findElement(By.id("listOfPeople"));
        List<WebElement> persons = mainList.findElements(By.tagName("li"));
        for (WebElement e : persons) {
            if (e.getText().contains(inputName)) {
                e.findElements(By.tagName("span")).get(0).click();
                break;
            }
        }
    }


    @Then("^I check person \"([^\"]*)\" was deleted$")
    public void iCheckPersonWasDeleted(String inputName) throws Throwable {

        WebElement mainList = driver.findElement(By.id("listOfPeople"));
        List<WebElement> persons = mainList.findElements(By.tagName("li"));
        for (WebElement e : persons) {
            if (e.getText().contains(inputName)) {
                assertFalse(true);
            }
        }
        assertTrue(true);
    }


    @When("^I click edit \"([^\"]*)\" person$")
    public void iClickEditPerson(String inputName) throws Throwable {

        WebElement mainList = driver.findElement(By.id("listOfPeople"));
        List<WebElement> persons = mainList.findElements(By.tagName("li"));

        for (WebElement e : persons) {
            if (e.getText().contains(inputName)) {
                e.findElements(By.tagName("span")).get(1).click();
                break;
            }
        }
    }

    @And("^I click edit button$")
    public void iClickEditButton() {
        driver.findElement(By.xpath("//*[contains(text(), 'Edit')]")).click();
    }

    @And("^I see person \"([^\"]*)\" edited$")
    public void iSeePersonEdited(String inputName) throws Throwable {
        WebElement mainList = driver.findElement(By.id("listOfPeople"));
        List<WebElement> persons = mainList.findElements(By.tagName("li"));
        assertEquals(inputName, persons.get(0).findElements(By.tagName("span")).get(2).getText());
    }
}
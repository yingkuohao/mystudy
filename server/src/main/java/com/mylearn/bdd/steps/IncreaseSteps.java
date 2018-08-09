package com.mylearn.bdd.steps;

import org.jbehave.core.annotations.*;
import org.junit.Assert;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/5/22
 * Time: 下午3:40
 * CopyRight: taobao
 * Descrption:
 */

public class IncreaseSteps {
    private int counter;
    private int previousValue;

    @BeforeStory
     public void beforeStories() {
         System.out.println("-----beforeStories----[1]");
     }
    @BeforeScenario(uponType = ScenarioType.NORMAL)
    public void beforeNormalScenario() {
        System.out.println("-----beforeNormalScenario----[2]");
    }


    @Given("a counter")
    public void aCounter() {
        System.out.println("-----given----[3]");
    }

    @Given("the counter has any integral value")
    public void counterHasAnyIntegralValue() {
        counter = new Random().nextInt();
        previousValue = counter;
    }


    @Given("the counter has any float value")
    public void counterHasAnyFloatValue() {
        Float f = new Random().nextFloat();
        System.out.println("f=" + f);
    }

    @When("the user increases the counter")
    public void increasesTheCounter() {
        System.out.println("-----When----[4]");
//        Assert.assertThat(2, greaterThanOrEqualTo(1));
        counter++;
    }

    @Then("the value of the counter must be 1 greater than previous value")
    public void theValueOfTheCounterMustBe1Greater() {
        System.out.println("-----Then----[5]");
        assertThat(1, is(counter - previousValue));
        System.out.println("----deal success!----");
    }


    @AfterScenario(uponType = ScenarioType.NORMAL, uponOutcome = AfterScenario.Outcome.SUCCESS)
    public void afterSuccessfulScenario() {
        System.out.println("-----afterSuccessfulScenario----[6]");
    }


    @AfterStory
    public void afterStories() {
        System.out.println("-----afterStories----[7]");
    }

    @AfterScenario(uponType = ScenarioType.NORMAL, uponOutcome = AfterScenario.Outcome.FAILURE)
    public void afterFailedScenario() {
        System.out.println("-----afterFailedScenario----[6]");
    }


}

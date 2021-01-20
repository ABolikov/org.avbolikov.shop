package org.abolikov.shop.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-html-report"},
        features = {"classpath:features"},
        glue = {"org.abolikov.shop.steps"},
        snippets = SnippetType.CAMELCASE)
public class LaunchTest {

}

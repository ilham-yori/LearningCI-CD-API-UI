package com.ilhamyp.api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "com.ilhamyp.api.steps",
        plugin = {"pretty", "html:build/reports/cucumber/report.html"},
        monochrome = true
)
public class APITestRunner {
}
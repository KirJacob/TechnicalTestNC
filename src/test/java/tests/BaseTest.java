package tests;

import com.codeborne.selenide.Configuration;
import data.LoadProperties;
import helpers.Common;
import org.testng.annotations.BeforeSuite;

import java.util.Optional;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest extends Common {

    protected static String mainURL = LoadProperties.load("URL");

    private void printEnvInfo() {
        String f = "%-25s %s\n";
        System.out.printf(f, "timeout, ms:", "<" + Configuration.timeout + ">");
        System.out.printf(f, "mainURL:", "<" + mainURL + ">");
        System.out.printf(f, "webdriver:", "<" + getWebDriver().toString() + ">");
        System.out.println("");
    }

    @BeforeSuite
    public void browserConfig() {
        String actualBrowser = LoadProperties.load("BROWSER");

        switch (actualBrowser){
            case "firefox": {
                Configuration.browser = "firefox";
            } break;

            case "chrome": {
                String driverPath = "bin/chromedriver";
                System.setProperty("webdriver.chrome.driver", driverPath);
                Configuration.browser = "chrome";

            } break;
            default:
                break;
        }

        Configuration.timeout = 8000;
        printEnvInfo();
    }
}

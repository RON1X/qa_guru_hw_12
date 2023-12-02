package guru.qa;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открыть страницу github.com")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Найти репозиторий {repository}")
    public void searchRepository(String repository) {
        $(".search-input").click();
        $("#query-builder-test").sendKeys(repository);
        $("#query-builder-test").submit();
    }

    @Step("Кликнуть по ссылке репозитория {repository}")
    public void clickOnRepositoryLink(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Открыть вкладку Issues")
    public void clickIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверить наличие Issue с названием {issue}")
    public void shouldExistIssueWithName(String issue) {
        $(withText(issue)).should(exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}

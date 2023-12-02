package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class IssueSearchTests {
    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final String ISSUE = "Issue_created_to_test_allure_reports";
    @Test
    @Epic("Issue 1")
    @Feature("Issue 2")
    @Story("Issue 3")
    @Owner("ea.chubkov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "github.com/eroshenkoam/allure-example/issues", url = "https://github.com/eroshenkoam/allure-example/issues")
    @DisplayName("Чистый Selenide (с Listener)")
    void issueSearchSelenoidTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".search-input").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();

        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE)).should(Condition.exist);
    }

    @Test
    @Epic("Issue 1")
    @Feature("Issue 2")
    @Story("Issue 3")
    @Owner("ea.chubkov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "github.com/eroshenkoam/allure-example/issues", url = "https://github.com/eroshenkoam/allure-example/issues")
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    void issueSearchLambdaStepTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть страницу github.com", () ->
            open("https://github.com")
        );

        step("Найти репозиторий " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });

        step("Кликнуть по ссылке репозитория " + REPOSITORY, () ->
            $(linkText(REPOSITORY)).click()
        );

        step("Открыть вкладку Issues ", () ->
            $("#issues-tab").click()
        );
        step("Проверить наличие Issue с названием " + ISSUE, () ->
            $(withText(ISSUE)).should(Condition.exist)
        );
    }

    @Test
    @Epic("Issue 1")
    @Feature("Issue 2")
    @Story("Issue 3")
    @Owner("ea.chubkov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "github.com/eroshenkoam/allure-example/issues", url = "https://github.com/eroshenkoam/allure-example/issues")
    @DisplayName("Шаги с аннотацией @Step")
    void issueSearchWebStepTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps webSteps = new WebSteps();
        webSteps.openMainPage();
        webSteps.searchRepository(REPOSITORY);
        webSteps.clickOnRepositoryLink(REPOSITORY);
        webSteps.clickIssuesTab();
        webSteps.shouldExistIssueWithName(ISSUE);
    }
}

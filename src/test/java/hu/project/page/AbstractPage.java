package hu.project.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract ős osztály a page objecteknek.
 *
 * @author Széles Adorján
 */
public abstract class AbstractPage extends LoadableComponent {
    protected WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
        this.load();
        this.isLoaded();
    }

    @Override
    protected void load() {
        this.loadPage();
    }

    @Override
    protected void isLoaded() throws Error {
        this.isLoadedPage();
    }

    protected void waitForVisibility(WebElement element, int timeoutInSeconds) throws Error {
        new WebDriverWait(this.webDriver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    protected abstract void loadPage();

    protected abstract void isLoadedPage();

}

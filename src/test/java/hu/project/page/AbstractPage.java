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
    protected static final int DEFAULT_ELEMENT_TIMEOUT = 30;
    protected WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
        this.load();
        this.isLoaded();
    }

    public String getHeaderTitle() {
        return this.webDriver.getTitle();
    }

    public String getCurrentUrl() {
        return this.webDriver.getCurrentUrl();
    }

    @Override
    protected void load() {
        this.loadPage();
    }

    @Override
    protected void isLoaded() throws Error {
        this.isLoadedPage();
    }

    protected void waitForVisibility(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(this.webDriver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    protected abstract void loadPage();

    protected abstract void isLoadedPage();

}

package hu.project.page;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * A keresés eredményét megjelenítő Google oldal.
 *
 * @author Széles Adorján
 */
public class GoogleResultPage extends AbstractPage {
    @FindBy(id = "top_nav")
    private WebElement navigationBar;

    @FindBy(id = "lst-ib")
    private WebElement searchField;

    public GoogleResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void loadPage() {
        // At oldalt már az előző page object betöltötte...
    }

    @Override
    protected void isLoadedPage() throws Error {
        this.waitForVisibility(this.navigationBar, 60);
        String url = this.webDriver.getCurrentUrl();
        Assert.assertTrue("Not on the Google's result page!", url.contains("#q="));
    }

    public String getHeaderTitle() {
        return this.webDriver.getTitle();
    }

    public String getSearchFieldValue() {
        return this.searchField.getAttribute("value");
    }

}

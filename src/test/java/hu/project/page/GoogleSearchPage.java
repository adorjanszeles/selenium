package hu.project.page;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * A Google kereső oldala, ezt teszteli az alkalmazás.
 *
 * @author Széles Adorján
 */
public class GoogleSearchPage extends AbstractPage {
    public static final String pageUrl = "http://www.google.com";

    // Lehetne név alapján is, akkor az annotációban (name = "q") szerepelne
    @FindBy(id = "lst-ib")
    private WebElement searchField;

    public GoogleSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void loadPage() {
        this.webDriver.get(GoogleSearchPage.pageUrl);
    }

    @Override
    protected void isLoadedPage() throws Error {
        this.waitForVisibility(this.searchField, GoogleSearchPage.DEFAULT_ELEMENT_TIMEOUT);
        String url = this.webDriver.getCurrentUrl();
        Assert.assertTrue("Not on the Google's search page!", url.contains("google"));
    }

    public GoogleSearchPage enterSearchKey(String searchKey) {
        this.waitForVisibility(this.searchField, GoogleSearchPage.DEFAULT_ELEMENT_TIMEOUT);
        this.searchField.click();
        this.searchField.sendKeys(searchKey);
        return this;
    }

    public GoogleResultPage submitSearch() {
        this.searchField.submit();
        return new GoogleResultPage(this.webDriver);
    }

}

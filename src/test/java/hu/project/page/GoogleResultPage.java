package hu.project.page;

import hu.project.exception.NoResultFound;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    /**
     * Ezt a WebElement-et csak a várakozás miatt hoztam létre,
     * hiszen nem biztos, hogy a keresésnek lesz eredménye.
     */
    @FindBy(id = "res")
    private WebElement resultWrapperElement;

    @FindBy(className = "r")
    private List<WebElement> results;

    public GoogleResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void loadPage() {
        // Az oldalt már az előző page object betöltötte...
    }

    @Override
    protected void isLoadedPage() throws Error {
        this.waitForVisibility(this.navigationBar, GoogleResultPage.DEFAULT_ELEMENT_TIMEOUT);
        this.waitForVisibility(this.resultWrapperElement, GoogleResultPage.DEFAULT_ELEMENT_TIMEOUT);
        String url = this.webDriver.getCurrentUrl();
        Assert.assertTrue("Not on the Google's result page!", url.contains("#q="));
    }

    public GoogleResultPage clickOnSearchResult(int numberOfFoundElement) throws Exception {
        if(numberOfFoundElement > 9) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if(this.results == null || this.results.isEmpty()) {
            throw new NoResultFound();
        }
        this.results.get(numberOfFoundElement).click();
        return this;
    }

    public String getSearchFieldValue() {
        return this.searchField.getAttribute("value");
    }

}

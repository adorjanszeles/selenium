package hu.project.test;

import hu.project.page.GoogleResultPage;
import hu.project.page.GoogleSearchPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Google keresés teszt. Beírunk egy karakterláncot a Google kereső mezőjébe
 * és ellenőrizzük, hogy az eredmény a megfelelő-e.
 *
 * @author Széles Adorján
 */
@RunWith(JUnit4.class)
public class SearchForTest {
    private WebDriver webDriver;
    private GoogleSearchPage googleSearchPage;
    private GoogleResultPage googleResultPage;

    @Before
    public void setUp() {
        this.webDriver = new ChromeDriver();
        this.googleSearchPage = new GoogleSearchPage(this.webDriver);
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }

    @Test
    public void testSearchForTree() throws Exception {
        String searchKey = "tree";
        Assert.assertEquals("The page title should equal Google at the start of the test.", "Google",
                this.googleSearchPage.getHeaderTitle());

        // Ennek a verziónak is vannak előnyei, ez már inkább ízlés dolga...
        //this.googleSearchPage.enterSearchKey(searchKey);
        //this.googleResultPage = this.googleSearchPage.submitSearch();

        // Ez így sokak szerint olvashatóbb...
        this.googleResultPage = this.googleSearchPage.enterSearchKey(searchKey).submitSearch();
        Assert.assertTrue("The page title should start with tree!",
                this.googleResultPage.getHeaderTitle().startsWith(searchKey));
    }

    @Test
    public void testSearchForWombat() throws Exception {
        String searchKey = "Wombat";
        Assert.assertEquals("The page title should equal Google at the start of the test.", "Google",
                this.googleSearchPage.getHeaderTitle());
        this.googleResultPage = this.googleSearchPage.enterSearchKey(searchKey).submitSearch();
        Assert.assertTrue("The search field must contain the " + searchKey + " keyword!",
                this.googleResultPage.getSearchFieldValue().contains(searchKey));
        Assert.assertTrue("The page title should start with tree!",
                this.googleResultPage.getHeaderTitle().startsWith(searchKey));
    }

}

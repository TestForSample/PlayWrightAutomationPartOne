package org.example.tests.page;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;


public class HomePage {
    private final Locator devPath;
    private final Locator commonPath;
    private final Page page;
    private final Locator searchInput;

    public HomePage(Page page) {
     this.page=page;
     this.devPath=page.locator("//button[text()='Developers']");
     this.commonPath=page.locator("//*[@id=\"developers-dd-menu\"]/div/div");
     this.searchInput = page.locator("#main-content");
    }
public void navigateToThePage(String url){
        page.navigate(url);
}

    public void hoverTheElement(){
        devPath.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        devPath.hover();
    }

    public void selectTheDev(String name){
       // commonPath.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        for(int i=1;i<=commonPath.count();i++){
            Locator locator=page.locator("//*[@id=\"developers-dd-menu\"]/div/div["+i+"]/a/span");
            String dev=locator.innerText();
            if(dev.equals(name)){
                locator.click();
                break;
            }
        }

    }
    public void searchInput(String input){
        searchInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        searchInput.fill(input);
    }

}

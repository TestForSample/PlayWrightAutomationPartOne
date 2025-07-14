package org.example.tests.page;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;



public class HomePage {
    private final Locator devPath;
    private final Locator commonPath;
    private final Page page;

    public HomePage(Page page) {
     this.page=page;
     this.devPath=page.locator("//button[text()='Developers']");
     this.commonPath=page.locator("//*[@id=\"developers-dd-menu\"]/div/div");
    }
public void navigateToThePage(String url){
        page.navigate(url);
}
    public void hoverTheElement(){
        devPath.hover();
    }
    public void selectTheDev(String name){
        for(int i=1;i<=commonPath.count();i++){
            Locator locator=page.locator("//*[@id=\"developers-dd-menu\"]/div/div["+i+"]/a/span");
            String dev=locator.innerText();
            if(dev.equals(name)){
                locator.click();
                break;
            }
        }

    }

}

package org.example.tests.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {
    private final Page page;
    private final Locator username;
    private final Locator password;
    private final Locator loginButton;

    public LoginPage(Page page){
        this.page=page;
        this.username=page.locator("#username");
        this.password=page.locator("#password");
        this.loginButton=page.locator("#login-button");
    }

    public void setUsername(String name){
        username.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        username.fill(name);
    }
    public void setPassword(String pass){
        password.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        password.fill(pass);
    }
    public void clickOnLoginButton(){
        loginButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        loginButton.click();
    }
}

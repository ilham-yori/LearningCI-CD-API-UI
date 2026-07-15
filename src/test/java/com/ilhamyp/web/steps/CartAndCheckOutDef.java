package com.ilhamyp.web.steps;

import com.ilhamyp.web.common.WebHook;
import com.ilhamyp.web.pages.CartPage;
import com.ilhamyp.web.pages.LoginPage;
import com.ilhamyp.web.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CartAndCheckOutDef {
    private ProductPage productPage;
    private CartPage cartPage;
    private LoginPage loginPage;

    public CartAndCheckOutDef() {
        this.productPage = new ProductPage(WebHook.getDriver());
        this.cartPage = new CartPage(WebHook.getDriver());
        this.loginPage = new LoginPage(WebHook.getDriver());
    }

    @Given("User sucessfully login to the account")
    public void berhasilLogin() {
        WebHook.getDriver().get("https://www.demoblaze.com/");

        if (WebHook.currentUsername == null) {
            WebHook.currentUsername = "nikenriri";
        }

        loginPage.clickLoginMenu();
        loginPage.fillUsername(WebHook.currentUsername);
        loginPage.fillPassword("150500Niken");
        loginPage.clickLoginButton();
        loginPage.getWelcomeDashboardText(); // Menunggu dashboard login termuat sempurna
    }
    @When("User choose the category of the product {string}")
    public void pilihKategori(String kat) {
        productPage.selectLaptopCategory();
    }
    @And("User click on the item {string}")
    public void pilihProdukItem(String prod) {
        productPage.selectMacbookProduct();
    }
    @And("User click on the button {string}")
    public void klikTombolBerdasarkanNama(String btn) {
        if (btn.equalsIgnoreCase("Add to cart")) {
            productPage.clickAddToCart();
        } else if (btn.equalsIgnoreCase("Place Order")) {
            cartPage.clickPlaceOrder();
        }
    }

    @Then("System shows the notification {string}")
    public void validasiAlertKeranjang(String expectedAlert) {
        Assert.assertEquals(expectedAlert, productPage.getAlertMessageAndAccept());
    }
    @When("User move to page {string}")
    public void pindahKeHalamanCart(String menu) {
        productPage.clickCartMenuLink();
    }

    @And("User complete the order information")
    public void isiDataFormAlamat() {
        cartPage.fillCheckoutDetails("Andi", "Indonesia", "Semarang", "54321234", "10", "2029");
    }
    @And("User confirm the order {string}")
    public void selesaikanTransaksi(String btn) {
        cartPage.clickPurchase();
    }
    @Then("System shows the order summary and message {string}")
    public void validasiSuksesCheckoutAkhir(String expectedReceiptText) {
        Assert.assertEquals(expectedReceiptText, cartPage.getConfirmationText());
    }
}

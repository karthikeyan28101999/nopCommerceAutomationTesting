package com.nopecommmerce.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class CheckoutPage extends Base {
	public CheckoutPage() {
		PageFactory.initElements(getDriver(), this);
	}

	@CacheLookup
	@FindBy(css = ".page-title h1")
	private WebElement pageTitle;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_FirstName")
	private WebElement firstName;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_LastName")
	private WebElement lastName;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_Email")
	private WebElement email;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_CountryId")
	private WebElement country;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_StateProvinceId")
	private WebElement state;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_City")
	private WebElement city;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_Address1")
	private WebElement addressFirst;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_Address2")
	private WebElement addressSeccond;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_ZipPostalCode")
	private WebElement postalCode;

	@CacheLookup
	@FindBy(id = "BillingNewAddress_PhoneNumber")
	private WebElement phoneNumber;

	@CacheLookup
	@FindBy(xpath = "(//button[@name='save'])[1]")
	private WebElement addressContinue;

	@CacheLookup
	@FindBy(xpath = "//div[@id='shipping-methods-form']/ul//input")
	private List<WebElement> shippingMethods;

	@CacheLookup
	@FindBy(className = "shipping-method-next-step-button")
	private WebElement shippingMethodContinueButton;

	@CacheLookup
	@FindBy(xpath = "//div[@id='checkout-payment-method-load']//input")
	private List<WebElement> paymmentMethodes;

	@CacheLookup
	@FindBy(xpath = "//button[contains(@class,'payment-method-next-step-button')]")
	private WebElement paymentContinue;

	@CacheLookup
	@FindBy(xpath = "//div[@class='billing-info-wrap']//ul/li[not(@class='payment-method')]")
	private List<WebElement> confirmBillingAddress;

	@CacheLookup
	@FindBy(xpath = "//button[contains(@class,'confirm-order-next-step-button')]")
	private WebElement orderConfirm;

	@CacheLookup
	@FindBy(xpath = "//div[@class='title']/strong[contains(text(),'successfully')]")
	private WebElement orderConfrimSucessesMessage;

	@CacheLookup
	@FindBy(className = "payment-info-next-step-button")
	private WebElement paymentInfformationContinue;

	@CacheLookup
	@FindBy(className = "confirm-order-next-step-button")
	private WebElement confirmOrder;

	@CacheLookup
	@FindBy(xpath = "(//div[@class='section order-completed']//strong)[1]")
	private WebElement orderConfirmMessage;

	public String getTitle() {
		return pageTitle.getText();
	}

	public void enterUserAddess(String fName, String lName, String mail, String contry, String stat, String cityy,
			String address1, String address2, String postalcode, String phonenumber) {
		Action.sendText(firstName, fName);
		Action.sendText(lastName, lName);
		Action.sendText(email, mail);
		Action.selectByVisibleText(country, contry);
		Action.selectByVisibleText(state, stat);
		Action.sendText(city, cityy);
		Action.sendText(addressFirst, address1);
		Action.sendText(addressSeccond, address2);
		Action.sendText(postalCode, postalcode);
		Action.sendText(phoneNumber, phonenumber);
		Action.click(addressContinue);

	}

	public void enterUserAddess(String contry, String stat, String cityy, String address1, String address2,
			String postalcode, String phonenumber) {

		Action.selectByVisibleText(country, contry);
		Action.click(state);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Action.selectByVisibleText(state, stat);
		Action.sendText(city, cityy);
		Action.sendText(addressFirst, address1);
		Action.sendText(addressSeccond, address2);
		Action.sendText(postalCode, postalcode);
		Action.sendText(phoneNumber, phonenumber);
		Action.click(addressContinue);

	}

	public void enterUserAddess(String contry, String cityy, String address1, String address2, String postalcode,
			String phonenumber) {

		Action.selectByVisibleText(country, contry);
		Action.sendText(city, cityy);
		Action.sendText(addressFirst, address1);
		Action.sendText(addressSeccond, address2);
		Action.sendText(postalCode, postalcode);
		Action.sendText(phoneNumber, phonenumber);
		Action.click(addressContinue);

	}

	public void selectPaymentMehtode(String paymentMethode) {
		for (WebElement paymentMethod : paymmentMethodes) {
			if (paymentMethod.getAttribute("value").contains(paymentMethode)) {
				paymentMethod.click();
				break;
			}
		}
		Action.click(paymentContinue);
	}

	public void selectShippingMethod(String shippingMethode) {
		for (WebElement shippigMethod : shippingMethods) {
			if (shippigMethod.getAttribute("value").contains(shippingMethode)) {
				shippigMethod.click();
				break;
			}
		}
		Action.click(shippingMethodContinueButton);
	}

	public void clickPaymentInformationClickButton() {
		WebElement fluentWait = Action.fluentWait(getDriver(), paymentInfformationContinue, 5, 1);
		Action.click(fluentWait);
	}

	public void confirmOrder() {
		Action.click(Action.fluentWait(getDriver(), confirmOrder, 5, 1));
	}

	public String orderSucessesMessage() {
		return orderConfirmMessage.getText();
	}

}

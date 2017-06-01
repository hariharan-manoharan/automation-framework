package main.java.businessComponents.MOBILE.AIRTEL;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import main.java.testDataAccess.DataTable;
import main.java.utils.Utility;
import main.java.utils.SqlQueries;

public class FunctionalComponents extends Utility {

	@SuppressWarnings("rawtypes")
	public FunctionalComponents(ExtentTest test, AndroidDriver driver, DataTable dataTable) {
		super(test, driver, dataTable);
	}

	public void createNewConnection() {

		LoginActivity loginActivity = new LoginActivity(test, driver, dataTable);
		loginActivity.addConnection();

	}

	public void login() {

		LoginActivity loginActivity = new LoginActivity(test, driver, dataTable);
		loginActivity.login();

	}

	public void selectUserProfile() {
		ProfilesActivity profilesActivity = new ProfilesActivity(test, driver, dataTable);
		profilesActivity.selectProfile();
	}
	
	
	public void openContainer() throws TimeoutException, NoSuchElementException {
		Container Container = new Container(test, driver, dataTable);
		Container.openContainer();
	}
	
	public void closeContainer() throws TimeoutException, NoSuchElementException {
		Container Container = new Container(test, driver, dataTable);
		Container.closeContainer();
	}
	
	public void addToContainer() throws TimeoutException, NoSuchElementException {
		Container Container = new Container(test, driver, dataTable);
		Container.addToContainer();
	}
	
	public void itemInquiry() throws TimeoutException, NoSuchElementException{
		
		Inquiry Inquiry = new Inquiry(test, driver , dataTable);
		Inquiry.itemInquiry();
		
	}
}
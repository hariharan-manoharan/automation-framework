package main.java.businessComponents.MOBILE.AIRTEL;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import main.java.testDataAccess.DataTable;
import main.java.utils.Utility;

public class AssetUpdate extends Utility implements RoutineObjectRepository {
	

	@SuppressWarnings("rawtypes")
	public AssetUpdate(ExtentTest test, AndroidDriver driver, DataTable dataTable) {
		super(test, driver, dataTable);
	}

	public void selectRoutine(String routineName) {
		ScrolltoText(routineName);
		Click(By.name(routineName), "Click - Routine - " + routineName + " is selected");
	}

	
	public void assetUpdate() throws TimeoutException, NoSuchElementException,  WebDriverException {
		
		
		
		String assetcode = "TEST"+ String.valueOf(generateRandomNum());
		
		if(!ifAssetAvailable(assetcode)){		
		stock_serializedItem(assetcode, "BAL-MUNDKA-MDEL", "ON HAND", null, "SPART10001", null, 446, "LOT-A1");
		}
		
		selectRoutine("Asset Update");
		String text = GetText(ID_ACTION_BAR_SUBTITLE, "Routine name");
		
		if (text.equals("Asset Update")) {
			EnterText(By.xpath(String.format(XPATH_TXT, "Enter Location (*) :")), "Enter Location (*) :", "BAL-MUNDKA-MDEL");
			ClickNext();
			EnterText(By.xpath(String.format(XPATH_TXT, "Enter Barcode (*) :")), "Enter Barcode (*) :" + assetcode, assetcode);
			ClickNext();
			String serialNumber = "SLNUM"+generateRandomNum();
			EnterText(By.xpath(String.format(XPATH_TXT, "Enter New Serial Number :")), "Enter New Serial Number :" + serialNumber, serialNumber);
			ClickNext();
			ClickNext();
			ClickNext();
			EnterText(By.xpath(String.format(XPATH_TXT, "Enter New Software Revision :")), "Enter New Software Revision :", "1.2.0");
			ClickNext();
			EnterText(By.xpath(String.format(XPATH_TXT, "Enter New Firmware Revision :")), "Enter New Firmware Revision :", "1.2.0");
			ClickNext();
			EnterText(By.xpath(String.format(XPATH_TXT, "Enter New Hardware Revision :")), "Enter New Hardware Revision :", "1.2.0");
			ClickNext();
			ClickNext();
			EnterText(By.xpath(String.format(XPATH_TXT, "Enter Notes :")), "Enter Notes :", "Asset Update - Test Notes");
			ClickNext();
			
		}
		
	}
	
	

}

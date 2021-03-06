package main.java.businessComponents.MOBILE.AIRTEL;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import main.java.testDataAccess.DataTable;
import main.java.utils.Utility;

public class Container extends Utility implements RoutineObjectRepository  {
	
	//Xpath
		
	By LOCATION_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Location Name (*) :"));
	By ADD_TO_CONTAINER_LOCATION_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Location (*) :"));
	By CONTAINERCODE_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Container Code (*) :"));
	By TOSTATUS_XPATH = By.xpath(String.format(XPATH_TXT, "Enter To Status (*) :"));
	By BARCODE_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Barcode (*) :"));
	By ASSET_SERIAL_BARCODE_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Asset Code (UIN) or Serial Number :"));	
	By MFGPARTNUM_BARCODE_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Mfg. Part Number :"));
	By LOTNUMBER_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Lot Number (*) :"));
	By QTY_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Quantity (*) :"));
	By NOTES_XPATH = By.xpath(String.format(XPATH_TXT, "Enter Notes :"));
	
	
	
	
	
	//Confirm Messages
	
	String confirmMsg = "Container %s does not exist. Do you wish to create a new container?";
	
	
	//Error Messages
	
	
	//Routine Names
	
	String ADD_TO_CONTAINER = "Add To Container";
	String REMOVE_FROM_CONTAINER = "Remove From Container";
	String OPEN_CONTAINER = "Open Container";
	String CLOSE_CONTAINER = "Close Container";
	
	
	//Other Declarations
	
	private String folderName = "Container";
	private HashMap<String, String> containerTestDataHashmap = new HashMap<String, String>();

	@SuppressWarnings("rawtypes")
	public Container(ExtentTest test, AndroidDriver driver, DataTable dataTable) {
		super(test, driver, dataTable);
		getTestData();
		selectRoutineFolder(folderName);
	}
	
	public void getTestData(){
		containerTestDataHashmap = dataTable.getRowData("Container");
	}

	public void selectRoutine(String routineName) {
		//ScrolltoText(routineName);
		Click(By.name(routineName), "Click - Routine - " + routineName + " is selected");
	}
	
	public void selectRoutineFolder(String folderName) {		
		Click(By.name(folderName), "Click - Routines Folder - " + folderName + " is selected");
	}
	
	public void validateTransaction(String loopField) {		
		if (isObjectPresent(By.xpath(String.format(XPATH_TXT, loopField)),"Loop field - "+loopField)) {
			report("Open Container Transaction is successfull", LogStatus.PASS);			
		} else {
			report("Open Container Transaction is not successfull", LogStatus.FAIL);			
		}
	}
	
	
	public void validateMessage(String msg) {		
		if (GetText(ID_MESSAGE, GetText(ID_ALERT_TITLE, "Alert Title")).equalsIgnoreCase(msg)) {
			report(msg + " is displayed", LogStatus.PASS);			
		} else {
			report(msg + " is not displayed", LogStatus.FAIL);			
		}
	}
	
	public boolean validateMessageContains(String msgContains) {
		if(isObjectPresent(ID_MESSAGE, "Prompt")){
		if (GetText(ID_MESSAGE, GetText(ID_ALERT_TITLE, "Alert Title")).contains(msgContains)) {
			report("Message contains '" + msgContains + "' is displayed", LogStatus.INFO);			
			return true;
		} else {
			report("Message contains '" + msgContains + "' is not displayed", LogStatus.INFO);	
			return false;
		}
		}else {
			report("Message contains '" + msgContains + "' is not displayed", LogStatus.INFO);	
			return false;
		}
	}
	
	
	public void addToContainer() throws TimeoutException, NoSuchElementException,  WebDriverException {
		
		String containerType = containerTestDataHashmap.get("CONTAINER_TYPE");
		String location = containerTestDataHashmap.get("LOCATION_NAME");
		String containerCode = containerTestDataHashmap.get("CONTAINER_CODE");
		String toStatus = containerTestDataHashmap.get("TO_STATUS");		
		String noOfItemsToBeAdded = containerTestDataHashmap.get("NO_ITEMS_TO_BE_ADDED");		
		String notes = containerTestDataHashmap.get("NOTES");
		
		selectRoutine("Add To Container");		
		if (GetText(ID_ACTION_BAR_SUBTITLE, "Routine name").equals(ADD_TO_CONTAINER)) {
			EnterText(ADD_TO_CONTAINER_LOCATION_XPATH, "Enter Location (*) :", location);			
			ClickNext();
			EnterText(CONTAINERCODE_XPATH, "Enter Container Code (*) :", containerCode);
			ClickNext();
			if (containerType.equalsIgnoreCase("NEW") && GetText(ID_MESSAGE, "Confirm Message").equalsIgnoreCase(String.format(confirmMsg, containerCode))) {
			Click(ID_MESSAGE_CONFIRM_YES, "Clicked 'Yes' for message");
			EnterText(TOSTATUS_XPATH, "Enter To Status (*) :", toStatus);
			ClickNext();
			}
			
			for(int i=1; i<= Integer.parseInt(noOfItemsToBeAdded); i++) {							
			
			String barcodeType = containerTestDataHashmap.get("BARCODE_TYPE_"+i);
			String barcode = containerTestDataHashmap.get("BARCODE_"+i);
			String mfgPartNumbertype = containerTestDataHashmap.get("MFG_PART_NUMBER_TYPE_"+i);		
									
			switch(barcodeType){
			
			case "CONTAINERCODE":
				EnterText(BARCODE_XPATH, "Enter Barcode (*) :", barcode);
				ClickNext();
				ClickNext();
			break;
			
			case "ASSETCODE":
			case "SERIAL_NUMBER":	
			case "PACKAGE_TAG":
				EnterText(BARCODE_XPATH, "Enter Barcode (*) :", barcode);
				ClickNext();
				ClickNext();				
			break;	
			
			case "SERIALIZED_ITEMCODE":	
				EnterText(BARCODE_XPATH, "Enter Barcode (*) :", barcode);
				ClickNext();
				EnterText(ASSET_SERIAL_BARCODE_XPATH, "Enter Asset Code (UIN) or Serial Number :", barcode);
				ClickNext();
				ClickNext();
			break;
			
			case "NON_SERIALIZED_ITEMCODE":
				
				String qty = containerTestDataHashmap.get("QTY_"+i);
				
				EnterText(BARCODE_XPATH, "Enter Barcode (*) :", barcode);
				ClickNext();
				if(mfgPartNumbertype.equalsIgnoreCase("MULTIPLE")){
				if(isObjectPresent(MFGPARTNUM_BARCODE_XPATH, "Enter Mfg. Part Number :")){
					ClickSpyGlass("Enter Mfg. Part Number :",19);
					EnterText(MFGPARTNUM_BARCODE_XPATH, "Enter Mfg. Part Number :", GetPickListValue(1));
					ClickNext();
				}
				}
				if(isObjectPresent(LOTNUMBER_XPATH, "Enter Lot Number (*) :")){
					ClickSpyGlass("Enter Lot Number (*) :",25);	
					List<String> pickListValues = GetPickListValues();					
					for(int j = 0; j <= pickListValues.size(); j++){
					EnterText(LOTNUMBER_XPATH, "Enter Lot Number (*) :",pickListValues.get(j) );
					ClickNext();
					EnterText(QTY_XPATH, "Enter Quantity (*) :", qty);
					ClickNext();
					if(validateMessageContains("exceeds")){
						Click(ID_MESSAGE_OK, "Clicked 'OK' for the mobility prompt");
						clickDeviceBackButton();
						continue;
					}else{
						break;
					}
					}
					}else{
						EnterText(QTY_XPATH, "Enter Quantity (*) :", qty);
						ClickNext();
					}
				
				ClickNext();
			break;
			
			default:
			// do nothing	
			break;
			}
			
			}
			
			EnterText(NOTES_XPATH, "Enter Notes :", notes);
			ClickNext();
			
			//Verify whether Transaction is completed successfully
			validateTransaction("Enter Barcode (*) :");
		}
		
	}
	
	public void removeFromContainer() throws TimeoutException, NoSuchElementException,  WebDriverException {
		
		selectRoutine("Remove From Container");		
		if (GetText(ID_ACTION_BAR_SUBTITLE, "Routine name").equals(REMOVE_FROM_CONTAINER)) {
			
		}
	}
	
	public void openContainer() throws TimeoutException, NoSuchElementException,  WebDriverException {
		
		String location = containerTestDataHashmap.get("LOCATION_NAME");
		String containerCode = containerTestDataHashmap.get("CONTAINER_CODE");
		String notes = containerTestDataHashmap.get("NOTES");
		
		selectRoutine("Open Container");
		
		if (GetText(ID_ACTION_BAR_SUBTITLE, "Routine name").equals(OPEN_CONTAINER)) {
			EnterText(LOCATION_XPATH, "Enter Location Name (*) :", location);
			ClickNext();
			EnterText(CONTAINERCODE_XPATH, "Enter Container Code (*) :", containerCode);
			ClickNext();
			ClickNext();
			EnterText(NOTES_XPATH, "Enter Notes :", notes);
			ClickNext();
		}		
		
		//Verify whether Transaction is completed successfully
		validateTransaction("Enter Location Name (*) :");
		
		
	}
	
	public void closeContainer() throws TimeoutException, NoSuchElementException,  WebDriverException {
				
		String location = containerTestDataHashmap.get("LOCATION_NAME");
		String containerCode = containerTestDataHashmap.get("CONTAINER_CODE");
		String notes = containerTestDataHashmap.get("NOTES");
		
		selectRoutine("Close Container");		
		if (GetText(ID_ACTION_BAR_SUBTITLE, "Routine name").equals(CLOSE_CONTAINER)) {
			EnterText(LOCATION_XPATH, "Enter Location Name (*) :", location);
			ClickNext();
			EnterText(CONTAINERCODE_XPATH, "Enter Container Code (*) :", containerCode);
			ClickNext();
			ClickNext();
			EnterText(NOTES_XPATH, "Enter Notes :", notes);
			ClickNext();
		}
		
		//Verify whether Transaction is completed successfully
		validateTransaction("Enter Location Name (*) :");
		
	}

}

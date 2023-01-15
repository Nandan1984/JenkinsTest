package cs.qa.te.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import cs.qa.te.utils.ExcelUtil;



public class NegotiationsAddOfferPageTest extends BaseTest {
	
	SoftAssert softAssert = new SoftAssert();
	// Navigate to the Login page
	
	// User5 == Supplier ==Jonathan Arnold
	//User6 == Importer == Kimberly SMith
	//User7 == Forwarder == Adam smith
	
		@BeforeClass()
		public void loginPageTest()throws  InterruptedException {
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
		
		}
		
		@Test(priority =1, enabled=false) 
		public void createAddOffer() throws InterruptedException {
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.navigateAddOfferPage();
			addOfferPage.buyerDetails();
			addOfferPage.deliveryDetails();
			addOfferPage.productDetails();
			addOfferPage.product1();
			addOfferPage.clickNegotiate();
		
			String actualSpecVolumeValue = addOfferPage.supplierConfirmAddOfferFromNegotiations();
			
			String expectedSpecVolumeValue =  ("A Test Offer"+"300"+"60000");
			
			softAssert.assertEquals(actualSpecVolumeValue, expectedSpecVolumeValue);
			softAssert.assertAll();
		}
		
		@Test(priority =2, enabled=false)
		public void importerConfirmAddofferFromNegotiaitons() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			String actualImpNegotiationData = addOfferPage.importerConfirmAddOfferFromNegotiations();
			String expectedImpNegotiationData =  ("A Test Offer"+"300"+"60000");
			
			softAssert.assertEquals(actualImpNegotiationData, expectedImpNegotiationData);
			softAssert.assertAll();
		}
		
		@Test(priority =3, enabled=false)
		public void supplierAddProductNegotiationsSpecification() throws InterruptedException, IOException {
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.navigateToSpecification();
			
			
			String actualAddMessage = addOfferPage.negotiationsSpecificationAddProduct();
			String expectAddMessage = "Product successfully saved.";
			
			softAssert.assertEquals(actualAddMessage, expectAddMessage);
			softAssert.assertAll();
			
			String actualTxt = addOfferPage.getLastRowDataFromSpecification();
			
			System.out.println(actualTxt);
			
			
			loginPage.closePopUp();
			
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.importerNavigateDealPopup();
			
			String expectedImporterTxt = addOfferPage.getLastRowDataFromImporterSpecification();
			softAssert.assertEquals(actualTxt, expectedImporterTxt);
			softAssert.assertAll();
			
			String expectedImporterExcelTxt =addOfferPage.getLastRowDataFromImporterSpecificationForExcel();
			
			addOfferPage.getDataFromImporterNegotationsSpecificationDownloadExcel();
			String actualSpecificationDataTxt = addOfferPage.input_Prop().getProperty("NegotiationsSpecifiactionDetailsInExcel").replace(",", "").trim();
			
			softAssert.assertEquals(actualSpecificationDataTxt, expectedImporterExcelTxt);
			softAssert.assertAll();
		}
		
		
		@Test(priority =4, enabled=false)
		public void supplierAddDocDocuments() throws InterruptedException, IOException, AWTException {
		
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.navigateToSpecification();
			String actualText = addOfferPage.negotiationsDocumentsAddDocuments();
			String expectedText = "virus scanning in process";
			
			softAssert.assertEquals(actualText, expectedText);
			softAssert.assertAll();
		}
		
		@Test(priority =5, enabled=false)
		public void supplierImporterForwarderNegotationsAddSchedule() throws InterruptedException, IOException, AWTException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.navigateToSpecification();
			addOfferPage.negotiationsSchedule();
			
			String actualScheduleDataTxt = addOfferPage.input_Prop().getProperty("ScheduleDetails").replace("[", "").replace("]","").trim();
			String expectScheduleDataTxt = "27/08/2022Late June23/12/202225/12/202227/12/2022Test1, 12-09-2022, Yes, Importer, Forwarder, Internal Company, Test Demo Sawmill 10,";
			
			System.out.println(actualScheduleDataTxt);
			softAssert.assertEquals(actualScheduleDataTxt, expectScheduleDataTxt);
			softAssert.assertAll();
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.importerNavigateDealPopup();
			addOfferPage.navigateToSpecification();
			addOfferPage.getDataFromImporterSchedule();
			
			String actualImporterScheduleDataTxt = addOfferPage.input_Prop().getProperty("ImporterScheduleDetails").replace("[", "").replace("]","").trim();
			softAssert.assertEquals(actualImporterScheduleDataTxt, expectScheduleDataTxt);
			softAssert.assertAll();
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username7").trim(), prop.getProperty("password7").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.navigateToSpecification();
			addOfferPage.getDataFromForwarderSchedule();
			
			String actualForwarderScheduleDataTxt = addOfferPage.input_Prop().getProperty("ForwarderScheduleDetails").replace("[", "").replace("]","").trim();
			softAssert.assertEquals(actualForwarderScheduleDataTxt, expectScheduleDataTxt);
			softAssert.assertAll();
		}
		
		
		@Test(priority =6, enabled=false)
		public void supplierAddProductActiveDealsSpecification() throws InterruptedException, IOException {
		
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			
			addOfferPage.navigateToNegotiationsPage();
			
			addOfferPage.moveToActiveDeals();
			
			String actualAddMessage = addOfferPage.activeDealsSpecificationAddProduct();
			String expectAddMessage = "Product successfully saved.";
			
			softAssert.assertEquals(actualAddMessage, expectAddMessage);
			softAssert.assertAll();
			
			String expectedDetails = addOfferPage.getLastRowDataFromSpecification();
			System.out.println(expectedDetails);
			
			addOfferPage.activeDealsSpecificationLoadedVolume();
			
			String expectedLoadVolume =  addOfferPage.input_Prop().getProperty("LoadVolumeDetails").trim();
			
			
			
			loginPage.closePopUp();
			
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			
			String actualImporterTxt = addOfferPage.getLastRowDataFromImporterSpecification();
			
			
			
			String actualLoadVolume = addOfferPage.activeDealsImporterSpecificationLoadedVolume();
			
			System.out.println("actualImporterTxt "+actualImporterTxt);
			System.out.println("actualLoadVolume "+actualLoadVolume);
			
			softAssert.assertEquals(actualImporterTxt, expectedDetails );
			softAssert.assertAll();
			
			softAssert.assertEquals(actualLoadVolume, expectedLoadVolume);
			softAssert.assertAll();	
			
			String expectedImporterExcelTxt =addOfferPage.getLastRowDataFromImporterSpecificationForExcel();
			
			addOfferPage.getDataFromImporterActiveDealsSpecificationDownloadExcel();
			String actualSpecificationDataTxt = addOfferPage.input_Prop().getProperty("NegotiationsSpecifiactionDetailsInExcel").replace(",", "").trim();
			
			softAssert.assertEquals(actualSpecificationDataTxt, expectedImporterExcelTxt);
			softAssert.assertAll();
			
		}
		
		@Test(priority =7, enabled=false)
		public void supplierActiveDealsAddDocDocuments() throws InterruptedException, IOException, AWTException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			String actualText = addOfferPage.negotiationsDocumentsAddDocuments();
			String expectedText = "virus scanning in process";
			
			softAssert.assertEquals(actualText, expectedText);
			softAssert.assertAll();
		}
		
		
		@Test(priority =8, enabled=false)
		public void supplierImporterForwarderActiveDealsAddSchedule() throws InterruptedException, IOException, AWTException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.getDataFromForwarderSchedule(); //Use forwarder method to fetch data from Supplier
			
			String actualScheduleDataTxt = addOfferPage.input_Prop().getProperty("ScheduleDetails").replace("[", "").replace("]","").trim();
			String expectScheduleDataTxt = "27/08/2022Late June23/12/202225/12/202227/12/2022Test1, 12-09-2022, Yes, Importer, Forwarder, Internal Company, Test Demo Sawmill 10,";
			
			System.out.println(actualScheduleDataTxt);
			softAssert.assertEquals(actualScheduleDataTxt, expectScheduleDataTxt);
			softAssert.assertAll();
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.getDataFromImporterSchedule();
			
			String actualImporterScheduleDataTxt = addOfferPage.input_Prop().getProperty("ImporterScheduleDetails").replace("[", "").replace("]","").trim();
			softAssert.assertEquals(actualImporterScheduleDataTxt, expectScheduleDataTxt);
			softAssert.assertAll();
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username7").trim(), prop.getProperty("password7").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.getDataFromForwarderSchedule();
			
			String actualForwarderScheduleDataTxt = addOfferPage.input_Prop().getProperty("ForwarderScheduleDetails").replace("[", "").replace("]","").trim();
			softAssert.assertEquals(actualForwarderScheduleDataTxt, expectScheduleDataTxt);
			softAssert.assertAll();
		}
		
		
		
		
		@Test(priority =9, enabled=false)
		public void supplierActiveDealsInstructionsSettings() throws InterruptedException, IOException, AWTException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionSettings();
			
			String actualExporterID = addOfferPage.getExporterIDFromManageCompany();
			
			String expectedExporterID = addOfferPage.input_Prop().getProperty("ExporterIdentificationNumber").trim();
			
			softAssert.assertEquals(actualExporterID, expectedExporterID);
			softAssert.assertAll();
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			String actualImporterVAT = addOfferPage.settingsManageCompany();
			String expectedImporterVAT = addOfferPage.input_Prop().getProperty("ImporterTaxIDNumber").trim();
			
			
			softAssert.assertEquals(actualImporterVAT, expectedImporterVAT);
			softAssert.assertAll();
			
		}
		
		@Test(priority =10, enabled=false)
		public void supplierActiveDealsInstructionsCADHandelsbankenBank() throws InterruptedException, IOException, AWTException {
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionCADHandelsbankenBank();
			addOfferPage.enterDataCADHandelsbankenBankFromSupplier();
			String actualEnterDataInCAD = addOfferPage.getEnterDataCADHandelsbankenBankFromSupplier();
			String expectEnterDataInCAD = "1"+"2"+"2"+"2"+"true"+"true"+"true"+"2000"+"11223344"+"true"+"$1000"+"A13046";
			
			softAssert.assertEquals(actualEnterDataInCAD, expectEnterDataInCAD);
			softAssert.assertAll();
			
			
			addOfferPage.validateCADHandelsbankenBankFromSupplierMyProfile();
			addOfferPage.validateCADHandelsbankenBankFromSupplierCompanyProfile();
			
			//From Importer profile
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.validateCADHandelsbankenBankFromImporterMyProfile();
			
			String actualCADHandelsbankenBank = addOfferPage.input_Prop().getProperty("HandleBy"+ "ReceiverDetails"+ "SupplierAddrsDetails"+ "SupplierAddrsZipDetails"
			+"ConsigneeAddrs"+"BuyerDetails"+"CollectBankDetails");
			
			String expectedCADHandelsbankenBank = addOfferPage.input_Prop().getProperty("SupplierName"+ "SupplierBankDetails"+ "PublicProfileOfficeAddress"+ "PublicProfileCityZipDetails"
			+"ImporterConsigneeAddress"+"ImporterNotify"+"ImporterBankNameAddress");
			
			softAssert.assertEquals(actualCADHandelsbankenBank, expectedCADHandelsbankenBank);
			softAssert.assertAll();
			
			
			
		}
		
		@Test(priority =11, enabled=false)
		public void supplierActiveDealsInstructionsCADNordeaBank() throws InterruptedException, IOException, AWTException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionCADNordeaBank();
			
			String actualCADNordeaBank = addOfferPage.input_Prop().getProperty("PrincipalReference"+"DraweesBankAddres"+"ContactPerson");
			String expectedCADNordeaBank = addOfferPage.input_Prop().getProperty("AddOfferDealNumber".replace("N", "P")+"SupplierBankDetails"+"SupplierName");
			
			softAssert.assertEquals(actualCADNordeaBank, expectedCADNordeaBank);
			softAssert.assertAll();
		}
		
		@Test(priority =12, enabled=false)
		public void supplierActiveDealsInstructionsProductDescription() throws InterruptedException, IOException, AWTException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			
			String actualProdDescText = addOfferPage.activeDealsInstructionProductDescription();
			String expectProdDescText = "Test1234";
			
			softAssert.assertEquals(actualProdDescText, expectProdDescText);
			softAssert.assertAll();
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username7").trim(), prop.getProperty("password7").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			
			String actualForwarderProdDescText = addOfferPage.activeDealsForwarderInstructionProductDescription();
			
			//Validate Product description with Supply & Forwarder
			softAssert.assertEquals(actualForwarderProdDescText, expectProdDescText);
			softAssert.assertAll();
		}
		
		@Test(priority =13, enabled=false)
		public void supplierActiveDealsInstructionsPackListWeightDemo() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsContractLCBLValidatePackList();
			addOfferPage.activeDealsFreightCountryAndPortValidatePackList();
			
			String actualPackingListDetails = addOfferPage.activeDealsInstructionPackListWeightDemo();
			
			//String contractLC = addOfferPage.input_Prop().getProperty("ContractLC");
			
			String getContractBL = addOfferPage.input_Prop().getProperty("ContractBL");
			String ACID = addOfferPage.input_Prop().getProperty("ACID");
			String GetOrgCountry = addOfferPage.input_Prop().getProperty("GetOrgCountry");
			String GetOrgPort = addOfferPage.input_Prop().getProperty("GetOrgPort");
			String GetDestCountry = addOfferPage.input_Prop().getProperty("GetDestCountry");
			String GetDestPort = addOfferPage.input_Prop().getProperty("GetDestPort");
			String ImporterTaxIDNumber = addOfferPage.input_Prop().getProperty("ImporterTaxIDNumber");
			String LoadVolumeDetails = addOfferPage.input_Prop().getProperty("LoadVolumeDetails");
			String ContractInvoiceNo = addOfferPage.input_Prop().getProperty("ContractInvoiceNo");
			
			
			
			String expectPackingListDetails = (ContractInvoiceNo+getContractBL+ACID+GetOrgCountry+GetOrgPort
					+GetDestCountry+GetDestPort+ImporterTaxIDNumber+LoadVolumeDetails).replace("kg","");
			
			System.out.println(expectPackingListDetails);
			
			
			softAssert.assertEquals(actualPackingListDetails, expectPackingListDetails);
			softAssert.assertAll();
			
		}
		
		@Test(priority =14, enabled=false)
		public void supplierActiveDealsInstructionsEUR1() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionEUR1();
			addOfferPage.validateInstructionEUR1FromSupplier();
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.validateInstructionEUR1FromImporterMyProfile();
			
			String EUR1ExporterAddress = addOfferPage.input_Prop().getProperty("EUR1ExporterAddress").replaceAll("[\\n\\t ]", "");
			String EUR1ExporterID = addOfferPage.input_Prop().getProperty("EUR1ExporterID").replaceAll("[\\n\\t ]", "");
			String EUR1Consignee = addOfferPage.input_Prop().getProperty("EUR1Consignee").replaceAll("[\\n\\t ]", "");
			String EUR1ImporterID = addOfferPage.input_Prop().getProperty("EUR1ImporterID").replaceAll("[\\n\\t ]", "");
			String EUR1TradeOrginCountry = addOfferPage.input_Prop().getProperty("EUR1TradeOrginCountry").replaceAll("[\\n\\t ]", "");
			String EUR1TradeDestinationCountry = addOfferPage.input_Prop().getProperty("EUR1TradeDestinationCountry").replaceAll("[\\n\\t ]", "");
			String EUR1ProductOrginCountry = addOfferPage.input_Prop().getProperty("EUR1ProductOrginCountry").replaceAll("[\\n\\t ]", "");
			String EUR1ProductDestinationCountry = addOfferPage.input_Prop().getProperty("EUR1ProductDestinationCountry").replaceAll("[\\n\\t ]", "");
			String EUR1Transport = addOfferPage.input_Prop().getProperty("EUR1Transport").replaceAll("[\\n\\t ]", "").replaceAll("BL","");
			String EUR1GrossWeight = addOfferPage.input_Prop().getProperty("EUR1GrossWeight").replaceAll("[\\n\\t ]", "");
			String EUR1ACID = addOfferPage.input_Prop().getProperty("EUR1ACID").replaceAll("[\\n\\t ]", "");
			
			String actualEUR1 = EUR1ExporterAddress+EUR1ExporterID+EUR1Consignee+EUR1ImporterID+EUR1TradeOrginCountry+
					EUR1TradeDestinationCountry+EUR1ProductOrginCountry+EUR1ProductDestinationCountry+EUR1Transport+
					EUR1GrossWeight+EUR1ACID;

			
			
			
			String PublicProfiletLegalCompanyName = addOfferPage.input_Prop().getProperty("PublicProfiletLegalCompanyName").replaceAll("[\\n\\t ]", "");
			String PublicProfileOfficeAddress = addOfferPage.input_Prop().getProperty("PublicProfileOfficeAddress").replaceAll("[\\n\\t ]", "");
			String GetOrgCountry = addOfferPage.input_Prop().getProperty("GetOrgCountry").replaceAll("[\\n\\t ]", "");
			String ExporterIdentificationNumber = addOfferPage.input_Prop().getProperty("ExporterIdentificationNumber").replaceAll("[\\n\\t ]", "");
			String ImporterNotify = addOfferPage.input_Prop().getProperty("ImporterNotify").replaceAll("[\\n\\t ]", "");
			String ImporterTaxIDNumber = addOfferPage.input_Prop().getProperty("ImporterTaxIDNumber").replaceAll("[\\n\\t ]", "");
			String OrginalCountry = addOfferPage.input_Prop().getProperty("OrginalCountry").replaceAll("[\\n\\t ]", "");
			String DestinationCountry = addOfferPage.input_Prop().getProperty("DestinationCountry").replaceAll("[\\n\\t ]", "");
			String orginalCountry = addOfferPage.input_Prop().getProperty("OrginalCountry").replaceAll("[\\n\\t ]", "");
			String destinationCountry = addOfferPage.input_Prop().getProperty("DestinationCountry").replaceAll("[\\n\\t ]", "");
			String ContractBL = addOfferPage.input_Prop().getProperty("ContractBL").replaceAll("[\\n\\t ]", "").replaceAll("BL","");
			String GrossWeight = addOfferPage.input_Prop().getProperty("GrossWeight").replaceAll("[\\n\\t ]", "");
			String ACID = addOfferPage.input_Prop().getProperty("ACID").replaceAll("[\\n\\t ]", "");
			
			String expectEUR1 =PublicProfiletLegalCompanyName+ PublicProfileOfficeAddress+GetOrgCountry+ExporterIdentificationNumber+ImporterNotify+
					ImporterTaxIDNumber+OrginalCountry+DestinationCountry+orginalCountry+destinationCountry+
					ContractBL+GrossWeight+ACID;
			
			softAssert.assertEquals(actualEUR1, expectEUR1);
			softAssert.assertAll();
		}
		
		
		@Test(priority =15, enabled=false)
		public void supplierActiveDealsInstructionsCO() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionCO();
			addOfferPage.validateInstructionCOFromSupplier();
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.validateInstructionCOFromImporterMyProfile();
			
			String actualCO = addOfferPage.input_Prop().getProperty("CODetails").trim().replaceAll("[\\n\\t ]", "");
			
			String fromSupplier = addOfferPage.input_Prop().getProperty("ValidateCODetailsFromSupplier").trim().replaceAll("[\\n\\t ]", "");
			String fromImporter = addOfferPage.input_Prop().getProperty("ImporterNotify").trim().replaceAll("[\\n\\t ]", "");
			
			String expectCO = fromSupplier + fromImporter;
			
			
			softAssert.assertEquals(actualCO, expectCO);
			softAssert.assertAll();	
			
		}
		
		@Test(priority =16, enabled=false)
		public void supplierActiveDealsInstructionsECO() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionECO();
			
			String actualECO = addOfferPage.input_Prop().getProperty("ECODetails").replace(",", "").trim().replaceAll("[\\n\\t ]", "");
			
			addOfferPage.validateInstructionECOFromSupplier();
			String validateFromSupplier = addOfferPage.input_Prop().getProperty("ValidateECODetailsFromSupplier").trim();
					
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.validateInstructionECOFromImporterMyProfile();
			String validateFromImporter = addOfferPage.input_Prop().getProperty("ImporterNotify").trim();
			
			String expectECO = (validateFromSupplier+validateFromImporter).replace(",", "").replaceAll("[\\n\\t ]", "");
			
			
			softAssert.assertEquals(actualECO, expectECO);
			softAssert.assertAll();	
			
		}
		
		@Test(priority =17, enabled=false)
		public void supplierActiveDealsInstructionsEEUR1() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionEEUR1();
			
			String actualEEUR1 = addOfferPage.input_Prop().getProperty("EEUR1Details").replace(",", "").trim().replaceAll("[\\n\\t ]", "");
			
			addOfferPage.validateInstructionEEUR1FromSupplier();
			String validateFromSupplier = addOfferPage.input_Prop().getProperty("ValidateEEUR1DetailsFromSupplier").trim();
					
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.validateInstructionEEUR1FromImporterMyProfile();
			String validateFromImporter = addOfferPage.input_Prop().getProperty("ImporterNotify").trim();
			
			String expectEEUR1 = (validateFromSupplier+validateFromImporter).replace(",", "").replaceAll("[\\n\\t ]", "");
			
			
			softAssert.assertEquals(actualEEUR1, expectEEUR1);
			softAssert.assertAll();	
		}
		
		@Test(priority =18, enabled=false)
		public void supplierActiveDealsInstructionsPhyto() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionPhyto();
			
			String actualPhyto = addOfferPage.input_Prop().getProperty("PhytoDetails").trim().replaceAll("[\\n\\t ]", "");
			
			addOfferPage.validateInstructionPhytoFromSupplier();
			String validateFromSupplier = addOfferPage.input_Prop().getProperty("ValidatePhytoDetailsFromSupplier").trim();
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.validateInstructionPhytoFromImporterMyProfile();
			String validateFromImporter = addOfferPage.input_Prop().getProperty("ImporterAddressDetails").trim();
			
			String expectPhyto = (validateFromSupplier+validateFromImporter).replaceAll("[\\n\\t ]", "");
			
			softAssert.assertEquals(actualPhyto, expectPhyto);
			softAssert.assertAll();	
		}
		
		@Test(priority =19, enabled=false)
		public void supplierActiveDealsInstructionsBL() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsInstructionBL();
			
			String actualBL = addOfferPage.input_Prop().getProperty("BLDetails").trim().replaceAll("[\\n\\t ]", "");
			
			addOfferPage.validateInstructionBLFromSupplier();
			String validateFromSupplier = addOfferPage.input_Prop().getProperty("ValidateBLDetailsFromSupplier").trim();
			
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.validateBLFromImporterMyProfile();
			
			String validateFromImporterConsignee = addOfferPage.input_Prop().getProperty("ImporterConsigneeAddress").trim();
			String validateFromImporter = addOfferPage.input_Prop().getProperty("ImporterNotify").trim();
			String expectBL = (validateFromSupplier+validateFromImporterConsignee+validateFromImporter).replaceAll("[\\n\\t ]", "");
			
			softAssert.assertEquals(actualBL, expectBL);
			softAssert.assertAll();	
		}
		
		@Test(priority =20, enabled=false)
		public void supplierActiveDealsPackingListDataValidate() throws InterruptedException, IOException, InvalidFormatException {
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			
			
			addOfferPage.activeDealsPackingList(); //include Packing Sign in credential in this method
			
			
			String actualPackIdData = addOfferPage.getPackIDDataFromUploadExcel().toString();
			String expectPackIdData = addOfferPage.getPackIDDataFromInputExcel().toString();
		   
			String actualSpeciesData = addOfferPage.getSpeciesDataFromUploadExcel().toString();
			String expectSpeciesData = addOfferPage.getSpeciesDataFromInputExcel().toString();
			
			String actualGradeData = addOfferPage.getGradeDataFromUploadExcel().toString();
			String expectGradeData = addOfferPage.getGradeDataFromInputExcel().toString();
			
			String actualThickessData = addOfferPage.getThicknessDataFromUploadExcel().toString();
			String expectThicknessData = addOfferPage.getThicknessDataFromInputExcel().toString();
			
			String actualWidthData = addOfferPage.getWidthDataFromUploadExcel().toString();
			String expectWidthData = addOfferPage.getWidthDataFromInputExcel().toString();
			
			String actualContainerData = addOfferPage.getContainerDataFromUploadExcel().toString();
			String expectContainerData = addOfferPage.getContainerDataFromInputExcel().toString();
			
			
			String actualTruckData = addOfferPage.getTruckDataFromUploadExcel().toString();
			String expectTruckData = addOfferPage.getTruckDataFromInputExcel().toString();
			
			
			String actualWagonData = addOfferPage.getWagonDataFromUploadExcel().toString();
			String expectWagonData = addOfferPage.getWagonDataFromInputExcel().toString();
			
			String actualBLData = addOfferPage.getBLDataFromUploadExcel().toString();
			String expectBLData = addOfferPage.getBLDataFromInputExcel().toString();
			
			String actualRFIDData = addOfferPage.getRFIDDataFromUploadExcel().toString();
			String expectRFIDData = addOfferPage.getRFIDDataFromInputExcel().toString();
			
			String actualTotalQTY = addOfferPage.getTotalQTYDataFromUploadExcel();
			String expectTotalQTY = addOfferPage.getTotalQTYDataFromInputExcel();
			
			System.out.println("Total Qty :" + expectTotalQTY);
			
			
			softAssert.assertEquals(actualPackIdData, expectPackIdData);
			softAssert.assertEquals(actualSpeciesData, expectSpeciesData);
			softAssert.assertEquals(actualGradeData, expectGradeData);
			softAssert.assertEquals(actualThickessData, expectThicknessData);
			softAssert.assertEquals(actualWidthData, expectWidthData);
			softAssert.assertEquals(actualContainerData, expectContainerData);
			softAssert.assertEquals(actualTruckData, expectTruckData);
			softAssert.assertEquals(actualWagonData, expectWagonData);
			softAssert.assertEquals(actualBLData, expectBLData);
			softAssert.assertEquals(actualRFIDData, expectRFIDData);
			softAssert.assertEquals(actualTotalQTY, expectTotalQTY);
			softAssert.assertAll();
			
			 
		}
		
		
		@Test(priority =21, enabled=false)
		public void supplierActiveDealsPackingListCalculationVolume() throws InterruptedException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			String actualVolume = addOfferPage.getPackListVolumeCalc();
			String expectVolume = addOfferPage.getPackListVolume();
			
			softAssert.assertEquals(actualVolume, expectVolume);
			softAssert.assertAll();
		}
		
		@Test(priority =22, enabled=false)
		public void validatePackingListDataWithForwarder() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username7").trim(), prop.getProperty("password7").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			addOfferPage.activeDealsForwarderPackingList();
			
		    String actualPackIdData = addOfferPage.getPackIDDataFromUploadExcel().toString();
		    String expectPackIdData =  addOfferPage.getPackIDDataFromInputExcel().toString();
		    softAssert.assertEquals(actualPackIdData, expectPackIdData);
		    softAssert.assertAll();
		}
		
		@Test(priority =23, enabled=false)
		public void validatePackingListDataWithImporter() throws InterruptedException, IOException {
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username6").trim(), prop.getProperty("password6").trim());
			
			addOfferPage.importerActiveDealsDealpopUpOpen();
			addOfferPage.activeDealsImporterPackingList();
			
		    String actualPackIdData = addOfferPage.getPackIDDataFromUploadExcel().toString();
		    String expectPackIdData =  addOfferPage.getPackIDDataFromInputExcel().toString();
		    softAssert.assertEquals(actualPackIdData, expectPackIdData);
		    softAssert.assertAll();
		    
		    
		}
		
		@Test(priority =24, enabled=false)
		public void supplierActiveDealsPackingListValidateTableQtyAndPercentage() throws InterruptedException, IOException, InvalidFormatException {
			
			
			loginPage.closePopUp();
			loginPage.clickLogin();
			loginPage.doLogin(prop.getProperty("username5").trim(), prop.getProperty("password5").trim());
			addOfferPage.navigateToNegotiationsPage();
			addOfferPage.activeDealsDealpopUpOpen();
			String actualTableDataDetails = addOfferPage.activeDealsPackingListTableData().replace("%","");
			
			String expectTableDataDetails= addOfferPage.getTableDataPercentageFromUploadExcel();
			
			softAssert.assertEquals(actualTableDataDetails, expectTableDataDetails);
			softAssert.assertAll();
			
		}
		
		
}

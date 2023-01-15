package cs.qa.te.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.awt.AWTException;
import java.awt.Robot;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import cs.qa.te.utils.ElementUtil;

public class NegotiationsAddOfferPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	private Properties inputProp;
	private Properties outputProp;
	
	private static final DecimalFormat df = new DecimalFormat("0.000");
	private static final DecimalFormat df1 = new DecimalFormat("0.0000");
	private static final DecimalFormat df2 = new DecimalFormat("0.00");
	
	public Properties props = new Properties();
	
	
	//1. By locator
	private By clickExport = By.id("menu_walkthrough_2");
	private By clickImport = By.id("menu_walkthrough_2");
	private By clickNegotiations = By.id("menu_walkthrough_2_2");
	private By endTourButton = By.id("end_tour");
	private By addOfferButton = By.xpath("//a[@class='pull-right btn btn-primary btn-cons']");
	private By clickCustomer = By.xpath("//span[@class='select2-selection__arrow']");
	private By selectCustomer = By.xpath("//select[@name='customer']");
	private By clickDestination = By.xpath("(//span[@class='select2-selection__arrow'])[3]");
	private By selectDestination = By.xpath("//select[@name='destination']");
	private By selectIncoterms = By.xpath("//select[@name='incoterm']");
	private By clickContainer = By.xpath("//label[contains(text(),'Container')]");
	private By clickFreightYes = By.xpath("//label[contains(text(),'Yes')]");
	private By clickAddFreight = By.xpath("//input[@class='freight_ref']");
	private By spec = By.name("spec");
	private By validUntil = By.xpath("//input[@data-date-format='dd/mm/yyyy']");
	
	private By selectOrigin = By.xpath("//select[@name='grading_by_country']");
	private By selectLocation = By.xpath("//select[@id='company_location']");
	private By clickCheckboxLocation = By.xpath("//span[@class='fs-checkbox']");
	private By clickSelectCurrency = By.xpath("(//span[@class='select2-selection__arrow'])[5]");
	private By selectCurrency = By.xpath("//select[@name='tp_currency']");
	private By ClickGoodsReady = By.xpath("(//span[@class='select2-selection__rendered'])[6]"); //For Testing server it is [6]
	private By goodsReady = By.xpath("//select[@name='goods_ready_approx']");
	private By clickGoodsReadyMonth = By.xpath("(//span[@class='select2-selection__arrow'])[7]");//For Testing server it is [7]
	private By enterMonth = By.xpath("//input[@class='select2-search__field']");
	private By goodsReadyMonth = By.xpath("//select[@name='goods_ready_month']");
	private By selectOfferGrade = By.xpath("//select[@name='tp_grade[]']");
	private By selectOfferSpecies = By.xpath("//select[@name='tp_species_id[]']");
	private By thickness = By.name("tp_thickness[]");
	private By width = By.name("tp_width[]");
	private By length = By.name("tp_length[]");
	private By m3 = By.name("tp_m3[]");
	private By price = By.name("tp_priceidea[]");
	private By negotiate = By.name("add_offer_submit");
	private By clickPagination = By.xpath("//a[contains(text(),24)]");
	private By clickLastRow = By.xpath("(//tbody)[3]/tr[last()]");
	private By sampleCSV = By.xpath("//a[contains(text(),'Sample CSV')]");
	private By negoSpec = By.xpath("(//tr[@role='row']//td)[6]");
	private By negoVolume = By.xpath("(//tr[@role='row']//td)[7]");
	private By negoValue = By.xpath("(//tr[@role='row']//td)[8]");
	private By importerSpec = By.xpath("(//td[@class='deal_attribute deal-attribute-cursor'])[6]");
	private By importerVolume = By.xpath("//td[contains(text(),'A Test Offer')]//following::td[1]");
	private By importerValue = By.xpath("//td[contains(text(),'A Test Offer')]//following::td[2]");
	
	//Deal popup > Negotiations
	private By getDealID = By.xpath("//td[contains(text(),'A Test Offer')]//preceding::td[@class='deal_attribute deal-attribute-cursor sorting_1']");
	private By clickSpecification = By.id("modal_specification_tab_head");
	private By addProd = By.xpath("//input[@value='(+) Add']");
	private By certificateContract = By.xpath("//td[@class='certificate_on_contract_content ']");
	private By selectGrade = By.xpath("//select[starts-with(@id,'toc_grade_')]");
	private By selectSpecies = By.xpath("//select[@id='tp_species_id_new_0']");
	private By selectMoisture = By.xpath("//select[@id='tp_moisture_id_0']");
	private By enterThickness = By.xpath("//input[@placeholder='Thickness']");
	private By enterWidthProd = By.xpath("//input[@placeholder='Width']");
	private By enterLength = By.xpath("//input[@placeholder='Length']");
	private By enterVolume = By.id("append_tp_m3_0");
	private By enterGWeight = By.id("append_tp_gross_weight_0");
	private By enterNWeight = By.id("append_tp_net_weight_0");
	private By enterPrice = By.id("append_tp_priceidea_unit_0");
	private By salesValue = By.xpath("//input[@placeholder='Sale value']");
	private By enterMarking = By.id("append_tp_priceidea_unit_0");
	//private By selectCertificate = By.xpath("(//select[@name='tp_certificate[]'])[2]");
	private By clickSpecSave = By.xpath("//input[@onclick='addOrUpdateSpecificationProduct(event)']");
	private By saveSuccessMsg = By.xpath("//div[@class='swal-text']");
	private By clickOk = By.xpath("//button[@class='swal-button swal-button--confirm']");
	private By getLastRowData = By.xpath("(//tbody/tr[position()=(last() - 1)])[2]//input[@type='text']"); //(//tbody/tr[position()=(last() - 1)])[2]//input
	private String dealNumber = input_Prop().getProperty("AddOfferDealNumber").trim();
	private By clickToOpenDeal = By.xpath("//td[contains(text(),'"+dealNumber+"')]");		
	private By getTotalVolume = By.xpath("//td[contains(text(),' Volume')]/h3");
	private By getTotalValue = By.xpath("//td[contains(text(),'Sales')]/h3");
	private By clickExportExcel = By.xpath("//a[contains(text(),'Export to Excel')]");
	private By getLastHSCode = By.xpath("(//tbody/tr[position()=(last() - 1)])[2]//following::span[@class='select2-selection__rendered']");
	private By getLastGrade = By.xpath("(//tbody/tr[position()=(last() - 1)])[2]//select[@name='tp_grade[]']/option[@selected]");
	private By getLastSpecies = By.xpath("(//tbody/tr[position()=(last() - 1)])[2]//select[@name='tp_species_id[]']/option[@selected]");
	private By getLastMoisture = By.xpath("(//tbody/tr[position()=(last() - 1)])[2]//select[@name='tp_moisture_id[]']/option[@selected]");
	private By getLastGS1 = By.xpath("(//tbody/tr[position()=(last() - 1)])[2]//select[@name='tp_gs1_code[]']/option[@selected][2]");
	
	
	
	
	
	
	private By clickDocuments = By.id("modal_attachments_tab_head");
	private By clickAddDoc = By.id("documents_add_tab");
	private By uploadFile = By.xpath("//input[@id='user_doc_0']");
	private By clickUpload = By.id("upload_attachments");
	private By clickDocOk = By.xpath("//button[@class='swal-button swal-button--confirm']");
	private By clickAllDocTab = By.id("documents_list_tab");
	private By virusScanText = By.xpath("//div[contains(text(),'virus scanning in process')]");
	
	
	//Negotiations > Schedule
	private By clickSchedule = By.xpath("//a[@id='modal_schedules_tab_head']");
	private By clickScheduleEdit = By.xpath("//input[@class='btn btn-primary btn_edit_schedules']");
	private By getScheduleDeadLineAccept = By.xpath("//h6[contains(text(),'27/08/2022')]");
	private By getScheduleGoodsEstimate = By.xpath("//h6[contains(text(),'Late June')]");
	private By getScheduleGoodsEstimateMonth = By.xpath("//select[@name='goods_ready_estimate_month']");
	private By enterScheduleGoodsMill = By.xpath("//input[@name='goods_ready_in_mill']");
	private By enterScheduleDeliveryPort = By.xpath("//input[@name='delivery_to_port_by']");
	private By enterScheduleETS = By.xpath("//input[@name='ets_date']");
	private By getScheduleSave = By.xpath("//input[@class='btn btn-primary btn_save_schedules']");
	private By getScheduleSaveOk = By.xpath("//div[contains(text(),'Details are saved successfully.')]/following::button");
	private By getScheduleGoodsMill = By.xpath("(//h6[contains(text(),'Goods ready in mill')]/following::h6)[1]");
	private By getScheduleDeliveryPort = By.xpath("(//h6[contains(text(),'Delivery to port')]/following::h6)[1]");
	private By getScheduleETS = By.xpath("(//h6[contains(text(),'ETS')]/following::h6)[1]");
	private By getScheduleAddDate = By.xpath("//input[@class='btn btn-primary btn_add_schedules']");
	private By getScheduleAddDateEventName = By.xpath("//input[@name='event_name']");
	private By getScheduleAddDateEventDate = By.xpath("//input[@name='event_date']");
	private By getScheduleAddDateAddCalendarYes = By.xpath("//input[@id='is_add_to_own_calendar_yes']/following::label[1]");
	private By getScheduleAddDateImporter = By.xpath("//label[@for='is_visible_to_importer']");
	private By getScheduleAddDateForwarder = By.xpath("//label[@for='is_visible_to_forwarder']");
	private By getScheduleAddDateInternalCompany = By.xpath("//label[@for='is_visible_to_company']");
	private By getScheduleAddDateSave = By.xpath("//a[@id='save_deal_schedules_btn']");
	private By getScheduleAddDateSaveOk = By.xpath("//div[contains(text(),'Details are saved successfully.')]/following::button");
	private By getScheduleAddDateData = By.xpath("//table[@class='table table-striped']//td");
	
	
	
	
	
	
	
	//Active Deals
	
	private By clickActionMenu = By.xpath("(//td[contains(text(),'"+dealNumber+"')]/following::a[@id='dropdownMenuLink'])[1]");
	private By moveToActiveDeals = By.xpath("(//td[contains(text(),'"+dealNumber+"')]/following::a[@class='dropdown-item move_to_active_deal_btn'])");
	private String changeDealNumber = input_Prop().getProperty("AddOfferDealNumber").replace("N", "P").trim();
	private By clickToOpenDealActiveDeals = By.xpath("//td[contains(text(),'"+changeDealNumber+"')]");
	private By clickActiveCloseBtn = By.xpath("//button[@class='close activeDealEscBtn']");
	private By clickActiveDeals = By.xpath("//a[contains(text(),'Active Deals')]");
	private By clickLoadVolume = By.id("modal_loaded_volume_tab_head");
	private By clickEdit = By.xpath("//input[@class='btn btn-primary btn_edit_loaded_volume m-b-10']");
	private By enterQtyPack = By.id("bundles");
	private By enterGrossWeight = By.id("gross_weight");
	private By getGrossWeight = By.xpath("//div[contains(text(),'Total gross weight')]/following::h4");
	private By enterNetWeight = By.id("net_weight");
	private By getNetWeight = By.xpath("(//div[contains(text(),'Total net weight')]/following::h4)[1]");
	private By getNoOfPack = By.xpath("//div[starts-with(text(),'Quantity ')]/following::h4[1]");
	private By clickLoadSave = By.xpath("//div[@id='modal_loaded_volume_tab']//input[contains(@value,'Save')]");
	
	private By getImportQty = By.xpath("//div[contains(text(),'Quantity of packages')]/following-sibling::h4");
	private By getImportGrossWeight = By.xpath("//div[contains(text(),'Total gross weight')]/following-sibling::h4");
	private By getImportNetWeight= By.xpath("//div[contains(text(),'Total net weight')]/following-sibling::h4");
	
	
	private By clickContract = By.id("modal_contract_tab_head");
	private By enterBL = By.id("bl_number_invoice");
	private By enterLC = By.id("lc_number_invoice");
	private By getInvoiceNo = By.id("invoice_number");
	private By clickContractSave = By.id("save_deal_popup_contract_invoice_supplier");
	private By clickWillDoLater = By.xpath("//button[contains(text(),'I will do it later')]");
	
	private By clickFreight = By.id("modal_freight_tab_head");
	private By getOrgCountry = By.xpath("(//table[@id='basicTable']//td[6])[1]");
	private By getDestCountry = By.xpath("(//table[@id='basicTable']//td[8])[1]");
	private By getOrgPort = By.xpath("(//h6[contains(text(),'Port of Loading')]/following::h6)[1]");
	private By getDestPort = By.xpath("(//h6[contains(text(),'Port of Destination')]/following::h6)[1]");
			
	// Instruction
	private By clickInstruction = By.id("modal_instructuions_tab_head");
	private By clickSettings = By.id("deal_popup_instruction_seller_producer_head_tab");
	
	private By clickSellercompany = By.id("seller_company");
	private By selectSellerCompany = By.xpath("//select[@id='seller_company']");
	private By selectSellerCompany1 = By.xpath("//select[@id='seller_company']/option[@value='88']");
	
	private By selectProducerCompany = By.xpath("//select[@id='producer_company']");
	private By exporterConfirm = By.xpath("(//button[contains(text(),'Confirm')])[2]");
	private By getImporterTaxId = By.xpath("//input[@id='importerId']"); 
	private By getExporterID = By.xpath("//input[@id='exporterReg']");
	private By clearACID = By.id("aciRef");
	private By enterACID = By.xpath("//input[@id='aciRef']");
	private By saveACID = By.id("save_cargox_reference_no");
	
	private By clickCAD = By.id("deal_popup_instruction_cad_head_tab");
	private By selectBank = By.xpath("//select[@id='select-cad-bank']");
	private By getSupplierName = By.xpath("//label[contains(text(),'Handled by')]/following-sibling::input");
	private By getConsignee = By.xpath("//textarea[@name='consignee_name']");
	private By getDraweeBuyer = By.xpath("//label[contains(text(),'Drawee/Buyer')]/following-sibling::input");
	private By getReceiverAddrs = By.xpath("//label[contains(text(),'Receiver, address')]/following-sibling::input");
	private By getAmount = By.xpath("//label[contains(text(),'Currency code, amount')]/following-sibling::input");
	private By getCollectBank = By.xpath("//label[contains(text(),'Collecting Bank')]/following-sibling::input");
	private By getCompany = By.xpath("//label[contains(text(),'Company/Organization')]/following-sibling::input");
	private By getSupplierAddrs = By.xpath("//label[contains(text(),'Address (Street, P.O. Box etc.)')]/following-sibling::input");
	private By getSupplierZipcode = By.xpath("//label[contains(text(),'Address (Zip-code and City)')]/following-sibling::input");
	private By getNameCapital = By.xpath("//label[contains(text(),'Name in block capitals')]/following-sibling::input");
	private By getTelephone = By.xpath("//label[contains(text(),'Telephone')]/following-sibling::input");
	private By getPrincipalRef = By.xpath("//label[starts-with(text(),'Principal’s reference')]");
	private By getDraweesBank = By.xpath("//label[starts-with(text(),'Drawee’s bank')]");
	private By getContactPerson = By.xpath("//label[starts-with(text(),'Contact person')]");
	
	private By enterHandelsBankDraft = By.xpath("//input[@name='draft']");
	private By enterHandelsBankAcceptence = By.xpath("//input[@name='acceptance']");
	private By enterHandelsBankCustomInvoice = By.xpath("//input[@name='consulate_invoice']");
	private By enterHandelsBankBillLading = By.xpath("//input[@name='bill_of_lading']");
	private By clickHandelsBankPayement = By.xpath("//input[@value='document_to_be_delivered_against_x_payment']");
	private By clickHandelsBankCollectionDueDate = By.xpath("//input[@value='collected_on_due_date']");
	private By clickHandelsBankProtest = By.xpath("//input[@value='in_case_of_non_payment_or_non_acceptance_protest']");
	private By enterHandelsBankCreditAmount = By.xpath("//input[@name='proceeds_to_be_credited_account_no']");
	private By enterHandelsBankDebitedAccount = By.xpath("//input[@name='charges_to_be_debited_account_no']");
	private By clickHandelsBankAgentAutho = By.xpath("//input[@value='agent_authorization_advisory']");
	private By enterHandelsBankAgentCurrencyAmount = By.xpath("//input[@name='please_pay_out_of_the_proceeds_to_the_agent_currency_amount']");
	private By enterHandelsBankCentralBankrefNo = By.xpath("//input[@name='central_bank_ref_no']");
	private By clickHandelsBankSaveBtn  = By.id("savedata_cad_handelsbanken_sweden");
	private By clickHandelsBankSaveOKBtn  = By.xpath("//div[contains(text(),'Successfully saved')]/following::button");
	
	
	
	
	
	
	private By clickLC = By.id("deal_popup_instruction_lc_head_tab");
	
	private By clickInsurance = By.id("deal_popup_instruction_insurance_head_tab");
	
	private By clickProdDescription = By.id("deal_popup_instruction_productDescripition_head_tab");
	private By clearProdDescBox = By.xpath("//textarea[@name='tpd_details']");
	private By enterProdDescBox = By.name("tpd_details");
	private By clickProdDescSave = By.id("save_product_description");
	private By clickProdSuccessMsg = By.xpath("//div[contains(text(),'Product description saved successfully')]/following::button");
	
	private By clickPackList = By.id("deal_popup_instruction_packing_head_tab");
	private By getInstructionLC = By.name("lc_no");
	private By getInstructionPO = By.name("po_no");
	private By getInstructionBL = By.name("bl_no");
	private By getInstructionBLDate = By.name("bl_date");
	private By getInstructionACID = By.name("acid_number");
	private By getInstructionOriginCountry = By.name("origin_country");
	private By getInstructionOriginPort = By.name("origin_port");
	private By getInstructionDestinationCountry = By.name("destination_country");
	private By getInstructionDestinationPort = By.name("destination_port");
	private By getInstructionImpVAT = By.name("importer_vat_number");
	private By getInstructionQtyPack = By.name("total_bundles");
	private By getInstructionGrWeight = By.name("total_gross_weight");
	private By getInstructionNeWeight = By.name("total_net_weight");
	private By getInstructionTotalM3 = By.xpath("(//b[contains(text(),'Total m3')]/following::div)[1]");
	
	private By clickEUR1 = By.id("deal_popup_instruction_eur_head_tab");
	private By getEUR1ExporterAddrs = By.xpath("//textarea[@name='exporter']");
	private By getEUR1ExporterID = By.xpath("//textarea[@name='exporter registration']");
	private By getEUR1Consignee = By.xpath("//textarea[@name='consignee']");
	private By getEUR1ImporterID = By.xpath("//textarea[@name='importer tax']");
	private By getEUR1TradeOrgCountry = By.xpath("//b[starts-with(text(),'2.')]/following::input[@name='sawmill_country']");
	private By getEUR1TradeDestCountry = By.xpath("//b[starts-with(text(),'2.')]/following::input[@name='customer_country']");
	private By getEUR1ProdOrgCountry = By.xpath("//p[starts-with(text(),'.Country')]/following::textarea[@name='sawmill_country']");
	private By getEUR1ProdDestCountry = By.xpath("//p[starts-with(text(),'.Country')]/following::textarea[@name='customer_country']");
	private By getEUR1Transport = By.name("transport_details");
	private By getEUR1GrossWeight = By.name("gross_weight");
	private By getEUR1ACID = By.name("aci reference");
	
	private By clickCO = By.id("deal_popup_instruction_co_head_tab");
	private By getCOExporterAddrs = By.xpath("//div[@id='co_preview']//textarea[@name='exporter']");
	private By getCOExporterID = By.xpath("//div[@id='co_preview']//textarea[@name='exporter registration']");
	private By getCOConsignee = By.xpath("//div[@id='co_preview']//textarea[@name='consignee']");
	private By getCOImporterID = By.xpath("//div[@id='co_preview']//textarea[@name='importer tax']");
	private By getCOOrgCountry = By.xpath("//p[starts-with(text(),'5.')]/following::textarea[@name='country_of_origin']");
	private By getCONoOfPack = By.xpath("//p[starts-with(text(),'5.')]/following::textarea[@name='packages']");
	private By getCOGrossWeight = By.xpath("//p[starts-with(text(),'5.')]/following::textarea[@name='gross_weight']");
	private By getCOACID = By.xpath("//p[starts-with(text(),'5.')]/following::textarea[@name='aci reference']");
	private By getCODestCountry = By.xpath("//p[starts-with(text(),'5.')]/following::textarea[@name='sawmill_country']");
	private By getCOCityName = By.xpath("//p[starts-with(text(),'5.')]/following::textarea[@name='sawmill_city']");
	
	private By clickECO = By.id("deal_popup_instruction_tradecert_head_tab");
	private By getECOOrgCountry = By.xpath("//input[@name='details_origin']");
	private By getECODestCountry = By.xpath("//input[@name='details_destination']");
	private By getECOQty = By.xpath("//input[@name='details_quantity']");
	private By getECOAmount = By.xpath("//input[@name='details_amount']");
	private By getECOInvoice = By.xpath("//input[@name='invoice_number_type_text']");
	private By getECOConsignor = By.xpath("//div[@class='consignor-table']//input[@class='form-control']");
	private By getECOConsignee = By.xpath("//div[@class='consignee-table']//input[@class='form-control']");
	private By getECOGrossWeight = By.xpath("//input[@id='goods_gross']");
	private By getECONetWeight = By.xpath("//input[@id='goods_net']");
	private By getECOCube = By.xpath("//input[@id='goods_cube']");
	private By getECOM3 = By.xpath("//input[@id='goods_qty']");
	private By getECOFName  = By.xpath("(//b[contains(text(),'First Name')]/following::td)[1]");
	private By getECOLName  = By.xpath("(//b[contains(text(),'Last Name')]/following::td)[1]");
	private By getECOEmail  = By.xpath("(//b[contains(text(),'Email')]/following::td)[1]");
	
	private By clickEEUR1 = By.id("deal_popup_instruction_eeur1_head_tab");
	private By getEEUR1OrgCountry = By.xpath("//input[@name='details_origin_eeur']");
	private By getEEUR1DestCountry = By.xpath("//input[@name='details_destination_eeur']");
	private By getEEUR1Qty = By.xpath("//input[@name='details_quantity_eeur']");
	private By getEEUR1Amount = By.xpath("//input[@name='details_amount_eeur']");
	private By getEEUR1Invoice = By.xpath("//input[@name='invoice_number_eeur']");
	private By getEEUR1Consignor = By.xpath("//div[@id='deal_popup_instruction_eeur1']//div[@class='consignor-table']//input[@class='form-control']");
	private By getEEUR1Consignee = By.xpath("//div[@id='deal_popup_instruction_eeur1']//div[@class='consignee-table']//input[@class='form-control']");
	private By getEEUR1GrossWeight = By.xpath("//input[@id='goods_gross_eeur']");
	private By getEEUR1NetWeight = By.xpath("//input[@id='goods_net_eeur']");
	private By getEEUR1Cube = By.xpath("//input[@id='goods_cube_eeur']");
	private By getEEUR1M3 = By.xpath("//input[@id='goods_qty_eeur']");
	private By getEEUR1FName  = By.xpath("(//input[@id=\"goods_qty_eeur\"]//following::b[contains(text(),'First Name')]/following::td)[1]");
	private By getEEUR1LName  = By.xpath("(//input[@id=\"goods_qty_eeur\"]//following::b[contains(text(),'Last Name')]/following::td)[1]");
	private By getEEUR1Email  = By.xpath("(//input[@id='goods_qty_eeur']//following::b[contains(text(),'Email')]/following::td)[1]");
	
	private By clickPhyto = By.id("deal_popup_instruction_phyto_head_tab");
	private By getPhytoSellerDetails = By.xpath("//textarea[@name='seller']");
	private By getPhytoExporterId = By.xpath("//textarea[@name='exporter registration']");
	private By getPhytoImporterDetails = By.xpath("//textarea[@name='Receiver']");
	private By getPhytoImporterId = By.xpath("//textarea[@name='importer tax']");
	private By getPhytoPortDestination = By.xpath("//textarea[@name='port_of_destination']");
	private By getPhytoQty = By.xpath("//textarea[@name='Quantity']");
	private By getPhytoACID = By.xpath("//textarea[@name='aci reference']");
	private By getPhytoOrgPort = By.xpath("//textarea[@name='place_of_issue']");
	
	private By clickBL = By.id("deal_popup_instruction_bl_head_tab");
	private By getBLSupplierDetails = By.id("bl_sawmill");
	private By getBLExporterID = By.id("bl_exporter_registration_number");
	private By getBLConsigneeDetails = By.name("bl_consignee");
	private By getBLImporterID = By.id("bl_importer_tax_id");
	private By getBLNotifyParty = By.name("bl_notify");
	private By getBLPoL = By.name("bl_port_of_loading");
	private By getBLACID = By.id("bl_aci_reference");
	private By getBLPoD = By.name("bl_port_of_discharge");
	private By getBLQty = By.name("bl_quantity_packages");
	private By getBLGrWeight = By.name("bl_gross_weight");
	private By getBLNetWeight = By.name("bl_net_weight");
	
	private By clickPackingList = By.id("modal_packing_list_tab_head");
	private By enterPackingUserName = By.xpath("//div[@class='form-group form-group-default required']/input[@name='username']");
	private By enterPackingPassword = By.xpath("//div[@class='form-group form-group-default required']/input[@name='pass']");
	private By clickPackingSignIn = By.id("packing_list_login_btn");
	private By clickPackingUploadData = By.id("pkl_modal_upload_package_head");
	private By packingUploadFile = By.xpath("//input[@name='file']");
	private By clickPackingSubmit = By.id("packing_list_upload_xls_btn");
	private By clicPackingOK = By.xpath("//button[@class='swal-button swal-button--confirm']");
	private By clicPackingOK1 = By.xpath("//div[contains(text(),'Records have been updated successfully.')]/following::button[@class=\"swal-button swal-button--confirm\"]");
	private By clickPackingList2 = By.id("pkl_modal_package_tab_head");
	private By clickPackingList21 = By.xpath("//a[@id='pkl_modal_package_tab_head']");
	
	
	private By packingList2HeaderText = By.xpath("//h4[contains(text(),'Total Length Summary')]");
	private By clickPackingPagination = By.name("pkg_lst_datatable_length");
	private By packingSelectDropDown = By.xpath("//select[@name='pkg_lst_datatable_length']");
	private By packingAllCheck = By.name("action_chk");
	private By packingCheck = By.xpath("//td[@class='select-checkbox']");
	private By packingDeleteAll = By.xpath("//a[@title='Remove All Records']");
	private By clickPackingDeleteOk = By.xpath("//div[contains(text(),'Once deleted, you will not be able to recover this record!')]/following::button[contains(text(),'OK')]");
	private By getPackListDetails = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr[1]/td");
	private By getPackListNumber = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[2]");
	private By getPackListSpecies = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[3]");
	private By getPackListGrade = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[4]");
	private By getPackListThick = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[5]");
	private By getPackListWidth = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[6]");
	private By getPackListTotalQty = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[8]");
	private By getPackListTotalVolume = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[9]");
	private By getPackListTotalValue = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[10]");
	private By getPackListContainer = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[11]");
	private By getPackListTruck = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[12]");
	private By getPackListWagon = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[13]");
	private By getPackListBL = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[14]");
	private By getPackListRFID = By.xpath("//table[@id='pkg_lst_datatable']/tbody/tr/td[15]");
	private By checkBox = By.xpath("//div[@id='pkg_lst_datatable_info']");
	private By clickPackListLength = By.xpath("(//h6[@class='btn btn-link btn-block m-0 head_tit'])[1]");
	private By getPackListTableLength = By.xpath("((//table[@class='table table-bordered'])[2]//th)[2]");
	private By getPackListTableQty = By.xpath("((//table[@class='table table-bordered'])[2]//th[2])[2]");
	private By getPackListFirstThick = By.xpath("((//td[@class=\"select-checkbox sorting_1\"])[1]/following-sibling::td)[4]");
	private By getPackListFirstWidth = By.xpath("((//td[@class=\"select-checkbox sorting_1\"])[1]/following-sibling::td)[5]");
	private By getPackListTableVolume = By.xpath("((//table[@class='table table-bordered'])[2]//th[2])[3]");
	private By getPackListTableDataQty = By.xpath("//th[contains(text(),'1.8')]/following::td[1]");
	private By getPackListTableDataPercentage = By.xpath("//th[contains(text(),'1.8')]/following::tr[2]/td[1]");
	
	//Network > My profile
	private By clickNetwork = By.xpath("//span[contains(text(),'Network')]");
	private By clickMyProfile = By.xpath("//a[contains(text(),'My Profile')]");
	private By getMyProfileName = By.xpath("(//label[contains(text(),'Name')]/following::div)[1]");
	
	//Settings//
	private By getMyProfileBankName = By.xpath("(//label[starts-with(text(),'Bank Name')]/following::div)[1]");
	private By getMyProfileBankAddrs = By.xpath("(//label[starts-with(text(),'Bank Address')]/following::div)[1]");
	private By getMyProfileEmail = By.xpath("(//label[contains(text(),'Email')]/following::div)[1]");
	
	
	
	// Settings > Manage company
	private By navigateSettings = By.xpath("//span[contains(text(),'Settings')]");
	private By clickManageCompany = By.xpath("//a[contains(text(),'Manage Company')]");
	private By clickLocation = By.xpath("(//a[contains(text(),'Location')])[1]");
	private By getACIDExpNumber = By.xpath("(//td[contains(text(),'Gavle City')]/following-sibling::td)[4]");
	private By clickCompanyProfile = By.xpath("//a[contains(text(),'Company Profile')]");
	private By getPublicProfileLegalName = By.xpath("(//label[starts-with(text(),'Legal Company')]/following::div)[1]");
	private By getPublicProfileOfficeAddrs = By.xpath("(//label[contains(text(),'Office Address')]/following::div)[1]");
	private By getPublicProfileCity = By.xpath("(//label[contains(text(),'City')]/following::div)[1]");
	private By getPublicProfileZip = By.xpath("(//label[contains(text(),'Postcode/Zipcode')]/following::div)[1]");
	
	private By clickLocationAction = By.xpath("//table[@id='company_datatable']//td[contains(text(),'[HQ]')]//following::a[@id='dropdownMenuLink']"); 
	private By clickLocationActionEdit = By.xpath("//table[@id='company_datatable']//td[contains(text(),'[HQ]')]//following::a[contains(text(),'Edit')]");
	private By clickLocationBankName = By.xpath("//input[@name='tcld_bank']");
	private By clickLocationBankAddress = By.xpath("//textarea[@name='tcld_bank_address']");
	private By clickLocationBankIBANNumber = By.xpath("//input[@name='tcld_iban']");
	private By clickLocationBankSwiftCode = By.xpath("//input[@name='tcld_swift']");
	private By clickLocationEditCancelBtn = By.xpath("//a[contains(text(),'Cancel')]");
	private By clickLocationGavleCity = By.xpath("//td[contains(text(),'Gavle City')]");
	private By clickLocationGavleCityCountry = By.xpath("//td[contains(text(),'Gavle City')]/parent::tr/td");
	private By clickLocationGavleCityAddress = By.xpath("//td[contains(text(),'Gavle City')]/parent::tr/td[3]");
	
	
	//Importer 
	private By clickImports = By.id("menu_walkthrough_2");
	private By importerNavigateNegotiation = By.id("menu_walkthrough_2_2");
	private By activeDeals = By.id("menu_walkthrough_2_1");
	private By importerSettings = By.xpath("//span[contains(text(),'Settings')]"); 
	private By importerManageCompany = By.xpath("//a[contains(text(),'Manage Company')]");
	private By importerPublicProfile = By.xpath("//a[contains(text(),'Company Profile')]");
	private By getImporterVAT = By.xpath("(//label[contains(text(),'Business ID/VAT')]//following::div)[1]");
	private By getImporterConsigneeAddrs = By.xpath("(//label[contains(text(),'Consignee')]/following::div)[1]");
	private By getImporterNotify = By.xpath("(//label[contains(text(),'Notify')]/following::div)[1]");
	
	private By clickImporterBankingInfo = By.xpath("//a[contains(text(),'Banking Info')]");
	private By getImporterBankName = By.xpath("(//label[contains(text(),'Bank Name')]/following::div)[1]");
	private By getImporterBankAddrs = By.xpath("(//label[contains(text(),'Bank Address')]/following::div)[1]");
	private By getImporterLegalCompanyName = By.xpath("(//label[contains(text(),'Legal Company Name')]/following::div[@class='defaultCompanyProfileSection'])[1]");
	private By getImporterOfficeAddress = By.xpath("(//label[contains(text(),'Office Address')]/following::div[@class='defaultCompanyProfileSection'])[1]");
	
	
	
	
	//2.Constructor
	public NegotiationsAddOfferPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
	
	// Properties file read script
		public Properties input_Prop() {
				inputProp = new Properties();
				try {
					FileInputStream ip = new FileInputStream("./src/test/resources/testdata/negotiationsAddOfferPage.properties");
					inputProp.load(ip);
					} catch (FileNotFoundException e) {
					e.printStackTrace();
				}  catch (IOException e) {
					e.printStackTrace();
				}
				
				return inputProp;
			}
			
			// Properties file write script
		public Properties output_Prop(String text, String value) throws IOException {
				
				FileInputStream ip = new FileInputStream("./src/test/resources/testdata/negotiationsAddOfferPage.properties");
				props.load(ip);
				ip.close();
				
				outputProp = new Properties();
				FileOutputStream outputStream = null;
				try {
					outputStream = new FileOutputStream("./src/test/resources/testdata/negotiationsAddOfferPage.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				props.setProperty(text, value);
				try {
					props.store(outputStream, null);
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 return outputProp;
			}	
	
	//3. Page Actions
	
	public void navigateToNegotiationsPage() {
		try {
		elementUtil.doVisiblityOfElementLocated(endTourButton, 8).click();
		}
		catch(Exception e) {
			System.out.println("No pop up message");
		}
		elementUtil.doVisiblityOfElementLocated(clickExport, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickNegotiations, 8).click();
	}
	
	public void navigateAddOfferPage() {
		elementUtil.doVisiblityOfElementLocated(addOfferButton, 7).click();
	}
	
	public void buyerDetails() {
		//elementUtil.doPresenceOfElementLocated(clickCustomer, 8).click();
		elementUtil.waitDoSelectByVisibleText(selectCustomer,"Kimberly Smith (Test Demo Importer 10)", 5);
	}
	
	public void deliveryDetails() {
		elementUtil.doVisiblityOfElementLocated(clickDestination, 8).click();
		elementUtil.doSelectByVisibleText(selectDestination, "Egypt");
		elementUtil.waitDoSelectByVisibleText(selectIncoterms, "CFR (Incoterms 2010)",8);
		elementUtil.doVisiblityOfElementLocated(clickContainer, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickFreightYes, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickAddFreight, 8).click();
		
	}
	
	public void productDetails()throws InterruptedException {
		//JavascriptExecutor js = (JavascriptExecutor) driver;  ///////// This is for Testing Server//////////
		//js.executeScript("window.scrollBy(0,500)");  ///////// This is for Testing Server//////////
		elementUtil.sendKeys(spec, "A Test Offer");
		elementUtil.doVisiblityOfElementLocated(validUntil, 6).clear();
		elementUtil.waitForSendKeys(validUntil,8, "27/08/2022");   //If it is changed then need to change schedule elements also
		elementUtil.waitDoSelectByVisibleText(selectOrigin, "Sweden", 8);
		elementUtil.waitDoSelectByVisibleText(selectLocation,"Gavle City", 6);
		Thread.sleep(6000);
		//elementUtil.doSelectByVisibleText(clickCheckboxLocation, "sawmill supply Exporter ltd");
		
		//elementUtil.doSelectFromDropDown(clickCheckboxLocation, "sawmill supply Exporter ltd");
		Thread.sleep(6000);
		elementUtil.waitDoSelectByVisibleText(selectCurrency, "USD", 8);
		//elementUtil.doPresenceOfElementLocated(ClickGoodsReady, 7).click();
		elementUtil.waitDoSelectByVisibleText(goodsReady, "Late", 8); //If it is changed then need to change schedule elements also
		Thread.sleep(6000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		elementUtil.waitDoSelectByVisibleText(goodsReadyMonth, "June", 8); //If it is changed then need to change schedule elements also
		
	}
	
	public void product1() {
		elementUtil.waitDoSelectByVisibleText(selectOfferGrade, "VI",8);
		elementUtil.waitDoSelectByVisibleText(selectOfferSpecies, "Eucalyptus",8);
		elementUtil.sendKeys(thickness, "20");
		elementUtil.sendKeys(width, "10");
		elementUtil.sendKeys(length, "100");
		elementUtil.sendKeys(m3, "300");
		elementUtil.sendKeys(price, "200");
	}
	
	public void clickNegotiate() throws InterruptedException {
		elementUtil.doVisiblityOfElementLocated(negotiate, 8).click();
	}
	
	public String supplierConfirmAddOfferFromNegotiations() throws InterruptedException {
		Thread.sleep(5000);
		String spec = elementUtil.waitDoGetText(negoSpec, 8).toString().trim();
		String volume = elementUtil.waitDoGetText(negoVolume, 8).toString().trim();
		String value = elementUtil.waitDoGetText(negoValue, 8).toString().trim();
		
		String confirmAddOfferFromNego = (spec+volume+value);
		
		return confirmAddOfferFromNego;
		
	}
	
	public String importerConfirmAddOfferFromNegotiations() throws InterruptedException, IOException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickImport, 10).click();
		elementUtil.doVisiblityOfElementLocated(clickNegotiations, 10).click();
		Thread.sleep(5000);
		String impSpec = elementUtil.doGetText(importerSpec).toString().trim();
		String impVolume = elementUtil.doGetText(importerVolume).toString().trim();
		String impValue = elementUtil.doGetText(importerValue).toString().trim();
		
		String getDealNumber = elementUtil.doGetText(getDealID).toString().trim();
		
		output_Prop("AddOfferDealNumber",getDealNumber);
		output_Prop("AddOfferDealValue",impValue);
		output_Prop("AddOfferDealVolume",impVolume);
		
		String impConfirmAddOffer = (impSpec+impVolume+impValue);
		
		return impConfirmAddOffer;
	}
	
	public void navigateToSpecification() throws  InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickToOpenDeal, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		
		
	}
	
	public String negotiationsSpecificationAddProduct() throws InterruptedException {
		Thread.sleep(3000);	
		//need to use waitDoSelectElementsTextList
		elementUtil.doVisiblityOfElementLocated(addProd, 8).click();
		elementUtil.waitDoSelectByVisibleText(selectGrade, "OS+V",8);
		WebElement scrollCert = elementUtil.doVisiblityOfElementLocated(certificateContract, 9);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",scrollCert );
		elementUtil.waitDoSelectByVisibleText(selectSpecies, "Eucalyptus", 8);
		elementUtil.waitDoSelectByVisibleText(selectMoisture, "KD 18% +-2", 8);
		elementUtil.doVisiblityOfElementLocated(enterThickness, 8).sendKeys("20");
		elementUtil.doVisiblityOfElementLocated(enterWidthProd, 8).sendKeys("10");
		elementUtil.doVisiblityOfElementLocated(enterLength, 8).sendKeys("100");		
		elementUtil.doVisiblityOfElementLocated(enterVolume, 8).sendKeys("200");
		elementUtil.doVisiblityOfElementLocated(enterGWeight, 8).sendKeys("200");
		elementUtil.doVisiblityOfElementLocated(enterNWeight, 8).sendKeys("100");
		elementUtil.doVisiblityOfElementLocated(enterPrice, 8).sendKeys("50");
	
		elementUtil.doVisiblityOfElementLocated(clickSpecSave, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickOk, 15).click();
		String saveMessage = elementUtil.doVisiblityOfElementLocated(saveSuccessMsg, 13).getText();
		
		return saveMessage;
		
	}
	
	public String getLastRowDataFromSpecification() throws InterruptedException {
		
		Thread.sleep(15000);
		String getTxt = elementUtil.getAttributeForValues(getLastRowData, "value");
		
		return getTxt;
	}
	

	
	
	public String negotiationsDocumentsAddDocuments() throws InterruptedException, AWTException {
		elementUtil.doVisiblityOfElementLocated(clickDocuments, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickAddDoc, 8).click();
		//elementUtil.doVisiblityOfElementLocated(uploadFile, 8).click();
		
		WebElement uploadElement = elementUtil.doVisiblityOfElementLocated(uploadFile, 8);
		uploadElement.sendKeys("C:/Users/IND/Desktop/SeleniumTEAutomation/src/test/resources/testdata/uploadCertificate.pdf");
		/*
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", uploadElement);
		
		Thread.sleep(5000);
		Robot robot = new Robot();
		robot.mouseMove(930, 150);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(2000);
		
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		robot.mouseMove(230, 150);
		Thread.sleep(5000);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(5000);
		robot.mouseMove(870, 580);
		Thread.sleep(5000);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(5000);
		*/
		elementUtil.doVisiblityOfElementLocated(clickUpload, 8).click();
		
		Thread.sleep(15000);
		
		elementUtil.doVisiblityOfElementLocated(clickDocOk, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickAllDocTab, 8).click();
		
		String uploadMsgText = elementUtil.doGetText(virusScanText).toString().trim();
		
		return uploadMsgText;
	}
	
	
	
	public void negotiationsSchedule() throws InterruptedException, IOException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickSchedule, 8).click();
		
		elementUtil.doVisiblityOfElementLocated(clickScheduleEdit, 8).click();
		String getDeadLIneAcceptTxt = elementUtil.waitDoGetText(getScheduleDeadLineAccept, 8);
		String getGoodsReadyEstimateTxt = elementUtil.waitDoGetText(getScheduleGoodsEstimate, 8);
		
		elementUtil.waitForSendKeys(enterScheduleGoodsMill, 8, "2022/12/23");
		elementUtil.waitForSendKeys(enterScheduleDeliveryPort, 8, "2022/12/25");
		elementUtil.waitForSendKeys(enterScheduleETS, 8, "2022/12/27");
		elementUtil.doVisiblityOfElementLocated(getScheduleSave, 8).click();
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(getScheduleSaveOk, 8).click();
		
		String getScheduleGoodsMillTxt = elementUtil.waitDoGetText(getScheduleGoodsMill, 8);
		String getScheduleDeliveryPortTxt = elementUtil.waitDoGetText(getScheduleDeliveryPort, 8);
		String getScheduleETSTxt = elementUtil.waitDoGetText(getScheduleETS, 8);
		
		String getSystemDates = getDeadLIneAcceptTxt+getGoodsReadyEstimateTxt+getScheduleGoodsMillTxt+getScheduleDeliveryPortTxt
				+getScheduleETSTxt;
		
		elementUtil.doVisiblityOfElementLocated(getScheduleAddDate, 8).click();
		elementUtil.waitForSendKeys(getScheduleAddDateEventName, 8, "Test1");
		elementUtil.waitForSendKeys(getScheduleAddDateEventDate, 8, "09/12/2022");
		elementUtil.doVisiblityOfElementLocated(getScheduleAddDateEventName, 8).click();
		elementUtil.doVisiblityOfElementLocated(getScheduleAddDateAddCalendarYes, 8).click();
		elementUtil.doVisiblityOfElementLocated(getScheduleAddDateImporter, 8).click();
		elementUtil.doVisiblityOfElementLocated(getScheduleAddDateForwarder, 8).click();
		elementUtil.doVisiblityOfElementLocated(getScheduleAddDateInternalCompany, 8).click();
		elementUtil.doVisiblityOfElementLocated(getScheduleAddDateSave, 8).click();
		elementUtil.doVisiblityOfElementLocated(getScheduleAddDateSaveOk, 8).click();
		List<String> getAddDateDetailTxt = elementUtil.arrayListMethod(getScheduleAddDateData, 8);
		
		
		 
		output_Prop("ScheduleDetails",(getSystemDates.concat(getAddDateDetailTxt.toString())));
	
		
	}
	
	
	public void moveToActiveDeals() throws InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickActionMenu, 8).click();
		elementUtil.doVisiblityOfElementLocated(moveToActiveDeals, 13).click();
		
	}
	
	public String activeDealsSpecificationAddProduct() throws InterruptedException {
		Thread.sleep(4000);
		elementUtil.doVisiblityOfElementLocated(clickExport, 8).click();
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickActiveDeals, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickToOpenDealActiveDeals, 15).click();
		elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		
		
		elementUtil.doVisiblityOfElementLocated(addProd, 8).click();
		elementUtil.waitDoSelectByVisibleText(selectGrade, "OS+V",8);
		WebElement scrollCert = elementUtil.doVisiblityOfElementLocated(certificateContract, 9);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",scrollCert );
		elementUtil.waitDoSelectByVisibleText(selectSpecies, "Eucalyptus", 8);
		elementUtil.waitDoSelectByVisibleText(selectMoisture, "KD 18% +-2", 8);
		elementUtil.doVisiblityOfElementLocated(enterThickness, 8).sendKeys("40");
		elementUtil.doVisiblityOfElementLocated(enterWidthProd, 8).sendKeys("20");
		elementUtil.doVisiblityOfElementLocated(enterLength, 8).sendKeys("200");		
		elementUtil.doVisiblityOfElementLocated(enterVolume, 8).sendKeys("300");
		elementUtil.doVisiblityOfElementLocated(enterGWeight, 8).sendKeys("300");
		elementUtil.doVisiblityOfElementLocated(enterNWeight, 8).sendKeys("200");
		elementUtil.doVisiblityOfElementLocated(enterPrice, 8).sendKeys("100");
	
		elementUtil.doVisiblityOfElementLocated(clickSpecSave, 8).click();;
		String saveMessage = elementUtil.waitDoGetText(saveSuccessMsg, 13);
		elementUtil.doVisiblityOfElementLocated(clickOk, 8).click();
		return saveMessage;
	}
	
	public String activeDealsSpecificationLoadedVolume() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickLoadVolume, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickEdit, 8).click();
		
		elementUtil.doVisiblityOfElementLocated(enterQtyPack, 8).clear();
		elementUtil.waitForSendKeys(enterQtyPack, 8, "20");
		
		elementUtil.doVisiblityOfElementLocated(enterGrossWeight, 8).clear();
		elementUtil.waitForSendKeys(enterGrossWeight, 8, "110");
		
		elementUtil.doVisiblityOfElementLocated(enterNetWeight, 8).clear();
		elementUtil.waitForSendKeys(enterNetWeight, 8, "100");
	
		
		
		
		elementUtil.doVisiblityOfElementLocated(clickLoadSave, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickOk, 8).click();
		Thread.sleep(4000);
		
		String getQtyPack = elementUtil.waitDoGetText(getImportQty, 8);
		String getGrossWeight = elementUtil.waitDoGetText(getImportGrossWeight, 8);
		String getNetWeight = elementUtil.waitDoGetText(getImportNetWeight, 8);
		
		String LoadVolumeText = getQtyPack+getGrossWeight+getNetWeight;
		
		output_Prop("LoadVolumeDetails",LoadVolumeText);
		
		return LoadVolumeText;
	}
	
	
	
	public void activeDealsInstructionSettings() throws IOException, InterruptedException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickSettings, 8).click();
		
		Thread.sleep(15000);
		
		String getImpTaxID = elementUtil.WaitGetAttributeForValue(getImporterTaxId, "value", 8).toString();
		elementUtil.doVisiblityOfElementLocated(clearACID, 8).clear();
		elementUtil.waitForSendKeys(enterACID,8, "1234567890123456789");
		elementUtil.doVisiblityOfElementLocated(saveACID, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickDocOk, 8).click();
		String getACID = elementUtil.WaitGetAttributeForValue(enterACID, "value", 8).toString();
		
			
		String  getExpID = elementUtil.WaitGetAttributeForValue(getExporterID, "value", 8).toString();
		
		output_Prop("ImporterTaxIDNumber",getImpTaxID);
		output_Prop("ExporterIdentificationNumber",getExpID);
		output_Prop("ACID",getACID);
		
	}
	
	public String getExporterIDFromManageCompany() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(5000);
		
		elementUtil.doVisiblityOfElementLocated(navigateSettings, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickLocation, 8).click();
		
		String getACIDExp = elementUtil.waitDoGetText(getACIDExpNumber, 8).toString();
		
		return getACIDExp;
	}
	
	public void activeDealsInstructionCADHandelsbankenBank() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickCAD, 8).click();
		elementUtil.waitDoSelectByVisibleText(selectBank, "Handelsbanken, Sweden", 8);
		String handleBy = elementUtil.WaitGetAttributeForValue(getSupplierName, "value", 8).toString().trim();
		String getConsigneeAddrs = elementUtil.doGetText(getConsignee);
		String getBuyerDetails = elementUtil.waitGetAttributeForValues(getDraweeBuyer, "value", 8).toString().trim();
		String getReceiverDetails = elementUtil.waitGetAttributeForValues(getReceiverAddrs, "value", 8).toString().trim();
		String getAmountDetails = elementUtil.WaitGetAttributeForValue(getAmount, "value", 8).toString().trim();
		String getCollectBankDetails = elementUtil.waitGetAttributeForValues(getCollectBank, "value", 8).toString().trim();
		String getCompanyLocationName = elementUtil.WaitGetAttributeForValue(getCompany, "value", 8).toString().trim();
		String getSupplierAddrsDetails = elementUtil.WaitGetAttributeForValue(getSupplierAddrs, "value", 8).toString().trim();
		String getSupplierAddrsZipDetails = elementUtil.WaitGetAttributeForValue(getSupplierZipcode, "value", 8).toString().trim();
		String getName = elementUtil.WaitGetAttributeForValue(getNameCapital, "value", 8).toLowerCase().trim();
		String getTelephoneNumber = elementUtil.WaitGetAttributeForValue(getTelephone, "value", 8).toString().trim();
		
		output_Prop("HandleBy",handleBy);
		output_Prop("ConsigneeAddrs",getConsigneeAddrs);
		output_Prop("BuyerDetails",getBuyerDetails);
		output_Prop("ReceiverDetails",getReceiverDetails);
		output_Prop("AmountDetails",getAmountDetails);
		output_Prop("CollectBankDetails",getCollectBankDetails);
		output_Prop("CompanyLocationName",getCompanyLocationName);
		output_Prop("SupplierAddrsDetails",getSupplierAddrsDetails);
		output_Prop("SupplierAddrsZipDetails",getSupplierAddrsZipDetails);
		output_Prop("Name",getName);
		output_Prop("TelephoneNumber",getTelephoneNumber);
		
	}
	
	public void enterDataCADHandelsbankenBankFromSupplier() throws IOException, InterruptedException {
		
		elementUtil.waitForSendKeys(enterHandelsBankDraft, 8, "1");
		elementUtil.waitForSendKeys(enterHandelsBankAcceptence, 8, "2");
		elementUtil.waitForSendKeys(enterHandelsBankCustomInvoice, 8, "2");
		elementUtil.waitForSendKeys(enterHandelsBankBillLading, 8, "2");
		elementUtil.doVisiblityOfElementLocated(clickHandelsBankPayement, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickHandelsBankCollectionDueDate, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickHandelsBankProtest, 8).click();
		elementUtil.waitForSendKeys(enterHandelsBankCreditAmount, 8, "2000");
		elementUtil.waitForSendKeys(enterHandelsBankDebitedAccount, 8, "11223344");
		elementUtil.doVisiblityOfElementLocated(clickHandelsBankAgentAutho, 8).click();
		elementUtil.waitForSendKeys(enterHandelsBankAgentCurrencyAmount, 8, "$1000");
		elementUtil.waitForSendKeys(enterHandelsBankCentralBankrefNo, 8, "A13046");
		elementUtil.doVisiblityOfElementLocated(clickHandelsBankSaveBtn, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickHandelsBankSaveOKBtn, 8).click();
		
		
	}
	
	public String getEnterDataCADHandelsbankenBankFromSupplier() throws IOException, InterruptedException {
		
		String getEnterHandelsBankDraftTxt = elementUtil.WaitGetAttributeForValue(enterHandelsBankDraft, "value",8);
		String getenterHandelsBankAcceptenceTxt = elementUtil.WaitGetAttributeForValue(enterHandelsBankAcceptence, "value",8);
		String getenterHandelsBankCustomInvoiceTxt = elementUtil.WaitGetAttributeForValue(enterHandelsBankCustomInvoice, "value",8);
		String getenterHandelsBankBillLadingTxt = elementUtil.WaitGetAttributeForValue(enterHandelsBankBillLading, "value",8);
		
		boolean wb = elementUtil.doVisiblityOfElementLocated(clickHandelsBankPayement, 8).isSelected();
		boolean wb1 = elementUtil.doVisiblityOfElementLocated(clickHandelsBankCollectionDueDate, 8).isSelected();
		boolean wb2 = elementUtil.doVisiblityOfElementLocated(clickHandelsBankProtest, 8).isSelected();
		String getenterHandelsBankCreditAmountTxt = elementUtil.WaitGetAttributeForValue(enterHandelsBankCreditAmount, "value",8);
		String getenterHandelsBankDebitedAccountTxt = elementUtil.WaitGetAttributeForValue(enterHandelsBankDebitedAccount, "value",8);
		boolean wb3 = elementUtil.doVisiblityOfElementLocated(clickHandelsBankAgentAutho, 8).isSelected();
		String getenterHandelsBankAgentCurrencyAmountTxt = elementUtil.WaitGetAttributeForValue(enterHandelsBankAgentCurrencyAmount, "value",8);
		String getenterHandelsBankCentralBankrefNoTxt = elementUtil.WaitGetAttributeForValue(enterHandelsBankCentralBankrefNo, "value",8);
		
		String getEnterDataHandelsBankDetails = getEnterHandelsBankDraftTxt+getenterHandelsBankAcceptenceTxt+getenterHandelsBankCustomInvoiceTxt
				+getenterHandelsBankBillLadingTxt+wb+wb1+wb2+getenterHandelsBankCreditAmountTxt+getenterHandelsBankDebitedAccountTxt
				+wb3+getenterHandelsBankAgentCurrencyAmountTxt+getenterHandelsBankCentralBankrefNoTxt; 
		
		return getEnterDataHandelsBankDetails;
			
	}
	
	
	public void supplierBankDetails() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickLocationAction,8).click();
		elementUtil.doVisiblityOfElementLocated(clickLocationActionEdit,8).click();
		String getSupplierBankName = elementUtil.waitDoGetText(clickLocationBankName, 8);
		String getSupplierBankAddress = elementUtil.waitDoGetText(clickLocationBankAddress, 8);
		String getSupplierBankIBAN = elementUtil.waitDoGetText(clickLocationBankIBANNumber, 8);
		String getSupplierBankSwift = elementUtil.waitDoGetText(clickLocationBankSwiftCode, 8);
		elementUtil.doVisiblityOfElementLocated(clickLocationEditCancelBtn, 8).click();
		
		output_Prop("SupplierBankName", getSupplierBankName);
		output_Prop("SupplierBankAddress", getSupplierBankAddress);
		output_Prop("SupplierBankIBAN", getSupplierBankIBAN);
		output_Prop("SupplierBankSwift", getSupplierBankSwift);
		
	}
	
	public void validateCADHandelsbankenBankFromSupplierMyProfile() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickNetwork,8).click();
		elementUtil.doVisiblityOfElementLocated(clickMyProfile, 8).click();
		String getMyProfileNameText = elementUtil.waitDoGetText(getMyProfileName, 15);
		elementUtil.doVisiblityOfElementLocated(clickNetwork,8).click();
		Thread.sleep(5000);
		elementUtil.doVisiblityOfElementLocated(navigateSettings, 15).click();
		Thread.sleep(5000);
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 15).click();
		Thread.sleep(4000);
		elementUtil.doVisiblityOfElementLocated(clickLocation, 8).click();
		supplierBankDetails();
		String getSupplierBankNameText = input_Prop().getProperty("SupplierBankName");
		String getSupplierBankAddrsText = input_Prop().getProperty("SupplierBankAddress");
		
		String getSupplierBankDetailsText =  getSupplierBankNameText+getSupplierBankAddrsText;
		
		output_Prop("SupplierName",getMyProfileNameText);
		output_Prop("SupplierBankDetails",getSupplierBankDetailsText);
		
		
	}
	
	public void validateCADHandelsbankenBankFromSupplierCompanyProfile() throws IOException, InterruptedException {
		Thread.sleep(4000);
		elementUtil.doVisiblityOfElementLocated(clickCompanyProfile, 8).click();
		
		String getPublicProfileOfficeAddrsText =  elementUtil.waitDoGetText(getPublicProfileOfficeAddrs, 15).trim();
		String getPublicProfileOfficeCityText  = elementUtil.waitDoGetText(getPublicProfileCity, 8).trim();
		String  getPublicProfileOfficeZipText = elementUtil.waitDoGetText(getPublicProfileZip, 8).trim();
		
		String getPublicProfileCityZipDetailsText = getPublicProfileOfficeCityText+", "+getPublicProfileOfficeZipText;
		
		output_Prop("PublicProfileOfficeAddress",getPublicProfileOfficeAddrsText);
		output_Prop("PublicProfileCityZipDetails",getPublicProfileCityZipDetailsText);
		
		
	}
	
	
	
	public void activeDealsInstructionCADNordeaBank() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickCAD, 8).click();
		elementUtil.waitDoSelectByVisibleText(selectBank, "Nordea, Sweden", 8);
		String getPrincipalRefText =  elementUtil.waitDoGetText(getPrincipalRef, 8).trim();
		String getDraweesBankText= elementUtil.waitDoGetText(getDraweesBank, 8).trim();
		String getContactPersonText = elementUtil.waitDoGetText(getContactPerson, 8).trim();
		
		
		output_Prop("PrincipalReference",getPrincipalRefText);
		output_Prop("DraweesBankAddres",getDraweesBankText);
		output_Prop("ContactPerson",getContactPersonText);
	}
	
	
	public void activeDealsInstructionInsurance() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickLC, 8).click();
	
	}
	
	public String activeDealsInstructionProductDescription() throws IOException, InterruptedException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickProdDescription, 8).click();
		elementUtil.doVisiblityOfElementLocated(clearProdDescBox, 8).clear();
		elementUtil.waitForSendKeys(enterProdDescBox, 8, "Test1234");
		elementUtil.doVisiblityOfElementLocated(clickProdDescSave, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickProdSuccessMsg, 8).click();
		Thread.sleep(5000);
		elementUtil.doVisiblityOfElementLocated(clickSettings, 8).click();
		
		elementUtil.doVisiblityOfElementLocated(clickProdDescription, 8).click();
		Thread.sleep(3000);
		String getText = elementUtil.waitDoGetText(clearProdDescBox, 8).toString().trim();
		
		return getText;
	}
	
	public void activeDealsContractLCBLValidatePackList() throws IOException {
		
		elementUtil.doVisiblityOfElementLocated(clickContract, 8).click();
		elementUtil.doVisiblityOfElementLocated(enterBL, 8).clear();
		elementUtil.waitForSendKeys(enterBL, 8, "BL1231");
		
		try {	
		elementUtil.doVisiblityOfElementLocated(enterLC, 8).clear();
		elementUtil.waitForSendKeys(enterLC, 8, "LC1231");
		}catch(Exception e){
			System.out.println("No LC present");
		}
		elementUtil.doVisiblityOfElementLocated(clickContractSave, 8).click();
		try {
		elementUtil.doVisiblityOfElementLocated(clickWillDoLater, 8).click();
		}catch(Exception e1) {
			System.out.println("Payment message pop up isn't appear");
		}
		elementUtil.doVisiblityOfElementLocated(clickOk, 8).click();
		
		String getContractBL = elementUtil.WaitGetAttributeForValue(enterBL, "value", 8).toString().trim();
		try {
		String getContractLC = elementUtil.WaitGetAttributeForValue(enterLC,"value", 8).toString().trim();
		output_Prop("ContractLC",getContractLC);
		}catch(Exception e2) {
			System.out.println("LC isn't displayed");
		}
		String getContractInvoiceNo = elementUtil.WaitGetAttributeForValue(getInvoiceNo,"value", 8).toString().trim();
		
		
		output_Prop("ContractBL",getContractBL);
		
		output_Prop("ContractInvoiceNo",getContractInvoiceNo);
		
	}
	
	
	
	public void activeDealsFreightCountryAndPortValidatePackList() throws IOException {
	
		elementUtil.doVisiblityOfElementLocated(clickFreight, 8).click();
		String getOrgCountryText = elementUtil.waitDoGetText(getOrgCountry, 8);
		String getOrgPortText = elementUtil.waitDoGetText(getOrgPort, 8);
		String getDestCountryText = elementUtil.waitDoGetText(getDestCountry, 8);
		String getDestPortText = elementUtil.waitDoGetText(getDestPort, 8);
	
		output_Prop("OrginalCountry",getOrgCountryText);
		output_Prop("OrginalPort",getOrgPortText);
		output_Prop("DestinationCountry",getDestCountryText);
		output_Prop("DestinationPort",getDestPortText);
		
		
	}
	
	
	
	public String activeDealsInstructionPackListWeightDemo() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickPackList, 8).click();
		
		String getInstLC = elementUtil.WaitGetAttributeForValue(getInstructionLC, "value", 8).toString().trim();
		String getInstPO = elementUtil.WaitGetAttributeForValue(getInstructionPO, "value", 8).toString().trim();
		String getInstBL = elementUtil.WaitGetAttributeForValue(getInstructionBL, "value", 8).toString().trim();
		//String getInstBLDate = elementUtil.WaitGetAttributeForValue(getInstructionBLDate, "value", 8).toString().trim();
		String getInstACID = elementUtil.WaitGetAttributeForValue(getInstructionACID, "value", 8).toString().trim();
		String getInstOrgCountry = elementUtil.WaitGetAttributeForValue(getInstructionOriginCountry, "value", 8).toString().trim();
		String getInstOrgPort = elementUtil.WaitGetAttributeForValue(getInstructionOriginPort, "value", 8).toString().trim();
		String getInstDestCountry = elementUtil.WaitGetAttributeForValue(getInstructionDestinationCountry, "value", 8).toString().trim();
		String getInstDestPort = elementUtil.WaitGetAttributeForValue(getInstructionDestinationPort, "value", 8).toString().trim();
		String getInstImpVAT = elementUtil.WaitGetAttributeForValue(getInstructionImpVAT, "value", 8).toString().trim();
		String getInstQtyPack = elementUtil.WaitGetAttributeForValue(getInstructionQtyPack, "value", 8).toString().trim();
		String getInstGrWeight = elementUtil.WaitGetAttributeForValue(getInstructionGrWeight, "value", 8).toString().trim();
		String getInstNeWeight = elementUtil.WaitGetAttributeForValue(getInstructionNeWeight, "value", 8).toString().trim();
		//String getInstTotalM3 = elementUtil.WaitGetAttributeForValue(getInstructionTotalM3, "value", 8).toString().trim();
		
		String getInstPackListWeightDemoDetails = (getInstLC+getInstPO+getInstBL+getInstACID+getInstOrgCountry+getInstOrgPort+
				getInstDestCountry+getInstDestPort+getInstImpVAT+getInstQtyPack+getInstGrWeight+getInstNeWeight);
		
		return getInstPackListWeightDemoDetails;
	}
	
	
	public void activeDealsInstructionEUR1() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickEUR1, 8).click();
		String getEUR1ExporterAddrsText = elementUtil.waitDoGetText(getEUR1ExporterAddrs, 8);
		String getEUR1ExporterIDText = elementUtil.waitDoGetText(getEUR1ExporterID, 8).replace("Exporter Identification Number - ","");
		String getEUR1ConsigneeText = elementUtil.waitDoGetText(getEUR1Consignee, 8);
		String getEUR1ImporterIDText = elementUtil.waitDoGetText(getEUR1ImporterID, 8).replace("Importer Tax ID - ","");
		String getEUR1TradeOrgCountryText = elementUtil.WaitGetAttributeForValue(getEUR1TradeOrgCountry, "value", 8);
		String getEUR1TradeDestCountryText = elementUtil.WaitGetAttributeForValue(getEUR1TradeDestCountry, "value", 8);
		String getEUR1ProdOrgCountryText = elementUtil.waitDoGetText(getEUR1ProdOrgCountry, 8);
		String getEUR1ProdDestCountryText = elementUtil.waitDoGetText(getEUR1ProdDestCountry, 8);
		String getEUR1TransportText = elementUtil.waitDoGetText(getEUR1Transport, 8);
		String getEUR1GrossWeightText = elementUtil.waitDoGetText(getEUR1GrossWeight, 8);
		String getEUR1ACIDText = elementUtil.waitDoGetText(getEUR1ACID, 8);
		
		output_Prop("EUR1ExporterAddress",getEUR1ExporterAddrsText);
		output_Prop("EUR1ExporterID",getEUR1ExporterIDText);
		output_Prop("EUR1Consignee",getEUR1ConsigneeText);
		output_Prop("EUR1ImporterID",getEUR1ImporterIDText);
		output_Prop("EUR1TradeOrginCountry",getEUR1TradeOrgCountryText);
		output_Prop("EUR1TradeDestinationCountry",getEUR1TradeDestCountryText);
		output_Prop("EUR1ProductOrginCountry",getEUR1ProdOrgCountryText);
		output_Prop("EUR1ProductDestinationCountry",getEUR1ProdDestCountryText);
		output_Prop("EUR1Transport",getEUR1TransportText);
		output_Prop("EUR1GrossWeight",getEUR1GrossWeightText);
		output_Prop("EUR1ACID",getEUR1ACIDText);
		
	}
	
	
	
	public void validateInstructionEUR1FromSupplier() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickSettings, 8).click();
		String getImpTaxID = elementUtil.WaitGetAttributeForValue(getImporterTaxId, "value", 8).toString();
		String getACID = elementUtil.WaitGetAttributeForValue(enterACID, "value", 8).toString();
		String  getExpID = elementUtil.WaitGetAttributeForValue(getExporterID, "value", 8).toString();
		String getOrgCountryText = elementUtil.waitDoGetText(getOrgCountry, 8);
		String getDestCountryText = elementUtil.waitDoGetText(getDestCountry, 8);
		
		
		output_Prop("ImporterTaxIDNumber ",getImpTaxID);
		output_Prop("ExporterIdentificationNumber ",getExpID);
		output_Prop("ACID ",getACID);
		output_Prop("OrginalCountry",getOrgCountryText);
		output_Prop("DestinationCountry",getDestCountryText);
		
		elementUtil.doVisiblityOfElementLocated(clickContract, 8).click();;
		String getContractBL = elementUtil.WaitGetAttributeForValue(enterBL,"value", 8);
		
		output_Prop("ContractBL",getContractBL);
		
		elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickLoadVolume, 8).click();		
		String getGrossWeightText = elementUtil.waitDoGetText(getGrossWeight, 8).replace("kg","");
		
		output_Prop("GrossWeight",getGrossWeightText);
		
		elementUtil.doVisiblityOfElementLocated(clickActiveCloseBtn, 8).click();
		elementUtil.doVisiblityOfElementLocated(navigateSettings, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickCompanyProfile, 8).click();
		
		String getLegalCompanyName = elementUtil.waitDoGetText(getPublicProfileLegalName, 8);
		String getOfficeAddress = elementUtil.waitDoGetText(getPublicProfileOfficeAddrs, 8);
		
		output_Prop("PublicProfileOfficeAddress",getOfficeAddress);
		output_Prop("PublicProfiletLegalCompanyName",getLegalCompanyName);
	
		
	}
	
	
	public void activeDealsInstructionCO() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickCO, 8).click();
		String getCOExporterAddrsText = elementUtil.waitDoGetText(getCOExporterAddrs, 8).replace("\n", "").replace("\r", "").trim();
		String getCOExporterIDText = elementUtil.waitDoGetText(getCOExporterID, 8).replace("Exporter Identification Number - ","").trim();
		String getCOConsigneeText = elementUtil.waitDoGetText(getCOConsignee, 8).trim();
		String getCOImporterIDText = elementUtil.waitDoGetText(getCOImporterID, 8).replace("Importer Tax ID - ","").trim();
		String getCOOrgCountryText = elementUtil.waitDoGetText(getCOOrgCountry, 8).trim();
		String getCONoOfPackText = elementUtil.waitDoGetText(getCONoOfPack, 8).trim();
		String getCOGrossWeightText = elementUtil.waitDoGetText(getCOGrossWeight, 8).trim();
		String getCOACIDText = elementUtil.waitDoGetText(getCOACID, 8).replace("ACID number - ", "").trim();
		String getCODestCountryText = elementUtil.waitDoGetText(getCODestCountry, 8).trim();
		String getCOCityNameText = elementUtil.waitDoGetText(getCOCityName, 8).trim();
		
			
		String getCODetails = (getCOExporterAddrsText+getCOExporterIDText+getCOImporterIDText+
				getCOOrgCountryText+getCONoOfPackText+getCOGrossWeightText+getCOACIDText+getCODestCountryText+getCOCityNameText+getCOConsigneeText);
		
		output_Prop("CODetails",getCODetails);
	}
	
	
	public void validateInstructionCOFromSupplier() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickSettings, 8).click();
		String getImpTaxID = elementUtil.WaitGetAttributeForValue(getImporterTaxId, "value", 8).toString().trim();
		String getACID = elementUtil.WaitGetAttributeForValue(enterACID, "value", 8).toString().trim();
		String  getExpID = elementUtil.WaitGetAttributeForValue(getExporterID, "value", 8).toString().trim();
		String getOrgCountryText = elementUtil.waitDoGetText(getOrgCountry, 8).trim();
		String getDestCountryText = elementUtil.waitDoGetText(getDestCountry, 8).trim();
		
				
		elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickLoadVolume, 8).click();		
		String getGrossWeightText = elementUtil.waitDoGetText(getGrossWeight, 8).replace("kg","").trim();
		String getNoOfPackText = elementUtil.waitDoGetText(getNoOfPack, 8).trim();
		
		
		
		elementUtil.doVisiblityOfElementLocated(clickActiveCloseBtn, 8).click();
		elementUtil.doVisiblityOfElementLocated(navigateSettings, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickCompanyProfile, 8).click();
		
		String getLegalCompanyName = elementUtil.waitDoGetText(getPublicProfileLegalName, 8).trim();
		String getOfficeAddress = elementUtil.waitDoGetText(getPublicProfileOfficeAddrs, 8).trim();
		String getCity = elementUtil.waitDoGetText(getPublicProfileCity, 8).trim();
		
		
		
		
		
		String validateCODetailsFromSupplier = (getLegalCompanyName+getOfficeAddress+getCity+"Sweden"+getExpID+getImpTaxID+getOrgCountryText+getNoOfPackText+
				getGrossWeightText+getACID+getDestCountryText+getCity);
		
		output_Prop("ValidateCODetailsFromSupplier",validateCODetailsFromSupplier);
	}
	
	
	
	public void activeDealsInstructionECO() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickECO, 8).click();
		String getOrgCountry = elementUtil.WaitGetAttributeForValue(getECOOrgCountry, "value", 8);
		String getDestCountry = elementUtil.WaitGetAttributeForValue(getECODestCountry, "value", 8);
		String getQty = elementUtil.WaitGetAttributeForValue(getECOQty, "value", 8);
		String getAmount = elementUtil.WaitGetAttributeForValue(getECOAmount, "value", 8);
	    String getConsignor = elementUtil.waitGetAttributeForValues(getECOConsignor, "value", 8).replace("SE", "").replace(",","");

	    String getConsignee = elementUtil.waitGetAttributeForValues(getECOConsignee, "value", 8).replace(",","");
		String getGrossWeight = elementUtil.WaitGetAttributeForValue(getECOGrossWeight, "value", 8).replace(".000","");
		String getNetWeight = elementUtil.WaitGetAttributeForValue(getECONetWeight, "value", 8).replace(".000","");
		String getCube = elementUtil.WaitGetAttributeForValue(getECOCube, "value", 8).replace(".000","");
		String getM3 = elementUtil.WaitGetAttributeForValue(getECOM3, "value", 8).replace(".000","");
		String getFName = elementUtil.waitDoGetText(getECOFName, 8);
		String getLName = elementUtil.waitDoGetText(getECOLName, 8);
		String getFullName = getFName+" "+getLName;
		String getEmail = elementUtil.waitDoGetText(getECOEmail, 8).toLowerCase();
		
		
		String getECODetails = (getOrgCountry+getDestCountry+getQty+getAmount+getConsignor+
				getGrossWeight+getNetWeight+getCube+getM3+getFullName+getEmail+getConsignee);
		
		output_Prop("ECODetails",getECODetails);
	}
	
	
	
	public void validateInstructionECOFromSupplier() throws IOException, InterruptedException {
		String getOrgCountryText = elementUtil.waitDoGetText(getOrgCountry, 8).trim();
		String getDestCountryText = elementUtil.waitDoGetText(getDestCountry, 8).trim();
		
		elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		String getTotalVolumeText = elementUtil.waitDoGetText(getTotalVolume, 8).trim();
		String getTotalValueText = elementUtil.waitDoGetText(getTotalValue, 8).trim();
		elementUtil.doVisiblityOfElementLocated(clickLoadVolume, 8).click();		
		String getGrossWeightText = elementUtil.waitDoGetText(getGrossWeight, 8).replace("kg","").trim();
		String getNetWeightText = elementUtil.waitDoGetText(getNetWeight, 8).replace("kg", "").trim();
		String getNoOfPackText = elementUtil.waitDoGetText(getNoOfPack, 8).trim();
		
		elementUtil.doVisiblityOfElementLocated(clickActiveCloseBtn, 8).click();
		elementUtil.doVisiblityOfElementLocated(navigateSettings, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickCompanyProfile, 8).click();
		String getLegalCompanyNameText = elementUtil.waitDoGetText(getPublicProfileLegalName, 8).trim();
		String getOfficeAddressText = elementUtil.waitDoGetText(getPublicProfileOfficeAddrs, 8).trim();
		String getCityText = elementUtil.waitDoGetText(getPublicProfileCity, 8).trim();
		String getZipCodeText = elementUtil.waitDoGetText(getPublicProfileZip, 8).trim();
		
		String getConsignorText = (getLegalCompanyNameText+getOfficeAddressText+getCityText+getOrgCountryText+getZipCodeText);
		
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickNetwork, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickMyProfile, 8).click();
		String getNameText = elementUtil.waitDoGetText(getMyProfileName, 8).trim();
		Thread.sleep(2000);
		String getEmailText = elementUtil.waitDoGetText(getMyProfileEmail, 8).trim();
		
				
		
		String validateECODetailsFromSupplier = (getOrgCountryText+getDestCountryText+getTotalVolumeText+
				getTotalValueText+getConsignorText+getGrossWeightText+getNetWeightText+getTotalVolumeText.replace(",000","")+
				getTotalVolumeText.replace(",000","")+getNameText+getEmailText);
		
		output_Prop("ValidateECODetailsFromSupplier",validateECODetailsFromSupplier);
	
	}
	
	
	public void activeDealsInstructionEEUR1() throws IOException, InterruptedException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickEEUR1, 8).click();
		
		String getOrgCountry = elementUtil.WaitGetAttributeForValue(getEEUR1OrgCountry, "value", 8);
		String getDestCountry = elementUtil.WaitGetAttributeForValue(getEEUR1DestCountry, "value", 8);
		String getQty = elementUtil.WaitGetAttributeForValue(getEEUR1Qty, "value", 8);
		String getAmount = elementUtil.WaitGetAttributeForValue(getEEUR1Amount, "value", 8);
	    String getConsignor = elementUtil.waitGetAttributeForValues(getEEUR1Consignor, "value", 8).replace("SE", "").replace(",","");

	    String getConsignee = elementUtil.waitGetAttributeForValues(getEEUR1Consignee, "value", 8).replace(",","");
		String getGrossWeight = elementUtil.WaitGetAttributeForValue(getEEUR1GrossWeight, "value", 8).replace(".000","");
		String getNetWeight = elementUtil.WaitGetAttributeForValue(getEEUR1NetWeight, "value", 8).replace(".000","");
		String getCube = elementUtil.WaitGetAttributeForValue(getEEUR1Cube, "value", 8).replace(".000","");
		String getM3 = elementUtil.WaitGetAttributeForValue(getEEUR1M3, "value", 8).replace(".000","");
		String getFName = elementUtil.waitDoGetText(getEEUR1FName, 8);
		String getLName = elementUtil.waitDoGetText(getEEUR1LName, 8);
		String getFullName = getFName+" "+getLName;
		Thread.sleep(3000);
		String getEmail = elementUtil.waitDoGetText(getEEUR1Email, 8).toLowerCase();
		
		
		String getEEUR1Details = (getOrgCountry+getDestCountry+getQty+getAmount+getConsignor+
				getGrossWeight+getNetWeight+getCube+getM3+getFullName+getEmail+getConsignee);
		
		output_Prop("EEUR1Details",getEEUR1Details);
	}
	
	public void validateInstructionEEUR1FromSupplier() throws IOException, InterruptedException {
		String getOrgCountryText = elementUtil.waitDoGetText(getOrgCountry, 8).trim();
		String getDestCountryText = elementUtil.waitDoGetText(getDestCountry, 8).trim();
		
		elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		String getTotalVolumeText = elementUtil.waitDoGetText(getTotalVolume, 8).trim();
		String getTotalValueText = elementUtil.waitDoGetText(getTotalValue, 8).trim();
		elementUtil.doVisiblityOfElementLocated(clickLoadVolume, 8).click();		
		String getGrossWeightText = elementUtil.waitDoGetText(getGrossWeight, 8).replace("kg","").trim();
		String getNetWeightText = elementUtil.waitDoGetText(getNetWeight, 8).replace("kg", "").trim();
		String getNoOfPackText = elementUtil.waitDoGetText(getNoOfPack, 8).trim();
		
		elementUtil.doVisiblityOfElementLocated(clickActiveCloseBtn, 8).click();
		elementUtil.doVisiblityOfElementLocated(navigateSettings, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickCompanyProfile, 8).click();
		String getLegalCompanyNameText = elementUtil.waitDoGetText(getPublicProfileLegalName, 8).trim();
		String getOfficeAddressText = elementUtil.waitDoGetText(getPublicProfileOfficeAddrs, 8).trim();
		String getCityText = elementUtil.waitDoGetText(getPublicProfileCity, 8).trim();
		String getZipCodeText = elementUtil.waitDoGetText(getPublicProfileZip, 8).trim();
		
		String getConsignorText = (getLegalCompanyNameText+getOfficeAddressText+getCityText+getOrgCountryText+getZipCodeText);
		
		elementUtil.doVisiblityOfElementLocated(clickNetwork, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickMyProfile, 8).click();
		String getNameText = elementUtil.waitDoGetText(getMyProfileName, 8).trim();
		Thread.sleep(3000);
		String getEmailText = elementUtil.waitDoGetText(getMyProfileEmail, 8).trim();
		
				
		
		String validateEEUR1DetailsFromSupplier = (getOrgCountryText+getDestCountryText+getTotalVolumeText+
				getTotalValueText+getConsignorText+getGrossWeightText+getNetWeightText+getTotalVolumeText.replace(",000", "")+
				getTotalVolumeText.replace(",000", "")+getNameText+getEmailText);
		
		output_Prop("ValidateEEUR1DetailsFromSupplier",validateEEUR1DetailsFromSupplier);
	}
	
	
		
	
	public void activeDealsInstructionPhyto() throws IOException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickPhyto, 8).click();
		String getSellerDetails = elementUtil.waitDoGetText(getPhytoSellerDetails, 8).replaceAll("\\r|\\n","").trim();
		String getExporterId = elementUtil.waitDoGetText(getPhytoExporterId, 8).replace("Exporter Identification Number - ","");
		String getImporterDetails = elementUtil.waitDoGetText(getPhytoImporterDetails, 8).replaceAll("\\r|\\n","").trim();
		String getImporterId = elementUtil.waitDoGetText(getPhytoImporterId, 8).replace("Importer Tax ID - ","");		
		String getPortDestination = elementUtil.waitDoGetText(getPhytoPortDestination, 8);
		String getQty = elementUtil.waitDoGetText(getPhytoQty, 8).replace("m3","");
		String getACID = elementUtil.waitDoGetText(getPhytoACID, 8).replace("ACID number - ","");
		String getPlaceOfIssue = elementUtil.waitDoGetText(getPhytoOrgPort, 8);
		
		String getPhytoDetails = (getSellerDetails+getExporterId+getImporterId+getPortDestination+
				getQty+getACID+getPlaceOfIssue+getImporterDetails);
		
		output_Prop("PhytoDetails",getPhytoDetails);
	}
	
	 public void validateInstructionPhytoFromSupplier() throws IOException {
		 elementUtil.doVisiblityOfElementLocated(clickSettings, 8).click();
		 String getImpTaxID = elementUtil.WaitGetAttributeForValue(getImporterTaxId, "value", 8).toString().trim();
		 String getACID = elementUtil.WaitGetAttributeForValue(enterACID, "value", 8).toString().trim();
		 String getExpID = elementUtil.WaitGetAttributeForValue(getExporterID, "value", 8).toString().trim();
		 
		 
		 elementUtil.doVisiblityOfElementLocated(clickPackList, 8).click();
		 String getInstDestCountry = elementUtil.WaitGetAttributeForValue(getInstructionDestinationCountry, "value", 8).toString().trim();
		 String getInstDestPort = elementUtil.WaitGetAttributeForValue(getInstructionDestinationPort, "value", 8).toString().trim();
		 
		 elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		 String getTotalVolumeText = elementUtil.waitDoGetText(getTotalVolume, 8).trim();
		 
		 elementUtil.doVisiblityOfElementLocated(clickActiveCloseBtn, 8).click();
		 elementUtil.doVisiblityOfElementLocated(navigateSettings, 8).click();
		 elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		 elementUtil.doVisiblityOfElementLocated(clickCompanyProfile, 8).click();
		 String getLegalCompanyNameText = elementUtil.waitDoGetText(getPublicProfileLegalName, 8).trim();
		 String getOfficeAddressText = elementUtil.waitDoGetText(getPublicProfileOfficeAddrs, 8).replace("\r","\t").trim();
		 String getCityText = elementUtil.waitDoGetText(getPublicProfileCity, 8).trim();
		 
		 String getSellerDetailsText = getLegalCompanyNameText+getOfficeAddressText;
		 
		 String validatePhytoDetailsFromSupplier = (getSellerDetailsText+getCityText+getExpID+getImpTaxID+getInstDestPort+","+getInstDestCountry+
				 getTotalVolumeText+getACID+getCityText);
		 
		 output_Prop("ValidatePhytoDetailsFromSupplier",validatePhytoDetailsFromSupplier);
	}
	
	
	 
	 public void activeDealsInstructionBL() throws IOException {
		 elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		 elementUtil.doVisiblityOfElementLocated(clickBL, 8).click();
		 String getSupplierDetails = elementUtil.waitDoGetText(getBLSupplierDetails, 8).replaceAll("\\r|\\n","").trim();
		 String getExporterID = elementUtil.waitDoGetText(getBLExporterID, 8);
		 String getConsigneeDetails = elementUtil.waitDoGetText(getBLConsigneeDetails, 8).replaceAll("\\r|\\n","").trim();
		 String getImporterID = elementUtil.waitDoGetText(getBLImporterID, 8);
		 String getNotifyParty = elementUtil.waitDoGetText(getBLNotifyParty, 8).replaceAll("\\r|\\n","").trim();
		 String getPoL = elementUtil.waitDoGetText(getBLPoL, 8);
		 String getACID = elementUtil.waitDoGetText(getBLACID, 8);
		 String getPoD = elementUtil.waitDoGetText(getBLPoD, 8);
		 String getQty = elementUtil.waitDoGetText(getBLQty, 8);
		 String getGrWeight = elementUtil.waitDoGetText(getBLGrWeight, 8);
		 String getNetWeight = elementUtil.waitDoGetText(getBLNetWeight, 8);
		 
		 String getBLDetails = (getSupplierDetails+getExporterID+getImporterID+
				 getPoL+getACID+getPoD+getQty+getGrWeight+getNetWeight+getConsigneeDetails+getNotifyParty);
		 
		 output_Prop("BLDetails",getBLDetails);
		 
		 
	 }
	 
	 
	 public void validateInstructionBLFromSupplier() throws IOException, InterruptedException {
		 elementUtil.doVisiblityOfElementLocated(clickSettings, 8).click();
		 String getImpTaxIDText = elementUtil.WaitGetAttributeForValue(getImporterTaxId, "value", 8).toString().trim();
		 String getACIDText = elementUtil.WaitGetAttributeForValue(enterACID, "value", 8).toString().trim();
		 String getExpIDText = elementUtil.WaitGetAttributeForValue(getExporterID, "value", 8).toString().trim();
		 
		 elementUtil.doVisiblityOfElementLocated(clickPackList, 8).click();
		 String getOriginPortText = elementUtil.WaitGetAttributeForValue(getInstructionOriginPort,"value", 8);
		 String getDestinationPortText = elementUtil.WaitGetAttributeForValue(getInstructionDestinationPort,"value", 8);
		 
		 
		 
		 elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		 elementUtil.doVisiblityOfElementLocated(clickLoadVolume, 8).click();		
		 String getGrossWeightText = elementUtil.waitDoGetText(getGrossWeight, 8).replace("kg","").trim();
		 String getNetWeightText = elementUtil.waitDoGetText(getNetWeight, 8).replace("kg", "").trim();
		 String getNoOfPackText = elementUtil.waitDoGetText(getNoOfPack, 8).trim();
		 
		 elementUtil.doVisiblityOfElementLocated(clickActiveCloseBtn, 8).click();
		 elementUtil.doVisiblityOfElementLocated(navigateSettings, 8).click();
		 elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		 Thread.sleep(3000);
		 elementUtil.doVisiblityOfElementLocated(clickLocation, 8).click();
		 
		
		 String getLocationNameText = elementUtil.waitDoGetText(clickLocationGavleCity, 8).trim();
		 String getLocationCountryText = elementUtil.waitDoGetText(clickLocationGavleCityCountry, 8).trim();
		 String getLocationAddressText = elementUtil.waitDoGetText(clickLocationGavleCityAddress, 8).replaceAll("\\r|\\n","").trim();
		 
		
			
		 String getSellerDetailsText = getLocationNameText+getLocationCountryText+getLocationAddressText;
		 
		 String validateBLDetailsFromSupplier = (getSellerDetailsText+getExpIDText+getImpTaxIDText+getOriginPortText+
				 getACIDText+getDestinationPortText+getNoOfPackText+getGrossWeightText+getNetWeightText);
		 
		 output_Prop("ValidateBLDetailsFromSupplier",validateBLDetailsFromSupplier);
		 
	 }
	 
	 
	 
	 
	 public void activeDealsPackingList() throws IOException, InterruptedException, InvalidFormatException {
		 elementUtil.doVisiblityOfElementLocated(clickPackingList, 8).click();
		 //elementUtil.waitForSendKeys(enterPackingUserName, 8, "j.arnol7708372");
		 //elementUtil.waitForSendKeys(enterPackingPassword, 8, "CSTimber#2020!");
		 //elementUtil.doVisiblityOfElementLocated(clickPackingSignIn, 8).click();
		 elementUtil.doVisiblityOfElementLocated(clickPackingList2, 8).click();
		 try{
			 String headerText = elementUtil.waitDoGetText(packingList2HeaderText, 8);
			 System.out.print(headerText);
			
			 if(headerText.isEmpty()==false) {
				elementUtil.doVisiblityOfElementLocated(clickPackingPagination, 8).click(); 
				elementUtil.doSelectByVisibleText(packingSelectDropDown, "100");
				elementUtil.doVisiblityOfElementLocated(packingAllCheck, 8).click();
				elementUtil.doVisiblityOfElementLocated(packingDeleteAll,8).click();
				elementUtil.doVisiblityOfElementLocated(clickPackingDeleteOk, 8).click();
				Thread.sleep(5000);
				elementUtil.doVisiblityOfElementLocated(clicPackingOK, 8).click();
			}else {
				System.out.println("Blanked....");
			}
		 }catch(Exception e) {
			 
			 System.out.println("Packing List blank");
			 
		 }
		
		
		 elementUtil.doVisiblityOfElementLocated(clickPackingUploadData, 8).click();
		 WebElement upload_file = elementUtil.doVisiblityOfElementLocated(packingUploadFile, 8);
		 upload_file.sendKeys("C:/Users/IND/Desktop/SeleniumTEAutomation/src/test/resources/testdata/sample.xlsx");
		 elementUtil.doVisiblityOfElementLocated(clickPackingSubmit, 8).click();
		 Thread.sleep(5000);
		 elementUtil.doVisiblityOfElementLocated(clicPackingOK1, 8).click();
		 Thread.sleep(5000);
		 elementUtil.doVisiblityOfElementLocated(clickPackingList21, 8).click();
		 
		 // click on Pagination number to select 100
		 Thread.sleep(8000);
		 elementUtil.doVisiblityOfElementLocated(clickPackingPagination, 8).click(); 
		 elementUtil.doSelectByVisibleText(packingSelectDropDown, "100");
		 elementUtil.doVisiblityOfElementLocated(packingAllCheck, 8).click();
		 
		 WebElement scrollCert = elementUtil.doVisiblityOfElementLocated(checkBox, 9);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView(true)",scrollCert );
		 Thread.sleep(8000);
		// elementUtil.waitWriteInExcelRow1(getPackListDetails,2, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListNumber,2, 8);
		 
		 
		 elementUtil.waitWriteInExcelRow1(getPackListSpecies,3, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListGrade,4, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListThick,5, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListWidth,6, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListTotalQty,7, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListTotalVolume,8, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListTotalValue,9, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListContainer,10, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListTruck,11, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListWagon,12, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListBL,13, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListRFID,14, 8);
		 

	}
	 
	 
	 public ArrayList<String> getPackIDDataFromUploadExcel() throws IOException, InterruptedException {
		String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
		
		ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 7);
		Collections.sort(x);
		System.out.println(x);
		
		System.out.print("After select array " + x);
		
		return x;
			
	 }
	 
	 /* 
	  * Use string buffer to convert from ArrayList to Strig
	  * 
	  * 
	  
	 public String getPackIDDataFromInputExcel1() throws IOException, InterruptedException {
		 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
		 ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 2);
			
			Collections.sort(x);
			StringBuffer sb = new StringBuffer();
			
			for (String s : x) {
		         sb.append(s);
		         sb.append(", ");
		      }
			String str = sb.toString();
			
			System.out.print("String buffer use :" + str);
			
			return str;
		 
	 }
	 
	 
	 */
	 
	 
	 public ArrayList<String> getPackIDDataFromInputExcel() throws IOException, InterruptedException {
		 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
			
		 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 2);
			
			Collections.sort(x);
			System.out.println(x);
			return x;
						
		 }
	 
	 public ArrayList<String> getSpeciesDataFromUploadExcel() throws IOException, InterruptedException {
			String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
			
			ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 5);
			Collections.sort(x);
			System.out.println(x);
			
			System.out.print("After select array " + x);
			
			return x;
				
		 }
		 
		 
		 public ArrayList<String> getSpeciesDataFromInputExcel() throws IOException, InterruptedException {
			 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
				
			 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 3);
				
				Collections.sort(x);
				System.out.println(x);
				return x;
							
			 }

		 public ArrayList<String> getGradeDataFromUploadExcel() throws IOException, InterruptedException {
				String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
				
				ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 6);
				Collections.sort(x);
				System.out.println(x);
				
				System.out.print("After select array " + x);
				
				return x;
					
			 }
			 
			 
			 public ArrayList<String> getGradeDataFromInputExcel() throws IOException, InterruptedException {
				 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
					
				 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 4);
					
					Collections.sort(x);
					System.out.println(x);
					return x;
								
				 }
			 
			 
			 public ArrayList<String> getThicknessDataFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 8);
					Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
				 
				 
				 public ArrayList<String> getThicknessDataFromInputExcel() throws IOException, InterruptedException {
					 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
						
					 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 5);
						
						Collections.sort(x);
						System.out.println(x);
						return x;
									
					 }
				 
			
			 public ArrayList<String> getWidthDataFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 9);
					Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
				 
				 
				 public ArrayList<String> getWidthDataFromInputExcel() throws IOException, InterruptedException {
					 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
						
					 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 6);
						
						Collections.sort(x);
						System.out.println(x);
						return x;
									
					 }
				 
			 public ArrayList<String> getContainerDataFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 0);
					Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
				 
				 
			 public ArrayList<String> getContainerDataFromInputExcel() throws IOException, InterruptedException {
					 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
						
					 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 10);
						
						Collections.sort(x);
						System.out.println(x);
						return x;
									
					 }
			 
			 
			 
			 public ArrayList<String> getTruckDataFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 1);
					Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
				 
				 
			 public ArrayList<String> getTruckDataFromInputExcel() throws IOException, InterruptedException {
					 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
						
					 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 11);
						
						Collections.sort(x);
						System.out.println(x);
						return x;
									
					 }
			 
			 
			 public ArrayList<String> getWagonDataFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 2);
					Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
				 
				 
			 public ArrayList<String> getWagonDataFromInputExcel() throws IOException, InterruptedException {
					 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
						
					 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 12);
						
						Collections.sort(x);
						System.out.println(x);
						return x;
									
					 }
			 
			 
			 
			 
			 
			 public ArrayList<String> getBLDataFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 3);
					Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
				 
				 
			 public ArrayList<String> getBLDataFromInputExcel() throws IOException, InterruptedException {
					 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
						
					 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 13);
						
						Collections.sort(x);
						System.out.println(x);
						return x;
									
					 }
			 
			 public ArrayList<String> getRFIDDataFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					ArrayList<String> x = elementUtil.readTheExcelRow(fileLoc, 4);
					Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
				 
				 
			 public ArrayList<String> getRFIDDataFromInputExcel() throws IOException, InterruptedException {
					 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
						
					 	ArrayList<String> x = elementUtil.readTheExcelRow(fileLo, 14);
						
						Collections.sort(x);
						System.out.println(x);
						return x;
									
					 }
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 public String getTotalQTYDataFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					String x = elementUtil.readTheExcelRowWithMultipleCoulmnTotalSum(fileLoc);
					//Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
			 
			 public String getTotalQTYDataFromInputExcel() throws IOException, InterruptedException {
				 String fileLo = ".\\src\\test\\resources\\testdata\\sample5.xlsx ";
					
				 	String getTotalVloume = elementUtil.readTheExcelMultipleRowWithACoulmnTotalSum(fileLo, 7);
					
					
					System.out.println("Total Volume : "+getTotalVloume);
					return getTotalVloume;
								
				 }
			 public String getPackListVolumeCalc() {
				 elementUtil.doVisiblityOfElementLocated(clickPackingList, 8).click();
				 //elementUtil.waitForSendKeys(enterPackingUserName, 8, "j.arnol7708372");
				// elementUtil.waitForSendKeys(enterPackingPassword, 8, "CSTimber#2020!");
				 //elementUtil.doVisiblityOfElementLocated(clickPackingSignIn, 8).click();
				 elementUtil.doVisiblityOfElementLocated(clickPackingList2, 8).click();
				 elementUtil.doVisiblityOfElementLocated(clickPackListLength, 8).click();
				 
				 String getLeghthTxt = elementUtil.waitDoGetText(getPackListTableLength, 9);
				 String getQtyTxt = elementUtil.waitDoGetText(getPackListTableQty, 9);
				 String getThickTxt = elementUtil.waitDoGetText(getPackListFirstThick, 9);
				 String getWidthTxt = elementUtil.waitDoGetText(getPackListFirstWidth, 9);
				 
				 
				 
				 double getL = Double.valueOf(getLeghthTxt);
				 double getQ = Double.valueOf(getQtyTxt);
				 double getT = Double.valueOf(getThickTxt);
				 double getW = Double.valueOf(getWidthTxt);
				 double perc = 0.001;
				 double getVolume = (getL*getQ)*getT*perc*getW*perc;
				 
				 df.setRoundingMode(RoundingMode.UP);
				 System.out.println(df.format(getVolume));
				 
				 String getVolumeText = df.format(getVolume);
				
				 return getVolumeText;
				 
			 }
			 public String getPackListVolume() {
				 String getVolumeTxt = elementUtil.waitDoGetText(getPackListTableVolume, 9);
				 //double getExpectVolume = Double.valueOf(getVolumeTxt);
				 //System.out.println(getExpectVolume);
				 
				 
				 return getVolumeTxt;
			 }
			 
			 
			 public String activeDealsPackingListTableData() throws IOException, InterruptedException, InvalidFormatException {
				 elementUtil.doVisiblityOfElementLocated(clickPackingList, 8).click();
				 //elementUtil.waitForSendKeys(enterPackingUserName, 8, "j.arnol7708372");
				 //elementUtil.waitForSendKeys(enterPackingPassword, 8, "CSTimber#2020!");
				 //elementUtil.doVisiblityOfElementLocated(clickPackingSignIn, 8).click();
				 elementUtil.doVisiblityOfElementLocated(clickPackingList2, 8).click();
				 
				 String getTableDataQuantity = elementUtil.waitDoGetText(getPackListTableDataQty, 8);
				 
				 String getPercentage = elementUtil.waitDoGetText(getPackListTableDataPercentage, 8);
				
				 
				 String getTableDataDetails = getTableDataQuantity+getPercentage;
				 
				 return getTableDataDetails;
				 
			 }
			 
			 //Get particular 1.8 length total Qty
			 public String getTableDataTotalQTYFromUploadExcel() throws IOException, InterruptedException {
					String fileLoc = ".\\src\\test\\resources\\testdata\\sample.xlsx ";
					
					String x = elementUtil.readTheExcelMultipleRowWithACoulmnTotalSum(fileLoc, 28);
					//Collections.sort(x);
					System.out.println(x);
					
					System.out.print("After select array " + x);
					
					return x;
						
				 }
			 
			 //
			 public String getTableDataPercentageFromUploadExcel() throws IOException, InterruptedException {
				 String getTotalQty = getTotalQTYDataFromInputExcel();
				 String getOneLengthQty =getTableDataTotalQTYFromUploadExcel() ;
				 
				 double getTotalQtyNumber = Double.parseDouble(getTotalQty);
				// System.out.println(df1.format("total v :" +getTotalQtyNumber));
				 double getOneLengthQtyNumber = Double.parseDouble(getOneLengthQty);
				 
				 
				 double getPercentage = getOneLengthQtyNumber/getTotalQtyNumber*100;
				 
				 df2.setRoundingMode(RoundingMode.UP);
				 System.out.println("get percentege = " +df2.format(getPercentage));
				 
				 String str = df2.format(getPercentage);
				 
				 String getDetails = getOneLengthQty+str;
				 return getDetails;
			 }

	/////////////////////////////////////Importer Negotiaitons//////
	
	public void importerNavigateDealPopup() throws InterruptedException{
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickImports, 7).click();
		elementUtil.doVisiblityOfElementLocated(importerNavigateNegotiation, 7).click();
		
		//navigates to the specification
		navigateToSpecification();
		
	}
	
	public String getLastRowDataFromImporterSpecification() throws InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickSpecification, 8).click();
		Thread.sleep(15000);
		String getTxt = elementUtil.getAttributeForValues(getLastRowData, "value");
		
		return getTxt;
	}
	
	public String getLastRowDataFromImporterSpecificationForExcel() throws InterruptedException {
		Thread.sleep(3000);
		String text = getLastRowDataFromImporterSpecification();
		Thread.sleep(6000);
		String getLastHSCodeText = elementUtil.WaitGetAttributeForValue(getLastHSCode, "title", 8);
		Thread.sleep(6000);
		String getLastGradeText = elementUtil.waitDoGetText(getLastGrade, 8);
		String getLastSpeciesText = elementUtil.waitDoGetText(getLastSpecies,8);
		String getLastMoistureText = elementUtil.waitDoGetText(getLastMoisture, 8);
		String getLastGS1Text = elementUtil.waitDoGetText(getLastGS1, 8);
		
		
		
		String lastTableDataSpecification = (text+getLastGradeText+getLastSpeciesText+getLastMoistureText+getLastGS1Text).replace(".000","")
				.replace(".00","")
				.replace(",00","")
				.replace(",","");
		
		return lastTableDataSpecification;
	}
	
	public String getDataFromImporterNegotationsSpecificationDownloadExcel() throws InterruptedException, IOException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickExportExcel, 8).click();
		Thread.sleep(45000);
		String file = "C:\\Users\\IND\\Downloads\\"+dealNumber+"_Test_Demo_Importer_10_Test_Demo_Sawmill_10_A_Test_Offer.xls";
		
		
		String getHSCodeTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 0);
		String getGradeTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 1);
		String getSpeciesTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 2);
		String getMoistureTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 3);
		String getDimensionTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 4).replace("x","");
		String getVolumeTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 5).replace(",00","");
		String getPriceTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 6).replace(",00","");
		String getValueTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 7).replace(",00","");
		String getCurrencyTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 8);
		String getGS1CodeTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 2, 10);
		
		String getSpecifiactionDetails = (getValueTxt+getDimensionTxt+getVolumeTxt+"200"+"100"+getPriceTxt+getGradeTxt+getSpeciesTxt+getMoistureTxt
				+getGS1CodeTxt).replace(",","");
		
		
		
		System.out.println("getSpecifiactionDetails ====="+getSpecifiactionDetails.replace(",", ""));
		
		output_Prop("NegotiationsSpecifiactionDetailsInExcel", getSpecifiactionDetails);
		return getSpecifiactionDetails;
	}
	
	public String getDataFromImporterActiveDealsSpecificationDownloadExcel() throws InterruptedException, IOException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickExportExcel, 8).click();
		Thread.sleep(45000);
		String file = "C:\\Users\\IND\\Downloads\\"+changeDealNumber+"_Test_Demo_Importer_10_Test_Demo_Sawmill_10_A_Test_Offer.xls";
		
		
		String getHSCodeTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 0);
		String getGradeTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 1);
		String getSpeciesTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 2);
		String getMoistureTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 3);
		String getDimensionTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 4).replace("x","");
		String getVolumeTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 5).replace(",00","");
		String getPriceTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 6).replace(",00","");
		String getValueTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 7).replace(",00","");
		String getCurrencyTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 8);
		String getGS1CodeTxt = elementUtil.readTheXlsExcelSpecificRowCell(file, "Worksheet", 3, 10);
		
		String getSpecifiactionDetails = (getValueTxt+getDimensionTxt+getVolumeTxt+"300"+"200"+getPriceTxt+getGradeTxt+getSpeciesTxt+getMoistureTxt
				+getGS1CodeTxt).replace(",","");
		
		
		
		System.out.println("getSpecifiactionDetails ====="+getSpecifiactionDetails.replace(",", ""));
		
		output_Prop("NegotiationsSpecifiactionDetailsInExcel", getSpecifiactionDetails);
		return getSpecifiactionDetails;
	}
	
	
	
	public void getDataFromImporterSchedule() throws InterruptedException, IOException {
		elementUtil.doVisiblityOfElementLocated(clickSchedule, 8).click();
		
		
		String getDeadLIneAcceptTxt = elementUtil.waitDoGetText(getScheduleDeadLineAccept, 8);
		String getGoodsReadyEstimateTxt = elementUtil.waitDoGetText(getScheduleGoodsEstimate, 8);
		String getScheduleGoodsMillTxt = elementUtil.waitDoGetText(getScheduleGoodsMill, 8);
		String getScheduleDeliveryPortTxt = elementUtil.waitDoGetText(getScheduleDeliveryPort, 8);
		String getScheduleETSTxt = elementUtil.waitDoGetText(getScheduleETS, 8);
		
		String getSystemDates = getDeadLIneAcceptTxt+getGoodsReadyEstimateTxt+getScheduleGoodsMillTxt+getScheduleDeliveryPortTxt
				+getScheduleETSTxt;
		
		List<String> getAddDateDetailTxt = elementUtil.arrayListMethod(getScheduleAddDateData, 8);
		
		
		 
		output_Prop("ImporterScheduleDetails",(getSystemDates.concat(getAddDateDetailTxt.toString())));
	}
	
	
	
	//////Importer Active deals//////
	
	public void activeDealsDealpopUpOpen() throws InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(activeDeals, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickToOpenDealActiveDeals, 15).click();
	}
	
	public void importerActiveDealsDealpopUpOpen() throws InterruptedException {
		Thread.sleep(3000);
		elementUtil.doPresenceOfElementLocated(clickImports, 7).click();
		elementUtil.doVisiblityOfElementLocated(activeDeals, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickToOpenDealActiveDeals, 8).click();
	}
	
	public String activeDealsImporterSpecificationLoadedVolume() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(clickLoadVolume, 8).click();
		
		String getImporterQtyPack = elementUtil.waitDoGetText(getImportQty, 8);
		String getImporterGrossWeight = elementUtil.waitDoGetText(getImportGrossWeight, 8);
		String getImporterNetWeight = elementUtil.waitDoGetText(getImportNetWeight, 8);
		
		String LoadVolumeImporterDetails = getImporterQtyPack+getImporterGrossWeight+getImporterNetWeight;
				
		return LoadVolumeImporterDetails;
	}
	
	
	
	////////Importer Settings///////////
	
	public String settingsManageCompany() throws InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(importerSettings, 8).click();
		elementUtil.doVisiblityOfElementLocated(importerManageCompany, 15).click();
		elementUtil.doVisiblityOfElementLocated(importerPublicProfile, 8).click();
		
		String getBusinessIDVAT = elementUtil.doGetText(getImporterVAT).trim();
		
		return getBusinessIDVAT;
		
	}
	
	
	public void validateCADHandelsbankenBankFromImporterMyProfile() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(navigateSettings,8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		String getImporterConsigneeAddrsText = elementUtil.waitDoGetText(getImporterConsigneeAddrs, 8).trim();
		String getImporterNotifyText = elementUtil.waitDoGetText(getImporterNotify, 8).trim();
		Thread.sleep(5000);
		elementUtil.doVisiblityOfElementLocated(clickImporterBankingInfo, 8).click();
		String getImporterBankNameText = elementUtil.waitDoGetText(getImporterBankName, 8).trim();
		String getImporterBankAddrsText = elementUtil.waitDoGetText(getImporterBankAddrs, 8).trim(); 
		
		String getImporterBankNameAddrs = getImporterBankNameText+", "+getImporterBankAddrsText;
		
		output_Prop("ImporterConsigneeAddress",getImporterConsigneeAddrsText);
		output_Prop("ImporterNotify",getImporterNotifyText);
		output_Prop("ImporterBankNameAddress",getImporterBankNameAddrs);
		
		
	}
	
	
	
	public void validateInstructionEUR1FromImporterMyProfile() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(navigateSettings,8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		
		String getImporterNotifyText = elementUtil.waitDoGetText(getImporterNotify, 8).trim();
		output_Prop("ImporterNotify",getImporterNotifyText);
	}
    
	
	public void validateInstructionCOFromImporterMyProfile() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(navigateSettings,8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		
		String getImporterNotifyText = elementUtil.waitDoGetText(getImporterNotify, 8).trim();
		output_Prop("ImporterNotify",getImporterNotifyText);
	}
	
	public void validateInstructionECOFromImporterMyProfile() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(navigateSettings,8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		
		String getImporterNotifyText = elementUtil.waitDoGetText(getImporterNotify, 8).replace("\n","").trim();
		output_Prop("ImporterNotify",getImporterNotifyText);
	}
	
	public void validateInstructionEEUR1FromImporterMyProfile() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(navigateSettings,8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		
		String getImporterNotifyText = elementUtil.waitDoGetText(getImporterNotify, 8).replace("\n","").trim();
		output_Prop("ImporterNotify",getImporterNotifyText);
	}
	
	public void validateInstructionPhytoFromImporterMyProfile() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(navigateSettings,8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany,8).click();
		elementUtil.doVisiblityOfElementLocated(clickCompanyProfile,8).click();
		
		
		String getImporterNotifyDetialsText = elementUtil.waitDoGetText(getImporterNotify,8).trim();
		
		
		
		output_Prop("ImporterAddressDetails",getImporterNotifyDetialsText);
	}
	
	
	public void validateBLFromImporterMyProfile() throws IOException, InterruptedException {
		Thread.sleep(3000);
		elementUtil.doVisiblityOfElementLocated(navigateSettings,8).click();
		elementUtil.doVisiblityOfElementLocated(clickManageCompany, 8).click();
		String getImporterConsigneeAddrsText = elementUtil.waitDoGetText(getImporterConsigneeAddrs, 8).trim().replace("\n","");
		String getImporterNotifyText = elementUtil.waitDoGetText(getImporterNotify, 8).trim().replace("\n","");
		 
		output_Prop("ImporterConsigneeAddress",getImporterConsigneeAddrsText);
		output_Prop("ImporterNotify",getImporterNotifyText);
			
	}
	
	//Importer Packing list
	
	public void activeDealsImporterPackingList() throws IOException, InterruptedException {
		 elementUtil.doVisiblityOfElementLocated(clickPackingList, 8).click();
		// elementUtil.waitForSendKeys(enterPackingUserName, 8, "k.smith2926373");
		// elementUtil.waitForSendKeys(enterPackingPassword, 8, "CSTimber#2020!");
		// elementUtil.doVisiblityOfElementLocated(clickPackingSignIn, 8).click();
		 elementUtil.doVisiblityOfElementLocated(clickPackingList2, 8).click();
		 elementUtil.doVisiblityOfElementLocated(clickPackingPagination, 8).click(); 
		 elementUtil.doSelectByVisibleText(packingSelectDropDown, "100");
		 
		 //elementUtil.doVisiblityOfElementLocated(packingCheck, 8).click();
		 
		 WebElement scrollCert = elementUtil.doVisiblityOfElementLocated(checkBox, 9);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView(true)",scrollCert );
		 Thread.sleep(8000);
		// elementUtil.waitWriteInExcelRow1(getPackListDetails,2, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListNumber,2, 8);
		 
		 
		 elementUtil.waitWriteInExcelRow1(getPackListSpecies,3, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListGrade,4, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListThick,5, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListWidth,6, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListTotalQty,7, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListTotalVolume,8, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListTotalValue,9, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListContainer,10, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListTruck,11, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListWagon,12, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListBL,13, 8);
		 elementUtil.waitWriteInExcelRow1(getPackListRFID,14, 8);
	}
	
	
	//////Forwarder Active deals --> Product Description//////
	
	public String activeDealsForwarderInstructionProductDescription() throws IOException, InterruptedException {
		elementUtil.doVisiblityOfElementLocated(clickInstruction, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickProdDescription, 8).click();
		Thread.sleep(3000);
		String getForwarderText = elementUtil.waitDoGetText(clearProdDescBox, 8).toString().trim();
		
		return getForwarderText;
	}
	
	public void getDataFromForwarderSchedule() throws InterruptedException, IOException {
		elementUtil.doVisiblityOfElementLocated(clickSchedule, 8).click();
		
		
		String getDeadLIneAcceptTxt = elementUtil.waitDoGetText(getScheduleDeadLineAccept, 8);
		String getGoodsReadyEstimateTxt = elementUtil.waitDoGetText(getScheduleGoodsEstimate, 8);
		String getScheduleGoodsMillTxt = elementUtil.waitDoGetText(getScheduleGoodsMill, 8);
		String getScheduleDeliveryPortTxt = elementUtil.waitDoGetText(getScheduleDeliveryPort, 8);
		String getScheduleETSTxt = elementUtil.waitDoGetText(getScheduleETS, 8);
		
		String getSystemDates = getDeadLIneAcceptTxt+getGoodsReadyEstimateTxt+getScheduleGoodsMillTxt+getScheduleDeliveryPortTxt
				+getScheduleETSTxt;
		
		List<String> getAddDateDetailTxt = elementUtil.arrayListMethod(getScheduleAddDateData, 8);
		
		
		 
		output_Prop("ForwarderScheduleDetails",(getSystemDates.concat(getAddDateDetailTxt.toString())));
	}
	
	//Forwarder Packing List
		public void activeDealsForwarderPackingList() throws IOException, InterruptedException {
			 elementUtil.doVisiblityOfElementLocated(clickPackingList, 8).click();
			 //elementUtil.waitForSendKeys(enterPackingUserName, 8, "a.smith9477374");
			 //elementUtil.waitForSendKeys(enterPackingPassword, 8, "CSTimber#2020!");
			 //elementUtil.doVisiblityOfElementLocated(clickPackingSignIn, 8).click();
			 elementUtil.doVisiblityOfElementLocated(clickPackingList2, 8).click();
			 elementUtil.doVisiblityOfElementLocated(clickPackingPagination, 8).click(); 
			 elementUtil.doSelectByVisibleText(packingSelectDropDown, "100");
			 
			 //elementUtil.doVisiblityOfElementLocated(packingCheck, 8).click();
			 
			 WebElement scrollCert = elementUtil.doVisiblityOfElementLocated(checkBox, 9);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("arguments[0].scrollIntoView(true)",scrollCert );
			 Thread.sleep(8000);
			// elementUtil.waitWriteInExcelRow1(getPackListDetails,2, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListNumber,2, 8);
			 
			 
			 elementUtil.waitWriteInExcelRow1(getPackListSpecies,3, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListGrade,4, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListThick,5, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListWidth,6, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListTotalQty,7, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListTotalVolume,8, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListTotalValue,9, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListContainer,10, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListTruck,11, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListWagon,12, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListBL,13, 8);
			 elementUtil.waitWriteInExcelRow1(getPackListRFID,14, 8);
		}
	
}

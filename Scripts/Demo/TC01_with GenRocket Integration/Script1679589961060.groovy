import java.lang.ProcessBuilder as ProcessBuilder
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import groovy.json.JsonSlurper

//Import GenRocket Jars
import com.genRocket.engine.EngineAPI as EngineAPI
import com.genRocket.engine.EngineManual as EngineManual

//Load GenRocket components
String scenarioPath = '/Users/sakthi/Desktop/GenRocket/home'
String scenarioName = 'UserAccScenario.grs'
String testDataSuiteName = 'TestDataCase.gtdc'
String testDataCategoryName = 'System'
String testDataCaseName = 'pwdGen'
String fileSep = System.getProperty("file.separator");
String scenarioPathAndName = scenarioPath + fileSep + scenarioName;
String testDataSuitePath = scenarioPath + fileSep + testDataSuiteName;

//GenRocket Synthetic Data Generation
EngineAPI engine = new EngineManual()
engine.scenarioLoad(scenarioPathAndName)
engine.testDataCaseAdd(testDataSuitePath, testDataCategoryName, testDataCaseName,true, true, null, null)
engine.applyGCases()
engine.scenarioRun()

//Parse the generated data
def jsonFile = new File(scenarioPath+fileSep+'output/UserAccounts.json')
def jsonSlurper = new JsonSlurper()
def jsonData = jsonSlurper.parseText(jsonFile.text)

	//Iterate data for execution
	for (def testData : jsonData) {

			String grFName = testData.get("fName")
			String grLName = testData.get("lName")
			String gremail = testData.get("email")
			String grPwd = testData.get("pwd")
			String grCnfPwd = testData.get("confirmPwd")
			String grPwdStrength = testData.get("pwdStrength")
			System.out.println("Testemail:"+gremail)
			
			WebUI.openBrowser('')
			WebUI.navigateToUrl('https://magento.softwaretestingboard.com/')
			WebUI.click(findTestObject('Object Repository/Page_Home Page/a_Create an Account'))
			WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/firstName'), grFName)
			WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/lastName'), grLName)
			WebUI.click(findTestObject('Object Repository/Page_Create New Customer Account/subscription checkbox'))
			WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/email'), gremail)
			WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/pwd'), grPwd)
			WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/pwd_confirm'), grCnfPwd)
			WebUI.verifyElementText(findTestObject('Page_Create New Customer Account/passwordMeter'), grPwdStrength)
			WebUI.click(findTestObject('Object Repository/Page_Create New Customer Account/Create an Account button'))
			WebUI.click(findTestObject('Object Repository/Page_My Account/img'))
			WebUI.enableSmartWait()
			WebUI.click(findTestObject('Object Repository/Page_Home Page/button_Change'))
			WebUI.click(findTestObject('Object Repository/Page_Home Page/a_Sign Out'))
			WebUI.closeBrowser()
	}



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
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.remote.DesiredCapabilities



EngineAPI engine = new EngineManual()
def jsonSlurper = new JsonSlurper()
List<Object> genrocketData
String jsonData
// Set Desired Capabilities
def desiredCapabilities = new HashMap<String, Object>()
desiredCapabilities.setCapability("browserName", "chrome")
desiredCapabilities.setCapability("version", "latest")
desiredCapabilities.setCapability("platform", "macOS 12.5")
desiredCapabilities.setCapability("testopsKey", "39176078-16e5-46c3-919d-b3767c96c321")

//Import GenRocket Jars
import com.genRocket.engine.EngineAPI as EngineAPI
import com.genRocket.engine.EngineManual as EngineManual
//Load GenRocket components
String scenarioPath = '/Users/sakthi/Desktop/GenRocket/home'
String scenarioName = 'UserAccScenario.grs'
String testDataSuiteName = 'TestDataCase.gtdc'
String testDataCategoryName = 'System'
String testDataCaseName = 'pwdGen'
String genrocketDomain = 'UserAccounts' 
String fileSep = System.getProperty("file.separator");
String scenarioPathAndName = scenarioPath + fileSep + scenarioName;
String testDataSuitePath = scenarioPath + fileSep + testDataSuiteName;
engine.scenarioLoad(scenarioPathAndName)
engine.testDataCaseAdd(testDataSuitePath, testDataCategoryName, testDataCaseName,true, true, null, null)
engine.applyGCases()

//GenRocket Synthetic Data Generation
genrocketData = engine.scenarioRunInMemory(genrocketDomain)

//Parse and Iterate the generated data for execution
jsonData = JsonOutput.toJson(genrocketData)
def jsonObject = jsonSlurper.parseText(jsonData)
	for (def testData : jsonObject) {

			String grFName = testData.get("fName")
			String grLName = testData.get("lName")
			String gremail = testData.get("email")
			String grPwd = testData.get("pwd")
			String grCnfPwd = testData.get("confirmPwd")
			String grPwdStrength = testData.get("pwdStrength")
			System.out.println("Testemail:"+gremail)
			
			WebUI.comment("Starting browser with Desired Capabilities: " + desiredCapabilities.toString())
			DriverFactory.changeWebDriver(desiredCapabilities)
			//WebUI.openBrowser('')
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



	
	
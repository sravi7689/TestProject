import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import java.lang.ProcessBuilder as ProcessBuilder
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

// Test automation scripts
WebUI.openBrowser('')

WebUI.navigateToUrl('https://magento.softwaretestingboard.com/')

WebUI.click(findTestObject('Object Repository/Page_Home Page/a_Create an Account'))

WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/firstName'), fName)

WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/lastName'), lName)

WebUI.click(findTestObject('Object Repository/Page_Create New Customer Account/subscription checkbox'))

WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/email'), email)

WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/pwd'), pwd)

System.out.println('pwd:' + pwd)

WebUI.setText(findTestObject('Object Repository/Page_Create New Customer Account/pwd_confirm'), confirmPwd)

WebUI.verifyElementText(findTestObject('Page_Create New Customer Account/passwordMeter'), pwdStrength)

WebUI.click(findTestObject('Object Repository/Page_Create New Customer Account/Create an Account button'))

WebUI.click(findTestObject('Object Repository/Page_My Account/img'))

WebUI.enableSmartWait()

WebUI.click(findTestObject('Object Repository/Page_Home Page/button_Change'))

WebUI.click(findTestObject('Object Repository/Page_Home Page/a_Sign Out'))

WebUI.closeBrowser()
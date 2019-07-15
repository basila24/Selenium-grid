package comtest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ssts.pcloudy.Connector;
import com.ssts.pcloudy.Version;
import com.ssts.pcloudy.appium.PCloudyAppiumSession;
import com.ssts.pcloudy.dto.appium.booking.BookingDtoDevice;
import com.ssts.pcloudy.dto.device.MobileDevice;
import com.ssts.pcloudy.dto.file.PDriveFileDTO;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Android {
	
	private PCloudyAppiumSession pCloudySession;
	private static URL endpoint;
	private BookingDtoDevice bookedDevice;
	
	static String appPath = "C:\\Users\\extmbasith\\Downloads\\CLCHotelLocator-release-18-06-2019.apk";
	
	public String pCloudySetUp(String email, String authKey, String platform, String version, int duration,
			String browser, String deviceName, String deviceBrand, String location) throws Exception, IOException {

		// PCloudyAppiumSession pCloudySession = null;
		String message = "";
		try {
			Connector con = new Connector(location);
			String authToken = con.authenticateUser(email, authKey);
			ArrayList<MobileDevice[]> selectAvailableDevices = new ArrayList<>();
			ArrayList<MobileDevice> selectedDevices = new ArrayList<>();

			if (deviceBrand.equalsIgnoreCase("NONE")) {
				if (platform.equalsIgnoreCase("android")) {
					selectedDevices.addAll(
							con.chooseDevices(authToken, platform, new Version("6.*.*"), new Version(version), 1));
				} else {
					selectedDevices.addAll(
							con.chooseDevices(authToken, platform, new Version("10.*.*"), new Version(version), 1));
				}
			} else {
				selectAvailableDevices.add(con.getDevices(authToken, 15, platform, true));

				String deviceModel = deviceName.replaceAll("\\s+", "");

				String full_name = (platform.equalsIgnoreCase("ios"))
						? "Apple" + "_" + deviceModel + "_" + platform + "_" + version
						: deviceBrand + "_" + deviceModel + "_" + platform + "_" + version;

				for (MobileDevice[] Devices : selectAvailableDevices) {
					for (MobileDevice dev : Devices) {
						if (dev.full_name.equalsIgnoreCase(full_name) || (dev.manufacturer.equalsIgnoreCase(deviceBrand)
								&& dev.display_name.equalsIgnoreCase(deviceModel)
								&& dev.version.equals(version))/*
																 * || (dev.display_name.equalsIgnoreCase(deviceModel) &&
																 * dev.version.equals(version))
																 */) {
							selectedDevices.add(dev);
						}
					}
				}
			}
			if (!selectedDevices.isEmpty()) {
				String name = selectedDevices.get(0).full_name; // Apple_iPhone6sPlus_Ios_10.2.1
				bookedDevice = con.AppiumApis().bookDevicesForAppium(authToken, selectedDevices, duration,
						"STM Session:" + name)[0];

				if (appPath.equals("chrome"))
					con.AppiumApis().initAppiumHubForBrowser(authToken, browser);
				else {
					 PDriveFileDTO pDriveFile = con.uploadApp(authToken, new File(appPath));
					//PDriveFileDTO pDriveFile = con.getAvailableAppIfUploaded(authToken, appPath); // .uploadApp(authToken,
																									// new
																									// File(appPath));
					System.out.println("ipa file uploaded successfully");
					con.AppiumApis().initAppiumHubForApp(authToken, pDriveFile);
				}

				endpoint = con.AppiumApis().getAppiumEndpoint(authToken);
				System.out.println("Appium Endpoint:" + endpoint);

				setpCloudySession(new PCloudyAppiumSession(con, authToken, bookedDevice));
			} else {
				message = "The desired device is not available.";
			}
		} catch (Exception e) {
			message = e.toString();
		}
		return message;
	}

	
	
	public static void main(String[] args) throws IOException, Exception {
		try {
			// TODO Auto-generated method stub

			String email = "badrampalli.roshan@fleetcor.com", authKey = "jm4mjc4mc433w22q2w45s7p5";
			String platform = "Android", version = "8.0.0";
			int duration = 5;
			String browser = "", deviceName = "Galaxy S8 +";
			String deviceBrand = "NONE", location = "https://fleetcor.pcloudy.com";
			String driverMobile = "Safari";
			String UDID = "b0db7c28d720d0b17e95c15a7eea1f8a2c2effb3";

			/*if (UDID.equals("365a581fe6f6427971178e78cd21bc67be29f849")) {
				version = "11.0.0";
				deviceName = "iPhone 8 Plus";
			} else if (UDID.equals("b0db7c28d720d0b17e95c15a7eea1f8a2c2effb3")) {
				version = "10.0.1";
				deviceName = "iPad Air";
			} else if (UDID.equals("8710332c280d0bb814589005fb6338483adf392b")) {
				version = "10.1.1";
				deviceName = "iPhone 6S";
			} else if (UDID.equals("d1a080640a9066b221c6d7b215a8f6d6a7e3dffe")) {
				version = "11.3.0";
				deviceName = "iPhone 7 Plus";
			}*/

			// else if(UDID.equals("")) { version = ""; deviceName = ""; }

			Android obj = new Android();
			String message = obj.pCloudySetUp(email, authKey, platform, version, duration, browser, deviceName,
					deviceBrand, location);
			
			AppiumDriver driver=null;
			
			//********************************************************************
			
			DesiredCapabilities capabilities1 = new DesiredCapabilities();
			capabilities1.setCapability("pCloudy_Username", "Enter your email Id");
			capabilities1.setCapability("pCloudy_ApiKey", "Enter your API Key");
			capabilities1.setCapability("pCloudy_ApplicationName", "pCloudyAppiumDemo.apk");
			capabilities1.setCapability("pCloudy_DurationInMinutes", 5);
			capabilities1.setCapability("pCloudy_DeviceFullName", "Samsung_GalaxyTabA_Android_7.1.1");
			driver = new AndroidDriver(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities1);

			
			
			
			//********************************************************************

			if (message.isEmpty()) {
				DesiredCapabilities capabilities = new DesiredCapabilities();

				// capabilities.setCapability("app", appPath); // appPath "Safari"

				capabilities.setCapability("newCommandTimeout", 600);
				capabilities.setCapability("launchTimeout", 90000);

				if (appPath.equals("Safari")) {
					capabilities.setBrowserName(driverMobile);
					capabilities.setCapability("UDID", UDID);
					capabilities.setCapability("bundleId", "com.pcloudy.safarilauncher");
				} else {
					//capabilities.setCapability("bundleId", "com.pcloudy.TestmunkDemo"); // com.pcloudy.safarilauncher
					// "com.ddstudio.onetwothreemovieinfo"
					// capabilities.setCapability("browserName", "Safari");
					//capabilities.setCapability("UDID", UDID);
					//capabilities.setCapability("usePrebuiltWDA", false);
					//capabilities.setCapability("acceptAlerts", true);
				}

				capabilities.setCapability("platformName", obj.getBookedDevice().capabilities.platformName);
				capabilities.setCapability("deviceName", obj.getBookedDevice().capabilities.deviceName);

				
				//*****************************************************
				/*if (obj.getBookedDevice().getVersion().compareTo(new Version("9.3")) >= 0)
					capabilities.setCapability("automationName", "XCUITest");
				else
					throw new Exception(
							"IosSafari automation based on Instruments is not supported. Please run on newer devices with XCUITest.");

				System.out.println("About to launch the Driver now!!.");*/
				
				//******************************************************
				
				System.out.println("Capabilities :" + capabilities.toString());

				//AppiumDriver d = new IOSDriver(obj.getEndpoint(), capabilities);
				
				AppiumDriver d = new AndroidDriver(endpoint, capabilities);
				// XCUITest xcui = new XCUITest ();
				System.out.println("Driver Lauched successfully!!.");
				Thread.sleep(2000);
				
				d.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")).sendKeys("test@testname.com");
				
				d.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField[1]")).sendKeys("testmunk");
				
				d.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]")).click();

//				List<MobileElement> elementsTwo = d.findElementsByClassName("UIATextField");
//				
//				for (MobileElement ele : elementsTwo) {
//			        System.out.println(ele.getText());
//			       ele.sendKeys("Magesh");
//			    }
//				
//				d.findElement(By.xpath("//*[@text='Email']")).sendKeys("Password");//
////				MobileElement elements = (MobileElement) d.findElementsByClassName("UIATextField");//
////				System.out.println("Text Elements Count : " + elementsTwo.size());
////				((MobileElement) d.findElementsByClassName("UIATextField")).sendKeys("Magesh"); // By.xpath("//*[@placeholder='Email']")).sendKeys("Magesh");
//				d.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("Password");
//				d.findElement(By.id("SIGN IN")).click();

				System.out.println("Element clicked!!");
				
				obj.pCloudySession.releaseSessionNow();
			} else
				System.out.println("Message : " + message);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	public URL getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(URL endpoint) {
		this.endpoint = endpoint;
	}

	public PCloudyAppiumSession getpCloudySession() {
		return pCloudySession;
	}

	public void setpCloudySession(PCloudyAppiumSession pcloudyAppiumSession) {
		this.pCloudySession = pcloudyAppiumSession;
	}

	public BookingDtoDevice getBookedDevice() {
		return bookedDevice;
	}

	public void setBookedDevice(BookingDtoDevice bookedDevice) {
		this.bookedDevice = bookedDevice;
	}
}

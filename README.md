# Automation Test With Appium in Java - Browser Testing on Mobile Devices (Android & iOS)
This repository contains automated test scripts for browser testing on Android and iOS mobile devices using Appium and Selenium. The tests are designed to run on mobile devices, utilizing the Chrome browser for Android and Safari browser for iOS.

## Prerequisites
Before running the tests, ensure you have the following installed on your system:
- *Java Development Kit (JDK)* - [Download Here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- *Node.js* - [Download Here](https://nodejs.org/en/download/)
- *Appium* - [Installation Guide](http://appium.io/docs/en/about-appium/getting-started/?lang=en)
- *Android Debug Bridge (ADB)* - Required for Android devices. Install via Android Studio or stand-alone [here](https://developer.android.com/studio/command-line/adb)
- *Xcode (For iOS)* - Required for iOS devices, available from the Mac App Store.

------------------------------------------------------------------------------------
## Setup Instructions
**Clone the repository:**


```bash
git clone https://github.com/okiabrian123/Automation-Test-Mobile-Browser.git
cd Automation-Test-Mobile-Browser
```
## Install required dependencies:

### 1. Install dependencies using npm:
```bash
npm install
```


### 2. Set up Chromedriver (Android Only)

The Chromedriver is necessary to automate the Chrome browser on Android devices. It must match the version of Chrome installed on your mobile device.

Download the correct version of Chromedriver for your Android Chrome version from here.
Place the downloaded chromedriver file in the drivers folder inside this project.
For example, if your mobile Chrome version is 128, download the corresponding driver and place it in:


```bash
/Automation-Test-Mobile-Browser/drivers/
```
**example:**
```bash
/Automation-Test-Mobile-Browser/drivers/128.0.6613.84
```


### 3. Set up WebDriverAgent (iOS Only):

For testing on iOS, you need to configure WebDriverAgent to work with Safari. Follow the instructions from the official Appium documentation for setting up WebDriverAgent on iOS, or follow my instructions [here](https://github.com/okiabrian123/Automation-Test-Mobile-Browser/blob/main/WebDriverAgent_Setup.md).



### 4. Set Chromedriver Path in the Script (Android Only):

For doing testing in Android, we need update the path to the Chromedriver to match the version you placed in the project. For example:

```java
caps.setCapability("appium:chromedriverExecutable", "/path/to/your/chromedriver");
```
Ensure the path points to the correct location of the Chromedriver file in your project.
In this project only need to write folder name in driver folder.

```java
String projectDir = System.getProperty("user.dir");
if (!driverPath.startsWith("/")) { // Jika tidak dimulai dengan '/'
   driverPath = projectDir + "/driver/" + driverPath+"/chromedriver"; // Menggabungkan dengan projectDir
}
```

------------------------------------
## Important Note
Ensure the Chromedriver version matches the Chrome browser version installed on your Android device. You can check your mobile Chrome version by navigating to Settings -> About Chrome.
For iOS, ensure WebDriverAgent is properly configured for Safari testing.

--------------------------------------
## Running the Tests
### Android
Connect your Android device with USB debugging enabled.

**Launch Appium:**

```bash
appium
```
**Run the tests for Android:**
*example :*
- *device Name = OPPO A57*
- *OS Version = 14*
- *chrome Version = 128.0.6613*
```bash
mvn test -Pandroid -DdeviceName="OPPO A57" -DplatformVersion="14" -DchromedriverPath="128.0.6613.84"
```
### iOS###
Connect your iOS device with Developer mode enabled.

**Launch Appium:**

```bash
appium
```
**Run the tests for iOS(simulator):**
I only created this for the simulator because I don't have a real iOS device.
*example :*
- *device Name = iPhone SE (3rd generation)*
- *OS Version = 17.4*
```bash
mvn test -Pios -DdeviceName="iPhone SE (3rd generation)" -DplatformVersion="17.4"
```

## Repository Structure
```bash
Automation-Test-Mobile-Browser/
.
|
├── drivers/                # Folder to store your Chromedriver for Android
├── src/test/java/com.test.example
│   ├── LoginTest.java           # Test scripts
├── pom.xml                 # Maven project file for dependencies, plugin and profile
└── README.md               # Project documentation
```
-------------------------------
## Contributing
Feel free to submit pull requests for enhancements or bug fixes. Please ensure code changes are properly tested.


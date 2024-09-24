# WebDriverAgent Setup for Web Testing in Safari on iOS
Overview
This repository provides a guide to set up WebDriverAgent for automated web testing in Safari on iOS devices. WebDriverAgent is essential for running tests on physical iOS devices.

Before you begin, ensure you have the following:

- **macOS with Xcode installed**
- **Homebrew for installing dependencies**
- **Basic knowledge of Appium and Selenium**
----------------
## Installation
Clone the Repository

```bash
git clone https://github.com/appium/WebDriverAgent.git
cd WebDriverAgent
```
------------
## Install Dependencies

``` bash
./Scripts/bootstrap.sh
```
----------
## Configuration
### Open in Xcode
Open WebDriverAgent.xcodeproj in Xcode.
Select the WebDriverAgentRunner target and choose your device or simulator.

### Signing & Capabilities
Set up signing for the WebDriverAgentRunner In the "Signing & Capabilities" section, check "Automatically manage signing" and select your development team.
  
### Build and Run
Build and run WebDriverAgentRunner on your physical device or simulator to launch the WebDriverAgent app.

-------------
## Important Notes
Ensure your iOS device is connected to your Mac and is registered in Xcode.
WebDriverAgent must be running for each new test session if you are testing on a physical device.

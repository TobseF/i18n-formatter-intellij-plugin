# 🛠 I18N-Formatter IntelliJ Plugin
**I18N-Formatter** is a plugin for [Intellij IDEA](https://www.jetbrains.com/idea/) which formats a `message.properties` file.

This plugin sorts, formats and groups Java `message.properties` files in the same way as [Eclipse](https://www.eclipse.org/). 

## Usage
Just select *Edit > Format Messages.properties* in menu.  
Or use the shortcut: <kbd>Alt</kbd>+<kbd>Shift</kbd>+<kbd>L</kbd>.

## Installation
From public plugin repository: [i18n-Formatter](https://plugins.jetbrains.com/plugin/8573?pr=idea)

## Notes
The property file has to be open in the editor as active tab.
The formatter adapts the [Eclipse](https://www.eclipse.org/) `message.properties` style.

The formatting consists of three steps:

1. Sort keys
2. Group key by parent key
3. Align values bases on longest key in the group

Groups are separated by new lines 
First line includes 'formatter:off' to protect file against the IntelliJ formatter.

## Sample
```properties
#@formatter:off
Popup_Messages_Alter   = Alert
Popup_Messages_Warning = Be careful

Settings_User_Email_Title = Email
Settings_User_FirstName   = First Name
Settings_User_LastName    = Last Name
Settings_Window_Title     = Title
```

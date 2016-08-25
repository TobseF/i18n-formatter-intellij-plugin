**I18N-Formatter** is a plugin for Intellij IDEA which formats a Message.Properties file

Just call *Edit->Format Messages.properties* menu, or *Alt+ Shift+L* shortcut.

##Installation
From public plugin repository: [i18n-Formatter](https://plugins.jetbrains.com/plugin/8573?pr=idea)

##Notes
The property file has to be open in the editor as active tab.

Formatting consists of three steps:

1. Sort keys
2. Group key by parent key
3. Align values bases on longest key in the group

Groups are separated by new lines 
First line includes 'formatter:off' to protect file against the IntelliJ formatter

##Samlpe
```properties
#@formatter:off
Popup_Messages_Alter   = Alert
Popup_Messages_Warning = Be careful

Settings_User_Email_Title = Email
Settings_User_FirstName   = First Name
Settings_User_LastName    = Last Name
Settings_Window_Title     = Title
```

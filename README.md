# social_image_share

Flutter Plugin for sharing image to social media.

You can use it to share:
- Facebook 
- Instagram 
- Whatsapp 
- Twitter 
- System

support:
- Android 
- iOS


## Getting Started

add `social_image_share` as a [dependency in your pubspec.yaml file](https://flutter.io/platform-plugins/).

##Setup

#### Android

Add "facebook app id" to the application tag of AndroidManifest.xml
```xml
            <application>
               ...
               //add this 
                <meta-data
                    android:name="com.facebook.sdk.ApplicationId"
                    android:value="@string/facebook_app_id" />
                <provider
                    android:name="com.facebook.FacebookContentProvider"
                    android:authorities="com.facebook.app.FacebookContentProvider[facebook_app_id]" android:exported="false" ></provider>
					...
            </application>
```

string.xml:
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
<!-- Replace "123456789" with your Facebook App ID here. -->
    <string name="facebook_app_id">123456789</string>
</resources>
```


#### IOS
    

 Add below deatils in your plist file and replace "123456789" with your Facebook App ID .

```
	<key>FacebookAppID</key>
	<string>123456789</string>
	<key>LSApplicationQueriesSchemes</key>
	<array>
		<string>fbauth2</string>
		<string>fbapi</string>
		<string>fbapi20130214</string>
		<string>fbapi20130410</string>
		<string>fbapi20130702</string>
		<string>fbapi20131010</string>
		<string>fbapi20131219</string>
		<string>fbapi20140410</string>
		<string>fbapi20140116</string>
		<string>fbapi20150313</string>
		<string>fbapi20150629</string>
		<string>fbapi20160328</string>
		<string>fbauth</string>
		<string>fb-messenger-share-api</string>
		<string>fbauth2</string>
		<string>fbshareextension</string>
		<string>facebook-stories</string>
        	<string>whatsapp</string>
        	<string>twitter</string>
        	<string>instagram</string>
	</array>
```

## Usage

#### Add the following imports to your Dart code:

```
import 'package:social_image_share /social_image_share .dart';
```


## Methods

### Instagram
#### shareToInstagram({String imagePath})

### Whatsapp
#### shareToWhatsapp({String imagePath})

### Twitter
#### shareToTwitter({String imagePath})

### Facebook
#### shareToFacebook({String imagePath})

### System Share
#### shareToSystem({String imagePath})


## Example
#### shareToInstagram
```
	Directory? directory ;
    if (Platform.isAndroid) {
      directory = await getExternalStorageDirectory();
    } else {
      directory = await getApplicationDocumentsDirectory();
    }
    String path = '${directory!.path}/' + UniqueKey().toString() + '.png';
    final imagePath = await File(path).create();
    await imagePath.writeAsBytes(capturedImage);
	ShareUtil().shareToInstagram(imagePath: path);
``` 

## Note:
Use getExternalStorageDirectory() for **Android** and getApplicationDocumentsDirectory() for **IOS**


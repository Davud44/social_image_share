import Flutter
import UIKit

public class SwiftSocialImageSharePlugin: NSObject, FlutterPlugin {

        let controller : FlutterViewController = window?.rootViewController as! FlutterViewController

        let _methodWhatsApp = "whatsapp_share";
        let _methodFaceBook = "facebook_share";
        let _methodTwitter = "twitter_share";
        let _methodInstagram = "instagram_share";
        let _methodSystem = "system_share";

  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "social_image_share", binaryMessenger: registrar.messenger())
    let instance = SwiftSocialImageSharePlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }

}

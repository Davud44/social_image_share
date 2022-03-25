
import 'dart:async';

import 'package:flutter/services.dart';

class SocialImageShare {
  static const MethodChannel _channel = MethodChannel('social_image_share');
  static const String _methodWhatsApp = 'whatsapp_share';
  static const String _methodFaceBook = 'facebook_share';
  static const String _methodTwitter = 'twitter_share';
  static const String _methodInstagramShare = 'instagram_share';
  static const String _methodSystemShare = 'system_share';

  Future<String?> shareToInstagram({required String imagePath}) async
  {
    final Map<String, dynamic> arguments = <String, dynamic>{};
    arguments.putIfAbsent('url', () => imagePath);
    String? result;

    try {
      result = await _channel.invokeMethod<String>(_methodInstagramShare, arguments);
    } catch (e) {
      return  e.toString();
    }
    return result;
  }

  Future<String?> shareToWhatsapp({required String imagePath}) async
  {
    final Map<String, dynamic> arguments = <String, dynamic>{};
    arguments.putIfAbsent('url', () => imagePath);
    String? result;

    try {
      result = await _channel.invokeMethod<String>(_methodWhatsApp, arguments);
    } catch (e) {
      return  e.toString();
    }
    return result;
  }

  Future<String?> shareToTwitter({required String imagePath}) async
  {
    final Map<String, dynamic> arguments = <String, dynamic>{};
    arguments.putIfAbsent('url', () => imagePath);
    String? result;

    try {
      result = await _channel.invokeMethod<String>(_methodTwitter, arguments);
    } catch (e) {
      return  e.toString();
    }
    return result;
  }

  Future<String?> shareToFacebook({required String imagePath}) async
  {
    final Map<String, dynamic> arguments = <String, dynamic>{};
    arguments.putIfAbsent('url', () => imagePath);
    String? result;

    try {
      result = await _channel.invokeMethod<String>(_methodFaceBook, arguments);
    } catch (e) {
      return  e.toString();
    }
    return result;
  }

  Future<String?> shareToSystem({required String imagePath}) async
  {
    final Map<String, dynamic> arguments = <String, dynamic>{};
    arguments.putIfAbsent('url', () => imagePath);
    String? result;

    try {
      result = await _channel.invokeMethod<String>(_methodSystemShare, arguments);
    } catch (e) {
      return  e.toString();
    }
    return result;
  }
}

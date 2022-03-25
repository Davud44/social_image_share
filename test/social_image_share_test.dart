import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:social_image_share/social_image_share.dart';

void main() {
  const MethodChannel channel = MethodChannel('social_image_share');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await SocialImageShare.platformVersion, '42');
  });
}

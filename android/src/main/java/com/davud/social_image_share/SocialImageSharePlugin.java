package com.davud.social_image_share;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.util.List;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * SocialImageSharePlugin
 */
public class SocialImageSharePlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

    final private static String _methodWhatsApp = "whatsapp_share";
    final private static String _methodFaceBook = "facebook_share";
    final private static String _methodTwitter = "twitter_share";
    final private static String _methodSystemShare = "system_share";
    final private static String _methodInstagramShare = "instagram_share";

    private MethodChannel channel;
    private Activity activity;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "social_image_share");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivity() {

    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        String url, msg;
        switch (call.method) {
            case _methodFaceBook:
                url = call.argument("url");
                shareFacebook(url, result);
                break;
            case _methodTwitter:
                url = call.argument("url");
                shareTwitter(url, result);
                break;
            case _methodWhatsApp:
                url = call.argument("url");
                shareWhatsApp(url, result);
                break;
            case _methodSystemShare:
                url = call.argument("url");
                shareSystem(url, result);
                break;
            case _methodInstagramShare:
                msg = call.argument("url");
                shareInstagram(msg, result);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

  private void shareInstagram(String imagePath, MethodChannel.Result result) {
    File file = new File(imagePath);
    Uri fileUri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);

    Intent instagramIntent = new Intent(Intent.ACTION_SEND);
    instagramIntent.setType("image/*");
    instagramIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
    instagramIntent.setPackage("com.instagram.android");
    try {
      activity.startActivity(instagramIntent);
      result.success("Success");
    } catch (ActivityNotFoundException e) {
      e.printStackTrace();
      result.success("Failure");
    }
  }

  private void shareWhatsApp(String imagePath, MethodChannel.Result result) {
    try {
      Intent whatsappIntent = new Intent(Intent.ACTION_SEND);

      whatsappIntent.setPackage("com.whatsapp");
      if (!TextUtils.isEmpty(imagePath)) {
        whatsappIntent.setType("*/*");
        File file = new File(imagePath);
        Uri fileUri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);
        whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        whatsappIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      } else {
        whatsappIntent.setType("text/plain");
      }
      activity.startActivity(whatsappIntent);
      result.success("success");
    } catch (Exception var9) {
      result.error("error", var9.toString(), "");
    }
  }


  private void shareTwitter(String imagePath, MethodChannel.Result result) {
    File file = new File(imagePath);
    Uri fileUri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);

    Intent instagramIntent = new Intent(Intent.ACTION_SEND);
    instagramIntent.setType("image/*");
    instagramIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
    instagramIntent.setPackage("com.twitter.android");
    try {
      activity.startActivity(instagramIntent);
      result.success("Success");
    } catch (ActivityNotFoundException e) {
      e.printStackTrace();
      result.success("Failure");
    }
  }

  private void shareFacebook(String imagePath, MethodChannel.Result result) {
    try {
      SharePhoto photo = new SharePhoto.Builder()
              .setBitmap(BitmapFactory.decodeFile(imagePath))
              .build();
      SharePhotoContent content = new SharePhotoContent.Builder()
              .addPhoto(photo)
              .build();

      ShareDialog shareDialog = new ShareDialog(activity);
      shareDialog.show(content);

    } catch (ActivityNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void shareSystem(String imagePath, MethodChannel.Result result) {
    File file = new File(imagePath);
    Uri fileUri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);

    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    sharingIntent.setType("image/jpeg");
    sharingIntent.putExtra(Intent.EXTRA_STREAM, fileUri);

    Intent chooser = Intent.createChooser(sharingIntent, "Share image using");

    List<ResolveInfo> resInfoList = activity.getPackageManager().queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY);

    for (ResolveInfo resolveInfo : resInfoList) {
      String packageName = resolveInfo.activityInfo.packageName;
      activity.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }

    activity.startActivity(chooser);
  }

}

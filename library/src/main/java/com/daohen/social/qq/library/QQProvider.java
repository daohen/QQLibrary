package com.daohen.social.qq.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.daohen.personal.toolbox.library.Singleton;
import com.daohen.personal.toolbox.library.util.Strings;
import com.daohen.personal.toolbox.library.util.Toasts;
import com.daohen.social.qq.library.listener.DefaultLoginIUiListener;
import com.daohen.social.qq.library.listener.LoginIUiListener;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 22:53
 */
public class QQProvider {

    private Tencent tencent;
    private Context context;
    private String appid;
    private String appname;

    private DefaultLoginIUiListener defaultLoginIUiListener;
    private LoginIUiListener loginIUiListener;

    public static final QQProvider get(){
        return gDefault.get();
    }

    public void registerQQ(Context context, String appid, String appname){
        this.context = context;
        this.appid = appid;
        this.appname = appname;
        tencent = Tencent.createInstance(appid, context);
    }

    public Tencent getTencent(){
        return tencent;
    }

    public void login(Activity activity, LoginIUiListener listener){
        if (checkNull())
            return;

        this.loginIUiListener = listener;
        defaultLoginIUiListener = new DefaultLoginIUiListener(context, tencent, loginIUiListener);
        tencent.login(activity, "all", defaultLoginIUiListener);
    }

    public void logout(Context context){
        if (checkNull())
            return;

        tencent.logout(context);
    }

    public void onActivityResultData(int requestCode, int resultCode, Intent data){
        Tencent.onActivityResultData(requestCode, resultCode, data, defaultLoginIUiListener);
    }

    public void shareLocalImage(Activity activity, String path, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, path);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, appname);
        share(activity, bundle, listener, false);
    }

    public void shareDefault(Activity activity, String title, String summary, String targetUrl, String imageUrl, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, appname);
        share(activity, bundle, listener, false);
    }

    public void shareApp(Activity activity, String title, String summary, String targetUrl, String imageUrl, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, appname);
        share(activity, bundle, listener, false);
    }

    public void shareAudio(Activity activity, String title, String summary, String targetUrl, String imageUrl, String audioUrl, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, appname);
        bundle.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, audioUrl);
        share(activity, bundle, listener, false);
    }

    private void share(final Activity activity, final Bundle bundle, final IUiListener listener, final boolean isQzone){
        if (checkNull())
            return;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isQzone){
                    tencent.shareToQzone(activity, bundle, listener);
                } else {
                    tencent.shareToQQ(activity, bundle, listener);
                }
            }
        });
    }

    public void shareQzone(Activity activity, @NonNull String title, String summary, @NonNull String targetUrl, ArrayList<String> imageUrls, IUiListener listener){
        Bundle bundle = new Bundle();
        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);
        if (!Strings.isNull(summary))
            bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, summary);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        if (imageUrls != null && imageUrls.size() > 0)
            bundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        share(activity, bundle, listener, true);
    }

    private static final Singleton<QQProvider> gDefault = new Singleton<QQProvider>() {
        @Override
        protected QQProvider create() {
            return new QQProvider();
        }
    };

    private boolean checkNull(){
        if (tencent == null) {
            Toasts.show("调起QQ失败,请重启应用");
            return true;
        }
        return false;
    }

}

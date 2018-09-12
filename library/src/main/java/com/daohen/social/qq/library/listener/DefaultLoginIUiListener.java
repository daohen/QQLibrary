package com.daohen.social.qq.library.listener;


import android.content.Context;

import com.daohen.personal.toolbox.library.function.Callback;
import com.daohen.personal.toolbox.library.util.Booleans;
import com.daohen.personal.toolbox.library.util.Logs;
import com.daohen.social.qq.library.bean.LoginResponse;
import com.daohen.social.qq.library.bean.UserInfoWrap;
import com.daohen.thirdparty.library.gson.GsonFactory;
import com.google.gson.reflect.TypeToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:53
 */
public class DefaultLoginIUiListener implements IUiListener {

    private LoginIUiListener loginIUiListener;
    private Context context;
    private Tencent tencent;

    public DefaultLoginIUiListener(Context context, Tencent tencent, LoginIUiListener loginIUiListener){
        this.loginIUiListener = loginIUiListener;
        this.context = context;
        this.tencent = tencent;
    }

    @Override
    public void onComplete(Object o) {
        JSONObject jsonObject = (JSONObject) o;
        if (!Booleans.isRelease()){
            Logs.d(DefaultLoginIUiListener.class.getSimpleName() + "=" + jsonObject.toString());
        }
        Type type = new TypeToken<LoginResponse>(){}.getType();
        LoginResponse response = GsonFactory.getDefault().fromJson(jsonObject.toString(), type);

        tencent.setOpenId(response.getOpenid());
        tencent.setAccessToken(response.getAccessToken(), response.getExpiresIn());
        loginIUiListener.setLoginResponse(response);

        final UserInfoWrap userInfo = new UserInfoWrap(context, tencent.getQQToken());
        userInfo.getUnionId(new DefaultUnionIdIUiListener(loginIUiListener, new Callback() {
            @Override
            public void run() {
                userInfo.getUserInfo(loginIUiListener);
            }
        }));
    }

    @Override
    public void onError(UiError uiError) {
        if (!Booleans.isRelease()){
            Logs.d(DefaultLoginIUiListener.class.getSimpleName() + "=" + GsonFactory.getDefault().toJson(uiError));
        }
        loginIUiListener.onError(uiError);
    }

    @Override
    public void onCancel() {
        if (!Booleans.isRelease()){
            Logs.d(DefaultLoginIUiListener.class.getSimpleName() + " onCancel");
        }
    }

}

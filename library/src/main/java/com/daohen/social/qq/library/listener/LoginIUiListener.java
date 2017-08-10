package com.daohen.social.qq.library.listener;


import com.daohen.personal.toolbox.library.util.Booleans;
import com.daohen.personal.toolbox.library.util.Logs;
import com.daohen.social.qq.library.bean.LoginResponse;
import com.daohen.thirdparty.library.gson.GsonFactory;
import com.google.gson.reflect.TypeToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:53
 */
public abstract class LoginIUiListener implements IUiListener {

    public abstract void onSuccess(LoginResponse response);

    public abstract void onFail(UiError uiError);

    @Override
    public void onComplete(Object o) {
        JSONObject jsonObject = (JSONObject) o;
        if (!Booleans.isRelease()){
            Logs.d(LoginIUiListener.class.getSimpleName() + "=" + jsonObject.toString());
        }
        Type type = new TypeToken<LoginResponse>(){}.getType();
        LoginResponse response = GsonFactory.getDefault().fromJson(jsonObject.toString(), type);
        onSuccess(response);
    }

    @Override
    public void onError(UiError uiError) {
        if (!Booleans.isRelease()){
            Logs.d(LoginIUiListener.class.getSimpleName() + "=" + GsonFactory.getDefault().toJson(uiError));
        }
        onFail(uiError);
    }

    @Override
    public void onCancel() {
        if (!Booleans.isRelease()){
            Logs.d(LoginIUiListener.class.getSimpleName() + " onCancel");
        }
    }

}
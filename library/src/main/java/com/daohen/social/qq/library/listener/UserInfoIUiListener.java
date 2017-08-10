package com.daohen.social.qq.library.listener;

import com.daohen.personal.toolbox.library.util.Booleans;
import com.daohen.personal.toolbox.library.util.Logs;
import com.daohen.social.qq.library.bean.UserInfoResponse;
import com.daohen.thirdparty.library.gson.GsonFactory;
import com.google.gson.reflect.TypeToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by alun on 17/7/18.
 */

public abstract class UserInfoIUiListener implements IUiListener {

    public abstract void onSuccess(UserInfoResponse response);

    public abstract void onFail(UiError uiError);

    @Override
    public void onComplete(Object o) {
        JSONObject jsonObject = (JSONObject) o;
        if (!Booleans.isRelease()){
            Logs.d(UserInfoIUiListener.class.getSimpleName() + "=" + jsonObject.toString());
        }
        Type type = new TypeToken<UserInfoResponse>(){}.getType();
        UserInfoResponse response = GsonFactory.getDefault().fromJson(jsonObject.toString(), type);
        onSuccess(response);
    }

    @Override
    public void onError(UiError uiError) {
        if (!Booleans.isRelease()){
            Logs.d(UserInfoIUiListener.class.getSimpleName() + "=" + GsonFactory.getDefault().toJson(uiError));
        }
        onFail(uiError);
    }

    @Override
    public void onCancel() {
        if (!Booleans.isRelease()){
            Logs.d(UserInfoIUiListener.class.getSimpleName() + " onCancel");
        }
    }
}

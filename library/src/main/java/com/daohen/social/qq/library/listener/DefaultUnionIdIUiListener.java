package com.daohen.social.qq.library.listener;

import com.daohen.personal.toolbox.library.function.Callback;
import com.daohen.personal.toolbox.library.util.Booleans;
import com.daohen.personal.toolbox.library.util.Logs;
import com.daohen.social.qq.library.bean.UnionIdResponse;
import com.daohen.thirdparty.library.gson.GsonFactory;
import com.google.gson.reflect.TypeToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2018/09/12 10:44
 */
public class DefaultUnionIdIUiListener implements IUiListener {

    private LoginIUiListener loginIUiListener;
    private Callback callback;

    public DefaultUnionIdIUiListener(LoginIUiListener loginIUiListener, Callback callback){
        this.loginIUiListener = loginIUiListener;
        this.callback = callback;
    }

    @Override
    public void onComplete(Object o) {
        JSONObject jsonObject = (JSONObject) o;
        if (!Booleans.isRelease()){
            Logs.d(DefaultUnionIdIUiListener.class.getSimpleName() + "=" + jsonObject.toString());
        }

        Type type = new TypeToken<UnionIdResponse>(){}.getType();
        UnionIdResponse response = GsonFactory.getDefault().fromJson(jsonObject.toString(), type);

        if (loginIUiListener != null && response != null)
            loginIUiListener.setUnionId(response.getUnionid());

        if (callback != null)
            callback.run();
    }

    @Override
    public void onError(UiError uiError) {
        if (!Booleans.isRelease()){
            Logs.d(DefaultUnionIdIUiListener.class.getSimpleName() + "=" + GsonFactory.getDefault().toJson(uiError));
        }
        loginIUiListener.onError(uiError);
    }

    @Override
    public void onCancel() {
        if (!Booleans.isRelease()){
            Logs.d(DefaultUnionIdIUiListener.class.getSimpleName() + " onCancel");
        }
    }
}

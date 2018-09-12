package com.daohen.social.qq.library.bean;

import android.content.Context;
import android.os.Bundle;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.d;
import com.tencent.tauth.IUiListener;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2018/09/12 10:40
 */
public class UserInfoWrap extends UserInfo {

    public UserInfoWrap(Context context, QQToken qqToken) {
        super(context, qqToken);
    }

    public void getUnionId(IUiListener var1) {
        Bundle var2 = this.a();
        var2.putString("unionid", "1");
        TempRequestListener var3 = new TempRequestListener(var1);
        HttpUtils.requestAsync(this.b, d.a(), "oauth2.0/me", var2, "GET", var3);
    }
}

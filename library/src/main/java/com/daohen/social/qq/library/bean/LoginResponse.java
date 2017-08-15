package com.daohen.social.qq.library.bean;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/07/17 23:58
 */
public class LoginResponse {

    private int ret;
    private String pay_token;
    private String pf;
    private String expires_in;
    private String openid;
    private String pfkey;
    private String msg;
    private String access_token;

    public int getRet() {
        return ret;
    }

    public String getPayToken() {
        return pay_token;
    }

    public String getPf() {
        return pf;
    }

    public String getExpiresIn() {
        return expires_in;
    }

    public String getOpenid() {
        return openid;
    }

    public String getPfkey() {
        return pfkey;
    }

    public String getMsg() {
        return msg;
    }

    public String getAccessToken() {
        return access_token;
    }

}

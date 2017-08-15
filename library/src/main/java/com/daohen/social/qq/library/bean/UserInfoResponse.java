package com.daohen.social.qq.library.bean;

/**
 * Created by alun on 17/7/18.
 */

public class UserInfoResponse {

    private String is_yellow_year_vip;
    private int ret;
    private String figureurl_qq_1;
    private String figureurl_qq_2;
    private String nickname;
    private String yellow_vip_level;
    private String msg;
    private String figureurl_1;
    private String vip;
    private String level;
    private String figureurl_2;
    private String is_yellow_vip;
    private String gender;
    private String figureurl;
    private int is_lost;
    private String province;
    private String city;

    private String pay_token;
    private String pf;
    private String expires_in;
    private String openid;
    private String pfkey;
    private String access_token;

    public void setLoginResponse(LoginResponse response) {
        this.pay_token = response.getPayToken();
        this.pf = response.getPf();
        this.expires_in = response.getExpiresIn();
        this.openid = response.getOpenid();
        this.pfkey = response.getPfkey();
        this.access_token = response.getAccessToken();
    }



    public String getIs_yellow_year_vip() {
        return is_yellow_year_vip;
    }

    public int getRet() {
        return ret;
    }

    public String getFigureurl_qq_1() {
        return figureurl_qq_1;
    }

    public String getFigureurl_qq_2() {
        return figureurl_qq_2;
    }

    public String getNickname() {
        return nickname;
    }

    public String getYellow_vip_level() {
        return yellow_vip_level;
    }

    public String getMsg() {
        return msg;
    }

    public String getFigureurl_1() {
        return figureurl_1;
    }

    public String getVip() {
        return vip;
    }

    public String getLevel() {
        return level;
    }

    public String getFigureurl_2() {
        return figureurl_2;
    }

    public String getIs_yellow_vip() {
        return is_yellow_vip;
    }

    public String getGender() {
        return gender;
    }

    public String getFigureurl() {
        return figureurl;
    }

    public int getIs_lost() {
        return is_lost;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getPay_token() {
        return pay_token;
    }

    public String getPf() {
        return pf;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getOpenid() {
        return openid;
    }

    public String getPfkey() {
        return pfkey;
    }

    public String getAccess_token() {
        return access_token;
    }
}

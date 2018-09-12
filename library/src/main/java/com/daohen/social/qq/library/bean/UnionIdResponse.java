package com.daohen.social.qq.library.bean;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATE : 2018/09/12 10:54
 */
public class UnionIdResponse {

    public boolean isValid(){
        return nameValuePairs != null;
    }

    private UnionIdEntity nameValuePairs;

    public UnionIdEntity getNameValuePairs() {
        return nameValuePairs;
    }

    public static class UnionIdEntity{
        private String client_id;
        private String openid;
        private String unionid;

        public String getUnionid() {
            return unionid;
        }
    }

}

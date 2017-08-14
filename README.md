# socialLibrary
qq分享、登陆相关代码的封装

# 使用方法
1.gradle
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile 'com.github.daohen:socialLibrary:qqsocial-xx'
}
```

2.不要忘记在AndroidManifest.xml中添加以下Activity
```xml
<activity
    android:name="com.tencent.tauth.AuthActivity"
    android:noHistory="true"
    android:launchMode="singleTask" >
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="tencent你的AppId" />
    </intent-filter>
</activity>
```
以上就配置好了，下面我们就可以调用QQProvider的方法来进行业务处理，所有操作都已经封装在QQProvider中

在做登录操作时，要注意，需要在页面的onActivityResult方法里面调用以下代码
```java
if (requestCode == Constants.REQUEST_LOGIN){
    QQProvider.get().onActivityResultData(requestCode, resultCode, data, loginIUiListener);
}
```


代码混淆
```
-keep class com.tencent.** {*;}
```
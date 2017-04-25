package un.app1.appConfig;

import android.content.Context;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

@SuppressWarnings({"ConstantConditions", "unused"})
public class AppData {

    private Preference<String> message;
    private Preference<String> statusCode;
    private Preference<String> statusLogin;
    private Preference<String> token;
    private Preference<String> fcmId;

    private Preference<Boolean> isSetUp;
    private Preference<Boolean> isLogin;

    public Preference<String> setBannerShow;
    private Preference<String> setUserPreviewShow;

    public AppData(Context context) {
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(PreferenceManager.getDefaultSharedPreferences(context));

        message = rxPreferences.getString("message");
        statusCode = rxPreferences.getString("statusCode");
        statusLogin = rxPreferences.getString("statusLogin");
        token = rxPreferences.getString("token");
        fcmId = rxPreferences.getString("fcmId");

        isSetUp = rxPreferences.getBoolean("isSetUp", false);
        isLogin = rxPreferences.getBoolean("isLogin", false);

        setBannerShow = rxPreferences.getString("setBannerShow");
        setUserPreviewShow = rxPreferences.getString("setUserPreviewShow");

    }


    public boolean isLogin() {
        return getIsLogin();
    }

    public void defaultConfig() {
        if (!getIsSetUp()) {
            isSetUp.set(true);
        }
    }

    public void clearUserData() {
        isLogin.delete();
        token.delete();
        message.delete();
        statusCode.delete();
        statusLogin.delete();
        token.delete();
    }

    public void clearData() {
        setBannerShow.delete();
        setUserPreviewShow.delete();
    }


    public String getFcmId() {
        return fcmId.get();
    }

    public void setFcmId(String value) {
        fcmId.set(value);
    }

    public String getUserPreviewShow() {
        return setUserPreviewShow.get();
    }

    public void setUserPreviewShow(String value) {
        setUserPreviewShow.set(value);
    }

    public String getBannerShow() {
        return setBannerShow.get();
    }

    public void setBannerShow(String value) {
        setBannerShow.set(value);
    }

    public String getMessage() {
        return message.get();
    }

    public void setMessage(String value) {
        message.set(value);
    }

    public String getStatusCode() {
        return statusCode.get();
    }

    public void setStatusCode(String value) {
        statusCode.set(value);
    }

    public String getStatusLogin() {
        return statusLogin.get();
    }

    public void setStatusLogin(String value) {
        statusLogin.set(value);
    }

    public String getToken() {
        return token.get();
    }

    public void setToken(String value) {
        token.set(value);
    }

    private boolean getIsSetUp() {
        return isSetUp.get();
    }

    public void setIsSetUp(Boolean value) {
        isSetUp.set(value);
    }

    private boolean getIsLogin() {
        return isLogin.isSet();
    }

    public void setIsLogin(Boolean value) {
        isLogin.set(value);
    }
}

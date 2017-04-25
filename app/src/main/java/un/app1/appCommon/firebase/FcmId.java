package un.app1.appCommon.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.appConfig.AppData;

public class FcmId extends FirebaseInstanceIdService {

    @Inject
    AppData appData;

    @Override
    public void onCreate() {
        super.onCreate();
        ((MainApp) getApplication()).providesAppComponents().inject(this);
    }

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        if(appData.getFcmId() == null){
            appData.setFcmId(FirebaseInstanceId.getInstance().getToken());
        } else if(!appData.getFcmId().equalsIgnoreCase(token)) {
            appData.setFcmId(FirebaseInstanceId.getInstance().getToken());
        }
    }

    @SuppressWarnings("unused")
    private void sendRegistrationToServer(String token) {

    }

}
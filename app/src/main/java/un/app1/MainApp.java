package un.app1;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import un.app1.appDeps.AdapterModule;
import un.app1.appDeps.ApiModule;
import un.app1.appDeps.AppComponents;
import un.app1.appDeps.AppModule;
import un.app1.appDeps.DaggerAppComponents;
import un.app1.appNetwork.internet.ConnectivityReceiver;

public class MainApp extends Application {

    private static MainApp mainApp;
    private AppComponents appComponents;

    public static synchronized MainApp getInstance() {
        return mainApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //LeakCanary.install(MainApp.this);
        mainApp = MainApp.this;
        appComponents = DaggerAppComponents.builder()
                .appModule(new AppModule(mainApp))
                .apiModule(new ApiModule())
                .adapterModule(new AdapterModule())
                .build();
    }

    public AppComponents providesAppComponents() {
        return appComponents;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(new ConnectivityReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}

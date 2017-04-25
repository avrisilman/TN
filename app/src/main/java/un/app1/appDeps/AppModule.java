package un.app1.appDeps;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import un.app1.appConfig.AppData;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.pageModule.appHome.HomePresenter;
import un.app1.pageModule.pageForgot.ForgotPasswordPresenter;
import un.app1.pageModule.pageListrik.ListrikPresenter;
import un.app1.pageModule.pageListrik.historylistrik.HistoryListrikPresenter;
import un.app1.pageModule.pageLogin.LoginPresenter;
import un.app1.pageModule.pageNotif.NotifPresenter;
import un.app1.pageModule.pageProfile.ProfilePresenter;
import un.app1.pageModule.pagePulsa.PulsaPresenter;
import un.app1.pageModule.pageRegister.RegisterPresenter;
import un.app1.pageModule.pageSplash.SplashPresenter;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public ConnectivityReceiver providesConnectivityReceiver() {
        return new ConnectivityReceiver();
    }

    @Provides
    @Singleton
    public AppData providesAppData(Context context) {
        return new AppData(context);
    }

    //---PRESENTER---

    @Provides
    @Singleton
    public NotifPresenter providesNotifPresenter(Context context) {
        return new NotifPresenter(context);
    }

    @Provides
    @Singleton
    public ProfilePresenter providesProfilePresenter(Context context) {
        return new ProfilePresenter(context);
    }

    @Provides
    @Singleton
    public ForgotPasswordPresenter providesForgotPasswordPresenter(Context context) {
        return new ForgotPasswordPresenter(context);
    }

    @Provides
    @Singleton
    public PulsaPresenter providesPulsaPresenter(Context context) {
        return new PulsaPresenter(context);
    }

    @Provides
    @Singleton
    public RegisterPresenter providesRegisterPresenter(Context context) {
        return new RegisterPresenter(context);
    }

    @Provides
    @Singleton
    public HomePresenter providesHomePresenter(Context context) {
        return new HomePresenter(context);
    }

    @Provides
    @Singleton
    public LoginPresenter providesLoginPresenter(Context context) {
        return new LoginPresenter(context);
    }

    @Provides
    @Singleton
    public ListrikPresenter providesListrikPresenter(Context context) {
        return new ListrikPresenter(context);
    }

    @Provides
    @Singleton
    public HistoryListrikPresenter providesHistoryListrikPresenter(Context context) {
        return new HistoryListrikPresenter(context);
    }

    @Provides
    @Singleton
    public SplashPresenter providesSplashPresenter() {
        return new SplashPresenter();
    }

}

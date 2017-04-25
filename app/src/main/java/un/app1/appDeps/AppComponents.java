package un.app1.appDeps;

import javax.inject.Singleton;

import dagger.Component;
import un.app1.appCommon.firebase.FcmId;
import un.app1.appNetwork.service.MainService;
import un.app1.pageModule.appHome.ActivityHome;
import un.app1.pageModule.appHome.HomePresenter;
import un.app1.pageModule.pageForgot.ActivityForgotPassword;
import un.app1.pageModule.pageForgot.ForgotPasswordPresenter;
import un.app1.pageModule.pageListrik.ActivityListrik;
import un.app1.pageModule.pageListrik.ListrikPresenter;
import un.app1.pageModule.pageListrik.historylistrik.ActivityHistoryListrik;
import un.app1.pageModule.pageListrik.historylistrik.HistoryListrikPresenter;
import un.app1.pageModule.pageLogin.ActivityLogin;
import un.app1.pageModule.pageLogin.LoginPresenter;
import un.app1.pageModule.pageNotif.ActivityNotif;
import un.app1.pageModule.pageNotif.NotifPresenter;
import un.app1.pageModule.pageProfile.ActivityProfile;
import un.app1.pageModule.pageProfile.ProfilePresenter;
import un.app1.pageModule.pagePulsa.ActivityPulsa;
import un.app1.pageModule.pagePulsa.PulsaPresenter;
import un.app1.pageModule.pageRegister.ActivityRegister;
import un.app1.pageModule.pageRegister.RegisterPresenter;
import un.app1.pageModule.pageSplash.ActivitySplash;


@Singleton
@Component(modules = {
        AppModule.class,
        AdapterModule.class,
        ApiModule.class})

public interface AppComponents {

    void inject(HistoryListrikPresenter historyListrikPresenter);

    void inject(ListrikPresenter listrikPresenter);

    void inject(FcmId fcmId);

    void inject(ActivityNotif activityNotif);

    void inject(ActivityProfile activityProfile);

    void inject(ActivityForgotPassword activityForgotPassword);

    void inject(ActivityPulsa activityPulsa);

    void inject(ActivityRegister activityRegister);

    void inject(ActivityHome activityHome);

    void inject(ActivityLogin activitylogin);

    void inject(ActivityListrik activityListrik);

    void inject(ActivityHistoryListrik activityHistoryListrik);

    void inject(ActivitySplash activitySplash);

    void inject(MainService mainService);


    void inject(NotifPresenter notifPresenter);

    void inject(ProfilePresenter profilePresenter);

    void inject(ForgotPasswordPresenter forgotPasswordPresenter);

    void inject(PulsaPresenter pulsaPresenter);

    void inject(RegisterPresenter registerPresenter);

    void inject(HomePresenter homePresenter);

    void inject(LoginPresenter loginPresenter);


}

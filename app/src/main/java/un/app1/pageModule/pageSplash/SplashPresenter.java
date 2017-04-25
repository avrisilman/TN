package un.app1.pageModule.pageSplash;

public class SplashPresenter {

    boolean splash = false;

    SplashView splashView;

    void setSplashPresenter(SplashView splashView) {
        this.splashView = splashView;
    }

    void checkFirstRun() {
        if (splash) {
            splashView.checkFirstRun();
        } else {
            splashView.runSplash();
        }
    }

}

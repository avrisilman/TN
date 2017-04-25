package un.app1.pageModule.pageSplash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.stephentuso.welcome.WelcomeHelper;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import un.app1.MainApp;
import un.app1.R;
import un.app1.databinding.ActivitySplashBinding;
import un.app1.pageModule.appHome.ActivityHome;

public class ActivitySplash extends AppCompatActivity implements SplashView {

    @Inject
    SplashPresenter presenter;

    private static int WELCOME_SCREEN = 2;
    private Disposable disposable;
    private ActivitySplashBinding binding;
    private int counter = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivitySplash.this);
        binding = DataBindingUtil.setContentView(ActivitySplash.this, R.layout.activity_splash);
        presenter.setSplashPresenter(ActivitySplash.this);
        binding.setPresenter(presenter);
        disposable = Disposables.empty();
        presenter.checkFirstRun();

    }

    private void splashFirstRun() {
        new WelcomeHelper(ActivitySplash.this, AppWelcome.class).forceShow(WELCOME_SCREEN);
    }

    public void startTimer() {
        disposable = Observable.range(0, counter)
                .flatMap(v -> Observable.just(v).delay(counter - v, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(this::justDo)
                .subscribe(tick -> { });
    }

    private void justDo() {
        startActivity(new Intent(ActivitySplash.this, ActivityHome.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WELCOME_SCREEN) {
            if (resultCode == RESULT_OK) {

            } else if (resultCode == RESULT_CANCELED) {
                disposable.dispose();
                this.finish();
            }
        }
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }

    @Override
    public void checkFirstRun() {
        splashFirstRun();
    }

    @Override
    public void runSplash() {
        startTimer();
    }

    @Override
    public void onBackPressed() {
        disposable.dispose();
        super.onBackPressed();
    }

}



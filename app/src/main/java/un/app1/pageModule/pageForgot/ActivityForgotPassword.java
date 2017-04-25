package un.app1.pageModule.pageForgot;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityForgotPasswordBinding;

public class ActivityForgotPassword extends AppCompatActivity implements ForgotPasswordView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    ForgotPasswordPresenter presenter;

    ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityForgotPassword.this);
        MainApp.getInstance().setConnectivityListener(ActivityForgotPassword.this);
        binding = DataBindingUtil.setContentView(ActivityForgotPassword.this, R.layout.activity_forgot_password);
        presenter.setForgotPasswordPresenter(ActivityForgotPassword.this, binding);
        binding.setPresenter(presenter);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        presenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        presenter.checkInternet(isConnected);
    }

    @Override
    public void onClose() {
        this.finish();
    }
}

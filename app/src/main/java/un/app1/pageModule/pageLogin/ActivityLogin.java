package un.app1.pageModule.pageLogin;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.appCommon.common.EBus;
import un.app1.appCommon.event.EventLoginSuccess;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityLoginBinding;

public class ActivityLogin extends AppCompatActivity implements LoginView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    LoginPresenter presenter;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityLogin.this);
        MainApp.getInstance().setConnectivityListener(ActivityLogin.this);

        binding = DataBindingUtil.setContentView(ActivityLogin.this, R.layout.activity_login);
        presenter.setLoginPresenter(ActivityLogin.this, binding);
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
    public void onNetworkConnectionChanged(boolean isConnected) {
        presenter.checkInternet(isConnected);
    }

    @Override
    public void onResult() {
        EBus.post(new EventLoginSuccess());
        CommonUtils.hideSoftKeyboard(this);
        this.finish();
    }

    @Override
    public void onClose() {
        this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

package un.app1.pageModule.pageRegister;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityRegisterBinding;

public class ActivityRegister extends AppCompatActivity implements RegisterView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    RegisterPresenter presenter;

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityRegister.this);
        MainApp.getInstance().setConnectivityListener(ActivityRegister.this);

        binding = DataBindingUtil.setContentView(ActivityRegister.this, R.layout.activity_register);
        presenter.setRegisterPresenter(ActivityRegister.this, binding);
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClose() {
        this.finish();
    }

}


package un.app1.pageModule.pageNotif;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityNotifBinding;

public class ActivityNotif extends AppCompatActivity implements NotifView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    NotifPresenter presenter;

    ActivityNotifBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityNotif.this);
        MainApp.getInstance().setConnectivityListener(ActivityNotif.this);
        binding = DataBindingUtil.setContentView(ActivityNotif.this, R.layout.activity_notif);
        presenter.setNotifPresenter(ActivityNotif.this, binding);
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

}


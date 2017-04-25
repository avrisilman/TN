package un.app1.pageModule.pageProfile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityProfileBinding;

public class ActivityProfile extends AppCompatActivity implements ProfileView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    ProfilePresenter presenter;

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityProfile.this);
        MainApp.getInstance().setConnectivityListener(ActivityProfile.this);
        binding = DataBindingUtil.setContentView(ActivityProfile.this, R.layout.activity_profile);
        presenter.setProfilePresenter(ActivityProfile.this, binding);
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


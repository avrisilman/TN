package un.app1.pageModule.pagePulsa;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityPulsaBinding;
import un.app1.pageModule.pagePulsa.custom.CustomWatcher;

public class ActivityPulsa extends AppCompatActivity implements PulsaView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    PulsaPresenter presenter;

    ActivityPulsaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityPulsa.this);
        MainApp.getInstance().setConnectivityListener(ActivityPulsa.this);
        binding = DataBindingUtil.setContentView(ActivityPulsa.this, R.layout.activity_pulsa);
        presenter.setPulsaPresenter(ActivityPulsa.this, binding);
        binding.setPresenter(presenter);

        CommonUtils.setFont(binding.coordinatorLayout, CommonUtils.typeface(ActivityPulsa.this));
        binding.inputNumber.addTextChangedListener(new CustomWatcher(ActivityPulsa.this));

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
    public void onCloseActivity() {
        this.finish();
    }

    @Override
    public void goToAddFrom() {

    }

    @Override
    public void ifDenomFailed() {

    }

    @Override
    public void onClickBuy() {

    }

    @Override
    public void checkDenom() {
        presenter.checkDenomPulsa();
    }

}

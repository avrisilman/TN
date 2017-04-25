package un.app1.pageModule.pageListrik;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityListrikBinding;
import un.app1.pageModule.pageListrik.adapter.ListrikAdapter;
import un.app1.pageModule.pageListrik.custom.CustomWatcher;
import un.app1.pageModule.pageListrik.historylistrik.ActivityHistoryListrik;
import un.app1.pageModule.pageListrik.model.DenomListrik;

public class ActivityListrik extends AppCompatActivity implements ListrikView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    ListrikAdapter listrikAdapter;

    @Inject
    ListrikPresenter presenter;

    ActivityListrikBinding binding;

    DenomListrik denomListrik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityListrik.this);
        MainApp.getInstance().setConnectivityListener(ActivityListrik.this);

        binding = DataBindingUtil.setContentView(ActivityListrik.this, R.layout.activity_listrik);
        presenter.setListrikPresenter(ActivityListrik.this, binding);
        binding.setPresenter(presenter);

        CommonUtils.setFont(binding.coordinatorLayout, CommonUtils.typeface(ActivityListrik.this));
        binding.inputNumber.addTextChangedListener(new CustomWatcher(ActivityListrik.this));

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
    public void onCloseActivity() {
        presenter.unSubscribe();
        finish();
    }

    @Override
    public void onClickBuy() {
        presenter.goToActivity();
    }

    @Override
    public void enableClickBuy(DenomListrik denomListrik) {
        this.denomListrik = denomListrik;
        presenter.createSON(binding.inputNumber.getText().toString(), denomListrik.denom, denomListrik.adminFee, denomListrik.kwh);
        presenter.isShownLayoutBuy(binding.layoutBuy);
    }

    @Override
    public void checkDenom() {
        presenter.checkDenom();
    }

    @Override
    public void ifDenomFailed() {
        CommonUtils.animFadeOut(binding.layoutErrorDenom, 500);
        presenter.getTokenListrik();
    }

    @Override
    public void goToHistory() {
        startActivity(new Intent(ActivityListrik.this, ActivityHistoryListrik.class));
    }

    @Override
    public void goToAddFrom() {

    }

    @Override
    public void onBackPressed() {
        presenter.unSubscribe();
        finish();
    }

}
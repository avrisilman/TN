package un.app1.pageModule.pageListrik.historylistrik;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Window;

import java.util.List;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityHistoryListrikBinding;
import un.app1.pageModule.pageListrik.adapter.HistoryAdapter;
import un.app1.pageModule.pageListrik.model.HistoryPayment;

public class ActivityHistoryListrik extends AppCompatActivity implements HistoryListrikView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    HistoryListrikPresenter presenter;

    @Inject
    HistoryAdapter adapter;

    ActivityHistoryListrikBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityHistoryListrik.this);
        MainApp.getInstance().setConnectivityListener(ActivityHistoryListrik.this);

        binding = DataBindingUtil.setContentView(ActivityHistoryListrik.this, R.layout.activity_history_listrik);
        presenter.setHistoryListrikPresenter(ActivityHistoryListrik.this, binding);
        binding.setPresenter(presenter);

        CommonUtils.setFont(binding.coordinatorLayout, CommonUtils.typeface(ActivityHistoryListrik.this));

    }

    @Override
    public void animFadeOutArcLoader() {
        CommonUtils.animFadeOut(binding.arcLoader, 500);
    }

    @Override
    public void animFadeInArcLoader() {
        CommonUtils.animFadeIn(binding.arcLoader, 500);
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

    }

    @Override
    public void onBackPressed() {
        presenter.unSubscribe();
        super.onBackPressed();
    }

    @Override
    public void onCloseActivity() {
        this.finish();
    }

    @Override
    public void toAdapter(List<HistoryPayment> historyPayments) {
        adapter.setFrom(ActivityHistoryListrik.this);
        adapter.setHistoryPayments(historyPayments);
        binding.recyclerHistory.setLayoutManager(new LinearLayoutManager(ActivityHistoryListrik.this));
        binding.recyclerHistory.setAdapter(adapter);
        CommonUtils.animFadeIn(binding.recyclerHistory, 500);
    }

}

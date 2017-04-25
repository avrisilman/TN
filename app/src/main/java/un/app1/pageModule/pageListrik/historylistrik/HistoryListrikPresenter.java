package un.app1.pageModule.pageListrik.historylistrik;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.appConfig.AppData;
import un.app1.appNetwork.service.MainService;
import un.app1.appNetwork.service.MyCallBack;
import un.app1.databinding.ActivityHistoryListrikBinding;
import un.app1.pageModule.pageListrik.model.History;
import un.app1.pageModule.pageListrik.model.SubmitTHistoryListrik;

public class HistoryListrikPresenter {

    @Inject
    MainService mainService;

    @Inject
    AppData appData;

    private HistoryListrikView historyListrikView;
    private Disposable disposable;
    private Context context;
    private ActivityHistoryListrikBinding binding;

    public HistoryListrikPresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(HistoryListrikPresenter.this);
    }

    void setHistoryListrikPresenter(Context context, ActivityHistoryListrikBinding binding) {
        this.context = context;
        this.historyListrikView = ((HistoryListrikView) context);
        this.binding = binding;
        disposable = Disposables.empty();

        getHistoryListrik(new SubmitTHistoryListrik("", ""));
        setImageBack();
    }

    void unSubscribe() {
        disposable.dispose();
    }

    private void setImageBack() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_backburger);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageBack.setImageDrawable(icon);
    }

    private void getHistoryListrik(SubmitTHistoryListrik submitTHistoryListrik) {
        historyListrikView.animFadeInArcLoader();
        disposable = mainService.requestHistoryListrik("", "", new MyCallBack.CallHistoryListrik() {

            @Override
            public void onStartRequest() {
                CommonUtils.toast(context, "onStartRequest");
            }

            @Override
            public void onNotFound() {
                CommonUtils.toast(context, "onNotFound");
            }

            @Override
            public void onUnauthorized() {
                CommonUtils.toast(context, context.getResources().getString(R.string.strUnauthorized));
            }

            @Override
            public void onError() {
                CommonUtils.toast(context, "onError");
            }

            @Override
            public void onSuccess(History history) {
                historyListrikView.animFadeOutArcLoader();
                historyListrikView.toAdapter(history.historyPayment);
            }
        });
    }

    public void onCloseActivity() {
        historyListrikView.onCloseActivity();
    }

}

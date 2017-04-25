package un.app1.pageModule.pagePulsa;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.appConfig.AppData;
import un.app1.appNetwork.service.MainService;
import un.app1.appNetwork.service.MyCallBack;
import un.app1.databinding.ActivityPulsaBinding;
import un.app1.pageModule.pagePulsa.model.DenomPulsa;
import un.app1.pageModule.pagePulsa.model.Pulsa;
import un.app1.pageModule.pagePulsa.pulsaAdapter.PulsaAdapter;

public class PulsaPresenter {

    @Inject
    MainService mainService;

    @Inject
    AppData appData;

    @Inject
    PulsaAdapter pulsaAdapter;

    private Context context;
    private ActivityPulsaBinding binding;
    private PulsaView pulsaView;
    private Disposable disposable;
    private List<DenomPulsa> denomPulsas = null;

    public PulsaPresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(PulsaPresenter.this);
    }

    void setPulsaPresenter(Context context, ActivityPulsaBinding binding) {
        this.pulsaView = ((PulsaView) context);
        this.context = context;
        this.binding = binding;
        this.disposable = Disposables.empty();

        setImageBack();
        setImageList();
        setImageHistory();
    }

    void checkDenomPulsa() {
        if (denomPulsas == null) {
            getDenomPulsa();
        }
    }

    private void getDenomPulsa() {
        disposable = mainService.requestDenomPulsa("deviceId", appData.getToken(), new MyCallBack.CallDenomPulsa() {

            @Override
            public void onStartRequest() {
                CommonUtils.animFadeIn(binding.layoutLoader, 500);
                CommonUtils.hideSoftKeyboard(context);
            }

            @Override
            public void onNotFound() {
                CommonUtils.animFadeOut(binding.layoutLoader, 500);
                CommonUtils.toast(context, "onNotFound");
            }

            @Override
            public void onUnauthorized() {
                CommonUtils.animFadeOut(binding.layoutLoader, 500);
                CommonUtils.toast(context, context.getResources().getString(R.string.strUnauthorized));
            }

            @Override
            public void onError() {
                CommonUtils.animFadeOut(binding.layoutLoader, 500);
                CommonUtils.toast(context, "onError");
            }

            @Override
            public void onSuccess(Pulsa pulsa) {
                denomPulsas = pulsa.denomPulsa;
                pulsaAdapter.setViewData(pulsaView);
                pulsaAdapter.setFrom(context);
                pulsaAdapter.setDenomPulsa(pulsa.denomPulsa);
                pulsaAdapter.setUnCheckedFirst(true);
                binding.recyclerDenomListrik.setLayoutManager(new GridLayoutManager(context, 2));
                binding.recyclerDenomListrik.setAdapter(pulsaAdapter);
                CommonUtils.animFadeOut(binding.layoutLoader, 500);
                CommonUtils.animFadeIn(binding.recyclerDenomListrik, 500);
            }

        });
    }

    void checkInternet(boolean status) {
        if (!status) {
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
            CommonUtils.snackBar(binding.coordinatorLayout, context);
        }
    }

    void unSubscribe() {
        if (disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void setImageBack() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_backburger);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageBack.setImageDrawable(icon);
    }

    private void setImageList() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_attachment);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageList.setImageDrawable(icon);
    }

    private void setImageHistory() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_history);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageHistory.setImageDrawable(icon);
    }

    public void onOpenHistory() {

    }

    public void onCloseActivity() {
        pulsaView.onCloseActivity();
    }

    public void onOpenAddFrom() {
        pulsaView.goToAddFrom();
    }

    public void onClicRetry() {
        pulsaView.ifDenomFailed();
    }

    public void onClickBuy() {
        pulsaView.onClickBuy();
    }

}

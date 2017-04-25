package un.app1.pageModule.pageListrik;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

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
import un.app1.databinding.ActivityListrikBinding;
import un.app1.pageModule.pageListrik.adapter.ListrikAdapter;
import un.app1.pageModule.pageListrik.model.DenomListrik;
import un.app1.pageModule.pageListrik.model.TokenListrik;

public class ListrikPresenter {

    @Inject
    MainService mainService;

    @Inject
    AppData appData;

    @Inject
    ListrikAdapter listrikAdapter;

    private ListrikView listrikView;
    private Disposable disposable;
    private Context context;
    private String json = null;
    private ActivityListrikBinding binding;
    private List<DenomListrik> denomListriks = null;

    public ListrikPresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(ListrikPresenter.this);
    }

    void setListrikPresenter(Context context, ActivityListrikBinding binding) {
        this.context = context;
        this.listrikView = ((ListrikView) context);
        this.binding = binding;
        disposable = Disposables.empty();

        setImageBack();
        setImageList();
        setImageHistory();
    }

    void goToActivity() {
        //context.startActivity(new Intent(context, ActivityHome.class));
        CommonUtils.toast(context, json);
    }

    void createSON(String idPelanggan, String denom, String adminFee, String kwh) {
        JSONObject jso = new JSONObject();
        try {
            jso.put("idPelanggan", idPelanggan);
            jso.put("denom", denom);
            jso.put("adminFee", adminFee);
            jso.put("kwh", kwh);
            jso.put("timeStamp", CommonUtils.timeStamp());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error", e.toString());
        }
        json = jso.toString();
    }

    void checkDenom() {
        if (denomListriks == null) {
            getTokenListrik();
        }
    }

    void isShownLayoutBuy(View view) {
        if (!view.isShown()) {
            CommonUtils.animFadeIn(view, 300);
            view.setEnabled(true);
        }
    }

    void getTokenListrik() {

        disposable = mainService.requestTokenListrik("", "", new MyCallBack.CallTokenListrik() {

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
            public void onSuccess(TokenListrik tokenListrik) {
                denomListriks = tokenListrik.denomListrik;
                listrikAdapter.setViewData(listrikView);
                listrikAdapter.setFrom(context);
                listrikAdapter.setDenomListrik(tokenListrik.denomListrik);
                listrikAdapter.setUnCheckedFirst(true);
                binding.recyclerDenomListrik.setLayoutManager(new GridLayoutManager(context, 2));
                binding.recyclerDenomListrik.setAdapter(listrikAdapter);
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

    public void onCloseActivity() {
        listrikView.onCloseActivity();
    }

    public void onOpenHistory() {
        listrikView.goToHistory();
    }

    public void onOpenAddFrom() {
        listrikView.goToAddFrom();
    }

    public void onClickBuy() {
        listrikView.onClickBuy();
    }

    public void onClicRetry() {
        listrikView.ifDenomFailed();
    }

}

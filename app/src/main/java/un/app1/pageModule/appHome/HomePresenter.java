package un.app1.pageModule.appHome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.indicators.IndicatorShape;
import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.appConfig.AppData;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.appNetwork.service.MainService;
import un.app1.appNetwork.service.MyCallBack;
import un.app1.databinding.ActivityHomeBinding;
import un.app1.pageModule.appHome.adapter.BannerSlideAdapter;
import un.app1.pageModule.appHome.adapter.ProductModel;
import un.app1.pageModule.appHome.model.ArrayBanner;
import un.app1.pageModule.appHome.model.Banner;
import un.app1.pageModule.appHome.model.MainMenuModel;
import un.app1.pageModule.appHome.model.QuickPreview;
import un.app1.pageModule.pageListrik.ActivityListrik;
import un.app1.pageModule.pageLogin.ActivityLogin;
import un.app1.pageModule.pageNotif.ActivityNotif;
import un.app1.pageModule.pageProfile.ActivityProfile;
import un.app1.pageModule.pagePulsa.ActivityPulsa;
import un.app1.pageModule.pageRegister.ActivityRegister;

public class HomePresenter {

    @Inject
    MainService mainService;

    @Inject
    BannerSlideAdapter adapter;

    @Inject
    AppData appData;

    private HomeView homeView;
    private Context context;
    private ActivityHomeBinding binding;

    private Disposable disposable;

    public HomePresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(HomePresenter.this);
    }

    void seHomePresenter(Context context, ActivityHomeBinding binding) {
        this.context = context;
        this.homeView = ((HomeView) context);
        this.binding = binding;
        this.disposable = Disposables.empty();
        getHomeBanner();
        Log.e("fcmId","fcmId -> "+  appData.getFcmId());
    }

    @SuppressWarnings("unused")
    ArrayList<ProductModel> getProduct() {
        ArrayList<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel(R.drawable.ic_menu_1, " "));
        productModels.add(new ProductModel(R.drawable.ic_listrik, " "));
        productModels.add(new ProductModel(R.drawable.ic_pulsa, " "));
        productModels.add(new ProductModel(R.drawable.ic_user, " "));
        productModels.add(new ProductModel(R.drawable.ic_menu_1, " "));
        return productModels;
    }

    ArrayList<MainMenuModel> getMainMenu() {
        ArrayList<MainMenuModel> mainMenuModel = new ArrayList<>();
        mainMenuModel.add(new MainMenuModel(context.getResources().getString(R.string.strTokenListrik)));
        mainMenuModel.add(new MainMenuModel(context.getResources().getString(R.string.strPulsa)));
        mainMenuModel.add(new MainMenuModel(context.getResources().getString(R.string.strVoucherGame)));
        mainMenuModel.add(new MainMenuModel(context.getResources().getString(R.string.strTiketKereta)));
        mainMenuModel.add(new MainMenuModel(context.getResources().getString(R.string.strHotel)));
        mainMenuModel.add(new MainMenuModel(context.getResources().getString(R.string.strTiketPesawat)));
        mainMenuModel.add(new MainMenuModel(context.getResources().getString(R.string.strBPJS)));
        mainMenuModel.add(new MainMenuModel(context.getResources().getString(R.string.strTopUpSaldo)));
        return mainMenuModel;
    }

    void isUserLogin() {
        if (ConnectivityReceiver.isConnected()) {
            if (appData.isLogin()) {
                getQuickPreview();
            } else {
                homeView.setLayoutSignOut();
            }
        }
    }

    void checkInternet(boolean status) {
        if (!status) {
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
            CommonUtils.animFadeOut(binding.arcLoader, 700);
            CommonUtils.snackBar(binding.coordinatorLayout, context);
        }
    }

    void toActivity(int position) {
        if (position == 0) {
            context.startActivity(new Intent(context, ActivityListrik.class));
        } else if (position == 1) {
            context.startActivity(new Intent(context, ActivityPulsa.class));
        } else if (position == 2) {
            context.startActivity(new Intent(context, ActivityListrik.class));
        } else if (position == 3) {
            context.startActivity(new Intent(context, ActivityListrik.class));
        } else if (position == 4) {
            context.startActivity(new Intent(context, ActivityListrik.class));
        } else if (position == 5) {
            context.startActivity(new Intent(context, ActivityListrik.class));
        } else if (position == 6) {
            context.startActivity(new Intent(context, ActivityListrik.class));
        } else if (position == 7) {
            context.startActivity(new Intent(context, ActivityListrik.class));
        }
    }

    void unSubscribe() {
        if (disposable.isDisposed()) {
            disposable.dispose();
            appData.setBannerShow.delete();
        }
    }

    void setImageLogout() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_logout);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageLogout.setImageDrawable(icon);
    }

    void setImageAccount() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_account);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imagePreference.setImageDrawable(icon);
    }

    void setImageNotificationAler() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_bullhorn);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageNotification.setImageDrawable(icon);
    }

    private void getBalance() {
        disposable.dispose();
        countDownTimer();
    }

    void getQuickPreview() {
        disposable = mainService.requestQuickPreview("deviceId", appData.getToken(), new MyCallBack.CallQuickPreview() {

            @Override
            public void onStartRequest() {
                homeView.setLayoutChecking();
                CommonUtils.animFadeIn(binding.layoutHomeDashboardChecking, 500);
            }

            @Override
            public void onNotFound() {
                CommonUtils.toast(context, "onNotFound");
            }

            @Override
            public void onUnauthorized() {
                CommonUtils.animFadeOut(binding.layoutHomeDashboardChecking, 700);
                CommonUtils.animFadeIn(binding.layoutHomeDashboardBlank, 700);
                homeView.setLayoutBlank();
                CommonUtils.toast(context, context.getResources().getString(R.string.strUnauthorized));
            }

            @Override
            public void onError() {
                CommonUtils.animFadeOut(binding.layoutHomeDashboardChecking, 700);
                CommonUtils.animFadeIn(binding.layoutHomeDashboardBlank, 700);
                homeView.setLayoutBlank();
            }

            @Override
            public void onSuccess(QuickPreview quickPreview) {
                appData.setUserPreviewShow(String.valueOf(Integer.parseInt(quickPreview.statusCode)));
                homeView.setLayoutSignIn();
                CommonUtils.animFadeIn(binding.layoutHomeDashboardSignIn, 700);
                homeView.setUserName(quickPreview.user);
                homeView.setUserImage(quickPreview.imageUrl);
                homeView.setShowUserImage();
                homeView.setBalance(quickPreview.balance);
                homeView.setLastLogin(quickPreview.lastLogin);
            }

        });
    }

    private void getHomeBanner() {
        if (ConnectivityReceiver.isConnected()) {
            disposable = mainService.requestBanner("deviceId", new MyCallBack.CallBanner() {

                @Override
                public void onStartRequest() {
                    CommonUtils.animFadeIn(binding.arcLoader, 700);
                }

                @Override
                public void onNotFound() {
                    CommonUtils.toast(context, "onNotFound");
                }

                @Override
                public void onUnauthorized() {
                    CommonUtils.animFadeOut(binding.arcLoader, 700);
                    CommonUtils.animFadeIn(binding.textRetry, 700);
                    CommonUtils.toast(context, context.getResources().getString(R.string.strUnauthorized));
                }

                @Override
                public void onError() {
                    CommonUtils.animFadeOut(binding.arcLoader, 700);
                    CommonUtils.animFadeIn(binding.textRetry, 700);
                    binding.textRetry.setText(context.getResources().getString(R.string.strRetry));
                }

                @Override
                public void onSuccess(Banner banner) {
                    CommonUtils.animFadeOut(binding.arcLoader, 700);
                    CommonUtils.animFadeIn(binding.bannerSlider, 700);
                    setBanner(banner.arrayBanners);
                }
            });
        }
    }

    private void countDownTimer() {
        binding.dotProgressBar.setVisibility(View.VISIBLE);
        disposable = Observable
                .range(0, 30)
                .flatMap(v -> Observable.just(v).delay(30 - v, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(this::getBalance)
                .subscribe(tick -> Log.e("un", "tick -> " + tick));
    }

    private void setBanner(List<ArrayBanner> arrayBanners) {
        appData.setBannerShow(String.valueOf(arrayBanners.size()));
        for (int i = 0; i < arrayBanners.size(); i++) {
            binding.bannerSlider.addBanner(new RemoteBanner(arrayBanners.get(i).url));
        }
        binding.bannerSlider.setCurrentSlide(2);
        binding.bannerSlider.setInterval(4000);
        binding.bannerSlider.setLoopSlides(true);
        binding.bannerSlider.setDefaultIndicator(IndicatorShape.CIRCLE);
        binding.bannerSlider.setMustAnimateIndicators(true);
        binding.bannerSlider.setHideIndicators(true);
    }

    public void onClickLogOut() {
        homeView.logOut();
    }

    @SuppressWarnings("unused")
    public void onClickAccount() {
        context.startActivity(new Intent(context, ActivityProfile.class));
    }

    public void onClickNotif() {
        context.startActivity(new Intent(context, ActivityNotif.class));
    }

    public void onClickRetry() {
        if (ConnectivityReceiver.isConnected()) {
            CommonUtils.animFadeOut(binding.textRetry, 500);
            getHomeBanner();
        } else {
            CommonUtils.snackBar(binding.coordinatorLayout, context);
        }
    }

    public void onClickLogin() {
        context.startActivity(new Intent(context, ActivityLogin.class));
    }

    public void onClickRegister() {
        context.startActivity(new Intent(context, ActivityRegister.class));
    }

}

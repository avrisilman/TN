package un.app1.pageModule.pageLogin;

import android.content.Context;
import android.content.Intent;
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
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.appNetwork.service.MainService;
import un.app1.appNetwork.service.MyCallBack;
import un.app1.databinding.ActivityLoginBinding;
import un.app1.pageModule.pageForgot.ActivityForgotPassword;
import un.app1.pageModule.pageLogin.model.DataLogin;

public class LoginPresenter {

    @Inject
    MainService mainService;

    @Inject
    AppData appData;

    private Disposable disposable;

    private LoginView loginView;
    private ActivityLoginBinding binding;
    private Context context;

    public LoginPresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(LoginPresenter.this);
    }

    void setLoginPresenter(Context context, ActivityLoginBinding binding) {
        this.loginView = ((LoginView) context);
        this.context = context;
        this.binding = binding;
        this.disposable = Disposables.empty();

        setImageEmail();
        setImageUnlock();
        setImageClose();
    }

    void checkInternet(boolean status) {
        if (!status) {
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
            CommonUtils.animFadeOut(binding.arcLoader, 500);
            CommonUtils.snackBar(binding.coordinatorLayout, context);
        }
    }

    void unSubscribe() {
        if (disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void setImageEmail() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_email_outline);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageEmail.setImageDrawable(icon);
    }

    private void setImageUnlock() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_lock_unlocked);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageUnlock.setImageDrawable(icon);
    }

    private void setImageClose() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.ic_close);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageClose.setImageDrawable(icon);
    }

    private void checkFirst(String userName, String password) {
        binding.layoutButtonLogin.setEnabled(false);
        CommonUtils.animFadeOut(binding.textAlert, 500);
        if (userName.isEmpty() && password.isEmpty()) {
            binding.textAlert.setText(context.getResources().getString(R.string.strEmptyEmailorPassword));
            CommonUtils.animFadeIn(binding.textAlert, 500);
            binding.layoutButtonLogin.setEnabled(true);
        } else if (userName.isEmpty()) {
            binding.textAlert.setText(context.getResources().getString(R.string.strEmailEmpty));
            CommonUtils.animFadeIn(binding.textAlert, 500);
            binding.layoutButtonLogin.setEnabled(true);
        } else if (password.isEmpty()) {
            binding.textAlert.setText(context.getResources().getString(R.string.strPasswordEmpty));
            CommonUtils.animFadeIn(binding.textAlert, 500);
            binding.layoutButtonLogin.setEnabled(true);
        } else {
            if (CommonUtils.isValidEmail(userName)) {
                getLogin();
            } else {
                CommonUtils.animFadeIn(binding.textAlert, 500);
                binding.textAlert.setText(context.getResources().getString(R.string.strInvalidPassword));
                binding.layoutButtonLogin.setEnabled(true);
            }
        }
    }

    private void getLogin() {
        disposable = mainService.requestLogin(appData.getFcmId(), binding.inputEmail.getText().toString(),
                binding.inputPassword.getText().toString(), new MyCallBack.CallLogin() {

                    @Override
                    public void onStartRequest() {
                        CommonUtils.animFadeOut(binding.textAlert, 500);
                        CommonUtils.animFadeOut(binding.textLogin, 500);
                        CommonUtils.animFadeIn(binding.arcLoader, 500);
                        CommonUtils.hideSoftKeyboard(context);
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
                        CommonUtils.animFadeIn(binding.arcLoader, 500);
                        CommonUtils.animFadeIn(binding.textLogin, 500);
                        binding.textAlert.setText("");
                        binding.layoutButtonLogin.setEnabled(true);
                    }


                    @Override
                    public void onSuccess(DataLogin dataLogin) {
                        appData.setIsLogin(true);
                        appData.setToken(dataLogin.token);
                        CommonUtils.animFadeOut(binding.arcLoader, 500);
                        CommonUtils.animFadeIn(binding.textLogin, 500);
                        CommonUtils.animFadeOut(binding.textAlert, 500);
                        loginView.onResult();
                    }
                });
    }

    public void onLoginClick() {
        if (ConnectivityReceiver.isConnected()) {
            checkFirst(binding.inputEmail.getText().toString(), binding.inputEmail.getText().toString());
        } else {
            CommonUtils.snackBar(binding.coordinatorLayout, context);
        }
    }

    public void onCloseActivity() {
        loginView.onClose();
    }

    public void onClickForgotPassword() {
        context.startActivity(new Intent(context, ActivityForgotPassword.class));
    }

}

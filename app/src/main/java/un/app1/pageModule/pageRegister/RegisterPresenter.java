package un.app1.pageModule.pageRegister;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.databinding.ActivityRegisterBinding;

public class RegisterPresenter {

    private Context context;
    private ActivityRegisterBinding binding;
    private RegisterView registerView;
    private Disposable disposable;

    public RegisterPresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(RegisterPresenter.this);
    }

    void setRegisterPresenter(Context context, ActivityRegisterBinding binding) {
        this.registerView = ((RegisterView) context);
        this.context = context;
        this.binding = binding;
        this.disposable = Disposables.empty();

        setImageAccount();
        setImageEmail();
        setImagePhoneNumber();
        setImageClose();
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

    private void setImageAccount() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_account);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageAccount.setImageDrawable(icon);
    }

    private void setImageEmail() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_email_outline);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageEmail.setImageDrawable(icon);
    }

    private void setImagePhoneNumber() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_cellphone);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imagePhoneNumber.setImageDrawable(icon);
    }

    private void setImageClose() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.ic_close);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageClose.setImageDrawable(icon);
    }

    public void onCloseActivity() {
        registerView.onClose();
    }

}

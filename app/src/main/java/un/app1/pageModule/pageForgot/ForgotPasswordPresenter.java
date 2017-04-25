package un.app1.pageModule.pageForgot;

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
import un.app1.appNetwork.service.MainService;
import un.app1.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordPresenter {

    @Inject
    MainService mainService;

    private Context context;
    private ActivityForgotPasswordBinding binding;
    private ForgotPasswordView forgotPasswordView;
    private Disposable disposable;

    public ForgotPasswordPresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(ForgotPasswordPresenter.this);
    }

    void setForgotPasswordPresenter(Context context, ActivityForgotPasswordBinding binding) {
        this.forgotPasswordView = ((ForgotPasswordView) context);
        this.context = context;
        this.binding = binding;
        this.disposable = Disposables.empty();

        setImageClose();
        setImageEmail();
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

    private void setImageClose() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.ic_close);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageClose.setImageDrawable(icon);
    }

    private void setImageEmail() {
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.zzz_email_outline);
        icon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        binding.imageEmail.setImageDrawable(icon);
    }

    public void onCloseActivity() {
        forgotPasswordView.onClose();
    }

}

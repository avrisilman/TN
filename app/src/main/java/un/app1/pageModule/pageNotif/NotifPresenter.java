package un.app1.pageModule.pageNotif;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import un.app1.MainApp;
import un.app1.appCommon.common.CommonUtils;
import un.app1.databinding.ActivityNotifBinding;

public class NotifPresenter {
    private Context context;
    private ActivityNotifBinding binding;
    private NotifView notifView;
    private Disposable disposable;

    public NotifPresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(NotifPresenter.this);
    }

    void setNotifPresenter(Context context, ActivityNotifBinding binding) {
        this.notifView = ((NotifView) context);
        this.context = context;
        this.binding = binding;
        this.disposable = Disposables.empty();
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
}

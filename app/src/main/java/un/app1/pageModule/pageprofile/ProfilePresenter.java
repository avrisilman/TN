package un.app1.pageModule.pageProfile;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import un.app1.MainApp;
import un.app1.appCommon.common.CommonUtils;
import un.app1.databinding.ActivityProfileBinding;

public class ProfilePresenter {
    private Context context;
    private ActivityProfileBinding binding;
    private ProfileView profileView;
    private Disposable disposable;

    public ProfilePresenter(Context context) {
        this.context = context;
        ((MainApp) context).providesAppComponents().inject(ProfilePresenter.this);
    }

    void setProfilePresenter(Context context, ActivityProfileBinding binding) {
        this.profileView = ((ProfileView) context);
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

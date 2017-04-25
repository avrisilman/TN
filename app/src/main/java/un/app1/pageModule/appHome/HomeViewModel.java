package un.app1.pageModule.appHome;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import un.app1.BR;

public class HomeViewModel extends BaseObservable {

    private HomeMainModel model;

    HomeViewModel(HomeMainModel model) {
        this.model = model;
    }

    @Bindable
    public String getBalance() {
        return model != null ? model.balance : "";
    }

    public void setBalance(String balance) {
        model.balance = balance;
        notifyPropertyChanged(BR.balance);
    }

    @Bindable
    public String getLastLogin() {
        return model != null ? model.lastLogin : "";
    }

    public void setLastLogin(String lastLogin) {
        model.lastLogin = lastLogin;
        notifyPropertyChanged(BR.lastLogin);
    }

    @Bindable
    public String getUserName() {
        return model != null ? model.userName : "";
    }

    public void setUserName(String userName) {
        model.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getClickRetry() {
        return model != null ? model.clickRetry : "";
    }

    public void setClickRetry(String clickRetry) {
        model.clickRetry = clickRetry;
        notifyPropertyChanged(BR.clickRetry);
    }

    @Bindable
    public String getUserImage() {
        return model != null ? model.userImage : "";
    }

    public void setUserImage(String userImage) {
        model.userImage = userImage;
        notifyPropertyChanged(BR.userImage);
    }

}

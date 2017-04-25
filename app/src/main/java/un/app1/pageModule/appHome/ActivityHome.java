package un.app1.pageModule.appHome;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import un.app1.MainApp;
import un.app1.R;
import un.app1.appCommon.common.EBus;
import un.app1.appCommon.event.EventLoginSuccess;
import un.app1.appConfig.AppData;
import un.app1.appNetwork.internet.ConnectivityReceiver;
import un.app1.databinding.ActivityHomeBinding;
import un.app1.pageModule.appHome.adapter.MainMenuAdapter;
import un.app1.pageModule.appHome.adapter.ProductAdapter;

public class ActivityHome extends AppCompatActivity implements HomeView, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    ProductAdapter productAdapter;

    @Inject
    MainMenuAdapter menuAdapter;

    @Inject
    HomePresenter presenter;

    @Inject
    AppData appData;

    ActivityHomeBinding binding;

    HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ((MainApp) getApplication()).providesAppComponents().inject(ActivityHome.this);
        MainApp.getInstance().setConnectivityListener(ActivityHome.this);

        binding = DataBindingUtil.setContentView(ActivityHome.this, R.layout.activity_home);
        viewModel = new HomeViewModel(new HomeMainModel());
        binding.setViewModel(viewModel);

        presenter.seHomePresenter(ActivityHome.this, binding);
        binding.setPresenter(presenter);

        presenter.isUserLogin();
        presenter.setImageAccount();
        presenter.setImageNotificationAler();
        presenter.setImageLogout();
        setAdapter();


    }

    private void setAdapter() {
        menuAdapter.setViewData(ActivityHome.this);
        menuAdapter.setMainMenu(presenter.getMainMenu());
        binding.recyclerMainMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerMainMenu.setAdapter(menuAdapter);

        //productAdapter.setViewData(ActivityHome.this);
        //productAdapter.setProduct(presenter.getProduct());
        //binding.recyclerProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //binding.recyclerProduct.setAdapter(productAdapter);
    }

    @Override
    public void goToMenuActivity(int position) {
        presenter.toActivity(position);
    }

    @Override
    public void onResume() {
        EBus.register(this);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        EBus.unregister(this);
        presenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    public void setShowUserImage() {
        Picasso.with(ActivityHome.this)
                .load(viewModel.getUserImage())
                .placeholder(R.drawable.ic_user)
                .resize(350, 350)
                .into(binding.imageUser);
    }

    @Override
    public void setUserImage(String imageUrl) {
        viewModel.setUserImage(imageUrl);
    }

    @Override
    public void setLayoutBlank() {
        binding.layoutHomeDashboardBlank.setVisibility(View.VISIBLE);
        binding.layoutHomeDashboardChecking.setVisibility(View.GONE);
        binding.layoutHomeDashboardSignIn.setVisibility(View.GONE);
        binding.layoutHomeDashboardSignOut.setVisibility(View.GONE);
    }

    @Override
    public void logOut() {
        appData.clearUserData();
        this.finish();
    }

    @Override
    public void setLayoutChecking() {
        binding.layoutHomeDashboardChecking.setVisibility(View.VISIBLE);
        binding.layoutHomeDashboardBlank.setVisibility(View.GONE);
        binding.layoutHomeDashboardSignIn.setVisibility(View.GONE);
        binding.layoutHomeDashboardSignOut.setVisibility(View.GONE);
    }

    @Override
    public void setLayoutSignOut() {
        binding.layoutHomeDashboardSignOut.setVisibility(View.VISIBLE);
        binding.layoutHomeDashboardBlank.setVisibility(View.GONE);
        binding.layoutHomeDashboardChecking.setVisibility(View.GONE);
        binding.layoutHomeDashboardSignIn.setVisibility(View.GONE);
    }

    @Override
    public void setLayoutSignIn() {
        binding.layoutHomeDashboardSignIn.setVisibility(View.VISIBLE);
        binding.layoutHomeDashboardBlank.setVisibility(View.GONE);
        binding.layoutHomeDashboardChecking.setVisibility(View.GONE);
        binding.layoutHomeDashboardSignOut.setVisibility(View.GONE);
    }

    @Override
    public void setUserName(String username) {
        viewModel.setUserName(username);
    }

    @Override
    public void setBalance(String balance) {
        viewModel.setBalance(balance);
    }

    @Override
    public void setLastLogin(String lastLogin) {
        viewModel.setLastLogin(lastLogin);
    }

    @Override
    public void setLogin(String login) {
        viewModel.setUserName(login);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        presenter.checkInternet(isConnected);
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onEventGotoActivityDataUser(EventLoginSuccess event) {
        presenter.getQuickPreview();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

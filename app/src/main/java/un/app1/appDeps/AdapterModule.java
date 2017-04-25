package un.app1.appDeps;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import un.app1.pageModule.appHome.adapter.BannerSlideAdapter;
import un.app1.pageModule.appHome.adapter.MainMenuAdapter;
import un.app1.pageModule.appHome.adapter.ProductAdapter;
import un.app1.pageModule.pageListrik.adapter.HistoryAdapter;
import un.app1.pageModule.pageListrik.adapter.ListrikAdapter;
import un.app1.pageModule.pagePulsa.pulsaAdapter.PulsaAdapter;

@Module
public class AdapterModule {


    @Provides
    @Singleton
    public PulsaAdapter providesPulsaAdapter() {
        return new PulsaAdapter();
    }

    @Provides
    @Singleton
    public BannerSlideAdapter providesBannerSlideAdapter() {
        return new BannerSlideAdapter();
    }

    @Provides
    @Singleton
    public ProductAdapter providesProductAdapter() {
        return new ProductAdapter();
    }

    @Provides
    @Singleton
    public MainMenuAdapter providesMainMenuAdapter() {
        return new MainMenuAdapter();
    }

    @Provides
    @Singleton
    public ListrikAdapter providesListrikAdapter() {
        return new ListrikAdapter();
    }

    @Provides
    @Singleton
    public HistoryAdapter providesHistoryListrikAdapter() {
        return new HistoryAdapter();
    }

}

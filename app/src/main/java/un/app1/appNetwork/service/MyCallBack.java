package un.app1.appNetwork.service;

import un.app1.pageModule.appHome.model.Banner;
import un.app1.pageModule.appHome.model.QuickPreview;
import un.app1.pageModule.pageListrik.model.History;
import un.app1.pageModule.pageListrik.model.TokenListrik;
import un.app1.pageModule.pageLogin.model.DataLogin;
import un.app1.pageModule.pagePulsa.model.Pulsa;

public class MyCallBack {

    public interface CallDenomPulsa {

        void onStartRequest();

        void onNotFound();

        void onUnauthorized();

        void onError();

        void onSuccess(Pulsa pulsa);
    }

    public interface CallHistoryListrik {

        void onStartRequest();

        void onNotFound();

        void onUnauthorized();

        void onError();

        void onSuccess(History history);
    }

    public interface CallTokenListrik {

        void onStartRequest();

        void onNotFound();

        void onUnauthorized();

        void onError();

        void onSuccess(TokenListrik tokenListrik);

    }

    public interface CallBanner {

        void onStartRequest();

        void onNotFound();

        void onUnauthorized();

        void onError();

        void onSuccess(Banner banner);
    }

    public interface CallQuickPreview {

        void onStartRequest();

        void onNotFound();

        void onUnauthorized();

        void onError();

        void onSuccess(QuickPreview quickPreview);
    }

    public interface CallLogin {

        void onStartRequest();

        void onNotFound();

        void onUnauthorized();

        void onError();

        void onSuccess(DataLogin dataLogin);
    }

}

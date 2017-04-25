package un.app1.pageModule.pageListrik;

import un.app1.pageModule.pageListrik.model.DenomListrik;

public interface ListrikView {

    void onCloseActivity();

    void onClickBuy();

    void enableClickBuy(DenomListrik denomListrik);

    void checkDenom();

    void ifDenomFailed();

    void goToHistory();

    void goToAddFrom();

}

package un.app1.pageModule.pageListrik.historylistrik;

import java.util.List;

import un.app1.pageModule.pageListrik.model.HistoryPayment;

public interface HistoryListrikView {

    void onCloseActivity();

    void toAdapter(List<HistoryPayment> historyPayments);

    void animFadeOutArcLoader();

    void animFadeInArcLoader();

}

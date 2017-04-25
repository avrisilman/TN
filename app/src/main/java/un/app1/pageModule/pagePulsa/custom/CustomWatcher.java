package un.app1.pageModule.pagePulsa.custom;

import android.text.Editable;
import android.text.TextWatcher;

import un.app1.pageModule.pagePulsa.PulsaView;

public class CustomWatcher implements TextWatcher {

    private PulsaView pulsaView;
    private boolean mWasEdited = false;

    public CustomWatcher(PulsaView pulsaView) {
        this.pulsaView = pulsaView;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 13) {
            pulsaView.checkDenom();
        } else if (s.length() < 13) {

        }
    }
}
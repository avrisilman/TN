package un.app1.pageModule.pageListrik.custom;

import android.text.Editable;
import android.text.TextWatcher;

import un.app1.pageModule.pageListrik.ListrikView;

public class CustomWatcher implements TextWatcher {

    private ListrikView listrikView;
    private boolean mWasEdited = false;

    public CustomWatcher(ListrikView listrikView) {
        this.listrikView = listrikView;
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
            listrikView.checkDenom();
        } else if (s.length() < 13) {

        }
    }
}
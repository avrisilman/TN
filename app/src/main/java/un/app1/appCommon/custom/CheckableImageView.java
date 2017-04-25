package un.app1.appCommon.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

public class CheckableImageView extends ImageView implements Checkable {

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };
    private boolean mChecked;

    @SuppressLint("NewApi")
    public CheckableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        try {
            if (isChecked()) {
                mergeDrawableStates(drawableState, CHECKED_STATE_SET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drawableState;
    }

    public void toggle() {
        setChecked(!mChecked);
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        try {
            if (mChecked != checked) {
                mChecked = checked;
                refreshDrawableState();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
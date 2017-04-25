package un.app1.appCommon.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class CommonUtils {

    static InputMethodManager imm;

    public static String date01(){
        return java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    }

    public static boolean isNotFound(Throwable throwable){
        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == 404) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnauthorized(Throwable throwable){
        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == 401) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void a(String mlog) {
        Log.e("x", mlog);
    }

    public static String timeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }

    public static void setFont(ViewGroup group, Typeface font) {
        View view;
        for (int i = 0; i < group.getChildCount(); i++) {
            view = group.getChildAt(i);
            if (view instanceof TextView || view instanceof EditText | view instanceof Button) {
                ((TextView) view).setTypeface(font);
            } else if (view instanceof ViewGroup) {
                setFont((ViewGroup) view, font);
            }
        }
    }

    public static Typeface typeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/robotolight.ttf");
    }

    public static void snackBar(View view, Context context) {
        Snackbar.make(view, "Connection Lost", Snackbar.LENGTH_LONG).show();
    }

    public static void snackBarWithText(String message, View view, Context context) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String deviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static void hideSoftKeyboard(Context context) {
        imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static boolean isSoftKeyboard() {
        return imm.isActive();
    }

    public static void animFadeIn(View view, int duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.setVisibility(View.VISIBLE);
        view.startAnimation(alphaAnimation);
    }

    public static void animFadeOut(View view, int duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
        view.setVisibility(View.GONE);
    }

    public static String CurrencyID(String s) {
        if (s.length() >= 3 && s.length() <= 6) {
            Double amount = Double.parseDouble(s.toString());
            NumberFormat formatter = new DecimalFormat("##,###");
            String currencyFormat = formatter.format(amount).replaceAll(",", ".");
            String trim = trimChar(".", currencyFormat);
            return trim;
        } else if (s.length() >= 6 && s.length() <= 7) {
            Double amount = Double.parseDouble(s.toString());
            NumberFormat formatter = new DecimalFormat("#,###,###");
            String currencyFormat = formatter.format(amount).replaceAll(",", ".");
            String trim = trimChar(".", currencyFormat);
            return trim;
        } else {
            return s;
        }
    }

    public static String currency(String s) {
        Double amount = Double.parseDouble(s.toString());
        NumberFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(amount).replaceAll(",", ".");
    }

    public static String trimDot(String sring) {
        String currencyFormat = "";
        if (sring.length() >= 7) {
            currencyFormat = ".000";
        } else {
            currencyFormat = ".000";
        }

        return currencyFormat;
    }

    public static String trimChar(String trim, String inString) {
        String string = trim;
        char toTrim = string.charAt(0);
        int from = 0;
        int to = inString.length();

        for (int i = 0; i < inString.length(); i++) {
            if (inString.charAt(i) != toTrim) {
                from = i;
                break;
            }
        }

        for (int i = inString.length() - 1; i >= 0; i--) {
            if (inString.charAt(i) == toTrim) {
                to = i;
                break;
            }
        }

        return inString.substring(from, to);
    }

}

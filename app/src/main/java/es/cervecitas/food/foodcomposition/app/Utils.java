package es.cervecitas.food.foodcomposition.app;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

public class Utils {

    public static String capitalizeFirstLetter(String str) {
        return String.valueOf(str.charAt(0)).toUpperCase().concat(str.substring(1, str.length()));
    }

    public static void hideKeyboard(Context context, IBinder windowToken) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
    }
}

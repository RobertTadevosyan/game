package square.com.avoidsquare.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by anahit on 4/25/16.
 */
public class PreferenceUtil {

    public static void saveInSharedPreference(Context context, String key, Object value)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(value instanceof Boolean){
            editor.putBoolean(key,(Boolean)value);
        }
        else if(value instanceof String){
            editor.putString(key, (String)value);
        }
        else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }
        editor.apply();
    }

    public static Object readPreference(Context context, String key, Object defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if(defaultValue instanceof String){
            return sharedPreferences.getString(key, (String)defaultValue);
        }
        else if(defaultValue instanceof Boolean){
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        }
        else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer)defaultValue);
        }
        return null;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}

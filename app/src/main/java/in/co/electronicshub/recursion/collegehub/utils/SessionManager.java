package in.co.electronicshub.recursion.collegehub.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "com.example.mahaemergency";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_UID = "uid";
    private static final String KEY_IMAGE = "image";

    public SessionManager(Context context){
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setLogin(boolean login){
        if(!login){
            editor.clear();
        }else {
            editor.putBoolean(KEY_IS_LOGGED_IN, login);
        }
        editor.apply();
    }

    public void setName(String name){
        editor.putString(KEY_NAME, name);
        editor.apply();
    }


    public void setUid(int uid){
        editor.putInt(KEY_UID, uid);
        editor.apply();
    }

    public void setImage(String bitmap){
        editor.putString(KEY_IMAGE, bitmap);
        editor.apply();
    }

    public void setPhone(String phone){
        editor.putString(KEY_PHONE, phone);
        editor.apply();
    }


    public boolean isLoggedIn(){
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getName(){
        return preferences.getString(KEY_NAME, null);
    }

    public int getUid(){
        return preferences.getInt(KEY_UID, 0);
    }


    public String getImage(){
        return preferences.getString(KEY_IMAGE, null);
    }

    public String getPhone(){
        return preferences.getString(KEY_PHONE, null);
    }


}

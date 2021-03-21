package in.co.electronicshub.recursion.collegehub.utils;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import in.co.electronicshub.recursion.collegehub.R;

public class CustomToast {

    static void show(Context context, int id){
        show(context, context.getResources().getString(id));
    }

    public static void show(Context context, String msg){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) ((Activity)context).findViewById(R.id.toast_root));
        TextView textView = layout.findViewById(R.id.message);
        textView.setText(msg);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}

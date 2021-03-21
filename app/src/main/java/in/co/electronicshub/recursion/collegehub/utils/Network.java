package in.co.electronicshub.recursion.collegehub.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import in.co.electronicshub.recursion.collegehub.activities.RegisterActivity;

public class Network extends AsyncTask<HashMap<String, String>, Void, String> {

    private ResponseListener responseListener;
    private String page;
    public final static String DOMAIN = "http://recursion.electronicshub.co.in/";
    private ProgressDialog progressDialog;
    private Context context;
    private boolean error;

    public Network(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading Data");
    }

    public void setResponseListener(ResponseListener responseListener){
        this.responseListener  = responseListener;
    }

    public void setPage(String page){
        this.page = page;
    }

    public void setMessage(String message){
        progressDialog.setMessage(message);
    }

    @Override
    protected String doInBackground(HashMap<String, String>... hashMaps) {
        try {
            HashMap<String, String> data = hashMaps[0];
            URL link = new URL( DOMAIN  + page);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(3000);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
            bufferedWriter.write(getPostableData(data));
            bufferedWriter.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while((line=bufferedReader.readLine())!=null){
                result.append(line);
            }
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
            error = true;
            return null;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(error){
            CustomToast.show(context, "Unable to connnect to server");
        }else{
            Log.i("Response", s);
        }
        responseListener.responseReceived(page, s);

        if(progressDialog.isShowing())
            progressDialog.dismiss();

    }

    private String getPostableData(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    public static interface ResponseListener {
        void responseReceived(String page, String data);
    }
}


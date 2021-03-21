package in.co.electronicshub.recursion.collegehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import in.co.electronicshub.recursion.collegehub.R;
import in.co.electronicshub.recursion.collegehub.utils.CustomToast;
import in.co.electronicshub.recursion.collegehub.utils.Network;
import in.co.electronicshub.recursion.collegehub.utils.SessionManager;

public class LoginActivity extends AppCompatActivity implements Network.ResponseListener {

    private TextView registerTextView;
    private EditText passwordEditText, phoneEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerTextView = findViewById(R.id.registerTextView);

        phoneEditText = findViewById(R.id.phoneEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);


        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(phone.isEmpty() || password.isEmpty()){
                    CustomToast.show(LoginActivity.this, "Please fill all fields");
                }else{
                    HashMap<String, String> data = new HashMap<>();
                    data.put("phone", phone);
                    data.put("password", password);
                    Network network = new Network(LoginActivity.this);
                    network.setMessage("Logging in... Please wait");
                    network.setPage("login.php");
                    network.setResponseListener(LoginActivity.this);
                    network.execute(data);
                }
            }
        });
    }

    @Override
    public void responseReceived(String page, String data) {
        if(data!=null) {
            try {
                JSONObject json = new JSONObject(data);
                CustomToast.show(this, json.getString("msg"));
                if (json.getBoolean("success")) {
                    SessionManager sessionManager = new SessionManager(this);
                    sessionManager.setLogin(true);
                    sessionManager.setName(json.getString("name"));
                    sessionManager.setPhone(json.getString("phone"));
                    sessionManager.setUid(json.getInt("id"));
                    sessionManager.setImage(json.getString("image"));
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            } catch (JSONException je) {
                je.printStackTrace();
                CustomToast.show(this, "There was a problem");
            }
        }
    }
}
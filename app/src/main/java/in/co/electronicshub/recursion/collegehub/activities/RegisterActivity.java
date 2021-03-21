package in.co.electronicshub.recursion.collegehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import in.co.electronicshub.recursion.collegehub.R;
import in.co.electronicshub.recursion.collegehub.utils.CustomToast;
import in.co.electronicshub.recursion.collegehub.utils.Network;
import in.co.electronicshub.recursion.collegehub.utils.SessionManager;

public class RegisterActivity extends AppCompatActivity implements Network.ResponseListener {

    private ImageView imageView;
    private TextView loginTextView;
    private EditText nameEditText, passwordEditText, cpasswordEditText, phoneEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginTextView = findViewById(R.id.loginTextView);
        imageView = findViewById(R.id.imageView);
        nameEditText = findViewById(R.id.nameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        cpasswordEditText = findViewById(R.id.cpasswordEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        registerButton = findViewById(R.id.registerButton);

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(RegisterActivity.this);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Bitmap bm=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                String image = Base64.encodeToString(byteArray,  Base64.DEFAULT);
                String name = nameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String cpassword = cpasswordEditText.getText().toString();
                if(cpassword.equals(password)) {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("image", image);
                    data.put("name", name);
                    data.put("password", password);
                    data.put("phone", phone);
                    Network network = new Network(RegisterActivity.this);
                    network.setMessage("Registering.. Please wait");
                    network.setPage("register.php");
                    network.setResponseListener(RegisterActivity.this);
                    network.execute(data);
                }else{
                    CustomToast.show(RegisterActivity.this, "Passwords do not match");
                }
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                CustomToast.show(this, error.getMessage());
            }
        }
    }

    @Override
    public void responseReceived(String page, String data) {
        if(data!=null) {
            try {
                SessionManager sessionManager = new SessionManager(this);
                JSONObject json = new JSONObject(data);
                CustomToast.show(this, json.getString("msg"));
                if (json.getBoolean("success")) {
                    sessionManager.setUid(json.getInt("id"));
                    sessionManager.setImage(json.getString("image"));
                    sessionManager.setName(json.getString("name"));
                    sessionManager.setPhone(json.getString("phone"));
                    sessionManager.setLogin(true);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException je) {
                CustomToast.show(this, "There was a problem");
            }
        }
    }

}
package com.example.testgradle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView chat_background, imageView_facebook, imageView_google, imageView_linkdin, imageView_twitter;
    private EditText editText_email, editText_password, editText_socialSignUp;
    private TextView textView_login, textView_forgetPassword, textView_addNewUser;
    private AppCompatButton button_signIn;
    private CheckBox checkBox_remember;
    public static final String TAG = "volley";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.responseText);
        initViews();
        onclick();
        validateEmail();
        validatePassword();
        {

        }
    }

    private void initViews() {
        chat_background = findViewById(R.id.chat_background);
        imageView_facebook = findViewById(R.id.imageView_facebook);
        imageView_google = findViewById(R.id.imageView_google);
        imageView_linkdin = findViewById(R.id.imageView_linkdin);
        imageView_twitter = findViewById(R.id.imageView_twitter);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_socialSignUp = findViewById(R.id.editText_socialsignUp);
        checkBox_remember = findViewById(R.id.checkBox_remember);
        textView_login = findViewById(R.id.textView_login);
        textView_forgetPassword = findViewById(R.id.textView_forget_password);
        textView_addNewUser = findViewById(R.id.textView_add_new_user);
        button_signIn = findViewById(R.id.button_signIn);


//        on click new activity why not work
        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MvpActivity.class);
                startActivity(intent);
            }
        });
//

        String email = Spref.getSharedPreferences(this).getString(Spref.EMAIL, "");
        editText_email.setText(email);
        String password = Spref.getSharedPreferences(this).getString(Spref.PASSWORD, "");
        editText_password.setText(password);
    }

    private void onclick() {
        textView_forgetPassword.setOnClickListener(this);
        editText_socialSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == button_signIn.getId()) {
            String email = editText_email.getText().toString().trim();
            Spref.getSharedPreferences(this).edit().putString(Spref.EMAIL, email).apply();
        }
        if (view.getId() == editText_password.getId()) {
            String password = editText_password.getText().toString().trim();
            Spref.getSharedPreferences(this).edit().putString(Spref.PASSWORD, password).apply();

        }
        if (view == editText_socialSignUp) {

        }
    }

    private boolean validateEmail() {
        String val = editText_email.getText().toString().trim();
        String emailPattern = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b";
        if (val.isEmpty()) {
            editText_email.setError("Field cannot empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            editText_email.setError("Invalid email address");
            return false;
        } else {
            editText_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = editText_password.getText().toString().trim();
        String passwordPattern = "[a-zA-z0-9._-]+0[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            editText_password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordPattern)) {
            editText_password.setError("Invalid email address");
            return false;
        } else {
            editText_password.setError(null);
            return true;
        }
    }
}


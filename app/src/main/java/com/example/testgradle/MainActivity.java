package com.example.testgradle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView chat_background, imageView_facebook, imageView_google, imageView_linkdin, imageView_twitter;
    private EditText editText_email, editText_password, editText_socialSignUp;
    private TextView textView_login, textView_forgetPassword, textView_addNewUser;
    private AppCompatButton button_signIn;
    private CheckBox checkBox_remember;
    public static final String TAG = "volley";
    public static final String EMAIL = "email";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        onclick();

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
        textView_forgetPassword = findViewById(R.id.textView_forgetpassword);
        textView_addNewUser = findViewById(R.id.textView_addnewuser);
        button_signIn = findViewById(R.id.button_signIn);

        sharedPreferences = getSharedPreferences(MainActivity.TAG, MODE_PRIVATE);
        String email = spref.getSharedPreferences(this).getString(spref.EMAIL, "");
        editText_email.setText(email);
    }

    private void onclick() {
        button_signIn.setOnClickListener(this);
        textView_forgetPassword.setOnClickListener(this);
        editText_socialSignUp.setOnClickListener(this);
    }

    public static class spref {
        public static final String EMAIL = "email";
        static SharedPreferences sharedPreferences;

        public static SharedPreferences getSharedPreferences(Context context) {
            if (sharedPreferences == null)
                sharedPreferences = context.getSharedPreferences(MainActivity.TAG, Context.MODE_PRIVATE);
            return sharedPreferences;
        }
    }

    @Override
    public void onClick(View view) {
        if (view == button_signIn) {
            String email = editText_email.getText().toString().trim();
            spref.getSharedPreferences(this).edit().putString(spref.EMAIL, email).apply();
        }
        if (view == textView_forgetPassword) {

        }
        if (view == editText_socialSignUp) {

        }
    }

    private boolean validateName() {
        String val = editText_email.getText().toString().trim();
        if (val.isEmpty()) {
            editText_email.setError("Field cannot empty");
            return false;
        } else {
            editText_email.setError(null);
            return true;
        }
    }
}

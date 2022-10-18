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
    APIInterface apiInterface;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.responseText);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        initViews();
        onclick();
        validateEmail();
        validatePassword();

        /**
         GET List Resources
         **/
        Call<MultipleResource> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {


                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                for (MultipleResource.Datum datum : datumList) {
                    displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                }

                responseText.setText(displayResponse);

            }

            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {
                call.cancel();
            }
        });

        /**
         Create new user
         **/
        User user = new User("morpheus", "leader");
        Call<User> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();

                Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });

        /**
         GET List Users
         **/
        Call<UserList> call2 = apiInterface.doGetUserList("2");
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });


        /**
         POST name and job Url encoded.
         **/
        Call<UserList> call3 = apiInterface.doCreateUserWithField("morpheus", "leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });

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



//        on click new activity why not work
        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MvpActivity.class);
                startActivity(intent);
            }
        });
//        why ?

        sharedPreferences = getSharedPreferences(MainActivity.TAG, MODE_PRIVATE);
        String email = spref.getSharedPreferences(this).getString(spref.EMAIL, "");
        editText_email.setText(email);
        sharedPreferences =getSharedPreferences(MainActivity.TAG,MODE_PRIVATE);
        String password = spref.getSharedPreferences(this).getString(spref.PASSWORD,"");
        editText_password.setText(password);
    }

    private void onclick() {
        textView_forgetPassword.setOnClickListener(this);
        editText_socialSignUp.setOnClickListener(this);
    }

    public static class spref {
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        static SharedPreferences sharedPreferences;

        public static SharedPreferences getSharedPreferences(Context context) {
            if (sharedPreferences == null)
                sharedPreferences = context.getSharedPreferences(MainActivity.TAG, Context.MODE_PRIVATE);
            return sharedPreferences;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == button_signIn.getId()) {
            String email = editText_email.getText().toString().trim();
            spref.getSharedPreferences(this).edit().putString(spref.EMAIL, email).apply();
        }
        if (view.getId()== editText_password.getId()) {
            String password = editText_password.getText().toString().trim();
            spref.getSharedPreferences(this).edit().putString(spref.PASSWORD, password).apply();

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
        }else if(!val.matches(emailPattern)) {
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


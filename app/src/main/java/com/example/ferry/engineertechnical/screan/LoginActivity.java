package com.example.ferry.engineertechnical.screan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ferry.engineertechnical.R;
import com.example.ferry.engineertechnical.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {


    private AppCompatAutoCompleteTextView inputEmail;
    private EditText inputPassword;
    private SessionManager session;

    private static SharedPreferences pref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.editEmail);
        inputPassword= findViewById(R.id.editPassword);
        Button btnLogin = findViewById(R.id.singInButton);


        pref = getSharedPreferences(getString(R.string.key_pref), Context.MODE_PRIVATE);

        Bundle extras=getIntent().getExtras();

        if(extras!=null){

            String emaillol = extras.getString("Email");
            String passwordlol = extras.getString("Password");
            inputEmail.setText(emaillol);
            inputPassword.setText(passwordlol);
        }

        else {

            load();
        }

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);



        session = new SessionManager(getApplicationContext());


        if (session.isLoggedIn()) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                if(!Validator()) {


                    if(email.equalsIgnoreCase("alumagubi")&&password.equalsIgnoreCase("123abc123")){
                        session.setLogin(true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {

                        DialogError();

                    }

                }

            }

        });


    }




    public boolean Validator() {


        inputEmail.setError(null);
        inputPassword.setError(null);
        String emailValidator = inputEmail.getText().toString();
        String passwordValidator = inputPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (passwordValidator.isEmpty()) {
            inputPassword.setError("Confirm Password must be filed ");
            focusView = inputPassword;
            cancel = true;
        }
        if (emailValidator.isEmpty()) {
            inputEmail.setError("Email must be filed");
            focusView =inputEmail;
            cancel = true;
        }

        if (cancel) {focusView.requestFocus();}
        return cancel;
    }



    public void load(){
        try {

            String email = pref.getString(getString(R.string.key_email), "");
            String password = pref.getString(getString(R.string.key_password), "");
            inputEmail.setText(email);
            inputPassword.setText(password);

        }
        catch (Exception e){
            inputEmail.setText("");
            inputPassword.setText("");

            Toast.makeText(getApplicationContext(), "SharedPreferences Error ", Toast.LENGTH_SHORT).show();
        }

    }




    private void DialogError(){

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Wrong user name or password. Try again later").setTitle("Warring");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}

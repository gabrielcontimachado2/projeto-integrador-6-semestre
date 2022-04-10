package com.example.vetfootprint.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;

import com.example.vetfootprint.R;

public class Login extends AppCompatActivity {

    private EditText edtLogin, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        onInitView();
    }

    private void onInitView() {
        edtLogin = findViewById(R.id.edittext_user_email);
        edtSenha = findViewById(R.id.edittext_user_password);
    }

}
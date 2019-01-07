package com.example.bobby.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText edtRegUser,edtRegPass,edtRegPassConf;
    Button btRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DatabaseHelper(this);
        edtRegUser = (EditText)findViewById(R.id.edtSignUpUser);
        edtRegPass = (EditText)findViewById(R.id.edtSignUpPass);
        edtRegPassConf = (EditText)findViewById(R.id.edtSignUpPassConf);
        btRegister = (Button)findViewById(R.id.btnRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String s1 = edtRegUser.getText().toString();
              String s2 = edtRegPass.getText().toString();
              String s3 = edtRegPassConf.getText().toString();
              if(s1.equals("")||s2.equals("")||s3.equals("")){
                  Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
              }
              else{
                  if(s2.equals(s3)){
                      Boolean chkmail = db.chkemail(s1);
                      if(chkmail==true){
                          Boolean insert = db.insert(s1,s2);
                          if(insert==true){
                              Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                              Intent a = new Intent(SignUpActivity.this,Login.class);
                              startActivity(a);
                              finish();
                          }
                      }
                      else{
                          Toast.makeText(getApplicationContext(),"Email Already Exist", Toast.LENGTH_SHORT).show();
                      }
                      }
                    Toast.makeText(getApplicationContext(),"Password Do Not Match", Toast.LENGTH_SHORT).show();
              }
              }
        });
    }
}

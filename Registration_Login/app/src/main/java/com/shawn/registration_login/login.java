package com.shawn.registration_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    //declare variable for finding the id's of xml components
    EditText email_login, pass_login;
    Button login;
    TextView ask_signup;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //finding the id's of xml components
        email_login=findViewById(R.id.email_login);
        pass_login=findViewById(R.id.password_login);
        login=findViewById(R.id.button_login);
        ask_signup=findViewById(R.id.ask_signup);

        //switch block for switch to sign up screen
        ask_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,MainActivity.class));
                Toast.makeText(login.this,"Switched to SignUp", Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_email= email_login.getText().toString();
                String get_pass=pass_login.getText().toString();
                firebaseAuth=FirebaseAuth.getInstance();

                if (get_email.isEmpty()){
                    email_login.setError("blank email");
                }else if (get_pass.isEmpty()){
                    pass_login.setError("blank Password");
                }else if (!get_email.isEmpty() && !get_pass.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(get_email,get_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this, Home.class));
                            }else {
                                Toast.makeText(login.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}

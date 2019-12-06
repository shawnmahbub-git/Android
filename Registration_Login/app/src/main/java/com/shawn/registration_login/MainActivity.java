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

public class MainActivity extends AppCompatActivity {

    //create variable for finding the xml components of main activity
    EditText email, password;
    Button sign_up;
    TextView ask_login;

    //creating a variable for adding fire base dependency
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding the id's of xml of components of main activity
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        sign_up=findViewById(R.id.sign_up);
        ask_login=findViewById(R.id.ask_login);

        //switch to login activity if user is an existing user
        ask_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,login.class));
                Toast.makeText(MainActivity.this, "Switched to Login", Toast.LENGTH_SHORT).show();
            }
        });

        //this block is for sign up authentication
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //now I've to set on click listener to sign up as the authentication starts by clicking the sign up button
                //to get the user email and password we need to declare two variable
                String get_email=email.getText().toString();
                String get_password=password.getText().toString();
                //calling the fire base Auth
                firebaseAuth=firebaseAuth.getInstance();

                //authentication starts
                if (get_email.isEmpty())
                {
                    email.setError("Blank Email");
                    email.requestFocus();

                }else if (get_password.isEmpty()){
                    password.setError("Blank Password");
                    password.requestFocus();

                }else if (!get_email.isEmpty() && !get_password.isEmpty()){
                    firebaseAuth.createUserWithEmailAndPassword(get_email,get_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                            }else {
                                startActivity(new Intent(MainActivity.this, Home.class));
                                Toast.makeText(MainActivity.this, "SignUp Successful!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });


    }
}

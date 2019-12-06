package com.shawn.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName, Password;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName=findViewById(R.id.userName);
        Password=findViewById(R.id.password);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().equals("admin")&& Password.getText().toString().equals("admin")){
                    Intent intent;
                    intent = new Intent(MainActivity.this, Main2Activity.class);
                    Toast.makeText(getApplicationContext(), "Successfully login", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }else {

                    Toast.makeText(getApplicationContext(), "wrong credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

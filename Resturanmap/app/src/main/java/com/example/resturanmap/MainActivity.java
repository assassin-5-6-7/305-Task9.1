package com.example.resturanmap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> cd = new ArrayList<>();


    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.AddButton);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivityForResult(intent,1);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                ArrayList<String> ab = data.getStringArrayListExtra("a");
                cd.add(ab.get(0));
                cd.add(ab.get(1));
            }
        }
        else if(resultCode==RESULT_CANCELED){
            Toast.makeText(this,"no location ",Toast.LENGTH_LONG).show();
        }


    }

    public void Add(View view) {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

    public void View(View view) {
        Intent intent = new Intent(MainActivity.this,MapsActivity2.class);
        intent.putStringArrayListExtra("a",cd);
        startActivity(intent);
    }
}
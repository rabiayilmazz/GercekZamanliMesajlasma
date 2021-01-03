package com.rabiayilmaz.gercekzamanlimesajlasma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ChatActivity extends AppCompatActivity {

    String userName;
    String otherName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        System.out.println("cahta activity");
        tanimla();
    }
    public void tanimla(){
        userName = getIntent().getExtras().getString("userNmame");
        otherName = getIntent().getExtras().getString("otherNmame");
        Log.i("alınandegerler : ", userName + "--" + otherName);
    }
}
package com.rabiayilmaz.gercekzamanlimesajlasma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class GirisActivity extends AppCompatActivity {

    EditText kullaniciAdEditText;
    Button kayitOlButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        tanimla();
    }
    public void tanimla(){
        kullaniciAdEditText = (EditText)findViewById(R.id.kullaniciAdEditText);
        kayitOlButton = (Button)findViewById(R.id.kayitOlButton);
    }
}
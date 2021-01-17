package com.rabiayilmaz.gercekzamanlimesajlasma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    String userName, otherName;
    ImageView sendImage;
    ImageView backImage;
    EditText chatEditText;
    TextView chatUserName;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();
        loadMesaj();
    }
    public void olustur(){
        userName = getIntent().getExtras().getString("username");
        otherName = getIntent().getExtras().getString("othername");
        Log.i("alınandegerler : ", userName + "--" + otherName);
        chatUserName = (TextView)findViewById(R.id.chatUserName);
        backImage = (ImageView)findViewById(R.id.backImage);
        sendImage = (ImageView)findViewById(R.id.sendImage);
        chatEditText = (EditText)findViewById(R.id.chatEditText);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
    }
    public void tanimla(){
        olustur();
        chatUserName.setText(otherName);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                intent.putExtra("kadi",userName);
                startActivity(intent);
            }
        });
        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mesaj = chatEditText.getText().toString();
                chatEditText.setText("");
                mesajGonder(mesaj);
            }
        });
    }
    public void mesajGonder(String text){
        final String key = reference.child("Mesajlar").child(userName).child(otherName).push().getKey();
        final Map mesajMap = new HashMap();
        mesajMap.put("text",text);
        mesajMap.put("from",userName);
        reference.child("Mesajlar").child(userName).child(otherName).child(key).setValue(mesajMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    reference.child("Mesajlar").child(otherName).child(userName).child(key).setValue(mesajMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }
        });
    }
    public void loadMesaj() {
        reference.child("Mesajlar").child(userName).child(otherName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MesajModel mesajmodel = dataSnapshot.getValue(MesajModel.class);
                Log.i("Mesajlar", mesajmodel.toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
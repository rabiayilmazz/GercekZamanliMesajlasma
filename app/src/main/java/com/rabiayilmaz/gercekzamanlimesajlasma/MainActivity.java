package com.rabiayilmaz.gercekzamanlimesajlasma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<String> list = new ArrayList<>();
    String username;
    RecyclerView userRecycView;
    UserAdapter userAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(getApplicationContext(),"Başarılı bir şekilde kayıt yapılmıştır.",Toast.LENGTH_SHORT).show();
        tanimla();
        listele();
    }

    public void tanimla(){
        username = getIntent().getExtras().getString("kadi");
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        userRecycView = (RecyclerView)findViewById(R.id.userRecycView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
        userRecycView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(MainActivity.this,list,MainActivity.this);
        userRecycView.setAdapter(userAdapter);

    }
    public void listele(){
        reference.child("Kullanıcılar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(!dataSnapshot.getKey().equals(username)) {
                    list.add(dataSnapshot.getKey());
                    Log.i("Kullanıcılar",dataSnapshot.getKey());
                    userAdapter.notifyDataSetChanged();
                }
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
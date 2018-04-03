package com.example.vijaygarg.delagain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.vijaygarg.delagain.Adapters.SchemeAdapter;
import com.example.vijaygarg.delagain.Model.SchemeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveModel extends AppCompatActivity {

    RecyclerView rv;
    Button upload;
    SchemeAdapter schemeAdapter;
    ArrayList<SchemeModel> arr;
    DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_model);
        rv=findViewById(R.id.rvscheme);
        upload=findViewById(R.id.uploadscheme);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference();
        arr=new ArrayList<>();
        getSchemesModel();
        schemeAdapter=new SchemeAdapter(this,arr);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(schemeAdapter);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RemoveModel.this,Scheme.class));
            }
        });

    }

    public void getSchemesModel() {
DatabaseReference mydr=firebaseDatabase.child("schemes");
mydr.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
            for(DataSnapshot dataSnapshot2:dataSnapshot1.getChildren()){
                SchemeModel schemeModel=dataSnapshot2.getValue(SchemeModel.class);
                if(schemeModel.getIs_active()){
                    arr.add(schemeModel);
                }

            }
        }
        schemeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

    }
}
package com.example.vijaygarg.delagain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vijaygarg.delagain.Adapters.PromoterAdapter;
import com.example.vijaygarg.delagain.Model.PromoterModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DeleteUpdate extends AppCompatActivity {

    RecyclerView recyclerView;

    EditText idold,name,store,contact,date;
    Button remove,update,updatenow;
    DatabaseReference databaseReference;
    HashMap<String,PromoterModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);

        data=new HashMap<>();
        final PromoterAdapter promoterAdapter=new PromoterAdapter(this,data);

        recyclerView=findViewById(R.id.rvpromoter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(promoterAdapter);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("promoterinfo");

        idold=findViewById(R.id.updateid);
        name=findViewById(R.id.promotername);
        store=findViewById(R.id.storeassigned);
        contact=findViewById(R.id.contactnumber);
        date=findViewById(R.id.dateofjoin);

        update=findViewById(R.id.update);
        remove=findViewById(R.id.remove);
        updatenow=findViewById(R.id.updatedata);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setVisibility(View.VISIBLE);
                store.setVisibility(View.VISIBLE);
                contact.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                updatenow.setVisibility(View.VISIBLE);
                update.setVisibility(View.GONE);
                idold.setVisibility(View.GONE);
                remove.setVisibility(View.GONE);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(idold.getText().toString()).setValue(null);

            }
        });

        updatenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] sname,sstore,sdate,scontact;
                sname=new String[1];
                sstore=new String[1];
                sdate=new String[1];
                scontact=new String[1];
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                            String iod = idold.getText().toString();
                            if (dataSnapshot1.getKey().equals(iod)) {
                                PromoterModel promoterModel = dataSnapshot1.getValue(PromoterModel.class);
                                sname[0] = promoterModel.getName();
                                sstore[0] = promoterModel.getStore();
                                sdate[0] = promoterModel.getDate();
                                scontact[0] = promoterModel.getContact();
                                databaseReference.child(idold.getText().toString()).setValue(null);

                                if (name.getText().toString().length() > 0) {
                                    sname[0] = name.getText().toString();
                                }

                                if (store.getText().toString().length() > 0) {
                                    sstore[0] = store.getText().toString();

                                }
                                if (date.getText().toString().length() > 0) {
                                    sdate[0] = date.getText().toString();

                                }
                                if (contact.getText().toString().length() > 0) {
                                    scontact[0] = contact.getText().toString();

                                }
                                PromoterModel newdetail = new PromoterModel(sname[0], iod, scontact[0], sdate[0], sstore[0]);
                                databaseReference.child(iod).setValue(newdetail);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    data.put(dataSnapshot1.getValue(PromoterModel.class).getId(),dataSnapshot1.getValue(PromoterModel.class));
                }
                promoterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

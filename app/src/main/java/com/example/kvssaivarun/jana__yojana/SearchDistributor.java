package com.example.kvssaivarun.jana__yojana;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchDistributor extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Distributor> distributorList;
    DatabaseReference databasedistributor;
    ProgressDialog dialog;



EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search_distributor );
        recyclerView=findViewById( R.id.recyclerview );
        databasedistributor = FirebaseDatabase.getInstance().getReference( "Distributor" );
        distributorList=new ArrayList<>(  );
        search=findViewById( R.id.search_et );
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading Distributor Data..plz..wait");
        dialog.setCancelable(false);
        dialog.show();
        //distributorList=(List<Distributor>)getIntent().getSerializableExtra("distributorlist");
        recyclerView.addItemDecoration( new DividerItemDecoration( this,DividerItemDecoration.VERTICAL ) );

    }

    @Override
    protected void onResume() {
        super.onResume();
        databasedistributor.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                distributorList.clear();
                for (DataSnapshot distributorsnapshot : dataSnapshot.getChildren()) {
                    Distributor distributor = distributorsnapshot.getValue( Distributor.class );
                    distributorList.add( distributor );
                }
                dialog.dismiss();
                Adaptor adaptor= new Adaptor(SearchDistributor.this,distributorList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
                recyclerView.setAdapter( adaptor );

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }

    public void search(View view) {
        Intent intent= new Intent( SearchDistributor.this,SearchDistibutorProduct.class );
        String product=search.getText().toString();
        intent.putExtra( "product",product );
        startActivity( intent );
    }
}
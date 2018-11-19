package com.example.kvssaivarun.jana__yojana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFarmerProduct extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Worker> workerlist;
    DatabaseReference databaseworker;
    FProductAdapter fProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search_farmer_product );
        String product = getIntent().getStringExtra( "product" );
        recyclerView = findViewById( R.id.recyclerproduct );

        databaseworker = FirebaseDatabase.getInstance().getReference( "Worker" );
       // databaseworker.addListenerForSingleValueEvent( valueEventListener );


        Query query= FirebaseDatabase.getInstance().getReference( "Worker" ).orderByChild( "product" ).equalTo( product );


        query.addListenerForSingleValueEvent( valueEventListener );


        workerlist = new ArrayList<>();
        recyclerView.addItemDecoration( new DividerItemDecoration( this,DividerItemDecoration.VERTICAL ) );
    }

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                workerlist.clear();

                for (DataSnapshot workersnapshot : dataSnapshot.getChildren()) {
                    Worker worker = workersnapshot.getValue( Worker.class );
                    workerlist.add( worker );


                }
                if (workerlist.size()==0)
                Toast.makeText( SearchFarmerProduct.this, "SPECIFIED ITEM NOT FOUND", Toast.LENGTH_SHORT ).show();
                final FProductAdapter fProductAdapter = new FProductAdapter( SearchFarmerProduct.this, workerlist );
                recyclerView.setLayoutManager( new LinearLayoutManager( SearchFarmerProduct.this ) );
                recyclerView.setAdapter( fProductAdapter );
                fProductAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }












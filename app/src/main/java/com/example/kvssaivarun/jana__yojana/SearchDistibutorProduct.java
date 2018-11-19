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

public class SearchDistibutorProduct extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Distributor> distributorList;
    DatabaseReference databasedistributor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search_distibutor_product );
        String product = getIntent().getStringExtra( "product" );
        recyclerView = findViewById( R.id.recyclerdistributor );

        databasedistributor = FirebaseDatabase.getInstance().getReference( "Distributor" );
        // databaseworker.addListenerForSingleValueEvent( valueEventListener );


        Query query= FirebaseDatabase.getInstance().getReference( "Distributor" ).orderByChild( "product" ).equalTo( product );

        query.addListenerForSingleValueEvent( valueEventListener );

        distributorList = new ArrayList<>();
        recyclerView.addItemDecoration( new DividerItemDecoration( this,DividerItemDecoration.VERTICAL ) );
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            distributorList.clear();

            for (DataSnapshot distributorsnapshot : dataSnapshot.getChildren()) {
                Distributor distributor = distributorsnapshot.getValue( Distributor.class );
                distributorList.add( distributor );

            }
            if (distributorList.size()==0)
                Toast.makeText( SearchDistibutorProduct.this, "SPECIFIED ITEM NOT FOUND", Toast.LENGTH_SHORT ).show();
            final DistributorAdapter distributorAdapter = new DistributorAdapter( SearchDistibutorProduct.this, distributorList );
            recyclerView.setLayoutManager( new LinearLayoutManager( SearchDistibutorProduct.this ) );
            recyclerView.setAdapter( distributorAdapter );
            distributorAdapter.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}



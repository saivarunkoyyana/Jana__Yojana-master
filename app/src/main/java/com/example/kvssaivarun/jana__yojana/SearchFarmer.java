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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFarmer extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Worker>workerList;
    DatabaseReference databaseworker;
    ProgressDialog dialog;
    EditText search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search_farmer );
        recyclerView=findViewById( R.id.rec );
        databaseworker = FirebaseDatabase.getInstance().getReference( "Worker" );
        workerList=new ArrayList<>(  );
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading Farmer Data..plz..wait");
        dialog.setCancelable(false);
        dialog.show();
        search=findViewById( R.id.search_et );

        //workerList=(List<Worker>)getIntent().getSerializableExtra("workerlist");
        recyclerView.addItemDecoration( new DividerItemDecoration( this,DividerItemDecoration.VERTICAL ) );
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseworker.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                workerList.clear();

                for (DataSnapshot workersnapshot : dataSnapshot.getChildren()) {
                    Worker worker = workersnapshot.getValue( Worker.class );
                    workerList.add( worker );

                }
                dialog.dismiss();
                Toast.makeText(SearchFarmer.this, ""+workerList.size(), Toast.LENGTH_SHORT).show();
                MyAdapter myAdapter= new MyAdapter(SearchFarmer.this,workerList);

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
                recyclerView.setAdapter( myAdapter );

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );


    }

    public void search(View view) {
        Intent intent= new Intent( SearchFarmer.this,SearchFarmerProduct.class );
        String product=search.getText().toString();
        intent.putExtra( "product",product );
        startActivity( intent );

    }
}
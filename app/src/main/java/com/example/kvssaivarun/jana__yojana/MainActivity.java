package com.example.kvssaivarun.jana__yojana;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseworker, databasedistributor;
    List<Worker> workerList;
    List<Distributor>distributorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        //databaseworker = FirebaseDatabase.getInstance().getReference( "Worker" );
        //databasedistributor = FirebaseDatabase.getInstance().getReference( "Distributor" );
        workerList = new ArrayList<>();
        distributorList=new ArrayList<>(  );

    }

    @Override
    protected void onStart() {
        super.onStart();


    }



    public void registerfarmer(View view) {
        Intent intent = new Intent( MainActivity.this, RegisterFarmer.class );
        startActivity( intent );
    }

    public void registerdistributor(View view) {
        Intent intent = new Intent( MainActivity.this, RegisterDistributor.class );
        startActivity( intent );
    }

    public void searchforfarmer(View view)
    {
        Intent intent = new Intent( MainActivity.this, SearchFarmer.class );
        startActivity( intent );
    }

    public void searchfordistributor(View view) {
        Intent intent = new Intent( MainActivity.this, SearchDistributor.class );
        startActivity( intent );
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit??");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}

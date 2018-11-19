package com.example.kvssaivarun.jana__yojana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisterFarmer extends AppCompatActivity {
    EditText name,product,Quantity,price,phonenumber;
    Button save;
    DatabaseReference databaseworker;
    List<Worker> workerlist;
    ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_farmer );
        name=findViewById( R.id.name_et );
        product=findViewById( R.id.product_et );
        Quantity=findViewById( R.id.quantity_et );
        price=findViewById( R.id.price_et );
        phonenumber=findViewById( R.id.phnumber_et3 );
        save=findViewById( R.id.save_bt );
        workerlist= new ArrayList<>(  );

        int images[]={R.drawable.a ,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.f,R.drawable.g,R.drawable.h};

        viewFlipper=findViewById( R.id.v_flipper );

        for (int image: images){
            flipperImages( image );
        }


        databaseworker= FirebaseDatabase.getInstance().getReference("Worker");

        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addworker();
            }
        } );

    }


    public void flipperImages(int image){
        ImageView imageView=new ImageView( this );
        imageView.setBackgroundResource( image );
        viewFlipper.addView( imageView );
        viewFlipper.setFlipInterval( 2000 );
        viewFlipper.setAutoStart( true );

        viewFlipper.setInAnimation( this,android.R.anim.slide_in_left );
        viewFlipper.setOutAnimation( this,android.R.anim.slide_out_right );


    }


 /*   @Override
    protected void onStart() {
        super.onStart();
        databaseworker.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                workerlist.clear();

                for (DataSnapshot workersnapshot : dataSnapshot.getChildren() ){
                    Worker worker =workersnapshot.getValue(Worker.class);
                    workerlist.add( worker );




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }
*/
    private void addworker(){
        String W_name=name.getText().toString().trim();
        String W_product=product.getText().toString();
        String W_quantity=Quantity.getText().toString().trim();
        String W_price=price.getText().toString();
        String W_phnumber=phonenumber.getText().toString();


        if (!TextUtils.isEmpty( W_name )){
            String id=  databaseworker.push().getKey();

            Worker worker= new Worker(id,W_name,W_product,W_quantity,W_price,W_phnumber);

           databaseworker.child( id ).setValue( worker );

            Toast.makeText( this, "data saved", Toast.LENGTH_SHORT ).show();

        }
        else {
            Toast.makeText( this, "Enter name ", Toast.LENGTH_SHORT ).show();
        }


       if (TextUtils.isEmpty( W_product )) {
            Toast.makeText( this, "Enter Product Name", Toast.LENGTH_SHORT ).show();
        }


      if (TextUtils.isEmpty( W_quantity )) {
           Toast.makeText( this, "Enter Quantity Of Product Available", Toast.LENGTH_SHORT ).show();
        }
        if (TextUtils.isEmpty( W_price )) {
            Toast.makeText( this, "Enter Price of Product Per KG", Toast.LENGTH_SHORT ).show();
        }
        if (TextUtils.isEmpty( W_phnumber )) {
            Toast.makeText( this, "Enter phone number of the Farmer", Toast.LENGTH_SHORT ).show();
        }

   }

}
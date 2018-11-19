package com.example.kvssaivarun.jana__yojana;

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

import java.util.ArrayList;
import java.util.List;

public class RegisterDistributor extends AppCompatActivity {  EditText name,product,Quantity,price,phonenumber;
    Button save;
    DatabaseReference databasedistributor;
    List<Distributor> distributorList;
    ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_distributor );
        name=findViewById( R.id.name_et );
        product=findViewById( R.id.product_et );
        Quantity=findViewById( R.id.quantity_et );
        price=findViewById( R.id.price_et );
        phonenumber=findViewById( R.id.phnumber_et3 );
        save=findViewById( R.id.save_bt );
        distributorList= new ArrayList<>(  );

        int images[]={R.drawable.a ,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.f,R.drawable.g,R.drawable.h};

        viewFlipper=findViewById( R.id.v_flipper );

        for (int image: images){
            flipperImages( image );
        }


        databasedistributor= FirebaseDatabase.getInstance().getReference("Distributor");

        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddistributor();
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


  /*  @Override
    protected void onStart() {
        super.onStart();
        databasedistributor.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                distributorList.clear();

                for (DataSnapshot distributorsnapshot : dataSnapshot.getChildren() ){
                    Distributor distributor =distributorsnapshot.getValue(Distributor.class);
                    distributorList.add( distributor );




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }*/

    private void adddistributor(){
        String D_name=name.getText().toString().trim();
        String D_product=product.getText().toString();
        String D_quantity=Quantity.getText().toString().trim();
        String D_price=price.getText().toString();
        String D_phnumber=phonenumber.getText().toString();


        if (!TextUtils.isEmpty( D_name )){
            String id=  databasedistributor.push().getKey();

            Distributor distributor= new Distributor(id,D_name,D_product,D_quantity,D_price,D_phnumber);

            databasedistributor.child( id ).setValue( distributor );

            Toast.makeText( this, "data saved", Toast.LENGTH_SHORT ).show();

        }
        else {
            Toast.makeText( this, "Enter Distributor name ", Toast.LENGTH_SHORT ).show();
        }


        if (TextUtils.isEmpty( D_product )) {
            Toast.makeText( this, "Enter Required Product Name", Toast.LENGTH_SHORT ).show();
        }


        if (TextUtils.isEmpty( D_quantity )) {
            Toast.makeText( this, "Enter Approx Quantity Of Product Required", Toast.LENGTH_SHORT ).show();
        }
        if (TextUtils.isEmpty( D_price )) {
            Toast.makeText( this, "Enter Approx Purchase Price of Product Per KG ", Toast.LENGTH_SHORT ).show();
        }
        if (TextUtils.isEmpty( D_phnumber )) {
            Toast.makeText( this, "Enter phone number of the Distributor", Toast.LENGTH_SHORT ).show();
        }

    }

}
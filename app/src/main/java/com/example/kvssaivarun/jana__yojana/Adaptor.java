package com.example.kvssaivarun.jana__yojana;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class Adaptor extends RecyclerView.Adapter<Adaptor.ViewHolder> {
    Context context;
    List<Distributor>distributorList;

    public Adaptor(SearchDistributor searchDistributor, List<Distributor> distributorList) {
        this.context=searchDistributor;
        this.distributorList=distributorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from( context ).inflate( R.layout.roww,viewGroup,false );

        return  new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        StringBuilder stringBuilder = new StringBuilder();
        String name = distributorList.get( i ).getName();
        String product = distributorList.get( i ).getProduct();
        String quantity = distributorList.get( i ).getQuantity();
        String price = distributorList.get( i ).getPrice();
        String phone = distributorList.get( i ).getPhnumber();



        viewHolder.textView1.setText(" DISTRIBUTOR NAME  :"+ name );
        viewHolder.textView2.setText("REQUIRED PRODUCT NAME :"+ product );
        viewHolder.textView3.setText( "REQUIRED QUANTITY IN KG'S  :"+quantity );
        viewHolder.textView4.setText( "APPROX BUYING PRICE  :"+price );
        viewHolder.textView5.setText(  phone  );

    }

    @Override
    public int getItemCount() {
        return distributorList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3,textView4,textView5,textView6;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            textView1=itemView.findViewById( R.id.name_tv );
            textView2=itemView.findViewById( R.id.product_tv );
            textView3=itemView.findViewById( R.id.quantity_tv );
            textView4=itemView.findViewById( R.id.price_tv );
            textView5=itemView.findViewById( R.id.phnumber_tv );
            textView6=itemView.findViewById( R.id.call );
            textView6.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse( "tel:" + textView5.getText().toString() ) );
                    context.startActivity( intent );
                }
            } );

                }
        }
    }

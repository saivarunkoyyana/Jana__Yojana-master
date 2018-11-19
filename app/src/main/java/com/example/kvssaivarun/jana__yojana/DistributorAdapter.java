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

public class DistributorAdapter extends RecyclerView.Adapter<DistributorAdapter.ViewHolder> {

    Context context;
    List<Distributor> distributorList;

    public DistributorAdapter(SearchDistibutorProduct searchDistibutorProduct, List<Distributor> distributorList) {
        this.context=searchDistibutorProduct;
        this.distributorList=distributorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( context ).inflate( R.layout.column, viewGroup, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {

        String name = distributorList.get( position ).getName();
        String product = distributorList.get( position ).getProduct();
        String quantity = distributorList.get( position ).getQuantity();
        String price = distributorList.get( position ).getPrice();
        String phone = distributorList.get( position ).getPhnumber();
        viewholder.textView1.setText( " DISTRIBUTOR NAME  : "+name );
        viewholder.textView2.setText( "REQUIRED PRODUCT  :"+product );
        viewholder.textView3.setText("QUANTITY IN KG'S  :"+ quantity );
        viewholder.textView4.setText( "BUYING PRICE  :"+price );
        viewholder.textView5.setText( phone );


    }

    @Override
    public int getItemCount() {
        return distributorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView1, textView2, textView3, textView4, textView5,textView6;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            textView1 = itemView.findViewById( R.id.name_tv1 );
            textView2 = itemView.findViewById( R.id.product_tv1 );
            textView3 = itemView.findViewById( R.id.quantity_tv1 );
            textView4 = itemView.findViewById( R.id.price_tv1 );
            textView5 = itemView.findViewById( R.id.phnumber_tv );
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

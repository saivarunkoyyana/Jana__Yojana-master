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

class FProductAdapter extends RecyclerView.Adapter<FProductAdapter.VIEWHOLDER> {
    Context context;
    List<Worker> workerList;

    public FProductAdapter(SearchFarmerProduct searchFarmerProduct, List<Worker> workerlist) {
        this.context = searchFarmerProduct;
        this.workerList = workerlist;
    }

    @NonNull
    @Override
    public VIEWHOLDER onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( context ).inflate( R.layout.columnn, viewGroup, false );
        return new VIEWHOLDER( view );
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER viewholder, int position) {
        String name = workerList.get( position ).getName();
        String product = workerList.get( position ).getProduct();
        String quantity = workerList.get( position ).getQuantity();
        String price = workerList.get( position ).getPrice();
        String phone = workerList.get( position ).getPhnumber();
        viewholder.textView1.setText("FARMER NAME  :"+ name );
        viewholder.textView2.setText( "AVAILABLE PRODUCT  :"+product );
        viewholder.textView3.setText( "QUANTITY IN KG'S  :"+quantity );
        viewholder.textView4.setText( "SELLING PRICE  :"+price );
        viewholder.textView5.setText(  phone );

    }


    @Override
    public int getItemCount() {
        return workerList.size();
    }

    class VIEWHOLDER extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4, textView5,textView6;

        public VIEWHOLDER(@NonNull View itemView) {
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

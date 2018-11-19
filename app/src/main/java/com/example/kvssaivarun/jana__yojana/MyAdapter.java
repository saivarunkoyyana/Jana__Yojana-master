package com.example.kvssaivarun.jana__yojana;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<Worker> workerList;

    public MyAdapter(SearchFarmer searchFarmer, List<Worker> workerlist) {
        this.context = searchFarmer;
        this.workerList = workerlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( context ).inflate( R.layout.row, viewGroup, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        String name = workerList.get( position ).getName();
        String product = workerList.get( position ).getProduct();
        String quantity = workerList.get( position ).getQuantity();
        String price = workerList.get( position ).getPrice();
        String phone = workerList.get( position ).getPhnumber();
        //  stringBuilder.append( "name :" + name + "\t\t\t\t\t" + "Product :" + product + "\n" + "Quantity :" + quantity + "\n\n"+" Price :" +price +"\n\n"+ "PhoneNumber :" +phone + "\n\n" );
        //  viewHolder.textView.setText( stringBuilder.toString() );
        viewHolder.textView1.setText( "FARMER NAME  :" + name );
        viewHolder.textView2.setText( "AVAILABLE PRODUCT   :" + product );
        viewHolder.textView3.setText( "QUANTITY IN KG'S :" + quantity );
        viewHolder.textView4.setText( "SELLING PRICE  :" + price );
        viewHolder.textView5.setText(   phone);
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4, textView5,textView6;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            textView1 = itemView.findViewById( R.id.name_tv );
            textView2 = itemView.findViewById( R.id.product_tv );
            textView3 = itemView.findViewById( R.id.quantity_tv );
            textView4 = itemView.findViewById( R.id.price_tv );
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

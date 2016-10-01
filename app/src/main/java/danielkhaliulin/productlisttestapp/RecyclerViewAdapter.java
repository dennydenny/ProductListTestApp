package danielkhaliulin.productlisttestapp;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    List<Product> products;

    RecyclerViewAdapter(List<Product> products){
        this.products = products;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_card, viewGroup, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(String.valueOf(products.get(i).Name));
        personViewHolder.count.setText("Осталось: " + String.valueOf(products.get(i).Count));
        personViewHolder.cost.setText(String.valueOf(products.get(i).Cost));

        personViewHolder.cv.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
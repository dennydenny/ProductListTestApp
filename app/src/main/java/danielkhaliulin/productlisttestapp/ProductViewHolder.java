package danielkhaliulin.productlisttestapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView name;
    TextView count;
    TextView cost;

    ProductViewHolder(View itemView) {
        super(itemView);

        cv = (CardView)itemView.findViewById(R.id.cv);
        name = (TextView)itemView.findViewById(R.id.product_name);
        count = (TextView)itemView.findViewById(R.id.product_count);
        cost = (TextView)itemView.findViewById(R.id.product_cost);
    }

}

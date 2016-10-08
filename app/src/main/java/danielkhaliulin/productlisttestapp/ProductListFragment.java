package danielkhaliulin.productlisttestapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;


@EFragment(R.layout.fragment_product_list)
public class ProductListFragment extends Fragment {

    ArrayList<Product> products = new ArrayList<Product>();

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @ViewById(R.id.rv)
    RecyclerView rv;

    @AfterViews
    void makeview () {
        Log.i("makeview", "Making view...");
        this.setToolbar();
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);

        initializeData();
        initializeAdapter();
    }

    // TODO: Получать данные в другом потоке.
    public void initializeData(){
        try {
            Log.i("initializeData", "Data is initializing...");
            DataBaseHelper db = new DataBaseHelper(getActivity());
            products = db.GetProductList();
            Log.i("initializeData", "Data initializing is finished");
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initializeAdapter(){
        Log.i("initializeAdapter", "Adapter is initializing...");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(products);
        rv.setAdapter(adapter);
        Log.i("initializeAdapter", "Adapter is finished...");
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Bundle args = new Bundle();
                        Product product = products.get(position);
                        args.putInt("Cost", product.Cost);
                        args.putString("Name", product.Name);
                        args.putInt("Count", product.Count);
                        ProductDialog pd = new ProductDialog();
                        pd.setArguments(args);
                        pd.show(getActivity().getFragmentManager(), "ProductListFragment");
                    }
                })
        );
    }

    private void setToolbar ()
    {
        ((MainActivity_)getActivity()).getSupportActionBar().setTitle("Список товаров");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity_)getActivity()).showFab();
        Log.i("onResume()", "we are onResume()");
    }
}

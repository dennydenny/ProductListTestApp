package danielkhaliulin.productlisttestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

    @Click(R.id.fab)
    void OnClick() {
        Toast.makeText(getActivity(), "Works!!", Toast.LENGTH_SHORT).show();
    }

    @ViewById(R.id.rv)
    RecyclerView rv;

    @AfterViews
    void makeview () {

        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        products.add(new Product("Стул", 21, 4999));
        products.add(new Product("Стол", 123, 6999));
        products.add(new Product("Кресло", 5, 8999));
        products.add(new Product("Кресло", 5, 8992));
        products.add(new Product("Кресло", 5, 8993));
        products.add(new Product("Кресло", 5, 8994));
        products.add(new Product("Кресло", 5, 8995));
        products.add(new Product("Кресло", 5, 8996));
        products.add(new Product("Кресло", 5, 8997));
        products.add(new Product("Кресло", 5, 8998));
        products.add(new Product("Кресло!", 5, 89955));
    }

    private void initializeAdapter(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(products);
        rv.setAdapter(adapter);
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
                        pd.show(getActivity().getFragmentManager(), "TAG");
                    }
                })
        );
    }
}

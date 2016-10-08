package danielkhaliulin.productlisttestapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.system.ErrnoException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_product_edit)
public class ProductEditFragment extends Fragment {
    @ViewById
    EditText product_count_edit;

    @ViewById
    EditText product_cost_edit;

    @ViewById
    EditText product_name_edit;

    Product currentProduct;

    @Click
    void product_edit_save_btn ()
    {
        try
        {
            if (!isProductParamsCorrect())
            {
                Toast.makeText(getActivity(), "Пожалуйста, проверьте корректность параметров", Toast.LENGTH_LONG).show();
            }
            else
            {
                Product newProduct = new Product(
                        product_name_edit.getText().toString(),
                        Integer.valueOf(product_count_edit.getText().toString()),
                        Integer.valueOf(product_cost_edit.getText().toString()));
                updateProductInDB(currentProduct, newProduct);
                Toast.makeText(getActivity(), "Товар успешно обновлён!", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @AfterViews
    void makeview()
    {
        setToolbar();
        currentProduct = getProductFromBundle();
        setProductValues(currentProduct);
    }

    private void setToolbar ()
    {
        ((MainActivity_)getActivity()).getSupportActionBar()
                .setTitle(getResources().getString(R.string.ProductEditFragment_title));
    }

    private void setProductValues(Product product)
    {
        product_name_edit.setText(product.Name);
        product_cost_edit.setText(String.valueOf(product.Cost));
        product_count_edit.setText(String.valueOf(product.Count));
    }

    private Product getProductFromBundle ()
    {
        Bundle args = getArguments();
        return new Product(args.getString("Name"),args.getInt("Count"),args.getInt("Cost"));
    }

    // Метод, осуществляющий проверку входных параметров нового товара
    // и возвращающий истину, если параметры корректны.
    private boolean isProductParamsCorrect ()
    {
        // Проверка названия.
        if (product_name_edit.getText().toString().trim().length() == 0)
        {
            return false;
        }
        if (product_count_edit.getText().toString().trim().length() == 0
                || product_cost_edit.getText().toString().trim().length() == 0) {
            return false;
        }
        if (Integer.valueOf(product_count_edit.getText().toString()) < 0
                || Integer.valueOf(product_cost_edit.getText().toString()) < 0) {
            return false;
        }
        return true;
    }

    private void updateProductInDB (Product oldProduct, Product newProduct)
    {
        if (oldProduct != null && newProduct != null) {
            DataBaseHelper db = new DataBaseHelper(getActivity());
            db.UpdateProduct(oldProduct, newProduct);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
}

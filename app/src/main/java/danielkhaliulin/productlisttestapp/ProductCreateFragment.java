package danielkhaliulin.productlisttestapp;


import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_create_product)
public class ProductCreateFragment extends Fragment {

    @ViewById
    EditText product_name_create;

    @ViewById
    EditText product_count_create;

    @ViewById
    EditText product_cost_create;

    @Click
    public void product_create_save_btn()
    {
        try
        {
            if (!isProductParamsCorrect())
            {
                Toast.makeText(getActivity(), "Пожалуйста, проверьте корректность параметров", Toast.LENGTH_LONG).show();
            }
            else
            {
                Product product = new Product(
                        product_name_create.getText().toString(),
                        Integer.valueOf(product_count_create.getText().toString()),
                        Integer.valueOf(product_cost_create.getText().toString()));
                addProductToDB(product);
                Toast.makeText(getActivity(), "Товар успешно добавлен!", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @AfterViews
    void makeview ()
    {
        ((MainActivity_)getActivity()).hideFab();
        this.setToolbar();
    }

    private void setToolbar ()
    {
        ((MainActivity_)getActivity()).getSupportActionBar()
                .setTitle(getResources().getString(R.string.ProductCreateFragment_title));
    }

    // TODO: Метод, активирующий кнопку создания товара только тогда, когда введены все поля.
    private void enableButton()
    {}

    // Метод, осуществляющий отправку нового экземпляра товара в БД.
    private void addProductToDB (Product product)
    {
        DataBaseHelper db = new DataBaseHelper(getContext());
        db.CreateProduct(product);
    }

    // Метод, осуществляющий проверку входных параметров нового товара
    // и возвращающий истину, если параметры корректны.
    private boolean isProductParamsCorrect ()
    {
        // Проверка названия.
        if (product_name_create.getText().toString().trim().length() == 0)
        {
            return false;
        }
        if (product_count_create.getText().toString().trim().length() == 0
                || product_cost_create.getText().toString().trim().length() == 0) {
            return false;
        }
        if (Integer.valueOf(product_count_create.getText().toString()) < 0
                || Integer.valueOf(product_cost_create.getText().toString()) < 0) {
            return false;
        }
        return true;
    }
}

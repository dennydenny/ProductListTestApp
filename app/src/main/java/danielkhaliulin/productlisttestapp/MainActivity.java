package danielkhaliulin.productlisttestapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.frame)
    FrameLayout frame;

    ProductListFragment productListFragment = new ProductListFragment_();
    FragmentTransaction fTrans;

    @Click
    public void fab()
    {
        Toast.makeText(this, "wow!", Toast.LENGTH_SHORT).show();
    }

    @AfterViews
    void makeView ()
    {
        this.setToolbar();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frame, productListFragment);
        fTrans.commit();
    }

    private void setToolbar ()
    {
        setSupportActionBar(toolbar);
        setTitle("Список товаров");
    }
    
}

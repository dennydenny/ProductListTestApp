package danielkhaliulin.productlisttestapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

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

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    ProductListFragment productListFragment = new ProductListFragment_();
    FragmentTransaction fTrans;

    @Click
    public void fab()
    {
        ProductCreateFragment productCreateFragment = new ProductCreateFragment_();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.frame, productCreateFragment);
        fTrans.addToBackStack(null);
        fTrans.commit();
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
        setTitle("");
    }

    // Метод, скрывающий плаваюущую кнопку.
    public void hideFab ()
    {
        fab.hide();
    }

    // Метод, показывающий плаваюущую кнопку.
    public void showFab ()
    {
        fab.show();
    }

    // Метод, для замены текущего открытого фрагмента.
    public void replaceFragment (Fragment fragment)
    {
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.frame, fragment);
        fTrans.addToBackStack(null);
        fTrans.commit();
    }
}

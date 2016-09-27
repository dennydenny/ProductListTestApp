package danielkhaliulin.productlisttestapp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @Click
    public void fab()
    {
        Toast.makeText(this, "wow!", Toast.LENGTH_SHORT).show();
    }

    @AfterViews
    void setToolbar ()
    {
        setSupportActionBar(toolbar);
        setTitle("Список товаров");
    }
    
}

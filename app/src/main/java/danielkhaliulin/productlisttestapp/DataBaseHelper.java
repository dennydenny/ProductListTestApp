package danielkhaliulin.productlisttestapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataBaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "ProductsDB.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<Product> GetProductList ()
    {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = "SELECT name, count, cost FROM products";

            Cursor c = db.rawQuery(query, null);
            ArrayList<Product> result = new ArrayList<Product>();
            while (c.moveToNext()) {
                String _name = c.getString(c.getColumnIndex("name"));
                int _count = c.getInt(c.getColumnIndex("count"));
                int _cost = c.getInt(c.getColumnIndex("cost"));

                result.add(new Product(_name, _count, _cost));
            }
            return result;
        }
        catch (Exception e)
        {
            Log.e("GetProductList", e.getMessage());
            throw e;
        }
    }
}

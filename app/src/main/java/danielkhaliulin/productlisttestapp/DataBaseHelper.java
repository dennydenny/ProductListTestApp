package danielkhaliulin.productlisttestapp;

import android.content.ContentValues;
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

    // Возвращает список всех продуктов из БД.
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
            c.close();
            return result;
        }
        catch (Exception e)
        {
            Log.e("GetProductList", e.getMessage());
            throw e;
        }
    }

    // Метод, осуществляющий создание переданного товара.
    public void CreateProduct (Product product)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", product.Name);
            contentValues.put("count", product.Count);
            contentValues.put("cost", product.Cost);
            db.insert("products", null, contentValues);
        }
        catch (Exception e)
        {
            Log.e("CreateProduct", e.getMessage());
            throw e;
        }
    }

    // Метод, осуществляющий удаление продукта.
    public void DeleteProduct (Product product)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete("products", "name = ? AND cost = ? AND count = ?",
                    new String[] { product.Name,
                            String.valueOf(product.Cost),
                            String.valueOf(product.Count)});
        }
        catch (Exception e) {
            Log.e("DeleteProduct", e.getMessage());
            throw e;
        }
    }
}

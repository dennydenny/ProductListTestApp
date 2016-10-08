package danielkhaliulin.productlisttestapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.app.DialogFragment;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;


public class ProductDialog extends DialogFragment implements OnClickListener {

    int cost;
    int count;
    String name;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle mArgs = getArguments();
        cost = mArgs.getInt("Cost");
        count = mArgs.getInt("Count");
        name = mArgs.getString("Name");

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setPositiveButton("Редактировать", this)
                .setNegativeButton("Удалить", this)
                .setMessage("Товар: " + name + "\nЦена: " + cost + "\nОстаток: " + count);
        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.i("ProductDialog", String.valueOf(which));
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                break;
            case Dialog.BUTTON_NEGATIVE:
                try {
                    DataBaseHelper db = new DataBaseHelper(getActivity());
                    db.DeleteProduct(new Product(name, count, cost));
                    Toast.makeText(getActivity(), "Товар успешно удалён!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}


package danielkhaliulin.productlisttestapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.app.DialogFragment;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;


public class ProductDialog extends DialogFragment implements OnClickListener {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle mArgs = getArguments();
        int cost = mArgs.getInt("Cost");
        int count = mArgs.getInt("Count");
        String name = mArgs.getString("Name");

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setPositiveButton("Редактировать", this)
                .setNegativeButton("Удалить", this)
                .setMessage("Товар: " + name + "\nЦена: " + cost + "\nОстаток: " + count);
        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        Log.i("ProductDialog", String.valueOf(which));
        /*
        switch (which) {

            case Dialog.BUTTON_POSITIVE:
                break;
            case Dialog.BUTTON_NEGATIVE:
                i = R.string.no;
                break;
            case Dialog.BUTTON_NEUTRAL:
                i = R.string.maybe;
                break;
                */
        }

}


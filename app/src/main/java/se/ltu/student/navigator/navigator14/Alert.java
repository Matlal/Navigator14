package se.ltu.student.navigator.navigator14;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Alert class
 * this is the pop-up when you press a object in the list.
 *
 */

public class Alert extends DialogFragment {
    public AlertDialog alertDialogStores;
    public String title;

    //Include all information from one item in the List
    public ObjectItem listItem;
    public AdapterView<?> Adapter;
    //Constructor
    public Alert(ObjectItem listItem, String listItemText) {
        this.title = listItemText;
        this.listItem = listItem;


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.popup,null);
        builder1.setView(view);


        builder1.setTitle(title);
        builder1.setMessage("Vill du:");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Visa Rutt",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                           listItem.callBack(true);

                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton("Dölj Rutt",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listItem.callBack(false);
                        dialog.cancel();
                    }
                });


        AlertDialog dialog = builder1.create();
        return dialog;
    }

}

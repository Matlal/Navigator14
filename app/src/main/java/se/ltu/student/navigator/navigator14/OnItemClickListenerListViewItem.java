package se.ltu.student.navigator.navigator14;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;


/**
 * OnItemClickListenerListViewItem class
 *
 */
 // Here you can control what to do next when the user selects an item
 public class OnItemClickListenerListViewItem implements OnItemClickListener{
    Alert myAlert;
    boolean test = false;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewItem));

        // get the clicked item name
        String listItemText = textViewItem.getText().toString();

        // get the clicked item ID
        ObjectItem listItem = (ObjectItem) textViewItem.getTag();
        int listItemIdInt = textViewItem.getId();

        // do what you want with the object, for now: toast
        Toast.makeText(context, "Item: " + listItem.itemId + ", Item ID: " + listItem.itemName.toString(), Toast.LENGTH_SHORT).show();




        //Bygger ALERTPOPUP,

        myAlert = new Alert(listItem, listItemText);
        myAlert.show(((MapsActivity) context).getFragmentManager(), "My Alert");





    }


}

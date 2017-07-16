package com.example.abhinabera.jainmarketing_order;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditItem extends Activity {

    AutoCompleteTextView searchItem;
    EditText itemName, sellingRate;
    Button edit, save, delete;

    ItemDatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        myDb = new ItemDatabaseHelper(this);

        searchItem = (AutoCompleteTextView)findViewById(R.id.searchItem);
        itemName = (EditText)findViewById(R.id.itemName);
        sellingRate = (EditText)findViewById(R.id.sellingRate);
        edit = (Button)findViewById(R.id.edit);
        save = (Button)findViewById(R.id.save);
        delete = (Button)findViewById(R.id.delete);

        ItemDatabaseHelper itemDatabaseHelper = new ItemDatabaseHelper(this);

        ArrayList<String> allItemNames = itemDatabaseHelper.getAllItems();
        ArrayAdapter<String> adaAllItems = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allItemNames);
        searchItem.setAdapter(adaAllItems);

        UpdateData();
        deleteData();

    }

    public void UpdateData(){

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemName.setText(searchItem.getText().toString());
                ArrayList<Double> selling_rate = myDb.getSellingRate(itemName.getText().toString());
                Double sellrate = selling_rate.get(0);
                sellingRate.setText(sellrate.toString());
                searchItem.setText("");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = myDb.updateData(itemName.getText().toString(), Double.parseDouble(sellingRate.getText().toString()));
                if(isUpdate == true){
                    Toast.makeText(EditItem.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(EditItem.this, "Data not Updated", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void deleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRow = myDb.deleteData(searchItem.getText().toString());
                if(deletedRow > 0){
                    Toast.makeText(EditItem.this, "Item deleted", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(EditItem.this, "Unable to delete", Toast.LENGTH_SHORT).show();
                searchItem.setText("");
                Intent i = new Intent(EditItem.this, AddItem.class);
                startActivity(i);
            }
        });
    }
}

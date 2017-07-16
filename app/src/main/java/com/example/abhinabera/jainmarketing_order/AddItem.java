package com.example.abhinabera.jainmarketing_order;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddItem extends Activity {

    EditText itemName, sellingRate;
    Button add;

    ItemDatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemName = (EditText)findViewById(R.id.itemName);
        sellingRate = (EditText) findViewById(R.id.sellingRate);
        add = (Button) findViewById(R.id.add);

        myDb = new ItemDatabaseHelper(this);

        AddData();
    }

    public void AddData(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item_name = itemName.getText().toString();
                double selling_rate = Double.parseDouble(sellingRate.getText().toString());
                boolean isInserted  = myDb.insertData(item_name, selling_rate);
                if(isInserted == true)
                    Toast.makeText(AddItem.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(AddItem.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

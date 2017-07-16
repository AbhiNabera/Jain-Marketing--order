package com.example.abhinabera.jainmarketing_order;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Items extends Activity {

    Button additem, edititem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        additem = (Button)findViewById(R.id.additem);
        edititem = (Button)findViewById(R.id.edititem);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Items.this, AddItem.class);
                startActivity(i);
            }
        });

        edititem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Items.this, EditItem.class);
                startActivity(i);
            }
        });

    }
}

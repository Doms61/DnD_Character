package com.example.dnd;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Create_item extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    EditText item_name;
    EditText item_qty;
    EditText item_desc;

    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        item_name = findViewById(R.id.item_edit_name);
        item_qty = findViewById(R.id.item_edit_qty);
        item_desc = findViewById(R.id.item_edit_desc);


        Intent i = getIntent();


        if(i.hasExtra("item")) {
            item = (Item)i.getSerializableExtra("item");
            editItem();
        }
    }

    private void editItem() {
        item_name.setText(item.getItem_name());
        item_desc.setText(item.getItem_desc());
        item_qty.setText(item.getItem_qty());
    }

    public void saveItem(View view) {
        Item item = new Item(item_name.getText().toString(),
                item_qty.getText().toString(),
                item_desc.getText().toString());

        db.collection(currentUser.getEmail()).document("Inventory").collection("Inventory").document(item.getItem_name()).set(item);

        /**
         * Safety precaution, for uploading to the google firebase
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Create_item.this, Inventory.class);
                startActivity(intent);
            }
        }, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle("Create item");
        return true;
    }

    /**
     * On Item select from the menu change activity to the respective one
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.skills:
                intent = new Intent(this, Skillset.class);
                startActivity(intent);
                return true;
            case R.id.spells:
                intent = new Intent(this, Spells.class);
                startActivity(intent);
                return true;
            case R.id.inventory:
                intent = new Intent(this, Inventory.class);
                startActivity(intent);
                return true;
            case R.id.weapons:
                intent = new Intent(this, Equipments.class);
                startActivity(intent);
                return true;
            case R.id.overal_profile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.dnd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Item_activity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseFirestore db;

    TextView item_name;
    EditText item_qty;
    TextView item_desc;

    Item item;

    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabDelete;
    private LinearLayout layoutFabEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_activity);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Intent i = getIntent();
        item = (Item)i.getSerializableExtra("item");

        item_name = findViewById(R.id.item_name_name);
        item_qty = findViewById(R.id.item_qty_qty);
        item_desc = findViewById(R.id.item_desc_desc);

        item_name.setText(item.getItem_name());
        item_qty.setText(item.getItem_qty());
        item_desc.setText(item.getItem_desc());

        fabSettings = this.findViewById(R.id.fabSetting);

        layoutFabDelete =  this.findViewById(R.id.layoutFabDelete);
        layoutFabEdit =  this.findViewById(R.id.layoutFabEdit);

        layoutFabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabDelete();
            }
        });

        layoutFabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabEdit();
            }
        });

        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });

        closeSubMenusFab();
    }

    @Override
    protected void onPause() {
        super.onPause();
        DocumentReference updateRef = db.collection(mAuth.getCurrentUser().getEmail()).document("Inventory").collection("Inventory").document(item.getItem_name());
        updateRef.update("item_qty", item_qty.getText().toString());
    }

    /**
     * onStop update the firebase with the two values
     */
    @Override
    protected void onStop() {
        super.onStop();

        DocumentReference updateRef = db.collection(mAuth.getCurrentUser().getEmail()).document("Inventory").collection("Inventory").document(item.getItem_name());
        updateRef.update("item_qty", item_qty.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle(item.getItem_name());
        return true;
    }

    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabDelete.setVisibility(View.INVISIBLE);
        layoutFabEdit.setVisibility(View.INVISIBLE);
        fabSettings.setImageResource(R.drawable.ic_settings_black_24dp);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabDelete.setVisibility(View.VISIBLE);
        layoutFabEdit.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fabSettings.setImageResource(R.drawable.ic_close_black_24dp);
        fabExpanded = true;
    }

    private void fabDelete() {
        DocumentReference deleteRef = db.collection(mAuth.getCurrentUser().getEmail()).document("Inventory").collection("Inventory").document(item.getItem_name());
        deleteRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent i = new Intent(Item_activity.this, Inventory.class);
                startActivity(i);
            }
        });
    }

    private void fabEdit() {
        intent = new Intent (this, Create_item.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    /**
     * On Item select from the menu change activity to the respective one
     *
     * @param item
     * @return
     */
    Intent intent;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }
}

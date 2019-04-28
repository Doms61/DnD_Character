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

public class Create_equipment extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    EditText equi_name;
    EditText equi_type;
    EditText equi_dmg;
    EditText equi_dmgType;
    EditText equi_def;
    EditText equi_desc;

    Equipment e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_equipment);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        equi_name = findViewById(R.id.edit_equi_name);
        equi_type = findViewById(R.id.edit_equi_type);
        equi_dmg = findViewById(R.id.edit_equi_dmg);
        equi_dmgType = findViewById(R.id.edit_equi_dmgType);
        equi_def = findViewById(R.id.edit_equi_def);
        equi_desc = findViewById(R.id.edit_equi_desc);

        Intent i = getIntent();

        if(i.hasExtra("equi")) {
            e = (Equipment) i.getSerializableExtra("equi");
            editEquipment();
        }

    }

    private void editEquipment() {
        equi_name.setText(e.getName());
        equi_type.setText(e.getType());
        equi_dmg.setText(e.getDmg());
        equi_dmgType.setText(e.getDmgType());
        equi_def.setText(e.getDef());
        equi_desc.setText(e.getDesc());
    }

    public void saveEquipment(View view) {
        Equipment equipment = new Equipment(equi_name.getText().toString(),
                equi_type.getText().toString(),
                equi_dmg.getText().toString(),
                equi_dmgType.getText().toString(),
                equi_def.getText().toString(),
                equi_desc.getText().toString());

        db.collection(currentUser.getEmail()).document("Equipment").collection("Equipment").document(equipment.getName()).set(equipment);

        /**
         * Safety precaution, for uploading to the google firebase
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Create_equipment.this, Equipments.class);
                startActivity(intent);
            }
        }, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle("Create equipment");
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

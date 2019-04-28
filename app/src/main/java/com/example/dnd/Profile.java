package com.example.dnd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Profile extends AppCompatActivity {
    /**
     * Mandatory
     * TODO: Correct the overlap on a smaller screen/more responsive
     * TODO: Comment out the code
     */

    /**
     * Extra, if time allows
     * TODO: Clean up the code
     * TODO: Test everything on both PC and phone
     * TODO: Add edit and delete on inventory, spells, skills, equipment and character
     */

    /**
     * Firebase necessary stuff
     */
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseFirestore db;
    Character character;
    /**
     * All the text views, to hold some changable values
     */
    TextInputEditText current_hp_hp;
    TextInputEditText current_mana_mana;

    TextView char_class;
    TextView char_race;
    TextView char_level;
    TextView char_alignment;
    TextView char_initiative;
    TextView char_str_value;
    TextView char_str_mod;
    TextView char_dex_value;
    TextView char_dex_mod;
    TextView char_con_value;
    TextView char_con_mod;
    TextView char_int_value;
    TextView char_int_mod;
    TextView char_wis_value;
    TextView char_wis_mod;
    TextView char_char_value;
    TextView char_char_mod;
    TextView char_max_hp;
    TextView char_max_mana;
    TextView char_armor_class;
    TextView char_speed;
    TextView char_prof;

    Intent intent;
    /**
     * onCreate get database instance and retrieve the data
     * onSuccess load the values to their respective fields
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        char_class = findViewById(R.id.class_class);
        char_race = findViewById(R.id.race_race);
        char_level = findViewById(R.id.level_level);
        char_alignment = findViewById(R.id.alignment_alignment);
        char_initiative = findViewById(R.id.initiative_initiative);
        char_str_value = findViewById(R.id.str_str);
        char_str_mod = findViewById(R.id.str_mod);
        char_dex_value = findViewById(R.id.dex_dex);
        char_dex_mod = findViewById(R.id.dex_mod);
        char_con_value = findViewById(R.id.con_con);
        char_con_mod = findViewById(R.id.con_mod);
        char_int_value = findViewById(R.id.int_int);
        char_int_mod = findViewById(R.id.int_mod);
        char_wis_value = findViewById(R.id.wis_wis);
        char_wis_mod = findViewById(R.id.wis_mod);
        char_char_value = findViewById(R.id.char_char);
        char_char_mod = findViewById(R.id.char_mod);
        char_max_hp = findViewById(R.id.max_hp_hp);
        current_hp_hp = findViewById(R.id.current_hp_hp);
        char_max_mana = findViewById(R.id.max_mana_mana);
        current_mana_mana = findViewById(R.id.current_mana_mana);
        char_armor_class = findViewById(R.id.ac_ac);
        char_speed = findViewById(R.id.speed_speed);
        char_prof = findViewById(R.id.proficiency_prof);


        db.collection(currentUser.getEmail()).document("Character").collection("Character").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference docRef = db.collection(currentUser.getEmail()).document("Character").collection("Character").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        character = documentSnapshot.toObject(Character.class);

                                        char_class.setText(character.getChar_class());
                                        char_race.setText(character.getRace());
                                        char_level.setText(character.getLevel());
                                        char_alignment.setText(character.getAlignment());
                                        char_initiative.setText(character.getInitiative());
                                        char_str_value.setText(character.getStr_value());
                                        char_str_mod.setText(character.getStr_mod());
                                        char_dex_value.setText(character.getDex_value());
                                        char_dex_mod.setText(character.getDex_mod());
                                        char_con_value.setText(character.getCon_value());
                                        char_con_mod.setText(character.getCon_mod());
                                        char_int_value.setText(character.getInt_value());
                                        char_int_mod.setText(character.getInt_mod());
                                        char_wis_value.setText(character.getWis_value());
                                        char_wis_mod.setText(character.getWis_mod());
                                        char_char_value.setText(character.getChar_value());
                                        char_char_mod.setText(character.getChar_mod());
                                        char_max_hp.setText(character.getMax_hp());
                                        current_hp_hp.setText(character.getCurrent_hp());
                                        char_max_mana.setText(character.getMax_mana());
                                        current_mana_mana.setText(character.getCurrent_mana());
                                        char_armor_class.setText(character.getArmor_class());
                                        char_speed.setText(character.getSpeed());
                                        char_prof.setText(character.getProficiency());
                                    }
                                });
                            }
                        }
                    }
                });
    }


    /**
     * On pause just store the two main things, which changes the most locally.
     */
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("DnDPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("hp", current_hp_hp.getText().toString());
        editor.putString("mana", current_mana_mana.getText().toString());
        editor.apply();
    }

    /**
     * onStop update the firebase with the two values
     */
    @Override
    protected void onStop() {
        super.onStop();

        db.collection(currentUser.getEmail()).document("Character").collection("Character").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference updateRef = db.collection(currentUser.getEmail()).document("Character").collection("Character").document(document.getId());
                                updateRef.update("current_hp", current_hp_hp.getText().toString(),
                                        "current_mana", current_mana_mana.getText().toString());
                            }
                        }
                    }
                });
    }

    /**
     * onResume retrieve the values from the local storage
     */
    @Override
    protected void onResume() {
        super.onResume();

        currentUser = mAuth.getCurrentUser();
        SharedPreferences preferences = getSharedPreferences("DnDPreferences", MODE_PRIVATE);

        current_hp_hp = findViewById(R.id.current_hp_hp);
        String hp = preferences.getString("hp", "N/A");
        current_hp_hp.setText(hp);

        current_mana_mana = findViewById(R.id.current_mana_mana);
        String mana = preferences.getString("mana", "N/A");
        current_mana_mana.setText(mana);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        getMenuInflater().inflate(R.menu.drop_menu, menu);

        db.collection(currentUser.getEmail()).document("Character").collection("Character").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference docRef = db.collection(currentUser.getEmail()).document("Character").collection("Character").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        character = documentSnapshot.toObject(Character.class);
                                        setTitle(character.getName());
                                    }
                                });
                            }
                        }
                    }
                });
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

    /**
     * Image button clicks
     * note: Skill, inventory and weapons to be implemented
     * @param view
     */
    public void inventoryBTN(View view) {
        intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }

    public void spellsBTN(View view) {
        intent = new Intent(this, Spells.class);
        startActivity(intent);
    }

    public void skillsBTN(View view) {
        intent = new Intent(this, Skillset.class);
        startActivity(intent);
    }

    public void weapondBTN(View view) {
        intent = new Intent(this, Equipments.class);
        startActivity(intent);
    }
}

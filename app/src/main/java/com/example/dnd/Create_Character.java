package com.example.dnd;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Create_Character extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    EditText new_name;
    EditText new_class;
    EditText new_race;
    EditText new_level;
    EditText new_alignment;
    EditText new_initiative;
    EditText new_str_value;
    EditText new_str_mod;
    EditText new_dex_value;
    EditText new_dex_mod;
    EditText new_con_value;
    EditText new_con_mod;
    EditText new_int_value;
    EditText new_int_mod;
    EditText new_wis_value;
    EditText new_wis_mod;
    EditText new_char_value;
    EditText new_char_mod;
    EditText new_max_hp;
    EditText new_current_hp;
    EditText new_max_mana;
    EditText new_current_mana;
    EditText new_armor_class;
    EditText new_speed;
    EditText new_prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__character);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        new_name = findViewById(R.id.new_edit_name);
        new_class = findViewById(R.id.new_edit_class);
        new_race = findViewById(R.id.new_edit_race);
        new_level = findViewById(R.id.new_edit_level);
        new_alignment = findViewById(R.id.new_edit_alignment);
        new_initiative = findViewById(R.id.new_edit_initiative);
        new_str_value = findViewById(R.id.new_edit_str_value);
        new_str_mod = findViewById(R.id.new_edit_str_mod);
        new_dex_value = findViewById(R.id.new_edit_dex_value);
        new_dex_mod = findViewById(R.id.new_edit_dex_mod);
        new_con_value = findViewById(R.id.new_edit_con_value);
        new_con_mod = findViewById(R.id.new_edit_con_mod);
        new_int_value = findViewById(R.id.new_edit_int_value);
        new_int_mod = findViewById(R.id.new_edit_int_mod);
        new_wis_value = findViewById(R.id.new_edit_wis_value);
        new_wis_mod = findViewById(R.id.new_edit_wis_mod);
        new_char_value = findViewById(R.id.new_edit_char_value);
        new_char_mod = findViewById(R.id.new_edit_char_mod);
        new_max_hp = findViewById(R.id.new_edit_max_hp);
        new_current_hp = findViewById(R.id.new_edit_current_hp);
        new_max_mana = findViewById(R.id.new_edit_current_mana);
        new_current_mana = findViewById(R.id.new_edit_current_mana);
        new_armor_class = findViewById(R.id.new_edit_armor_class);
        new_speed = findViewById(R.id.new_edit_speed);
        new_prof = findViewById(R.id.new_edit_prof);
    }

    /**
     * on save, creating a new Character object by retrieving the values and the converting them to String
     * afterwards saving the object in firebase
     * @param view
     */
    public void character_save(View view) {
        Character character = new Character(new_name.getText().toString(),
                new_class.getText().toString(),
                new_race.getText().toString(),
                new_level.getText().toString(),
                new_alignment.getText().toString(),
                new_initiative.getText().toString(),
                new_str_value.getText().toString(),
                new_str_mod.getText().toString(),
                new_dex_value.getText().toString(),
                new_dex_mod.getText().toString(),
                new_con_value.getText().toString(),
                new_con_mod.getText().toString(),
                new_int_value.getText().toString(),
                new_int_mod.getText().toString(),
                new_wis_value.getText().toString(),
                new_wis_mod.getText().toString(),
                new_char_value.getText().toString(),
                new_char_mod.getText().toString(),
                new_max_hp.getText().toString(),
                new_current_hp.getText().toString(),
                new_max_mana.getText().toString(),
                new_current_mana.getText().toString(),
                new_armor_class.getText().toString(),
                new_speed.getText().toString(),
                new_prof.getText().toString());

        db.collection(currentUser.getEmail()).document("Character").collection("Character").document(character.getName()).set(character);


        /**
         * Safety precaution, for uploading to the google firebase
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Create_Character.this, Profile.class);
                startActivity(intent);
            }
        }, 100);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("Create character");
        return true;
    }
}

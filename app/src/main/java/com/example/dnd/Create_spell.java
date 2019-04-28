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

public class Create_spell extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    EditText new_spell_name;
    EditText new_spell_mana;
    EditText new_spell_duration;
    EditText new_spell_CastTime;
    EditText new_spell_range;
    EditText new_spell_components;
    EditText new_spell_effect;
    EditText new_spell_description;
    Spell spell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spell);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        new_spell_name = findViewById(R.id.new_spell_name);
        new_spell_mana = findViewById(R.id.new_spell_mana);
        new_spell_duration = findViewById(R.id.new_spell_duration);
        new_spell_CastTime = findViewById(R.id.new_spell_castTime);
        new_spell_range = findViewById(R.id.new_spell_range);
        new_spell_components = findViewById(R.id.new_spell_components);
        new_spell_effect = findViewById(R.id.new_spell_effect);
        new_spell_description = findViewById(R.id.new_spell_description);

        Intent i = getIntent();

        if(i.hasExtra("spell")) {
            spell = (Spell)i.getSerializableExtra("spell");
            editSpell();
        }
    }

    private void editSpell() {
        new_spell_name.setText(spell.getSpellName());
        new_spell_mana.setText(spell.getSpellMana());
        new_spell_duration.setText(spell.getSpellDuration());
        new_spell_CastTime.setText(spell.getSpellCastTime());
        new_spell_range.setText(spell.getSpellRange());
        new_spell_components.setText(spell.getSpellComponents());
        new_spell_effect.setText(spell.getSpellEffect());
        new_spell_description.setText(spell.getSpellDescription());
    }

    public void createSpell(View view) {
        Spell spell = new Spell (new_spell_name.getText().toString(),
                new_spell_mana.getText().toString(),
                new_spell_duration.getText().toString(),
                new_spell_CastTime.getText().toString(),
                new_spell_range.getText().toString(),
                new_spell_components.getText().toString(),
                new_spell_effect.getText().toString(),
                new_spell_description.getText().toString());

        db.collection(currentUser.getEmail()).document("Spells").collection("Spells").document(spell.getSpellName()).set(spell);

        /**
         * Safety precaution, for uploading to the google firebase
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Create_spell.this, Spells.class);
                startActivity(intent);
            }
        }, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle("Create spell");
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

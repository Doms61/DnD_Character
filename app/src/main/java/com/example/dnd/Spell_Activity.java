package com.example.dnd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Spell_Activity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseFirestore db;

    TextView spell_name;
    TextView spell_mana;
    TextView spell_duration;
    TextView spell_castTime;
    TextView spell_range;
    TextView spell_components;
    TextView spell_effect;
    TextView spell_description;

    Spell spell;

    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabDelete;
    private LinearLayout layoutFabEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Intent i = getIntent();
        spell = (Spell)i.getSerializableExtra("spell");

        spell_name = findViewById(R.id.spell_name_name);
        spell_mana = findViewById(R.id.spell_mana_mana);
        spell_duration = findViewById(R.id.spell_duration_duration);
        spell_castTime = findViewById(R.id.spell_castingTime_castingTime);
        spell_range = findViewById(R.id.spell_range_range);
        spell_components = findViewById(R.id.spell_components_components);
        spell_effect = findViewById(R.id.spell_effects_effects);
        spell_description = findViewById(R.id.spell_desc_desc);


        spell_name.setText(spell.getSpellName());
        spell_mana.setText(spell.getSpellMana());
        spell_duration.setText(spell.getSpellDuration());
        spell_castTime.setText(spell.getSpellCastTime());
        spell_range.setText(spell.getSpellRange());
        spell_components.setText(spell.getSpellComponents());
        spell_effect.setText(spell.getSpellEffect());
        spell_description.setText(spell.getSpellDescription());

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
        DocumentReference deleteRef = db.collection(mAuth.getCurrentUser().getEmail()).document("Spells").collection("Spells").document(spell.getSpellName());
        deleteRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent i = new Intent(Spell_Activity.this, Inventory.class);
                startActivity(i);
            }
        });
    }

    private void fabEdit() {
        Intent intent = new Intent (this, Create_spell.class);
        intent.putExtra("spell", spell);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle(spell.getSpellName());
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

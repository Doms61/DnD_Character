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

public class Skill_activity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseFirestore db;

    TextView skill_name;
    TextView skill_desc;

    Skill skill;

    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabDelete;
    private LinearLayout layoutFabEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_activity);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Intent i = getIntent();
        skill = (Skill)i.getSerializableExtra("skill");

        skill_name = findViewById(R.id.skill_name_name);
        skill_desc = findViewById(R.id.skill_desc_desc);

        skill_name.setText(skill.getName());
        skill_desc.setText(skill.getDescription());

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
        DocumentReference deleteRef = db.collection(mAuth.getCurrentUser().getEmail()).document("Skill").collection("Skill").document(skill.getName());
        deleteRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent i = new Intent(Skill_activity.this, Inventory.class);
                startActivity(i);
            }
        });
    }

    private void fabEdit() {
        intent = new Intent (this, Create_skill.class);
        intent.putExtra("skill", skill);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle(skill.getName());
        return true;
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
}

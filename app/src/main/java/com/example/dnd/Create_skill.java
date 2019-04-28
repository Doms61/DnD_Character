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

public class Create_skill extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    EditText skill_name;
    EditText skill_desc;

    Skill skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_skill);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        skill_name = findViewById(R.id.edit_skill_name);
        skill_desc = findViewById(R.id.edit_skill_desc);

        Intent i = getIntent();

        if(i.hasExtra("skill")) {
            skill = (Skill)i.getSerializableExtra("skill");
            editSkill();
        }
    }

    private void editSkill() {
        skill_name.setText(skill.getName());
        skill_desc.setText(skill.getDescription());
    }

    public void saveSkill(View view) {
        Skill skill = new Skill(skill_name.getText().toString(),
                skill_desc.getText().toString());

        db.collection(currentUser.getEmail()).document("Skill").collection("Skill").document(skill.getName()).set(skill);

        /**
         * Safety precaution, for uploading to the google firebase
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Create_skill.this, Skillset.class);
                startActivity(intent);
            }
        }, 100);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle("Create skill");
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

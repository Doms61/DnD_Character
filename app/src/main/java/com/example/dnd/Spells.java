package com.example.dnd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

import java.util.ArrayList;

public class Spells extends AppCompatActivity implements SpellAdapter.OnListItemClickListener{

    RecyclerView mSpellList;
    RecyclerView.Adapter mSpellAdapter;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;

    ArrayList<Spell> spells;
    Spell spell;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spells);

        mSpellList = findViewById(R.id.spell_rv);
        mSpellList.hasFixedSize();
        mSpellList.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        spells = new ArrayList<>();

        getSpells();

    }

    private void getSpells() {
        db.collection(currentUser.getEmail()).document("Spells").collection("Spells").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference docRef = db.collection(currentUser.getEmail()).document("Spells").collection("Spells").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        spells.add(spell = documentSnapshot.toObject(Spell.class));

                                        mSpellAdapter = new SpellAdapter(spells, Spells.this);
                                        mSpellList.setAdapter(mSpellAdapter);
                                    }
                                });

                            }
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle("Spells");
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

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(Spells.this, Spell_Activity.class);
        Spell currentSpell = spells.get(clickedItemIndex);
        intent.putExtra("spell", currentSpell);
        startActivity(intent);

    }

    public void addSpell(View view) {
        Intent intent = new Intent(this, Create_spell.class);
        startActivity(intent);
    }
}

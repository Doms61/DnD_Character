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

public class Equipments extends AppCompatActivity implements EquipmentAdapter.OnListItemClickListener{

    RecyclerView mEquipmentList;
    RecyclerView.Adapter mEquipmentAdapter;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser currentUser;

    ArrayList<Equipment> equipments;
    Equipment equipment;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);

        mEquipmentList = findViewById(R.id.equipment_rv);
        mEquipmentList.hasFixedSize();
        mEquipmentList.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        equipments = new ArrayList<>();

        getEquipment();
    }

    private void getEquipment() {
        db.collection(currentUser.getEmail()).document("Equipment").collection("Equipment").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference docRef = db.collection(currentUser.getEmail()).document("Equipment").collection("Equipment").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        equipments.add(equipment = documentSnapshot.toObject(Equipment.class));

                                        mEquipmentAdapter = new EquipmentAdapter(equipments,Equipments.this);
                                        mEquipmentList.setAdapter(mEquipmentAdapter);
                                    }
                                });
                            }
                        }
                    }
                });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        intent = new Intent(Equipments.this, Equipment_activity.class);
        Equipment currentEquipment = equipments.get(clickedItemIndex);
        intent.putExtra("equipment", currentEquipment);
        startActivity(intent);
    }

    public void addEquipment(View view) {
        Intent intent = new Intent(this, Create_equipment.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/drop_menu.xml file.
        getMenuInflater().inflate(R.menu.drop_menu, menu);
        setTitle("Equipments");
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


}

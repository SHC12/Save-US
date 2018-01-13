package superhumancrew.com.saveus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PihakBerwajib extends AppCompatActivity {
    private DatabaseReference database;
    private Button btnLokasi;

    private TextView txtUser;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SaveUs> daftarSaveUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pihak_berwajib);


        btnLokasi = (Button)findViewById(R.id.btnLokasi);
        recyclerView = (RecyclerView)findViewById(R.id.rv_main);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference();
        database.child("saveus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<SaveUs> daftarSaveUs = new ArrayList<SaveUs>();
                for(DataSnapshot noteDataSnapshot: dataSnapshot.getChildren()){
                    SaveUs saveUs = noteDataSnapshot.getValue(SaveUs.class);

                    daftarSaveUs.add(0, saveUs);


                }

                adapter = new AdapterSaveUs(daftarSaveUs, PihakBerwajib.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

}
}

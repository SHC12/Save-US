package superhumancrew.com.saveus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Lapor extends AppCompatActivity implements LocationListener {
    private Handler handler = new Handler();
    private DatabaseReference database;
    private Button btnLapor;
    private EditText editLapor;
    //private ArrayList<SaveUs> daftarSaveUs;
    private static long DURASI = 60000;
    private TextView tlat,tlng;

    LocationManager locationManager;
    LocationListener locationListener;
    Context context;

    String nama;
    String lat,lng;


    String waktu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);

        Intent in = getIntent();
        nama = in.getStringExtra("nama");

        editLapor = (EditText) findViewById(R.id.editLapor);
        btnLapor = (Button) findViewById(R.id.btnLapor);
        tlat = (TextView)findViewById(R.id.txtLat);
        tlng = (TextView)findViewById(R.id.txtLng);
        btnLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
        handler.postDelayed(runnable, 1000);
        database = FirebaseDatabase.getInstance().getReference();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,DURASI, this);
        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new  StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yyyy HH:mm:ss a");
            waktu = sdf1.format(calendar.getTime());
        }
    };
    private void submitLapor(SaveUs saveus) {

        database.child("saveus").push().setValue(saveus).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                editLapor.setText("");
                Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_LONG).show();
            }
        });



    }
    private void insert(){
            submitLapor(new SaveUs(nama.toString(),waktu.toString(), editLapor.getText().toString(), lat.toString(), lng.toString()));


    }
    @Override
    public void onLocationChanged(Location location) {


        lat = String.valueOf(location.getLatitude());
        lng = String.valueOf(location.getLongitude());

        tlat.setText("Lat"+lat);
        tlng.setText("Long"+lng);
        insert();

    }


    public static Intent getActIntent(Activity activity){
        return new Intent(activity, MainActivity.class);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

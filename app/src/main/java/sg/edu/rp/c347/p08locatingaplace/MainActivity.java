package sg.edu.rp.c347.p08locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button btnNorth, btnCentral, btnEast;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_center = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_center, 11));

                LatLng poi_east = new LatLng(1.4183039, 103.7898392);//block 555
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("HQ - East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                
                UiSettings ui = map.getUiSettings();

                ui.setZoomControlsEnabled(true);

                LatLng poi_north = new LatLng(1.3613131, 103.8494639);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.btn_star_big_on)));

                LatLng poi_central = new LatLng(1.3140511, 103.8703913);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("HQ - Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    map.setMyLocationEnabled(true);
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
            }
        });



        btnNorth = findViewById(R.id.btnNorth);
        btnCentral = findViewById(R.id.btnCentral);
        btnEast = findViewById(R.id.btnEast);

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null){
                    LatLng poi_north = new LatLng(1.3613131, 103.8494639);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north, 15));
                }
            }
        });

        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null){
                    LatLng poi_central = new LatLng(1.3140511, 103.8703913);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 15));

                }
            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null){
                    LatLng poi_east = new LatLng(1.4183039, 103.7898392);//block 555
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 15));


                }
            }
        });
    }
}

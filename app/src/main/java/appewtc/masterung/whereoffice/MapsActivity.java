package appewtc.masterung.whereoffice;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Double latCenterADouble, lngCenterADouble;

    //Constant
    private Double douLatLng[][] = {{13.679919, 100.609586}, {13.668268, 100.604790}, {13.669312, 100.626138},
            {13.68102594, 100.60990691}, {13.68099466, 100.6082654}, {13.67874298, 100.61234236}};

    private LatLng udomsukLatLng, bangnaLatLng, bangpeeLatLng,
            udomsuk1LatLng, udomsuk2LatLng, udomsuk3LatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Receive Center From Intent
        receiveCenter();

        setUpMapIfNeeded();
    }   // onCreate

    private void receiveCenter() {
        latCenterADouble = getIntent().getExtras().getDouble("lat");
        lngCenterADouble = getIntent().getExtras().getDouble("lng");
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        //Setup Center Map  คือการสร้าง จุดกลางแผนที่
        setUpCenterMap();

        //Setup LatLng คือการสร้างพิกัด บน map
        setUpLatLng();

        //Create Maker
        createMaker();


    }   // setUpMap

    private void createMaker() {

        //About Udomsuk
        mMap.addMarker(new MarkerOptions()
                .position(udomsukLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build1))
        .title("สถานีอุดมสุข")
        .snippet("สถานีรถไฟฟ้าอุดมสุข"));

        mMap.addMarker(new MarkerOptions()
                .position(udomsuk1LatLng));
        mMap.addMarker(new MarkerOptions()
                .position(udomsuk2LatLng));
        mMap.addMarker(new MarkerOptions()
                .position(udomsuk3LatLng));

        //About Bangna
        mMap.addMarker(new MarkerOptions()
                .position(bangnaLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build2))
        .title("บางนา")
        .snippet("สถานีรถไฟฟ้า บางนา"));

        //About Bangpee
        mMap.addMarker(new MarkerOptions()
                .position(bangpeeLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build3))
        .title("บางพลี")
        .snippet("เส้นทางที่มาจาก ชลบุรี"));

    }   // createMaker

    private void setUpLatLng() {

        udomsukLatLng = new LatLng(douLatLng[0][0], douLatLng[0][1]);
        bangnaLatLng = new LatLng(douLatLng[1][0], douLatLng[1][1]);
        bangpeeLatLng = new LatLng(douLatLng[2][0], douLatLng[2][1]);
        udomsuk1LatLng = new LatLng(douLatLng[3][0], douLatLng[3][1]);
        udomsuk2LatLng = new LatLng(douLatLng[4][0], douLatLng[4][1]);
        udomsuk3LatLng = new LatLng(douLatLng[5][0], douLatLng[5][1]);

    }   // setUpLatLng

    private void setUpCenterMap() {

        mMap.animateCamera(CameraUpdateFactory.
                newLatLngZoom(new LatLng(latCenterADouble, lngCenterADouble), 16));

    }

}   // Main Class

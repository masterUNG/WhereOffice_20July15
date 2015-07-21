package appewtc.masterung.whereoffice;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Double latCenterADouble, lngCenterADouble;

    //Constant
    private Double douLatLng[][] = {{13.679919, 100.609586}, {13.668268, 100.604790}, {13.669312, 100.626138},
            {13.68102594, 100.60990691}, {13.68099466, 100.6082654}, {13.67874298, 100.61234236}};

    private LatLng udomsukLatLng, bangnaLatLng, bangpeeLatLng,
            udomsuk1LatLng, udomsuk2LatLng, udomsuk3LatLng,
            section1LatLng, section2LatLng, section3LatLng, EWTCLatLng, myLatLng;

    private PolylineOptions redPolylineOptions, blackPolylineOptions, bluePolylineOptions;

    private Double myLatDouble, myLngDouble;

    private PolygonOptions myPolygonOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Receive Center From Intent
        receiveCenter();

        setUpMapIfNeeded();
    }   // onCreate

    public void clickWhere(View view) {

        Double myRedDouble, myBlackDouble, myBlueDouble;

        myRedDouble = Math.pow((myLatDouble - 13.679919), 2) + Math.pow((myLngDouble - 100.609586), 2);
        myBlackDouble = Math.pow((myLatDouble - 13.668268), 2) + Math.pow((myLngDouble - 100.604790), 2);
        myBlueDouble = Math.pow((myLatDouble - 13.669312), 2) + Math.pow((myLngDouble - 100.626138), 2);

        if ((myRedDouble < myBlackDouble) && (myRedDouble < myBlueDouble)) {
            mMap.addPolyline(redPolylineOptions);
        }

        if ((myBlackDouble < myRedDouble) && (myBlackDouble < myBlueDouble)) {
            mMap.addPolyline(blackPolylineOptions);
        }

        if ((myBlueDouble < myRedDouble) && (myBlueDouble < myBlackDouble)) {
            mMap.addPolyline(bluePolylineOptions);
        }



    }   // clickWhere


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

        //Create Polyline
        createPolyline();

        //Create Polygon
        createPolyGon();


    }   // setUpMap

    private void createPolyGon() {

        myPolygonOptions = new PolygonOptions();
        myPolygonOptions.add(udomsukLatLng)
                .add(bangnaLatLng)
                .add(bangpeeLatLng)
                .add(udomsukLatLng)
                .strokeWidth(5)
                .strokeColor(Color.MAGENTA)
                .fillColor(Color.argb(50, 213, 230, 28));
        mMap.addPolygon(myPolygonOptions);

    }

    private void createPolyline() {

        //Red Polyline
        redPolylineOptions = new PolylineOptions();
        redPolylineOptions.add(myLatLng)
                .add(udomsukLatLng)
                .add(section1LatLng)
                .add(section2LatLng)
                .add(section3LatLng)
                .add(EWTCLatLng)
                .width(10)
                .color(Color.RED);
        //mMap.addPolyline(redPolylineOptions);

        //Blue Polyline
        bluePolylineOptions = new PolylineOptions();
        bluePolylineOptions.add(myLatLng)
                .add(section2LatLng)
                .add(section3LatLng)
                .add(EWTCLatLng)
                .width(10)
                .color(Color.BLUE);
        //mMap.addPolyline(bluePolylineOptions);

        //Black Polyline
        blackPolylineOptions = new PolylineOptions();
        blackPolylineOptions.add(myLatLng)
                .add(bangnaLatLng)
                .add(section1LatLng)
                .add(section2LatLng)
                .add(section3LatLng)
                .add(EWTCLatLng)
                .width(10)
                .color(Color.BLACK);
        //mMap.addPolyline(blackPolylineOptions);



    }   // createPolyline

    private void createMaker() {

        //About Udomsuk
        mMap.addMarker(new MarkerOptions()
                .position(udomsukLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build1))
                .title("สถานีอุดมสุข")
                .snippet("สถานีรถไฟฟ้าอุดมสุข"));

        mMap.addMarker(new MarkerOptions()
                .position(udomsuk1LatLng)
                .title("Green")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.addMarker(new MarkerOptions()
                .position(udomsuk2LatLng)
                .title("Yellow")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.addMarker(new MarkerOptions()
                .position(udomsuk3LatLng));

        //About Bangna
        mMap.addMarker(new MarkerOptions()
                .position(bangnaLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build2))
                .title("บางนา")
                .snippet("สถานีรถไฟฟ้า บางนา"));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.666218, 100.604281)));

        //About Bangpee
        mMap.addMarker(new MarkerOptions()
                .position(bangpeeLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build3))
                .title("บางพลี")
                .snippet("เส้นทางที่มาจาก ชลบุรี"));


        //my Mobile
        //รับค่าจาก Intent
        myLatDouble = getIntent().getExtras().getDouble("mylat");
        myLngDouble = getIntent().getExtras().getDouble("mylng");

        //สร้างพิกัด บนแผนที่
        myLatLng = new LatLng(myLatDouble, myLngDouble);

        //สร้าง Maker
        mMap.addMarker(new MarkerOptions()
                .position(myLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.friend))
                .title("ฉันอยู่นี่")
                .snippet("ที่นี่ฉันอยู่ ล่าสุด"));

        //For EWTC
        mMap.addMarker(new MarkerOptions()
        .position(EWTCLatLng)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo300))
        .title("EWTC")
        .snippet("โรงเรียนสอนแอนดรอยด์ โดย มาสเตอร์ อึ่ง"));



    }   // createMaker

    private void setUpLatLng() {

        udomsukLatLng = new LatLng(douLatLng[0][0], douLatLng[0][1]);
        bangnaLatLng = new LatLng(douLatLng[1][0], douLatLng[1][1]);
        bangpeeLatLng = new LatLng(douLatLng[2][0], douLatLng[2][1]);
        udomsuk1LatLng = new LatLng(douLatLng[3][0], douLatLng[3][1]);
        udomsuk2LatLng = new LatLng(douLatLng[4][0], douLatLng[4][1]);
        udomsuk3LatLng = new LatLng(douLatLng[5][0], douLatLng[5][1]);
        section1LatLng = new LatLng(13.673320, 100.606870);
        section2LatLng = new LatLng(13.665834, 100.644356);
        section3LatLng = new LatLng(13.669587, 100.624315);
        EWTCLatLng = new LatLng(13.666835, 100.623328);

    }   // setUpLatLng

    private void setUpCenterMap() {

        mMap.animateCamera(CameraUpdateFactory.
                newLatLngZoom(new LatLng(latCenterADouble, lngCenterADouble), 16));

    }

}   // Main Class

package appewtc.masterung.whereoffice;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private TextView latitudeTextView, longitudeTextView;
    private LocationManager objLocationManager;
    private Criteria objCriteria;
    private boolean GPSABoolean, networkABoolean;
    private Double myLatADouble, myLngADouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Widget
        initialWidget();

        //Setup Location
        setUpLocation();

    }   // onCreate

    public void clickWhereAreYou(View view) {

        myLatADouble = Double.parseDouble(latitudeTextView.getText().toString());
        myLngADouble = Double.parseDouble(longitudeTextView.getText().toString());

        myIntent(myLatADouble, myLngADouble);

    }   // clickWhereAreYou


    @Override
    protected void onResume() {
        super.onResume();

        setupAll();

    }

    private void setupAll() {

        objLocationManager.removeUpdates(objLocationListener);
        String strLat = "Unknow";
        String strLng = "Unknow";

        Location objNetworkLocation = requestUpdateFromProvider(LocationManager.NETWORK_PROVIDER, "Cannot Connected Internet");
        if (objNetworkLocation != null) {
            strLat = String.format("%.7f", objNetworkLocation.getLatitude());
            strLng = String.format("%.7f", objNetworkLocation.getLongitude());
        }

        Location objGPSLocation = requestUpdateFromProvider(LocationManager.GPS_PROVIDER, "GPS off");
        if (objGPSLocation != null) {
            strLat = String.format("%.7f", objGPSLocation.getLatitude());
            strLng = String.format("%.7f", objGPSLocation.getLongitude());
        }

        latitudeTextView.setText(strLat);
        longitudeTextView.setText(strLng);


    }   // setupAll

    @Override
    protected void onStop() {
        super.onStop();

        objLocationManager.removeUpdates(objLocationListener);

    }

    @Override
    protected void onStart() {
        super.onStart();

        GPSABoolean = objLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!GPSABoolean) {

            networkABoolean = objLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!networkABoolean) {

                Intent objIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(objIntent);

            }//if2

        }   //if1

    }

    //find Location
    public Location requestUpdateFromProvider(final String strProvider, String strError) {

        Location objLocation = null;

        if (objLocationManager.isProviderEnabled(strProvider)) {

            objLocationManager.requestLocationUpdates(strProvider, 1000, 10, objLocationListener);
            objLocation = objLocationManager.getLastKnownLocation(strProvider);

        } else {

            Toast.makeText(MainActivity.this, strError, Toast.LENGTH_SHORT).show();

        }

        return objLocation;
    }



    //Create Listener for android.location Class
    public final LocationListener objLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            latitudeTextView.setText(String.format("%.7f", location.getLatitude()));
            longitudeTextView.setText(String.format("%.7f", location.getLongitude()));

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
    };

    private void setUpLocation() {
        objLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); // Open Location Service
        objCriteria = new Criteria();
        objCriteria.setAltitudeRequired(false);
        objCriteria.setBearingRequired(false);
    }

    private void initialWidget() {
        latitudeTextView = (TextView) findViewById(R.id.txtLat);
        longitudeTextView = (TextView) findViewById(R.id.txtLng);
    }

    public void clickBangPee(View view) {
        myIntent(13.669312, 100.626138);
    }

    public void clickBangna(View view) {
        myIntent(13.668268, 100.604790);
    }

    public void clickUdomsuk(View view) {

        myIntent(13.679919, 100.609586);

    }

    private void myIntent(double lat, double lng) {

        Intent objIntent = new Intent(MainActivity.this, MapsActivity.class);
        objIntent.putExtra("lat", lat);
        objIntent.putExtra("lng", lng);
        objIntent.putExtra("mylat", Double.parseDouble(latitudeTextView.getText().toString()));
        objIntent.putExtra("mylng", Double.parseDouble(longitudeTextView.getText().toString()));
        startActivity(objIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class

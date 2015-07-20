package appewtc.masterung.whereoffice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }   // onCreate

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

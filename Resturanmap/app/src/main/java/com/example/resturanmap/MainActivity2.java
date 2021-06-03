package com.example.resturanmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import static com.google.android.libraries.places.api.model.Place.Field.LAT_LNG;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "Main";
    Button showbutton,findbutton;
    ArrayList<String> ab = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        showbutton = findViewById(R.id.showButton2);
        findbutton = findViewById(R.id.GetLocationButton);
        //ab.add("-37.81490");
        //ab.add("144.95334");


        // Initialize the SDK
        Places.initialize(getApplicationContext(), "AIzaSyC55JIOkwNUU9XW6CVYbkmsUbm6qThSMpk");

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                Toast.makeText(MainActivity2.this,"Place: " + place.getLatLng() ,Toast.LENGTH_LONG).show();
                LatLng a = place.getLatLng();
                ab.add(Double.toString(a.latitude));
                ab.add(Double.toString(a.longitude));

            }


            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void show(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putStringArrayListExtra("a",ab);
        startActivity(intent);

    }

    public void save(View view) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra("a",ab);
        setResult(RESULT_OK,intent);
        finish();

    }


}
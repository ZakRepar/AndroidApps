package com.example.finalassignment

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import com.example.finalassignment.address.Address
import com.example.finalassignment.api.AddressDataAdapter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val dataController = AddressDataAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))



        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit (query: String) : Boolean {
                var geocodeMatches: List<Address>? = null
                try {
                    geocodeMatches = Geocoder(this).getFromLocationName(query, 1)
                } catch (e: IOException) {
                    //send error message
                }

                if (geocodeMatches != null) {
                    val loc = LatLng(geocodeMatches[0].request?.latitude.toDouble(), geocodeMatches[0].request.longitude.toDouble())
                    mMap.addMarker((MarkerOptions().position(loc).title(query)))
                }
                return false
            }

            override fun onQueryTextChange(newText: String) : Boolean {
                Log.d("address", newText)
                return false
            }
        } )


        fun createErrorHandler(): CoroutineExceptionHandler {

            return CoroutineExceptionHandler { _, exception ->
                AlertDialog.Builder(this).setTitle("Error...")
                        .setMessage(exception.message)
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
            }
        }
    }
}
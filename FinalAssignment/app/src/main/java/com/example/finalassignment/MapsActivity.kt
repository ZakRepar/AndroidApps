package com.example.finalassignment

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.finalassignment.address.ISSPassTime
import com.example.finalassignment.api.AddressDataAdapter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

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
                    geocodeMatches = Geocoder(this@MapsActivity).getFromLocationName(query, 1)
                } catch (e: IOException) {
                    createErrorHandler()
                }

                if (geocodeMatches != null) {
                    val loc = LatLng(geocodeMatches[0].latitude, geocodeMatches[0].longitude)
                    mMap.addMarker((MarkerOptions().position(loc).title(loc.toString())))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 8.0f))
                }
                return false
            }

            override fun onQueryTextChange(newText: String) : Boolean {
                Log.d("iss-pass.json", newText)
                return false
            }
        } )

        mMap.setOnMarkerClickListener { marker ->

            val datetime: Long = 1605568424
            val date = Date(datetime * 1000) //Date object takes milliseconds past 1/1/70
            //use SimpleDateFormat to output a date as a string in the format you want
            val formatter = SimpleDateFormat("MM/dd/yyyy hh:mm a")
            Log.d("iss-pass.json", "${formatter.format(date)}")

            Toast.makeText(this, "${marker.title}\n(${formatter}", Toast.LENGTH_LONG).show()
            false
        }
    }

    fun createErrorHandler(): CoroutineExceptionHandler {

        return CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error: Invalid Address Entered!")
                    .setMessage(exception.message)
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
        }
    }
}
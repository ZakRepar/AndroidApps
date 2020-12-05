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
import com.example.finalassignment.api.ISSAPI

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val TAG ="iss-pass.json"
private const val FILENAME_ARRAY = "ISSPassTime.json"

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

        val searchView = findViewById<SearchView>(R.id.searchView)


        //read JSON
        Log.d(TAG, "read array clicked")

        val path = this.getExternalFilesDir(null)
        val file = File(path, FILENAME_ARRAY)

        if (file.exists()) {
            //read the data

            val bufferedReader = file.bufferedReader()

            val issPassTimeJSON = bufferedReader.use { it.readText() }

            Log.d(TAG, "student array data read : $issPassTimeJSON")

            val gson = Gson()

            try {

                val type = object: TypeToken<ArrayList<ISSPassTime>>() {}.type
                val locations = gson.fromJson<ArrayList<ISSPassTime>>(issPassTimeJSON, type)

                for (location in locations) {
                    Log.d(TAG, "saved latitude name is ${location.request.latitude} and latitude ${location.request.latitude}")
                }

                locations.add(ISSPassTime())

                for (location in locations) {
                    Log.d(TAG, "saved latitude name is ${location.request.latitude} and latitude ${location.request.latitude}")
                }

            }
            catch(e: Exception) {
                Log.d(TAG, "error parsing json : ${e.toString()} : '$issPassTimeJSON'")
            }

        }
        else {
            Log.d(TAG, "no ISSPassTime data saved")
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit (query: String) : Boolean {
                var geocodeMatches: List<Address>? = null
                try {
                    //uses user typed location to do a Geo look up
                    geocodeMatches = Geocoder(this@MapsActivity).getFromLocationName(query, 1)
                } catch (e: IOException) {
                    createErrorHandler()
                }

                if (geocodeMatches != null) {
                    val loc = LatLng(geocodeMatches[0].latitude, geocodeMatches[0].longitude)

                    //set marker to added location and zooms in on it
                    mMap.addMarker((MarkerOptions().position(loc).title(loc.toString())))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 8.0f))


                    //JSON write to file
                    Log.d(TAG, "write array clicked")

                    val locations = ArrayList<ISSPassTime>()
                    val currentLocation = //ISSPassTime object
                    locations.add(currentLocation)


                    val gson = Gson()
                    val locationsJSON = gson.toJson(locations)

                    Log.d(TAG, locationsJSON)

                    val path = this.getExternalFilesDir(null)
                    val file = File(path, FILENAME_ARRAY)

                    if(!file.exists()) {
                        file.createNewFile()
                    }

                    file.writeText(locationsJSON)

                    Log.d(TAG, "finished writing ISSPassTime array JSON")
                }
                return false
            }

            override fun onQueryTextChange(newText: String) : Boolean {
                Log.d(TAG, newText)
                return false
            }
        } )

        mMap.setOnMarkerClickListener { marker ->

            val datetime: Long = //access ISSPassTime object
            val date = Date(datetime * 1000) //Date object takes milliseconds past 1/1/70

            //use SimpleDateFormat to output a date as a string in the format you want
            val formatter = SimpleDateFormat("MM/dd/yyyy hh:mm a")


            //Toast message displays lat/lon and ISS pass time
            Toast.makeText(this, "${marker.title}\n(${formatter.format(date)}", Toast.LENGTH_LONG).show()
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
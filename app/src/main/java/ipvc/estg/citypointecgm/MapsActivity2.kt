package ipvc.estg.citypointecgm

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import api.EndPoints
import api.ServiceBuilder
import api.ocorrencia
import com.google.android.gms.common.api.Response
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.security.auth.callback.Callback

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private val newProblemaEditarActivityRequestCode = 1
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var  ocorrencias: List<ocorrencia>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)


        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getProblemas()
        var position: LatLng
        val utilizador_id = sharedPreferences.all[getString(R.string.id_ut)]

        Log.d("TAG", utilizador_id.toString())
        call.enqueue(object : retrofit2.Callback<List<ocorrencia>> {
            override fun onResponse(call: retrofit2.Call<List<ocorrencia>>, response: retrofit2.Response<List<ocorrencia>>){
                if (response.isSuccessful){
                    ocorrencias = response.body()!!
                    for (ocorrencia in ocorrencias){
                        position = LatLng(ocorrencia.latitude.toDouble(), ocorrencia.longitude.toDouble())
                        if(ocorrencia.utilizador_id == utilizador_id){
                            mMap.addMarker(MarkerOptions().position(position).title(ocorrencia.id.toString()).snippet(ocorrencia.descricao))
                                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        }else{
                            mMap.addMarker(MarkerOptions().position(position).title(ocorrencia.id.toString()).snippet(ocorrencia.descricao))
                                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                        }

                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<List<ocorrencia>>, t: Throwable){
                Toast.makeText(this@MapsActivity2,"${t.message}", Toast.LENGTH_LONG).show()
            }
        })

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

        // Add a marker in Sydney and move the camera
/*val sydney = LatLng(-34.0, 151.0)
mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        setUpMap()

}


private fun setUpMap() {
if(ActivityCompat.checkSelfPermission(this,
        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

    ActivityCompat.requestPermissions(this,
        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

    return
} else{
    //1
    mMap.isMyLocationEnabled = true

    //2
    fusedLocationClient.lastLocation.addOnSuccessListener(this) {location ->
        //3
        if(location != null){
            lastLocation = location
            //Toast.makeText(this@MapsActivity, lastLocation.toString(), Toast.LENGTH_SHORT).show()
            val currentLatLng = LatLng(location.latitude, location.longitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
        }
    }
}
}

}



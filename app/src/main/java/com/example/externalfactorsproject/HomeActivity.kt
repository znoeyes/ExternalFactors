package com.example.externalfactorsproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val PERMISSION_ID = 1000
    private lateinit var LAT: String
    private lateinit var LON: String
    private val CITY: String = "segok-dong,kr"
    private val API: String = "cf47ea0b59553630ae022abdf6c77247" //OpenWeatherMap 날씨 API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //위치
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        //날씨, 시간
        weatherTask().execute()

        imageButton_home.setOnClickListener{
            startActivity<HomeActivity>()
        }
        imageButton_popup.setOnClickListener {
            startActivity<EmotionPopUpActivity>()
        }
        imageButton_mystudio.setOnClickListener {
//            startActivity<UserFeedActivity>()
        }
    }

    //마지막으로 알려진 위치 가져오기
    @SuppressLint("MissingPermission")
    private fun getLastLocation(){
        if (checkPermissions()){ //위치 권한 검사
            if(isLocationEnabled()){ //사용자가 '설정'에서 위치 권한을 수락할 경우
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){
                        task -> var location: Location? = task.result
                    if(location == null){
                        requestNewLocationData() //실행 중 권한 요청 코드 적용
                    } else { //모든 권한이 승인됐을 때, 출력 코드
//                        findViewById<TextView>(R.id.latTextView).text = location.latitude.toString()
//                        findViewById<TextView>(R.id.lonTextView).text = location.longitude.toString()
                        LAT = location.latitude.toString()
                        LON = location.longitude.toString()
                    }
                }
            } else {
                Toast.makeText(this, "현재 위치 정보를 얻으려면 위치 권한이 필요합니다", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS) //위치 '설정' open
                startActivity(intent)
            }
        } else {
            requestPermissions() //이전에 권한을 거부한 적이 있는 경우 권한 요청
        }
    }

    //위치 권한이 있는지 검사
    private fun checkPermissions(): Boolean{
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }
    //이전에 권한을 거부한 적이 있는 경우 권한 요청
    private fun requestPermissions(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }
    //사용자가 권한을 수락 또는 거부했을 때: 수락 시 다음 단계로 이동
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_ID){
            if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                //Granted. Start getting the location information
            }
        }
    }
    //사용자가 '설정'에서 위치 권한을 수락할 경우
    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    //실행 중 권한 요청 코드 적용
    @SuppressLint("MissingPermission")
    private fun requestNewLocationData(){
        var locationRequest = LocationRequest() //위치 요청 객체
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //GPS 우선
        locationRequest.interval = 10000 //위치 정보 업데이트 시간 간격
        locationRequest.fastestInterval = 5000
        locationRequest.numUpdates = 1

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback,
            Looper.myLooper()
        )
    }
    //최근 현재 위치에 대한 Location 객체(위도,경도 정보)를 얻음:
    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
//            findViewById<TextView>(R.id.latTextView).text = lastLocation.latitude.toString()
//            findViewById<TextView>(R.id.lonTextView).text = lastLocation.longitude.toString()
            LAT = lastLocation.latitude.toString()
            LON = lastLocation.longitude.toString()
        }
    }


    //AsyncTask 클래스 - UI와 즉각적 상호작용
    inner class weatherTask() : AsyncTask<String, Void, String>() {

        //스레드가 시작하기 전에 수행할 작업(메인 스레드)
//        override fun onPreExecute() {
//            super.onPreExecute()
//            /* Showing the ProgressBar, Making the main design GONE */
//            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
//            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
//            findViewById<TextView>(R.id.errorText).visibility = View.GONE
//        }

        //스레드가 수행할 작업(생성된 스레드)
        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{ // lat=$LAT&lon=$LON | q=$CITY
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(Charsets.UTF_8)
            }
            catch (e: Exception){
                response = null
            }
            return response
        }

        //스레드 작업이 모두 끝난 후에 수행할 작업(메인 스레드)
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val sys = jsonObj.getJSONObject("sys")

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.KOREA).format(Date(updatedAt*1000))//.format(Date(updatedAt*1000))
                val weatherDescription = weather.getString("description")
                val temp = main.getString("temp")+"°C"
                val address = jsonObj.getString("name")+", "+sys.getString("country")


                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
//                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
//                findViewById<TextView>(R.id.address).text = address

                /* Views populated, Hiding the loader, Showing the main design */
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE

            } catch (e: Exception) {
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
            }
        }
    }

}

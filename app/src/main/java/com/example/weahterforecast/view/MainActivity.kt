package com.example.weahterforecast.view


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weahterforecast.R
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    /*
    private val adapter: NavMenuAdapter? = null
    private val mDrawerLayout: DrawerLayout? = null
    private val mActionBarDrawerToggle: ActionBarDrawerToggle? = null
    private val mRecyclerView: RecyclerView? = null
    
     */




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEE, MMM d, yy h:mm a z")
        val formattedDate = sdf.format(calendar.time)

        val x3DateHour: TextView = findViewById(R.id.D_Date_Hour)
        x3DateHour.text = formattedDate

        weatherAPI()

    }

    private fun weatherAPI() {

        val x1Temp: TextView = findViewById(R.id.D_Temp)
        val x2Location: TextView = findViewById(R.id.D_Location)
        val x3Description: TextView = findViewById(R.id.description)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://api.openweathermap.org/data/2.5/onecall?lat=40.999984&lon=28.783142&appid=63f33e3ec0a6a91900f07f917dbf7a8f&units=metric"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    val mainObject = response.getJSONObject("current")
                    val temp = mainObject.getDouble("temp")
                    val city = response.getString("timezone")
                    val description = mainObject.getJSONArray("weather")
                    val objectDes = description.getJSONObject(1)
                    val dep = objectDes.getString("main")


                    x1Temp.text = temp.toString() + "\u2103"
                    x2Location.text = city
                    x3Description.text = dep


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error -> error.printStackTrace() }
        )


        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

}

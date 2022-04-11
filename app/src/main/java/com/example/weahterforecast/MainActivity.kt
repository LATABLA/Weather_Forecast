package com.example.weahterforecast


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEE, MMM d, ''yy h:mm a")
        val formattedDate = sdf.format(calendar.time)

        val x3DateHour: TextView = findViewById(R.id.D_Date_Hour)
        x3DateHour.text = formattedDate

        weatherAPI()

    }

    private fun weatherAPI() {

        val x1Temp: TextView = findViewById(R.id.D_Temp)
        val x2Location: TextView = findViewById(R.id.D_Location)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://api.openweathermap.org/data/2.5/weather?lat=40.999984&lon=28.783142&appid=63f33e3ec0a6a91900f07f917dbf7a8f&units=metric"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    val mainObject = response.getJSONObject("main")
                    val temp = mainObject.getDouble("temp")
                    val city = response.getString("name")

                    x1Temp.text = temp.toString() + "\u2103"
                    x2Location.text = city


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

/*
// Request a string response from the provided URL.StringRequest
        val stringRequest = StringRequest(
            Request.Method.GET, url, Response.Listener<String> { response ->
                try {
                    // Display the first 500 characters of the response string."${response.substring(0, 500)}"
                    val mainObject =
                    x2Location.text = response.getString()


                } catch (e: JSONException) {
                    e.printStackTrace()
                }


            },
            Response.ErrorListener { error -> error.printStackTrace() })

// Access the RequestQueue through your singleton class.
//MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
*/
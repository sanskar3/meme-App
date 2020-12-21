package com.sanskar.memeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {


    var currentImageUrl:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadmeme()
    }

    private fun loadmeme() {



        val image= findViewById<ImageView>(R.id.memeImageView)
        val textView = findViewById<TextView>(R.id.text)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        currentImageUrl = "https://meme-api.herokuapp.com/gimme"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, currentImageUrl,null,
            { response ->
                currentImageUrl=response.getString("url")
                Glide.with(this).load(currentImageUrl).into(image)

            },
            { textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)


    }

    fun shareMe(view: View){
        val intent=Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT," Hyy , have a look at this amazing meme  $currentImageUrl")
        val chooser=Intent.createChooser(intent,"share this using.... ")
        startActivity(chooser)

    }

    fun nextMe(view: View){
        loadmeme()

    }
}
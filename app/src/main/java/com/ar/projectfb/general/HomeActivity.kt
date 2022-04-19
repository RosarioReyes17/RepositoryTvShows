package com.ar.projectfb.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ar.projectfb.user.ProfileActivity
import com.ar.projectfb.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()



        binding.btnprofile.setOnClickListener {

            startActivity(Intent(this, ProfileActivity::class.java))

        }

        binding.btntask.setOnClickListener {

            startActivity(Intent(this, HomeTvShow::class.java))

        }

        binding.btnFavorite.setOnClickListener {

            startActivity(Intent(this, FavoriteActivity::class.java))
        }

    }




}
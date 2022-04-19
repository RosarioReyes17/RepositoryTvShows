package com.ar.projectfb.general


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ar.projectfb.R
import com.ar.projectfb.databinding.ActivityDetailsBinding
import com.ar.projectfb.model.ApiRequestViewModel
import com.ar.projectfb.model.TvShow
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    lateinit var viewModel: ApiRequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)


        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val gson = Gson()
        val tvshow = gson.fromJson<TvShow>(intent.getStringExtra("tvshow"), TvShow::class.java)

        // viewModel = ViewModelProvider(this).get(ApiRequestViewModel::class.java)


        binding.tvTitleDeta.text = tvshow.title
        binding.txtDate.text = tvshow.releasedate
        binding.Txtdescription.text = tvshow.description
        binding.TxtEnd.text = tvshow.end_date
        binding.txtCount.text = tvshow.country
        binding.txtLink.text = tvshow.network
        binding.TxtStatus.text = tvshow.status



        Picasso.get()
            .load(tvshow.image_thumbnail_path)
            .placeholder(R.drawable.movise)
            .centerCrop()
            .resize(800, 800)
            .into(binding.imageView3)

        // viewModel.TvDetailsShow(tvshow.id.toString())


    }
}
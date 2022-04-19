package com.ar.projectfb.general

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar.projectfb.adapter.tvshowadapter
import com.ar.projectfb.databinding.ActivityHometvshowBinding
import com.ar.projectfb.model.ApiRequestViewModel
import com.ar.projectfb.model.TvShow
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

class HomeTvShow : AppCompatActivity() {
    lateinit var binding: ActivityHometvshowBinding

    lateinit var viewModel: ApiRequestViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_home)


        binding = ActivityHometvshowBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()

        val loading = Loading(this)
        loading.startLoading()
        val handler = Handler()

        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },1000)


        viewModel = ViewModelProvider(this).get(ApiRequestViewModel::class.java)

        viewModel.loading.observe(this,{
            if (it == true){
                binding.progressBar4.visibility = View.VISIBLE
            }
            else{
                binding.progressBar4.visibility = View.GONE
            }

        })

        viewModel.tvShowList.observe(this) {
            val UserId = auth!!.uid
            binding.RvidAll.adapter = tvshowadapter(this, it,this, UserId.toString()) {
                viewModel.select(it)
            }
        }

        binding.RvidAll.layoutManager =
            LinearLayoutManager(applicationContext)


        viewModel.loadtvShows()

        viewModel.paginations.observe(this){
            binding.currentPage.text = it.page.toString()
        }

        binding.BtnNext.setOnClickListener{



            val nextPages = viewModel.paginations.value!!.page + 1

            if (nextPages <=  viewModel.paginations.value!!.totalPages ){
                viewModel.loadtvShows(nextPages)

            }else{
                Toast.makeText(this,"No More Pages", Toast.LENGTH_SHORT).show()

            }

        }

        binding.BtnBack.setOnClickListener {


            val backPage = viewModel.loadtvShows(viewModel.paginations.value!!.page - 1)

//            if (backPage <= viewModel.paginations.value!!.totalPages) {
//                viewModel.loadtvShows(backPage)
//
//            } else {
//                Toast.makeText(this, "No More Pages", Toast.LENGTH_SHORT).show()
//            }
        }





        binding.btnSearch.setOnClickListener{
            viewModel.search(binding.txtSearch.text.toString())
        }


        viewModel.exception.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })

    }
    private fun setAdapter(items: List<TvShow>) {

        val UserId = auth!!.uid
        binding.RvidAll.layoutManager =
            LinearLayoutManager(this)
        binding.RvidAll.adapter= tvshowadapter(this,items,this, UserId.toString()){
            viewModel.select(it)
        }
    }

    fun onClickTvShow(tvShow: TvShow) {
        viewModel.select(tvShow)

        val intent = Intent(this@HomeTvShow,DetailsActivity::class.java)

        val gson = Gson()
        intent.putExtra("tvshow",gson.toJson(viewModel.selected.value))
        startActivity(intent)
        //this.finish()
    }
}
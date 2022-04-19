package com.ar.projectfb.general

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar.projectfb.R
import com.ar.projectfb.adapter.favoriteAdapter
import com.ar.projectfb.adapter.tvshowadapter
import com.ar.projectfb.databinding.ActivityFavoriteBinding
import com.ar.projectfb.model.ApiFavoritesViewModel
import com.ar.projectfb.model.ApiRequestViewModel

import com.ar.projectfb.model.TvShow
import com.google.firebase.auth.FirebaseAuth


class FavoriteActivity : AppCompatActivity() {

    lateinit var binding:ActivityFavoriteBinding
    lateinit var auth: FirebaseAuth
    //lateinit var listAllFavotite: List<TvShow>
    var listAllTvShow: List<TvShow> = emptyList()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_favorite)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()

        getFavorites2()

    }

    fun getFavorites2(){

        val viewModel = ViewModelProvider(this).get(ApiRequestViewModel::class.java)


        viewModel.tvShowList.observe(this) {
            if(it!=null){
                listAllTvShow=it
            }


        }

        viewModel.exception.observe(this){
            Toast.makeText(this, "Error en la carga de datos", Toast.LENGTH_LONG).show()
        }

        val favoriteViewModel = ViewModelProvider(this).get(ApiFavoritesViewModel::class.java)

        favoriteViewModel.listFav.observe(this){ it->
            if (it!=null){

                viewModel.tvShowList.value?.filter { x->
                    it.get(0).tvShowId!!.toInt() == x.id


            }
//            if(it !=null){
//                listAllFavotite = listAllTvShow.filter { x->
//                    it.any{it.tvShowId!!.toInt()==x.id}
//
//                }
//                var data = listAllFavotite!!

            }
            // val data=favorite
            binding.RvidAllFavorite.adapter = favoriteAdapter(listAllTvShow)



        }

        binding.RvidAllFavorite.layoutManager =
            LinearLayoutManager(applicationContext)

        favoriteViewModel.forError.observe(this){
            Toast.makeText(this, "Error en la carga de datos", Toast.LENGTH_LONG).show()
        }

        var idUser= auth.currentUser
        var userId=idUser!!.uid.toString()

        favoriteViewModel.getFavorite(userId)
    }









}



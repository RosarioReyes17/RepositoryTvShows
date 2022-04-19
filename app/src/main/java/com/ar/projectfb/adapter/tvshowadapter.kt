package com.ar.projectfb.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ar.projectfb.R
import com.ar.projectfb.databinding.TvshowRvItemBinding
import com.ar.projectfb.general.HomeTvShow
import com.ar.projectfb.model.ApiFavoriteServices
import com.ar.projectfb.model.ApiFavoritesViewModel
import com.ar.projectfb.model.ResponseFavoriteApi
import com.ar.projectfb.model.TvShow
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class tvshowadapter (
    private val context: HomeTvShow,
    private val tvShows : List<TvShow>,
    private val onClickTvShow : HomeTvShow,
    val userId : String,
    private val onItemSelect : (tvShow : TvShow) -> Unit,






) :
    RecyclerView.Adapter<tvshowadapter.ViewHolder>(){

    private val allTvShow = tvShows
    val service = ApiFavoritesViewModel()
    inner class ViewHolder(val  binding: TvshowRvItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        val TvShowTitle = binding.idTVTitle
        val TvShowDate = binding.idTVDate
        val TvShowImage = binding.imageView2
        val TvShowSource = binding.idSource
        val cardView = binding.cardidview


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TvshowRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = allTvShow[position]
        holder.TvShowTitle.text = tvShow.title
        holder.TvShowDate.text = "Publish Date : "+ tvShow.releasedate
        holder.TvShowSource.text = tvShow.network


        //adding tv show to favorite

        holder.binding.btnFavorite.setOnClickListener(){
            val tvFavorite = ResponseFavoriteApi(
            userId = userId,
                tvShowId = tvShow.id.toString(),
                id= null

            )

            service.addFavorite(tvFavorite){
                Snackbar.make(context.binding.root, "Tv Show fue agregado a favoritos", Snackbar.LENGTH_LONG).show()
            }
        }

        Picasso.get()
            .load(tvShow.image_thumbnail_path)
            .placeholder(R.drawable.movise)
            .centerCrop()
            .resize(350, 350)
            .into(holder.TvShowImage)
        holder.cardView.setOnClickListener{
            onItemSelect(tvShow)
            onClickTvShow.onClickTvShow(tvShow)
        }
    }


    override fun getItemCount(): Int {
        return allTvShow.size
    }





}
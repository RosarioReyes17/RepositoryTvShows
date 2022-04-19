package com.ar.projectfb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar.projectfb.R
import com.ar.projectfb.databinding.ActivityFavoriteBinding
import com.ar.projectfb.databinding.TvshowRvItemBinding
import com.ar.projectfb.databinding.TvshowRvItemFavoritesBinding
import com.ar.projectfb.general.FavoriteActivity
import com.ar.projectfb.general.HomeTvShow
import com.ar.projectfb.model.ApiFavoritesViewModel
import com.ar.projectfb.model.TvShow
import com.squareup.picasso.Picasso

class favoriteAdapter(


    private val tvShows : List<TvShow>

):RecyclerView.Adapter<favoriteAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: TvshowRvItemFavoritesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val TvShowTitle = binding.idTVTitle
        val TvShowDate = binding.idTVDate
        val TvShowImage = binding.imageView2
        val TvShowSource = binding.idSource
        val cardView = binding.cardidview

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TvshowRvItemFavoritesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.TvShowTitle.text = tvShow.title
        holder.TvShowDate.text = tvShow.releasedate
        holder.TvShowSource.text = tvShow.network


        Picasso.get().load(tvShow.image_thumbnail_path)
            .resize(350, 350)
            .centerCrop()
            .placeholder(R.drawable.movise)
            .into(holder.binding.imageView2)




    }









    override fun getItemCount(): Int {
        return tvShows.size
    }








}

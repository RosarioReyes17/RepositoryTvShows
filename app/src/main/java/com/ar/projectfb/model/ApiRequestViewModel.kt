package com.ar.projectfb.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRequestViewModel(): ViewModel() {

    private val _tvShowList = MutableLiveData<List<TvShow>>()
    private val _exception = MutableLiveData<Throwable>()
    private val _select: MutableLiveData<TvShow> = MutableLiveData()
    private val _tvShow = MutableLiveData<TvShow>()




    private val _pagination: MutableLiveData<pagination> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()


    val tvShow : LiveData<TvShow> = _tvShow
    val tvShowList : LiveData<List<TvShow>> = _tvShowList
    val selected: LiveData<TvShow> = _select
    val exception: LiveData<Throwable> = _exception
    val paginations: LiveData<pagination> = _pagination
    val loading: LiveData<Boolean> = _loading

    val services = ApiServices.getInstance()

    fun loadtvShows(page: Int = 1) {

        _loading.value = true;

        var response = services.getPopular(page).enqueue(object : Callback<ResponseApi> {
            override fun onResponse(
                call: Call<ResponseApi>,
                response: Response<ResponseApi>
            )
            {
                val responseBody = response.body();
                _tvShowList.value = responseBody!!.tv_shows
                _pagination.value = pagination(
                    responseBody.page,
                    responseBody.totals,
                    responseBody.pages
                )
                _loading.value = false;
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable)
            {
                _exception.value = t
                _loading.value = false;
            }

        })

    }
    fun select(tvShow: TvShow ){

        _select.value = tvShow
        getTvDetailsShow(tvShow.id.toString())

    }


    fun search(search: String) {
        _loading.value = true;
        CoroutineScope(Dispatchers.IO).launch {
            val response = services.search(search).execute()
            var responseBody = response.body()
            _tvShowList.postValue(responseBody!!.tv_shows)
            _pagination.postValue(pagination(
                responseBody.page,
                responseBody.totals,
                responseBody.pages

            ))
            _loading.postValue(false)
        }
    }



    fun getTvDetailsShow(q: String){

        var response = services.tvDetailsShow(q).enqueue(object : Callback<ResponseAll> {
            override fun onResponse(
                call: Call<ResponseAll>,
                response: Response<ResponseAll>
            )
            {
                val responseBody = response.body();
                _tvShow.value = responseBody!!.tvShow


            }

            override fun onFailure(call: Call<ResponseAll>, t: Throwable)
            {
                _exception.value = t

            }



        })
    }
}
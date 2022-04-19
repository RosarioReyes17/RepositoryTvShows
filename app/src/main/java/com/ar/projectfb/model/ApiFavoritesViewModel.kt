package com.ar.projectfb.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ApiFavoritesViewModel():ViewModel() {
    val FavoriteApiObject = ApiFavoriteServices.getInstance()

    private val _listFavorite: MutableLiveData<List<ResponseFavoriteApi>> = MutableLiveData()
    val listFav: LiveData<List<ResponseFavoriteApi>> = _listFavorite;

    private val _anyError: MutableLiveData<String> = MutableLiveData()
    val forError: LiveData<String> = _anyError;
//
//    private val _tvShow = MutableLiveData<TvShow>()
//    val tvShow : LiveData<TvShow> = _tvShow;
//
//    private val _exception = MutableLiveData<Throwable>()
//    val exception: LiveData<Throwable> = _exception;




    fun addFavorite(ResponseFavoriteApi: ResponseFavoriteApi, onResult:(ResponseFavoriteApi?)-> Unit) {

        FavoriteApiObject.post(ResponseFavoriteApi).enqueue(
            object: retrofit2.Callback<ResponseFavoriteApi>{
                override fun onResponse(
                    call: Call<ResponseFavoriteApi>,
                    response: Response<ResponseFavoriteApi>
                ) {
                    val varResponse = response.body()
                    onResult (varResponse)
                }

                override fun onFailure(call: Call<ResponseFavoriteApi>, t: Throwable) {
                    onResult(null)
                }


            }

        )

    }

    fun getFavorite(UserId: String ){

        CoroutineScope(Dispatchers.IO).launch {
            FavoriteApiObject.getFavorite(UserId).enqueue(

                object : Callback<List<ResponseFavoriteApi>>{
                    override fun onResponse(
                        call: Call<List<ResponseFavoriteApi>>,
                        response: Response<List<ResponseFavoriteApi>>
                    ) {
                            _listFavorite.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<ResponseFavoriteApi>>, t: Throwable) {
                        _anyError.postValue(t.message)
                    }

                }
            )

        }
    }


}


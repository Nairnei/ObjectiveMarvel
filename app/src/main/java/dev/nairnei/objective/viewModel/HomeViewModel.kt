package dev.nairnei.objective.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.nairnei.objective.model.CharactersModel
import dev.nairnei.objective.repository.MarvelRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var _repository: MarvelRepository = MarvelRepository()
    private lateinit var _context: Context

    private var _liveDataStore = MutableLiveData<CharactersModel>()
    val liveDataStoreModel: LiveData<CharactersModel> get() = _liveDataStore

    private var _liveDataError = MutableLiveData<String>()
    val liveDataError: LiveData<String> get() = _liveDataError

    private var _liveDataLoading = MutableLiveData<Boolean>()
    val liveDataLoading: LiveData<Boolean> get() = _liveDataLoading

    fun setContext(context: Context) {
        _context = context
    }


    fun listShop(currentPage: Int?) {
        try {
            _liveDataLoading.postValue(true)
            _repository.listCharacters(page = currentPage)
                .enqueue(object : Callback<CharactersModel> {
                    override fun onFailure(call: Call<CharactersModel>, error: Throwable) {
                        showErrorLoadingStore(error.message.toString())
                    }

                    override fun onResponse(
                        call: Call<CharactersModel>,
                        response: Response<CharactersModel>
                    ) {
                        if (response.code() != 200) {

                        } else {
                            _liveDataStore.postValue(response.body())
                            _liveDataLoading.postValue(false)
                        }

                    }
                })
        } catch (error: Throwable) {

            _liveDataError.postValue(error.message.toString())
            _liveDataLoading.postValue(false)

        }

    }


    private fun showErrorLoadingStore(error: String) {
        _liveDataError.postValue(error)
        _liveDataLoading.postValue(false)
    }

    fun search(characterName: String) {
        try {
            _liveDataLoading.postValue(true)
            _repository.listCharacters(
                page = null,
                name = if (characterName.isEmpty()) null else characterName
            ).enqueue(object : Callback<CharactersModel> {
                override fun onFailure(call: Call<CharactersModel>, error: Throwable) {
                    showErrorLoadingStore(error.message.toString())
//                    getDataFromCache()
                }

                override fun onResponse(
                    call: Call<CharactersModel>,
                    response: Response<CharactersModel>
                ) {
                    if (response.code() != 200) {

                    } else {
                        _liveDataStore.postValue(response.body())
                        _liveDataLoading.postValue(false)
                    }

                }
            })
        } catch (error: Throwable) {

            _liveDataError.postValue(error.message.toString())
            _liveDataLoading.postValue(false)

        }

    }

}
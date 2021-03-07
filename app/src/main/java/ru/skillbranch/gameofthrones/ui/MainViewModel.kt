package ru.skillbranch.gameofthrones.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.repositories.RootRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val rootRepository: RootRepository,
    val appContext: Application
): ViewModel() {

    fun syncDataIfNeed(): LiveData<LoadResult<Boolean>>{
        val result = MutableLiveData<LoadResult<Boolean>>(LoadResult.Loading(false))
        viewModelScope.launch(Dispatchers.IO) {
            if (rootRepository.isNeedUpdate()){
                if (!isNetworkAvailable(appContext)) {
                    result.postValue(LoadResult.Error(message = appContext.getString(R.string.internet_not_available)))
                    return@launch
                }
                rootRepository.getAllHouses {  }
                result.postValue(LoadResult.Success(true))
            }else{
                delay(5000)
                result.postValue(LoadResult.Success(true))
            }
        }
        return result
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}
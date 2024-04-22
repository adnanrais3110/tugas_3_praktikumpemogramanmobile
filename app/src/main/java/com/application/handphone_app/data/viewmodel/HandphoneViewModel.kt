package com.application.handphone_app.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.handphone_app.data.database.Handphone
import com.application.handphone_app.data.database.HandphoneDao
import com.application.handphone_app.data.database.HandphoneDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HandphoneViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: HandphoneDao
    private val _allHandphones = MutableLiveData<List<Handphone>>()
    private val _getHandphone = MutableLiveData<Handphone>()

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    val allHandphones: LiveData<List<Handphone>> = _allHandphones
    val getHandphone: LiveData<Handphone> = _getHandphone

    init {
        dao = HandphoneDatabase.getInstance(application).hpDao()
    }

    fun getHandphones() {
        viewModelScope.launch(Dispatchers.IO) {
            _allHandphones.postValue(dao.getAllHandphones())
        }
    }

    fun getHp(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _getHandphone.postValue(dao.getHp(id))
        }
    }

    fun addHp(handphone: Handphone) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {dao.insertHp(handphone)}
        }
        getHandphones()
    }

    fun deleteHp(handphone: Handphone) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {dao.deleteHp(handphone)}
        }
        getHandphones()
    }

    fun updateHp(id: Int, hpName: String, brand: String, hpClass: String, price: String) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {
                dao.updateHp(id, hpName, brand, hpClass, price)
            }
        }
        getHandphones()
    }
}
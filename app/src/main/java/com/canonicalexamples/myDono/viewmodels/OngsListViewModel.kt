package com.canonicalexamples.myDono.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.canonicalexamples.myDono.model.Ong
import com.canonicalexamples.myDono.model.ONGDatabase
import com.canonicalexamples.myDono.model.DonationsService
import com.canonicalexamples.myDono.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await


class OngsListViewModel(private val database: ONGDatabase, private val webservice: DonationsService): ViewModel() {
    private val _navigate: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigate: LiveData<Event<Boolean>> = _navigate
    private var ongsList = listOf<Ong>()
    data class Item(val name: String)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            ongsList = database.ongDAO.fetchOngs()
        }
    }

    val numberOfItems: Int
        get() = ongsList.count()

    fun addButtonClicked() {
        _navigate.value = Event(true)
    }

    fun getItem(n: Int) = Item(name = ongsList[n].name)

    fun onClickItem(n: Int) {
        println("Item $n clicked")
        viewModelScope.launch(Dispatchers.IO) {
            val todo = webservice.getDonations(n).await()
            println("todo: ${todo.title}")
        }
    }
}

class OngsListViewModelFactory(private val database: ONGDatabase, private val webservice: DonationsService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OngsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OngsListViewModel(database, webservice) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.weslie10.showmo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val fragment = MutableLiveData<String>()

    fun setFragment(fragment: String) {
        this.fragment.value = fragment
    }

    fun getFragment(): LiveData<String> = fragment
}
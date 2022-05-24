package com.aspk.aspk.ui.home.menu.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel: ViewModel() {
    private var clickCount:Int =0
    private var countLiveData= MutableLiveData<Int>()

    open fun getInitialcount():MutableLiveData<Int>{
        countLiveData.value=clickCount
        return countLiveData
    }

    open fun setInitNumber(number: Int){
        clickCount = number
    }

    open fun add(){
        clickCount+=1
        countLiveData.value=clickCount
    }
    open fun sub(){
        clickCount-=1
        countLiveData.value=clickCount
    }
}
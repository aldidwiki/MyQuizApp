package com.aldidwiki.myquizapp.ui.pregame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aldidwiki.myquizapp.data.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreGameViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    fun getToken() = appRepository.getToken().asLiveData()

    fun clearQuestionEntity() {
        viewModelScope.launch { appRepository.clearQuestionEntity() }
    }
}
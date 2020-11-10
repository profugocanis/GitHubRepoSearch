package com.ijk.githubreposearch.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ijk.domain.entity.RestResult
import com.ijk.githubreposearch.utils.LoadView
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    var errorLiveData = MutableLiveData<String>()

    fun setFragment(fragment: Fragment) {
        errorLiveData.observe(fragment) {
            if (it == "") return@observe
            Toast.makeText(fragment.requireContext(), it, Toast.LENGTH_SHORT).show()
            errorLiveData.value = ""
        }
    }

    fun <T> launch(result: suspend () -> RestResult<T>, success: (T) -> Unit) {
        viewModelScope.launch {
            errorHandle(result.invoke()) {
                success(it)
            }
        }
    }

    open fun <T> errorHandle(result: RestResult<T>, success: (T) -> Unit) {
        when (result) {
            is RestResult.Success -> {
                success(result.value)
            }
            is RestResult.GenericError -> {
                LoadView.dismiss()
                if (!result.error?.message.isNullOrEmpty())
                    errorLiveData.value = result.error?.message
            }
            is RestResult.NetworkError -> {
                LoadView.dismiss()
                errorLiveData.value = "Error Connect!"
            }
        }
    }
}
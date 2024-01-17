package com.palash.biometricauth.view_model

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.palash.biometricauth.biometric.BiometricAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val biometricAuthManager: BiometricAuthManager) : ViewModel() {
    private val _authenticationStatus = MutableLiveData<Boolean>()
    val authenticationStatus: LiveData<Boolean> = _authenticationStatus

    fun authenticate(activity: FragmentActivity) {
        biometricAuthManager.authenticate(
            activity = activity,
            successCallback = {
                _authenticationStatus
                    .value = true
            },
            errorCallback = { error ->
                // Handle the error case
                Log.d("AuthViewModel", "Authentication error: $error")
                _authenticationStatus.value = false
            }
        )
    }
}
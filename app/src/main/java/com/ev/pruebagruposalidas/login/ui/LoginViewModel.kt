package com.ev.pruebagruposalidas.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ev.pruebagruposalidas.utils.FormValidator

class LoginViewModel: ViewModel() {

    private val formValidator = FormValidator()

    private val _name = MutableLiveData<String>("")
    val name : LiveData<String> = _name

    private val _isNameValid = MutableLiveData(true)
    val isNameValid : LiveData<Boolean> = _isNameValid

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _isEmailValid = MutableLiveData(true)
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    private val _age = MutableLiveData("")
    val age : LiveData<String> = _age

    private val _isAgeValid = MutableLiveData(true)
    val isAgeValid : LiveData<Boolean> = _isAgeValid

    private val _isButtonLoginEnabled = MutableLiveData(false)
    val isButtonLoginEnabled : LiveData<Boolean> = _isButtonLoginEnabled


    fun validateName(mName: String) {
        _name.value = mName
        _isNameValid.value = formValidator.isNameValid(mName)
        validateForm()
    }

    fun validateEmail(mEmail: String) {
        _email.value = mEmail
        _isEmailValid.value = formValidator.isEmailValid(mEmail)
        validateForm()
    }

    fun validateAge(mAge: String) {
        _age.value = mAge
        _isAgeValid.value = formValidator.isAgeValid(mAge.toIntOrNull() ?: 0)
        validateForm()
    }

    fun validateForm() {
        _isButtonLoginEnabled.value =
            formValidator.isNameValid(name.value ?: "") == true
                    && formValidator.isEmailValid(email.value ?: "") == true
                    && formValidator.isAgeValid(age.value?.toIntOrNull() ?: 0) == true
    }

    fun handleOnLoginClick() {

    }

}
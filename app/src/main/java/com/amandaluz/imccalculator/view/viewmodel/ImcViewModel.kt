package com.amandaluz.imccalculator.view.viewmodel

import androidx.lifecycle.*
import com.amandaluz.imccalculator.core.State
import com.amandaluz.imccalculator.usecase.calcusecase.ImcCalcUseCase
import com.amandaluz.imccalculator.usecase.resultusecase.ImcResultUseCase
import kotlinx.coroutines.launch

class ImcViewModel(
    private val calcUseCase: ImcCalcUseCase,
    private val resultUsecase: ImcResultUseCase
) : ViewModel() {

    private val _imcCalc = MutableLiveData<State<Double>>()
    val imcCalc: LiveData<State<Double>> = _imcCalc

    private val _imcResult = MutableLiveData<State<String>>()
    val imcResult: LiveData<State<String>> = _imcResult

    fun getCalculatorImc(weight: Double, height: Double) {
        viewModelScope.launch {
            try {
                _imcCalc.value = State.loading(true)
                val response = calcUseCase.calcImc(weight, height)
                _imcCalc.value = State.success(response)
            } catch (e: Exception) {
                _imcCalc.value = State.error(e)
            }
        }
    }

    fun getResultImc(result: Double) {
        viewModelScope.launch {
            try {
                _imcResult.value = State.loading(true)
                val response = resultUsecase.resultImc(result)
                _imcResult.value = State.success(response)
            } catch (e: Exception) {
                _imcResult.value = State.error(e)
            }
        }
    }
}


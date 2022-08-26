package com.amandaluz.imccalculator.usecase.calcusecase

interface ImcCalcUseCase {
    suspend fun calcImc(weight: Double, height: Double): Double
}
package com.amandaluz.imccalculator.usecase.resultusecase

interface ImcResultUseCase {
    suspend fun resultImc(result: Double): String
}
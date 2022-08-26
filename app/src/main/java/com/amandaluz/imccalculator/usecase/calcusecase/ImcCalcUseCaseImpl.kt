package com.amandaluz.imccalculator.usecase.calcusecase

class ImcCalcUseCaseImpl() : ImcCalcUseCase {
    override suspend fun calcImc(weight: Double, height: Double): Double {
        val heightToCm: Double = height.div(100)
        val heightTotal = heightToCm * heightToCm
        val imcTotal = weight.div(heightTotal)
        return "%.4f".format(imcTotal).toDouble()
    }
}



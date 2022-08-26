package com.amandaluz.imccalculator.usecase.resultusecase

class ImcResultUseCaseImpl: ImcResultUseCase {
    override suspend fun resultImc(result: Double): String {
        return when (result) {
            in 0.0..18.4 -> UNDERWEIGHT
            in 18.5..24.9 -> NORMAL_WEIGHT
            in 25.0..29.9 -> OVERWEIGHT
            in 30.0..39.9 -> OBESITY
            else -> SEVERE_OBESITY
        }
    }

    companion object{
        const val UNDERWEIGHT = "Considerado: Abaixo do peso"
        const val NORMAL_WEIGHT = "Considerado: Peso normal"
        const val OVERWEIGHT = "Considerado: Sobrepeso"
        const val OBESITY = "Considerado: Obesidade"
        const val SEVERE_OBESITY = "Considerado: Obesidade grave"

        const val INFO = """
        O IMC é obtido calculando: 
        O peso dividido pela altura ao quadrado.
        Procure um médico para saber mais."""
            .plus("\n\n Zero A 18.4 ${ImcResultUseCaseImpl.UNDERWEIGHT}\n")
            .plus("18.5 A 24.9 ${ImcResultUseCaseImpl.NORMAL_WEIGHT}\n")
            .plus("25.0 A 29.9 ${ImcResultUseCaseImpl.OVERWEIGHT}\n")
            .plus("30.0 A 39.9 ${ImcResultUseCaseImpl.OBESITY}\n")
            .plus("Acima de 40.0 ${ImcResultUseCaseImpl.SEVERE_OBESITY}\n")
    }
}
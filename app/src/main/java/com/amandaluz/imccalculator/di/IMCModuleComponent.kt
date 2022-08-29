package com.amandaluz.imccalculator.di

import com.amandaluz.imccalculator.usecase.calcusecase.ImcCalcUseCase
import com.amandaluz.imccalculator.usecase.calcusecase.ImcCalcUseCaseImpl
import com.amandaluz.imccalculator.usecase.resultusecase.ImcResultUseCase
import com.amandaluz.imccalculator.usecase.resultusecase.ImcResultUseCaseImpl
import com.amandaluz.imccalculator.view.viewmodel.ImcViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object IMCModuleComponent: KoinComponent {

    private val viewModule = module{
        viewModel {
            ImcViewModel(get(), get())
        }
    }

    private val calcUseCase = module {
        single<ImcCalcUseCase> {
            ImcCalcUseCaseImpl()
        }
    }

    private val resultUseCase = module {
        single<ImcResultUseCase> {
            ImcResultUseCaseImpl()
        }
    }

    fun inject() = loadKoinModules(
        listOf(
            viewModule,
            calcUseCase,
            resultUseCase
        )
    )
}
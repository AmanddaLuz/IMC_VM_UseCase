package com.amandaluz.imccalculator.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amandaluz.imccalculator.R
import com.amandaluz.imccalculator.core.Status
import com.amandaluz.imccalculator.databinding.ActivityMainBinding
import com.amandaluz.imccalculator.usecase.calcusecase.ImcCalcUseCase
import com.amandaluz.imccalculator.usecase.calcusecase.ImcCalcUseCaseImpl
import com.amandaluz.imccalculator.usecase.resultusecase.ImcResultUseCase
import com.amandaluz.imccalculator.usecase.resultusecase.ImcResultUseCaseImpl
import com.amandaluz.imccalculator.view.viewmodel.ImcViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ImcViewModel
    private lateinit var calcUseCase: ImcCalcUseCase
    private lateinit var resultUseCase: ImcResultUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        calcUseCase = ImcCalcUseCaseImpl()
        resultUseCase = ImcResultUseCaseImpl()
        viewModel = ImcViewModel(calcUseCase, resultUseCase)
        observeVMEvents()
        setLayout()
    }

    private fun setLayout() {
        binding.run {
            setBtnCalc()
            setDialogConfirm()
            setLearMore()
            setBtnBack()
        }
    }

    private fun ActivityMainBinding.setBtnCalc() {
        btnCalc.setOnClickListener {
            inputsField()
        }
    }

    private fun ActivityMainBinding.inputsField() {
        setVisibility()
        if (inputHeightEdt.text.toString().isEmpty()
            || inputWeightEdit.text.toString().isEmpty()
        ) {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.user_feedback_datas),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val height = inputHeightEdt.text.toString().toDouble()
            val weight = inputWeightEdit.text.toString().toDouble()
            viewModel.getCalculatorImc(weight, height)
        }
    }

    private fun ActivityMainBinding.setBtnBack() {
        icLearnMore.setOnClickListener {
            setVisibility()
            icLearnMore.visibility = View.INVISIBLE
        }
    }

    private fun ActivityMainBinding.setDialogConfirm() {
        icRedefine.setOnClickListener {
            val alertDialog = MaterialAlertDialogBuilder(this@MainActivity)
            alertDialog.setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_message))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.dialog_button_confirm)) { _, _ ->
                    inputHeightEdt.setText("")
                    inputWeightEdit.setText("")
                    txtCalcImc.text = ""
                    txtResultImc.text = ""
                }
            alertDialog.setNegativeButton(getString(R.string.dialog_button_cancel)) { _, _ ->
            }
            val dialog = alertDialog.create()
            dialog.show()
            setVisibility()
        }
    }

    private fun ActivityMainBinding.setLearMore() {
        btnLearnMore.setOnClickListener {
            txtResultImc.visibility = View.GONE
            txtCalcImc.visibility = View.GONE
            containerResultadoImc.visibility = View.GONE
            txtDescription.visibility = View.VISIBLE
            icLearnMore.visibility = View.VISIBLE
            txtDescription.text = ImcResultUseCaseImpl.INFO
        }
    }

    private fun ActivityMainBinding.setVisibility() {
        txtDescription.visibility = View.GONE
        txtResultImc.visibility = View.VISIBLE
        txtCalcImc.visibility = View.VISIBLE
        containerResultadoImc.visibility = View.VISIBLE
    }

    private fun getResult(result: Double) {
        viewModel.getResultImc(result)
    }

    private fun observeVMEvents() {
        viewModel.imcCalc.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { result ->
                        val text = "O seu IMC é: \n"
                        binding.txtCalcImc.text = text.plus(result.toString())
                        getResult(result)
                    }
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
        viewModel.imcResult.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { result ->
                        binding.txtResultImc.text = result
                    }
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
    }
}



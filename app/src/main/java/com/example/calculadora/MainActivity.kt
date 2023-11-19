package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var operators : List<Int>
    private lateinit var allButtons : List<Button>
    private var actualOperation : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        operators = listOf(binding.buttonsLayout.btnSum.id, binding.buttonsLayout.btnDivide.id, binding.buttonsLayout.btnMultiply.id, binding.buttonsLayout.btnSubtract.id, binding.buttonsLayout.btnPercentage.id)

        allButtons = listOf(binding.buttonsLayout.btn0, binding.buttonsLayout.btn00, binding.buttonsLayout.btn1, binding.buttonsLayout.btn2, binding.buttonsLayout.btn3, binding.buttonsLayout.btn4, binding.buttonsLayout.btn5, binding.buttonsLayout.btn6, binding.buttonsLayout.btn7, binding.buttonsLayout.btn8, binding.buttonsLayout.btn9, binding.buttonsLayout.btnClear, binding.buttonsLayout.btnDelete, binding.buttonsLayout.btnPercentage, binding.buttonsLayout.btnDivide, binding.buttonsLayout.btnMultiply, binding.buttonsLayout.btnSubtract, binding.buttonsLayout.btnSum, binding.buttonsLayout.btnEquals, binding.buttonsLayout.btnDecimal)

        for (btn in allButtons) {
            btn.setOnClickListener(this)
        }

    }


    override fun onClick(v: View?) {
        if (v?.id == binding.buttonsLayout.btnEquals.id) {
            if (isPresentOneOperator()) {
                val result = resolveOperation()
                binding.tvResult.text = result.toString()
                binding.tvTemp.text = actualOperation
            }

        } else {
            if (operators.contains(v?.id)) {
                if (endsWithNumber() && onlyOneOperatorExists())
                    appendButtonText(v)

            } else if (v?.id == binding.buttonsLayout.btnClear.id) {
                actualOperation = ""
                binding.tvTemp.text = "0"
                binding.tvResult.text = "0"
                return

            } else if (v?.id == binding.buttonsLayout.btnDelete.id) {
                if (actualOperation.isNotEmpty())
                    actualOperation = actualOperation.substring(0, actualOperation.length - 1)

            } else if (v?.id == binding.buttonsLayout.btnDecimal.id) {
                if (!actualNumberHasDecimal()) {
                    appendButtonText(v)
                }
            } else
                appendButtonText(v)

            binding.tvTemp.text = actualOperation
        }
    }

    private fun isPresentOneOperator(): Boolean {

        for (operator in listOf("%", "÷", "x", "-", "+")) {
            if (actualOperation.contains(operator))
                return true
        }

        return false
    }

    private fun appendButtonText(v: View?) {
        actualOperation += (v as Button).text
    }

    private fun endsWithNumber() = actualOperation.matches("^.*\\d$".toRegex())

    private fun onlyOneOperatorExists() = actualOperation.split("%", "÷", "x", "-", "+").size <= 1

    private fun actualNumberHasDecimal(): Boolean {
        val numbers = actualOperation.split("%", "÷", "x", "-", "+")
        val actualNumber = numbers[numbers.size-1]
        return actualNumber.contains(".")
    }

    private fun resolveOperation(): Double {
        val numbers = actualOperation.split("%", "÷", "x", "-", "+")
        val firstNumber = numbers[0].toDouble()
        val secondNumber = numbers[1].toDouble()
        val operator = actualOperation.substring(numbers[0].length, numbers[0].length + 1)
        var result = 0.0

        when (operator) {
            "%" -> result = firstNumber % secondNumber
            "÷" -> result = firstNumber / secondNumber
            "x" -> result = firstNumber * secondNumber
            "-" -> result = firstNumber - secondNumber
            "+" -> result = firstNumber + secondNumber
        }

        return result
    }


}
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
    private var actualOperation : String = ""

    var firstNumber : Double = Double.NaN
    var secondNumber : Double = Double.NaN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        operators = listOf(binding.buttonsLayout.btnSum.id, binding.buttonsLayout.btnDivide.id, binding.buttonsLayout.btnMultiply.id, binding.buttonsLayout.btnSubtract.id, binding.buttonsLayout.btnPercentage.id, binding.buttonsLayout.btnDecimal.id)

    }

    override fun onClick(v: View?) {
        if (operators.contains(v?.id)) {
            if (actualOperation.matches("^.*\\d$".toRegex()))
                actualOperation += (v as Button).text
        } else if (v?.id == binding.buttonsLayout.btnEquals.id) {
            /*resolveOperation()*/
        } else {
            actualOperation += (v as Button).text
        }

        binding.tvTemp.text = actualOperation
    }


}
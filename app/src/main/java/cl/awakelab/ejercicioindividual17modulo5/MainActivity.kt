package cl.awakelab.ejercicioindividual17modulo5

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import cl.awakelab.ejercicioindividual17modulo5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val divisas = listOf<String>("Dolar", "Pesos", "Euro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, divisas)

        binding.spinner.adapter = adapter
        binding.spinner2.adapter = adapter

        window.statusBarColor = ContextCompat.getColor(this, cl.awakelab.ejercicioindividual17modulo5.R.color.status_bar_color)

        initListener()

    }

    private fun initListener() {
        binding.buttonConvert.setOnClickListener {
            val monto = binding.editTextNumber.text.toString().toDouble()
            val divisaActual = binding.spinner.selectedItem.toString()
            val divisaCambio = binding.spinner2.selectedItem.toString()
            val resultado = conversorDivisas(monto, divisaActual, divisaCambio)
            binding.textViewConversion.setText(resultado.toString())
        }
        binding.buttonReset.setOnClickListener {
            limpiar()
        }
    }

    fun conversorDivisas(monto: Double, divisaActual: String, divisaCambio: String): Double {

        var valorFinal: Double = 0.0

        when(divisaActual) {
            "Dolar" -> if (divisaCambio == "Pesos") {
                valorFinal = monto*817
            } else if (divisaCambio == "Dolar") {
                valorFinal = monto
            } else if (divisaCambio == "Euro") {
                valorFinal = monto*0.90
            }

            "Pesos" -> if (divisaCambio == "Pesos") {
                valorFinal = monto
            } else if (divisaCambio == "Dolar") {
                valorFinal = monto*0.001223
            } else if (divisaCambio == "Euro") {
                valorFinal = monto*0.001098
            }

            "Euro" -> if (divisaCambio == "Pesos") {
                valorFinal = monto*910.02
            } else if (divisaCambio == "Dolar") {
                valorFinal = monto*1.11
            } else if (divisaCambio == "Euro") {
                valorFinal = monto
            }

            else -> {valorFinal = monto}
        }

        return valorFinal

    }

    fun limpiar() {
        binding.textViewConversion.text = ""
        binding.editTextNumber.text.clear()
    }

}
package com.example.jwtloginfinal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.jwtloginfinal.Services.ValueService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Pega referência dos botões
        val btnGet = findViewById(R.id.btnGet) as Button
        val btnGetTok = findViewById(R.id.btnGetTok) as Button
        val btnSair = findViewById(R.id.btnSair) as Button

        // Para receber a lista de valores
        var list : List<String>?
        list = listOf("1" , "2" , "3")

        btnGet.setOnClickListener{

            // Instancia API de valores
            var api = ValueService()

            api.getValues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val responseCode = response.code()
                    when (responseCode) {
                        200, 201, 202 -> {
                            Log.e("Sucesso:", response.code().toString())
                            list = response.body()?.toList()
                            var texto : String = ""
                            list?.forEach{

                                texto = texto + it + ", "
                            }

                            Toast.makeText(this@Home, texto, Toast.LENGTH_SHORT).show()
                        }
                        401 -> {
                            Log.e("Erro:", response.code().toString())}
                        402 -> {
                            Log.e("Erro:", response.code().toString())}
                        500 -> {
                            Log.e("Erro:", response.code().toString())}
                        501 -> {
                            Log.e("Erro:", response.code().toString())}
                    }
                }, { error ->
                    //mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = (error as HttpException).response()
                        when (response.code()) {

                        }
                    } else {
                        Log.e("Token:", "Other errors")
                    }
                    //mainView!!.onError(error)
                })
        }

        btnGetTok.setOnClickListener {

            // Instancia API de valores
            var api = ValueService()

            api.getValuesAuth(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val responseCode = response.code()
                    when (responseCode) {
                        200, 201, 202 -> {
                            Log.e("Sucesso:", response.code().toString())
                            list = response.body()?.toList()
                            var texto : String = ""
                            list?.forEach{

                                texto = texto + it + ", "
                            }

                            Toast.makeText(this@Home, texto, Toast.LENGTH_SHORT).show()
                        }
                        401 -> {
                            Toast.makeText(this@Home, "Erro 401", Toast.LENGTH_SHORT).show()}
                        402 -> {
                            Log.e("Erro:", response.code().toString())}
                        500 -> {
                            Log.e("Erro:", response.code().toString())}
                        501 -> {
                            Log.e("Erro:", response.code().toString())}
                    }
                }, { error ->
                    //mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = (error as HttpException).response()
                        when (response.code()) {

                        }
                    } else {
                        Log.e("Token:", "Other errors")
                    }
                    //mainView!!.onError(error)
                })

        }

        btnSair.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

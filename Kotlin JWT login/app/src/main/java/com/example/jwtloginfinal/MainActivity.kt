package com.example.jwtloginfinal

import android.content.Intent
import com.example.jwtloginfinal.Model.AccesCredentials
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.jwtloginfinal.Services.LoginService
import com.example.jwtloginfinal.Services.ValueService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

// Variável para guardar o token
var token = "";

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pega referência dos botões
        val btnLogin = findViewById(R.id.btnLogin) as Button

        // Evento de clique do botão
        btnLogin.setOnClickListener {

            // Instancia credenciais
            var credentials = AccesCredentials()
            credentials.userID = "igorbrandao00@gmail.com"
            credentials.accessKey = "123456"
            credentials.grantType = "password"

            // Instancia API de login
            var api = LoginService()

            api.getToken(credentials)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val responseCode = response.code()
                    when (responseCode) {
                        200, 201, 202 -> {
                            Log.e("Sucesso:", response.code().toString())
                            token = response.body()?.accessToken.toString()

                            val intent = Intent(this, Home::class.java)
                            startActivity(intent)

                        }
                        401 -> {Log.e("Erro:", response.code().toString())}
                        402 -> {Log.e("Erro:", response.code().toString())}
                        500 -> {Log.e("Erro:", response.code().toString())}
                        501 -> {Log.e("Erro:", response.code().toString())}
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

            Toast.makeText(this@MainActivity, token, Toast.LENGTH_SHORT).show()
        }



    }
}

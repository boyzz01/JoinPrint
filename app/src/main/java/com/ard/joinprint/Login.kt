package com.ard.joinprint

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ard.joinprint.API.BaseApiService
import com.ard.joinprint.API.UtilsApi.aPIService
import com.ard.joinprint.Model.Response.TokenResponse
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class Login : AppCompatActivity() {


    var uname: String? = null
    var pass: String? = null

    var tokenResponse: TokenResponse? = null
    var prefManager: PrefManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        btn_Login.setOnClickListener(View.OnClickListener {
            uname = username.editText!!.text.toString()
            pass = password.editText!!.text.toString()
            val grantType = "password"
            val clientId = "7"
            val clientSecret = "7NDniuscI4542dXzaUiCTN79iIuuMNiQ0wcItmxa"
            tokenResponse = TokenResponse()
            val progressDialog = ProgressDialog.show(this@Login, null, "Please Wait...", true, false)
            aPIService!!.tokenRequest(grantType, clientId, clientSecret, uname, pass)!!
                .enqueue(object : Callback<TokenResponse?> {
                    override fun onResponse(
                        call: Call<TokenResponse?>,
                        response: Response<TokenResponse?>
                    ) {
                        if (response.isSuccessful) {
                            prefManager = PrefManager("data", this@Login)
                            tokenResponse = response.body()
                            prefManager!!.isLogin = true
                            prefManager!!.setToken(
                                tokenResponse!!.accessToken,
                                tokenResponse!!.refreshToken
                            )
                            progressDialog.dismiss()
                            val intent = Intent(this@Login, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                        }
                        if (response.code() == 400) {
                            if (!response.isSuccessful) {
                                var jsonObject: JSONObject? = null
                                try {
                                    jsonObject = JSONObject(response.errorBody()!!.string())
                                    val errorMessage = jsonObject.getString("message")
                                    Toast.makeText(
                                        this@Login,
                                        "Error : $errorMessage",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    progressDialog.dismiss()
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                    progressDialog.dismiss()
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                    progressDialog.dismiss()
                                }
                                progressDialog.dismiss()
                            }
                            progressDialog.dismiss()
                        } else {
                        }
                    }

                    override fun onFailure(call: Call<TokenResponse?>, t: Throwable) {
                        Toast.makeText(this@Login, "Error : " + t.message, Toast.LENGTH_SHORT)
                            .show()
                        progressDialog.dismiss()
                    }
                })
        })
    }

    override fun onStart() {
        prefManager = PrefManager("data", this@Login)
        if (prefManager!!.isLogin) {
            val intent = Intent(this@Login, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        super.onStart()
    }

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press Back Again To Exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
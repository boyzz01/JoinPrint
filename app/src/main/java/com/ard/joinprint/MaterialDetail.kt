package com.ard.joinprint

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.ard.joinprint.API.BaseApiService
import com.ard.joinprint.API.UtilsApi
import com.ard.joinprint.API.UtilsApi.aPIService
import com.ard.joinprint.Model.RawMaterial
import com.ard.joinprint.Model.Response.MaterialDetailResponse
import com.ard.joinprint.Model.Store
import com.ard.joinprint.Model.Supplier
import kotlinx.android.synthetic.main.activity_material_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Array
import java.util.*
@Suppress("DEPRECATION")
class MaterialDetail : AppCompatActivity() {

    var rawMaterial: RawMaterial? = null
    var rawMaterialId: String? = null
    var actionBar: ActionBar? = null
    var prefManager: PrefManager? = null
    var bearerToken: String? = null

    var apiService: BaseApiService? = null
    var storeList: List<Store>? = null
    var supplier: Supplier? = null
    var storeAdapter: ArrayAdapter<String>? = null
    var storeNameList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_detail)
        val intent = intent
        rawMaterialId = intent.getStringExtra("materialId")
        actionBar = supportActionBar
        supportActionBar!!.setTitle("Material Detail")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        apiService = aPIService
        prefManager = PrefManager("data", this)
        bearerToken = prefManager!!.accessToken

        storeNameList = ArrayList()
        storeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            storeNameList as ArrayList<String>
        )

       val progressDialog =
            ProgressDialog.show(this@MaterialDetail, null, "Please Wait...", true, false)
        aPIService!!.showRawMaterial("Bearer $bearerToken", rawMaterialId)!!.enqueue(object : Callback<MaterialDetailResponse?> {
            override fun onResponse(
                call: Call<MaterialDetailResponse?>,
                response: Response<MaterialDetailResponse?>
            ) {
                storeList = ArrayList()
                supplier = Supplier()
                rawMaterial = response.body()!!.data
                storeList = rawMaterial!!.stores
                supplier = rawMaterial!!.supplier
                materialName.setText(rawMaterial!!.nameEng)
                materialId.setText(": " + rawMaterial!!.id)
                materialPrice.setText(": " + rawMaterial!!.defaultPrice + "$")
                materialSKU.setText(": " + rawMaterial!!.sku)
                materialUnit.setText(": " + rawMaterial!!.unit)
                materialSupplier.setText(": " + supplier!!.name)
                for (i in storeList!!.indices) {
                    (storeNameList as ArrayList<String>).add((i + 1).toString() + ". " + storeList!![i].name)
                }
                storeListView.setAdapter(storeAdapter)
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<MaterialDetailResponse?>, t: Throwable) {
                Toast.makeText(this@MaterialDetail, "Error : " + t.message, Toast.LENGTH_SHORT)
                    .show()
                progressDialog.dismiss()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


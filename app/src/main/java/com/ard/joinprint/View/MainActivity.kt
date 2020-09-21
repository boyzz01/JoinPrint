package com.ard.joinprint.View


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ard.joinprint.API.UtilsApi.aPIService
import com.ard.joinprint.Adapter.AdapterMaterial
import com.ard.joinprint.Model.RawMaterial
import com.ard.joinprint.Model.Response.ListRawMaterialResponse
import com.ard.joinprint.Model.Response.ListStoreResponse
import com.ard.joinprint.Model.Store
import com.ard.joinprint.Util.PrefManager
import com.ard.joinprint.R
import com.ard.joinprint.Util.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var prefManager: PrefManager? = null

    var accessToken: String? = null
    var refreshToken: String? = null
    var actionBar: ActionBar? = null
    var storeList: List<Store>? = null
    var rawMaterials: List<RawMaterial>? = null
    var searchList: MutableList<RawMaterial>? = null



    var adapterMaterial: AdapterMaterial? = null
    var lastItem = 0
    var preLast = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        mainLayout.setVisibility(View.GONE)
        prefManager = PrefManager("data", this@MainActivity)
        accessToken = prefManager!!.accessToken
        refreshToken = prefManager!!.refreshToken

        rvMaterial.setHasFixedSize(true)
        rvMaterial.setLayoutManager(LinearLayoutManager(this@MainActivity))
        actionBar = supportActionBar
        supportActionBar!!.title = ""
        loadStore()
    }

    private fun loadStore() {

        storeList = ArrayList<Store>()
        val loadingDialog = LoadingDialog(this@MainActivity)
        loadingDialog.startLoadingDialog()

        aPIService!!.listStoreRequest("Bearer $accessToken")!!.enqueue(object :Callback<ListStoreResponse?>
        {
            override fun onResponse(
                call: Call<ListStoreResponse?>,
                response: Response<ListStoreResponse?>
            ) {
                if (response.isSuccessful()) {

                    storeList = response.body()!!.data
                    loadingDialog.dismissDialog()
                    val builderSingle = AlertDialog.Builder(this@MainActivity)
                    //builderSingle.setIcon(R.drawable.ic_launcher);
                    builderSingle.setTitle("Please Select One Store-")
                    builderSingle.setCancelable(false)
                    val arrayAdapter = ArrayAdapter<String>(
                        this@MainActivity,
                        android.R.layout.select_dialog_singlechoice
                    )
                    for (i in storeList!!.indices) {
                        arrayAdapter.add(storeList!![i].name)
                    }
                    builderSingle.setAdapter(arrayAdapter) { dialog, which ->
                        val strName = arrayAdapter.getItem(which)
                        mainLayout!!.visibility = View.VISIBLE
                        val storeId: String = storeList!![which].uuid.toString()
                        supportActionBar!!.setTitle(strName)
                        supportActionBar!!.setSubtitle("Raw Material List")
                        loadMaterial(storeId)
                    }
                    builderSingle.show()
                }
            }

            override fun onFailure(call: Call<ListStoreResponse?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error : " + t.message, Toast.LENGTH_SHORT)
                    .show()
                loadingDialog.dismissDialog()
            }

        })


    }

    private fun loadMaterial(storeId: String) {
        val loadingDialog = LoadingDialog(this@MainActivity)
        loadingDialog.startLoadingDialog()
        aPIService!!.listRawMaterial("Bearer $accessToken", storeId)!!.enqueue(object : Callback<ListRawMaterialResponse?> {
            override fun onResponse(
                call: Call<ListRawMaterialResponse?>,
                response: Response<ListRawMaterialResponse?>
            ) {
                if (response.isSuccessful()) {
                    rawMaterials = ArrayList<RawMaterial>()
                    rawMaterials = response.body()!!.data
                    searchList = ArrayList<RawMaterial>()
                    searchList!!.addAll(rawMaterials!!)
                    adapterMaterial = AdapterMaterial(this@MainActivity,
                        rawMaterials as MutableList<RawMaterial>
                    )
                    rvMaterial.adapter = adapterMaterial
                    loadingDialog.dismissDialog()
                }
            }

            override fun onFailure(call: Call<ListRawMaterialResponse?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error : " + t.message, Toast.LENGTH_SHORT)
                    .show()
                loadingDialog.dismissDialog()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapterMaterial!!.filter(query, searchList)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapterMaterial!!.filter(newText, searchList)
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.logout -> {
                prefManager = PrefManager("data", this)
                val intent = Intent(this@MainActivity, Login::class.java)
                prefManager!!.isLogin = false
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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


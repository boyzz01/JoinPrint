package com.ard.joinprint.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ard.joinprint.View.MaterialDetail
import com.ard.joinprint.Model.RawMaterial
import com.ard.joinprint.R

class AdapterMaterial(
    private val context: Context,
    private val rawMaterialList: MutableList<RawMaterial>
) : RecyclerView.Adapter<AdapterMaterial.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.single_material_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rawMaterial = rawMaterialList[position]
        holder.rawMaterialName.text = rawMaterial.nameEng
        holder.rawMaterialPrice.text = "" + rawMaterial.defaultPrice + "$"
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MaterialDetail::class.java)
            intent.putExtra("materialId", rawMaterial.uuid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return rawMaterialList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rawMaterialName: TextView
        var rawMaterialPrice: TextView
        var backgroundLayout: ConstraintLayout

        init {
            rawMaterialName = itemView.findViewById(R.id.materialName)
            backgroundLayout = itemView.findViewById(R.id.backgroundLayout)
            rawMaterialPrice = itemView.findViewById(R.id.materialPrice)
        }
    }

    fun filter(text: String, itemsCopy: MutableList<RawMaterial>?) {
        var query = text
        if (text.isEmpty()) {
            rawMaterialList.clear()
            rawMaterialList.addAll(itemsCopy!!)
        } else {
            rawMaterialList.clear()
            query = text.toLowerCase()
            for (item in itemsCopy!!) {
                if (item.nameEng!!.toLowerCase().contains(query)) {
                    rawMaterialList.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }
}
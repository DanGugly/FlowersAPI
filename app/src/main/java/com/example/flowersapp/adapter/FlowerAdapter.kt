package com.example.flowersapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowersapp.R
import com.example.flowersapp.model.Flowers
import com.example.flowersapp.model.FlowersItem

class FlowerAdapter(
    private val flowerList: MutableList<FlowersItem> = mutableListOf()
) : RecyclerView.Adapter<FlowerViewHolder>() {

    fun importFlowers(flowers: Flowers) {
        flowerList.addAll(flowers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val flowerView = LayoutInflater.from(parent.context).inflate(
            R.layout.flower_items,
            parent,
            false
        )
        return FlowerViewHolder(flowerView)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.flowerName.text = flowerList[position].name
        holder.flowerCategory.text = flowerList[position].category
        holder.flowerPhoto.text = flowerList[position].photo
        holder.flowerId.text = flowerList[position].productId.toString()
        holder.flowerInstructions.text = flowerList[position].instructions
        holder.flowerPrice.text = flowerList[position].price.toString()
    }

    override fun getItemCount(): Int = flowerList.size

}

class FlowerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val flowerName : TextView = itemView.findViewById(R.id.flower_name)
    val flowerCategory : TextView = itemView.findViewById(R.id.flower_category)
    val flowerPhoto : TextView = itemView.findViewById(R.id.flower_photo)
    val flowerId : TextView = itemView.findViewById(R.id.flower_id)
    val flowerInstructions : TextView = itemView.findViewById(R.id.flower_instructions)
    val flowerPrice : TextView = itemView.findViewById(R.id.flower_price)
}


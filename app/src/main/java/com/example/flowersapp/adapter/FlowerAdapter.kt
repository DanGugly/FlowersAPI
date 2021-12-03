package com.example.flowersapp.adapter

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowersapp.R
import com.example.flowersapp.model.Flowers
import com.squareup.picasso.Picasso


class FlowerAdapter(
    //private val flowerList: MutableList<FlowersItem> = mutableListOf()
    private val flowerList: Flowers = Flowers()
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
        val flower = flowerList[position]
        holder.flowerName.text = flower.name
        holder.flowerCategory.text = flower.category
        getFlowerImage(flower.photo, holder.flowerPhoto)
        //holder.flowerPhoto.setImageBitmap(getFlowerImage(RestApi.BASE_URL+flowerList[position].photo))
        holder.flowerId.text = flower.productId.toString()
        holder.flowerInstructions.text = flower.instructions
        holder.flowerPrice.text = flower.price.toString()
    }

    override fun getItemCount(): Int = flowerList.size

    fun getFlowerImage(path:String, flower: ImageView){
        Picasso
            .get()
            .load(IMAGE_URL+path)
            .resize(350,350)
            //.centerCrop()
            .into(flower)
    }

    companion object {
        //Url we use to download image with picasso
        private const val IMAGE_URL = "http://services.hanselandpetal.com/photos/"
    }
}

class FlowerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val flowerName : TextView = itemView.findViewById(R.id.flower_name)
    val flowerCategory : TextView = itemView.findViewById(R.id.flower_category)
    val flowerPhoto : ImageView = itemView.findViewById(R.id.flower_photo)
    val flowerId : TextView = itemView.findViewById(R.id.flower_id)
    val flowerInstructions : TextView = itemView.findViewById(R.id.flower_instructions)
    val flowerPrice : TextView = itemView.findViewById(R.id.flower_price)
}


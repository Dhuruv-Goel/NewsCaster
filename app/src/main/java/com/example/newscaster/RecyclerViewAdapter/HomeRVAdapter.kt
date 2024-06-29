package com.example.newscaster.RecyclerViewAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newscaster.DataClass.NewsDataClass
import com.example.newscaster.R

class HomeRVAdapter( private val itemlistener:NewsItemClicked, private val btnlistener: ShareBtnClicked ) :RecyclerView.Adapter<HomeRVAdapter.HomeViewHolder>(){

    private  val arrayList : ArrayList<NewsDataClass> = ArrayList()

    class HomeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
          val mainHeading:TextView = itemView.findViewById(R.id.mainHeading)
          val content:TextView = itemView.findViewById(R.id.content)
          val author:TextView = itemView.findViewById(R.id.author)
          val time:TextView = itemView.findViewById(R.id.time)
          val image:ImageView = itemView.findViewById(R.id.imageView)
//          val cardView:CardView = itemView.findViewById(R.id.cardView)
        val btnShare: ImageButton = itemView.findViewById(R.id.btnShare)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        val viewHolder= HomeViewHolder(view)
        view.setOnClickListener {
            itemlistener.onItemClicked(arrayList[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
      return  arrayList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
      val currentItem = arrayList[position]
        holder.mainHeading.text = currentItem.title
        holder.content.text = currentItem.description
        holder.author.text = currentItem.author
        holder.time.text = "Published At->" + currentItem.publishedAt
        Glide.with(holder.itemView.context)
            .load(currentItem.urlToImage)
            .into(holder.image)
        holder.btnShare.setOnClickListener{
            btnlistener.onButtonCheckedListener(arrayList[holder.absoluteAdapterPosition])
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updateNews: ArrayList<NewsDataClass>){
        arrayList.clear()
        arrayList.addAll(updateNews)
        notifyDataSetChanged()
    }

}

interface ShareBtnClicked {
fun onButtonCheckedListener(items:NewsDataClass)
}

interface NewsItemClicked {
    fun onItemClicked(items:NewsDataClass)
}
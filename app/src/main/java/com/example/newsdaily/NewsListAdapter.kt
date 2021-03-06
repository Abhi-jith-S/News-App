package com.example.newsdaily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(val listner:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)//inflater converts xml to view format
        val viewHolder=NewsViewHolder(view)//when clicked on each news what should happen
        view.setOnClickListener {
            listner.onItemClicked(items[viewHolder.adapterPosition])
        }
      return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val current=items[position]
        holder.titleNew.text=current.title
        holder.author.text=current.author
        Glide.with(holder.itemView.context).load(current.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
      return items.size
    }

    fun updateNews(updatedNews:ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
val titleNew: TextView=itemView.findViewById(R.id.title)
    val image:ImageView=itemView.findViewById(R.id.image)
    val author:TextView=itemView.findViewById(R.id.author)
}


//interface helps  to call back activity from adapter when a news is clicked
interface NewsItemClicked{
    fun onItemClicked(item:News)
}
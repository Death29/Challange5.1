package com.example.challange51

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SlideAdapter(private val introSlide: List<IntroSlide>):RecyclerView.Adapter<SlideAdapter.SlideViewHolder>() {
    inner class SlideViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val desk = view.findViewById<TextView>(R.id.desk)
        private val imageSlide =view.findViewById<ImageView>(R.id.imageSlide)
        private val nameText = view.findViewById<EditText>(R.id.editNama)

        fun bind (introSlide: IntroSlide, position: Int){
            desk.text = introSlide.description
            imageSlide.setImageResource(introSlide.icon)
            nameText.setText("")
            if (position == 0){
                nameText.visibility = View.GONE
            }else if (position == 1){
                nameText.visibility = View.GONE
            }else{
                nameText.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        return SlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider_image,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return introSlide.size
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.bind(introSlide[position],position)
    }
}
package com.example.leitordenoticias

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ItemAdapter(var list: ArrayList<MainActivity.Item>, val context: Context):
RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

	class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
		val titulo		= view.findViewById(R.id.titulo)		as TextView
		val autor		= view.findViewById(R.id.autor)			as TextView
		val data		= view.findViewById(R.id.data)			as TextView
		val btnVerMais	= view.findViewById(R.id.btnVerMais)	as Button
		val imagem		= view.findViewById(R.id.imagem)		as ImageView
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
		val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
		val ivh = ItemViewHolder(v)

		return  ivh
	}

	override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
		holder?.titulo?.text		= list[position].titulo
		holder?.autor?.text		= list[position].autor
		holder?.data?.text		= SimpleDateFormat("dd MM yyyy").format(Date(list[position].data))
		holder?.btnVerMais?.setOnClickListener {
			val intent = Intent(Intent.ACTION_VIEW, list[position].link)
			context.startActivity(intent)
		}

		DownloadImageTask(holder?.imagem!!).execute(list[position].imagem)
	}

	override fun getItemCount(): Int = list.size



}//class ItemAdapter(var list:ArrayList<MainActivity.Item>, val context: Context){


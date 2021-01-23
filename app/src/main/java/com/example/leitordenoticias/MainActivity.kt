package com.example.leitordenoticias

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback {
    lateinit var listView: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>

    val listItens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //gerenciador de layout
        val layout = LinearLayoutManager(this)
        listView = findViewById(R.id.recView)
        listView.layoutManager = layout

        adapter = ItemAdapter(listItens, this)
        listView.adapter = adapter


        /* these sites worked as an example....in .load
			https://rss.tecmundo.com.br/feed
		 */
        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed")
			.callback(this).async()


    }//override fun onCreate(savedInstanceState: Bundle?) {

    override fun onLoaded(newArticles: MutableList<Article>?) {
        newArticles?.mapTo(listItens){
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }//newArticles?.mapTo(listItens){

        adapter.notifyDataSetChanged()
    }//override fun onLoaded(newArticles: MutableList<Article>?) {

    override fun onLoadFailed() {}
    override fun onPreload() {}

    data class Item (val titulo:String, val autor: String, val data:Long, val link:Uri, val imagem:String)

}//class MainActivity : AppCompatActivity() {
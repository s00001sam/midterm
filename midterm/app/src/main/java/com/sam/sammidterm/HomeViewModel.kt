package com.sam.sammidterm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.util.*


class HomeViewModel: ViewModel() {

    var articles = FirebaseFirestore.getInstance()
        .collection("articles")

    val document = articles.document()

    var allArticles = mutableListOf<Articles>()

    val setArticles = MutableLiveData<List<Articles>>()

    val test = MutableLiveData<List<String>>()

    var authorList = listOf<String>()

    fun addData() {

        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "wayne@school.appworks.tw",
                "id" to "waynechen323",
                "name" to "AKA小安老師"
            ),
                "title" to "​IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值撐住女神氣場​",
                "content" to "​南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，但俗話說人無完美、美玉微瑕，曾再跟工作人員的互動影片中坦言自己品味很奇怪，近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」超復古穿搭造型，卻意外美出新境界。",
                "createdTime" to Calendar.getInstance().timeInMillis,
                "id" to document.id,
                "tag" to "Beauty")

        document.set(data)

    }

    fun getAll(){
        articles.get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("sam", document.id + " => " + document.data)
                    }
                } else {
                    Log.w("sam", "Error getting documents.", task.exception)
                }
            })
            .addOnSuccessListener {
                for(i in it){
                    val artilcle = Articles()
                    artilcle.name = i.getString("author.name")
                    artilcle.title = i["title"] as String
                    artilcle.content = i["content"] as String
                    artilcle.createdTime = i["createdTime"] as Long
                    artilcle.id = i["id"] as String
                    artilcle.tag = i["tag"] as String
                    artilcle.content = i["content"] as String

                    allArticles.add(artilcle)
                    Log.d("sam", "id=${artilcle.id}")
                }
                setArticles.value = allArticles
                Log.d("sam", "all=${setArticles.value}")
            }


    }


}
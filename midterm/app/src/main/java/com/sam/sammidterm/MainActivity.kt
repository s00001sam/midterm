package com.sam.sammidterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity() {

    var articles = FirebaseFirestore.getInstance()
        .collection("articles")

    val setArticles = MutableLiveData<List<Articles>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.myNavHostFragment)

    }

    fun getAll(){
        articles
            .orderBy("createdTime", Query.Direction.DESCENDING)
            .get()
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
                var allArticles = mutableListOf<Articles>()
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

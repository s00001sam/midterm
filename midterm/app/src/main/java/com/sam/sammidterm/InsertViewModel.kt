package com.sam.sammidterm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class InsertViewModel: ViewModel() {

    var articles = FirebaseFirestore.getInstance()
        .collection("articles")

    val document = articles.document()

    val enterTitle = MutableLiveData<String>()
    val enterTag = MutableLiveData<String>()
    val enterContent = MutableLiveData<String>()

    // Handle leave login
    private val _leave = MutableLiveData<Boolean>()

    val leave: LiveData<Boolean>
        get() = _leave
    fun leave() {
        _leave.value = true
    }
    fun onLeaveCompleted() {
        _leave.value = null
    }

    fun addData(title: String, tag: String, content: String) {

        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "wayne@school.appworks.tw",
                "id" to "waynechen323",
                "name" to "AKA小安老師"
            ),
            "title" to title,
            "content" to content,
            "createdTime" to Calendar.getInstance().timeInMillis,
            "id" to document.id,
            "tag" to tag)

        document.set(data)

    }


}
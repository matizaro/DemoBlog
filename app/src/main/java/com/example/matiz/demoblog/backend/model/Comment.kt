package com.example.matiz.demoblog.backend.model

data class Comment(val postId : Int = 0,
                   val id : Int = 0,
                   val name : String = "",
                   val email : String = "",
                   val body : String = "")
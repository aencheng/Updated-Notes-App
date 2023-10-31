package com.example.project7

import com.google.firebase.database.Exclude

data class Note(
    @get:Exclude
    var noteId: String = "",
    var noteName: String = "",
    var noteContent: String = ""
)

package com.example.project7

import androidx.recyclerview.widget.DiffUtil

class NoteDiffItemCallBack : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note)
            = (oldItem.noteId == newItem.noteId)
    override fun areContentsTheSame(oldItem: Note, newItem: Note) = (oldItem == newItem)
}
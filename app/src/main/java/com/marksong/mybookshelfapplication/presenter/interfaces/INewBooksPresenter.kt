package com.marksong.mybookshelfapplication.presenter.interfaces

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface INewBooksPresenter {
    fun onNewBooks(context: Context, new_booklist_recyclerview: RecyclerView)
}
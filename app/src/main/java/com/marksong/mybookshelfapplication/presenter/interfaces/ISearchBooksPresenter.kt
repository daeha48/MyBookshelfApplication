package com.marksong.mybookshelfapplication.presenter.interfaces

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface ISearchBooksPresenter{
    fun onSearchBooks(context: Context, search_booklist_recyclerview: RecyclerView, search: String)
    fun onSearchBooksNextPage(context: Context, search_booklist_recyclerview: RecyclerView, search: String, page: Int)
}
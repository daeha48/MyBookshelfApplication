package com.marksong.mybookshelfapplication.presenter.interfaces

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface IBookmarkPresenter {
    fun onBookmarks(context: Context, bookHistoryRecyclerView: RecyclerView)
}
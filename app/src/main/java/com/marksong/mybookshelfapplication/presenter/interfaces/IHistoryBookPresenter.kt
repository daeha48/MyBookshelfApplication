package com.marksong.mybookshelfapplication.presenter.interfaces

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface IHistoryBookPresenter {
    fun onBooksHistory(context: Context, bookHistoryRecyclerView: RecyclerView)
}
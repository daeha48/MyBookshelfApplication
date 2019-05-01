package com.marksong.mybookshelfapplication.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marksong.bookshelfapp.model.BooksItem
import com.marksong.mybookshelfapplication.MainActivity
import com.marksong.mybookshelfapplication.adapters.HistoryTabSwipeRecyclerViewAdapter
import com.marksong.mybookshelfapplication.fragmentviews.interfaces.IHistoryBookFragment
import com.marksong.mybookshelfapplication.presenter.interfaces.IHistoryBookPresenter

class HistoryBookPresenter(internal var iHistoryBookFragment: IHistoryBookFragment): IHistoryBookPresenter{
    override fun onBooksHistory(context: Context, bookHistoryRecyclerView: RecyclerView) {
        val bookItem = (context as MainActivity).getBoxStore()?.boxFor(BooksItem::class.java)
        val bookList = bookItem.all
        val bookHistoryList = arrayListOf<BooksItem>()
        bookList.forEach { bookHistoryList.add(0,it) }
        if (bookList.size > 0){
            iHistoryBookFragment.onBookHistoryResult(true, "Book history exists and received")
        } else{
            iHistoryBookFragment.onBookHistoryResult(false, "No history or caching failed")
        }

        bookHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        bookHistoryRecyclerView.adapter = HistoryTabSwipeRecyclerViewAdapter(context!!, bookList = bookHistoryList)
    }
}
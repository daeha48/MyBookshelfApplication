package com.marksong.mybookshelfapplication.presenter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marksong.bookshelfapp.model.BooksItem
import com.marksong.bookshelfapp.model.BooksItem_
import com.marksong.mybookshelfapplication.MainActivity
import com.marksong.mybookshelfapplication.adapters.BookmarkSwipeRecyclerViewAdapter
import com.marksong.mybookshelfapplication.fragmentviews.interfaces.IBookmarkFragment
import com.marksong.mybookshelfapplication.presenter.interfaces.IBookmarkPresenter

class BookmarkPresenter(internal var iBookmarkFragment: IBookmarkFragment): IBookmarkPresenter{
    override fun onBookmarks(context: Context, bookmarkedRecyclerView: RecyclerView) {
        val bookItem = (context as MainActivity).getBoxStore()?.boxFor(BooksItem::class.java)
        val bookList = bookItem.query().equal(BooksItem_.bookmark, true).build()
        val bookmarkedList = arrayListOf<BooksItem>()
        bookList.forEach { bookmarkedList.add(0,it) }

        if (bookmarkedList.size > 0){
            iBookmarkFragment.onBookmarkedResult(true, "Bookmark list received")
        }else {
            iBookmarkFragment.onBookmarkedResult(false, "No bookmarked books yet. Go bookmark some!")
        }

        bookmarkedRecyclerView.layoutManager = LinearLayoutManager(context)
        bookmarkedRecyclerView.adapter = BookmarkSwipeRecyclerViewAdapter(context!!, bookmarkedList)
    }
}
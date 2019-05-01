package com.marksong.mybookshelfapplication.fragmentviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marksong.mybookshelfapplication.R
import com.marksong.mybookshelfapplication.fragmentviews.interfaces.IBookmarkFragment
import com.marksong.mybookshelfapplication.presenter.BookmarkPresenter
import com.marksong.mybookshelfapplication.presenter.interfaces.IBookmarkPresenter
import kotlinx.android.synthetic.main.books_bookmark_layout.*
import kotlinx.android.synthetic.main.books_bookmark_layout.messages_text_view

class BookmarkFragment: Fragment(), IBookmarkFragment{

    private lateinit var iBookMarkPresenter: IBookmarkPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iBookMarkPresenter = BookmarkPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.books_bookmark_layout,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iBookMarkPresenter.onBookmarks(context!!, bookmark_booklist_recyclerview)
    }

    override fun onBookmarkedResult(success: Boolean, message: String) {
        if (!success){
            val errorMsg = "Bookmark message: " + message
            messages_text_view.text = errorMsg
            messages_text_view.visibility = View.VISIBLE
            bookmark_booklist_recyclerview.visibility = View.GONE
        } else{
            messages_text_view.text = ""
            messages_text_view.visibility = View.GONE
            bookmark_booklist_recyclerview.visibility = View.VISIBLE
        }
    }
}
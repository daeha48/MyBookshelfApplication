package com.marksong.mybookshelfapplication.fragmentviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marksong.mybookshelfapplication.R
import com.marksong.mybookshelfapplication.adapters.IBookDetailClick

class BookmarkFragment: Fragment(), IBookDetailClick {
    override fun onBookClicked(isbn13: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.books_bookmark_layout,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBookmarkedBooks()
    }

    fun showBookmarkedBooks(){
        TODO("not implemented")
    }
}
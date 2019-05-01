package com.marksong.mybookshelfapplication.fragmentviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marksong.mybookshelfapplication.R
import com.marksong.mybookshelfapplication.fragmentviews.interfaces.IHistoryBookFragment
import com.marksong.mybookshelfapplication.presenter.HistoryBookPresenter
import com.marksong.mybookshelfapplication.presenter.interfaces.IHistoryBookPresenter
import kotlinx.android.synthetic.main.books_history_layout.*
import kotlinx.android.synthetic.main.books_history_layout.messages_text_view

class HistoryBookFragment: Fragment(), IHistoryBookFragment{

    private lateinit var bookHistoryPresenter: IHistoryBookPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookHistoryPresenter = HistoryBookPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.books_history_layout,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookHistoryPresenter.onBooksHistory(context!!, history_booklist_recyclerview)
    }

    override fun onBookHistoryResult(success: Boolean, message: String) {
        if (!success){
            val errorMsg = "Book history message: " + message
            messages_text_view.text = errorMsg
            messages_text_view.visibility = View.VISIBLE
            history_booklist_recyclerview.visibility = View.GONE
        } else{
            messages_text_view.text = ""
            messages_text_view.visibility = View.GONE
            history_booklist_recyclerview.visibility = View.VISIBLE
        }
    }

}
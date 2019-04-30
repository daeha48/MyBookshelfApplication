package com.marksong.mybookshelfapplication.fragmentviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.marksong.mybookshelfapplication.adapters.IBookDetailClick
import com.marksong.mybookshelfapplication.R
import com.marksong.mybookshelfapplication.fragmentviews.interfaces.INewBooksFragment
import com.marksong.mybookshelfapplication.presenter.interfaces.INewBooksPresenter
import com.marksong.mybookshelfapplication.presenter.NewBooksPresenter
import com.marksong.mybookshelfapplication.presenter.interfaces.IBookDetailsPresenter
import kotlinx.android.synthetic.main.books_new_layout.*

class NewBooksFragment: Fragment(), IBookDetailClick, INewBooksFragment {
    internal lateinit var newBooksFragmentPresenter: INewBooksPresenter
    internal lateinit var bookClicked: IBookDetailsPresenter

    override fun onBookClicked(isbn13: String) {
        bookClicked.getBookDetails(context!!, fragmentManager!!, isbn13)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newBooksFragmentPresenter = NewBooksPresenter(this)
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.books_new_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNewBooks()
    }

    override fun onNewBooksResult(success: Boolean, message: String) {
        if (!success){
            val errorMsg = "NewBook fail message: " + message
            messages_text_view.text = errorMsg
            messages_text_view.visibility = View.VISIBLE
        } else{
            messages_text_view.text = ""
            messages_text_view.visibility = View.GONE
        }
    }

    fun getNewBooks(){
        newBooksFragmentPresenter.onNewBooks(context!!, new_booklist_recyclerview)
    }
}
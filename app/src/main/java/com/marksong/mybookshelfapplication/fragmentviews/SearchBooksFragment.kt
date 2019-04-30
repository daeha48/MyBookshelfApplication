package com.marksong.mybookshelfapplication.fragmentviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.marksong.mybookshelfapplication.R
import com.marksong.mybookshelfapplication.adapters.IBookDetailClick
import com.marksong.mybookshelfapplication.fragmentviews.interfaces.ISearchBooksFragment
import com.marksong.mybookshelfapplication.presenter.SearchBooksPresenter
import com.marksong.mybookshelfapplication.presenter.interfaces.IBookDetailsPresenter
import com.marksong.mybookshelfapplication.presenter.interfaces.ISearchBooksPresenter
import kotlinx.android.synthetic.main.books_search_layout.*
import kotlinx.android.synthetic.main.books_search_layout.messages_text_view



class SearchBooksFragment: Fragment(), IBookDetailClick, ISearchBooksFragment{
    private lateinit var searchBooksFragmentPresenter: ISearchBooksPresenter
    internal lateinit var bookClicked: IBookDetailsPresenter
    private var pageNum: Int = -1
    private lateinit var currentQuery: String

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.books_search_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchBooksFragmentPresenter = SearchBooksPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_booklist_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    searchBooksNextPage(currentQuery, pageNum++)

                }
            }
        })
        search_bar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBooks(query!!)
                pageNum = 1
                currentQuery = query!!
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onBookClicked(isbn13: String) {
        bookClicked.getBookDetails(context!!, fragmentManager!!, isbn13)
    }

    override fun onSearchBookResult(success: Boolean, message: String) {
        if (!success){
            val errorMsg = "SearchBooks fail message: " + message
            messages_text_view.text = errorMsg
            messages_text_view.visibility = View.VISIBLE
            search_booklist_recyclerview.visibility = View.GONE
        } else{
            messages_text_view.text = ""
            messages_text_view.visibility = View.GONE
        }
    }

    fun searchBooks(search: String){
        searchBooksFragmentPresenter.onSearchBooks(context!!, search_booklist_recyclerview, search)
    }

    fun searchBooksNextPage(search: String, pageNum: Int){
        searchBooksFragmentPresenter.onSearchBooksNextPage(context!!, search_booklist_recyclerview, search, pageNum)
    }
}
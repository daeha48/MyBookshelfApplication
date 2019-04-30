package com.marksong.mybookshelfapplication.presenter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marksong.bookshelfapp.model.BookList
import com.marksong.bookshelfapp.network.BookServiceMethod
import com.marksong.mybookshelfapplication.adapters.SwipeRecyclerViewAdapter
import com.marksong.mybookshelfapplication.fragmentviews.interfaces.ISearchBooksFragment
import com.marksong.mybookshelfapplication.network.RetrofitHelper
import com.marksong.mybookshelfapplication.presenter.interfaces.ISearchBooksPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBooksPresenter(internal var iSearchBooksFragment: ISearchBooksFragment): ISearchBooksPresenter{

    private lateinit var classAdapter: SwipeRecyclerViewAdapter

    override fun onSearchBooks(context: Context, search_booklist_recyclerview: RecyclerView, search: String) {
        val searchBooksService = RetrofitHelper.getBookUrlInstance()?.create(BookServiceMethod::class.java)
        val searchBooksCall = searchBooksService?.getSearchBooks(search)
        searchBooksCall?.enqueue(object: Callback<BookList> {
            override fun onResponse(call: Call<BookList>, response: Response<BookList>) {
                if (response.isSuccessful){
                    search_booklist_recyclerview.visibility = View.VISIBLE
                    val booksFromResponse = response?.body()?.books
                    search_booklist_recyclerview.layoutManager = LinearLayoutManager(context)
                    classAdapter = SwipeRecyclerViewAdapter(context!!, booksFromResponse!!)
                    search_booklist_recyclerview.adapter = classAdapter
                    iSearchBooksFragment.onSearchBookResult(true, "success")
                }
                else {
                    iSearchBooksFragment.onSearchBookResult(false, response.code().toString())
                }
            }

            override fun onFailure(call: Call<BookList>, t: Throwable) {
                val errorMessage = t.localizedMessage
                iSearchBooksFragment.onSearchBookResult(false, errorMessage)
                search_booklist_recyclerview.visibility = View.GONE
            }
        })
    }

    override fun onSearchBooksNextPage(context: Context, search_booklist_recyclerview: RecyclerView, search: String, pageNumber: Int) {
        val searchBooksService = RetrofitHelper.getBookUrlInstance()?.create(BookServiceMethod::class.java)
        val searchBooksCall = searchBooksService?.getSearchBooksPage(search, page = pageNumber)
        searchBooksCall?.enqueue(object: Callback<BookList> {
            override fun onResponse(call: Call<BookList>, response: Response<BookList>) {
                if (response.isSuccessful){
                    val booksFromResponse = response?.body()
                    classAdapter.addItems(booksFromResponse!!)
//                    search_booklist_recyclerview.layoutManager = LinearLayoutManager(context)
//                    search_booklist_recyclerview.adapter = SwipeRecyclerViewAdapter(context!!, booksFromResponse!!)
                    iSearchBooksFragment.onSearchBookResult(true, "success")
                }
                else {
                    iSearchBooksFragment.onSearchBookResult(false, response.code().toString())
                }
            }

            override fun onFailure(call: Call<BookList>, t: Throwable) {
                val errorMessage = t.localizedMessage
                iSearchBooksFragment.onSearchBookResult(false, errorMessage)
                search_booklist_recyclerview.visibility = View.GONE
            }
        })
    }
}
package com.marksong.mybookshelfapplication.presenter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marksong.bookshelfapp.model.BookList
import com.marksong.bookshelfapp.network.BookServiceMethod
import com.marksong.mybookshelfapplication.adapters.SwipeRecyclerViewAdapter
import com.marksong.mybookshelfapplication.fragmentviews.interfaces.INewBooksFragment
import com.marksong.mybookshelfapplication.network.RetrofitHelper
import com.marksong.mybookshelfapplication.presenter.interfaces.INewBooksPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewBooksPresenter(internal var iNewBooksFragment: INewBooksFragment): INewBooksPresenter {
    override fun onNewBooks(context: Context, new_booklist_recyclerview: RecyclerView) {
        val newBooksService = RetrofitHelper.getBookUrlInstance()?.create(BookServiceMethod::class.java)
        val newBooksCall = newBooksService?.getNewBooks()
        newBooksCall?.enqueue(object: Callback<BookList> {
            override fun onResponse(call: Call<BookList>, response: Response<BookList>) {
                if (response.isSuccessful){
                    new_booklist_recyclerview.visibility = View.VISIBLE
                    val booksFromResponse = response?.body()?.books
                    new_booklist_recyclerview.layoutManager = LinearLayoutManager(context)
                    new_booklist_recyclerview.adapter = SwipeRecyclerViewAdapter(context!!, booksFromResponse!!)
                    iNewBooksFragment.onNewBooksResult(true, "success")
                }
                else {
                    iNewBooksFragment.onNewBooksResult(false, response.code().toString())
                }
            }
            override fun onFailure(call: Call<BookList>, t: Throwable) {
                val errorMessage = t.localizedMessage
                iNewBooksFragment.onNewBooksResult(false, errorMessage)
                new_booklist_recyclerview.visibility = View.GONE
            }
        })
    }
}
package com.marksong.mybookshelfapplication.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.marksong.bookshelfapp.model.BooksItem
import com.marksong.bookshelfapp.network.BookServiceMethod
import com.marksong.mybookshelfapplication.fragmentviews.BookDetailBottomSheetDialog
import com.marksong.mybookshelfapplication.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object OpenBookDetailUtil {
    fun getBookDetails(context: Context, fragmentManager: FragmentManager, isbn13: String) {
        val getBookDetailsService = RetrofitHelper.getBookUrlInstance()?.create(BookServiceMethod::class.java)
        val getBookDetailsCall = getBookDetailsService?.getBookDetail(isbn13)
        getBookDetailsCall?.enqueue(object: Callback<BooksItem> {
            override fun onResponse(call: Call<BooksItem>, response: Response<BooksItem>) {
                if (response.isSuccessful){
                    Toast.makeText(context, "Open up Book detail", Toast.LENGTH_SHORT).show()
                    val bookDetailBottomSheet = BookDetailBottomSheetDialog.getInstance(response.body()!!)
                    bookDetailBottomSheet?.show(fragmentManager, "newBook_BookDetails")
                }
            }
            override fun onFailure(call: Call<BooksItem>, t: Throwable) {
                Toast.makeText(context, "Failed to get data for book detail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
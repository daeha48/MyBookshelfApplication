package com.marksong.mybookshelfapplication.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.marksong.bookshelfapp.model.BookDetailItem
import com.marksong.bookshelfapp.network.BookServiceMethod
import com.marksong.mybookshelfapplication.MainActivity
import com.marksong.mybookshelfapplication.fragmentviews.BookDetailBottomSheetDialog
import com.marksong.mybookshelfapplication.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object OpenBookDetailUtil {
    fun getBookDetails(context: Context, fragmentManager: FragmentManager, isbn13: String) {
        val getBookDetailsService = RetrofitHelper.getBookUrlInstance()?.create(BookServiceMethod::class.java)
        val getBookDetailsCall = getBookDetailsService?.getBookDetail(isbn13)
        getBookDetailsCall?.enqueue(object: Callback<BookDetailItem> {
            override fun onResponse(call: Call<BookDetailItem>, response: Response<BookDetailItem>) {
                if (response.isSuccessful){
                    Toast.makeText(context, "Open up Book detail", Toast.LENGTH_SHORT).show()
                    val bookItem = (context as MainActivity).getBoxStore()?.boxFor(BookDetailItem::class.java)
                    bookItem.put(response.body()!!)
                    val bookDetailBottomSheet = BookDetailBottomSheetDialog()
                    bookDetailBottomSheet?.show(fragmentManager, "newBook_BookDetails")
                }
            }
            override fun onFailure(call: Call<BookDetailItem>, t: Throwable) {
                Toast.makeText(context, "Failed to get data for book detail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
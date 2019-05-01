package com.marksong.mybookshelfapplication.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.marksong.bookshelfapp.model.BooksItem
import com.marksong.bookshelfapp.model.BooksItem_
import com.marksong.bookshelfapp.network.BookServiceMethod
import com.marksong.mybookshelfapplication.MainActivity
import com.marksong.mybookshelfapplication.R
import com.marksong.mybookshelfapplication.fragmentviews.BookDetailBottomSheetDialog
import com.marksong.mybookshelfapplication.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object BookUtils {
    fun getBookDetails(context: Context, fragmentManager: FragmentManager, isbn13: String) {
        val getBookDetailsService = RetrofitHelper.getBookUrlInstance()?.create(BookServiceMethod::class.java)
        val getBookDetailsCall = getBookDetailsService?.getBookDetail(isbn13)
        getBookDetailsCall?.enqueue(object: Callback<BooksItem> {
            override fun onResponse(call: Call<BooksItem>, response: Response<BooksItem>) {
                if (response.isSuccessful){
                    val bookItem = (context as MainActivity).getBoxStore()?.boxFor(BooksItem::class.java)
                    bookItem.put(response.body()!!)
                    val bookDetailBottomSheet = BookDetailBottomSheetDialog()
                    bookDetailBottomSheet?.show(fragmentManager, "newBook_BookDetails")
                }
            }
            override fun onFailure(call: Call<BooksItem>, t: Throwable) {
                Toast.makeText(context, "Failed to get data for book detail", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun bookMarkBook(context: Context, BookMarkedbook: BooksItem, starImageView: ImageView){
        val bookItem = (context as MainActivity).getBoxStore()?.boxFor(BooksItem::class.java)
        BookMarkedbook.bookmark = !BookMarkedbook.bookmark
        //another pro for objectbox. 'put' can be used for updating and inserting into cache
        bookItem.put(BookMarkedbook)
        if (BookMarkedbook.bookmark){
            starImageView.setImageDrawable(context.getDrawable(R.drawable.star_filled))
        } else{
            starImageView.setImageDrawable(context.getDrawable(R.drawable.star_unfilled))
        }
    }

    fun checkIfBookmarked(context: Context, bookToCheck: BooksItem, starImageView: ImageView): Boolean{
        val bookItem = (context as MainActivity).getBoxStore()?.boxFor(BooksItem::class.java)
        val bookmarkedBookCheck = bookItem.query().equal(BooksItem_.isbn13, bookToCheck.isbn13).equal(BooksItem_.bookmark, true).build()
        val extractBooks = arrayListOf<BooksItem>()
        bookmarkedBookCheck.forEach { extractBooks.add(it) }
        if (extractBooks.size > 0){
            starImageView.setImageDrawable(context.getDrawable(R.drawable.star_filled))
            return true
        }else {
            starImageView.setImageDrawable(context.getDrawable(R.drawable.star_unfilled))
            return false
        }
    }
}
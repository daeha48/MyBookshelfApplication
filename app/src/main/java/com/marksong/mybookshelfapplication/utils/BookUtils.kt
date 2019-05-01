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
import io.objectbox.Box
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
        val extractedBooks = queryBookFromOB(bookItem, BookMarkedbook)
        if (extractedBooks.isNotEmpty()){
            val firstBook = extractedBooks?.first()
            firstBook.bookmark = !firstBook.bookmark
            bookItem.put(firstBook)
            starImageView.setImageDrawable(context.getDrawable(R.drawable.star_unfilled))
        }else {
            BookMarkedbook.bookmark = true
            bookItem.put(BookMarkedbook)
            starImageView.setImageDrawable(context.getDrawable(R.drawable.star_filled))
        }
    }

    fun checkIfBookmarked(context: Context, bookToCheck: BooksItem, starImageView: ImageView){
        val bookItem = (context as MainActivity).getBoxStore()?.boxFor(BooksItem::class.java)
        val extractBooks = queryBookFromOB(bookItem, bookToCheck)
        if (extractBooks.isNotEmpty()){
            starImageView.setImageDrawable(context.getDrawable(R.drawable.star_filled))
        }else {
            starImageView.setImageDrawable(context.getDrawable(R.drawable.star_unfilled))
        }
    }

    private fun queryBookFromOB(bookBox: Box<BooksItem>, book: BooksItem): List<BooksItem>{
        val bookmarkedBookCheck = bookBox.query().equal(BooksItem_.isbn13, book.isbn13).equal(BooksItem_.bookmark, true).build()
        val extractBooks = arrayListOf<BooksItem>()
        bookmarkedBookCheck.forEach { extractBooks.add(it) }
        return extractBooks
    }
}
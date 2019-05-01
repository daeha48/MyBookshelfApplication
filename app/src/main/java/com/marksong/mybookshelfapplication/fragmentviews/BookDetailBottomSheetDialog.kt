package com.marksong.mybookshelfapplication.fragmentviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marksong.bookshelfapp.model.BooksItem
import com.marksong.bookshelfapp.utils.BOOK_DETAIL
import com.marksong.mybookshelfapplication.R
import kotlinx.android.synthetic.main.book_detail_layout.*

class BookDetailBottomSheetDialog: BottomSheetDialogFragment(){

    private lateinit var bookItem: BooksItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookItem = arguments?.getSerializable(BOOK_DETAIL) as BooksItem
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.book_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        book_detail_title.text = bookItem.title
        book_detail_subtitle.text = bookItem.subtitle
        book_detail_authors.text = bookItem.authors
        book_detail_publisher.text = bookItem.publisher
        book_detail_language.text = bookItem.language
        book_detail_isbn10.text = bookItem.isbn10
        book_detail_isbn13.text = bookItem.isbn13
        book_detail_pages.text = bookItem.pages
        book_detail_year.text = bookItem.year
        book_detail_rating.text = bookItem.rating
        book_detail_description.text = bookItem.desc
        book_detail_price.text = bookItem.price
        book_detail_url.text = bookItem.url
        Glide.with(context!!)
            .load(bookItem.image)
            .into(book_detail_img)

    }

    companion object{
        fun getInstance(bookDetail: BooksItem): BottomSheetDialogFragment? {
            val fragment = BottomSheetDialogFragment()
            val bundle = Bundle()
            bundle.putSerializable(BOOK_DETAIL, bookDetail)
            fragment.arguments = bundle
            return fragment
        }
    }

}
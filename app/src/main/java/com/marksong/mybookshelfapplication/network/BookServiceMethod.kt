package com.marksong.bookshelfapp.network

import com.marksong.bookshelfapp.model.BookList
import com.marksong.bookshelfapp.model.BooksItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookServiceMethod{
    @GET("new")
    fun getNewBooks(): Call<BookList>

    @GET("search")
    fun getSearchBooks(
            @Query("searchQuery") search: String
    ): Call<BookList>

    @GET("search")
    fun getSearchBooksPage(
            @Query("searchQuery") search: String,
            @Query("page") page: Int
    ): Call<BookList>

    @GET("books")
    fun getBookDetail(
            @Query("isbn") isbn: String
    ): Call<BooksItem>
}
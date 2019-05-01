package com.marksong.bookshelfapp.network

import com.marksong.bookshelfapp.model.BookList
import com.marksong.bookshelfapp.model.BooksItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BookServiceMethod{
    @GET("new")
    fun getNewBooks(): Call<BookList>

    @GET("search/{searchPath}")
    fun getSearchBooks(
            @Path("searchPath") search: String
    ): Call<BookList>

    @GET("search/{searchPath}/{page}")
    fun getSearchBooksPage(
            @Path("searchPath") search: String,
            @Path("page") page: Int
    ): Call<BookList>

    @GET("books/{isbn}")
    fun getBookDetail(
            @Path("isbn") isbn: String
    ): Call<BooksItem>
}
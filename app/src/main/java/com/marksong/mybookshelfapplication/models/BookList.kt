package com.marksong.bookshelfapp.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class BookList(

	@field:SerializedName("total")
	val total: String? = null,

	@field:SerializedName("books")
	val books: ArrayList<BooksItem>? = null,

	@field:SerializedName("page")
	val page: String? = null,

	@field:SerializedName("error")
	val error: String? = null
)
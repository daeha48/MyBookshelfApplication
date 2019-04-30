package com.marksong.bookshelfapp.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Generated("com.robohorse.robopojogenerator")
data class BooksItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("year")
	val year: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("pages")
	val pages: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("subtitle")
	val subtitle: String? = null,

	@field:SerializedName("isbn13")
	val isbn13: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("isbn10")
	val isbn10: String? = null,

	@field:SerializedName("authors")
	val authors: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
): Serializable
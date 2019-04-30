package com.marksong.mybookshelfapplication.presenter.interfaces

import android.content.Context
import androidx.fragment.app.FragmentManager

interface IBookDetailsPresenter {
    fun getBookDetails(context: Context, fragmentManager: FragmentManager, isbn13: String)
}
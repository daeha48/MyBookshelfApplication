package com.marksong.mybookshelfapplication

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.marksong.mybookshelfapplication.fragmentviews.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_new -> {
                val newFrag = NewBooksFragment()
                supportFragmentManager.beginTransaction().replace(R.id.visible_frame,newFrag, newFrag.javaClass.canonicalName).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                val searchFrag = SearchBooksFragment()
                supportFragmentManager.beginTransaction().replace(R.id.visible_frame,searchFrag, searchFrag.javaClass.canonicalName).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bookmark -> {
                val bookmarkFrag = BookmarkFragment()
                supportFragmentManager.beginTransaction().replace(R.id.visible_frame,bookmarkFrag, bookmarkFrag.javaClass.canonicalName).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_history -> {
                val historyFrag = HistoryBookFragment()
                supportFragmentManager.beginTransaction().replace(R.id.visible_frame,historyFrag, historyFrag.javaClass.canonicalName).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null){
            val fragment = NewBooksFragment()
            supportFragmentManager.beginTransaction().replace(R.id.visible_frame, fragment, fragment.javaClass.canonicalName).addToBackStack(null).commit()
        }
    }
}

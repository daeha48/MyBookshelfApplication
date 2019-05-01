package com.marksong.mybookshelfapplication.adapters

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.marksong.bookshelfapp.model.BookList
import com.marksong.bookshelfapp.model.BooksItem
import com.marksong.mybookshelfapplication.R
import com.marksong.mybookshelfapplication.utils.OpenBookDetailUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class SwipeRecyclerViewAdapter(val context: Context, val bookList: ArrayList<BooksItem>): RecyclerSwipeAdapter<SwipeRecyclerViewAdapter.BookViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item_cardview, parent, false)
        return BookViewHolder(view)
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe_bookItem
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(bookViewHolder: BookViewHolder, pos: Int) {
        val bookItem = bookList.get(pos)

        bookViewHolder.bookTitle.text = bookItem.title
        bookViewHolder.bookSubtitle.text = bookItem.subtitle
        bookViewHolder.bookIsbn13.text = bookItem.isbn13
        bookViewHolder.bookUrl.text = bookItem.url
        bookViewHolder.bookPrice.text = bookItem.price
        Glide.with(context)
            .load(bookItem.image)
            .into(bookViewHolder.bookImage)

        bookViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut)
        bookViewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, bookViewHolder.swipeLayout.findViewById(R.id.bottom_swipe_wrapper))
        bookViewHolder.swipeLayout

        bookViewHolder.swipeLayout.addSwipeListener(object : SwipeLayout.SwipeListener {
            override fun onClose(layout: SwipeLayout) {
                //when the SurfaceView totally cover the BottomView.
            }

            override fun onUpdate(layout: SwipeLayout, leftOffset: Int, topOffset: Int) {
                Toast.makeText(context, "Use this swipeable item to delete entries",Toast.LENGTH_SHORT).show()
            }

            override fun onStartOpen(layout: SwipeLayout) {

            }

            override fun onOpen(layout: SwipeLayout) {
                //when the BottomView totally show.
            }

            override fun onStartClose(layout: SwipeLayout) {

            }

            override fun onHandRelease(layout: SwipeLayout, xvel: Float, yvel: Float) {
                //when user's hand released.
            }
        })

        bookViewHolder.surfaceView.setOnClickListener { view ->
            val manager = (context as AppCompatActivity).supportFragmentManager
            OpenBookDetailUtil.getBookDetails(context, manager, bookViewHolder.bookIsbn13.text.toString())
        }

        bookViewHolder.deleteBtn.setOnClickListener { view ->
            bookList.removeAt(pos)
            notifyItemRemoved(pos)
            Toast.makeText(context, "Deleted Entry", Toast.LENGTH_SHORT).show()
        }
    }

    fun addItems(books: BookList){
        books.books!!.forEach { bookList.add(it) }
        notifyDataSetChanged()
    }


    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var swipeLayout: SwipeLayout = itemView.findViewById(R.id.swipe_bookItem)
        var surfaceView: ConstraintLayout = itemView.findViewById(R.id.surface_view)
        var bookTitle: TextView = itemView.findViewById(R.id.book_title)
        var bookSubtitle: TextView = itemView.findViewById(R.id.subtitle)
        var bookIsbn13: TextView = itemView.findViewById(R.id.isbn13)
        var bookPrice: TextView = itemView.findViewById(R.id.price)
        var bookUrl: TextView= itemView.findViewById(R.id.url)
        var bookImage: ImageView = itemView.findViewById(R.id.book_image)
        var deleteBtn: ImageView = itemView.findViewById(R.id.delete_swipe_button)
    }

}
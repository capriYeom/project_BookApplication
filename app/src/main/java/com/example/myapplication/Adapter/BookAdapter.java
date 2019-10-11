package com.example.myapplication.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.Book;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context context;
    private List<Book> mBookList;
    private OnBookClickListener mClickListener;
    private OnBookLongClickListener mLongClickListener;

    public BookAdapter(Context context) {
        this.context = context;
        mBookList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        holder.bind(context, book, mClickListener, mLongClickListener);
    }

    @Override
    public int getItemCount() {
        Log.d("ADAPTER", "getItemCount: " + mBookList.size());
        return mBookList.size();
    }

    public void setBookList(List<Book> bookList) {
        mBookList = bookList;
        notifyDataSetChanged();
    }

    public void deleteElement(Book book) {
        for (Book targetBook : mBookList) {
            if (targetBook.getTitle().equals(book.getTitle())) {
                mBookList.remove(targetBook);
                notifyDataSetChanged();
            }
        }
    }

    public void setBookClickListener(OnBookClickListener listener) {
        mClickListener = listener;
    }

    public void setBookLongClickListener(OnBookLongClickListener listener) {
        mLongClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView, subtitleView, priceView, isbnView;
        ImageView bookProfileImageView;

        ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.listtext_title);
            subtitleView = (TextView) itemView.findViewById(R.id.listtext_subtitle);
            priceView = (TextView) itemView.findViewById(R.id.listtext_price);
            isbnView = (TextView) itemView.findViewById(R.id.listtext_isbn);
            bookProfileImageView = (ImageView) itemView.findViewById(R.id.listimage_book);
        }

        void bind(Context context, final Book book, final OnBookClickListener bookClickListener, final OnBookLongClickListener bookLongClickListener) {
            titleView.setText(book.getTitle());
            subtitleView.setText(book.getSubtitle());
            priceView.setText(book.getPrice());
            isbnView.setText(String.valueOf(book.getIsbn13()));
            Glide.with(context).load(book.getImage()).into(bookProfileImageView);

            if (bookClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bookClickListener.onItemClick(book);
                    }
                });
            }

            if (bookLongClickListener != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        bookLongClickListener.onItemLongClick(book);
                        return true;
                    }
                });
            }
        }
    }

    public interface OnBookClickListener {
        void onItemClick(Book book);
    }

    public interface OnBookLongClickListener {
        void onItemLongClick(Book book);
    }
}

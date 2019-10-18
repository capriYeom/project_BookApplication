package com.example.myapplication.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.BookDetailActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Adapter.BookAdapter;
import com.example.myapplication.DataManager.BookBookmarkList;
import com.example.myapplication.Model.Book;
import com.example.myapplication.R;

import java.util.List;

public class BookmarkFragment extends Fragment {

    RecyclerView mRecyclerView;
    BookAdapter mAdapter;
    List<Book> mBookList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.process_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentEnvironment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBookList = BookBookmarkList.getInstance().getBookList();
        mAdapter.setBookList(mBookList);
    }

    private void setFragmentEnvironment() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new BookAdapter(getContext());
        setUpBookAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setUpBookAdapter() {
        mAdapter.setBookClickListener(new BookAdapter.OnBookClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);
                intent.putExtra(MainActivity.EXTRA_BOOK_ISBN_NUM, String.valueOf(book.getIsbn13()));
                startActivity(intent);
            }
        });
        mAdapter.setBookLongClickListener(new BookAdapter.OnBookLongClickListener() {
            @Override
            public void onItemLongClick(final Book book) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                        .setTitle("Unbookmark?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BookBookmarkList.getInstance().removeBookFromList(book);
                                mAdapter.deleteElement(book);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }
}

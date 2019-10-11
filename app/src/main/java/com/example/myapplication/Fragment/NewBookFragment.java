package com.example.myapplication.Fragment;

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
import com.example.myapplication.Model.Book;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.RetrofitConnector;
import com.example.myapplication.Retrofit.RetrofitException;

import java.util.List;

public class NewBookFragment extends Fragment {

    RecyclerView mRecyclerView;
    BookAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newbook, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.process_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentEnvironment();
        callNewBooks();
    }

    private void setFragmentEnvironment() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new BookAdapter(getContext());
        mAdapter.setBookClickListener(new BookAdapter.OnBookClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);
                intent.putExtra(MainActivity.EXTRA_BOOK_ISBN_NUM, String.valueOf(book.getIsbn13()));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void callNewBooks() {
        RetrofitConnector.getInstance().setBookListListener(new RetrofitConnector.BookListListener() {
            @Override
            public void onResult(List<Book> bookList, RetrofitException e) {
                if (e != null) {
                    e.printStackTrace();
                }
                mAdapter.setBookList(bookList);
            }
        });

        RetrofitConnector.getInstance().callNewList();
    }
}

package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.BookDetailActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Adapter.BookAdapter;
import com.example.myapplication.Model.Book;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.RetrofitConnector;

import java.util.List;

public class SearchBookFragment extends Fragment {

    RecyclerView mRecyclerView;
    BookAdapter mAdapter;
    EditText mEditText;
    Button mButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.process_list);
        mEditText = (EditText) view.findViewById(R.id.edittext_search);
        mButton = (Button) view.findViewById(R.id.button_search);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = mEditText.getText().toString();
                if (keyword.length() > 0) {
                    callSearchBooks(keyword);
                } else {
                    Toast.makeText(getContext(), "Keyword must be available." , Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentEnvironment();
    }

    private void setFragmentEnvironment() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new BookAdapter(getContext());
        mAdapter.setBookListener(new BookAdapter.onBookClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);
                intent.putExtra(MainActivity.EXTRA_BOOK_ISBN_NUM, String.valueOf(book.getIsbn13()));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void callSearchBooks(String keyword) {
        RetrofitConnector.getInstance().setBookListListener(new RetrofitConnector.BookListListener() {
            @Override
            public void onResult(List<Book> bookList) {
                mAdapter.setBookList(bookList);
            }
        });
        RetrofitConnector.getInstance().callSearchList(keyword);
    }
}

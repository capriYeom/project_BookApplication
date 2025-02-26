package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myapplication.Retrofit.RetrofitException;
import com.example.myapplication.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchBookFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BookAdapter mAdapter;
    private EditText mEditText;
    private List<Book> mInitialBookList = new ArrayList<>();

    private String mCurrentKeyword;
    private boolean isLoading = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = view.findViewById(R.id.process_list);
        mEditText = view.findViewById(R.id.edittext_search);

        view.findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = mEditText.getText().toString();
                if (keyword.length() > 0) {
                    mCurrentKeyword = keyword;
                    PreferenceUtils.setHistory(keyword);
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

    @Override
    public void onResume() {
        super.onResume();
        if (PreferenceUtils.getHistory() != null && PreferenceUtils.getHistory().length() > 0) {
            mEditText.setText(PreferenceUtils.getHistory());
        }
        if (!mInitialBookList.isEmpty()) {
            mAdapter.setBookList(mInitialBookList);
        }
    }

    private void setFragmentEnvironment() {
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
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
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (manager.findLastVisibleItemPosition() == mAdapter.getItemCount() -1) {
                    if (!isLoading) {
                        loadMore(mAdapter.getItemCount());
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore(int count) {
        RetrofitConnector.getInstance().setBookListListener(new RetrofitConnector.BookListListener() {
            @Override
            public void onResult(List<Book> bookList, RetrofitException e) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }

                for (Book book : bookList) {
                    mAdapter.addLast(book);
                }
                mInitialBookList.addAll(bookList);
                isLoading = false;
            }
        });
        int pageCount = count / 10;
        RetrofitConnector.getInstance().callSearchListWithPage(mCurrentKeyword, pageCount + 1);
    }

    private void callSearchBooks(String keyword) {
        RetrofitConnector.getInstance().setBookListListener(new RetrofitConnector.BookListListener() {
            @Override
            public void onResult(List<Book> bookList, RetrofitException e) {
                if (e != null) {
                    e.printStackTrace();
                }
                if (bookList.size() <= 0) {
                    Toast.makeText(getContext(), "No Result.", Toast.LENGTH_SHORT).show();
                }
                mInitialBookList = bookList;
                mAdapter.setBookList(bookList);
            }
        });
        RetrofitConnector.getInstance().callSearchList(keyword);
    }
}

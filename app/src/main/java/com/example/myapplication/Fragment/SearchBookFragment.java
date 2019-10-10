package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.BookAdapter;
import com.example.myapplication.Model.Book;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.RetrofitConnector;

import java.util.List;

public class SearchBookFragment extends Fragment {

    RecyclerView mRecyclerView;
    BookAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.process_list);
//        setFragmentEnvironment();
//        callNewBooks();
        return view;
    }

    private void setFragmentEnvironment() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new BookAdapter(getContext());
        mAdapter.setBookListener(new BookAdapter.onBookClickListener() {
            @Override
            public void onItemClick(Book book) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void callNewBooks() {

    }
}

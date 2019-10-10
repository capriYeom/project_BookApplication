package com.example.myapplication.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.Book;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.RetrofitConnector;

import java.util.List;

public class BookDetailActivity extends AppCompatActivity {
    private TextView mTitleText, mSubtitleText, mPriceText, mRatingText, mAuthorsText, mPublisherText, mPublishedText, mPageText, mLanguageText, mIsbn10Text, mIsbn13Text, mDescriptionText;
    private ImageView mProfileImageView;

    private Button mBookmarkButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();

        RetrofitConnector.getInstance().setBookListener(new RetrofitConnector.BookListener() {
            @Override
            public void onResult(Book book) {
                setBookDetail(book);
            }
        });

        mBookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2019-10-10 add Bookmark
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        String isbnNum = getIntent().getStringExtra(MainActivity.EXTRA_BOOK_ISBN_NUM);
        RetrofitConnector.getInstance().callDetailBook(isbnNum);
    }

    private void initView() {
        mTitleText = (TextView) findViewById(R.id.textview_title);
        mSubtitleText = (TextView) findViewById(R.id.textview_subtitle);
        mPriceText = (TextView) findViewById(R.id.textview_price);
        mRatingText = (TextView) findViewById(R.id.textview_rating);
        mAuthorsText = (TextView) findViewById(R.id.textview_author);
        mPublisherText = (TextView) findViewById(R.id.textview_publisher);
        mPublishedText = (TextView) findViewById(R.id.textview_published);
        mPageText = (TextView) findViewById(R.id.textview_page);
        mLanguageText = (TextView) findViewById(R.id.textview_language);
        mIsbn10Text = (TextView) findViewById(R.id.textview_isbn10);
        mIsbn13Text = (TextView) findViewById(R.id.textview_isbn13);
        mDescriptionText = (TextView) findViewById(R.id.textview_description);

        mProfileImageView = (ImageView) findViewById(R.id.imageview_bookprofile);

        mBookmarkButton = (Button) findViewById(R.id.button_bookmark);
    }

    private void setBookDetail(Book book) {
        mTitleText.setText(String.format(getString(R.string.book_title), book.getTitle()));
        mSubtitleText.setText(String.format(getString(R.string.book_subtitle), book.getSubtitle()));
        mPriceText.setText(String.format(getString(R.string.book_price), book.getPrice()));
        mRatingText.setText(String.format(getString(R.string.book_rating), book.getRating()));
        mAuthorsText.setText(String.format(getString(R.string.book_authors), book.getAuthors()));
        mPublisherText.setText(String.format(getString(R.string.book_publisher), book.getPublisher()));
        mPublishedText.setText(String.format(getString(R.string.book_year), book.getYear()));
        mPageText.setText(String.format(getString(R.string.book_page), book.getPages()));
        mLanguageText.setText(String.format(getString(R.string.book_language), book.getLanguage()));
        mIsbn10Text.setText(String.format(getString(R.string.book_isbn10), book.getIsbn10()));
        mIsbn13Text.setText(String.format(getString(R.string.book_isbn13), book.getIsbn13()));
        mDescriptionText.setText(String.format(getString(R.string.book_description), book.getDesc()));

        Glide.with(this).load(book.getImage()).into(mProfileImageView);
    }
}

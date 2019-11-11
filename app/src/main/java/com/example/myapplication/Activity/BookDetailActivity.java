package com.example.myapplication.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.DataManager.BookBookmarkList;
import com.example.myapplication.RoomDatabase.DatabaseWrapper;
import com.example.myapplication.DataManager.BookHistoryList;
import com.example.myapplication.RoomDatabase.Memo;
import com.example.myapplication.Model.Book;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.RetrofitConnector;
import com.example.myapplication.Retrofit.RetrofitException;

import java.util.List;

public class BookDetailActivity extends AppCompatActivity {
    private Book currentBook;
    private Memo currentMemo;

    private TextView mTitleText, mSubtitleText, mPriceText, mRatingText, mAuthorsText, mPublisherText, mPublishedText, mPageText, mLanguageText, mIsbn10Text, mIsbn13Text, mDescriptionText, mUrlText;
    private ImageView mProfileImageView;
    private Button mBookmarkButton , mMemoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();

        RetrofitConnector.getInstance().setBookListener(new RetrofitConnector.BookListener() {
            @Override
            public void onResult(Book book, RetrofitException e) {
                if (e != null) {
                    e.printStackTrace();
                }
                currentBook = book;
                mBookmarkButton.setEnabled(true);
                mMemoButton.setEnabled(true);
                setBookDetail(book);
                BookHistoryList.getInstance().addBookToList(currentBook);
                if (isBookmarked()) {
                    mBookmarkButton.setText(getString(R.string.book_unbookmark));
                } else {
                    mBookmarkButton.setText(R.string.book_bookmark);
                }
                getMemo();
            }
        });

        mBookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBookmarked()) {
                    BookBookmarkList.getInstance().removeBookFromList(currentBook);
                    Toast.makeText(BookDetailActivity.this, "This book's bookmark has deleted", Toast.LENGTH_SHORT).show();
                    mBookmarkButton.setText(R.string.book_bookmark);
                } else {
                    BookBookmarkList.getInstance().addBookToList(currentBook);
                    Toast.makeText(BookDetailActivity.this, "This book has bookmarked", Toast.LENGTH_SHORT).show();
                    mBookmarkButton.setText(R.string.book_unbookmark);
                }
            }
        });
        mMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog();
            }
        });

        mBookmarkButton.setEnabled(false);
        mMemoButton.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String isbnNum = getIntent().getStringExtra(MainActivity.EXTRA_BOOK_ISBN_NUM);
        RetrofitConnector.getInstance().callDetailBook(isbnNum);
    }

    private void initView() {
        mTitleText = findViewById(R.id.textview_title);
        mSubtitleText = findViewById(R.id.textview_subtitle);
        mPriceText = findViewById(R.id.textview_price);
        mRatingText = findViewById(R.id.textview_rating);
        mAuthorsText = findViewById(R.id.textview_author);
        mPublisherText = findViewById(R.id.textview_publisher);
        mPublishedText = findViewById(R.id.textview_published);
        mPageText = findViewById(R.id.textview_page);
        mLanguageText = findViewById(R.id.textview_language);
        mIsbn10Text = findViewById(R.id.textview_isbn10);
        mIsbn13Text = findViewById(R.id.textview_isbn13);
        mDescriptionText = findViewById(R.id.textview_description);
        mUrlText = findViewById(R.id.textView_url);

        mProfileImageView = findViewById(R.id.imageview_bookprofile);

        mBookmarkButton = findViewById(R.id.button_bookmark);
        mMemoButton = findViewById(R.id.button_memo);
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
        mUrlText.setText(String.format(getString(R.string.book_url),book.getUrl()));

        Glide.with(this).load(book.getImage()).into(mProfileImageView);
    }

    private boolean isBookmarked() {
        List<Book> bookList = BookBookmarkList.getInstance().getBookList();
        for (Book book : bookList) {
            if (book.getIsbn13().toString().equals(currentBook.getIsbn13().toString())) {
                return true;
            }
        }
        return false;
    }

    private void showDataDialog() {
        final EditText memoText = new EditText(BookDetailActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailActivity.this)
                .setTitle("MEMO")
                .setView(memoText)
                .setPositiveButton("SET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (memoText.getText().toString().length() > 0) {
                            Memo memo = new Memo(currentBook.getIsbn13(), memoText.getText().toString());
                            if (isMemo()) {
                                DatabaseWrapper.getInstance(BookDetailActivity.this).updateBookMemo(currentBook.getIsbn13(), memoText.getText().toString());
                            } else {
                                DatabaseWrapper.getInstance(BookDetailActivity.this).addBookMemo(new Memo(currentBook.getIsbn13(), memoText.getText().toString()));
                            }
                            currentMemo = memo;
                            Toast.makeText(BookDetailActivity.this, "Memo Saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BookDetailActivity.this, "the text must be more than 0", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        if (isMemo()) {
            memoText.setText(currentMemo.getMemo());
            builder.setNeutralButton("CLEAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseWrapper.getInstance(BookDetailActivity.this).deleteBookmemo(currentMemo);
                    currentMemo = null;
                }
            });
        }

        builder.create().show();
    }

    private void getMemo() {
        DatabaseWrapper.getInstance(BookDetailActivity.this).getBookMemo(currentBook.getIsbn13(), new DatabaseWrapper.GetMemoHandler() {
            @Override
            public void onResult(Memo memo) {
                currentMemo = memo;
                mMemoButton.setEnabled(true);
            }
        });
    }

    private boolean isMemo() {
        return currentMemo != null && currentMemo.getMemo().length() > 0;
    }
}

package at.wifi.swdev.task2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.wifi.swdev.task2.R;
import at.wifi.swdev.task2.db.Book;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private Context context;
    private List<Book> books;
    private LayoutInflater inflater;
    private PublishSubject<Book> onClickSubject = PublishSubject.create();

    public BookListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public Book getBookAt(int position) { return books.get(position); }

    public Observable<Book> getClickedBook() {
        return onClickSubject;
    }

    public Context getContext() { return context; }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.book_list_item, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        if(books != null) {
            Book book = books.get(position);
            holder.titleTv.setText(book.title);
            holder.authorTv.setText(book.author);

            holder.itemView.setOnClickListener(v -> {
                onClickSubject.onNext(book);
            });
        }
    }

    @Override
    public int getItemCount() {
        if(books != null) {
            return books.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTv;
        private final TextView authorTv;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.bookTitle);
            authorTv = itemView.findViewById(R.id.bookAuthor);
        }
    }

}


package hu.ait.android.maggie.readingtracker.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hu.ait.android.maggie.readingtracker.R;

/**
 * Created by Magisus on 5/1/2015.
 */
public class BookAdapter extends BaseAdapter {

    private List<Book> books;
    private Context context;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Book getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView titleText;
        TextView authorText;
        ImageView coverImage;
        TextView dateText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.book_row, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.titleText = (TextView) v.findViewById(R.id.titleText);
            holder.authorText = (TextView) v.findViewById(R.id.authorText);
            holder.coverImage = (ImageView) v.findViewById(R.id.coverImage);
            holder.dateText = (TextView) v.findViewById(R.id.dateText);
            v.setTag(holder);
        }

        Book book = books.get(position);
        if (v != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.titleText.setText(book.getTitle());
            holder.authorText.setText(book.getAuthor());
            if (book.getCoverUrl() != null) {
                Glide.with(context).load(book.getCoverUrl()).into(holder.coverImage);
            }
            if (Book.Status.FINISHED.equals(book.getStatus())) {
                holder.dateText.setText(context.getResources().getString(R.string
                        .date_finished_label) + book.getDateFinishedString());
            } else if (Book.Status.IN_PROGRESS.equals(book.getStatus())) {
                holder.dateText.setText((context.getResources().getString(R.string
                        .date_started_label) + book.getDateStartedString()));
            }
        }

        return v;
    }
}

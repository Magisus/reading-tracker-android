package hu.ait.android.maggie.readingtracker.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import hu.ait.android.maggie.readingtracker.R;

/**
 * Created by Magisus on 5/1/2015.
 */
public class CategoriesExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private String[] headers;
    private HashMap<String, List<Book>> groups;

    public CategoriesExpandableAdapter(Context context, String[] headers, HashMap<String,
            List<Book>> groups) {
        this.context = context;
        this.headers = headers;
        this.groups = groups;
    }

    public void refreshData(HashMap<String, List<Book>> groups){
        this.groups = groups;
    }

    @Override
    public int getGroupCount() {
        return headers.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(headers[groupPosition]).size();
    }

    @Override
    public List<Book> getGroup(int groupPosition) {
        return groups.get(headers[groupPosition]);
    }

    @Override
    public Book getChild(int groupPosition, int childPosition) {
        return groups.get(headers[groupPosition]).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup
            parent) {
        String headerTitle = headers[groupPosition];
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_list_group, null);
        }

        TextView header = (TextView) convertView
                .findViewById(R.id.categoryHeader);
        header.setText(headerTitle);

        return convertView;
    }

    public void addBook(Book book) {
        getGroup(book.getStatus().getIndex()).add(book);
    }

    public void removeChild(int groupPos, int childPos) {
        groups.get(headers[groupPos]).remove(childPos);
    }

    private class ViewHolder {
        TextView titleText;
        TextView authorText;
        ImageView coverImage;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View
            convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.book_row_child, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.titleText = (TextView) v.findViewById(R.id.titleText);
            holder.authorText = (TextView) v.findViewById(R.id.authorText);
            holder.coverImage = (ImageView) v.findViewById(R.id.coverImage);
            v.setTag(holder);
        }

        Book book = getChild(groupPosition, childPosition);
        if (v != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.titleText.setText(book.getTitle());
            holder.authorText.setText(book.getAuthor());

            if(book.getCoverUrl() != null) {
                Glide.with(context).load(book.getCoverUrl()).into(holder.coverImage);
            } else {
                holder.coverImage.setImageResource(R.drawable.book_default);
            }
        }

        return v;
    }
}

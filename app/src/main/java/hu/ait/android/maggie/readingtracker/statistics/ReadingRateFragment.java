package hu.ait.android.maggie.readingtracker.statistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hu.ait.android.maggie.readingtracker.R;
import hu.ait.android.maggie.readingtracker.books.Book;

/**
 * Created by Magisus on 5/16/2015.
 */
public class ReadingRateFragment extends Fragment {

    @InjectView(R.id.chart)
    LineChart readingRateChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reading_rate_fragment, container, false);
        ButterKnife.inject(this, rootView);

        setUpReadingRateChart();

        return rootView;
    }

    private void setUpReadingRateChart() {
        List<Book> finishedBooks = Book.find(Book.class, "status = ?", "FINISHED");
        Collections.sort(finishedBooks, new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {
                //Sort the books by finished date from most recent to least recent
                return lhs.getDateFinished() > rhs.getDateFinished() ? -1 : lhs.getDateFinished()
                        < rhs.getDateFinished() ? 1 : 0;
            }
        });

        ArrayList<String> months = getXAxisLabels();
        LineDataSet booksPerMonth = getBooksPerMonth(finishedBooks);
        booksPerMonth.setColor(getResources().getColor(R.color.dark_blue));
        booksPerMonth.setCircleColor(getResources().getColor(R.color.dark_blue));

        LineData chartData = new LineData(months, booksPerMonth);
        chartData.setValueFormatter(new IntegerValueFormatter());
        readingRateChart.setData(chartData);
        readingRateChart.invalidate();
    }

    private LineDataSet getBooksPerMonth(List<Book> finishedBooks) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        int currentMonth = now.get(Calendar.MONTH);
        int currentYear = now.get(Calendar.YEAR);
        Integer[] countsPerMonth = new Integer[12];
        Arrays.fill(countsPerMonth, 0);
        for (Book book : finishedBooks) {
            Calendar finishedDate = Calendar.getInstance();
            finishedDate.setTimeInMillis(book.getDateFinished());
            int monthsAgo = (currentMonth + 12) - (finishedDate.get(Calendar.MONTH) + 12);
            if(monthsAgo > countsPerMonth.length - 1){
                break;
            }
            countsPerMonth[monthsAgo]++;
        }
        ArrayList<Entry> entries = new ArrayList<>();
        for(int i = 0; i < countsPerMonth.length; i++){
            entries.add(new Entry(countsPerMonth[countsPerMonth.length - 1 - i], i));
        }
        return new LineDataSet(entries, "Finished book counts");
    }

    private ArrayList<String> getXAxisLabels() {
        ArrayList<String> months = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        int month = now.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR);
        for (int i = 0; i < 12; i++) {
            months.add(new DateFormatSymbols().getMonths()[month] + " " + year);
            month--;
            if (month == -1) {
                month = 11;
                year--;
            }
        }
        Collections.reverse(months);
        return months;
    }
}

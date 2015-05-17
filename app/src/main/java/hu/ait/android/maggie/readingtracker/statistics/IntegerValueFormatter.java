package hu.ait.android.maggie.readingtracker.statistics;

import com.github.mikephil.charting.utils.ValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by Magisus on 5/17/2015.
 */
public class IntegerValueFormatter implements ValueFormatter {

    private DecimalFormat formatter;

    public IntegerValueFormatter(){
        formatter = new DecimalFormat("###,###,##0");
    }

    @Override
    public String getFormattedValue(float value) {
        return formatter.format(value);
    }
}

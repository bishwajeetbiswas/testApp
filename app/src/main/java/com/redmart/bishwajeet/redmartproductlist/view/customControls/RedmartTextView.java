package com.redmart.bishwajeet.redmartproductlist.view.customControls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.redmart.bishwajeet.redmartproductlist.R;
import com.redmart.bishwajeet.redmartproductlist.RedmartApplication;

public class RedmartTextView extends AppCompatTextView {


    public static final int FONT_REDMART_REGULAR = 0x00;
    public static final int FONT_REDMART_REGULAR_MEDIUM = 0x01;
    public static final int FONT_REDMART_REGULAR_BOLD = 0x02;


    RedmartApplication mApp;

    public RedmartTextView(Context context) {
        super(context);
        init(null);
    }

    public RedmartTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RedmartTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setFont(int fontName) {
        switch (fontName) {
            case FONT_REDMART_REGULAR:
                setTypeface(mApp.getRedmartFontRegular());
                break;
            case FONT_REDMART_REGULAR_BOLD:
                setTypeface(mApp.getRedmartFontBold());
                break;
            case FONT_REDMART_REGULAR_MEDIUM:
                setTypeface(mApp.getRedmartFontMedium());
                break;
            default:
                setTypeface(mApp.getRedmartFontRegular());
        }
    }

    private void init(AttributeSet attrs) {
        mApp = (RedmartApplication) getContext().getApplicationContext();
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RedmartTextView);
            int fontName = a.getInteger(R.styleable.RedmartTextView_fontName, FONT_REDMART_REGULAR);
            setFont(fontName);
            a.recycle();
        } else
            setTypeface(mApp.getRedmartFontRegular());
    }
}

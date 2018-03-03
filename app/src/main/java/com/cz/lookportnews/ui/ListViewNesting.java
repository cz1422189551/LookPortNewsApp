package com.cz.lookportnews.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 14221 on 2018/2/22.
 */

public class ListViewNesting extends ListView {
    public ListViewNesting(Context context) {
        super(context);
    }

    public ListViewNesting(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewNesting(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSepc = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,expandSepc);
    }
}

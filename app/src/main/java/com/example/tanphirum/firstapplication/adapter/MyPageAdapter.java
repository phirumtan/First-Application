package com.example.tanphirum.firstapplication.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanphirum.firstapplication.R;
import com.example.tanphirum.firstapplication.fragment.PagerFragment;

public class MyPageAdapter extends FragmentPagerAdapter{

    private Context mContext;

    public MyPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance("Hello Pager " + position);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        Drawable image = mContext.getResources().getDrawable(R.mipmap.ic_launcher);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString("Title " + position);
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    private String tabTitles[] = new String[] { "Tab1", "Tab2" };
    private int[] imageResId = { R.drawable.ic_no_1, R.drawable.ic_no_1 };

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.txt_labe);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) v.findViewById(R.id.img_1);
        img.setImageResource(imageResId[position]);
        return v;
    }
}

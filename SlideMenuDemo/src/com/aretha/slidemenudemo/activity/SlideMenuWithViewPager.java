package com.aretha.slidemenudemo.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aretha.slidemenu.ScrollDetector;
import com.aretha.slidemenudemo.BaseSlideMenuActivity;
import com.aretha.slidemenudemo.R;
import com.aretha.slidemenudemo.widget.DragableFragmentPagerAdapter;

public class SlideMenuWithViewPager extends BaseSlideMenuActivity implements
		ScrollDetector {
	private ViewPager mViewPager;

	@Override
	public void onContentChanged() {
		super.onContentChanged();
		setSlideRole(R.layout.layout_slidemenu_with_view_pager);
		setSlideRole(R.layout.layout_primary_menu);
		setSlideRole(R.layout.layout_secondary_menu);

		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mViewPager.setAdapter(new DragableFragmentPagerAdapter(this,
				getSupportFragmentManager()));
		getSlideMenu().setScrollDetector(this);
	}

	@Override
	public boolean isScrollable(View v, float dx, float x, float y) {
		if (v == mViewPager) {
			ViewPager viewPager = mViewPager;
			PagerAdapter pagerAdapter = mViewPager.getAdapter();
			if (null == pagerAdapter) {
				return false;
			}

			final int currentItem = viewPager.getCurrentItem();
			return (dx < 0 && currentItem < pagerAdapter.getCount() - 1)
					|| (dx > 0 && currentItem > 0);
		}

		return false;
	}
}

package de.lette;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

public class TabFragment extends Fragment {

	private int woche;

	public TabFragment(int woche) {
		this.woche = woche;
	}

	public static TabFragment newInstance(int woche) {
		return new TabFragment(woche);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.pager_tabs, container, false);

		// Get the ViewPager and set it's PagerAdapter so that it can display
		// items
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
		viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), getActivity(), woche));

		// Give the SlidingTabLayout the ViewPager
		SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
		// Center the tabs in the layout
		slidingTabLayout.setDistributeEvenly(true);
		// Customize tab color
		slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return getResources().getColor(R.color.myPrimaryColor);
			}
		});

		slidingTabLayout.setViewPager(viewPager);
		slidingTabLayout.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}

			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});

		// Setzt den Tab auf den Aktuellen Tag.
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		if(today.weekDay >= 1 && today.weekDay <= 5) {
			viewPager.setCurrentItem(today.weekDay - 1);
		} else {
			viewPager.setCurrentItem(0);
		}
		viewPager.refreshDrawableState();
		return view;
	}
}
package de.lette.mensaplan.app;

import de.lette.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

	private Toolbar mToolbar;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private static final String FIRST_LAUNCH = "first_launch";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

		if (prefs.getBoolean(FIRST_LAUNCH, true)) {
			Intent intent = new Intent(this, FirstLaunch.class);
			startActivity(intent);
		}

		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
		mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

		// Schließe Drawer
		mNavigationDrawerFragment.closeDrawer();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			// Toast.makeText(this, "Woche selected -> " + position,
			// Toast.LENGTH_SHORT).show();
			fragment = TabFragment.newInstance(position);
			break;
		case 1:
			// Toast.makeText(this, "Woche selected -> " + position,
			// Toast.LENGTH_SHORT).show();
			fragment = TabFragment.newInstance(position);
			break;
		case 2:
			// Toast.makeText(this, "Woche selected -> " + position,
			// Toast.LENGTH_SHORT).show();
			fragment = TabFragment.newInstance(position);
			break;
		case 3:
			// Toast.makeText(this, "Woche selected -> " + position,
			// Toast.LENGTH_SHORT).show();
			fragment = TabFragment.newInstance(position);
			break;
		case 4:
			// Aufruf Zusatzstoffe, Allergene
			fragment = ZusaetzeFragment.newInstance(position);
			break;
		case 5:
			// Aufruf Impressum
			fragment = ImpressFragment.newInstance(position);
			break;
		default:
			break;
		}
		if (fragment != null) {
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("back").commit();
		}
	}

	@Override
	public void onBackPressed() {
		if (mNavigationDrawerFragment.isDrawerOpen()) {
			mNavigationDrawerFragment.closeDrawer();
		} else {
			if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
				super.onBackPressed();
			}
		}
	}
}

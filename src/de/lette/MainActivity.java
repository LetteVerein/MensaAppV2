package de.lette;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

	private Toolbar mToolbar;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private static final String FIRST_LAUNCH = "first_launch";
	private Fragment tabFragment;
	private ArrayList<Integer> positioning;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		positioning = new ArrayList<Integer>();
		
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
		// Check that the activity is using the layout version with
		// the fragment_container FrameLayout
		if (findViewById(R.id.fragment_container) != null) {

			// However, if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			if (savedInstanceState != null) {
				return;
			}

			// Create a new Fragment to be placed in the activity layout
			tabFragment = new TabFragment();

			// Add the fragment to the 'fragment_container' FrameLayout
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, tabFragment).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		Fragment fragment = null;
		switch (position) {
		case 0:
			Toast.makeText(this, "Woche selected -> " + position, Toast.LENGTH_SHORT).show();
			// fragment = new SpeiseplanFragment().newInstance(position);
			break;
		case 1:
			Toast.makeText(this, "Woche selected -> " + position, Toast.LENGTH_SHORT).show();
			// fragment = new SpeiseplanFragment().newInstance(position);
			break;
		case 2:
			Toast.makeText(this, "Woche selected -> " + position, Toast.LENGTH_SHORT).show();
			// fragment = new SpeiseplanFragment().newInstance(position);
			break;
		case 3:
			Toast.makeText(this, "Woche selected -> " + position, Toast.LENGTH_SHORT).show();
			// fragment = new SpeiseplanFragment().newInstance(position);
			break;
		case 4:
			// Aufruf Zusatzstoffe, Allergene
			fragment = new ZusaetzeFragment();
//			transaction.hide(tabFragment);
			break;
		case 5:
			// Aufruf Impressum
			fragment = new ImpressFragment();
//			transaction.hide(tabFragment);
			break;
		default:
			break;
		}
		if (fragment != null) {
			// Replace whatever is in the fragment_container view with this
			// fragment,
			// and add the transaction to the back stack so the user can
			// navigate back
			positioning.add(position);
			transaction.replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
		}
	}

	@Override
	public void onBackPressed() {
		if (mNavigationDrawerFragment.isDrawerOpen()){
			mNavigationDrawerFragment.closeDrawer();			
		} else {
			if(positioning.get(positioning.size()-1) != 0){
				super.onBackPressed();
				if(positioning.size() > 1){
					mNavigationDrawerFragment.getAdapter().selectPosition(positioning.get(positioning.size()-2));
				}else {
					mNavigationDrawerFragment.getAdapter().selectPosition(0);
				}
				positioning.remove(positioning.size()-1);
			} else {
				positioning.removeAll(positioning);
				positioning.add(0);
				Toast.makeText(this, "Zum Beenden nochmal drücken.", Toast.LENGTH_SHORT).show();
			}			
		}
		Log.e("Length", +positioning.size()+"");
	}
}

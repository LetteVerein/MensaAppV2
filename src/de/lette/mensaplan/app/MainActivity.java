package de.lette.mensaplan.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import de.lette.mensaplan.R;

public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks {

	private Toolbar mToolbar;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private SharedPreferences prefs;
	private static final String FIRST_LAUNCH = "first_launch";
	public static final String ARG_WOCHE = "ARG_WOCHE";
	private boolean isLast = false;
	private LocalTagesplan tagesplan;
	private Menu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tagesplan = new LocalTagesplan(getApplicationContext());

		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		if (prefs.getBoolean(FIRST_LAUNCH, true)) {
			Intent intent = new Intent(this, FirstLaunch.class);
			startActivity(intent);
		}

		if (prefs.getString("Tagesplan", null) == null) {
			try {
				tagesplan.setLocalTagesplan();
				Log.i("Neuer Tagesplan","Neuer Tagesplan geladen");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Calendar now = Calendar.getInstance();
		int mWoche = now.get(Calendar.WEEK_OF_MONTH);

		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
		mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

		// Schließe Drawer
		mNavigationDrawerFragment.closeDrawer();
		if(mWoche <= 4){
			mNavigationDrawerFragment.selectItem(mWoche-2);
		}else{
			mNavigationDrawerFragment.selectItem(3);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		this.menu = menu;
        return true;
	}
	
    public void resetUpdating()
    {
        // Get our refresh item from the menu
        MenuItem m = menu.findItem(R.id.action_refresh);
        if(m.getActionView()!=null)
        {
            // Remove the animation.
            m.getActionView().clearAnimation();
            m.setActionView(null);
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	ImageView iv = (ImageView)inflater.inflate(R.layout.iv_refresh, null);
//    	menu.findItem(R.id.action_refresh);
    	Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
    	rotation.setRepeatCount(Animation.INFINITE);
    	iv.startAnimation(rotation);
    	item.setActionView(iv);
        new UpdateSpeisen(this).execute();
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		Fragment fragment = null;
		final FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
		switch (position) {
		case 0:
			fragment = TabFragment.newInstance(position);
			break;
		case 1:
			fragment = TabFragment.newInstance(position);
			break;
		case 2:
			fragment = TabFragment.newInstance(position);
			break;
		case 3:
			fragment = TabFragment.newInstance(position);
			break;
		case 4:
			fragment = ZusaetzeFragment.newInstance(position);
			break;
		case 5:
			fragment = ImpressFragment.newInstance(position);
			break;
		default:
			break;
		}
		isLast = false;
		mNavigationDrawerFragment.getAdapter().selectPosition(position);
		fm.replace(R.id.fragment_container, fragment).addToBackStack("fragBack");
		if (fragment != null) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					fm.commitAllowingStateLoss();
				}
			}, 350);
		}
	}

	@Override
	public void onBackPressed() {
		if (mNavigationDrawerFragment.isDrawerOpen()) {
			mNavigationDrawerFragment.closeDrawer();
		} else {
			if (!isLast) {
				if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
					super.onBackPressed();
				} else if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
					Toast.makeText(this, "Durch nochmaliges drücken wird die App beendet.", Toast.LENGTH_SHORT).show();
					isLast = true;
				}
			} else if (isLast) {
				finish();
			}
		}
	}
}

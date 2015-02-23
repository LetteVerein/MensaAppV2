package de.lette;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import de.lette.mensaplan.server.SpeiseArt;

// In this case, the fragment displays simple text based on the page
public class SpeiseplanFragment extends Fragment {
	public static final String ARG_PAGE = "ARG_PAGE";
	private int mPage;
	boolean isActive = true;

	public static SpeiseplanFragment newInstance(int page) {
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, page);
		SpeiseplanFragment fragment = new SpeiseplanFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPage = getArguments().getInt(ARG_PAGE);
	}

	/**
	 * Fügt die Speisen hinzu und zeigt sie an..
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_speiseplan, container, false);
		try {
			List<Tagesplan> data = ConnectionHandler.getClientData();
			LinearLayout vorspeisen = (LinearLayout) view.findViewById(R.id.vorspeisen);
			LinearLayout vegetarisch = (LinearLayout) view.findViewById(R.id.vegetarisch);
			LinearLayout vollkosten = (LinearLayout) view.findViewById(R.id.vollkost);
			LinearLayout beilagen = (LinearLayout) view.findViewById(R.id.beilagen);
			LinearLayout desserts = (LinearLayout) view.findViewById(R.id.dessert);

			LinearLayout diätVorspeisen = (LinearLayout) view.findViewById(R.id.diätVorspeisen);
			LinearLayout diätVollkosten = (LinearLayout) view.findViewById(R.id.diätVollkost);
			LinearLayout gemüseteller = (LinearLayout) view.findViewById(R.id.gemüseteller);
			LinearLayout diätDesserts = (LinearLayout) view.findViewById(R.id.diätDessert);

			Calendar c = Calendar.getInstance(Locale.getDefault());

			for (Tagesplan tag : data) {
				for (final Speise speise : tag.getSpeisen()) {
					// Date Stuff
					c.setTime(tag.getDatum());
					// int year = c.get(Calendar.YEAR);
					// int mounth = c.get(Calendar.MONTH);
					// int week = c.get(Calendar.WEEK_OF_MONTH);
					int day = c.get(Calendar.DAY_OF_WEEK);
					if (day != mPage + 1)
						continue;

					Log.d("DEBUG", "SpeiseplanFragment about to init");
					if (speise.getArt() == SpeiseArt.VORSPEISE) {
						vorspeisen.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "vorspeise"));
						Log.d("DEBUG", "SpeiseplanFragment vorspeise");
					} else if (speise.getArt() == SpeiseArt.VEGETARISCH) {
						vegetarisch.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "vegetarisch"));
						Log.d("DEBUG", "SpeiseplanFragment vegetarisch");
					} else if (speise.getArt() == SpeiseArt.VOLLKOST) {
						vollkosten.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "hauptspeise"));
						Log.d("DEBUG", "SpeiseplanFragment hauptspeise");
					} else if (speise.getArt() == SpeiseArt.BEILAGEN) {
						beilagen.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "beilagen"));
					} else if (speise.getArt() == SpeiseArt.DESSERT) {
						desserts.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "dessert"));
					} else if (speise.getArt() == SpeiseArt.VORSPEISE && speise.isDiät()) {
						diätVorspeisen.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "vorspeise"));
					} else if (speise.getArt() == SpeiseArt.VOLLKOST && speise.isDiät()) {
						diätVollkosten.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "hauptspeise"));
					} else if (speise.getArt() == SpeiseArt.GEMÜSETELLER && speise.isDiät()) {
						gemüseteller.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "gemüseteller"));
					} else if (speise.getArt() == SpeiseArt.DESSERT && speise.isDiät()) {
						diätDesserts.addView(new SpeisenFragment(getActivity().getApplicationContext(), speise, "dessert"));
					}
					Log.d("DEBUG", "SpeiseplanFragment speise rdy.");
				}
				Log.d("DEBUG", "SpeiseplanFragment next day");
			}
			Log.d("DEBUG", "SpeiseplanFragment all rdy");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.refreshDrawableState();
		return view;
	}

	public int getPageNumber() {
		return mPage;
	}
}
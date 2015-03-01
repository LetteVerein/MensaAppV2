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
import android.widget.RelativeLayout;
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
					

					RelativeLayout wrap = null;

					if (speise.getArt() == SpeiseArt.VORSPEISE && !speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.vorspeisenWrap);
						vorspeisen.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "vorspeise"));
					} else if (speise.getArt() == SpeiseArt.VEGETARISCH && !speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.vegetarischWrap);
						vegetarisch.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "vegetarisch"));
					} else if (speise.getArt() == SpeiseArt.VOLLKOST && !speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.vollkostWrap);
						vollkosten.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "hauptspeise"));
					} else if (speise.getArt() == SpeiseArt.BEILAGEN && !speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.beilagenWrap);
						beilagen.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "beilagen"));
					} else if (speise.getArt() == SpeiseArt.DESSERT && !speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.dessertWrap);
						desserts.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "dessert"));
					} else if (speise.getArt() == SpeiseArt.VORSPEISE && speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.diätVorspeisenWrap);
						diätVorspeisen.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "vorspeise"));
					} else if (speise.getArt() == SpeiseArt.LEICHTEVOLLKOST && speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.diätVollkostWrap);
						diätVollkosten.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "hauptspeise"));
					} else if (speise.getArt() == SpeiseArt.GEMÜSETELLER && speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.gemüsetellerWrap);
						gemüseteller.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "gemueseteller"));
					} else if (speise.getArt() == SpeiseArt.DESSERT && speise.isDiät()) {
						wrap = (RelativeLayout) view.findViewById(R.id.diätDessertWrap);
						diätDesserts.addView(new SpeisenItem(getActivity().getApplicationContext(), speise, "dessert"));
					}
					wrap.setVisibility(RelativeLayout.VISIBLE);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	public int getPageNumber() {
		return mPage;
	}
}
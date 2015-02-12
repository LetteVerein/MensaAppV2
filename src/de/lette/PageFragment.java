package de.lette;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.ClientProtocolException;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.larvalabs.svgandroid.SVGParser;

import de.lette.mensaplan.server.SpeiseArt;

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
	public static final String ARG_PAGE = "ARG_PAGE";
	private int mPage;

	public static PageFragment newInstance(int page) {
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, page);
		PageFragment fragment = new PageFragment();
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
		View view = inflater.inflate(R.layout.fragment_page, container, false);
		try {
			List<Tagesplan> data = ConnectionHandler.getClientData();
			Drawable vorspeise = SVGParser.getSVGFromResource(getResources(), R.raw.vorspeise).createPictureDrawable();
			Drawable hauptspeise = SVGParser.getSVGFromResource(getResources(), R.raw.hauptspeise).createPictureDrawable();
			Drawable nachspeise = SVGParser.getSVGFromResource(getResources(), R.raw.nachspeise).createPictureDrawable();
			LinearLayout vorspeisen = (LinearLayout) view.findViewById(R.id.vorspeisen);
			LinearLayout hauptspeisen = (LinearLayout) view.findViewById(R.id.hauptspeisen);
			LinearLayout nachspeisen = (LinearLayout) view.findViewById(R.id.nachspeisen);
			Calendar c = Calendar.getInstance(Locale.getDefault());
			for(Tagesplan tag : data) {
				for(Speise speise : tag.getSpeisen()) {
					// Date Stuff
					c.setTime(tag.getDatum());
					// int year = c.get(Calendar.YEAR);
					// int mounth = c.get(Calendar.MONTH);
					// int week = c.get(Calendar.WEEK_OF_MONTH);
					int day = c.get(Calendar.DAY_OF_WEEK);
					if(day != mPage + 1) continue;
					
					// Add View to ViewGroup
					View newView = inflater.inflate(R.layout.fragment_page_entry, container, false);
					ImageView iv = (ImageView) newView.findViewById(R.id.fragment_page_entry_imageView);
					iv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
					TextView tv = (TextView) newView.findViewById(R.id.fragment_page_entry_textView);
					tv.setText(speise.getName() + ", " + speise.getKcal() + " kcal, " + speise.getEiweiß() + " Eiweiße, " + speise.getFett()
							+ " Fette, " + speise.getKohlenhydrate() + " Kohlenhydrate.\r\n");
					tv.append("Beachte: " + speise.getBeachte() + "\r\n");
					tv.append("Preis: " + speise.getPreis() + "€");
					
					if(speise.getArt() == SpeiseArt.VORSPEISE) {
						iv.setImageDrawable(vorspeise);
						vorspeisen.addView(newView);
					} else if(speise.getArt() == SpeiseArt.VOLLKOST) {
						iv.setImageDrawable(hauptspeise);
						hauptspeisen.addView(newView);
					} else if(speise.getArt() == SpeiseArt.DESSERT) {
						iv.setImageDrawable(nachspeise);
						nachspeisen.addView(newView);
					}
				}
			}
		} catch(ClientProtocolException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(URISyntaxException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	public int getPageNumber() {
		return mPage;
	}
}
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
			Drawable leichtevollkost = SVGParser.getSVGFromResource(getResources(), R.raw.hauptspeise).createPictureDrawable();
			Drawable gemüseteller = SVGParser.getSVGFromResource(getResources(), R.raw.gemueseteller).createPictureDrawable();
			Drawable dessert = SVGParser.getSVGFromResource(getResources(), R.raw.dessert).createPictureDrawable();
			LinearLayout vorspeisen = (LinearLayout) view.findViewById(R.id.vorspeisen);
			LinearLayout leichtevollkosten = (LinearLayout) view.findViewById(R.id.hauptspeisen);
			LinearLayout gemüsetellerr = (LinearLayout) view.findViewById(R.id.gemüseteller);
			LinearLayout desserts = (LinearLayout) view.findViewById(R.id.dessert);
			
			
			Drawable diätvorspeise = SVGParser.getSVGFromResource(getResources(), R.raw.vorspeise).createPictureDrawable();
			Drawable vegetarisch = SVGParser.getSVGFromResource(getResources(), R.raw.vegetarisch).createPictureDrawable();
			Drawable diätvollkost = SVGParser.getSVGFromResource(getResources(), R.raw.hauptspeise).createPictureDrawable();
			Drawable beilagen = SVGParser.getSVGFromResource(getResources(), R.raw.beilagen).createPictureDrawable();
			Drawable diätdessert = SVGParser.getSVGFromResource(getResources(), R.raw.dessert).createPictureDrawable();
			LinearLayout diätvorspeisen = (LinearLayout) view.findViewById(R.id.diätvorspeisen);
			LinearLayout vegetarische = (LinearLayout) view.findViewById(R.id.diätVegetarisch);
			LinearLayout diätvollkosten = (LinearLayout) view.findViewById(R.id.diätVollkost);
			LinearLayout beilagenn = (LinearLayout) view.findViewById(R.id.diätBeilagen);
			LinearLayout diätdesserts = (LinearLayout) view.findViewById(R.id.diätDessert);
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
						iv.setImageDrawable(leichtevollkost);
						leichtevollkosten.addView(newView);
					}  else if(speise.getArt() == SpeiseArt.GEMÜSETELLER) {
						iv.setImageDrawable(gemüseteller);
						gemüsetellerr.addView(newView);
					} else if(speise.getArt() == SpeiseArt.DESSERT) {
						iv.setImageDrawable(dessert);
						desserts.addView(newView);
					} else if(speise.getArt() == SpeiseArt.VORSPEISE && speise.isDiät()) {
						iv.setImageDrawable(diätvorspeise);
						diätvorspeisen.addView(newView);
					} else if(speise.getArt() == SpeiseArt.VEGETARISCH && speise.isDiät()) {
						iv.setImageDrawable(vegetarisch);
						vegetarische.addView(newView);
					} else if(speise.getArt() == SpeiseArt.VOLLKOST && speise.isDiät()) {
						iv.setImageDrawable(diätvollkost);
						diätvollkosten.addView(newView);
					} else if(speise.getArt() == SpeiseArt.BEILAGEN && speise.isDiät()) {
						iv.setImageDrawable(beilagen);
						beilagenn.addView(newView);
					} else if(speise.getArt() == SpeiseArt.DESSERT && speise.isDiät()) {
						iv.setImageDrawable(diätdessert);
						diätdesserts.addView(newView);
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
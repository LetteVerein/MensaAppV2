package de.lette;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View view = inflater.inflate(R.layout.fragment_speiseplan, container,
	// false);
	// try {
	// final Drawable like1 = SVGParser.getSVGFromResource(getResources(),
	// R.raw.like1).createPictureDrawable();
	// final Drawable dislike1 = SVGParser.getSVGFromResource(getResources(),
	// R.raw.dislike1).createPictureDrawable();
	// final Drawable like2 = SVGParser.getSVGFromResource(getResources(),
	// R.raw.like2).createPictureDrawable();
	// final Drawable dislike2 = SVGParser.getSVGFromResource(getResources(),
	// R.raw.dislike2).createPictureDrawable();
	// List<Tagesplan> data = ConnectionHandler.getClientData();
	// Drawable vorspeise = SVGParser.getSVGFromResource(getResources(),
	// R.raw.vorspeise).createPictureDrawable();
	// Drawable leichtevollkost = SVGParser.getSVGFromResource(getResources(),
	// R.raw.hauptspeise).createPictureDrawable();
	// Drawable gemüseteller = SVGParser.getSVGFromResource(getResources(),
	// R.raw.gemueseteller).createPictureDrawable();
	// Drawable dessert = SVGParser.getSVGFromResource(getResources(),
	// R.raw.dessert).createPictureDrawable();
	// LinearLayout vorspeisen = (LinearLayout)
	// view.findViewById(R.id.vorspeisen);
	// LinearLayout leichtevollkosten = (LinearLayout)
	// view.findViewById(R.id.hauptspeisen);
	// LinearLayout gemüsetellerr = (LinearLayout)
	// view.findViewById(R.id.gemüseteller);
	// LinearLayout desserts = (LinearLayout) view.findViewById(R.id.dessert);
	//
	// Drawable diätvorspeise = SVGParser.getSVGFromResource(getResources(),
	// R.raw.vorspeise).createPictureDrawable();
	// Drawable vegetarisch = SVGParser.getSVGFromResource(getResources(),
	// R.raw.vegetarisch).createPictureDrawable();
	// Drawable diätvollkost = SVGParser.getSVGFromResource(getResources(),
	// R.raw.hauptspeise).createPictureDrawable();
	// Drawable beilagen = SVGParser.getSVGFromResource(getResources(),
	// R.raw.beilagen).createPictureDrawable();
	// Drawable diätdessert = SVGParser.getSVGFromResource(getResources(),
	// R.raw.dessert).createPictureDrawable();
	// LinearLayout diätvorspeisen = (LinearLayout)
	// view.findViewById(R.id.diätvorspeisen);
	// LinearLayout vegetarische = (LinearLayout)
	// view.findViewById(R.id.diätVegetarisch);
	// LinearLayout diätvollkosten = (LinearLayout)
	// view.findViewById(R.id.diätVollkost);
	// LinearLayout beilagenn = (LinearLayout)
	// view.findViewById(R.id.diätBeilagen);
	// LinearLayout diätdesserts = (LinearLayout)
	// view.findViewById(R.id.diätDessert);
	// Calendar c = Calendar.getInstance(Locale.getDefault());
	// for (Tagesplan tag : data) {
	// for (final Speise speise : tag.getSpeisen()) {
	// // Date Stuff
	// c.setTime(tag.getDatum());
	// // int year = c.get(Calendar.YEAR);
	// // int mounth = c.get(Calendar.MONTH);
	// // int week = c.get(Calendar.WEEK_OF_MONTH);
	// int day = c.get(Calendar.DAY_OF_WEEK);
	// if (day != mPage + 1)
	// continue;
	//
	// // Add View to ViewGroup
	// View newView = inflater.inflate(R.layout.fragment_page_entry, container,
	// false);
	// ImageView iv = (ImageView)
	// newView.findViewById(R.id.fragment_page_entry_imageView);
	// final ImageView like = (ImageView)
	// newView.findViewById(R.id.fragment_page_entry_like);
	// final ImageView dislike = (ImageView)
	// newView.findViewById(R.id.fragment_page_entry_dislike);
	// final TextView likeCount = (TextView)
	// newView.findViewById(R.id.fragment_page_entry_like_count);
	// likeCount.setText("" + speise.getLikes());
	// final TextView dislikeCount = (TextView)
	// newView.findViewById(R.id.fragment_page_entry_dislike_count);
	// dislikeCount.setText("" + speise.getDislikes());
	// iv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	// like.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	// like.setImageDrawable(like2);
	// like.setOnClickListener(new OnClickListener() {
	// boolean clicked = false;
	// @Override
	// public void onClick(View v) {
	// if (clicked) {
	// speise.remLikes();
	// like.setImageDrawable(like2);
	// clicked = !clicked;
	// } else if (!clicked) {
	// speise.addLikes();
	// like.setImageDrawable(like1);
	// clicked = !clicked;
	// }
	// isActive = !isActive;
	// likeCount.setText("" + speise.getLikes());
	// dislike.setClickable(isActive);
	// }
	// });
	// dislike.setOnClickListener(new OnClickListener() {
	// boolean clicked = false;
	// @Override
	// public void onClick(View v) {
	// if (clicked) {
	// speise.remDislikes();
	// dislike.setImageDrawable(dislike2);
	// clicked = !clicked;
	// } else if (!clicked) {
	// speise.addDislikes();
	// dislike.setImageDrawable(dislike1);
	// clicked = !clicked;
	// }
	// isActive = !isActive;
	// dislikeCount.setText("" + speise.getDislikes());
	// like.setClickable(isActive);
	// }
	// });
	// dislike.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	// dislike.setImageDrawable(dislike2);
	// TextView tv = (TextView)
	// newView.findViewById(R.id.fragment_page_entry_textView);
	// tv.setText(speise.getName() + ", " + speise.getKcal() + " kcal, " +
	// speise.getEiweiß() + " Eiweiße, " + speise.getFett() + " Fette, " +
	// speise.getKohlenhydrate() + " Kohlenhydrate.\r\n");
	// tv.append("Beachte: " + speise.getBeachte() + "\r\n");
	// tv.append("Preis: " + speise.getPreis() + "€");
	//
	// if (speise.getArt() == SpeiseArt.VORSPEISE) {
	// iv.setImageDrawable(vorspeise);
	// vorspeisen.addView(newView);
	// } else if (speise.getArt() == SpeiseArt.VOLLKOST) {
	// iv.setImageDrawable(leichtevollkost);
	// leichtevollkosten.addView(newView);
	// } else if (speise.getArt() == SpeiseArt.GEMÜSETELLER) {
	// iv.setImageDrawable(gemüseteller);
	// gemüsetellerr.addView(newView);
	// } else if (speise.getArt() == SpeiseArt.DESSERT) {
	// iv.setImageDrawable(dessert);
	// desserts.addView(newView);
	// } else if (speise.getArt() == SpeiseArt.VORSPEISE && speise.isDiät()) {
	// iv.setImageDrawable(diätvorspeise);
	// diätvorspeisen.addView(newView);
	// } else if (speise.getArt() == SpeiseArt.VEGETARISCH && speise.isDiät()) {
	// iv.setImageDrawable(vegetarisch);
	// vegetarische.addView(newView);
	// } else if (speise.getArt() == SpeiseArt.VOLLKOST && speise.isDiät()) {
	// iv.setImageDrawable(diätvollkost);
	// diätvollkosten.addView(newView);
	// } else if (speise.getArt() == SpeiseArt.BEILAGEN && speise.isDiät()) {
	// iv.setImageDrawable(beilagen);
	// beilagenn.addView(newView);
	// } else if (speise.getArt() == SpeiseArt.DESSERT && speise.isDiät()) {
	// iv.setImageDrawable(diätdessert);
	// diätdesserts.addView(newView);
	// }
	// }
	// }
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (URISyntaxException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return view;
	// }

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
					
					SpeisenFragment speisenFragment = new SpeisenFragment(getActivity().getApplicationContext());

					if (speise.getArt() == SpeiseArt.VORSPEISE) {
						speisenFragment.createSpeise(speise, "vorspeise");
						vorspeisen.addView(speisenFragment);
					} else if (speise.getArt() == SpeiseArt.VEGETARISCH) {
						speisenFragment.createSpeise(speise, "vegetarisch");
						vegetarisch.addView(speisenFragment);
					} else if (speise.getArt() == SpeiseArt.VOLLKOST) {
						speisenFragment.createSpeise(speise, "hauptspeise");
						vollkosten.addView(speisenFragment);
					} else if (speise.getArt() == SpeiseArt.BEILAGEN) {
						speisenFragment.createSpeise(speise, "beilagen");
						beilagen.addView(speisenFragment);
					} else if (speise.getArt() == SpeiseArt.DESSERT) {
						speisenFragment.createSpeise(speise, "dessert");
						desserts.addView(speisenFragment);
					} else if (speise.getArt() == SpeiseArt.VORSPEISE && speise.isDiät()) {
						speisenFragment.createSpeise(speise, "vorspeise");
						diätVorspeisen.addView(speisenFragment);
					} else if (speise.getArt() == SpeiseArt.VOLLKOST && speise.isDiät()) {
						speisenFragment.createSpeise(speise, "hauptspeise");
						diätVollkosten.addView(speisenFragment);
					} else if (speise.getArt() == SpeiseArt.GEMÜSETELLER && speise.isDiät()) {
						speisenFragment.createSpeise(speise, "gemüseteller");
						gemüseteller.addView(speisenFragment);
					} else if (speise.getArt() == SpeiseArt.DESSERT && speise.isDiät()) {
						speisenFragment.createSpeise(speise, "dessert");
						diätDesserts.addView(speisenFragment);
					}
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
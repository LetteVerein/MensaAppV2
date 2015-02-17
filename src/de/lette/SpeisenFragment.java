package de.lette;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.larvalabs.svgandroid.SVGParser;

public class SpeisenFragment extends View {
	
	private ImageView icon = null;
	private ImageView likeButton,dislikeButton;
	private Drawable like1,like2,dislike1,dislike2;
	private boolean isActive;
	private TextView speisenBeschreibung,speisenInfo,speisenPreis;

	public SpeisenFragment(Context context, final Speise speise) {
		super(context);
		inflate(context, R.layout.fragment_page_entry, null);
		
		speisenBeschreibung = (TextView) findViewById(R.id.fragment_page_entry_description);
		speisenBeschreibung.setText(speise.getName() + ", " + speise.getKcal() + " kcal, " + speise.getEiweiß() + " Eiweiße, " + speise.getFett() + " Fette, " + speise.getKohlenhydrate() + " Kohlenhydrate.\r\n");
		speisenInfo = (TextView) findViewById(R.id.fragment_page_entry_warning);
		speisenInfo.append("Beachte: " + speise.getBeachte() + "\r\n");
		speisenPreis = (TextView) findViewById(R.id.fragment_page_entry_price);
		speisenPreis.append("Preis: " + speise.getPreis().unscaledValue() + "€");
		
		
		likeButton = (ImageView) findViewById(R.raw.like1);
		likeButton.setLayerType(LAYER_TYPE_SOFTWARE, null);
		like1 = SVGParser.getSVGFromResource(getResources(), R.raw.like1).createPictureDrawable();
		like2 = SVGParser.getSVGFromResource(getResources(), R.raw.like2).createPictureDrawable();
		likeButton.setImageDrawable(like1);
		final TextView likeCount = (TextView) findViewById(R.id.fragment_page_entry_like_count);
		
		dislikeButton = (ImageView) findViewById(R.raw.dislike1);
		dislikeButton.setLayerType(LAYER_TYPE_SOFTWARE, null);
		dislike1 = SVGParser.getSVGFromResource(getResources(), R.raw.dislike1).createPictureDrawable();
		dislike2 = SVGParser.getSVGFromResource(getResources(), R.raw.dislike2).createPictureDrawable();
		dislikeButton.setImageDrawable(dislike1);
		final TextView dislikeCount = (TextView) findViewById(R.id.fragment_page_entry_dislike_count);
		
		likeButton.setOnClickListener(new OnClickListener() {
			boolean clicked = false;
			@Override
			public void onClick(View v) {
				if (clicked) {
					speise.remLikes();
					likeButton.setImageDrawable(like2);
					clicked = !clicked;
				} else if (!clicked) {
					speise.addLikes();
					likeButton.setImageDrawable(like1);
					clicked = !clicked;
				}
				isActive = !isActive;
				likeCount.setText("" + speise.getLikes());
				dislikeButton.setClickable(isActive);
			}
		});
		dislikeButton.setOnClickListener(new OnClickListener() {
			boolean clicked = false;
			@Override
			public void onClick(View v) {
				if (clicked) {
					speise.remDislikes();
					dislikeButton.setImageDrawable(dislike2);
					clicked = !clicked;
				} else if (!clicked) {
					speise.addDislikes();
					dislikeButton.setImageDrawable(dislike1);
					clicked = !clicked;
				}
				isActive = !isActive;
				dislikeCount.setText("" + speise.getDislikes());
				likeButton.setClickable(isActive);
			}
		});
	}
	
	//Holt sich ein Vectorbild aus dem Raw Ordner.
	public void setIcon(String drawable){		
		Drawable imageResource = SVGParser.getSVGFromResource(getResources(), getResources().getIdentifier(drawable, "raw", "de.lette")).createPictureDrawable();
		icon.setImageDrawable(imageResource);
	}
	
}
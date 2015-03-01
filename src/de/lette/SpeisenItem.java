package de.lette;

import java.math.RoundingMode;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.larvalabs.svgandroid.SVGParser;

public class SpeisenItem extends LinearLayout {
	private ImageView icon;
	private ImageView likeButton, dislikeButton;
	private Drawable like1, like2, dislike1, dislike2;
	private View selectedButton = null;
	private TextView speisenBeschreibung, speisenInfo, speisenPreis;

	public SpeisenItem(Context context, final Speise speise, String drawable) {
		super(context);

		View view = View.inflate(getContext(), R.layout.speisen_item, this);
		icon = (ImageView) view.findViewById(R.id.fragment_page_entry_imageView);
		Drawable imageResource = SVGParser.getSVGFromResource(getResources(), getResources().getIdentifier(drawable, "raw", "de.lette")).createPictureDrawable();
		icon.setImageDrawable(imageResource);
		icon.setLayerType(LAYER_TYPE_SOFTWARE, null);

		speisenBeschreibung = (TextView) view.findViewById(R.id.fragment_page_entry_description);
		speisenBeschreibung.setText(speise.getName());
//		speisenInfo = (TextView) view.findViewById(R.id.fragment_page_entry_warning);
//		speisenInfo.setText("Beachte: " + speise.getBeachte());
		speisenPreis = (TextView) view.findViewById(R.id.fragment_page_entry_price);
		speisenPreis.setText("Preis: " + speise.getPreis().setScale(2, RoundingMode.HALF_DOWN) + "€");

		likeButton = (ImageView) view.findViewById(R.id.fragment_page_entry_like);
		likeButton.setLayerType(LAYER_TYPE_SOFTWARE, null);
		like1 = SVGParser.getSVGFromResource(getResources(), R.raw.like1).createPictureDrawable();
		like2 = SVGParser.getSVGFromResource(getResources(), R.raw.like2).createPictureDrawable();
		likeButton.setImageDrawable(like2);
		final TextView likeCount = (TextView) view.findViewById(R.id.fragment_page_entry_like_count);
		likeCount.setText("" + speise.getLikes());

		likeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (selectedButton == v) {
					speise.remLikes();
					likeButton.setImageDrawable(like2);
					selectedButton = null;
					dislikeButton.setClickable(true);
				} else if (selectedButton == null) {
					speise.addLikes();
					likeButton.setImageDrawable(like1);
					selectedButton = v;
					dislikeButton.setClickable(false);
				}
				likeCount.setText("" + speise.getLikes());
			}
		});

		dislikeButton = (ImageView) view.findViewById(R.id.fragment_page_entry_dislike);
		dislikeButton.setLayerType(LAYER_TYPE_SOFTWARE, null);
		dislike1 = SVGParser.getSVGFromResource(getResources(), R.raw.dislike1).createPictureDrawable();
		dislike2 = SVGParser.getSVGFromResource(getResources(), R.raw.dislike2).createPictureDrawable();
		dislikeButton.setImageDrawable(dislike2);
		final TextView dislikeCount = (TextView) view.findViewById(R.id.fragment_page_entry_dislike_count);
		dislikeCount.setText("" + speise.getDislikes());

		dislikeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (selectedButton == v) {
					speise.remDislikes();
					dislikeButton.setImageDrawable(dislike2);
					selectedButton = null;
					likeButton.setClickable(true);
				} else if (selectedButton == null) {
					speise.addDislikes();
					dislikeButton.setImageDrawable(dislike1);
					selectedButton = v;
					likeButton.setClickable(false);
				}
				dislikeCount.setText("" + speise.getDislikes());
			}
		});
	}
}
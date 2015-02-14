package de.lette;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ZusaetzeFragment extends Fragment {
	
	InputStream zusatzStream;
	InputStream allergenStream;
	
	public static ZusaetzeFragment newInstance(int page) {
		ZusaetzeFragment fragment = new ZusaetzeFragment();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		zusatzStream = getResources().openRawResource(R.raw.zusaetze);
		allergenStream = getResources().openRawResource(R.raw.allergene);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_zusaetze, container, false);
		
		TextView zusaetze = (TextView) view.findViewById(R.id.zusaetze);
		TextView allergene = (TextView) view.findViewById(R.id.allergene);
		
		try {
			zusaetze.setText(readRawTextFile(zusatzStream));
			allergene.setText(readRawTextFile(allergenStream));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return view;
	}
	
	public static String readRawTextFile(InputStream i) throws IOException
	{
	    BufferedReader reader = new BufferedReader(new InputStreamReader(i));
	    String line = reader.readLine();
	    StringBuilder text = new StringBuilder();
	    while (line != null) {
	            text.append(line);
	            text.append('\n');
	        }
	    return text.toString();
	}
}

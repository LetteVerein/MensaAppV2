package de.lette.mensaplan.app;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LocalTagesplan {
	
	static SharedPreferences sharedPrefs;
	static Editor editor;

	public LocalTagesplan(Context context) {
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public void setLocalTagesplan() throws IOException, URISyntaxException, ParseException{
		List<Tagesplan> data = ConnectionHandler.getClientData();
		String json = new Gson().toJson(data);
		editor = sharedPrefs.edit();
		editor.putString("Tagesplan", json);
		editor.commit();
	}
	
	public List<Tagesplan> getLocalTagesplan(){
		Type type = new TypeToken<List<Tagesplan>>() {}.getType();
		String json = sharedPrefs.getString("Tagesplan", "");
		List<Tagesplan> tagesplan = new Gson().fromJson(json, type);
		return tagesplan;
	}
}

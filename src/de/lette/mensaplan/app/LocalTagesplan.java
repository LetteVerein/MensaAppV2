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
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LocalTagesplan {

	static SharedPreferences sharedPrefs;
	static Editor editor;

	public LocalTagesplan(Context context) {
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public void setLocalTagesplan() {
		try {
			List<Tagesplan> data = ConnectionHandler.getClientData();
			if (data != null) {
				String json = new Gson().toJson(data);
				editor = sharedPrefs.edit();
				editor.putString("Tagesplan", json);
				editor.commit();
			}
		} catch (IOException e) {
			Log.d("CONNECTION", e.getMessage());
		} catch (URISyntaxException e) {
			Log.d("CONNECTION", e.getMessage());
		} catch (ParseException e) {
			Log.d("CONNECTION", e.getMessage());
		}
	}

	public List<Tagesplan> getLocalTagesplan() {
		Type type = new TypeToken<List<Tagesplan>>() {
		}.getType();
		String json = sharedPrefs.getString("Tagesplan", "");
		List<Tagesplan> tagesplan = new Gson().fromJson(json, type);
		return tagesplan;
	}
}

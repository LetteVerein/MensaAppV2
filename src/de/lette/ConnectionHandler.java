package de.lette;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.google.gson.Gson;

import de.lette.mensaplan.server.ClientData;
import de.lette.mensaplan.server.SpeiseArt;
import de.lette.mensaplan.server.Termin;
import de.lette.mensaplan.server.Zusatzstoff;

public class ConnectionHandler {
	public static String urlToServer = "";
	private static List<Tagesplan> tagesPläne;

	public static List<Tagesplan> getClientData() throws ClientProtocolException,
			IOException, URISyntaxException {
		// data = getClientDataFromServer();
		if (tagesPläne != null)
			return tagesPläne;
		ClientData data = new ClientData();

		Set<de.lette.mensaplan.server.Speise> alleSpeisen = new LinkedHashSet<de.lette.mensaplan.server.Speise>();
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(1, "Suppe", SpeiseArt.VORSPEISE, false, "Suppe", 200, 200, 200, 200));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(2, "Gurken Salat", SpeiseArt.VORSPEISE,false, "Salat", 100, 100, 100, 100));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(3, "Schwere Suppe", SpeiseArt.VORSPEISE, false, "Suppe", 200, 200, 200, 200));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(4, "Obelix Salat", SpeiseArt.VORSPEISE,false, "Salat", 100, 100, 100, 100));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(5, "Leichte Suppe", SpeiseArt.VORSPEISE, false, "Suppe", 200, 200, 200, 200));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(6, "Caesar Salat", SpeiseArt.VORSPEISE,false, "Salat", 100, 100, 100, 100));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(7, "Hamburger", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(8, "Pferde Steak", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(9, "Bio Burger", SpeiseArt.VOLLKOST, true, "Bio", 0, 0, 0, 0));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(10, "Mc Chicken", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(11, "Kuh Fladen", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(12, "Bio Birne", SpeiseArt.VOLLKOST, true, "Bio", 0, 0, 0, 0));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(13, "Cheeseburger", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(14, "Kuh Steak", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(15, "Bio Banane", SpeiseArt.VOLLKOST, true, "Bio", 0, 0, 0, 0));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(16, "Eis im Becher", SpeiseArt.DESSERT, false, "Eis", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(17, "Eis im Eimer", SpeiseArt.DESSERT, false, "Eis", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(18, "Pudding im Becher", SpeiseArt.DESSERT, false, "Pudding", 9000, 9000, 9000, 9000));
		data.setSpeisen(alleSpeisen);

		Set<Termin> termine = new LinkedHashSet<Termin>();
		Calendar c = Calendar.getInstance(Locale.getDefault());
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		// Monday This Week
		termine.add(new Termin(1, new Date(c.getTimeInMillis()), 1, new BigDecimal(1.00), false));
		termine.add(new Termin(2, new Date(c.getTimeInMillis()), 7, new BigDecimal(1.75), false));
		termine.add(new Termin(3, new Date(c.getTimeInMillis()), 8, new BigDecimal(1.75), false));
		termine.add(new Termin(4, new Date(c.getTimeInMillis()), 16, new BigDecimal(1.00), false));
		
		// Monday +1 ;D
		c.add(Calendar.DAY_OF_WEEK, 1);
		termine.add(new Termin(5, new Date(c.getTimeInMillis()), 2, new BigDecimal(2.00), false));
		termine.add(new Termin(6, new Date(c.getTimeInMillis()), 9, new BigDecimal(2.50), false));
		termine.add(new Termin(7, new Date(c.getTimeInMillis()), 17, new BigDecimal(3.75), false));

		// Monday +2 ;D
		c.add(Calendar.DAY_OF_WEEK, 1);
		termine.add(new Termin(8, new Date(c.getTimeInMillis()), 3, new BigDecimal(2.75), false));
		termine.add(new Termin(9, new Date(c.getTimeInMillis()), 10, new BigDecimal(1.75), false));
		termine.add(new Termin(10, new Date(c.getTimeInMillis()), 11, new BigDecimal(1.75), false));
		termine.add(new Termin(11, new Date(c.getTimeInMillis()), 18, new BigDecimal(2.00), false));

		// Monday +3 ;D
		c.add(Calendar.DAY_OF_WEEK, 1);
		termine.add(new Termin(12, new Date(c.getTimeInMillis()), 4, new BigDecimal(2.50), false));
		termine.add(new Termin(13, new Date(c.getTimeInMillis()), 12, new BigDecimal(3.75), false));
		termine.add(new Termin(14, new Date(c.getTimeInMillis()), 13, new BigDecimal(2.75), false));
		termine.add(new Termin(15, new Date(c.getTimeInMillis()), 16, new BigDecimal(1.75), false));
		
		// Monday +4 ;D
		c.add(Calendar.DAY_OF_WEEK, 1);
		termine.add(new Termin(16, new Date(c.getTimeInMillis()), 5, new BigDecimal(1.75), false));
		termine.add(new Termin(17, new Date(c.getTimeInMillis()), 14, new BigDecimal(2.00), false));
		termine.add(new Termin(18, new Date(c.getTimeInMillis()), 15, new BigDecimal(2.00), false));
		termine.add(new Termin(19, new Date(c.getTimeInMillis()), 18, new BigDecimal(2.50), false));
		data.setTermine(termine);

		Set<Zusatzstoff> zusätze = new LinkedHashSet<Zusatzstoff>();
		zusätze.add(new Zusatzstoff(1, 1, "Kohlenstoffdioxid"));
		zusätze.add(new Zusatzstoff(2, 2, "Kohlenstofftrioxid"));
		zusätze.add(new Zusatzstoff(3, 3, "Zucker"));
		data.setZusatzstoffe(zusätze);

		tagesPläne = new ArrayList<Tagesplan>();
		for(Entry<java.util.Date, Map<de.lette.mensaplan.server.Speise, Termin>> dateMap : data.getSpeisenForDate().entrySet()) {
			// Normalerweise ist jedes Date-Objekt ein Tag, es kann jedoch auch vorkommen, dass 2 Date-Objekte 1 Tag sind.
			Tagesplan tagesPlan = new Tagesplan(dateMap.getKey());
			for(Entry<de.lette.mensaplan.server.Speise, Termin> dataMap : dateMap.getValue().entrySet()) {
				de.lette.mensaplan.server.Speise s = dataMap.getKey();
				Speise speise = new Speise(s.getName(), s.getArt(), s.isDiät(), dataMap.getValue().getPreis(), s.getBeachte(), s.getKcal(), s.getEiweiß(), s.getFett(), s.getKohlenhydrate(), data.getZusatzstoffe(s));
				tagesPlan.addSpeise(speise);
			}
			tagesPläne.add(tagesPlan);
		}
		return tagesPläne;
	}

	/**
	 * Diese Methode stellt eine Verbindung zum MensaplanServer her, lädt die
	 * gewünschten Daten herunter und gibt sie als ClientData-Objekt zurück.
	 * 
	 * @return das ClientData-Objekt
	 * @throws IOException
	 *             wenn die Verbindung mit dem Server nicht aufgebaut werden
	 *             konnte oder Fehler beim Lesen auftreten.
	 * @throws URISyntaxException
	 */
	@SuppressWarnings("unused")
	private static ClientData getClientDataFromServer() throws IOException,
			URISyntaxException, ClientProtocolException {
		// Prepare Connection //
		URL url = new URL(urlToServer);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		// Read Stream //
		BufferedReader br = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String result = "";
		String currentLine = "";
		while ((currentLine = br.readLine()) != null) {
			result += currentLine;
		}
		Log.d("MensaplanData", "" + result);
		br.close();

		// Get the ClientData
		Gson gson = new Gson();
		return gson.fromJson(result, ClientData.class);
	}
}
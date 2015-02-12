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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import android.text.format.Time;
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
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(1, "Hamburger", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(2, "Caesar Salat", SpeiseArt.VORSPEISE,false, "Salat", 100, 100, 100, 100));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(3, "Suppe", SpeiseArt.VORSPEISE, false, "Suppe", 200, 200, 200, 200));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(4, "Pferde Steak", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(5, "Bio Burger", SpeiseArt.VOLLKOST, true, "Bio", 0, 0, 0, 0));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(6, "Eis im Eimer", SpeiseArt.DESSERT, false, "Eis", 9000, 9000, 9000, 9000));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(7, "Cheeseburger", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(8, "Obelix Salat", SpeiseArt.VORSPEISE,false, "Salat", 100, 100, 100, 100));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(9, "Leichte Suppe", SpeiseArt.VORSPEISE, false, "Suppe", 200, 200, 200, 200));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(10, "Kuh Steak", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(11, "Bio Banane", SpeiseArt.VOLLKOST, true, "Bio", 0, 0, 0, 0));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(12, "Eis im Becher", SpeiseArt.DESSERT, false, "Eis", 9000, 9000, 9000, 9000));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(13, "Mc Chicken", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(14, "Gurken Salat", SpeiseArt.VORSPEISE,false, "Salat", 100, 100, 100, 100));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(15, "Schwere Suppe", SpeiseArt.VORSPEISE, false, "Suppe", 200, 200, 200, 200));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(16, "Kuh Fladen", SpeiseArt.VOLLKOST, false, "Fleisch", 9000, 9000, 9000, 9000));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(17, "Bio Birne", SpeiseArt.VOLLKOST, true, "Bio", 0, 0, 0, 0));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(18, "Pudding im Becher", SpeiseArt.DESSERT, false, "Pudding", 9000, 9000, 9000, 9000));
		data.setSpeisen(alleSpeisen);

		Set<Termin> termine = new LinkedHashSet<Termin>();
		Time today = new Time(Time.getCurrentTimezone());
		Time tomorrow = new Time(Time.getCurrentTimezone());
		Time nextWeek = new Time(Time.getCurrentTimezone());
		today.setToNow();
		tomorrow.setToNow();
		nextWeek.setToNow();
		tomorrow.monthDay += 1;
		nextWeek.monthDay += 7;
		termine.add(new Termin(1, new Date(today.toMillis(false)), 1, new BigDecimal(3.75), false));
		termine.add(new Termin(2, new Date(today.toMillis(false)), 2, new BigDecimal(2.75), true));
		termine.add(new Termin(3, new Date(today.toMillis(false)), 3, new BigDecimal(1.75), true));
		termine.add(new Termin(4, new Date(today.toMillis(false)), 4, new BigDecimal(1.75), false));
		termine.add(new Termin(5, new Date(today.toMillis(false)), 5, new BigDecimal(2.00), false));
		termine.add(new Termin(6, new Date(today.toMillis(false)), 6, new BigDecimal(2.50), false));
		
		termine.add(new Termin(7, new Date(tomorrow.toMillis(false)), 7, new BigDecimal(3.75), false));
		termine.add(new Termin(8, new Date(tomorrow.toMillis(false)), 8, new BigDecimal(2.75), true));
		termine.add(new Termin(9, new Date(tomorrow.toMillis(false)), 9, new BigDecimal(1.75), true));
		termine.add(new Termin(10, new Date(tomorrow.toMillis(false)), 10, new BigDecimal(1.75), false));
		termine.add(new Termin(11, new Date(tomorrow.toMillis(false)), 11, new BigDecimal(2.00), false));
		termine.add(new Termin(12, new Date(tomorrow.toMillis(false)), 12, new BigDecimal(2.50), false));
		
		termine.add(new Termin(13, new Date(nextWeek.toMillis(false)), 13, new BigDecimal(3.75), false));
		termine.add(new Termin(14, new Date(nextWeek.toMillis(false)), 14, new BigDecimal(2.75), true));
		termine.add(new Termin(15, new Date(nextWeek.toMillis(false)), 15, new BigDecimal(1.75), true));
		termine.add(new Termin(16, new Date(nextWeek.toMillis(false)), 16, new BigDecimal(1.75), false));
		termine.add(new Termin(17, new Date(nextWeek.toMillis(false)), 17, new BigDecimal(2.00), false));
		termine.add(new Termin(18, new Date(nextWeek.toMillis(false)), 18, new BigDecimal(2.50), false));
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
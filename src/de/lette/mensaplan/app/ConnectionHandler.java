package de.lette.mensaplan.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Log;

import com.google.gson.Gson;

import de.lette.mensaplan.server.ClientData;
import de.lette.mensaplan.server.Termin;

public class ConnectionHandler {
	public static String urlToServer = "http://192.168.50.30:8080/MensaPlan/?startdate=2001-01-01";
	private static List<Tagesplan> tagesPläne;

	public static List<Tagesplan> getClientData() throws IOException, URISyntaxException, ParseException {
		if(tagesPläne != null) return tagesPläne;
		ClientData data = getClientDataFromServer();

		// Set<de.lette.mensaplan.server.Speise> alleSpeisen = new LinkedHashSet<de.lette.mensaplan.server.Speise>();
		//
		// data.setSpeisen(alleSpeisen);
		//
		// Set<Termin> termine = new LinkedHashSet<Termin>();
		//
		// data.setTermine(termine);
		//
		// Set<Zusatzstoff> zusätze = new LinkedHashSet<Zusatzstoff>();
		// zusätze.add(new Zusatzstoff(1, 1, "Konservierungsmittel"));
		// zusätze.add(new Zusatzstoff(2, 2, "Antioxidationsmittel"));
		// zusätze.add(new Zusatzstoff(3, 3, "Farbstoff"));
		// zusätze.add(new Zusatzstoff(4, 4, "Geschmacksverstärker, u.a. Natriumglutamat"));
		// zusätze.add(new Zusatzstoff(5, 5, "Schwefel"));
		// zusätze.add(new Zusatzstoff(6, 6, "Phosphat"));
		// zusätze.add(new Zusatzstoff(7, 7, "geschwärzt"));
		// zusätze.add(new Zusatzstoff(8, 8, "gewachst"));
		// zusätze.add(new Zusatzstoff(9, 9, "Süßungsmittel, u.a. Saccarin, Cyclamat, Aspartam"));
		// zusätze.add(new Zusatzstoff(10, 10, "enthält eine Phenylalaninquelle"));
		// zusätze.add(new Zusatzstoff(11, 11, "Säuerungsmittel"));
		// zusätze.add(new Zusatzstoff(12, 12, "Hühnereiweiß"));
		// zusätze.add(new Zusatzstoff(13, 13, "Jodsalz"));
		// zusätze.add(new Zusatzstoff(14, 14, "Nitritpökelsalz"));
		// zusätze.add(new Zusatzstoff(15, 15, "Milcheiweiß ( Kuhmilch und Kuhmilcherzeugnisse)"));
		// zusätze.add(new Zusatzstoff(16, 16, "Hefeextrakt"));
		// zusätze.add(new Zusatzstoff(17, 17, "Aroma"));
		// zusätze.add(new Zusatzstoff(18, 18, "Zuckerarten und /oder Süßungsmittel"));
		// zusätze.add(new Zusatzstoff(19, 19, "Gluten"));
		//
		// zusätze.add(new Zusatzstoff(20, 20, "Glutenhaltiges Getreide/ Erzeugnisse ( Weizen, Roggen, Gerste, Hafer, Dinkel)"));
		// zusätze.add(new Zusatzstoff(21, 21, "Soja und Sojaerzeugnisse"));
		// zusätze.add(new Zusatzstoff(22, 22, "Sellerie und Sellerieerzeugnisse"));
		// zusätze.add(new Zusatzstoff(23, 23, "Senf und Senfsaaten"));
		// zusätze.add(new Zusatzstoff(24, 24, "Sesamsaaten und Sesamsamenerzeugnisse"));
		// zusätze.add(new Zusatzstoff(25, 25, "Lupine und Lupinenerzeugnisse"));
		// zusätze.add(new Zusatzstoff(26, 26, "Erdnüsse und Erdnusserzeugnisse"));
		// zusätze.add(new Zusatzstoff(27, 27, "Fisch und Fischerzeugnisse"));
		// zusätze.add(new Zusatzstoff(28, 28, "Krebstiere und Krebserzeugnisse u.a. Krabben, Garnelen, Hummer"));
		// zusätze.add(new Zusatzstoff(29, 29, "Weichtiere und Weichtiererzeugnisse u.a. Muscheln, Schnecken"));
		// zusätze.add(new Zusatzstoff(30, 30, "Schalenfrüchte und Schalenfruchterzeugnisse(z.B. Mandeln, Haselnüsse)"));
		// zusätze.add(new Zusatzstoff(31, 31, "Eier und Eiererzeugnisse "));
		// zusätze.add(new Zusatzstoff(32, 32, "Milch und Milcherzeugnisse (incl. Lactose)"));
		// zusätze.add(new Zusatzstoff(33, 33, "Schwefeldioxid und Sulfite"));
		// data.setZusatzstoffe(zusätze);

		tagesPläne = new ArrayList<Tagesplan>();
		for(Entry<java.util.Date, Map<de.lette.mensaplan.server.Speise, Termin>> dateMap : data.getSpeisenForDate().entrySet()) {
			// Normalerweise ist jedes Date-Objekt ein Tag, es kann jedoch auch vorkommen, dass 2 Date-Objekte 1 Tag sind.
			Tagesplan tagesPlan = new Tagesplan(dateMap.getKey());
			for(Entry<de.lette.mensaplan.server.Speise, Termin> dataMap : dateMap.getValue().entrySet()) {
				de.lette.mensaplan.server.Speise s = dataMap.getKey();
				Speise speise = new Speise(s.getName(), s.getArt(), s.isDiät(), dataMap.getValue().getPreis(), s.getBeachte(), s.getKcal(), s.getEiweiß(), s.getFett(), s.getKohlenhydrate(),
						data.getZusatzstoffe(s), s.getLikes(), s.getDislikes());
				tagesPlan.addSpeise(speise);
			}
			tagesPläne.add(tagesPlan);
		}
		return tagesPläne;
	}

	/**
	 * Diese Methode stellt eine Verbindung zum MensaplanServer her, lädt die gewünschten Daten herunter und gibt sie als ClientData-Objekt zurück.
	 * 
	 * @return das ClientData-Objekt
	 * @throws IOException
	 *             wenn die Verbindung mit dem Server nicht aufgebaut werden konnte oder Fehler beim Lesen auftreten.
	 * @throws URISyntaxException
	 */
	private static ClientData getClientDataFromServer() throws IOException, URISyntaxException {
		// Prepare Connection //
		URL url = new URL(urlToServer);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		// Read Stream //
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String result = "";
		String currentLine = "";
		while((currentLine = br.readLine()) != null) {
			result += currentLine;
		}
		Log.d("MensaplanData", "" + result);
		br.close();

		// Get the ClientData
		Gson gson = new Gson();
		ClientData o = gson.fromJson(result, ClientData.class);
		if(o == null) new ClientData();
		return o;

		// HttpClient httpclient = new DefaultHttpClient();
		// HttpResponse response = httpclient.execute(new HttpGet(urlToServer));
		// StatusLine statusLine = response.getStatusLine();
		// if(statusLine.getStatusCode() == HttpStatus.SC_OK) {
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// response.getEntity().writeTo(out);
		// String responseString = out.toString();
		// out.close();
		// // ..more logic
		// Gson gson = new Gson();
		// ClientData o = gson.fromJson(responseString, ClientData.class);
		// if(o == null) new ClientData();
		// return o;
		// } else {
		// // Closes the connection.
		// response.getEntity().getContent().close();
		// throw new IOException(statusLine.getReasonPhrase());
		// }
	}
}
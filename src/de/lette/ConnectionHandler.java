package de.lette;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			IOException, URISyntaxException, ParseException {
		// data = getClientDataFromServer();
		if (tagesPläne != null)
			return tagesPläne;
		ClientData data = new ClientData();

		Set<de.lette.mensaplan.server.Speise> alleSpeisen = new LinkedHashSet<de.lette.mensaplan.server.Speise>();
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(1, "Möhren-Sellerie Cremesuppe", SpeiseArt.VORSPEISE, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(2, "Kartoffel-Zucchini Puffer mit Kräuterquark", SpeiseArt.VORSPEISE, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(3, "Kräutercremesuppe", SpeiseArt.VORSPEISE, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(4, "Blätterteigtasche mit Gemüsefüllung und Tomatensauce", SpeiseArt.VORSPEISE, false, "", 200, 200, 200, 200, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(5, "Chinakohlsalat", SpeiseArt.VORSPEISE,true, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(6, "Bruschetta", SpeiseArt.VORSPEISE,true, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(7, "Chicoreesalat", SpeiseArt.VORSPEISE,true, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(8, "Tomatencremesuppe", SpeiseArt.VORSPEISE,true, "", 100, 100, 100, 100, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(9, "Gemüsenudeln mit Käsesauce", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(10, "Milchreis mit Erdbeerkompott", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(11, "Gnocchi mit Tomaten-Basilikumsauce", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(12, "Gemüsepfanne mit Joghurt-Kräuterdip", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(13, "Spaghetti mit Gemüsebolognese", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));	
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(14, "Schweinegulasch mit gemischtem Gemüse", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(15, "Mangoldlasagne mit Champignons", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(16, "Seelachsfilet mit Senfdip und gemischtem Salat", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(17, "Hühnerfrikassee", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));

		alleSpeisen.add(new de.lette.mensaplan.server.Speise(18, "Hackbraten mit Schwarzwurzeln, Kartoffelpüree", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(20, "grüne Bohneneintopf mit Fleischeinlage", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(21, "Tilapia mit Kartoffelsalat und Dipp", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(22, "Putenbrust mit Ananas und Käse, Reis und Salat", SpeiseArt.LEICHTEVOLLKOST, true, "", 200, 200, 200, 200, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(24, "Kartoffelpüree", SpeiseArt.BEILAGEN,false, "", 100, 100, 100, 100, 10, 2));		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(25, "Petersilienkartoffeln", SpeiseArt.BEILAGEN,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(26, "Reis", SpeiseArt.BEILAGEN,false, "", 100, 100, 100, 100, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(27, "Erbsen, Schwarzwurzeln", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(29, "Bohneneintopf", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(30, "Kürbis, Broccoli", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(32, "Linsencurry", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(33, "Mango-Limettencreme", SpeiseArt.DESSERT, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(34, "Mandelcreme mit Lychees", SpeiseArt.DESSERT, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(35, "Quark mit Pfirsich", SpeiseArt.DESSERT, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(36, "Quark mit Himbeeren", SpeiseArt.DESSERT, false, "", 200, 200, 200, 200, 10, 2));

		alleSpeisen.add(new de.lette.mensaplan.server.Speise(37, "Grießflammeri mit Mandarinen", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(38, "Obstsalat", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(39, "Kirschgrütze", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(40, "Pfirsichquarkspeise", SpeiseArt.DESSERT, true, "", 200, 200, 200, 200, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(41, "Tomaten mit Mozzarella und Baguettebrot", SpeiseArt.VORSPEISE,false , "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(42, "Salatteller mit Tunfisch und Ei", SpeiseArt.VORSPEISE,false , "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(43, "Kräutercremesuppe", SpeiseArt.VORSPEISE,false , "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(44, "Toast Hawaii", SpeiseArt.VORSPEISE,false , "", 100, 100, 100, 100, 10, 2));

		alleSpeisen.add(new de.lette.mensaplan.server.Speise(45, "Pilzfrikadellen mit Käsesauce und grünen Bohnen", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(46, "Kartoffelpizza mit Tomaten und Schafkäse", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(47, "Kräuterquark mit Schwenkkartoffeln und Tomatensalat", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(48, "Gemüsecurry mit Kokosmilch", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(49, "Gemüse-Nudelauflauf", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(50, "Milchreis mit Kirschkompott", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(51, "Buletten mit Schafkäse und Oliven gefüllt, gemischtes Gemüse", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(53, "Hähnchenbrust mit Wirsing-Möhren-Gemüse", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(54, "Bratwurst mit gemischtem Gemüse", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(55, "Quark mit Mandarinen", SpeiseArt.DESSERT, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(56, "Joghurt mit gemischtem Obst", SpeiseArt.DESSERT, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(57, "Mandelflammeri mit Kiwi-Erdbeersauce", SpeiseArt.DESSERT, false, "", 200, 200, 200, 200, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(58, "Tomatensalat", SpeiseArt.VORSPEISE,true, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(59, "Salatteller", SpeiseArt.VORSPEISE,true, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(60, "Waldorfsalat", SpeiseArt.VORSPEISE,true, "", 100, 100, 100, 100, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(61, "Kotelette mit Kartoffelpüre, Prinzessbohnen", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(62, "Seelachsfilet nach brasilianischer Art, Reis", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(63, "Broccoli-Hackfleisch-Auflauf mit Tomatensauce", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(64, "Spinat mit Ei und Salzkartoffeln", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(65, "Prinzessbohnen, Apfel-Zwibelgemüse", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(66, "Paprika, Champignons", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(67, "Selleriemus, Broccoli", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(68, "Spinat, Pastinaken", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(69, "Birne Helene", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(70, "Mangomus", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(71, "Joghurt mit Kirschen", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(72, "Quarkspeise mit Erdbeeren", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(73, "Blumenkohl-Cremesuppe", SpeiseArt.VORSPEISE,false , "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(74, "Kartoffel-Cremesuppe", SpeiseArt.VORSPEISE,false , "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(75, "Gemüse-Cremesuppe", SpeiseArt.VORSPEISE,false , "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(76, "Sauer-Scharfsuppe", SpeiseArt.VORSPEISE,false , "", 100, 100, 100, 100, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(77, "Chop Suey", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(78, "Tortellini mit Käse-Kräutersauce und Möhrenrohkost", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(79, "Falafel mit Joghurtdip und gemischtem Salat", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(80, "Nudeln mit Champignonsauce und Salat", SpeiseArt.VEGETARISCH,false, "", 100, 100, 100, 100, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(81, "Seelachsfilet mit Kartoffelsalat und Dip", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(82, "Hähnchenkeule mit gemischtem Gemüse", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(83, "Fleischbällchen mit Senfsauce und Broccoli", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(84, "Reispuffer mit Kirschkompott", SpeiseArt.VOLLKOST, false, "", 200, 200, 200, 200, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(85, "Basmatireis", SpeiseArt.BEILAGEN,false, "", 100, 100, 100, 100, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(86, "Vanilleflammeri mit Erdbeersauce", SpeiseArt.DESSERT, false, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(87, "Grieß mit Beerencocktail", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(88, "Marmorierter Flammeri", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(89, "Vanilleflammeri mit Brombeeren", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));

		alleSpeisen.add(new de.lette.mensaplan.server.Speise(90, "Kürbiscremesuppe", SpeiseArt.VORSPEISE,true , "", 100, 100, 100, 100, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(91, "Kopfsalat", SpeiseArt.VORSPEISE,true , "", 100, 100, 100, 100, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(92, "Rindergulasch mit Spiralnudeln und Salat", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(93, "gebr. Tilapia mit Boullionkartoffeln, Kräutersauce", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(94, "Hühnerfrikassee mit Spargel und Champignons", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(95, "Steckrübeneintopf mit Schweinefleisch", SpeiseArt.LEICHTEVOLLKOST, true, "", 9000, 9000, 9000, 9000, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(96, "Sauerkraut, Möhren", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(97, "Bouillonkartoffeln", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(98, "Champignons, grüne Bohnen", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(99, "Steckrübeneintopf", SpeiseArt.GEMÜSETELLER, true, "", 9000, 9000, 9000, 9000, 10, 2));
		
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(100, "Joghurt mit Himbeeren", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(101, "Erdbeergrütze", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(102, "Ananas mit Schokosauce", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		alleSpeisen.add(new de.lette.mensaplan.server.Speise(103, "Quark mit Aprikosen", SpeiseArt.DESSERT, true, "", 0, 0, 0, 0, 10, 2));
		
		data.setSpeisen(alleSpeisen);

		Set<Termin> termine = new LinkedHashSet<Termin>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy",Locale.GERMANY);
		
		/*2.3.2015*/
		termine.add(new Termin(1, new Date(sdf.parse("02.03.2015").getTime()), 1, new BigDecimal(1.00), false));
		termine.add(new Termin(2, new Date(sdf.parse("02.03.2015").getTime()), 9, new BigDecimal(1.00), false));
		termine.add(new Termin(3, new Date(sdf.parse("02.03.2015").getTime()), 14, new BigDecimal(1.00), false));
		termine.add(new Termin(4, new Date(sdf.parse("02.03.2015").getTime()), 24, new BigDecimal(1.00), false));
		termine.add(new Termin(5, new Date(sdf.parse("02.03.2015").getTime()), 25, new BigDecimal(1.00), false));
		termine.add(new Termin(6, new Date(sdf.parse("02.03.2015").getTime()), 33, new BigDecimal(1.00), false));
		
		termine.add(new Termin(7, new Date(sdf.parse("02.03.2015").getTime()), 5, new BigDecimal(0.70), true));
		termine.add(new Termin(8, new Date(sdf.parse("02.03.2015").getTime()), 18, new BigDecimal(3.50), true));
		termine.add(new Termin(10, new Date(sdf.parse("02.03.2015").getTime()), 27, new BigDecimal(2.50), true));
		termine.add(new Termin(12, new Date(sdf.parse("02.03.2015").getTime()), 37, new BigDecimal(0.70), true));
		
		/*3.3.2015*/
		termine.add(new Termin(13, new Date(sdf.parse("03.03.2015").getTime()), 2, new BigDecimal(1.00), false));
		termine.add(new Termin(14, new Date(sdf.parse("03.03.2015").getTime()), 10, new BigDecimal(1.00), false));
		termine.add(new Termin(15, new Date(sdf.parse("03.03.2015").getTime()), 11, new BigDecimal(1.00), false));
		termine.add(new Termin(16, new Date(sdf.parse("03.03.2015").getTime()), 15, new BigDecimal(1.00), false));
		termine.add(new Termin(17, new Date(sdf.parse("03.03.2015").getTime()), 34, new BigDecimal(1.00), false));
		
		termine.add(new Termin(18, new Date(sdf.parse("03.03.2015").getTime()), 6, new BigDecimal(0.70), true));
		termine.add(new Termin(19, new Date(sdf.parse("03.03.2015").getTime()), 20, new BigDecimal(3.50), true));
		termine.add(new Termin(20, new Date(sdf.parse("03.03.2015").getTime()), 29, new BigDecimal(2.50), true));
		termine.add(new Termin(21, new Date(sdf.parse("03.03.2015").getTime()), 38, new BigDecimal(0.70), true));
		
		/*4.3.2015*/
		termine.add(new Termin(22, new Date(sdf.parse("04.03.2015").getTime()), 3, new BigDecimal(1.00), false));
		termine.add(new Termin(23, new Date(sdf.parse("04.03.2015").getTime()), 12, new BigDecimal(1.00), false));
		termine.add(new Termin(24, new Date(sdf.parse("04.03.2015").getTime()), 16, new BigDecimal(1.00), false));
		termine.add(new Termin(25, new Date(sdf.parse("04.03.2015").getTime()), 25, new BigDecimal(1.00), false));
		termine.add(new Termin(26, new Date(sdf.parse("04.03.2015").getTime()), 26, new BigDecimal(1.00), false));
		termine.add(new Termin(27, new Date(sdf.parse("04.03.2015").getTime()), 35, new BigDecimal(1.00), false));
		
		termine.add(new Termin(28, new Date(sdf.parse("04.03.2015").getTime()), 7, new BigDecimal(0.70), true));
		termine.add(new Termin(29, new Date(sdf.parse("04.03.2015").getTime()), 21, new BigDecimal(3.50), true));
		termine.add(new Termin(31, new Date(sdf.parse("04.03.2015").getTime()), 30, new BigDecimal(2.50), true));
		termine.add(new Termin(32, new Date(sdf.parse("04.03.2015").getTime()), 39, new BigDecimal(0.70), true));
		
		/*5.3.2015*/
		termine.add(new Termin(33, new Date(sdf.parse("05.03.2015").getTime()), 4, new BigDecimal(1.00), false));
		termine.add(new Termin(34, new Date(sdf.parse("05.03.2015").getTime()), 13, new BigDecimal(1.00), false));
		termine.add(new Termin(35, new Date(sdf.parse("05.03.2015").getTime()), 17, new BigDecimal(1.00), false));
		termine.add(new Termin(36, new Date(sdf.parse("05.03.2015").getTime()), 25, new BigDecimal(1.00), false));
		termine.add(new Termin(37, new Date(sdf.parse("05.03.2015").getTime()), 26, new BigDecimal(1.00), false));
		termine.add(new Termin(38, new Date(sdf.parse("05.03.2015").getTime()), 36, new BigDecimal(1.00), false));

		termine.add(new Termin(39, new Date(sdf.parse("05.03.2015").getTime()), 8, new BigDecimal(0.70), true));
		termine.add(new Termin(40, new Date(sdf.parse("05.03.2015").getTime()), 22, new BigDecimal(3.50), true));
		termine.add(new Termin(42, new Date(sdf.parse("05.03.2015").getTime()), 32, new BigDecimal(2.50), true));
		termine.add(new Termin(43, new Date(sdf.parse("05.03.2015").getTime()), 40, new BigDecimal(0.70), true));
		
		/*16.3.2015*/
		termine.add(new Termin(44, new Date(sdf.parse("16.3.2015").getTime()), 41, new BigDecimal(1.00), false));
		termine.add(new Termin(45, new Date(sdf.parse("16.3.2015").getTime()), 45, new BigDecimal(1.00), false));
		termine.add(new Termin(46, new Date(sdf.parse("16.3.2015").getTime()), 49, new BigDecimal(1.00), false));
		termine.add(new Termin(47, new Date(sdf.parse("16.3.2015").getTime()), 50, new BigDecimal(1.00), false));
		termine.add(new Termin(48, new Date(sdf.parse("16.3.2015").getTime()), 24, new BigDecimal(1.00), false));
		termine.add(new Termin(49, new Date(sdf.parse("16.3.2015").getTime()), 55, new BigDecimal(1.00), false));
		
		termine.add(new Termin(50, new Date(sdf.parse("16.3.2015").getTime()), 58, new BigDecimal(0.70), true));
		termine.add(new Termin(51, new Date(sdf.parse("16.3.2015").getTime()), 61, new BigDecimal(3.50), true));
		termine.add(new Termin(52, new Date(sdf.parse("16.3.2015").getTime()), 65, new BigDecimal(2.50), true));
		termine.add(new Termin(53, new Date(sdf.parse("16.3.2015").getTime()), 69, new BigDecimal(0.70), true));
		
		/*17.3.2015*/
		termine.add(new Termin(54, new Date(sdf.parse("17.3.2015").getTime()), 42, new BigDecimal(1.00), false));
		termine.add(new Termin(55, new Date(sdf.parse("17.3.2015").getTime()), 46, new BigDecimal(1.00), false));
		termine.add(new Termin(56, new Date(sdf.parse("17.3.2015").getTime()), 51, new BigDecimal(1.00), false));
		termine.add(new Termin(57, new Date(sdf.parse("17.3.2015").getTime()), 24, new BigDecimal(1.00), false));
		termine.add(new Termin(58, new Date(sdf.parse("17.3.2015").getTime()), 25, new BigDecimal(1.00), false));
		termine.add(new Termin(59, new Date(sdf.parse("17.3.2015").getTime()), 56, new BigDecimal(1.00), false));
		
		termine.add(new Termin(48, new Date(sdf.parse("17.3.2015").getTime()), 59, new BigDecimal(0.70), true));
		termine.add(new Termin(49, new Date(sdf.parse("17.3.2015").getTime()), 62, new BigDecimal(3.50), true));
		termine.add(new Termin(50, new Date(sdf.parse("17.3.2015").getTime()), 66, new BigDecimal(2.50), true));
		termine.add(new Termin(51, new Date(sdf.parse("17.3.2015").getTime()), 70, new BigDecimal(0.70), true));
		
		/*18.3.2015*/
		termine.add(new Termin(52, new Date(sdf.parse("18.3.2015").getTime()), 3, new BigDecimal(1.00), false));
		termine.add(new Termin(53, new Date(sdf.parse("18.3.2015").getTime()), 47, new BigDecimal(1.00), false));
		termine.add(new Termin(54, new Date(sdf.parse("18.3.2015").getTime()), 53, new BigDecimal(1.00), false));
		termine.add(new Termin(55, new Date(sdf.parse("18.3.2015").getTime()), 24, new BigDecimal(1.00), false));
		termine.add(new Termin(56, new Date(sdf.parse("18.3.2015").getTime()), 25, new BigDecimal(1.00), false));
		termine.add(new Termin(57, new Date(sdf.parse("18.3.2015").getTime()), 100, new BigDecimal(1.00), false));
		
		termine.add(new Termin(58, new Date(sdf.parse("18.3.2015").getTime()), 60, new BigDecimal(0.70), true));
		termine.add(new Termin(59, new Date(sdf.parse("18.3.2015").getTime()), 63, new BigDecimal(3.50), true));
		termine.add(new Termin(60, new Date(sdf.parse("18.3.2015").getTime()), 67, new BigDecimal(2.50), true));
		termine.add(new Termin(61, new Date(sdf.parse("18.3.2015").getTime()), 71, new BigDecimal(0.70), true));
		
		/*20.3.2015*/
		termine.add(new Termin(62, new Date(sdf.parse("20.3.2015").getTime()), 44, new BigDecimal(1.00), false));
		termine.add(new Termin(63, new Date(sdf.parse("20.3.2015").getTime()), 48, new BigDecimal(1.00), false));
		termine.add(new Termin(64, new Date(sdf.parse("20.3.2015").getTime()), 54, new BigDecimal(1.00), false));
		termine.add(new Termin(65, new Date(sdf.parse("20.3.2015").getTime()), 24, new BigDecimal(1.00), false));
		termine.add(new Termin(66, new Date(sdf.parse("20.3.2015").getTime()), 25, new BigDecimal(1.00), false));
		termine.add(new Termin(67, new Date(sdf.parse("20.3.2015").getTime()), 57, new BigDecimal(1.00), false));
		
		termine.add(new Termin(68, new Date(sdf.parse("20.3.2015").getTime()), 7, new BigDecimal(0.70), true));
		termine.add(new Termin(69, new Date(sdf.parse("20.3.2015").getTime()), 64, new BigDecimal(3.50), true));
		termine.add(new Termin(70, new Date(sdf.parse("20.3.2015").getTime()), 68, new BigDecimal(2.50), true));
		termine.add(new Termin(71, new Date(sdf.parse("20.3.2015").getTime()), 72, new BigDecimal(0.70), true));
		
		/*24.3.2015*/
		termine.add(new Termin(72, new Date(sdf.parse("24.3.2015").getTime()), 73, new BigDecimal(1.00), false));
		termine.add(new Termin(73, new Date(sdf.parse("24.3.2015").getTime()), 77, new BigDecimal(1.00), false));
		termine.add(new Termin(74, new Date(sdf.parse("24.3.2015").getTime()), 81, new BigDecimal(1.00), false));
		termine.add(new Termin(75, new Date(sdf.parse("24.3.2015").getTime()), 85, new BigDecimal(1.00), false));
		termine.add(new Termin(76, new Date(sdf.parse("24.3.2015").getTime()), 86, new BigDecimal(1.00), false));
		
		termine.add(new Termin(77, new Date(sdf.parse("24.3.2015").getTime()), 90, new BigDecimal(0.70), true));
		termine.add(new Termin(78, new Date(sdf.parse("24.3.2015").getTime()), 92, new BigDecimal(3.50), true));
		termine.add(new Termin(79, new Date(sdf.parse("24.3.2015").getTime()), 96, new BigDecimal(2.50), true));
		termine.add(new Termin(80, new Date(sdf.parse("24.3.2015").getTime()), 100, new BigDecimal(0.70), true));

		/*25.3.2015*/
		termine.add(new Termin(81, new Date(sdf.parse("25.3.2015").getTime()), 74, new BigDecimal(1.00), false));
		termine.add(new Termin(82, new Date(sdf.parse("25.3.2015").getTime()), 78, new BigDecimal(1.00), false));
		termine.add(new Termin(83, new Date(sdf.parse("25.3.2015").getTime()), 82, new BigDecimal(1.00), false));
		termine.add(new Termin(84, new Date(sdf.parse("25.3.2015").getTime()), 25, new BigDecimal(1.00), false));
		termine.add(new Termin(85, new Date(sdf.parse("25.3.2015").getTime()), 87, new BigDecimal(1.00), false));
		
		termine.add(new Termin(86, new Date(sdf.parse("25.3.2015").getTime()), 91, new BigDecimal(0.70), true));
		termine.add(new Termin(87, new Date(sdf.parse("25.3.2015").getTime()), 93, new BigDecimal(3.50), true));
		termine.add(new Termin(88, new Date(sdf.parse("25.3.2015").getTime()), 97, new BigDecimal(2.50), true));
		termine.add(new Termin(89, new Date(sdf.parse("25.3.2015").getTime()), 101, new BigDecimal(0.70), true));

		/*25.3.2015*/
		termine.add(new Termin(90, new Date(sdf.parse("25.3.2015").getTime()), 75, new BigDecimal(1.00), false));
		termine.add(new Termin(91, new Date(sdf.parse("25.3.2015").getTime()), 79, new BigDecimal(1.00), false));
		termine.add(new Termin(92, new Date(sdf.parse("25.3.2015").getTime()), 83, new BigDecimal(1.00), false));
		termine.add(new Termin(93, new Date(sdf.parse("25.3.2015").getTime()), 24, new BigDecimal(1.00), false));
		termine.add(new Termin(94, new Date(sdf.parse("25.3.2015").getTime()), 25, new BigDecimal(1.00), false));
		termine.add(new Termin(95, new Date(sdf.parse("25.3.2015").getTime()), 88, new BigDecimal(1.00), false));
		
		termine.add(new Termin(96, new Date(sdf.parse("25.3.2015").getTime()), 58, new BigDecimal(0.70), true));
		termine.add(new Termin(97, new Date(sdf.parse("25.3.2015").getTime()), 94, new BigDecimal(3.50), true));
		termine.add(new Termin(98, new Date(sdf.parse("25.3.2015").getTime()), 98, new BigDecimal(2.50), true));
		termine.add(new Termin(99, new Date(sdf.parse("25.3.2015").getTime()), 102, new BigDecimal(0.70), true));

		/*26.3.2015*/
		termine.add(new Termin(100, new Date(sdf.parse("26.3.2015").getTime()), 76, new BigDecimal(1.00), false));
		termine.add(new Termin(101, new Date(sdf.parse("26.3.2015").getTime()), 80, new BigDecimal(1.00), false));
		termine.add(new Termin(102, new Date(sdf.parse("26.3.2015").getTime()), 84, new BigDecimal(1.00), false));
		termine.add(new Termin(103, new Date(sdf.parse("26.3.2015").getTime()), 89, new BigDecimal(1.00), false));
		
		termine.add(new Termin(86, new Date(sdf.parse("26.3.2015").getTime()), 59, new BigDecimal(0.70), true));
		termine.add(new Termin(87, new Date(sdf.parse("26.3.2015").getTime()), 95, new BigDecimal(3.50), true));
		termine.add(new Termin(88, new Date(sdf.parse("26.3.2015").getTime()), 99, new BigDecimal(2.50), true));
		termine.add(new Termin(89, new Date(sdf.parse("26.3.2015").getTime()), 103, new BigDecimal(0.70), true));
		
		data.setTermine(termine);

		Set<Zusatzstoff> zusätze = new LinkedHashSet<Zusatzstoff>();
		zusätze.add(new Zusatzstoff(1, 1, "Konservierungsmittel"));
		zusätze.add(new Zusatzstoff(2, 2, "Antioxidationsmittel"));
		zusätze.add(new Zusatzstoff(3, 3, "Farbstoff"));
		zusätze.add(new Zusatzstoff(4, 4, "Geschmacksverstärker, u.a. Natriumglutamat"));
		zusätze.add(new Zusatzstoff(5, 5, "Schwefel"));
		zusätze.add(new Zusatzstoff(6, 6, "Phosphat"));
		zusätze.add(new Zusatzstoff(7, 7, "geschwärzt"));
		zusätze.add(new Zusatzstoff(8, 8, "gewachst"));
		zusätze.add(new Zusatzstoff(9, 9, "Süßungsmittel, u.a. Saccarin, Cyclamat, Aspartam"));
		zusätze.add(new Zusatzstoff(10, 10, "enthält eine Phenylalaninquelle"));
		zusätze.add(new Zusatzstoff(11, 11, "Säuerungsmittel"));
		zusätze.add(new Zusatzstoff(12, 12, "Hühnereiweiß"));
		zusätze.add(new Zusatzstoff(13, 13, "Jodsalz"));
		zusätze.add(new Zusatzstoff(14, 14, "Nitritpökelsalz"));
		zusätze.add(new Zusatzstoff(15, 15, "Milcheiweiß ( Kuhmilch und Kuhmilcherzeugnisse)"));
		zusätze.add(new Zusatzstoff(16, 16, "Hefeextrakt"));
		zusätze.add(new Zusatzstoff(17, 17, "Aroma"));
		zusätze.add(new Zusatzstoff(18, 18, "Zuckerarten und /oder Süßungsmittel"));
		zusätze.add(new Zusatzstoff(19, 19, "Gluten"));
		
		zusätze.add(new Zusatzstoff(20, 20, "Glutenhaltiges Getreide/ Erzeugnisse ( Weizen, Roggen, Gerste, Hafer, Dinkel)"));
		zusätze.add(new Zusatzstoff(21, 21, "Soja und Sojaerzeugnisse"));
		zusätze.add(new Zusatzstoff(22, 22, "Sellerie und Sellerieerzeugnisse"));
		zusätze.add(new Zusatzstoff(23, 23, "Senf und Senfsaaten"));
		zusätze.add(new Zusatzstoff(24, 24, "Sesamsaaten und Sesamsamenerzeugnisse"));
		zusätze.add(new Zusatzstoff(25, 25, "Lupine und Lupinenerzeugnisse"));
		zusätze.add(new Zusatzstoff(26, 26, "Erdnüsse und Erdnusserzeugnisse"));
		zusätze.add(new Zusatzstoff(27, 27, "Fisch und Fischerzeugnisse"));
		zusätze.add(new Zusatzstoff(28, 28, "Krebstiere und Krebserzeugnisse u.a. Krabben, Garnelen, Hummer"));
		zusätze.add(new Zusatzstoff(29, 29, "Weichtiere und Weichtiererzeugnisse u.a. Muscheln, Schnecken"));
		zusätze.add(new Zusatzstoff(30, 30, "Schalenfrüchte und Schalenfruchterzeugnisse(z.B. Mandeln, Haselnüsse)"));
		zusätze.add(new Zusatzstoff(31, 31, "Eier und Eiererzeugnisse "));
		zusätze.add(new Zusatzstoff(32, 32, "Milch und Milcherzeugnisse (incl. Lactose)"));
		zusätze.add(new Zusatzstoff(33, 33, "Schwefeldioxid und Sulfite"));
		data.setZusatzstoffe(zusätze);

		tagesPläne = new ArrayList<Tagesplan>();
		for(Entry<java.util.Date, Map<de.lette.mensaplan.server.Speise, Termin>> dateMap : data.getSpeisenForDate().entrySet()) {
			// Normalerweise ist jedes Date-Objekt ein Tag, es kann jedoch auch vorkommen, dass 2 Date-Objekte 1 Tag sind.
			Tagesplan tagesPlan = new Tagesplan(dateMap.getKey());
			for(Entry<de.lette.mensaplan.server.Speise, Termin> dataMap : dateMap.getValue().entrySet()) {
				de.lette.mensaplan.server.Speise s = dataMap.getKey();
				Speise speise = new Speise(s.getName(), s.getArt(), s.isDiät(), dataMap.getValue().getPreis(), s.getBeachte(), s.getKcal(), s.getEiweiß(), s.getFett(), s.getKohlenhydrate(), data.getZusatzstoffe(s), s.getLikes(), s.getDislikes());
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
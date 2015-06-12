/**
 *  Copyright 2013 Diego Ceccarelli
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package it.cnr.isti.hpc.wikipedia.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Models the locale for a language. 
 * 
 * @author Diego Ceccarelli <diego.ceccarelli@isti.cnr.it>
 * 
 *         Created on Feb 14, 2013
 */
public class Locale {

	private Properties properties;

	private static final Logger logger = LoggerFactory.getLogger(Locale.class);
	private static final String SEPARATOR = ",";
	private static Map<String, String> langMap = new HashMap<String, String>();

	public Locale(String lang) {
		properties = new Properties();
		
		try {
			properties.load(Locale.class.getResourceAsStream("/lang/locale-"
					+ lang + ".properties"));
		} catch (IOException e) {
			logger.error("readling the locale for language {} ({})", lang,
					e.toString());
			//Commenting the exit statement to not fail the program if there are no 
			//corresponding language files
			//System.exit(-1);
		}
		
		logger.info("using {} language ",properties.get("language"));
		langMap = insertRedirectSettings();

	}
	
	public Map<String, String> insertRedirectSettings(){
		
		Map<String, String> m = new HashMap<String, String>();
		
		m.put("af", "#AANSTUUR|#REDIRECT");
        m.put("ar", "#تحويل|#REDIRECT");
        m.put("arz", "#تحويل|#تحويل|#REDIRECT");
        m.put("be-tarask", "#перанакіраваньне|#REDIRECT");
        m.put("bg", "#пренасочване|#виж|#REDIRECT");
        m.put("br", "#ADKAS|#REDIRECT");
        m.put("bs", "#PREUSMJERI|#REDIRECT");
        m.put("cs", "#REDIRECT|#PŘESMĚRUJ");
        m.put("cu", "#ПРѢНАПРАВЛЄНИѤ|#REDIRECT");
        m.put("cy", "#ail-cyfeirio|#ailgyfeirio|#REDIRECT");
        m.put("de", "#WEITERLEITUNG|#REDIRECT");
        m.put("el", "#ΑΝΑΚΑΤΕΥΘΥΝΣΗ|#REDIRECT");
        m.put("en", "#REDIRECT");
        m.put("eo", "#ALIDIREKTU|#REDIRECT");
        m.put("es", "#REDIRECCIÓN|#REDIRECT");
        m.put("et", "#suuna|#REDIRECT");
        m.put("eu", "#BIRZUZENDU|#REDIRECT");
        m.put("fa", "#تغییرمسیر|#REDIRECT");
        m.put("fi", "#OHJAUS|#UUDELLEENOHJAUS|#REDIRECT");
        m.put("fr", "#REDIRECTION|#REDIRECT");
        m.put("ga", "#redirect|#athsheoladh");
        m.put("gl", "#REDIRECCIÓN|#REDIRECT");
        m.put("he", "#הפניה|#REDIRECT");
        m.put("hr", "#PREUSMJERI|#REDIRECT");
        m.put("hu", "#ÁTIRÁNYÍTÁS|#REDIRECT");
        m.put("hy", "#REDIRECT|#ՎԵՐԱՀՂՈՒՄ");
        m.put("id", "#ALIH|#REDIRECT");
        m.put("is", "#tilvísun|#TILVÍSUN|#REDIRECT");
        m.put("ja", "#転送|#リダイレクト|＃転送|＃リダイレクト|#REDIRECT");
        m.put("ka", "#REDIRECT|#გადამისამართება");
        m.put("kk-arab", "#REDIRECT|#ايداۋ");
        m.put("kk-cyrl", "#REDIRECT|#АЙДАУ");
        m.put("kk-latn", "#REDIRECT|#AÝDAW");
        m.put("km", "#បញ្ជូនបន្ត|#ប្ដូរទីតាំងទៅ #ប្តូរទីតាំងទៅ|#ប្ដូរទីតាំង|#ប្តូរទីតាំង|#ប្ដូរចំណងជើង|#REDIRECT");
        m.put("ko", "#넘겨주기|#REDIRECT");
        m.put("ksh", "#ÖMLEIDUNG|#REDIRECT");
        m.put("lt", "#PERADRESAVIMAS|#REDIRECT");
        m.put("mk", "#пренасочување|#види|#Пренасочување|#ПРЕНАСОЧУВАЊЕ|#REDIRECT");
        m.put("ml", "#REDIRECT|#തിരിച്ചുവിടുക|തിരിച്ചുവിടല്‍");
        m.put("mr", "#पुनर्निर्देशन|#REDIRECT");
        m.put("mt", "#RINDIRIZZA|#REDIRECT");
        m.put("mwl", "#ANCAMINAR|#REDIRECT");
        m.put("nds", "#redirect|#wiederleiden");
        m.put("nds-nl", "#DEURVERWIEZING|#DOORVERWIJZING|#REDIRECT");
        m.put("nl", "#DOORVERWIJZING|#REDIRECT");
        m.put("nn", "#omdiriger|#REDIRECT");
        m.put("oc", "#REDIRECCION|#REDIRECT");
        m.put("pl", "#PATRZ|#PRZEKIERUJ|#TAM|#REDIRECT");
        m.put("pt", "#REDIRECIONAMENTO|#REDIRECT");
        m.put("ro", "#REDIRECTEAZA|#REDIRECT");
        m.put("ru", "#перенаправление|#перенапр|#REDIRECT");
        m.put("sa", "#पुनर्निदेशन|#REDIRECT");
        m.put("sd", "#چوريو|#REDIRECT");
        m.put("si", "#යළියොමුව|#REDIRECT");
        m.put("sk", "#presmeruj|#REDIRECT");
        m.put("sq", "#RIDREJTO|#REDIRECT");
        m.put("srn", "#STIR|#DOORVERWIJZING|#REDIRECT");
        m.put("sr-ec", "#Преусмери|#redirect|#преусмери|#ПРЕУСМЕРИ");
        m.put("sr-el", "#Preusmeri|#redirect|#preusmeri|#PREUSMERI");
        m.put("sv", "#OMDIRIGERING|#REDIRECT");
        m.put("ta", "#வழிமாற்று|#REDIRECT");
        m.put("te", "#దారిమార్పు|#REDIRECT");
        m.put("th", "#เปลี่ยนทาง|#REDIRECT");
        m.put("tr", "#YÖNLENDİRME|#REDIRECT");
        m.put("tt-latn", "#yünältü|#REDIRECT");
        m.put("uk", "#ПЕРЕНАПРАВЛЕННЯ|#ПЕРЕНАПР|#перенаправление|#перенапр|#REDIRECT");
        m.put("vi", "#đổi|#đổi|#REDIRECT");
        m.put("vro", "#saadaq|#suuna|#REDIRECT");
        m.put("yi", "#ווייטערפירן|#הפניה|#REDIRECT");
        m.put("ab", "#перенаправление|#перенапр|#REDIRECT");
        m.put("ace", "#ALIH|#REDIRECT");
        m.put("aln", "#RIDREJTO|#REDIRECT");
        m.put("als", "#WEITERLEITUNG|#REDIRECT");
        m.put("an", "#REDIRECCIÓN|#REDIRECT");
        m.put("arn", "#REDIRECCIÓN|#REDIRECT");
        m.put("av", "#перенаправление|#перенапр|#REDIRECT");
        m.put("ay", "#REDIRECCIÓN|#REDIRECT");
        m.put("ba", "#перенаправление|#перенапр|#REDIRECT");
        m.put("bar", "#WEITERLEITUNG|#REDIRECT");
        m.put("bat-smg", "#PERADRESAVIMAS|#REDIRECT");
        m.put("bcc", "#تغییرمسیر|#REDIRECT");
        m.put("be-x-old", "#перанакіраваньне|#REDIRECT");
        m.put("bm", "#REDIRECTION|#REDIRECT");
        m.put("bqi", "#تغییرمسیر|#REDIRECT");
        m.put("bug", "#ALIH|#REDIRECT");
        m.put("cbk-zam", "#REDIRECCIÓN|#REDIRECT");
        m.put("ce", "#перенаправление|#перенапр|#REDIRECT");
        m.put("crh-cyrl", "#перенаправление|#перенапр|#REDIRECT");
        m.put("cv", "#перенаправление|#перенапр|#REDIRECT");
        m.put("de-at", "#WEITERLEITUNG|#REDIRECT");
        m.put("de-ch", "#WEITERLEITUNG|#REDIRECT");
        m.put("de-formal", "#WEITERLEITUNG|#REDIRECT");
        m.put("dsb", "#WEITERLEITUNG|#REDIRECT");
        m.put("ff", "#REDIRECTION|#REDIRECT");
        m.put("fiu-vro", "#saadaq|#suuna|#REDIRECT");
        m.put("frp", "#REDIRECTION|#REDIRECT");
        m.put("gag", "#YÖNLENDİRME|#REDIRECT");
        m.put("glk", "#تغییرمسیر|#REDIRECT");
        m.put("gn", "#REDIRECCIÓN|#REDIRECT");
        m.put("gsw", "#WEITERLEITUNG|#REDIRECT");
        m.put("hsb", "#WEITERLEITUNG|#REDIRECT");
        m.put("ht", "#REDIRECTION|#REDIRECT");
        m.put("inh", "#перенаправление|#перенапр|#REDIRECT");
        m.put("jv", "#ALIH|#REDIRECT");
        m.put("kaa", "#REDIRECT|#AÝDAW");
        m.put("kk", "#REDIRECT|#АЙДАУ");
        m.put("kk-cn", "#REDIRECT|#ايداۋ");
        m.put("kk-kz", "#REDIRECT|#АЙДАУ");
        m.put("kk-tr", "#REDIRECT|#AÝDAW");
        m.put("kv", "#перенаправление|#перенапр|#REDIRECT");
        m.put("lad", "#REDIRECCIÓN|#REDIRECT");
        m.put("lb", "#WEITERLEITUNG|#REDIRECT");
        m.put("lbe", "#перенаправление|#перенапр|#REDIRECT");
        m.put("li", "#DOORVERWIJZING|#REDIRECT");
        m.put("ln", "#REDIRECTION|#REDIRECT");
        m.put("map-bms", "#ALIH|#REDIRECT");
        m.put("mg", "#REDIRECTION|#REDIRECT");
        m.put("mhr", "#перенаправление|#перенапр|#REDIRECT");
        m.put("mo", "#REDIRECTEAZA|#REDIRECT");
        m.put("myv", "#перенаправление|#перенапр|#REDIRECT");
        m.put("mzn", "#تغییرمسیر|#REDIRECT");
        m.put("nah", "#REDIRECCIÓN|#REDIRECT");
        m.put("os", "#перенаправление|#перенапр|#REDIRECT");
        m.put("pdc", "#WEITERLEITUNG|#REDIRECT");
        m.put("qu", "#REDIRECCIÓN|#REDIRECT");
        m.put("rmy", "#REDIRECTEAZA|#REDIRECT");
        m.put("ruq", "#REDIRECTEAZA|#REDIRECT");
        m.put("ruq-cyrl", "#пренасочување|#види|#Пренасочување|#ПРЕНАСОЧУВАЊЕ|#REDIRECT");
        m.put("ruq-grek", "#ΑΝΑΚΑΤΕΥΘΥΝΣΗ|#REDIRECT");
        m.put("ruq-latn", "#REDIRECTEAZA|#REDIRECT");
        m.put("sah", "#перенаправление|#перенапр|#REDIRECT");
        m.put("shi", "#تحويل|#REDIRECT");
        m.put("simple", "#REDIRECT");
        m.put("sr", "#Преусмери|#redirect|#преусмери|#ПРЕУСМЕРИ");
        m.put("stq", "#WEITERLEITUNG|#REDIRECT");
        m.put("su", "#ALIH|#REDIRECT");
        m.put("szl", "#PATRZ|#PRZEKIERUJ|#TAM|#REDIRECT");
        m.put("tt", "#yünältü|#REDIRECT");
        m.put("tt-cyrl", "#перенаправление|#перенапр|#REDIRECT");
        m.put("ty", "#REDIRECTION|#REDIRECT");
        m.put("udm", "#перенаправление|#перенапр|#REDIRECT");
        m.put("vep", "#suuna|#REDIRECT");
        m.put("vls", "#DOORVERWIJZING|#REDIRECT");
        m.put("wa", "#REDIRECTION|#REDIRECT");
        m.put("wo", "#REDIRECTION|#REDIRECT");
        m.put("xmf", "#REDIRECT|#გადამისამართება");
        m.put("ydd", "#ווייטערפירן|#הפניה|#REDIRECT");
        m.put("zea", "#DOORVERWIJZING|#REDIRECT");
		return m;
		
	}
	private List<String> getValues(String key) {

		String val = properties.getProperty(key);
		if (val == null){
			return Collections.emptyList();
		}
		List<String> values = new ArrayList<String>();
		if (!val.contains(SEPARATOR)) {
			values.add(val);

		} else {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(val).useDelimiter(SEPARATOR);
			while (scanner.hasNext()) {
				values.add(scanner.next());
			}
			scanner.close();
		}
		return values;

	}

	public List<String> getDisambigutionIdentifiers() {
		return getValues("disambiguation");
	}
	
	public List<String> getCategoryIdentifiers() {
		return getValues("category");
	}

	public List<String> getImageIdentifiers() {
		return getValues("image");
	}

	public List<String> getListIdentifiers() {
		return getValues("list");
	}

	public List<String> getRedirectIdentifiers() {
		return getValues("redirect");
	}
	
	//Logic for Language specific redirects. Instead of fetching from the property files
	public List<String> getRedirects(String lang){
		String [] redirects = ((String) langMap.get(lang)).split("\\|");
		List <String> list = new ArrayList<String>();
		for (int i=0; i < redirects.length; i++)
			list.add(redirects[i]);
		return list;
			
	}

}

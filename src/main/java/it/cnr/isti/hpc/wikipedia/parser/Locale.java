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
	private static Map<String, String> redirectsMap = new HashMap<String, String>();
	private static Map<String, String> disambigMap = new HashMap<String, String>();

	public Locale(String lang) {
		properties = new Properties();
		
		try {
			properties.load(Locale.class.getResourceAsStream("/lang/locale-"
					+ lang + ".properties"));
		} catch (IOException e) {
			//logger.error("readling the locale for language {} ({})", lang,
			//		e.toString());
			logger.info("Missing Config file. Instead reading from locally");
			//Commenting the exit statement to not fail the program if there are no 
			//corresponding language files
			//System.exit(-1);
		}
		
		logger.info("using {} language ",properties.get("language"));
		redirectsMap = insertRedirectSettings();
		disambigMap = insertDisambiguation();
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
	
	public Map<String, String> insertDisambiguation(){
		
		Map<String, String> m = new HashMap<String, String>();
		
		m.put("ab", "???????????????");
		m.put("ace" , "Disambig");
		m.put("af" ,"Dubbelsinnig|Disambig");
		m.put("ak" ,"Disambig");
		m.put("als" ,"Begriffsklärung|Disambig|Begriffsklärig");
		m.put("am","???");
		m.put("an" ,"Desambig|Disambig|Desambigazión|Desambigación");
		m.put("ang" ,"Disambig|Geodis");
		m.put("ar" ,"Disambig|?????|???? ?????");
		m.put("arc" ,"?|?.?.|Disambig");
		m.put("arz" ,"?????");
		m.put("as" ,"??????????_?????");
		m.put("ast" ,"Dixebra");
		m.put("av" ,"???????????????");
		m.put("ay" ,"Desambiguación");
		m.put("az" ,"D?qiql?sdirm?");
		m.put("ba" ,"Disambiguation|??? ???????????|???????????????|Disambig");
		m.put("bar" ,"Begriffsklärung|Begriffsklearung");
		m.put("bat_smg" ,"Daugiareikšmis|Disambig");
		m.put("bcl" ,"Clarip");
		m.put("be" ,"???????????????");
		m.put("be_x_old" ,"Disambig|????????????????");
		m.put("bg" ,"?????????|Disambig");
		m.put("bjn" ,"Disambig");
		m.put("bm" ,"Homonymie");
		m.put("bn" ,"Disambiguation|??????????_?????|?????????? ?????|Disambig");
		m.put("bpy" ,"??????????_?????|????? ???");
		m.put("br" ,"Project:Liammoù_ouzh_ar_pajennoù_disheñvelaat|Disheñvelout lec'hanvioù|Disheñvelout anvioù-badez|Disheñvelout|Disheñvelout anvioù-tud");
		m.put("bs" ,"Cvor");
		m.put("bug" ,"Disambig");
		m.put("ca" ,"Acrònim|Onomàstica|Desambigua|DesambigCurta|Disambig|Biografies|Desambiguació");
		m.put("cbk_zam" ,"Desambiguación");
		m.put("ce" ,"???? ??? ???? ??????|Disambiguation|???? ??? ???? ????????|???? ??? ???? ????? ????? ???????|Moviedis|Disambig|?????? ???????|???? ??? ???? ?????????|Surname|Placedis|Metrodis|Roaddis|???????????????|???????? ?????? ?????|?????? ???");
		m.put("ceb" ,"Disambig|Giklaro");
		m.put("ch" ,"Disambig");
		m.put("ckb" ,"???????????");
		m.put("crh" ,"Disambig|Çoq manali");
		m.put("cs" ,"Rozcestník - príjmení|Rozcestník - sakrální stavba|Rozcestník - místopisné jméno|Rozcestník - kostel|Rozcestník - chemie|Rozcestník - lod|Rozcestník|Rozcestník - 3 znaky|Rozcestník - 4 znaky|Rozcestník - jméno|Rozcestník - 2 znaky");
		m.put("csb" ,"Disambig|Starnë_ùjednoznacznieniô");
		m.put("cu" ,"??????????????|??????????´????|Disambig|??????????????");
		m.put("cv" ,"Disambig");
		m.put("cy" ,"Anamrwysedd|Gwahaniaethu|Disambig");
		m.put("da" ,"Begriffsklärung|Navn|Disambig|Flertydig");
		m.put("de" ,"Begriffsklärung");
		m.put("diq" ,"Disambig");
		m.put("dsb" ,"Wecejwóznamowosc|Rozjasnjenje zapsimjesow");
		m.put("dv" ,"Disambiguation|Disambig");
		m.put("el" ,"?p?saf???s?|?p?saf|Disambig");
		m.put("eml" ,"Disambigua");
		m.put("en" ,"Disambiguation|disambiguation|Hndis");
		m.put("eo" ,"Apartigilo|Disambig");
		m.put("es" ,"Des|Desambig|Desambiguacion|Disambig|Desambiguación");
		m.put("eu" ,"Disambiguation|Disambig|Argipen");
		m.put("ext" ,"Desambiguáncia");
		m.put("fa" ,"???????????-???????|??? ?????|???????????|????? ?????|???????????");
		m.put("ff" ,"Homonymie");
		m.put("fi" ,"Täsmennyssivu|Täsmennyssivu/sukunimi|Disambig");
		m.put("fiu_vro" ,"Linke täpsüstüslehekülile|Täpsüstüslehekülg|Disambig");
		m.put("fo" ,"Fleiri týdningar|Disambig");
		m.put("fr" ,"Communes françaises homonymes|Unités homonymes|Batailles homonymes|Homonymie d'îles|Paronymie|Homonymie de route|Internationalisation|Homonymie d'établissements scolaires ou universitaires|Cantons homonymes|Homonymie bateau|Homonymie de comtés|Gouvernements homonymes|Homonymie de monument|Toponymie|Isomérie|Homonymie de nom romain|Titres homonymes|Saints homonymes|Guerres homonymes|Bandeau standard pour page d'homonymie|Homonymie de clubs sportifs|Homonymie|Villes homonymes|Patronymie|Arrondissements homonymes|Films homonymes|Patronyme basque|Patronyme italien|Homonymie dynastique|Homonymie vidéoludique|Personnes homonymes|Homonymie de parti politique|Patronyme|Homonymie édifice religieux|Édifices religieux homonymes");
		m.put("frp" ,"Disambig|Homonimia|Homonimos");
		m.put("frr" ,"Muardüüdag artiikel|Muar Meeningen");
		m.put("fur" ,"Disambigua|Disambig|Disambiguazion");
		m.put("fy" ,"Neibetsjuttings|Tfs|Disambig");
		m.put("ga" ,"Idirdhealú|{{ns:project}}:Naisc_go_leathanaigh_idirdhealaithe");
		m.put("gag" ,"Kisaltmalar (anlam ayrimi)|Anlam ayrimi|Disambig|Anlam ayrim");
		m.put("gan" ,"???|????|???|????|Disambig|???|????");
		m.put("gd" ,"Soilleireachadh");
		m.put("gl" ,"Disambig|Homónimos");
		m.put("glk" ,"???????????");
		m.put("gn" ,"Disambig");
		m.put("gv" ,"Reddaghey");
		m.put("he" ,"?????????");
		m.put("hi" ,"Disambiguation|Disambig|?????|?????????? ????");
		m.put("hif" ,"Disambig");
		m.put("hr" ,"Nova razdvojba|Preusmjerenje u razdvojbu|Razdvojba");
		m.put("hsb" ,"Wjacezmyslnosc");
		m.put("ht" ,"Homonymie|Menm non");
		m.put("hu" ,"Egyért-redir|Egyértelmusíto lap|Egyértelmusíto|Egyért|Disambig|Egyert");
		m.put("hy" ,"?????????|??????????????????|??|???????????|Disambig|????????????????|????????");
		m.put("ia" ,"Disambiguation");
		m.put("id" ,"Disambig karya|Disambig-cleanup|Disambig nama|Disingkat|Disambig sekolah|Disambig|Disambig suku|Hndis|Disambig sejarah|Disambig bandara|Disambig tempat|Disambig tempat indo|Disambig indo1|Disambiguasi|Disambig olahraga");
		m.put("ie" ,"Disambig");
		m.put("ii" ,"???|????|???|????|Disambig|???|????");
		m.put("ilo" ,"Disambig");
		m.put("io" ,"Homonimo");
		m.put("is" ,"Tveggja stafa aðgreining|Aðgreining");
		m.put("it" ,"Disambigua");
		m.put("ja" ,"Aimai");
		m.put("jv" ,"Disambig panggonan|Cekakan|Disambig|Disambig suku|Disambig jeneng");
		m.put("ka" ,"????????|Disambig|???????????????????");
		m.put("kaa" ,"Disambig-cleanup|Hospitaldis|Schooldis|Numberdis|Disamb-cleanup|Disambiguation|Bio-dab|Disambig|POWdis|Surname|Hndis|SIA|Disamb|Mountainindex|Dab|Given name|Roaddis|Shipindex|Geodis|Mathdab|Hndisambig|Diasmbig|Hndis-cleanup");
		m.put("kab" ,"Asefham");
		m.put("kbd" ,"Disambig");
		m.put("kk" ,"Disambig-cleanup|Hospitaldis|?????|Schooldis|Numberdis|Disamb-cleanup|Disambiguation|Bio-dab|Disambig|POWdis|Surname|Hndis|SIA|Disamb|Mountainindex|Dab|Given name|Roaddis|Shipindex|Geodis|Mathdab|Hndisambig|Diasmbig|Hndis-cleanup");
		m.put("kl" ,"Disambig|Flertydig|Qulequtaqatigiit");
		m.put("kn" ,"??????? ???????");
		m.put("ko" ,"?? ????|????|Disambig|?????|????");
		m.put("krc" ,"Disambiguation|??? ?????????|Disambig");
		m.put("ksh" ,"Disambig");
		m.put("ku" ,"Cudakirin|Disambig");
		m.put("kv" ,"???????????????");
		m.put("kw" ,"Klerheans");
		m.put("la" ,"Discretiva");
		m.put("lad" ,"Desambiguación");
		m.put("lb" ,"Homonymie Familljennumm|Homonymie|Homonymie Persounen|Disambig|Homonymie Ofkierzungen");
		m.put("lbe" ,"???????????????");
		m.put("li" ,"Vp|Verdudeliking|Disambig|Verdudelikingpazjena");
		m.put("lij" ,"Disambigua");
		m.put("lmo" ,"Desambiguassiú|Desambiguazzion|Desambiguazion|Dezambiguasiù|Disambigua|Disambig|Desambiguació|Dezambiguasiú");
		m.put("ln" ,"Bokokani|Homonymie");
		m.put("lt" ,"Daugiareikšmis|Nuorodiniai straipsniai|Kodai|Disambig|Nuorodinis|Valdovas-disambig|Pavarde");
		m.put("ltg" ,"Disambiguation|Zeimeibu škiršona|Disambig");
		m.put("lv" ,"Nozimju atdališana|Disambig");
		m.put("mai" ,"Disambig");
		m.put("map_bms" ,"Disambig");
		m.put("mdf" ,"Disambiguation|???????? ???????|Moviedis|Disambig|Surname|???????? ??????|Placedis|???????? ???|???????? ????? ???????????|????? ???|Metrodis|Roaddis|?? ??? ?????|???? ??????");
		m.put("mg" ,"Pejy mitovy anarana|Homonymie");
		m.put("mhr" ,"???????????????|???? ??????????-????|???? ??????????-????");
		m.put("mi" ,"Disambig");
		m.put("mk" ,"????????|???????????|Disambig|Geodis|??-???????????|Hndis");
		m.put("ml" ,"????????|Disambig|???????????|????????????");
		m.put("mn" ,"Disambig|????? ???????|?????");
		m.put("mo" ,"Dezambiguizare|Disambig");
		m.put("mr" ,"Disambiguation|??:???????????");
		m.put("mrj" ,"Disambig");
		m.put("ms" ,"Nyahkekaburan|Disambig");
		m.put("mt" ,"Dizambigwazzjoni|Dizambig");
		m.put("mwl" ,"Disambig");
		m.put("myv" ,"???????? ????? ?????");
		m.put("mzn" ,"???????????");
		m.put("nah" ,"Desambiguación");
		m.put("nap" ,"Disambigua");
		m.put("nds" ,"Mehrdüdig_Begreep|Mehrdüdig Begreep");
		m.put("nds_nl" ,"Dv");
		m.put("ne" ,"Disambig");
		m.put("nl" ,"Dp|Dp-intro|Disambig|DP|Dpintro");
		m.put("nn" ,"Pekerside|Tobokstavforkorting|Disambig|Fleirtyding|Peikar");
		m.put("no" ,"Etternavn|Tobokstavsforkortelse|Disambiguation|Pekerside|Trebokstavsforkortelse|Disambig|Geodis|Flertydig|Hndis|Peker|Disamb");
		m.put("nrm" ,"Page dé frouque|Disambig");
		m.put("nv" ,"Alts'á'áztiin");
		m.put("oc" ,"Sigla|Omonimia|Paronimia|Omonimia2|Siglas n letras");
		m.put("or" ,"DAB|Disambiguation|Numberdis|Dab|?????????|Disambig|Disamb");
		m.put("os" ,"???????????????|Disambig");
		m.put("pa" ,"Disambig");
		m.put("pam" ,"Callsigndis|Hospitaldis|Letter-NumberCombDisambig|Numberdis|Schooldis|Disamb-cleanup|Numdab|CleanupDisambig|LatinNameDisambig|Bio-dab|Roadab|Hurricane disambig|Church disambig|Hndis|Dab-cleanup|Disamb|Dabclean|Dab|Shortcut disambig|WP-disambig|Roaddis|Cleanup-disambig|Mathdab|Hndisambig|Roadis|Shorcut disambig|Disambig-CU|Disambig-Chinese-char-title|Disambig-cleanup|Disambiguation|WP Disambig|Cleanup disambig|Letter disambig|Geo-dis|Geodab|Disambig|Airport disambig|Pamipalino|Namedab|MolFormDisambig|CJKVdab|NA Broadcast List|WP disambig|Hndab|Geodis|Hndis-cleanup");
		m.put("pap" ,"Disambig");
		m.put("pdc" ,"Begriffsklärung");
		m.put("pfl" ,"BKL");
		m.put("pl" ,"Ujednoznacznienie|Disambig");
		m.put("pms" ,"Gestion dij sinònim");
		m.put("pt" ,"Disambiguation|!Desambiguação|Desambig-ini|Desambiguação|Desambig|Disambig|Desambig-wikcionário");
		m.put("qu" ,"Sut'ichana qillqa");
		m.put("rm" ,"Sclerir noziun");
		m.put("rmy" ,"Dezambiguizare");
		m.put("ro" ,"Dezamb|Persoane omonime|DezNume|Dezambiguizare comitate SUA|Dezambiguizare|DezGeo|Disambig|Hndis|Dez");
		m.put("roa_tara" ,"Disambigua|Disambig|Sigla2|Disambigue|Sigla3");
		m.put("ru" ,"????|??????????? ??????? ???|???????|??????????? ?????????|???????? ?????|???|Islanddis|?????|??????????? ????|??????????? ?????|?????? ?????|???|Metrodis|Roaddis|??????????? ??????????????? ???????|??????????? ?????|??????????? ?????|??????????? ????|??????????? ???????|???|????|Mondis|Mountaindis|Surname|??????? ?????|?????? ?????????????-?????|?????? ?????????????|?????? ?????-?????????????|??????????? ???????? ?????|??????????? ???????|???????? ????????????|???????????????|??????????????|??????????? ???????|Coorddis|Moviedis|???|Churchdis|Lakedis|??????????? ??????|??????????? ???????|Placedis|Stationdis|Militarydis|Riverdis|????|??????????? ?????????|??????????? ??????|Disambiguation|Monumdis|??????????? ??????????|???????|??????????? ????????????? ???????????|?????? ?????? ?????|Disambig|??????????? ?????????? ??????|??????????? ?????? ???????|??????????? ??????? ?????|??????????? ??|???????????????2|?????-????????????|????????????-?????|Shipdis");
		m.put("rue" ,"?????????");
		m.put("sa" ,"Disambig");
		m.put("sah" ,"Disambiguation|????? ?????????? ??????????|???????????????|Disambig|Surname|???????");
		m.put("sc" ,"Disambìgua|Disambigua");
		m.put("scn" ,"Disambigua|Sigla2|Sigla3");
		m.put("sco" ,"Disambig|Geodis");
		m.put("sd" ,"Disambig");
		m.put("sh" ,"Razdvojba|Višeznacnost|VZO|Višeznacna odrednica|Disambig|Razvrstavanje|Radzvojba|Homograf|Cvor");
		m.put("si" ,"Callsigndis|Letter-NumberCombDisambig|Numberdis|Schooldis|Disamb-cleanup|Numdab|CleanupDisambig|LatinNameDisambig|Biology disambiguation|Bio-dab|Hndis|Dab-cleanup|Disamb|Species Latin name disambiguation|Genus disambiguation|Dabclean|Dab|Shortcut disambig|WP-disambig|Hospital disambiguation|Mathdab|Hndisambig|Disambig-CU|Disambig-Chinese-char-title|Disambig-cleanup|Disambiguation|WP Disambig|Cleanup disambig|Letter disambig|Disambiguation cleanup|Geo-dis|Geodab|Disambig|Airport disambig|Species Latin name abbreviation disambiguation|Taxonomy disambiguation|MolFormDisambig|CJKVdab|SpeciesLatinNameDisambig|NA Broadcast List|WP disambig|Hndab|Chemistry disambiguation|Geodis|?????????????|Letter-NumberCombdisambig|Hndis-cleanup");
		m.put("sk" ,"Rozlišovacia stránka");
		m.put("sl" ,"Numberdis|Razlocitev|Kazalo ladij|Disambig|Disambig-ship|Priimek|Razlocitev osebnih imen|Disambig-divizija|Disambig-brigada|Disambig-polk|Disambig-korpus|Disambig-armada|SloPriimek|Razlocitev cest|Kratice|Geodis|Razlocitev-kraj|Disambig-unit|Georaz");
		m.put("sq" ,"Kthjellim");
		m.put("sr" ,"Višeznacna odrednica (latinica)|????????|Disambiguation|?????????? ?????????|????????????|???|Višeznacna odrednica molekulske formule-lat|???|Disambig|Višeznacna-lat|??????????|Disambig-lat");
		m.put("srn" ,"Dp|Doorverwijspagina");
		m.put("ss","Disambig");
		m.put("stq" ,"Begriepskläärenge");
		m.put("su" ,"Project:Tumbu_ka_kaca_disambiguasi|Disambig");
		m.put("sv" ,"3Bgren|Trebokstavsförgrening|Disambiguation|Namngrensida|Trebokstavsförkortning|3LC|Gaffel|Disambig|4LA|Fyrbokstavsförgrening|Betydelselista|Hndis|4LC|Förnamn|Grensida|Dab|Efternamn|Gren|Namnförgrening|Förgreningssida|Ortnamn|Flertydig|Förgrening|Robotskapad förgrening|TLAdisambig|3LA");
		m.put("sw" ,"Maana");
		m.put("szl" ,"Disambig");
		m.put("ta" ,"??????? ??????????????");
		m.put("te" ,"??????? ????? ????????|????? ????????");
		m.put("tg" ,"??????????|Disambig");
		m.put("th" ,"????????????|???????|Disambig|????????");
		m.put("tl" ,"Paglilinaw|Disambig");
		m.put("to" ,"Faka?uhingakehe");
		m.put("tpi" ,"Disambig");
		m.put("tr" ,"Kisaltmalar (anlam ayrimi)|Kisi adlari (anlam ayrimi)|Yerlesim anlam ayrimi|Anlam ayrimi|Disambig|Anlam ayrim|Sayilar (anlam ayrimi)|Cografya (anlam ayrimi)");
		m.put("tt" ,"??? ?????????|??? ????????");
		m.put("ty" ,"Homonymie");
		m.put("udm" ,"Disambiguation|???????????????|Disambig|??????????????");
		m.put("uk" ,"???????????????|?? ? ?????|Disambig|???????????????|DisambigN|?????? ?????????????|DisambigG");
		m.put("ur" ,"Disambig");
		m.put("uz" ,"Disambig");
		m.put("vec" ,"Disanbigua|Disambigua|Dixanbigua");
		m.put("vep" ,"Äiznamoicenduz");
		m.put("vi" ,"Ð?nh hu?ng|Trang d?nh hu?ng|Dis|Disambig|Hndis|TLAdisambig");
		m.put("vls" ,"Db|Disambig|Doorverwijspagina");
		m.put("vo" ,"Telplänov");
		m.put("wa" ,"Omonimeye");
		m.put("war" ,"Pansayod");
		m.put("wo" ,"Homonymie");
		m.put("wuu" ,"???|????|????|Disambig|????|???|???");
		m.put("xal" ,"????-???");
		m.put("xmf" ,"???????????????????");
		m.put("yi" ,"???????");
		m.put("yo" ,"Disambig|Ì?ojútùú");
		m.put("za" ,"???|????|???|????|Disambig|???|????");
		m.put("zea" ,"Doorverwijspagina");
		m.put("zh" ,"Disambiguation|????|Letter disambig|???|????|Disambig|????|???|????|Chemdab|Disamb|???|????|???|MolFormDisambig|??|Dab|????|Isomerdab|???|???|Deprecated");
		m.put("zh_min_nan" ,"KhPI|Khu-pia?t-iah|Khu-pia?t-ia?h|Disambig");
		m.put("zh_yue" ,"Disambig|???");
		
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
	
	/*
	public List<String> getDisambigutionIdentifiers() {
		return getValues("disambiguation");
	}
	
	*/
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
		String [] redirects = ((String) redirectsMap.get(lang)).split("\\|");
		List <String> list = new ArrayList<String>();
		for (int i=0; i < redirects.length; i++)
			list.add(redirects[i]);
		return list;
			
	}
	
	public List<String> getDisambigutionIdentifiers(String lang){
		String [] disambig = ((String) disambigMap.get(lang)).split("\\|");
		List <String> list = new ArrayList<String>();
		for (int i=0; i < disambig.length; i++)
			list.add(disambig[i]);
		return list;
	}

}

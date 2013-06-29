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
package it.isti.cnr.hpc.wikipedia.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Diego Ceccarelli <diego.ceccarelli@isti.cnr.it>
 * 
 * Created on Feb 14, 2013
 */
public class Locale {
	
	private Properties properties;
	
	
	private static final Logger logger = LoggerFactory.getLogger(Locale.class);
	private static final String SEPARATOR = ",";
	
	public Locale(String lang){
		properties = new Properties();
		try {
			properties.load(Locale.class.getResourceAsStream("/lang/local_"+lang+".properties"));
		} catch (IOException e) {
			logger.error("readling the locale for language {} ({})",lang,e.toString());
			System.exit(-1);
		}
		
	}
	
	private List<String> getValues(String key){
		List<String> values = new ArrayList<String>(3);
		String val = properties.getProperty(key);
		if (! val.contains(SEPARATOR)){
			values.add(val);
			return values;
		}
		Scanner scanner = new Scanner(val).useDelimiter(SEPARATOR);
		while (scanner.hasNext()){
			values.add(scanner.next());
		}
		return values;
		
	}
	
	public List<String> getDisambigutionNames(){
		return getValues("disambiguation");
	}
	
	public List<String> getListNames(){
		return getValues("list");
	}
	
	public List<String> getRedirectNames(){
		return getValues("redirect");
	}

}

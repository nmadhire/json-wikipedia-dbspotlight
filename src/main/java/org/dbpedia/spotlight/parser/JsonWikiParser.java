/**
 *  Copyright 2015 DBpedia Spotlight
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


package org.dbpedia.spotlight.parser;

import java.io.IOException;

import org.xml.sax.SAXException;

import it.cnr.isti.hpc.wikipedia.reader.WikipediaArticleReader;

/**
 * Class to Parse the Single XML Page. This is an abstraction for calling
 * Json-WikiPedia created by Deigo Ceccarelli
 * 
 * "https://github.com/diegoceccarelli/json-wikipedia"
 * 
 */
public class JsonWikiParser {
	
	private String lang;
	private String XMLInput;
	
	public JsonWikiParser(String XMLInput, String lang){
		this.lang = lang;
		this.XMLInput = XMLInput;
	}
	
	public String jsonParser() throws IOException, SAXException{
		WikipediaArticleReader wap = new WikipediaArticleReader(XMLInput, lang);
		wap.start();
		return wap.getJsonOutput();
	}

}

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


package org.dbpedia.spotlight.parsertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.cnr.isti.hpc.io.IOUtils;
import it.cnr.isti.hpc.wikipedia.article.Article;
import it.cnr.isti.hpc.wikipedia.article.Language;
import it.cnr.isti.hpc.wikipedia.reader.WikipediaArticleReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.junit.Test;
import org.xml.sax.SAXException;

/*
 * Test case for validating Disambiguation pages
 */
public class WikiParserDisambigTest {
	
	
	@Test
	public void testSpans() throws UnsupportedEncodingException, FileNotFoundException, IOException, SAXException {
		
		URL mediaWiki = Thread.currentThread().getContextClassLoader().getResource("enwiki-pages-test-disamb.xml");
		assertNotNull(mediaWiki);
		WikipediaArticleReader wap = new WikipediaArticleReader(mediaWiki.getFile(),"/wikisample.json", Language.EN);
		wap.start();
		String [] json = IOUtils.getFileAsUTF8String("/wikisample.json").split("\n");
		
		for(int i =0; i < json.length; i++){
			Article a = Article.fromJson(json[i]);
			assertEquals(a.getTypeName(), "Disambiguation");
		}
		
	}
}

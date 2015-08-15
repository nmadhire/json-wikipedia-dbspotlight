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
import it.cnr.isti.hpc.wikipedia.article.Article.Type;
import it.cnr.isti.hpc.wikipedia.article.Language;
import it.cnr.isti.hpc.wikipedia.article.Link;
import it.cnr.isti.hpc.wikipedia.reader.WikipediaArticleReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import org.dbpedia.spotlight.ParagraphLink;
import org.junit.Test;
import org.xml.sax.SAXException;

public class WikiParserTest {
	
	/*
	 * Test Cases for checking the links parsing.
	 */
	@Test
	public void testSpans() throws UnsupportedEncodingException, FileNotFoundException, IOException, SAXException {
		
		URL mediaWiki = Thread.currentThread().getContextClassLoader().getResource("wikisample.xml");
		assertNotNull(mediaWiki);
		WikipediaArticleReader wap = new WikipediaArticleReader(mediaWiki.getFile(),"/wikisample.json", Language.EN);
		wap.start();
		String [] json = IOUtils.getFileAsUTF8String("/wikisample.json").split("\n");
		
		for(int i =0; i < json.length; i++){
			Article a = Article.fromJson(json[i]);
			List<ParagraphLink> paraLinks = a.getParagraphsLink();
			List<Link> iLinks = a.getLinks();
			String wikiText = a.getWikiText();
			int totalLinks = a.getLinks().size();
			int paraLinksCount = 0;

			for(ParagraphLink l: paraLinks){
				String paraText = l.getParaText();
				List<Link> links = l.getLinks();
				paraLinksCount+=links.size();
				for(Link link: links){
				    if (wikiText.length() > 0)
				        assertEquals(link.getDescription(), paraText.substring(link.getStart(), link.getEnd()));
				}
			}
			
			//Adding test case to Check for all internal links against the Wiki Text
			for (Link l: iLinks){
				
				if(wikiText.length() > 0)
					assertEquals(l.getDescription(), wikiText.substring(l.getStart(), l.getEnd()));
			}
			
			//TestCase for checking the total Links against the individual para links in the WikiArticle
			assertEquals(paraLinksCount, totalLinks);
		}
		
	}
	
	/*
	 * Test Case for Checking the Type of WikiPage,WikiTitle
	 */
	
	@Test
	public void testWikiPage() throws UnsupportedEncodingException, FileNotFoundException, IOException, SAXException {
		
		URL mediaWiki = Thread.currentThread().getContextClassLoader().getResource("wikisample_oneArticle.xml");
		assertNotNull(mediaWiki);
		System.out.println("MediaWiki:" + mediaWiki);
		WikipediaArticleReader wap = new WikipediaArticleReader(mediaWiki.getFile(),"/tmp/wikisample.json", Language.EN);
		wap.start();
		String json = IOUtils.getFileAsUTF8String("/tmp/wikisample.json");
		
		Article a = Article.fromJson(json);
		assertEquals("Anarchism",a.getWikiTitle());
		assertEquals(Type.ARTICLE,a.getType());
		
	}

	/*
	 * Test case for Redirects
	 */
	@Test
	public void testWikiRedirect() throws UnsupportedEncodingException, FileNotFoundException, IOException, SAXException {
		
		URL mediaWiki = Thread.currentThread().getContextClassLoader().getResource("wikisample_Redirect.xml");
		assertNotNull(mediaWiki);
		System.out.println("MediaWiki:" + mediaWiki);
		WikipediaArticleReader wap = new WikipediaArticleReader(mediaWiki.getFile(),"/tmp/wikisample.json", Language.EN);
		wap.start();
		String json = IOUtils.getFileAsUTF8String("/tmp/wikisample.json");
		
		Article a = Article.fromJson(json);
		assertEquals("AfghanistanHistory",a.getWikiTitle());
		assertEquals(Type.REDIRECT,a.getType());
		assertEquals("History_of_Afghanistan",a.getRedirect());
		
	}
}

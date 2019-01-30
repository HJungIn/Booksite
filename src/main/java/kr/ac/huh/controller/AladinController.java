package kr.ac.huh.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserAdapter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Controller
public class AladinController {

	@RequestMapping("/aladin")
	public String aladin(Model model) throws Exception {

		return "aladin";
	}

	@RequestMapping("/aladinSearch")
	public String aladinSearch(Model model, HttpServletRequest request) throws Exception {
		String queryType = request.getParameter("queryType");
		String bookname = request.getParameter("bookname");

		String url = GetUrl(queryType, bookname);
		AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
		api.parseXml(url);

		
		for (Item item : api.Items) {
			System.out.println(item.Title + " : " + item.Link);
		}

		model.addAttribute("bookItems", api.Items);

		return "aladin";
	}

	
	
	
	// 알라딘 검색api
	private static final String BASE_URL = "http://www.aladdin.co.kr/ttb/api/ItemSearch.aspx?";

	public String GetUrl(String querytype, String searchWord) throws Exception {
		Map<String, String> hm = new HashMap<String, String>();
		hm.put("ttbkey", "");  //키코드
		hm.put("QueryType", querytype);
		hm.put("Query", URLEncoder.encode(searchWord, "UTF-8"));
		hm.put("MaxResults", "20");
		hm.put("start", "1");
		hm.put("SearchTarget", "Book");
		hm.put("output", "xml");

		StringBuffer sb = new StringBuffer();
		Iterator<String> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String val = hm.get(key);
			sb.append(key).append("=").append(val).append("&");
		}
		return BASE_URL + sb.toString();
	}

	@Getter
	@Setter
	@ToString
	public class Item {
		public String Cover = "";
		public String Title = "";
		public String Link = "";
		public String Author = "";
		public String Isbn = "";
		public String Publisher = "";
		public String Description = "";
	}

	class AladdinOpenAPIHandler extends DefaultHandler {
		public List<Item> Items;
		private Item currentItem;
		private boolean inItemElement = false;
		private String tempValue;

		public AladdinOpenAPIHandler() {
			Items = new ArrayList<Item>();
		}

		public void startElement(String namespace, String localName, String qName, Attributes atts) {
			if (localName.equals("item")) {
				currentItem = new Item();
				inItemElement = true;
			} else if (localName.equals("cover")) {
				tempValue = "";
			} else if (localName.equals("title")) {
				tempValue = "";
			} else if (localName.equals("link")) {
				tempValue = "";
			} else if (localName.equals("author")) {
				tempValue = "";
			} else if (localName.equals("isbn")) {
				tempValue = "";
			} else if (localName.equals("publisher")) {
				tempValue = "";
			} else if (localName.equals("description")) {
				tempValue = "";
			}
		}

		public void characters(char[] ch, int start, int length) throws SAXException {
			tempValue = tempValue + new String(ch, start, length);
		}

		public void endElement(String namespaceURI, String localName, String qName) {
			if (inItemElement) {
				if (localName.equals("item")) {
					Items.add(currentItem);
					currentItem = null;
					inItemElement = false;
				} else if (localName.equals("cover")) {
					currentItem.Cover = tempValue;
				} else if (localName.equals("title")) {
					currentItem.Title = tempValue;
				} else if (localName.equals("link")) {
					currentItem.Link = tempValue;
				} else if (localName.equals("author")) {
					currentItem.Author = tempValue;
				} else if (localName.equals("isbn")) {
					currentItem.Isbn = tempValue;
				} else if (localName.equals("publisher")) {
					currentItem.Publisher = tempValue;
				} else if (localName.equals("description")) {
					currentItem.Description = tempValue;
				}
				
			}
		}

		public void parseXml(String xmlUrl) throws Exception {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			ParserAdapter pa = new ParserAdapter(sp.getParser());
			pa.setContentHandler(this);
			pa.parse(xmlUrl);
		}
	}
	
	
	
	
	//알라딘 책의 상세 페이지
	@RequestMapping("/viewBook/{bookIsbn}")
	public String viewBook(@PathVariable String bookIsbn, Model model) throws Exception {
	
		
		String url = GetUrl("isbn", bookIsbn);
		AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
		api.parseXml(url);

		
		for (Item item : api.Items) {
			System.out.println("상세 +++ "+item.Title + " : " + item.Description);
		}

		model.addAttribute("bookDetail", api.Items.get(0));
		
		return "viewBook";
	}
}

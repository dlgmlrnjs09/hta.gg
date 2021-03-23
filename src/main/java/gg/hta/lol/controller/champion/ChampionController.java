package gg.hta.lol.controller.champion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import gg.hta.lol.riotapi.RuneSpellConverter;
import gg.hta.lol.service.ChampService;
import gg.hta.lol.vo.ChampionVo;

@Controller
public class ChampionController {
	@Autowired
	ChampService service;


	@GetMapping(value = "/champ/ChampInsert")
	public String ChmapList() {
		return "champ/ChampInsert";
	}

	@ResponseBody
	@PostMapping(value = "champ/ChampInsert2", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public String ChamInsert2(ChampionVo vo) {

		service.insertChamp(vo);
		return "{\"code\":\"" + vo + "\"}";
	}

	@ResponseBody
	@GetMapping(value = "/champ/ChampDelete")
	public String ChampDelete() {

		service.deleteChamp();

		return "{\"code\":\"dd\"}";
	}
	
	@GetMapping(value = "/champ/ChampList1")
	public String ChampList(Model mv) {
		
		
		return ".header2.champ.ChampList";
	}

	@ResponseBody
	@GetMapping(value = "/champ/ChampList", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Map<String, Object> ChampAll_List() {
		Map<String, Object> map = new HashMap<String, Object>();

	
		map.put("map", service.ChampAll_List());
		map.put("banlist", service.banlistAll());
		map.put("picklist", service.pickrank());
		map.put("winlist", service.winlistAll());

		
		
		return map;

	}

	@GetMapping(value = "/champ/selectList{championid}")
	public String selectList(int championid, Model mv) {
		mv.addAttribute("championid", championid);
		return ".header2.champ.selectList";
	}

	@ResponseBody
	@GetMapping(value = "/champ/selectList1")
	public ChampionVo selectList1(int championid) {
	
		service.selectList(championid);
		
		return service.selectList(championid);
	}
	
	@ResponseBody
	@GetMapping(value="/champ/count")
	public Map<String, Object> count(int championid){
		Map<String, Object> mc=new HashMap<String, Object>();
		
		mc.put("allCount", service.allCount(championid));
		mc.put("wincount", service.wincount(championid));
		mc.put("bancount", service.banCount(championid));
		mc.put("matchcount", service.matchCount(championid));
		mc.put("maxCount",service.maxCount(championid));
		mc.put("winrank",service.winrank(championid));
		mc.put("banrank",service.banrank(championid));
		mc.put("itemlist",service.itemtree(championid));
		return mc;
				
	}
	@ResponseBody
	@GetMapping(value="/champ/itemtree")
	public Map<String,Object> itemtree(int championid){
		Map<String, Object> mp=new HashMap<String, Object>();
		

		mp.put("itemlist",service.itemtree(championid));
		return mp;
	}
	@ResponseBody
	@GetMapping(value="/champ/conver")
	public Map<String,Object> conver(String spell1){
		Map<String,Object> aa=new HashMap<String, Object>();
		
		
		return null;
		
	}


/*
	static String api_key = "RGAPI-707cfdb4-37f8-461f-883e-e936f4d5b09c";

	@ResponseBody
	@GetMapping(value = "/champ/ChampPick")
	public String ChampPic(HttpServletResponse rs) {
		String surl = "https://kr.api.riotgames.com/lol/league/v4/entries/RANKED_SOLO_5x5/DIAMOND/I?page=1&api_key="
				+ api_key;
		StringBuffer sb = new StringBuffer();
	
		
		try {
			URL url = new URL(surl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (conn != null) {
				conn.setConnectTimeout(10000);
				conn.setUseCaches(false);
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
					String line = "";
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}

					JSONParser parser = new JSONParser();
					Object obj = parser.parse(sb.toString());
					JSONArray jsonObj = (JSONArray) obj;

					Object name = "";
					Object accountId="";
					for (int i = 0; i < jsonObj.size(); i++) {
						name = ((JSONObject) (jsonObj.get(i))).get("summonerId");
						
						String surl1 = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/" + name + "?api_key="
								+ api_key;

						URL url1 = new URL(surl1);
						HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
						if (conn1 != null) {
							conn1.setConnectTimeout(10000);
							conn1.setUseCaches(false);

							if (conn1.getResponseCode() == HttpURLConnection.HTTP_OK) {
								BufferedReader br1 = new BufferedReader(
										new InputStreamReader(conn1.getInputStream(), "utf-8"));
								String line1 = null;
								JSONArray jarr=new JSONArray();
								StringBuffer sb1 = new StringBuffer();
								while ((line1 = br1.readLine()) != null) {
									sb1.append(line1);
									JSONArray jarr=new JSONArray();
									JSONParser parser1 = new JSONParser();
									Object obj1 = parser1.parse(sb1.toString());
									JSONObject jsonObj1 = (JSONObject) obj1;
									jarr.add(jsonObj1);
								}
								for(int j=0; j<jarr.size(); j++)
								{
									
								Object ob=	((JSONObject)jarr.get(j)).get("accountId");
								String pickURL="https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+ob+"?api_key="+api_key;
								URL pickurl=new URL(pickURL);
								HttpURLConnection connPick=(HttpURLConnection)pickurl.openConnection();
								if(connPick!=null) {
									connPick.setConnectTimeout(10000);
									connPick.setUseCaches(false);
									if(connPick.getResponseCode()==HttpURLConnection.HTTP_OK) {
										BufferedReader br2=new BufferedReader(new InputStreamReader(connPick.getInputStream(), "utf-8"));
										String PickLine=null;
										JSONArray Pickjarr=new JSONArray();
										StringBuffer sb2 = new StringBuffer();
										while((PickLine=br2.readLine())!=null) {
											sb2.append(PickLine);
											JSONParser parser2 = new JSONParser();
											Object obj2 = parser2.parse(sb2.toString());
											JSONObject jsonObj2 = (JSONObject) obj2;
											Pickjarr.add(jsonObj2);
											System.out.println(Pickjarr.size());
											for(int k=0; k<Pickjarr.size(); k++) {
												
										//		System.out.println(Pickjarr.get(k));
												JSONArray ob2=(JSONArray)(((JSONObject)Pickjarr.get(k)).get("matches"));
												System.out.println(ob2);
												System.out.println(((JSONObject)ob2.get(k)).get("champion"));
											}
										}
										
								
										
									}
								}
								}								
							
								
								
								br1.close();
								conn1.disconnect();
							}
						}

					}

					br.close();
					conn.disconnect();
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
*/

	@GetMapping(value = "/champ/ChampPickList")
	public String ChampPickList() {
		return "champ/ChampPick";
	}

//	@ResponseBody
//	@GetMapping(value="/champ/ChampPick3")
//	public String  ChampPic2(String name2) {
//	
//		System.out.println(name2);
//		String surl = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+name2+"?api_key="+api_key;
//		StringBuffer sb = new StringBuffer();
//		try {
//		URL url = new URL(surl);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		if (conn != null) {
//			conn.setConnectTimeout(10000);
//			conn.setUseCaches(false);
//			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//				String line = "";
//				while ((line = br.readLine()) != null) {
//					sb.append(line);
//				}
//				System.out.println(sb.toString());
//				br.close();
//				conn.disconnect();
//			}
//		}
//		return sb.toString();
//		}catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	

}

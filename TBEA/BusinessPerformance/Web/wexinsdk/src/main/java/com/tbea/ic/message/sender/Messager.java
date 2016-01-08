package com.tbea.ic.message.sender;

import net.sf.json.JSONObject;

import com.tbea.ic.auth.Connection;
import com.tbea.ic.contacts.Department;
import com.tbea.ic.contacts.Employee;
import com.tbea.ic.message.News;
import com.tbea.ic.message.Text;
import com.tbea.ic.util.JSON;

public class Messager {
//	  "touser": "UserID1|UserID2|UserID3",
//	   "toparty": " PartyID1 | PartyID2 ",
//	   "totag": " TagID1 | TagID2 ",
//	   "msgtype": "image",
//	   "agentid": 1,
//	   "image": {
//	       "media_id": "MEDIA_ID"
//	   },
//	   "safe":"0"
	String touser = "";
	String toparty = "";
	String totag = "";
	String msgtype = "";
	Integer agentid;
	Integer safe = 0;
	
	Text text;
	News<News.Article> news;
	News<News.MPArticle> mpnews;
	
	public Messager(Integer agentid){
		this.agentid = agentid;
	}

	public Integer getSafe() {
		return safe;
	}

	public void setSafe(Integer safe) {
		this.safe = safe;
	}

	public String getTouser() {
		return touser;
	}

	public String getToparty() {
		return toparty;
	}

	public String getTotag() {
		return totag;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public Integer getAgentid() {
		return agentid;
	}
	
	public Messager toAll(){
		touser = "@all";
		return this;
	}
	
	public Messager toUser(String user){
		if (touser.isEmpty()){
			touser = user;
		}else{
			touser += "|" + user;
		}
		return this;
	}
	
	public Messager toUser(Employee user){
		return toUser(user.getUserid());
	}
	
	public Messager toParty(Integer partId){
		if (toparty.isEmpty()){
			toparty = "" + partId;
		}else{
			toparty += "|" + partId;
		}
		return this;
	}
	
	public Messager toParty(Department dep){
		return toParty(dep.getId());
	}
	
	public Messager toTag(String tagId){
		if (totag.isEmpty()){
			totag = "" + tagId;
		}else{
			totag += "|" + tagId;
		}
		return this;
	}
	
	public boolean send(){
		String resp = Connection.getInstance().httpsPost("https://qyapi.weixin.qq.com/cgi-bin/message/send?", JSON.stringify(this));
		JSONObject jResp = JSONObject.fromObject(resp);
		if (jResp.getInt("errcode") == 0){
			return true;
		}
		return false;
	}
	
	public Text getText() {
		return text;
	}

	public News<News.Article> getNews() {
		return news;
	}

	public News<News.MPArticle> getMpnews() {
		return mpnews;
	}

	private void clear(){
		text = null;
		news = null;
		mpnews = null;
		msgtype = null;
	}
	
	public Messager text(Text text){
		if (null != text){
			clear();
			msgtype = "text";
			this.text = text;
		}
		return this;
	}
	
	public Messager news(News<News.Article> news){
		if (null != news){
			clear();
			msgtype = "news";
			this.news = news;
		}
		return this;
	}
	public Messager MPNews(News<News.MPArticle> mpnews){
		if (null != mpnews){
			clear();
			msgtype = "mpnews";
			this.mpnews = mpnews;
		}
		return this;
	}
}

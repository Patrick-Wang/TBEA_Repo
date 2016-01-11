package com.tbea.ic.message.entity;

import java.util.ArrayList;
import java.util.List;

public class News<T> {
	public static class Article{
		  String title;
		  String description;
		  String url;
		  String picurl;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getPicurl() {
			return picurl;
		}
		public void setPicurl(String picurl) {
			this.picurl = picurl;
		}
		  
	}
	
	public static class MPArticle{
		
	}
	
	List<T> articles = new ArrayList<T>();

	public List<T> getArticles() {
		return articles;
	}

	public void setArticles(List<T> articles) {
		this.articles = articles;
	}
	
}

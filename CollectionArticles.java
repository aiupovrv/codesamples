package de.channelpilot.processing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.myapp.model.Article;

public class CollectionArticles {
	private static final CollectionArticles INSTANCE = new CollectionArticles();

	private CollectionArticles() {
	}
	
	public static CollectionArticles getInstance(){
		return INSTANCE;
	}
	
	private ConcurrentLinkedQueue<Article> queue = new ConcurrentLinkedQueue<Article>();

	public void addArticle(Article article) {
		queue.add(article);
	}
	
	public Collection<Article> getArticles() {
		Collection<Article> returnedArticles = new ArrayList<Article>();				
		while (!queue.isEmpty()) {
			returnedArticles.add(queue.poll());
		}
		return returnedArticles;
	}
}

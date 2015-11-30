package de.myapp.rest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.myapp.model.Article;
import de.myapp.model.ArticlesList;
import de.myapp.processing.CollectionArticles;
import de.myapp.processing.PriceCalculatorThread;

@Path("/articles")
public class ArticleService {
	
	private CollectionArticles collectionArticles = CollectionArticles.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArticlesList getArticles(){
		ArticlesList list = new ArticlesList();
		list.getArticles().addAll(collectionArticles.getArticles());
		return list;
	}
	
	@POST
	public boolean consume(ArticlesList articlesList){
		if (articlesList.getArticles().isEmpty()){ 
			return false;
		}
		ExecutorService service = Executors.newFixedThreadPool(articlesList.getArticles().size());
		for (Article article : articlesList.getArticles()) {
			service.execute(new PriceCalculatorThread(article));
		}
		return true;
	}	
}

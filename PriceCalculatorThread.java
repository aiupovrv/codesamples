package de.myapp.processing;

import de.myapp.calculators.Calculator;
import de.myapp.calculators.CalculatorFactory;
import de.myapp.model.Article;

public class PriceCalculatorThread implements Runnable{

	private static CalculatorFactory factory = new CalculatorFactory();
	private CollectionArticles queue = CollectionArticles.getInstance();
	private Article article;

	public PriceCalculatorThread(Article article) {
		super();
		this.article = article;
	}

	public void run() {
		Calculator calculator = factory.getPriceCalculator(article.getType());
		
		article.setPrice(calculator.calculate(article));
		queue.addArticle(article);
	}
}

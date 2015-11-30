This task is part of a service that accepts packets of multiple articles via a rest-service, imulates asynchronous processing of those articles. Service also provides a REST method to retrieve processed articles.

Here you can see 3 classes.

"ArticleService" is a REST service. It has 2 methods. "Get" for getting new processed articles in JSON format. And "post" method, which takes in a list of unprocessed articles and initiates processing of each article in its own thread.

"Collection Articles" is a singleton class, which contains list of processed articles. This list is cleared after every "GET" request for "ArticleService". The articles are stored in a thread-safe queue.

"PriceCalculatorThread" is a runnable, which performs processing of a particular article. "Factory" pattern is used to create appropriate "Calculator" class. This is made for special processing of each article type.

I think that this code is an example of a good code, as it's vell structured, it's easy to understand. Classes don't perform any operations, which logically don't belong to them.


I don't have an example of bad code, but I can try to describe, how I uderstand what bad code is:

a) Classes are very big and have functions, which don't belong to them.
b) Methods are too large and hard to understand
c) Classes are tightly coupled, and it's very hard to add new features or perform changes

I was mostly working in a new projects, where our team could maintain code in a good state, but I know that it's hard to have good code everywhere in old legacy projects. But I believe that it's better to refactor bad code as soon as possible, otherwise it tends to spread.

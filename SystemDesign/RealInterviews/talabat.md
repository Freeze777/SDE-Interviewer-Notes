## PROBLEM STATEMENT
* We have an existing secondhand book-selling site, where people can add their used books. Other users can search and buy these books.
* We want to build a feature, where you can save your search term to your favorite searches and receive a daily email on any new books that match that search term, once every 24 hours.

## EXAMPLE
If a user searches for ‘Javascript’ and saves that search, any book with a title containing ‘Javascript’, which was added in the last 24 hours will be emailed to the user once a day.

## Functional Requirements:
* Users can add used books.
* Users can save searches to favorites.
* The system sends 1 email daily based on saved searches.

## Scale:
* 20M total users
* 4M daily active users
* 500k books added daily
* 5 keywords/user on average

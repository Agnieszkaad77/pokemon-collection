# Pokemon Card Collection
The purpose of the application is to allow users to collect and trade Pokemon cards.

## Table of contents:
* [Description](#description)
* [Technologies](#technologies)

## Description
### Registration
To participate in the game, user must register an account and then log in. During registration, you can agree to share your data in the ranking of the best users or skip this option.
### PokeCoins
PokeCoins are the in-game currency. Each player after registering an account gets 20 PokeCoins to start. 
### Purchase
User can buy a pack of five random cards. One pack, which is called as a "booster" in application, costs 5 PokeCoins. Cards in individual packs are always unique, however, when purchasing several packs of cards, user must take into account possible repetitions. Thus, user can finally have several cards of the same type in his collection.
### Collection
User can view his cards by clicking on the "My collection" button.
### Auctions
User can put individual cards for sale. To do so, he should display the "My Collection" tab. Under each card, there is an option to select the number of cards to sell and enter its price. After clicking the "Sell" button, an auction with a particular card will be created. Thanks to this, other users will be able to buy a single card without having to purchase the whole pack.
### Ranking
The "Ranking" tab shows the top 5 users with the largest number of cards who agreed to share data during registration. It is not necessary to be logged in to see the ranking.

## Technologies
* Java 17
* Spring MVC
* Spring Data JPA
* Lombok
* HTML
* Thymeleaf

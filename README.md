# BookApplication
When you really want to have a selfhostet book library which is requesting Google Book API for getting some Book information 
than this BookApplication is perfect for you! With this App you can make a connection to your database. Perfect for selfhosting!

### Getting Started
1. Configure your database in `application.yml`
2. Execute `gradle clean build` in order to fetch all required dependencies
3. Run it!
4. Be happy

### Rest Calls
| URL                                     | Description                                                |
|-----------------------------------------|------------------------------------------------------------|
| GET localhost:8080/api/book/{isbn}      | Requests Google Book API in order to get book informations |
| GET localhost:8080/api/books            | Returns all books persisted in database                    |
| GET localhost:8080/api/books/{title}    | Returns movie with {title} from database                   |
| PUT localhost:8080/api/books/{title}    | Updates a book with {title}                                |
| POST localhost:8080/api/books/{title}   | Creates a book with {title}                                |
| DELETE localhost:8080/api/books/{title} | Deletes a book with {title}                                |

### Used Technologies
Micronaut, Kotlin, REST, H2 database

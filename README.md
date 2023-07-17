# Explore Products

Explore Products is a web application that allows users to add and share different affiliated products from various online stores, such as Amazon, Flipkart, etc. Users can create an account and add products with details such as name, description, image, and product link. Users can then generate a sharable link on the site that they can share with other users. When someone opens the link, they will see all the products that the user has added and can click on them to buy them from the online store. The user who shared the link can earn some money if the link is an affiliated link.

## Technology Used

- Java(1.8): The backend programming language.
- Spring Boot(2.0.4.RELEASE): A framework for Java web applications. Modules used:
  - Spring Security: A module for authentication and authorization.
  - Spring Data JPA: A module for data access and manipulation using JPA.
- JSP: A technology for dynamic web pages.
- JavaScript: A scripting language for web interactivity.
- HTML, CSS, Bootstrap(3.3.6), jquery(2.2.4): Technologies for web design and layout.
- Oracle(11g): A relational database management system.
- Maven(4.0.0): A build automation tool.

## Installation

To install and run Explore Products on localhost, you need to have Java, Eclipse/STS, and Oracle Database installed on your system. You also need to clone the repository from GitHub using the following command:

```bash
git clone https://github.com/ib321/Explore-Products.git
```

Then, you need to configure the database connection properties in the `application.properties` file located in the `src/main/resources` folder. You need to specify the database URL, username, password, and driver class name. For example:

```properties
spring.datasource.url = jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username = system
spring.datasource.password = system
spring.datasource.driver-class-name = oracle.jdbc.driver.OracleDriver
```

After that, you can run the application using Eclipse by following these steps:

- Import the project as a Maven project.
- Maven clean the imported project from -- project menu -> Run As -> Maven clean
- Maven install the imported project from -- project menu -> Run As -> Maven build
- Enter ``Goals: clean install`` make sure to tick the checkbox named `Skip Tests`
- Then Click `Run` it will build and clean install the project
- Run the `ExploreProductsApplication.java` class as a Java application.

## Usage

To use Explore Products, open your browser and go to http://localhost:8098/. You will see the login page of the application where you can enter your username and password. If you don't have an account, you can create one by clicking on the Create new account link. You need to provide your unique username, password, and confirm password.

Once you are logged in, you can go to the product page where you can view all your products with options to delete or update them. You can add a new product by clicking on the "Add Product" button and entering the product name, description, image file (under 1 MB), and product link (affiliated link of Amazon or Flipkart).

You can also generate a link for your all products that you can share with other users. The link will look something like this: `http://localhost:8098/show-products?refShareId=MOe7mgAlXOivDg30Nvb6EQ%3D%3D`. When someone opens this link, they will see all your products and can click on them to buy from the online store. User could earn some money if the product link is an affiliated link.

## Contributing

If you want to contribute to Explore Products, you are welcome to do so. Please follow these steps:

- Fork the repository and create a new branch for your feature or bug fix.
- Write clear and concise code and comments following the coding standards and best practices.
- Change only the part of code you have written don't format the already written code.
- Test your code thoroughly and ensure it works as expected and does not break any existing functionality.
- Commit and push your changes to your branch and create a pull request with a detailed description of your changes and why they are needed.
- Wait for feedback or approval from the maintainer before merging your pull request.

## License

The Explore Products sample application is licensed under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0). Also see [LICENSE](LICENSE.txt) file for more details.

## Contact

If you have any questions or feedback about Explore Products, please feel free to contact me:

- Developer Name: Indian Bittu
- Email: newtonbk123@gmail.com
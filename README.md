# Electric Vehicle Service

This is a RESTful web service that provides basic CRUD (Create, Read, Update, Delete) operations for electric vehicles.

## Requirements

* Java 8 or higher
* Maven 3.6.0 or higher
* Docker 20.10.2 or higher
* Postman (optional)

## Setup

1. Clone the repository: `git clone https://github.com/your-username/electric-vehicle-service.git`
2. Navigate to the project directory: `cd electric-vehicle-service`
3. Build the project: `mvn clean package`
4. Run the project: `java -jar target/electric-vehicle-service-0.0.1-SNAPSHOT.jar`
5. Test the project using Postman or any HTTP client.

## Using Docker

1. Clone the repository: `git clone https://github.com/your-username/electric-vehicle-service.git`
2. Navigate to the project directory: `cd electric-vehicle-service`
3. Build the Docker image: `docker build -t electric-vehicle-service .`
4. Run the Docker container: `docker run -p 8080:8080 electric-vehicle-service`
5. Test the project using Postman or any HTTP client.

## API Endpoints

### GET /api/v1/electric-vehicles

Returns a list of all electric vehicles.

### GET /api/v1/electric-vehicles/{id}

Returns the electric vehicle with the specified ID.

### POST /api/v1/electric-vehicles

Creates a new electric vehicle.

### PUT /api/v1/electric-vehicles/{id}

Updates the electric vehicle with the specified ID.

### DELETE /api/v1/electric-vehicles/{id}

Deletes the electric vehicle with the specified ID.

## Technologies Used

* Spring Boot
* Spring Data JPA
* H2 Database
* Docker
* Maven

## Contributors

* Jorge Uriel Lopez Diaz de Leon

## License

This project is licensed under the MIT License - see the [MIT](LICENSE) file for details.

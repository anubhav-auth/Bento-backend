# Bento Backend 🍱

Bento is a full-stack food delivery platform connecting restaurants, customers, and delivery personnel. This repository contains the backend, built with **Spring Boot**, providing APIs for authentication, order management, restaurant listings, and real-time order tracking.

## 🚀 Features
- **Authentication & Authorization**: Secure login and access control for customers, restaurants, and delivery personnel.
- **Restaurant Management**: CRUD operations for restaurants and their menus.
- **Order Management**: Place, update, and track food orders in real time.
- **Payment Integration**: Secure transactions using **Razorpay** (*PENDING*).
- **Real-time Updates**: WebSockets or GraphQL subscriptions for live tracking (*To be integrated*).
- **Delivery Management**: Assign delivery personnel and track orders.
- **Admin Dashboard**: Manage users, orders, and restaurant data.

## 🛠️ Tech Stack
### Backend:
- **Spring Boot** - Backend framework.
- **MongoDB** - NoSQL database for scalable storage.
- **Spring Security** - Authentication & authorization.
- **WebSockets / GraphQL** - Real-time order tracking (*Planned*).
- **Docker** - Containerized deployment.
- **Render** - Cloud hosting.

## 🔧 Getting Started
### 1. Clone the Repository:
```sh
  git clone https://github.com/anubhav-auth/Bento-backend.git
  cd Bento-backend
```

### 2. Setup Environment Variables
Create a `.env` file and configure:
```env
SPRING_DATASOURCE_URL=mongodb://localhost:27017/bento
SPRING_SECURITY_SECRET=your_secret_key
RAZORPAY_API_KEY=your_api_key  # If payment integration is enabled
```

### 3. Run the Backend Server
#### Using Maven:
```sh
mvn spring-boot:run
```
#### Using Docker:
```sh
docker-compose up -d
```

### 4. Access API
- Base URL: `http://localhost:8080`

## 📂 Project Structure
```
📦 bento-backend
 ┣ 📂 src/main/java/com/bento
 ┃ ┣ 📂 controllers       # REST API Controllers
 ┃ ┣ 📂 services          # Business logic
 ┃ ┣ 📂 repositories      # Database interactions
 ┃ ┣ 📂 models            # Data models
 ┃ ┣ 📂 security          # JWT-based authentication
 ┃ ┗ 📜 BentoApplication.java # Main entry point
 ┣ 📜 application.yml      # Spring Boot config
 ┣ 📜 Dockerfile           # Containerization
 ┣ 📜 README.md            # Project documentation
┗ 📜 pom.xml              # Maven dependencies
```

## 🏗️ API Endpoints
### Authentication
- `POST /auth/register` - User registration.
- `POST /auth/login` - User login.

### Restaurants
- `GET /restaurants` - Fetch all restaurants.
- `POST /restaurants` - Add a new restaurant (*Admin only*).

### Orders
- `POST /orders` - Place an order.
- `GET /orders/{id}` - Get order status.
- `PUT /orders/{id}/status` - Update order status (*Restaurant/Delivery only*).

## 🔗 Contributing
We welcome contributions! Please fork the repository and submit a pull request.

## 📜 License
Bento is licensed under the **MIT License**.

## 📩 Contact
For any questions or feedback, reach out via **anubhavauth@gmail.com**.

---
Happy Coding! 🚀


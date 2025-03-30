# TinyX Backend

This repository contains the backend services for **TinyX**, a distributed micro-blogging platform. The backend is built with **Quarkus**, following a **microservices** architecture.

## 🚀 Features
- User & Post Management
- Social interactions (likes, follows, blocking)
- Search & Indexing
- Scalable, high-availability backend

## 🛠️ Technologies
- **Java 17**
- **Quarkus** (High-performance Java framework)
- **MongoDB** (Document storage)
- **ElasticSearch** (Search and indexing)
- **Neo4j** (Graph database for social connections)
- **Redis** (Message queue)
- **REST API with JAX-RS**
- **JUnit & Testcontainers** (Testing)


## 📦 Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-org/tinyx-backend.git
   cd tinyx-backend
   ```

2. Install dependencies and build:
   ```bash
   mvn clean package
   ```

3. Run the application:
   ```bash
   java -jar target/quarkus-app-runner.jar
   ```
   API runs on **http://localhost:8080**.

## 📌 Microservices in this repository
- **Post Service** → Manages posts (MongoDB)
- **Social Service** → Handles likes, follows, blocks (Neo4j)
- **Search Service** → Indexes posts (ElasticSearch)
- **User Timeline Service** → Manages personal timelines
- **Home Timeline Service** → Aggregates posts from followed users

## 🔧 Environment Variables
Create a **.env file** with the following environment variables:
```env
MONGO_URI=mongodb://localhost:27017/tinyx
ELASTICSEARCH_URI=http://localhost:9200
NEO4J_URI=bolt://localhost:7687
REDIS_URI=redis://localhost:6379
```

## 📜 License
MIT License

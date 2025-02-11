## System Design

### **1. Fundamentals of System Design**
- **Client-Server Architecture:** Basics of how clients interact with servers.
- **Distributed Systems:** Principles of distributed computing, advantages, and challenges.
- **CAP Theorem:** Consistency, Availability, and Partition tolerance trade-offs.
- **Scalability:** Vertical vs. horizontal scaling.

### Load Balancing:
  In software development, a load balancer is a system or device that distributes incoming network traffic across multiple servers. The goal is to ensure no single server gets overwhelmed with too many requests, which could lead to slower performance or failure.

- Why Do We Need a Load Balancer?
  1. **Scalability**: As your application grows and gets more traffic, you might need to add more servers to handle the load. A load balancer ensures that requests are directed to the right servers, even as you scale horizontally (adding more servers).
  2. **Fault Tolerance**: If one server fails or is under maintenance, the load balancer can route traffic to the remaining healthy servers, ensuring your app stays online and doesn’t experience downtime.
  3. **Better Performance**: By spreading the load across multiple servers, each server doesn’t get overwhelmed and can perform its tasks more quickly, improving response times and reducing latency.

- Types of Load Balancers/ Algorithm
  1. **Round Robin**: The simplest form, where requests are distributed in a circular fashion. Server 1 gets the first request, server 2 gets the second, and so on. Once all servers are used, it starts over.
  2. **Least Connections**: The load balancer routes traffic to the server with the least number of active connections (requests). This helps ensure that the least busy server gets more traffic.
  3. **IP Hash**: This technique uses a hash of the IP address of the client to determine which server will handle the request. It ensures that a specific user will always be directed to the same server, which is useful for session persistence.
  4. **Weighted Load Balancing**: Some servers may be more powerful than others. With weighted load balancing, more traffic can be directed to the more powerful servers.

- When to Use a Load Balancer in Software Development?
  1. **High Traffic Websites**: If you're building a website or application expected to get a lot of visitors, you’ll want to use a load balancer to ensure the system can handle the load.
  2. **Microservices Architecture**: In a system where you have multiple services running, each potentially on different servers, a load balancer helps route traffic to the correct service.
  3. **Fault Tolerance**: If you want your system to be robust against server failure and downtime, the load balancer will ensure that traffic is directed only to healthy servers.
  4. **API Requests**: If you’re building an API that needs to handle high concurrency, load balancing helps distribute the load of API requests.

---

### **2. Networking Basics**
- **HTTP/HTTPS Protocols:** Request-response model, status codes, REST APIs.
- **DNS:** How domain names are resolved to IP addresses.
- **CDNs:** 
  - CDNs (Content Delivery Networks)
  - A network of geographically distributed servers that cache and deliver web content closer to the user's location.
  **Role**:
  - Reduces latency by minimizing the distance between the server and the user.
  - Balances traffic loads to avoid server overload.
  - Improves website performance and reliability.
- **Sockets and WebSockets:** Real-time communication protocols.
- **Firewalls and Proxies:** Security and intermediary layers.

---

### **3. Key Design Concepts**

#### **High Availability**
- **Fault Tolerance**: The ability of a system to continue functioning even when components fail.
- **Redundancy**: Duplication of critical components (e.g., servers, databases) to ensure availability in case of failures.
- **Failover Mechanisms**: Automatic switching to a backup system or resource when the primary system fails, ensuring minimal downtime.

#### **Partitioning (Sharding)**
- **Definition**: Dividing a large dataset into smaller, manageable chunks (shards) and distributing them across multiple storage nodes.
- **Benefits**:
  - Improves performance by reducing the load on individual nodes.
  - Enhances scalability by adding more nodes as needed.
- **Techniques**:
  - **Range-Based**: Divides data based on ranges of a key (e.g., date ranges).
  - **Hash-Based**: Uses a hash function to distribute data evenly.
  - **Geographical Partitioning**: Distributes data based on location.

#### **Caching**
- **Definition**: Storing frequently accessed data in a high-speed layer to reduce latency and database load.
- **Techniques**:
  - **Write-Through**: Updates cache and database simultaneously.
  - **Write-Back**: Writes to cache and later syncs with the database.
  - **Read-Through**: Cache fetches data from the database when needed.
- **Tools**:
  - **Redis**: In-memory data structure store supporting various data types.
  - **Memcached**: Lightweight in-memory key-value store for caching.
- **Cache Invalidation**:
  - **Manual**: Explicitly removing or updating cache entries.
  - **Time-Based**: Setting expiration times for cache entries.
  - **Event-Based**: Invalidating cache upon specific changes.

#### **Database Indexing**
- **Definition**: Creating a data structure (e.g., B-Tree, Hash Index) to speed up data retrieval.
- **How It Helps**:
  - Reduces the number of disk reads during a query.
  - Makes searches for specific rows in a table faster.
- **Trade-offs**:
  - Additional storage required for the index.
  - Slower write operations due to index updates.

#### **Data Replication**
- **Definition**: Copying data across multiple servers to improve availability and fault tolerance.
- **Types**:
  - **Master-Slave**: One primary server (master) handles writes; replicas (slaves) handle reads.
  - **Master-Master**: All servers handle both reads and writes; requires conflict resolution.
- **Consistency**:
  - **Strong Consistency**: Ensures all replicas are immediately updated.
  - **Eventual Consistency**: Updates propagate over time, and replicas eventually converge.

#### **Rate Limiting**
- **Definition**: Controlling the number of requests a user or client can make to an API within a specific timeframe.
- **Purpose**:
  - Prevents abuse and ensures fair usage.
  - Protects servers from being overwhelmed.
- **Techniques**:
  - **Token Bucket**: Users are given tokens for requests, and tokens regenerate over time.
  - **Leaky Bucket**: Requests are processed at a steady rate, queuing excess requests.
  - **Fixed Window**: Limits requests within a fixed time window (e.g., 100 requests/min).
  - **Sliding Window**: Adjusts limits based on a rolling time frame.

---

### **4. Databases**

#### **Relational Databases (SQL)**
- **Transactions**:
  - A sequence of database operations performed as a single logical unit of work.
  - Must be **atomic**, ensuring all or none of the operations are applied.
- **Normalization**:
  - Organizing data into tables to reduce redundancy and improve data integrity.
  - Divides large tables into smaller ones and defines relationships between them.
  - Normal forms (1NF, 2NF, 3NF, etc.) specify levels of normalization.
- **ACID Properties**:
  - **Atomicity**: Ensures a transaction is either fully completed or not at all.
  - **Consistency**: Guarantees the database transitions from one valid state to another.
  - **Isolation**: Concurrent transactions do not interfere with each other.
  - **Durability**: Once a transaction is committed, changes are permanent, even after system failures.

#### **NoSQL Databases**
- **Types**:
  - **Key-Value Stores**:
    - Data is stored as key-value pairs.
    - Example: Redis, DynamoDB.
  - **Document Stores**:
    - Data is stored as semi-structured documents (e.g., JSON or BSON).
    - Example: MongoDB, CouchDB.
  - **Graph Databases**:
    - Data is represented as nodes and edges for relationships.
    - Example: Neo4j, ArangoDB.
  - **Column-Family Stores**:
    - Data is stored in columns rather than rows, optimized for aggregation queries.
    - Example: Cassandra, HBase.
- **Advantages**:
  - High scalability and flexibility.
  - Schema-less or schema-optional design.

#### **Database Scalability**
- **Read Replicas**:
  - Additional database copies used for read-only queries to reduce the load on the primary database.
  - Asynchronous replication is common.
- **Sharding**:
  - Divides data horizontally across multiple databases based on a shard key.
  - Improves performance by distributing queries across shards.
- **Partitioning**:
  - Splits large datasets into smaller partitions for improved performance and manageability.
  - Types:
    - **Horizontal Partitioning**: Rows are divided across tables.
    - **Vertical Partitioning**: Columns are divided across tables.

---

### **5. Architectural Patterns**

#### **Monolithic vs. Microservices**
- **Monolithic**:
  - **Definition**: A single unified application where all components are tightly coupled.
  - **Pros**:
    - Simpler to develop, test, and deploy initially.
    - Easier to manage for small teams.
  - **Cons**:
    - Difficult to scale and maintain as the application grows.
    - A single failure can affect the entire system.
- **Microservices**:
  - **Definition**: A collection of loosely coupled, independently deployable services.
  - **Pros**:
    - Scalable and fault-tolerant.
    - Enables independent development and deployment of services.
  - **Cons**:
    - Higher complexity in communication, monitoring, and orchestration.
    - Requires strong DevOps practices.

#### **Event-Driven Architecture**
- **Definition**: A design paradigm where services communicate by producing and consuming events.
- **Use Cases**:
  - Real-time systems (e.g., stock trading platforms, IoT applications).
  - Asynchronous workflows (e.g., order processing systems).
- **Message Brokers**:
  - **Kafka**: Distributed, high-throughput system for streaming events.
  - **RabbitMQ**: Lightweight, general-purpose message broker supporting multiple messaging protocols.

#### **Service-Oriented Architecture (SOA)**
- **Definition**: An architectural style where services are designed to be loosely coupled, reusable, and interoperable over a network.
- **Characteristics**:
  - Services communicate through standardized protocols (e.g., SOAP, REST).
  - Designed for business functionalities rather than technical ones.
- **Advantages**:
  - Promotes code reuse and scalability.
  - Facilitates integration of heterogeneous systems.

#### **CQRS (Command Query Responsibility Segregation)**
- **Definition**: Separates the handling of commands (write operations) and queries (read operations) into different models.
- **Benefits**:
  - Optimized read and write performance independently.
  - Enables scalability and easier implementation of event sourcing.
- **Use Cases**:
  - Systems with high read/write disparity.
  - Applications requiring event sourcing or audit logs.

#### **Database Normalization vs. Denormalization**
- **Normalization**:
  - **Definition**: Organizing data into tables to minimize redundancy and ensure consistency.
  - **When to Apply**:
    - When data integrity and consistency are priorities.
    - For transactional systems with frequent updates.
- **Denormalization**:
  - **Definition**: Combining tables to reduce joins and improve read performance.
  - **When to Apply**:
    - When performance is critical, and read-heavy queries dominate.
    - For data warehouses or OLAP systems.
---

### **6. System Design Components**

#### **Authentication and Authorization**
- **Authentication**: Verifying the identity of a user or system (e.g., login with username and password).
- **Authorization**: Determining what resources or actions a user/system is allowed to access.
- **OAuth**:
  - Open standard for authorization, allowing third-party apps to access user resources without exposing credentials.
  - Example: Logging into an app using Google or Facebook.
- **JWT (JSON Web Token)**:
  - Compact, URL-safe token for securely transmitting information.
  - Used for stateless authentication in APIs.
- **SSO (Single Sign-On)**:
  - Allows users to log in once and access multiple systems without re-authenticating.
  - Example: Enterprise systems using services like Okta or Azure AD.

#### **Storage Systems**
- **File Storage**:
  - Data is organized in files and directories.
  - Suitable for traditional systems and applications.
  - Example: Network File Systems (NFS).
- **Block Storage**:
  - Stores raw blocks of data, optimized for high-performance databases.
  - Example: AWS EBS, SAN (Storage Area Network).
- **Object Storage**:
  - Stores data as objects with metadata and unique identifiers.
  - Ideal for unstructured data and large-scale systems.
  - Example: AWS S3, Google Cloud Storage.

#### **Message Queues**
- **Definition**: Mechanisms for asynchronous communication between distributed components.
- **Benefits**:
  - Decouples producers and consumers, improving scalability.
  - Handles retries and ensures message delivery.
- **Tools**:
  - **Kafka**: High-throughput, distributed message streaming platform.
  - **RabbitMQ**: Lightweight, general-purpose message broker supporting multiple protocols.

#### **Search Systems**
- **Definition**: Tools that enable efficient full-text search across large datasets.
- **Features**:
  - Indexing for fast query performance.
  - Advanced search capabilities (e.g., fuzzy matching, filtering).
- **Tools**:
  - **Elasticsearch**: Distributed search engine for full-text search and analytics.
  - **Solr**: Open-source search platform built on Apache Lucene.

#### **Monitoring and Logging**
- **Monitoring**:
  - Tracks system health, performance, and resource utilization.
  - Tools:
    - **Prometheus**: Time-series database with powerful querying.
    - **Grafana**: Visualization tool for real-time monitoring dashboards.
- **Logging**:
  - Captures and analyzes application/system logs for troubleshooting.
  - Tools:
    - **ELK Stack**: Elasticsearch, Logstash, Kibana for log aggregation and analysis.
    - **Fluentd**: Unified logging layer for data collection.

---

### **7. Design Principles**  

#### **SOLID Principles**
- **Definition**: A set of five principles for designing maintainable, scalable, and testable object-oriented software:
  1. **S**ingle Responsibility Principle:
     - A class should have only one reason to change.
     - Example: Separate business logic and database operations.
  2. **O**pen/Closed Principle:
     - Software entities should be open for extension but closed for modification.
     - Example: Use interfaces or inheritance for new functionality.
  3. **L**iskov Substitution Principle:
     - Subtypes must be substitutable for their base types without breaking functionality.
     - Example: Derived classes should not override methods with different behavior.
  4. **I**nterface Segregation Principle:
     - Clients should not be forced to depend on methods they do not use.
     - Example: Split large interfaces into smaller, specific ones.
  5. **D**ependency Inversion Principle:
     - High-level modules should not depend on low-level modules; both should depend on abstractions.
     - Example: Use dependency injection.

---

#### **12-Factor App Methodology**
- **Definition**: A set of best practices for building scalable and maintainable SaaS applications. Key factors:
  1. **Codebase**: Single codebase tracked in version control.
  2. **Dependencies**: Explicitly declare and isolate dependencies.
  3. **Config**: Store configuration in the environment, not code.
  4. **Backing Services**: Treat attached services (e.g., databases) as replaceable resources.
  5. **Build, Release, Run**: Separate build and deploy stages.
  6. **Processes**: Execute the app as one or more stateless processes.
  7. **Port Binding**: Export services via port binding.
  8. **Concurrency**: Scale out by running multiple instances.
  9. **Disposability**: Optimize for fast startup and graceful shutdown.
  10. **Dev/Prod Parity**: Keep development, staging, and production environments as similar as possible.
  11. **Logs**: Treat logs as event streams.
  12. **Admin Processes**: Run administrative tasks as one-off processes.

---

#### **Idempotency**
- **Definition**: An operation is idempotent if performing it multiple times has the same effect as performing it once.
- **Importance in APIs**:
  - Ensures consistency in case of retries due to network issues.
  - Example: `PUT` (update) operations are typically idempotent; `POST` (create) is not inherently idempotent unless designed to be.
- **Implementation**:
  - Use unique identifiers (e.g., request IDs) to track requests.
  - Avoid state changes for duplicate requests.

---

#### **Back-of-the-Envelope Calculations**
- **Definition**: Quick, rough calculations to estimate system requirements or capacity.
- **Applications**:
  - Estimate storage needs, bandwidth, or server capacity.
  - Example: Estimating traffic for a service handling 10 million requests/day:
    - **Request Size**: 1 KB/request.
    - **Total Data**: \(10 \times 10^6 \times 1 \, \text{KB} = 10 \, \text{GB/day}\).
- **Benefits**:
  - Guides initial design decisions.
  - Helps identify potential bottlenecks early.

---

### **8. APIs and Communication**

#### **REST vs. GraphQL**
- **REST (Representational State Transfer)**:
  - **Key Features**:
    - Follows HTTP methods (GET, POST, PUT, DELETE).
    - Resources are identified by URLs.
    - Fixed endpoints for specific operations.
  - **Pros**:
    - Simple and widely adopted.
    - Caching is straightforward.
  - **Cons**:
    - Over-fetching (retrieving unnecessary data).
    - Under-fetching (multiple requests to get all required data).
- **GraphQL**:
  - **Key Features**:
    - Query language for APIs that allows clients to request exactly the data they need.
    - Single endpoint for all queries and mutations.
  - **Pros**:
    - Reduces over-fetching and under-fetching.
    - Flexible and efficient for client-side data fetching.
  - **Cons**:
    - More complex to set up and maintain.
    - Caching requires additional strategies.

---

#### **gRPC**
- **Definition**: A high-performance, open-source Remote Procedure Call (RPC) framework developed by Google.
- **Key Features**:
  - Uses **Protocol Buffers (Protobuf)** for defining services and data serialization.
  - Supports bidirectional streaming for real-time communication.
  - Efficient and compact binary communication over HTTP/2.
- **Use Cases**:
  - Inter-service communication in microservices.
  - Real-time systems requiring low-latency communication.
- **Pros**:
  - High performance and low latency.
  - Strongly typed contracts using Protobuf.
- **Cons**:
  - Less human-readable compared to JSON.
  - Requires both client and server to implement gRPC-specific protocols.

---

#### **Webhooks and Polling**
- **Webhooks**:
  - **Definition**: A server sends real-time updates to a client by triggering HTTP callbacks when an event occurs.
  - **Pros**:
    - Immediate updates.
    - Reduces unnecessary network traffic.
  - **Cons**:
    - Requires reliable handling of incoming requests.
    - Debugging and error recovery can be challenging.
  - **Example**: Stripe or PayPal sending a notification when a payment is processed.
- **Polling**:
  - **Definition**: The client repeatedly sends requests to the server at regular intervals to check for updates.
  - **Pros**:
    - Simple to implement.
    - Works even if the client doesn’t support receiving callbacks.
  - **Cons**:
    - High network overhead and latency.
    - Inefficient for real-time updates.
  - **Example**: A news app checking for new articles every minute.

---

### **9. Cloud and DevOps Concepts**
- **Cloud Providers:** AWS, Azure, GCP (Compute, Storage, Network offerings).
- **CI/CD Pipelines:** Automating deployments.
- **Infrastructure as Code (IaC):** Tools like Terraform, Ansible.
- **Containerization:** Docker, Kubernetes, orchestration basics.

---

### **10. Design Case Studies**
- Design common systems to apply your knowledge:
  - **URL Shortener:** Core components, database schema, and scaling considerations.
  - **Distributed Cache:** Eviction policies, consistency.
  - **Social Media Feed:** Newsfeed generation, pagination, push vs. pull models.
  - **E-commerce System:** Inventory management, payment systems, cart design.
  - **Video Streaming Service:** CDN usage, video encoding, adaptive bitrate streaming.
  - **Ride-Sharing System:** Real-time location tracking, dynamic pricing.

---

### **11. Trade-offs and Decision-Making**

#### **Latency vs. Throughput**
- **Latency**:
  - The time taken to process a single request or operation (response time).
  - Low latency is critical for real-time systems (e.g., video streaming, gaming).
- **Throughput**:
  - The number of requests or operations a system can handle per unit time.
  - High throughput is essential for batch processing systems (e.g., data pipelines).
- **Balancing**:
  - Optimizing for one can affect the other (e.g., caching improves latency but might reduce throughput due to additional memory usage).
  - Use load balancers, caching, and resource scaling to achieve a balance.

---

#### **Consistency Models**
- **Strong Consistency**:
  - Ensures that all reads return the most recent write, maintaining a single consistent state across all replicas.
  - Pros:
    - Predictable and reliable.
    - Suitable for financial transactions or critical systems.
  - Cons:
    - Higher latency due to synchronization between replicas.
- **Eventual Consistency**:
  - Guarantees that all replicas will eventually converge to the same state, but not immediately.
  - Pros:
    - High availability and performance in distributed systems.
    - Suitable for non-critical applications (e.g., social media feeds).
  - Cons:
    - Data may temporarily be stale or inconsistent.

---

#### **Storage Models: Hot vs. Cold Storage**
- **Hot Storage**:
  - High-performance storage optimized for frequently accessed (active) data.
  - Characteristics:
    - Low latency and high throughput.
    - Examples: SSDs, in-memory databases (e.g., Redis).
  - Use Cases:
    - Real-time analytics, transactional databases.
- **Cold Storage**:
  - Cost-efficient storage for infrequently accessed (archival) data.
  - Characteristics:
    - High latency and low throughput.
    - Examples: AWS Glacier, tape drives.
  - Use Cases:
    - Data backups, long-term archival, compliance storage.

---

### **12. Security and Reliability**
- **Encryption:** Data at rest and in transit.
- **Rate Limiting and Throttling:** Preventing DDoS attacks.
- **Backup and Disaster Recovery:** Strategies and RTO/RPO metrics.
- **Zero Downtime Deployments:** Blue-green deployments, canary releases.

---

### **13. Documentation and Diagrams**
- **Architectural Diagrams:** HLD diagrams using tools like Lucidchart, Draw.io.
- **Sequence Diagrams:** User and system interaction flows.
- **ER Diagrams:** Database modeling.

---

### **14. Soft Skills for System Design**
- **Requirement Gathering:** Asking the right questions to stakeholders.
- **Collaboration:** Working with cross-functional teams.
- **Prioritization:** Focus on core requirements first.

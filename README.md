🚦 Smart Traffic Signal Optimization System

A smart, real-time traffic signal control platform that optimizes traffic flow using dynamic signal timing logic, intersection analytics, and a live dashboard—built with Java Spring Boot and Thymeleaf.

📌 Project Description:-

 Problem Statement:
🚗 Urban areas frequently suffer from poorly timed traffic signals, leading to long queues, congestion, and wasted fuel. Fixed-timing traffic lights don't adapt to real-world scenarios.

🎯 Goal: To design an intelligent signal management system that automatically optimizes green/red durations at intersections based on congestion data, while allowing real-time visualization and manual admin overrides.

✅ Solution Summary
🧠 The Smart Traffic Signal Optimization System continuously monitors intersection status and adjusts signal timings using configurable algorithms. It logs history, allows admin intervention, and presents the data through charts and tables on an intuitive dashboard.

🔍 Key highlights:

Tracks real-time intersection statuses.

Analyzes signal performance.

Provides visual insights.

Exports signal history and charts.


🚀 Key Features

Category	Features: 

🛣️ Signal Logic	Dynamic green-yellow-red adjustment based on real-time congestion data
📍 Intersection Panel	Add/update intersections with location metadata and control switches
📊 Live Dashboard	Real-time table of signal status, timings, and visual indicators
🧾 History Analytics	Detailed signal timing history logs with optimization reasons
📈 Live Charts	Visualize historical signal performance with downloadable graphs
📥 Export Options	Download signal history in CSV/Excel and charts as PNG
🔄 Auto-Refresh	Real-time dashboard refresh every 10 seconds
🌐 Google Maps (opt)	Plots intersections on an interactive map
👨‍💼 Admin Override	Manual override of signal timings via admin panel

🧑‍💻 Tech Stack
Layer	Technology
Backend	Java 17, Spring Boot, Spring MVC, JPA
Frontend	Thymeleaf, Bootstrap, JavaScript
Charts & UI	Chart.js, HTML5, CSS3
Database	MySQL
Reporting	Apache POI (Excel Export)
Deployment	JAR Executable on  AWS EC2 

##  🖼️ UI Screenshots : 

>📍 Live Dashboard
Real-time traffic status at all intersections:

 ![Live Dashboard](https://github.com/Vivek0375/SmartTrafficOptimizer/blob/6a98f216cbee287f13031650e15d1605d0aefb07/smart-traffic/Screenshot%202025-07-22%20123854.png)


📈 Live Chart for Signal Timing
Visualizes historical signal adjustments for performance analysis

![Live Chart](https://github.com/Vivek0375/SmartTrafficOptimizer/blob/ea42aeb6fb4f6a7d47e2d3338f44c64ec11dbb00/smart-traffic/Screenshot%202025-07-22%20123810.png)

📁 History Panel & SQL View:

Tracks every optimization decision with timestamps:-

![SQL Functioning](https://github.com/Vivek0375/SmartTrafficOptimizer/blob/5991134c77741cc4948d7e451d575f7160885c87/smart-traffic/Screenshot%202025-07-22%20160003.png)

![Data History](https://github.com/Vivek0375/SmartTrafficOptimizer/blob/5991134c77741cc4948d7e451d575f7160885c87/smart-traffic/Screenshot%202025-07-22%20160034.png)


---

🛠️ How to Run Locally:-
✅ Prerequisites

-Java 17+
-Maven
-MySQL (with a database named trafficdb or update in application.properties)

▶️ Clone & Run

-git clone https://github.com/Vivek0375/SmartTrafficOptimizer.git

-cd SmartTrafficOptimizer

📦 Build the Project

mvn clean install

🚀 Start the Application

mvn spring-boot:run

🌐 Access the App
Open http://localhost:8080 in your browser.

⚙️ Configuration
Edit your application.properties file:

spring.datasource.url=jdbc:mysql://localhost:3306/trafficdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true



📁 Project Structure

![Structure](https://github.com/Vivek0375/SmartTrafficOptimizer/blob/bc71d620667001f3d06a5c0c7319ad6d1c3951d8/smart-traffic/Screenshot%202025-07-27%20010734.png)

## 🧑‍💻 Author

**Vivek Yadav**

* LinkedIn: [www.linkedin.com/in/vivekyadav375](https://www.linkedin.com/in/vivekyadav375)
* GitHub: [@Vivek0375](https://github.com/Vivek0375)

## 📜 LICENCE

This project is licensed under [MIT LICENSE](LICENSE)

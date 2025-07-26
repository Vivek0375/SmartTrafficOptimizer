#  Smart Traffic Signal Optimization System

A Spring Boot-based web application that intelligently manages and optimizes traffic signal timings at various intersections based on real-time congestion data. It helps improve traffic flow and provides a dashboard for visualization and admin control.

---

##  Features

- ⏱️ **Dynamic Signal Optimization:** Adjusts green, red, and yellow timings using congestion data.
- 📍 **Intersection Management:** Add, update, and monitor intersections with location metadata.
- 📊 **Live Dashboard:** Real-time view of traffic conditions and current signal timings.
- 🧾 **Signal History Tracking:** Logs historical timing changes and optimization reasons.
- 🌐 **Google Maps Integration:** (Optional) Visual display of intersections on an interactive map.
- 👨‍💼 **Admin Panel:** Manual override for signal timings and intersection configurations.
- 📥 **Export Reports:** Download signal history as CSV/Excel and charts as images.
- 🔁 **Auto Refresh:** Dashboard updates every 10 seconds with the latest traffic signal data.
- 📂 **Backend Built with Spring Boot (Java 17)** using JPA, Thymeleaf, Lombok, and MySQL.

---

##  📸   Screenshots : 

>->  screenshots of the live dashboard:
>
> 
> ![Live Dashboard](https://github.com/Vivek0375/SmartTrafficOptimizer/blob/6a98f216cbee287f13031650e15d1605d0aefb07/smart-traffic/Screenshot%202025-07-22%20123854.png)


-> Screenshot of Live Chart to analyse:


![Live Chart](https://github.com/Vivek0375/SmartTrafficOptimizer/blob/ea42aeb6fb4f6a7d47e2d3338f44c64ec11dbb00/smart-traffic/Screenshot%202025-07-22%20123810.png)

---

## 🛠️ Technologies Used

| Layer         | Tools/Tech                     |
|--------------|--------------------------------|
| Backend      | Java 17, Spring Boot, Spring MVC, Spring JPA |
| Security     | Role-Based Access (if added)   |
| Frontend     | Thymeleaf, Bootstrap, Chart.js, JavaScript |
| Database     | MySQL                          |
| Reporting    | Apache POI (Excel), Chart.js   |
| Deployment   | JAR executable or AWS (optional) |

---

## 🧑‍💻 Project Setup (Run Locally)

### Prerequisites

- Java 17
- Maven
- MySQL

### Clone & Run

```bash
git clone https://github.com/<your-username>/smart-traffic-signal.git
cd smart-traffic-signal

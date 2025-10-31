
# Manchester Metro Route Planner 🚇

A Java application that calculates routes on a Manchester-style metro/tram network.

The app supports two different routing strategies:
1. Shortest path (fewest stops or lowest total weight) using Dijkstra’s Algorithm
2. Least changes (minimise line/interchange changes) using BFS / layered search

This project demonstrates object-oriented programming (OOP) design and graph algorithms.

---

## ✨ Features

- ✅ OOP design – stations, connections, and lines are modelled as objects
- ✅ Two route modes:
  - Shortest Path → Dijkstra’s algorithm
  - Least Changes → BFS / modified search
- ✅ Input validation for unknown stations
- ✅ Text-based route output (step-by-step)
- ✅ Easily extendable to new stations or lines
- ✅ Useful for teaching or demonstration purposes

---

## 🧠 How it works

- The metro network is represented as a graph:
  - Nodes = stations
  - Edges = connections (with optional weights such as time)
- Dijkstra’s Algorithm finds the minimum-cost path between two stations
- BFS finds a path that minimises transfers (even if slightly longer)

---

## 🚀 Getting Started

### Prerequisites
- Java 17 (or Java 11+)
- A terminal or IDE such as IntelliJ or VS Code

### Clone the repository
git clone https://github.com/E-RAFFERTY/metro-route-planner.git
cd metro-route-planner

### Compile
javac -d out $(find src -name "*.java")

### Run
java -cp out ui.Main

---

## 🧮 Algorithms

### Dijkstra’s Algorithm
- Treats the metro as a weighted graph
- Each edge has a cost (1 for stops, or travel time)
- Guarantees the optimal path for non-negative weights

### BFS (Least-Changes)
- Treats the network as an unweighted graph
- Explores by number of hops
- Prefers staying on the same line
- Good for minimising transfers

---

## 🧱 Object-Oriented Design

Station → stores name, zone, and neighbours  
Connection → represents links between stations  
MetroNetwork → manages all stations and connections  
RouteFinder → interface for route-finding algorithms  
DijkstraRouteFinder and BfsRouteFinder implement RouteFinder

This structure allows adding new algorithms, constraints, or GUIs easily.



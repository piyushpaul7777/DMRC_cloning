import java.util.*;
public class grap {
    static class Edge {
        String destination;
        int weight;
        
        public Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class Graph {
        private Map<String, List<Edge>> adjacencyList;
        public Graph() {
            adjacencyList = new HashMap<>();
        }
        public void addEdge(String source, String destination, int weight) {
            adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(destination, weight));
            adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(new Edge(source, weight)); // For undirected graph
            // For undirected graph
        
        }
        public void printMap() {
            System.out.println("Metro Map:");
            for (String station : adjacencyList.keySet()) {
                System.out.print(station + " -> ");
                for (Edge edge : adjacencyList.get(station)) {
                    System.out.print(edge.destination + " (" + edge.weight + "), ");
                }
                System.out.println();
            }
        }
        
        public Map<String, Integer> dijkstra(String start) {
            Map<String, Integer> distances = new HashMap<>();
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
            pq.add(new AbstractMap.SimpleEntry<>(start, 0));
            distances.put(start, 0);
        
            // While there are still nodes to process
            while (!pq.isEmpty()) {
                Map.Entry<String, Integer> current = pq.poll();
                String currentNode = current.getKey();
                int currentDistance = current.getValue();
        
                // Skip if we have already found a shorter path to this node
                if (currentDistance > distances.getOrDefault(currentNode, Integer.MAX_VALUE)) continue;
        
                // Check all neighbors of the current node
                for (Edge neighbor : adjacencyList.getOrDefault(currentNode, Collections.emptyList())) {
                    int newDist = currentDistance + neighbor.weight;
                    // If a shorter path to the neighbor is found, update the distance and add to the priority queue
                    if (newDist < distances.getOrDefault(neighbor.destination, Integer.MAX_VALUE)) {
                        distances.put(neighbor.destination, newDist);
                        pq.add(new AbstractMap.SimpleEntry<>(neighbor.destination, newDist));
                    }
                }
            }
            return distances;
        }
        public List<String> shortestPath(String start, String end) {
            Map<String, Integer> distances = dijkstra(start);
            Map<String, String> previous = new HashMap<>();
        
            // Check if there is no path from start to end
            if (!distances.containsKey(end) || distances.get(end) == Integer.MAX_VALUE) {
                return Collections.emptyList(); // or return a message indicating no path
            }
        
            // Reconstruct the path from end to start using the distances map
            List<String> path = new ArrayList<>();
            String currentNode = end;
            while (!currentNode.equals(start)) {
                path.add(0, currentNode); // Add to the beginning of the list
                for (Edge neighbor : adjacencyList.get(currentNode)) {
                    if (distances.getOrDefault(neighbor.destination, Integer.MAX_VALUE) == distances.get(currentNode) - neighbor.weight) {
                        currentNode = neighbor.destination;
                        break;
                    }
                }
            }
        
            path.add(0, start); // Add the start node to the beginning of the list
            return path;
        }
    }
    public static void main(String[] args) {
        Graph metroGraph = new Graph();
    
        // Add metro stations and connections (edges) to the graph
        metroGraph.addEdge("Rajiv Chowk", "Kashmere Gate", 5);
        metroGraph.addEdge("Kashmere Gate", "Rajiv Chowk", 5);
        metroGraph.addEdge("Rajiv Chowk", "Central Secretariat", 3);
        metroGraph.addEdge("Central Secretariat", "Rajiv Chowk", 3);
        metroGraph.addEdge("Kashmere Gate", "Chandni Chowk", 2);
        metroGraph.addEdge("Chandni Chowk", "Kashmere Gate", 2);
        metroGraph.addEdge("Central Secretariat", "INA", 4);
        metroGraph.addEdge("INA", "Central Secretariat", 4);
        metroGraph.addEdge("INA", "AIIMS", 2);
        metroGraph.addEdge("AIIMS", "INA", 2);
        metroGraph.addEdge("Chandni Chowk", "New Delhi", 3);
        metroGraph.addEdge("New Delhi", "Chandni Chowk", 3);
        metroGraph.addEdge("New Delhi", "Rajiv Chowk", 6);
        metroGraph.addEdge("Rajiv Chowk", "New Delhi", 6);

        metroGraph.addEdge("AIIMS", "Hauz Khas", 4);
        metroGraph.addEdge("Hauz Khas", "AIIMS", 4);
        metroGraph.addEdge("Hauz Khas", "Malviya Nagar", 3);
        metroGraph.addEdge("Malviya Nagar", "Hauz Khas", 3);
        metroGraph.addEdge("Malviya Nagar", "Saket", 2);
        metroGraph.addEdge("Saket", "Malviya Nagar", 2);
        metroGraph.addEdge("Saket", "Qutub Minar", 4);
        metroGraph.addEdge("Qutub Minar", "Saket", 4);
        metroGraph.addEdge("Qutub Minar", "Chhatarpur", 3);
        metroGraph.addEdge("Chhatarpur", "Qutub Minar", 3);
        metroGraph.addEdge("Chhatarpur", "Sultanpur", 2);
        metroGraph.addEdge("Sultanpur", "Chhatarpur", 2);

        metroGraph.addEdge("Sultanpur", "Ghitorni", 3);
        metroGraph.addEdge("Ghitorni", "Sultanpur", 3);
        metroGraph.addEdge("Ghitorni", "Arjan Garh", 2);
        metroGraph.addEdge("Arjan Garh", "Ghitorni", 2);
        metroGraph.addEdge("Arjan Garh", "Guru Dronacharya", 4);
        metroGraph.addEdge("Guru Dronacharya", "Arjan Garh", 4);
        metroGraph.addEdge("Guru Dronacharya", "Sikandarpur", 3);
        metroGraph.addEdge("Sikandarpur", "Guru Dronacharya", 3);
        metroGraph.addEdge("Sikandarpur", "MG Road", 2);
        metroGraph.addEdge("MG Road", "Sikandarpur", 2);
        metroGraph.addEdge("MG Road", "IFFCO Chowk", 4);
        metroGraph.addEdge("IFFCO Chowk", "MG Road", 4);

        metroGraph.addEdge("IFFCO Chowk", "Huda City Centre", 3);
        metroGraph.addEdge("Huda City Centre", "IFFCO Chowk", 3);
        metroGraph.addEdge("Huda City Centre", "Noida Sector 18", 2);
        metroGraph.addEdge("Noida Sector 18", "Huda City Centre", 2);
        metroGraph.addEdge("Noida Sector 18", "Noida Sector 15", 4);
        metroGraph.addEdge("Noida Sector 15", "Noida Sector 18", 4);
        metroGraph.addEdge("Noida Sector 15", "Noida Sector 16", 3);
        metroGraph.addEdge("Noida Sector 16", "Noida Sector 15", 3);
        metroGraph.addEdge("Noida Sector 16", "Noida Sector 18", 2);
        metroGraph.addEdge("Noida Sector 18", "Noida Sector 16", 2);

        metroGraph.addEdge("Noida Sector 18", "Botanical Garden", 4);
        metroGraph.addEdge("Botanical Garden", "Noida Sector 18", 4);
        metroGraph.addEdge("Botanical Garden", "Golf Course", 3);
        metroGraph.addEdge("Golf Course", "Botanical Garden", 3);
        metroGraph.addEdge("Golf Course", "Noida City Centre", 2);
        metroGraph.addEdge("Noida City Centre", "Golf Course", 2);
        metroGraph.addEdge("Noida City Centre", "Sector 34", 4);
        metroGraph.addEdge("Sector 34", "Noida City Centre", 4);
        metroGraph.addEdge("Sector 34", "Sector 29", 3);
        metroGraph.addEdge("Sector 29", "Sector 34", 3);
        metroGraph.addEdge("Sector 29", "Sector 18", 2);
        metroGraph.addEdge("Sector 18", "Sector 29", 2);

        metroGraph.addEdge("Sector 18", "Raj Nagar Extension", 4);
        metroGraph.addEdge("Raj Nagar Extension", "Sector 18", 4);
        metroGraph.addEdge("Raj Nagar Extension", "Shahdara", 3);
        metroGraph.addEdge("Shahdara", "Raj Nagar Extension", 3);
        metroGraph.addEdge("Shahdara", "Welcome", 2);
        metroGraph.addEdge("Welcome", "Shahdara", 2);
        metroGraph.addEdge("Welcome", "Seelampur", 4);
        metroGraph.addEdge("Seelampur", "Welcome", 4);
        metroGraph.addEdge("Seelampur", "Shastri Park", 3);
        metroGraph.addEdge("Shastri Park", "Seelampur", 3);
        metroGraph.addEdge("Shastri Park", "Kashmere Gate", 2);
        metroGraph.addEdge("Kashmere Gate", "Shastri Park", 2);

        metroGraph.addEdge("Kashmere Gate", "Tis Hazari", 4);
        metroGraph.addEdge("Tis Hazari", "Kashmere Gate", 4);
        metroGraph.addEdge("Tis Hazari", "Pul Bangash", 3);
        metroGraph.addEdge("Pul Bangash", "Tis Hazari", 3);
        metroGraph.addEdge("Pul Bangash", "Pratap Nagar", 2);
        metroGraph.addEdge("Pratap Nagar", "Pul Bangash", 2);
        metroGraph.addEdge("Pratap Nagar", "Shastri Nagar", 4);
        metroGraph.addEdge("Shastri Nagar", "Pratap Nagar", 4);
        metroGraph.addEdge("Shastri Nagar", "Inderlok", 3);
        metroGraph.addEdge("Inderlok", "Shastri Nagar", 3);
        metroGraph.addEdge("Inderlok", "Kanhaiya Nagar", 2);
        metroGraph.addEdge("Kanhaiya Nagar", "Inderlok", 2);

        metroGraph.addEdge("Kanhaiya Nagar", "Keshav Puram", 4);
        metroGraph.addEdge("Keshav Puram", "Kanhaiya Nagar", 4);
        metroGraph.addEdge("Keshav Puram", "Netaji Subhash Place", 3);
        metroGraph.addEdge("Netaji Subhash Place", "Keshav Puram", 3);
        metroGraph.addEdge("Netaji Subhash Place", "Kohat Enclave", 2);
        metroGraph.addEdge("Kohat Enclave", "Netaji Subhash Place", 2);
        metroGraph.addEdge("Kohat Enclave", "Pitampura", 4);
        metroGraph.addEdge("Pitampura", "Kohat Enclave", 4);
        metroGraph.addEdge("Pitampura", "Rohini East", 3);
        metroGraph.addEdge("Rohini East", "Pitampura", 3);
        metroGraph.addEdge("Rohini East", "Rohini West", 2);
        metroGraph.addEdge("Rohini West", "Rohini East", 2);

        metroGraph.addEdge("Rohini West", "Rithala", 4);
        metroGraph.addEdge("Rithala", "Rohini West", 4);
        metroGraph.addEdge("Rithala", "Samaypur Badli", 3);
        metroGraph.addEdge("Samaypur Badli", "Rithala", 3);
        metroGraph.addEdge("Samaypur Badli", "Rohini Sector", 18);
        metroGraph.addEdge("Rohini Sector", "Samaypur Badli", 18);

        metroGraph.addEdge("Janakpuri West", "Dabri Mor", 3);
        metroGraph.addEdge("Dabri Mor", "Janakpuri West", 3);
        metroGraph.addEdge("Dabri Mor", "Dashrath Puri", 2);
        metroGraph.addEdge("Dashrath Puri", "Dabri Mor", 2);
        metroGraph.addEdge("Dashrath Puri", "Palam", 4);
        metroGraph.addEdge("Palam", "Dashrath Puri", 4);
        metroGraph.addEdge("Palam", "Sadar Bazar Cantonment", 3);
        metroGraph.addEdge("Sadar Bazar Cantonment", "Palam", 3);
        metroGraph.addEdge("Sadar Bazar Cantonment", "Terminal 1-IGI Airport", 2);
        metroGraph.addEdge("Terminal 1-IGI Airport", "Sadar Bazar Cantonment", 2);

        metroGraph.addEdge("Terminal 1-IGI Airport", "Shankar Vihar", 4);
        metroGraph.addEdge("Shankar Vihar", "Terminal 1-IGI Airport", 4);
        metroGraph.addEdge("Shankar Vihar", "Vasant Vihar", 3);
        metroGraph.addEdge("Vasant Vihar", "Shankar Vihar", 3);
        metroGraph.addEdge("Vasant Vihar", "Munirka", 2);
        metroGraph.addEdge("Munirka", "Vasant Vihar", 2);
        metroGraph.addEdge("Munirka", "R.K. Puram", 4);
        metroGraph.addEdge("R.K. Puram", "Munirka", 4);
        metroGraph.addEdge("R.K. Puram", "IIT Delhi", 3);
        metroGraph.addEdge("IIT Delhi", "R.K. Puram", 3);

        metroGraph.addEdge("IIT Delhi", "Hauz Khas", 4);
        metroGraph.addEdge("Hauz Khas", "IIT Delhi", 4);
        metroGraph.addEdge("Hauz Khas", "Panchsheel Park", 3);
        metroGraph.addEdge("Panchsheel Park", "Hauz Khas", 3);
        metroGraph.addEdge("Panchsheel Park", "Chirag Delhi", 2);
        metroGraph.addEdge("Chirag Delhi", "Panchsheel Park", 2);
        metroGraph.addEdge("Chirag Delhi", "Greater Kailash", 4);
        metroGraph.addEdge("Greater Kailash", "Chirag Delhi", 4);
        metroGraph.addEdge("Greater Kailash", "Nehru Enclave", 3);
        metroGraph.addEdge("Nehru Enclave", "Greater Kailash", 3);

        metroGraph.addEdge("Nehru Enclave", "Kalkaji Mandir", 2);
        metroGraph.addEdge("Kalkaji Mandir", "Nehru Enclave", 2);
        metroGraph.addEdge("Kalkaji Mandir", "Okhla NSIC", 4);
        metroGraph.addEdge("Okhla NSIC", "Kalkaji Mandir", 4);
        metroGraph.addEdge("Okhla NSIC", "Sukhdev Vihar", 3);
        metroGraph.addEdge("Sukhdev Vihar", "Okhla NSIC", 3);
        metroGraph.addEdge("Sukhdev Vihar", "Jamia Millia Islamia", 2);
        metroGraph.addEdge("Jamia Millia Islamia", "Sukhdev Vihar", 2);
        metroGraph.addEdge("Jamia Millia Islamia", "Okhla Vihar", 4);
        metroGraph.addEdge("Okhla Vihar", "Jamia Millia Islamia", 4);

        metroGraph.addEdge("Okhla Vihar", "Jasola Vihar Shaheen Bagh", 3);
        metroGraph.addEdge("Jasola Vihar Shaheen Bagh", "Okhla Vihar", 3);
        metroGraph.addEdge("Jasola Vihar Shaheen Bagh", "Kalindi Kunj", 2);
        metroGraph.addEdge("Kalindi Kunj", "Jasola Vihar Shaheen Bagh", 2);
        metroGraph.addEdge("Kalindi Kunj", "Okhla Bird Sanctuary", 4);
        metroGraph.addEdge("Okhla Bird Sanctuary", "Kalindi Kunj", 4);
        metroGraph.addEdge("Okhla Bird Sanctuary", "Botanical Garden", 3);
        metroGraph.addEdge("Botanical Garden", "Okhla Bird Sanctuary", 3);


        // User input for the start and end stations
        while (true) {
            System.out.println("Metro Graph Menu:");
            System.out.println("1. Print Map");
            System.out.println("2. Print Distance");
            System.out.println("3. Find Shortest Path");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    metroGraph.printMap();
                    break;
                case 2:
                    System.out.print("Enter the start station: ");
                    String start = scanner.next();
                    System.out.print("Enter the end station: ");
                    String end = scanner.next();
                    Map<String, Integer> distances = metroGraph.dijkstra(start);
                    System.out.println("Distance from " + start + " to " + end + ": " + distances.getOrDefault(end, -1));
                    break;
                case 3:
                    System.out.println("Enter the start station:");
                    scanner.nextLine();
                    String start2 = scanner.nextLine();
                    System.out.println("Enter the end station: ");
                    String end2 = scanner.next();
                    List<String> path = metroGraph.shortestPath(start2, end2);
                    if (path.isEmpty()) {
                        System.out.println("No path found between " + start2 + " and " + end2);
                    } else {
                        System.out.println("Shortest path from " + start2 + " to " + end2 + ":");
                        for (String station : path) {
                            System.out.print(station + "->");
                        }
                        System.out.println();
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}




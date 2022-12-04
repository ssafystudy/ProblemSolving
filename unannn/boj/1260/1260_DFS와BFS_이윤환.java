import java.util.*;

public class Main{
    public static void main(String[] args){

        Graph graph = Graph.createGraph();
        Graph.Node startNode = graph.getStartNode();
        graph.dfs(startNode);
        graph.clear();
        System.out.println();
        graph.bfs(startNode);
    }
}

class Graph{

    private Node[] nodes;
    private int vertex;
    private int edge;
    private int start;

    private Graph(){
    }

    public Node getStartNode(){
        return nodes[start];
    }

    public void clear(){
        for(int i = 0;i < vertex;i++){
            nodes[i + 1].visited = false;
        }
    }

    public static Graph createGraph(){

        Scanner sc = new Scanner(System.in);


        Graph graph = new Graph();
        graph.vertex = sc.nextInt();
        graph.edge = sc.nextInt();
        graph.start = sc.nextInt();

        graph.nodes = new Node[graph.vertex + 1];
        for(int i = 0; i < graph.vertex; i++){
            graph.nodes[i + 1] = new Node(i + 1);
        }

        for(int i = 0; i < graph.edge; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            Node nodeA = graph.nodes[a];
            Node nodeB = graph.nodes[b];

            nodeA.getAdjacent().add(nodeB);
            nodeB.getAdjacent().add(nodeA);
        }

        return graph;
    }

    public void dfs(Node searchNode){
        searchNode.adjacent.sort(((o1, o2) -> o1.data - o2.data));
        if(!searchNode.visited){
            System.out.print(searchNode.data + " ");
            searchNode.visited = true;
            for(Node node: searchNode.adjacent){
                dfs(node);
            }
        }
    }

    public void bfs(Node searchNode){
        List<Node> queue = new LinkedList<>();
        queue.add(searchNode);
        while(!queue.isEmpty()){
            Node pollNode = queue.remove(0);
            if(!pollNode.visited){
                System.out.print(pollNode.data + " ");
                pollNode.visited = true;
            }

            for(Node node : pollNode.adjacent){
                if(!node.visited){
                    queue.add(node);
                }
            }
        }
    }

    static class Node{

        private final int data;
        private boolean visited;
        private final List<Node> adjacent;

        public Node(int data){
            this.data = data;
            adjacent = new LinkedList<>();
        }

        public List<Node> getAdjacent(){
            return adjacent;
        }

        public boolean isVisited(){
            return visited;
        }

        public void setVisited(boolean visited){
            this.visited = visited;
        }
    }
}
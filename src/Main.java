import java.util.Scanner;

public class Main {
    public static class Node {
        public String value;
        public int index;
        public int firstChildIndex;
        public int secondChildIndex;
        public int thirdChildIndex;
        public int parentIndex;


        public Node(String value, int index) {
            this.value = value;
            this.index = index;
            this.firstChildIndex = index * 3 + 1;
            this.secondChildIndex = index * 3 + 2;
            this.thirdChildIndex = index * 3 + 3;
            int parentSupport = index / 3;
            int parentSupport2 = index % 3;
            if(parentSupport2 != 0){
                this.parentIndex = parentSupport;
            } else {
                this.parentIndex = parentSupport - 1;
            }
        }
    }

    public static int Max(Node[] nodes, Node node){
        try {
            if(node.parentIndex == -1){
                return Integer.parseInt(node.value);
            }
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            Node[] nodeSupportArray = new Node[] {
                    nodes[nodes[node.parentIndex].firstChildIndex],
                    nodes[nodes[node.parentIndex].secondChildIndex],
                    nodes[nodes[node.parentIndex].thirdChildIndex]
            };
            for(int i = 0; i < nodeSupportArray.length; i++){
                if(nodeSupportArray[i].value.equals("x")){
                    nodeSupportArray[i].value = Integer.toString(Math.min(min, Min(nodes, nodes[nodeSupportArray[i].firstChildIndex])));
                }
                if(!nodeSupportArray[i].value.equals(".") && !nodeSupportArray[i].value.equals("x")){
                    max = Math.max(max, Integer.parseInt(nodeSupportArray[i].value));
                }
            }
            return max;
        } catch (Exception e){
            System.err.println("Hibás input: " + e.toString());
        }
        return Integer.MAX_VALUE;
    }
    public static int Min(Node[] nodes, Node node){
        try {
            if(node.parentIndex == -1){
                return Integer.parseInt(node.value);
            }
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            Node[] nodeSupportArray = new Node[] {
                    nodes[nodes[node.parentIndex].firstChildIndex],
                    nodes[nodes[node.parentIndex].secondChildIndex],
                    nodes[nodes[node.parentIndex].thirdChildIndex]
            };
            for(int i = 0; i < nodeSupportArray.length; i++){
                if(nodeSupportArray[i].value.equals("x")){
                    nodeSupportArray[i].value = Integer.toString(Math.max(max, Max(nodes, nodes[nodeSupportArray[i].firstChildIndex])));
                }
                if(!nodeSupportArray[i].value.equals(".") && !nodeSupportArray[i].value.equals("x")){
                    min = Math.min(min, Integer.parseInt(nodeSupportArray[i].value));
                }
            }
            return min;
        } catch(Exception e){
            System.err.println("Hibás input: " + e.toString());
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kerem adja meg a fat listakent abrazolva:");
        String stringNode = scanner.nextLine();
        long startTime = System.nanoTime();
        int input_length = stringNode.split(" ").length;
        Node[] nodes = new Node[input_length];
        for(int i = 0; i < input_length; i++){
            nodes[i] = new Node(stringNode.split(" ")[i], i);
        }
        int res = Max(nodes, nodes[1]);
        if(res == Integer.MAX_VALUE || res == Integer.MIN_VALUE){
            System.out.println("Nem megfelelő az input, ezért hibás az eredmény");
        } else {
            long endTime = System.nanoTime();
            double totalTime = (double) (endTime - startTime) /  1_000_000_000;
            System.out.println("A maximálisan elérhető nyereség: " + res + " Futási idő: " + totalTime + " sec");
        }
    }
}

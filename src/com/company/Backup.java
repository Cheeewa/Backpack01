package com.company;

public class Backup {
    /*package com.company;

import org.w3c.dom.Node;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Rucksack {

    static class Item implements Comparable<Item>{
        int value;
        int weight;
        public Item(int w, int v){
            this.value = v;
            this.weight = w;
        }

        @Override
        public boolean equals(Object o){
            if (o == null) return false;
            if (o instanceof Item){
                Item oitem  = (Item) o;
                return oitem.weight == this.weight && oitem.value == this.value;
            }
            return false;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(this.value / this.weight, o.value / o.weight);
        }
    }

    static class Node {
        Node parent, left, right;
        double ratio;
        int sumweight;
        int sumvalue;
        public Node(Node p, Item i){
            parent = p;
            if (p == null){
                ratio = i.weight/i.value;
                sumweight = i.weight;
                sumvalue = i.value;
            }
            ratio = (p.sumweight + i.weight)/(p.sumvalue+i.value);
            sumweight = p.sumweight + i.weight;
            sumvalue = p.sumvalue + i.value;
        }

        public Node() {

        }
    }

    static class TreeNode{
        Node root;

    }

    public static void main(String[] args) throws IOException {
        // write your code here
        try (FileInputStream inputStream = new FileInputStream(args[0]);
             Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            //read file here
            String[] nk = sc.nextLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            PriorityQueue<Item> items = new PriorityQueue<>();
            //sort items by value/weight
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                Item i = new Item(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
                items.add(i);
            }
            //int[][] table = new int[n][2];
            int currentWeight = 0;
            int currentValue = 0;
            //create recursion tree with nodes
            while ((!items.isEmpty()) && currentWeight < k){
                Item a = items.poll();
                if (a.weight + currentWeight > k) continue;
                currentWeight += a.weight;
                currentValue += a.value;
            }
            System.out.println("The backpack weights " + currentWeight + " and is worth " + currentValue);
        }

    }
    public static void createNode (PriorityQueue<Item> items){
        Item first = items.poll();
        Node e = new Node(null, first);
        e.left = createNode(e, items.poll(), items);
        e.right = new Node(e, first);
    }
    public static Node createNode (Node e, Item x, PriorityQueue<Item> items){
        if (items.isEmpty()) return null;
        Node y = new Node(e, x);
        y.left = createNode(e, items.poll(), items);
        y.right = new Node(e, x);
        return y;
    }

}
*/
}

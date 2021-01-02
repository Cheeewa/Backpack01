package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RucksackImproved {
    private static final DecimalFormat df4 = new DecimalFormat("0.0000");
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
        public String toString(){
            return "(" +this.weight+", "+this.value+")";
        }

        @Override
        public int compareTo(Item o) {
            float i1 = (float) this.value/this.weight;
            float i2 = (float) o.value/o.weight;
            return Float.compare(i2, i1);
        }
    }

    static class Node implements Comparable<Node>{
        int weight, value;
        float ratio;

        public Node(Node prev, int w, int v){
            if (prev == null){
                this.weight = w;
                this.value = v;
            }
            else {
                this.weight = w + prev.weight;
                this.value = v + prev.value;
            }
            this.ratio = (float) v/w;
        }

        @Override
        public String toString() {
            return "(val=" + value +
                    ", r=" + df4.format(ratio) +
                    ')';
        }

        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(args[0]);
             Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            //read file here
            String[] nk = sc.nextLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            PriorityQueue<Item> items = new PriorityQueue<>(n);
            //sort items by value/weight
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                Item i = new Item(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
                items.add(i);
            }
            //create decision tree with nodes. will be pruned, when both ratio and weight
            // are lower than those of the same level
            ArrayList<Node> prevNodes = new ArrayList<>();
            ArrayList<Node> currNodes = new ArrayList<>();
            //first level of the tree
            Item firstItem = items.poll();
            assert firstItem != null;
            prevNodes.add(new Node(null, firstItem.weight, firstItem.value));
            //add the next level
            for (int i = 1; i < n; i++){
                if (!items.isEmpty()) {
                    Item deq = items.poll();
                    float minUpperbound = 0;
                    int maxValue = 0;
                    for (Node prevNode : prevNodes) {
                        //add two children to parent nodes;
                        assert deq != null;
                        Node include = new Node(prevNode, deq.weight, deq.value);
                        currNodes.add(prevNode);//exclude
                        currNodes.add(include);
                    }
                    //if a node in the same level has lower value and ratio,
                    // or the weight exceeds the maximum,
                    // it'll be marked as pruned and removed
                    for (int j = 0; j < currNodes.size(); j++){
                        float r = currNodes.get(j).ratio;
                        int v = currNodes.get(j).value;
                        if (currNodes.get(j).weight > k) {currNodes.remove(j); j--;}
                        if (r < minUpperbound && v < maxValue) {currNodes.remove(j); j--;}
                    }
                    prevNodes = new ArrayList<>(currNodes);
                    currNodes.clear();
                    //System.out.println(Arrays.toString(prevNodes.toArray()));
                }
            }
            Node max = prevNodes.stream().max(Node::compareTo).get();
            System.out.println("The backpack is worth " + max.value + " and weights "+ max.weight);
        }
    }
}


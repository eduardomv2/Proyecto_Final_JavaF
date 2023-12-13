/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.Lists;
import Clases.Nodes.Node;
import javax.swing.JTextArea;
/**
 *
 * @author andre
 */
public class SimpleList {
    private Node head;

    public SimpleList() {
        head = null;
        }

    public void add(int data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            head = newNode;
            return;
        }

        if (exist(newNode.getData())) {
            return;
        }

        if (newNode.getData() < head.getData()) {
            newNode.setNext(head);
            head = newNode;
            return;
        }

        Node currentNode = head;
        while (currentNode.getNext() != null && currentNode.getNext().getData() < newNode.getData()) {
            currentNode = currentNode.getNext();
        }

        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
    }

    public void delete(int data, JTextArea textArea) {
        textArea.setText("");

        if (isEmpty()) {
            return;
        }

        if (head.getData() == data) {
            head = head.getNext();
            textArea.setText("- Dato[" + data + "] Eliminado de la lista");
            return;
        }

        Node currentNode = head;
        while (currentNode.getNext() != null && currentNode.getNext().getData() < data) {
            currentNode = currentNode.getNext();
        }

        if (currentNode.getNext() != null && currentNode.getNext().getData() == data) {
            currentNode.setNext(currentNode.getNext().getNext());
            textArea.setText("- Dato[" + data + "] Eliminado de la lista");
            return;
        }

        textArea.setText("- Dato[" + data + "] No encontrado/eliminado de la lista");
    }

    public void search(int data, JTextArea textArea) {
        textArea.setText("");

        if (isEmpty()) {
            return;
        }

        if (head.getData() == data) {
            textArea.setText("- Dato[" + data + "] Existe en la lista");
            return;
        }

        Node currentNode = head;
        while (currentNode.getNext() != null && currentNode.getNext().getData() <= data) {
            currentNode = currentNode.getNext();
        }

        if (currentNode.getData() == data) {
            textArea.setText("- Dato[" + data + "] Existe en la lista");
            return;
        }

        textArea.setText("- Dato[" + data + "] No Existe en la lista");
    }

    public void show(JTextArea textArea) {
        textArea.setText("");

        if (isEmpty()) {
            textArea.setText("Lista vacia");
            return;
        }

        int i = 0;
        Node currentNode = head;
        while (currentNode != null) {
            textArea.append("- Nodo[" + i + "] y dato: " + currentNode.getData() + "\n");
            currentNode = currentNode.getNext();
            i++;
        }
    }

    public boolean exist(int data) {
        if (isEmpty()) {
            return false;
        }

        if (head.getData() == data) {
            return true;
        }

        Node currentNode = head;
        while (currentNode.getNext() != null && currentNode.getNext().getData() <= data) {
            currentNode = currentNode.getNext();
        }

        if (currentNode.getData() == data) {
            return true;
        }

        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }
}

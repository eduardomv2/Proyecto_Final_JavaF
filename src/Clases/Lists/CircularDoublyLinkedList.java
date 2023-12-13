/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.Lists;
import Clases.Nodes.DoubleNode;
import javax.swing.*;
/**
 *
 * @author andre
 */
public class CircularDoublyLinkedList {
    private DoubleNode head;
    private DoubleNode lastNode;

    public CircularDoublyLinkedList() {
        head = null;
        lastNode = null;
    }

    public void add(int data) {
        //Caso 0: Creamos un nuevo nodo
        DoubleNode newNode = new DoubleNode(data);
        //Caso 1: Insertamos al inicio
        if (isEmpty()) {
            head = newNode;
            newNode.setBack(head);
            newNode.setNext(head);
            lastNode = head;
            return;
        }
        //Caso 2: Impedimos datos repetidos
        if (exist(newNode.getData())) {
            return;
        }
        //Caso 3: Insertamos al inicio si el dato es menor
        if (newNode.getData() < head.getData()) {
            newNode.setNext(head);
            newNode.setBack(head);
            head.setNext(newNode);
            head.setBack(newNode);
            lastNode = head;
            head = newNode;
            return;
        }
        //Caso 4: Insertamos al final si el dato es mayor
        if (newNode.getData() > lastNode.getData()) {
            newNode.setBack(lastNode);
            lastNode.setNext(newNode);
            lastNode = newNode;
            lastNode.setNext(head);
            head.setBack(lastNode);
            return;
        }
        // caso 5: Recorremos la lista
        DoubleNode currentNode = head;
        while (currentNode.getNext() != head && currentNode.getNext().getData() < newNode.getData()) {
            currentNode = currentNode.getNext();
        }
        //Caso 6: Insertamos en X posición
        newNode.setNext(currentNode.getNext());
        newNode.setBack(currentNode);
        currentNode.getNext().setBack(newNode);
        currentNode.setNext(newNode);
    }

    public void delete(int data, JTextArea textBox) {
        //Caso 1: Si la lista está vacía
        if (isEmpty()) {
            return;
        }
        //Caso 2: Eliminamos y comprobamos si hay solo un elemento
        if (head.getData() == lastNode.getData()) {
            clear();
            textBox.setText("- Dato[" + data + "] Eliminado de la lista");
            return;
        }
        //Caso 3: El dato está al inicio de la lista
        if (head.getData() == data) {
            head = head.getNext();
            head.setBack(lastNode);
            lastNode.setNext(head);
            textBox.setText("- Dato[" + data + "] Eliminado de la lista");
            return;
        }
        //Caso 4: El dato está al final de la lista
        if (lastNode.getData() == data) {
            lastNode = lastNode.getBack();
            lastNode.setNext(head);
            head.setBack(lastNode);
            textBox.setText("- Dato[" + data + "] Eliminado de la lista");
            return;
        }
        //Caso 5: Recorremos la lista
        DoubleNode currentNode = head;
        while (currentNode.getNext() != head && currentNode.getNext().getData() < data) {
            currentNode = currentNode.getNext();
        }
        //Caso 6: El dato está en X posición de la lista
        if (currentNode.getNext().getData() == data) {
            currentNode.getNext().getNext().setBack(currentNode);
            currentNode.setNext(currentNode.getNext().getNext());
            textBox.setText("- Dato[" + data + "] Eliminado de la lista");
            return;
        }
        //Caso 7: No se encontró el dato a eliminar
        textBox.setText("- Dato[" + data + "] No encontrado/eliminado de la lista");
    }

    public void search(int data, JTextArea textBox) {
        //Caso 1: Si la lista está vacía
        if (isEmpty()) {
            return;
        }
        //Caso 2: Si es dato está al inicio
        if (head.getData() == data) {
            textBox.setText("- Dato[" + data + "] Existe en la lista");
            return;
        }
        //Caso 3: Si es dato está al final
        if (lastNode.getData() == data) {
            textBox.setText("- Dato[" + data + "] Existe en la lista");
            return;
        }
        //Caso 4: Recorremos la lista
        DoubleNode currentNode = head;
        while (currentNode.getNext() != head && currentNode.getNext().getData() <= data) {
            currentNode = currentNode.getNext();
        }
        //Caso 5: Si existe el dato en la lista
        if (currentNode.getData() == data) {
            textBox.setText("- Dato[" + data + "] Existe en la lista");
            return;
        }
        //Caso 6: No existe el dato en la lista
        textBox.setText("- Dato[" + data + "] No Existe en la lista ");
    }

    public void show(JTextArea textBox) {
        textBox.setText("");
        //Caso 1: Si la lista está vacía
        if (isEmpty()) {
            textBox.setText("Lista vacía");
            return;
        }
        //Caso 2: Recorremos la lista
        DoubleNode currentNode = head;
        int i = 0;
        do {
            textBox.setText(textBox.getText() + "- Nodo[" + i + "] y dato: " + currentNode.getData() + "\r\n");
            currentNode = currentNode.getNext();
            i++;
        } while (currentNode != head);
    }

    public boolean exist(int data) {
        //Caso 1: Si la lista está vacía
        if (isEmpty()) {
            return false;
        }
        //Caso 2: Si el primer elemento ya existe
        if (head.getData() == data) {
            return true;
        }
        //Caso 3: Si es dato está al final
        if (lastNode.getData() == data) {
            return true;
        }
        //Caso 4: Recorremos la lista
        DoubleNode currentNode = head;
        while (currentNode.getNext() != head && currentNode.getNext().getData() <= data) {
            currentNode = currentNode.getNext();
        }
        //Caso 5: El dato ingresado existe en X posición
        if (currentNode.getData() == data) {
            return true;
        }
        //Caso 6: El dato no existe en la lista
        return false;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public void clear() {
        head = null;
        lastNode = null;
    }
}

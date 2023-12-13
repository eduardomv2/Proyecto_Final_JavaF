/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Clases.Nodes.TreeNode;
import javax.swing.*;
/**
 *
 * @author andre
 */
public class Tree {
    TreeNode root;
    
    public Tree(TreeNode root) {
        this.root = root;
    }

    public void printTree(TreeNode node, JTextArea textArea, String indent) {
        textArea.append(indent + "└─  " + node.getName() + "\n");

        for (int i = 0; i < node.getChildren().size(); i++) {
            printTree(node.getChildren().get(i), textArea, indent + "   ");
        }
    }

    public TreeNode findNode(String nodeName, TreeNode node) {
        if (node == null)
            node = root;

        if (node.getName().equals(nodeName))
            return node;

        for (TreeNode child : node.getChildren()) {
            TreeNode foundNode = findNode(nodeName, child);
            if (foundNode != null)
                return foundNode;
        }

        return null;
    }

    public void addNode(String parentNodeName, String newNodeName, JTextArea textArea) {
        TreeNode parentNode = findNode(parentNodeName, null);

        if (parentNode != null) {
            parentNode.getChildren().add(new TreeNode(newNodeName));
            textArea.setText("");
            printTree(root, textArea, "");
        } else {
            textArea.setText("No se encontró el nodo padre '" + parentNodeName + "'.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textArea.setText("");
            printTree(root, textArea, "");
        }
    }

    public void deleteNode(String nodeName, JTextArea textArea) {
        TreeNode nodeToDelete = findNode(nodeName, null);

        if (nodeToDelete != null) {
            TreeNode parent = findParentNode(nodeName, null);

            if (nodeToDelete == root) {
                if (nodeToDelete.getChildren().size() > 0) {
                    root = nodeToDelete.getChildren().get(0);
                    textArea.setText("El nodo raíz '" + nodeName + "' se eliminó, y el primer hijo se convirtió en el nuevo raíz.");
                } else {
                    root = null;
                    textArea.setText("El nodo raíz '" + nodeName + "' se eliminó.");
                }
            } else if (parent != null) {
                if (nodeToDelete.getChildren().size() > 0) {
                    TreeNode firstChild = nodeToDelete.getChildren().get(0);
                    firstChild.getChildren().addAll(nodeToDelete.getChildren().subList(1, nodeToDelete.getChildren().size()));
                    parent.getChildren().add(parent.getChildren().indexOf(nodeToDelete), firstChild);
                }
                parent.getChildren().remove(nodeToDelete);
                textArea.setText("El nodo '" + nodeName + "' se eliminó, y el primer hijo se convirtió en el nuevo padre sin cambiar la posición de la rama.");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textArea.setText("");
            printTree(root, textArea, "");
        } else {
            textArea.setText("No se encontró el nodo '" + nodeName + "'.");
        }
    }

    private TreeNode findParentNode(String nodeName, TreeNode node) {
        if (node == null)
            node = root;

        for (TreeNode child : node.getChildren()) {
            if (child.getName().equals(nodeName))
                return node;

            TreeNode parent = findParentNode(nodeName, child);
            if (parent != null)
                return parent;
        }

        return null;
    }
}

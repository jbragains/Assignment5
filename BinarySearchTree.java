package lab7;

import java.io.*;
import java.util.*;
import static lab7.Assignment5.searchesFound;
import static lab7.Assignment5.searchesNotFound;
import static lab7.Assignment5.wordsFound;
import static lab7.Assignment5.wordsNotFound;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        Long searches= 0L;
        Assignment5 a=new Assignment5();
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
                searches++;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
                searches++;
            } else // element matches current.element
            {
                searchesFound+=searches;
                wordsFound++;
                return true; // Element is found
            }
        }
        searchesNotFound+=searches;
        wordsNotFound++;
        System.out.println(e+" was not found.");
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }
    protected ArrayList<E> preorder(TreeNode<E> root, ArrayList<E> list) {
        if (root == null) {
            return list;
        }
        list.add(root.element);
        preorder(root.left, list);
        preorder(root.right, list);
        return list;
    }

    /**
     * Inner class tree node
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     */
    public ArrayList<E> path(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; 
        while (current != null) {
            list.add(current.element);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } 
            else{
                current=null;
            }
        }
        if(list.indexOf(e)==list.size()-1){
            return list;
        }
        else{
            list.clear();
            return list;
        }
    }

    /* Returns the number of leaf nodes in this tree, returns 0 if tree is empty*/
    public int getNumberOfLeaves() {
        return getNumberOfLeaves(root);
    }

    public int getNumberOfLeaves(TreeNode<E> root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
        }
    }

    /* Returns an ArrayList containing all elements in preorder of the specified element’s left sub-tree, returns an empty ArrayList if no  such element exists. */
    public ArrayList<E> leftSubTree(E e) {
        ArrayList<E> list=new ArrayList<>();
        boolean found = false;
        TreeNode<E> current = root; // Start from the root
        TreeNode<E> left= current;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } 
            else // element matches current.element
            {
                left=current;
                current=null;
                found = true;
            }
        }
        if (found==true){
            preorder(left.left, list);
            return list;
        }
        else{
            return list;
        }
    }

    /* Returns an ArrayList containing all elements in preorder of the specified element’s right sub-tree, returns an empty ArrayList if no  such element exists. */
    public ArrayList<E> rightSubTree(E e) {
        ArrayList<E> list=new ArrayList<>();
        boolean found = false;
        TreeNode<E> current = root; // Start from the root
        TreeNode<E> right= current;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                right= current;
                current=null;
                found = true;
            }
        }
        if (found==true){
            preorder(right.right, list);
            return list;
        }
        else{
            return list;
        }
    }

    /* Returns the inorder predecessor of the specified element, returns null if tree is empty or element 'e' is not in the tree. */
    public boolean sameTree(BinarySearchTree<E> tree2) {
        if (this.root == null && tree2.root == null) {
            return true;
        } else if (this.root != null && tree2.root != null) {
            return (this.root.element.equals(tree2.root.element)) && (sameTree(this.root.left, tree2.root.left)) && (sameTree(this.root.right, tree2.root.right));
        } else {
            return false;
        }
    }

    public boolean sameTree(TreeNode<E> tree1, TreeNode<E> tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        } else if (tree1 != null && tree2 != null) {
            return (tree1.element.equals(tree2.element)) && (sameTree(tree1.left, tree2.left)) && (sameTree(tree1.right, tree2.right));
        } else {
            return false;
        }
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else if (e.compareTo(parent.element) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}

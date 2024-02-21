/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao.arboles;

import Controlador.TDA.Listas.ListaEnlazada;
import Controlador.dao.arboles.NodeTree;



/**
 *
 * @author Santiago
 */
public class TreeInteger {

    private NodeTree<Integer> root;
    private Integer height;
    private ListaEnlazada<ListaEnlazada<NodeTree>> levels;
    private ListaEnlazada<NodeTree> orders;
    private Integer nro_nodes;

    public TreeInteger() {
        root = null;
        height = 0;
        nro_nodes = 0;
        levels = new ListaEnlazada<>();
        orders = new ListaEnlazada<>();
    }

    public TreeInteger(NodeTree<Integer> root, Integer height, ListaEnlazada<ListaEnlazada<NodeTree>> levels, ListaEnlazada<NodeTree> orders, Integer nro_nodes) {
        this.root = root;
        this.height = height;
        this.levels = levels;
        this.orders = orders;
        this.nro_nodes = nro_nodes;
    }

    private Integer calcHeight(NodeTree tree) {
        if (tree == null) {
            return 0;
        } else {
            Integer left = calcHeight(tree.getLeft());
            Integer right = calcHeight(tree.getRight());
            if (left.intValue() > right.intValue()) {
                //Se pone un +1 porque en el arbol no se cuenta la raiz
                return left + 1;
            } else {
                return right + 1;
            }
        }
    }

//    private Integer calcHeigth(NodeTree tree) {
//        if (tree == null) {
//            return 0;
//        } else {
//            Integer left = calcHeigth(tree.getLeft());
//            Integer right = calcHeigth(tree.getRigth());
//            return Math.max(left, right) + 1;
//        }
//    }
    private void calcLevels(NodeTree tree, Integer level) throws Exception {
        if (tree != null) {
            levels.getInfo(level).add(tree);
            level++;
            calcLevels(tree.getLeft(), level);
            calcLevels(tree.getRight(), level);
        } else if (level.intValue() != getHeight().intValue()) {
            levels.getInfo(level).add(null);
            level++;
            calcLevels(null, level);
            calcLevels(null, level);
        }
    }

    private void levels() throws Exception {
        levels = new ListaEnlazada<>();
        this.height = calcHeight(root);
        for (int i = 0; i <= this.height; i++) {
            levels.add(new ListaEnlazada<>());
        }
        calcLevels(root, 0);
        levels.extract(levels.getLength() - 1);
    }

    public Boolean insert(Integer data) throws Exception {
        if (root == null) {
            root = new NodeTree<>(data);
            nro_nodes++;
            levels();
            return true;
        } else {
            //nuevo
            NodeTree<Integer> nuevo = new NodeTree<>(data);
            NodeTree<Integer> recent = root;
            NodeTree<Integer> father;
            while (true) {
                father = recent;
                if (data.intValue() == recent.getData().intValue()) {
                    return false;
                } else if (data.intValue() < recent.getData().intValue()) {
                    recent = recent.getLeft();
                    if (recent == null) {
                        nuevo.setFather(father);
                        father.setLeft(nuevo);
                        nro_nodes++;
                        levels();
                        return true;
                    }
                } else {
                    recent = recent.getRight();
                    if (recent == null) {
                        nuevo.setFather(father);
                        father.setRight(nuevo);
                        nro_nodes++;
                        levels();
                        return true;
                    }
                }
            }
        }
    }

    private void pre_preorder(NodeTree<Integer> tree) {
        if (tree != null) {
            orders.add(tree);
            pre_preorder(tree.getLeft());
            pre_preorder(tree.getRight());
        }
    }

    public void pre_order() {
        orders = new ListaEnlazada<>();
        pre_preorder(root);
    }

    private void in_orden(NodeTree<Integer> tree) {
        if (tree != null) {
            in_orden(tree.getLeft());
            orders.add(tree);
            in_orden(tree.getRight());
        }
    }

    public void in_orden() {
        orders = new ListaEnlazada<>();
        in_orden(root);
    }

    private void post_Order(NodeTree<Integer> tree) {
        if (tree != null) {
            post_Order(tree.getLeft());
            post_Order(tree.getRight());
            orders.add(tree);
        }
    }

    public void post_Order() {
        orders = new ListaEnlazada<>();
        post_Order(root);
    }

    public NodeTree<Integer> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<Integer> root) {
        this.root = root;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public ListaEnlazada<ListaEnlazada<NodeTree>> getLevels() {
        return levels;
    }

    public void setLevels(ListaEnlazada<ListaEnlazada<NodeTree>> levels) {
        this.levels = levels;
    }

    public ListaEnlazada<NodeTree> getOrders() {
        return orders;
    }

    public void setOrders(ListaEnlazada<NodeTree> orders) {
        this.orders = orders;
    }

    public Integer getNro_nodes() {
        return nro_nodes;
    }

    public void setNro_nodes(Integer nro_nodes) {
        this.nro_nodes = nro_nodes;
    }

    public static void main(String[] args) {
        try {
//            int n = 15;
//            int x = 0; 
//            int aux[] = IntStream.rangeClosed(1, 15).toArray();
//            for (int i = 0; i < aux.length; i++) {
//                aux[i] = aux[i] * ((int)(Math.random() * 100));
//                System.out.println(aux[i]);
//            }
//            Arrays.sort(aux);
//            int medio = aux.length/2;
//            int sal = aux[0];
//            aux[medio] = sal;
//            TreeInteger arbol = new TreeInteger();
//            arbol.insert(aux[0]);
//            for (int i = 1; i < aux.length; i++) {
//                arbol.insert(aux[i]);
//            }
//            System.out.println("Altura es: " + arbol.getHeight());
//            System.out.println("Miveles es: " + arbol.getLevels().getLength());
            TreeInteger arbol = new TreeInteger();
            arbol.insert(100); //raiz
            arbol.insert(70); //left
            arbol.insert(130); //right
            arbol.insert(50);
            arbol.insert(80);
            arbol.insert(110);
            arbol.insert(150);
            arbol.insert(45);
            arbol.insert(60);
            arbol.insert(75);
            arbol.insert(85);
            arbol.insert(105);
            arbol.insert(115);
            arbol.insert(145);
            arbol.insert(155);
            System.out.println("Altura es: " + arbol.getHeight());
            System.out.println("Miveles es: " + arbol.getLevels().getLength());
            System.out.println("RECORRIDO PRE ORDER");
            arbol.pre_order();
            System.out.println(arbol.getOrders().toString());
        } catch (Exception e) {
        }
    }
}

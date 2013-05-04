package parallelhyflex.algebra.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author kommusoft
 */
public class Tree<T extends Comparable<T>> implements Collection<T> {

    private TreeNode<T> root;
    private int size = 0;

    public boolean add(T element) {
        TreeNode<T> node = new TreeNode<>(element);
        node.recalcHeight();
        this.root = this.add(this.root, node);
        return true;
    }

    private TreeNode<T> add(TreeNode<T> parent, TreeNode<T> element) {
        int cp = element.compareTo(parent);
        if (cp > 0x00) {
            parent.setLeft(add(parent.getRight(), element));
        } else if (cp < 0x00) {
            parent.setRight(add(parent.getRight(), element));
        } else {
            return element;
        }
        int lh = parent.getLeft().getHeight();
        int rh = parent.getRight().getHeight();
        int clh, crh;
        if (lh - rh <= -2) {
            clh = parent.getRight().getLeftHeight();
            crh = parent.getRight().getRightHeight();
            if (crh > clh) {
                return rrBalance(parent);
            } else {
                return rlBalance(parent);
            }
        } else if (lh - rh >= 2) {
            clh = parent.getLeft().getLeftHeight();
            crh = parent.getLeft().getRightHeight();
            if (crh > clh) {
                return lrBalance(parent);
            } else {
                return llBalance(parent);
            }
        } else {
            parent.recalcHeight();
            return parent;
        }
    }

    private TreeNode<T> singleLeftRotation(TreeNode<T> node) {
        TreeNode<T> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);
        node.recalcHeight();
        temp.recalcHeight();
        return temp;
    }

    private TreeNode<T> singleRightRotation(TreeNode<T> node) {
        TreeNode<T> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        node.recalcHeight();
        temp.recalcHeight();
        return temp;
    }

    private TreeNode<T> doubleLeftRotation(TreeNode<T> node) {
        node.setLeft(singleRightRotation(node.getLeft()));
        return singleLeftRotation(node);
    }

    private TreeNode<T> doubleRightRotation(TreeNode<T> node) {
        node.setRight(singleLeftRotation(node.getRight()));
        return singleRightRotation(node);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size > 0;
    }

    @Override
    public boolean contains(Object o) {
        try {
            T t = (T) o;
            if (t == null) {
                return false;
            }
            TreeNode<T> node = this.root;
            while (node != null) {
                int co = t.compareTo(node.getData());
                if (co < 0x00) {
                    node = node.getLeft();
                } else if (co > 0x00) {
                    node = node.getRight();
                } else {
                    return node.getData().equals(t);
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        for (Object o : clctn) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        boolean ch = false;
        for (T t : clctn) {
            ch |= this.add(t);
        }
        return ch;
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        boolean ch = false;
        for (Object t : clctn) {
            ch |= this.remove(t);
        }
        return ch;
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0x00;
    }

    private class TreeIterator<Q extends Comparable<Q>> implements Iterator<Q> {

        private final Stack<TreeNode<Q>> trace = new Stack<>();

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Q next() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}

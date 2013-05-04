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

    @Override
    public boolean add(T element) {
        this.size++;
        TreeNode<T> node = new TreeNode<>(element);
        node.recalcHeight();
        this.root = this.add(this.root, node);
        return true;
    }

    private TreeNode<T> add(TreeNode<T> parent, TreeNode<T> element) {
        if(parent == null) {
            return element;
        }
        int cp = element.compareTo(parent);
        if (cp > 0x00) {
            parent.setRight(add(parent.getRight(), element));
        } else if (cp < 0x00) {
            parent.setLeft(add(parent.getLeft(), element));
        } else {
            return element;
        }
        return rebalance(parent);
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
        return new TreeIterator<T>();
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
        try {
           T t = (T) o;
           this.root = remove(this.root,t);
           return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    private T popMostRight (TreeNode<T> grandfather, TreeNode<T> parent) {
        if(parent == null) {
            return null;
        }
        TreeNode<T> rig = parent.getRight();
        if(rig != null) {
            T data = popMostRight(parent,rig);
            parent.recalcHeight();
            return data;
        }
        rig = parent.getLeft();
        if(rig != null) {
            grandfather.setRight(rig);
            grandfather.recalcHeight();
            return parent.getData();
        }
        else {
            grandfather.setRight(null);
            grandfather.recalcHeight();
            return parent.getData();
        }
    }
    
    public TreeNode<T> remove (TreeNode<T> parent, T val) {
        if(parent == null) {
            return null;
        }
        int co = parent.compareTo(val);
        if(co > 0x00) {
            parent.setLeft(remove(parent.getLeft(),val));
            return parent;
        }
        else if(co < 0x00) {
            parent.setRight(remove(parent.getRight(),val));
            return parent;
        }
        else {
            if(parent.getLeft() == null) {
                return parent.getRight();
            }
            else if(parent.getRight() == null) {
                return parent.getLeft();
            }
            else {
                parent.setData(popMostRight(parent,parent.getLeft()));
                return parent;
            }
        }
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

    private TreeNode<T> rebalance(TreeNode<T> parent) {
        int lh = parent.getLeftHeight();
        int rh = parent.getRightHeight();
        int clh, crh;
        if (lh - rh <= -2) {
            clh = parent.getRight().getLeftHeight();
            crh = parent.getRight().getRightHeight();
            if (crh > clh) {
                return singleRightRotation(parent);
            } else {
                return doubleRightRotation(parent);
            }
        } else if (lh - rh >= 2) {
            clh = parent.getLeft().getLeftHeight();
            crh = parent.getLeft().getRightHeight();
            if (crh > clh) {
                return doubleLeftRotation(parent);
            } else {
                return singleLeftRotation(parent);
            }
        } else {
            parent.recalcHeight();
            return parent;
        }
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

    @Override
    public String toString() {
        return String.format("%s",this.root);
    }
    
}

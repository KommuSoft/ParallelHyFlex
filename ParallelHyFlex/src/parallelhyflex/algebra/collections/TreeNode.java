package parallelhyflex.algebra.collections;

/**
 *
 * @author kommusoft
 */
public class TreeNode<T extends Comparable<T>> implements Comparable<T> {

    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private int height;

    public TreeNode(T data) {
        this(data, null, null);
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 0;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the left
     */
    public TreeNode<T> getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public boolean setNullLeft(TreeNode<T> left) {
        if (this.left == null) {
            this.left = left;
            return true;
        }
        return false;
    }

    /**
     * @return the right
     */
    public TreeNode<T> getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public boolean setNullRight(TreeNode<T> right) {
        if (this.right == null) {
            this.right = right;
            return true;
        }
        return false;
    }

    /**
     * @return the balance
     */
    protected int getBalance() {
        return this.getRightHeight() - this.getLeftHeight();
    }

    public int getHeight() {
        return this.height;
    }
    
    void recalcHeight () {
        this.height = Math.max(this.getLeftHeight(),this.getRightHeight())+1;
    }

    @Override
    public int compareTo(T t) {
        return this.getData().compareTo(t);
    }

    public int compareTo(TreeNode<T> treeNode) {
        return this.getData().compareTo(treeNode.getData());
    }

    public int getRightHeight() {
        if (this.right != null) {
            return this.right.height;
        } else {
            return -1;
        }
    }

    public int getLeftHeight() {
        if (this.left != null) {
            return this.left.height;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return String.format("[ %s | %s |  %s ]", this.left,this.data,this.right);
    }
    
}
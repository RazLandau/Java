/*********************************************************WAVLTree*****************************************************/

/**
 * WAVLTree
 * <p>
 * An implementation of a WAVL Tree with
 * distinct integer keys and info
 */
public class WAVLTree {

    final static boolean DELETE = false, INSERT = true, LEFT = false, RIGHT = true;
    final static int INSERT_ERROR = -1, DELETE_ERROR = -1;

    enum NodeType {
        LEAF,
        RIGHT_UNARY,
        LEFT_UNARY,
        INTERNAL,
    }

    enum Edges {
        L2R2,
        L3R1,
        L1R3,
        L3R2,
        L2R3,
        L1R1,
        L2R1,
        L1R2,
        L0R1,
        L1R0,
        OTHER, L0R2, L2R0,

    }

    static WAVLNode EXTERNAL_NODE;

    WAVLNode root;
    WAVLNode min;
    WAVLNode max;

    /**
     * WAVLTree constructor
     */
    public WAVLTree() {
        EXTERNAL_NODE = new WAVLNode();
        root = max = min = EXTERNAL_NODE;
    }

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     * complexity: O(1)
     */
    public boolean empty() {
        return size() == 0;
    }

    /**
     * public String search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     * complexity : O(logn)
     */
    public String search(int k) {
        return treeSearch(root, k).value;
    }

    /**
     * public int insert(int k, String i)
     * <p>
     * inserts an item with key k and info i to the WAVL tree.
     * the tree must remain valid (keep its invariants).
     * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
     * returns -1 if an item with key k already exists in the tree.
     * complexity: O(logn)
     */
    public int insert(int k, String i) {
        if (empty()) {
            root = min = max = new WAVLNode(k, i, EXTERNAL_NODE);
            return 0;
        }
        WAVLNode insertionPoint = treePosition(root, k);
        if (k == insertionPoint.key) {
            return INSERT_ERROR;
        }
        WAVLNode newNode = new WAVLNode(k, i, insertionPoint);
        if (k < insertionPoint.key) {
            insertionPoint.left = newNode;
        } else {
            insertionPoint.right = newNode;
        }
        updateSizes(INSERT, insertionPoint);
        updateMinMax(INSERT, newNode);
        return rebalanceInsert(insertionPoint);
    }

    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there;
     * the tree must remain valid (keep its invariants).
     * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
     * returns -1 if an item with key k was not found in the tree.
     * complexity: O(logn)
     */
    public int delete(int k) {
        WAVLNode nodeToDelete = treeSearch(root, k);
        if (!nodeToDelete.isRealNode()) {
            return DELETE_ERROR;
        }
        updateMinMax(DELETE, nodeToDelete);
        WAVLNode deletionPoint = delete(nodeToDelete);
        updateSizes(DELETE, deletionPoint);
        return rebalanceDelete(deletionPoint);
    }

    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty
     * Complexity: O(1)
     */
    public String min() {
        return min.value;
    }

    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty
     * Complexity: O(1)
     */
    public String max() {
        return max.value;
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     * Complexity: O(n)
     */
    public int[] keysToArray() {
        int[] arr = new int[size()];
        recKeysToArray(arr, root, 0);
        return arr;
    }

    /**
     * public String[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     * Complexity: O(n)
     */
    public String[] infoToArray() {
        String[] arr = new String[size()];
        recInfoToArray(arr, root, 0);
        return arr;
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     * Complexity: O(1)
     * <p>
     * precondition: none
     * postcondition: none
     */
    public int size() {
        return root.size;
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root WAVL node, or null if the tree is empty
     * Complexity: O(1)
     * <p>
     * precondition: none
     * postcondition: none
     */
    public IWAVLNode getRoot() {
        return root;
    }

    /**
     * public int select(int i)
     * <p>
     * Returns the value of the i'th smallest key (return -1 if tree is empty)
     * Complexity: O(logi(n))
     * Example 1: select(1) returns the value of the node with minimal key
     * Example 2: select(size()) returns the value of the node with maximal key
     * Example 3: select(2) returns the value 2nd smallest minimal node, i.e the value of the node minimal node's successor
     * <p>
     * precondition: size() >= i > 0
     * postcondition: none
     */
    public String select(int i) {
        if (i <= 0 || i > size()) {
            return null;
        } // TODO
        return fingerSearch(i).value;
    }

    /**********************************************************PRIVATE*************************************************/

    /*
     * temp func
     */
    public IWAVLNode findbyKey(int k, IWAVLNode current)
    {
        if (current == null || !current.isRealNode())
            return null;

        int curKey = current.getKey();
        if (curKey == k)
            return current;
        else if (k < curKey)
            return findbyKey(k, current.getLeft());
        return findbyKey(k, current.getRight());
    }

    /**
     * Recursively fills an array which contains all keys in subtree of node,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     * Complexity: O(n)
     *
     * @param keysArray
     * @param node
     * @param index
     * @return
     */
    private int recKeysToArray(int[] keysArray, WAVLNode node, int index) {
        if (!node.isRealNode()) {
            return index;
        }
        index = recKeysToArray(keysArray, node.left, index);
        keysArray[index++] = node.key;
        index = recKeysToArray(keysArray, node.right, index);
        return index;
    }

    /**
     * Recursively fills an array which contains all info in subtree of node,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     * Complexity: O(n)
     *
     * @param infoArray
     * @param node
     * @param index
     * @return
     */
    private int recInfoToArray(String[] infoArray, WAVLNode node, int index) {
        if (!node.isRealNode()) {
            return index;
        }
        index = recInfoToArray(infoArray, node.left, index);
        infoArray[index++] = node.value;
        index = recInfoToArray(infoArray, node.right, index);
        return index;
    }

    /**
     * Complexity: O(logn)
     *
     * @param node
     * @return the item following node according to the sorted order of keys.
     */
    public WAVLNode successor(WAVLNode node) {
        if (node.isRealNode() && node.right.isRealNode()) {
            return min(node.right);
        }
        WAVLNode successor = node.parent;
        while (successor != null && successor.isRealNode() && node == successor.right) {
            node = successor;
            successor = node.parent;
        }
        return successor;
    }

    /**
     * Complexity: O(logn)
     *
     * @param node
     * @return the item preceding node according to the sorted order of keys.
     */
    public WAVLNode predecessor(WAVLNode node) {
        if (node.isRealNode() && node.left.isRealNode()) {
            return max(node.left);
        }
        WAVLNode predecessor = node.parent;
        while (predecessor != null && predecessor.isRealNode() && node == predecessor.left) {
            node = predecessor;
            predecessor = node.parent;
        }
        return predecessor;
    }

    /**
     * Deletes an unary node from the binary tree;
     * Complexity: O(1)
     *
     * @param unaryNode
     * @param side
     * @return the deleted node's parent.
     */
    private WAVLNode deleteUnary(WAVLNode unaryNode, boolean side) {
        WAVLNode son = unaryNode.getSon(side);
        son.parent = unaryNode.parent;
        if (unaryNode == root) {
            root = son;
        } else {
            unaryNode.parent.setSon(unaryNode.getSide(), son);
        }
        return unaryNode.parent;
    }

    /**
     * Deletes an internal node from the binary tree;
     * the tree must remain valid (keep its invariants).
     * Complexity: O(logn)
     *
     * @param internalNode
     * @return the deleted node's parent.
     */
    private WAVLNode deleteInternal(WAVLNode internalNode) {
        WAVLNode successor = successor(internalNode);
        internalNode.value = successor.value;
        internalNode.key = successor.key;
        if (successor == max) {
            max = internalNode;
        }
        return delete(successor);
    }

    /**
     * Deletes a leaf node from the binary tree;
     * the tree must remain valid (keep its invariants).
     * Complexity: O(1)
     *
     * @param leafNode
     * @return the deleted node's parent.
     */
    private WAVLNode deleteLeaf(WAVLNode leafNode) {
        if (leafNode == root) {
            root = min = max = EXTERNAL_NODE;
        } else {
            leafNode.parent.setSon(leafNode.getSide(), EXTERNAL_NODE);
        }
        return leafNode.parent;
    }

    /**
     * Deletes a node from the binary tree;
     * the tree must remain valid (keep its invariants).
     * Complexity: O(logn)
     *
     * @param node
     * @return the deleted node's parent.
     */
    public WAVLNode delete(WAVLNode node) {
        switch (node.getNodeType()) {
            case LEAF:
                return deleteLeaf(node);
            case LEFT_UNARY:
                return deleteUnary(node, LEFT);
            case RIGHT_UNARY:
                return deleteUnary(node, RIGHT);
            case INTERNAL:
                return deleteInternal(node);
            default:
                throw new RuntimeException();
        }
    }

    /**
     * Complexity: O(logn)
     *
     * @param node
     * @param k
     * @return last node encountered looking for k in the subtree of x
     */
    public WAVLNode treePosition(WAVLNode node, int k) {
        WAVLNode position = node;
        while (node.isRealNode()) {
            position = node;
            if (k == node.key) {
                return node;
            }
            if (k < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return position;
    }

    /**
     * Complexity: O(logn)
     *
     * @param x
     * @return min key in the subtree of x
     */
    public WAVLNode min(WAVLNode x) {
        while (x.isRealNode() && x.left.isRealNode()) {
            x = x.left;
        }
        return x;
    }

    /**
     * Complexity: O(logn)
     *
     * @param x
     * @return max key in the subtree of x
     */
    public WAVLNode max(WAVLNode x) {
        while (x.isRealNode() && x.right.isRealNode()) {
            x = x.right;
        }
        return x;
    }

    /**
     * Complexity: O(logn)
     *
     * @param node
     * @param k
     * @return node with key k in the subtree of node if found, external node otherwise
     */
    public WAVLNode treeSearch(WAVLNode node, int k) {
        while (node.isRealNode()) {
            if (k == node.key) {
                return node;
            }
            if (k < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    /**
     * WAVLNode select(WAVLNode x, int i)
     * <p>
     * Return the i-th largest item in subtree of x (indices start from 0)
     * <p>
     * precondition: size() > i >= 0
     * postcondition: none
     */
    public WAVLNode select(WAVLNode x, int i) {
        int r = x.left.size;
        if (i == r) {
            return x;
        } else {
            if (i < r) {
                return select(x.left, i);
            } else {
                return select(x.right, i - r - 1);
            }
        }
    }

    /**
     * Return the i-th largest item in D (indices start from 1)
     * precondition: size() >= i > 0
     * postcondition: none
     *
     * @param i
     * @return
     */
    public WAVLNode fingerSearch(int i) {
        WAVLNode node = min;
        while (node.size < i) {
            node = node.parent;
        }
        return select(node, i - 1);
    }

    /**
     * Updates min&max after insertion/deletion of given node
     * Complexity: O(logn)
     *
     * @param op
     * @param node
     */
    public void updateMinMax(boolean op, WAVLNode node) {
        if (op == INSERT) {
            if (node.key > max.key) {
                max = node;
            }
            if (node.key < min.key) {
                min = node;
            }
        } else {
            assert op == DELETE;
            if (node == min) {
                min = successor(min);
            }
            if (node == max) {
                max = predecessor(max);
            }
        }
    }

    /**
     * Updates of all nodes in tree from start to root (inclusive) after insertion/deletion
     * Complexity: O(logn)
     *
     * @param op
     * @param start
     */
    private void updateSizes(boolean op, WAVLNode start) {
        while (start.isRealNode()) {
            if (op == DELETE) {
                start.size--;
            } else {
                assert op == INSERT;
                start.size++;
            }
            start = start.parent;
        }
    }

    /**
     * Rotates the subtree of given node to the right
     * Complexity: O(1)
     *
     * @param rotationPoint
     * @return 1
     */
    private void rotateRight(WAVLNode rotationPoint) {
        WAVLNode y = rotationPoint;
        WAVLNode x = y.left;
        WAVLNode c = y.right;
        WAVLNode a = x.left;
        WAVLNode b = x.right;

        if (y == root) {
            root = x;
        } else {
            y.parent.setSon(y.getSide(), x);
        }
        x.right = y;
        x.parent = y.parent;
        y.left = b;
        y.parent = x;
        b.parent = y;

        y.size = b.size + c.size + 1;
        x.size = a.size + y.size + 1;

    }

    /**
     * Rotates the subtree of given node to the left
     * Complexity: O(1)
     *
     * @param rotationPoint
     * @return 1
     */
    private void rotateLeft(WAVLNode rotationPoint) {
        WAVLNode x = rotationPoint;
        WAVLNode y = x.right;
        WAVLNode a = x.left;
        WAVLNode c = y.right;
        WAVLNode b = y.left;

        if (x == root) {
            root = y;
        } else {
            x.parent.setSon(x.getSide(), y);
        }
        y.left = x;
        y.parent = x.parent;
        x.right = b;
        x.parent = y;
        b.parent = x;

        x.size = a.size + b.size + 1;
        y.size = x.size + c.size + 1;

    }

    /**
     * Rotates the subtree of given node to given side
     * Complexity: O(1)
     *
     * @param side
     * @param rotationPoint
     * @return 1
     */
    private int rotate(boolean side, WAVLNode rotationPoint) {
        if (side) {
            rotateRight(rotationPoint);
        } else {
            rotateLeft(rotationPoint);
        }
        return 1;
    }

    /**
     * Rebalances tree after Case 4 deletion
     * Complexity: O(logn)
     *
     * @param rebalancingPoint
     * @return number of rebalancing operations (promote/demote/rotate)
     */
    private int rebalanceDelete31(WAVLNode rebalancingPoint) {
        WAVLNode son = rebalancingPoint.right;
        Edges sonEdges = son.getEdges();
        switch (sonEdges) {
            case L2R2:
                return rebalancingPoint.demote() + son.demote() + rebalanceDelete(rebalancingPoint.parent);
            case L1R1:
                return rotate(LEFT, rebalancingPoint) + rebalancingPoint.demote() + son.promote();
            case L2R1:
                return rotate(LEFT, rebalancingPoint) + rebalancingPoint.demote() + son.promote() +
                        (rebalancingPoint.getNodeType() == NodeType.LEAF ? rebalancingPoint.demote() : 0);
            case L1R2:
                WAVLNode newRoot = son.left;
                return rotate(RIGHT, son) + rotate(LEFT, rebalancingPoint)
                        + newRoot.promote() + newRoot.promote()
                        + rebalancingPoint.demote() + rebalancingPoint.demote()
                        + son.demote();
            default:
                return 0;
        }
    }

    /**
     * Rebalances tree after Case 4 deletion
     * Complexity: O(logn)
     *
     * @param rebalancingPoint
     * @return number of rebalancing operations (promote/demote/rotate)
     */
    private int rebalanceDelete13(WAVLNode rebalancingPoint) {
        int steps = 0;
        WAVLNode son = rebalancingPoint.left;
        Edges sonEdges = son.getEdges();
        switch (sonEdges) {
            case L2R2:
                return rebalancingPoint.demote() + son.demote() + rebalanceDelete(rebalancingPoint.parent);
            case L1R1:
                return rotate(RIGHT, rebalancingPoint) + rebalancingPoint.demote() + son.promote();
            case L1R2:
                return rotate(RIGHT, rebalancingPoint) + rebalancingPoint.demote() + son.promote() +
                        (rebalancingPoint.getNodeType() == NodeType.LEAF ? rebalancingPoint.demote() : 0);
            case L2R1:
                steps += rotate(LEFT, son) + rotate(RIGHT, rebalancingPoint);
                WAVLNode newRoot = rebalancingPoint.parent;
                steps += newRoot.promote() + newRoot.promote();
                steps += rebalancingPoint.demote() + rebalancingPoint.demote();
                steps += son.demote();
                break;
        }
        return steps;
    }

    /**
     * Rebalances tree after deletion
     * Complexity: O(logn)
     *
     * @param rebalancingPoint
     * @return number of rebalancing operations (promote/demote/rotate)
     */
    private int rebalanceDelete(WAVLNode rebalancingPoint) {
        if (!rebalancingPoint.isRealNode()) {
            return 0;
        }
        switch (rebalancingPoint.getEdges()) {
            case L2R2:
                return rebalancingPoint.getNodeType() == NodeType.LEAF
                        ? rebalancingPoint.demote() + rebalanceDelete(rebalancingPoint.parent) : 0;
            case L2R3:
            case L3R2:
                return rebalancingPoint.demote() + rebalanceDelete(rebalancingPoint.parent);
            case L3R1:
                return rebalanceDelete31(rebalancingPoint);
            case L1R3:
                return rebalanceDelete13(rebalancingPoint);
            default:
                return 0;
        }
    }

    /**
     * Rebalances tree after Case 2 insertion
     * Complexity: O(logn)
     *
     * @param rebalancingPoint
     * @return number of rebalancing operations (promote/demote/rotate)
     */
    private int rebalanceInsert02(WAVLNode rebalancingPoint) {
        WAVLNode son = rebalancingPoint.left;
        switch (son.getEdges()) {
            case L1R2:
                return rotate(RIGHT, rebalancingPoint) + rebalancingPoint.demote();
            case L2R1:
                WAVLNode newRoot = son.right;
                return rotate(LEFT, son) + rotate(RIGHT, rebalancingPoint) + son.demote()
                        + rebalancingPoint.demote() + newRoot.promote();
            default:
                return 0;
        }
    }

    /**
     * Rebalances tree after Case 2 insertion
     * Complexity: O(logn)
     *
     * @param rebalancingPoint
     * @return number of rebalancing operations (promote/demote/rotate)
     */
    private int rebalanceInsert20(WAVLNode rebalancingPoint) {
        WAVLNode son = rebalancingPoint.right;
        switch (son.getEdges()) {
            case L2R1:
                return rotate(LEFT, rebalancingPoint) + rebalancingPoint.demote();
            case L1R2:
                WAVLNode newRoot = son.left;
                return rotate(RIGHT, son) + rotate(LEFT, rebalancingPoint) + son.demote()
                        + rebalancingPoint.demote() + newRoot.promote();
            default:
                return 0;
        }
    }

    /**
     * Rebalances tree after insertion
     * Complexity: O(logn)
     *
     * @param rebalancingPoint
     * @return number of rebalancing operations (promote/demote/rotate)
     */
    private int rebalanceInsert(WAVLNode rebalancingPoint) {
        if (!rebalancingPoint.isRealNode()) {
            return 0;
        }
        Edges edges = rebalancingPoint.getEdges();
        switch (edges) {
            case L0R1:
            case L1R0:
                return rebalancingPoint.promote() + rebalanceInsert(rebalancingPoint.parent);
            case L0R2:
                return rebalanceInsert02(rebalancingPoint);
            case L2R0:
                return rebalanceInsert20(rebalancingPoint);
            default:
                return 0;
        }
    }

/*********************************************************WAVLNode*************************************************/

    /**
     * public class WAVLNode
     * <p>
     * If you wish to implement classes other than WAVLTree
     * (for example WAVLNode), do it in this file, not in
     * another file.
     * This class can and must be modified.
     * (It must implement IWAVLNode)
     */
    public class WAVLNode implements IWAVLNode {

        Integer key;
        String value;

        WAVLNode right;
        WAVLNode left;
        WAVLNode parent;

        int rank;
        int size;

        /**
         * External node constructor
         */
        public WAVLNode() {
            rank = -1;
            key = -1;
        }

        /**
         * WAVLNode constructor
         *
         * @param key
         * @param value
         * @param parent
         */
        public WAVLNode(int key, String value, WAVLNode parent) {
            right = EXTERNAL_NODE;
            left = EXTERNAL_NODE;
            this.parent = parent;
            rank = 0;
            this.key = key;
            size = 1;
            this.value = value;
        }

        /**
         * Complexity: O(1)
         *
         * @return
         */
        public int getKey() {
            return key;
        }

        /**
         * Complexity: O(1)
         *
         * @return
         */
        public String getValue() {
            return value;
        }

        /**
         * Complexity: O(1)
         *
         * @return
         */
        public IWAVLNode getLeft() {
            return left;
//            return _left == EXTERNAL_NODE ? null : _left; // to be replaced by student code
        }

        /**
         * Complexity: O(1)
         *
         * @return
         */
        public WAVLNode getRight() {
            return right;
            //return _right == EXTERNAL_NODE ? null : _right; // to be replaced by student code
        }

        /**
         * Complexity: O(1)
         *
         * @return
         */
        public boolean isRealNode() {
            return rank != -1;
        }

        /**
         * Complexity: O(1)
         *
         * @return
         */
        public int getSubtreeSize() {
            return size;
        }

        /******************************************************PRIVATE*************************************************/

        /**
         * Complexity: O(1)
         *
         * @param side
         * @return node's son on given side
         */
        private WAVLNode getSon(boolean side) {
            if (side == LEFT) {
                return left;
            } else {
                assert side == RIGHT;
                return right;
            }
        }

        /**
         * Sets node's son on given side to given node
         * Complexity: O(1)
         *
         * @param side
         * @param newSon
         */
        private void setSon(boolean side, WAVLNode newSon) {
            if (side == LEFT) {
                left = newSon;
            } else {
                assert side == RIGHT;
                right = newSon;
            }
        }

        /**
         * Complexity: O(1)
         *
         * @return they type of the node (leaf/unary/internal)
         */
        private NodeType getNodeType() {
            if (!right.isRealNode() && !left.isRealNode()) {
                return NodeType.LEAF;
            }
            if (!right.isRealNode() && left.isRealNode())
                return NodeType.LEFT_UNARY;
            if (right.isRealNode() && !left.isRealNode())
                return NodeType.RIGHT_UNARY;
            return NodeType.INTERNAL;
        }

        /**
         * Complexity: O(1)
         * precondition: node != root
         *
         * @return the node's side compared to its parent
         */
        private boolean getSide() {
            assert parent.isRealNode();
            if (this == parent.left) {
                return LEFT;
            } else {
                assert this == parent.right;
                return RIGHT;
            }
        }

        /**
         * Complexity: O(1)
         *
         * @return the ranks state on node's edges
         */
        private Edges getEdges() {
            int leftEdge = rank - left.rank;
            int rightEdge = rank - right.rank;
            if (leftEdge == 2 && rightEdge == 2) {
                return Edges.L2R2;
            }
            if (leftEdge == 3 && rightEdge == 1) {
                return Edges.L3R1;
            }
            if (leftEdge == 1 && rightEdge == 3) {
                return Edges.L1R3;
            }
            if (leftEdge == 3 && rightEdge == 2) {
                return Edges.L3R2;
            }
            if (leftEdge == 2 && rightEdge == 3) {
                return Edges.L2R3;
            }
            if (leftEdge == 1 && rightEdge == 1) {
                return Edges.L1R1;
            }
            if (leftEdge == 2 && rightEdge == 1) {
                return Edges.L2R1;
            }
            if (leftEdge == 1 && rightEdge == 2) {
                return Edges.L1R2;
            }
            if (leftEdge == 0 && rightEdge == 1) {
                return Edges.L0R1;
            }
            if (leftEdge == 1 && rightEdge == 0) {
                return Edges.L1R0;
            }
            if (leftEdge == 0 && rightEdge == 2) {
                return Edges.L0R2;
            }
            if (leftEdge == 2 && rightEdge == 0) {
                return Edges.L2R0;
            }
            return Edges.OTHER;
        }

        /**
         * Decreases node's rank by 1
         * Complexity: O(1)
         *
         * @return 1
         */
        private int demote() {
            rank--;
            return 1;
        }

        /**
         * Increases node's rank by 1
         * Complexity: O(1)
         *
         * @return 1
         */
        private int promote() {
            rank++;
            return 1;
        }

        /**
         * Complexity: O(1)
         * @return node's rank
         */
        public int getRank() {
            return rank;
        }

    }

/********************************************************IWAVLNode*************************************************/


    /**
     * public interface IWAVLNode
     * ! Do not delete or modify this - otherwise all tests will fail !
     */
    public interface IWAVLNode {
        public int getKey(); //returns node's key (for virtuval node return -1)

        public String getValue(); //returns node's value [info] (for virtuval node return null)

        public IWAVLNode getLeft(); //returns left child (if there is no left child return null)

        public IWAVLNode getRight(); //returns right child (if there is no right child return null)

        public boolean isRealNode(); // Returns True if this is a non-virtual WAVL node (i.e not a virtual leaf or a sentinal)

        public int getSubtreeSize(); // Returns the number of real nodes in this node's subtree (Should be implemented in O(1))
    }

}


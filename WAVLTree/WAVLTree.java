
import java.util.ArrayList;
import java.util.List;


/**
 * WAVLTree
 * <p>
 * An implementation of a WAVL Tree with distinct integer keys and info
 */

public class WAVLTree {

    // WAVLNode consts
    public static final int INVALID_RANK_DIFFERENCE = -100;
    public static final boolean RIGHT = true;
    public static final boolean LEFT = false;
    public static final WAVLNode INSERT_ERROR = null;
    public static WAVLNode DELETE_ERROR;
    public static WAVLNode EXTERNAL_NODE;
    public static final WAVLNode KEY_ALREADY_EXIST = null;

    // create beforehand rank diffrent statuses for pooling purposes
    public static Pair<Integer, Integer> STATUS_1_1;
    public static Pair<Integer, Integer> STATUS_1_2;
    public static Pair<Integer, Integer> STATUS_2_1;
    public static Pair<Integer, Integer> STATUS_2_2;
    // bad ones
    public static Pair<Integer, Integer> STATUS_0_1;
    public static Pair<Integer, Integer> STATUS_0_2;
    public static Pair<Integer, Integer> STATUS_1_3;
    public static Pair<Integer, Integer> STATUS_2_3;

    // enum of node types
    enum NodeTypes {
        // leaf without a parent
        ROOT_LEAF,
        // leaf that is the right child of his parent
        RIGHT_LEAF,
        // leaf that is the left child of his parent
        LEFT_LEAF,
        // unary node that is has a right child
        RIGHT_UNARY,
        // unary node that is has a left child
        LEFT_UNARY,
        // a node with two kids
        INTERNAL
    }

    // enum status equality types
    enum EqualityTypes {
        DIFFERENT, ORDERED, DIS_ORDERED
    }

    // member containing the root of WAVLTree
    private WAVLNode root;
    // current amount of WAVLNodes in the WAVLTree
    private int size;
    // member containing the minimal key node of WAVLTree
    private WAVLNode min;
    // member containing the maximal key node of WAVLTree
    private WAVLNode max;

    // ctor

    public WAVLTree() { // set initial values to members
        // set initial values to members
        EXTERNAL_NODE = new WAVLNode();
        root = EXTERNAL_NODE;
        size = 0;
        DELETE_ERROR = new WAVLNode(-1, null);
        //EXTERNAL_NODE = new WAVLNode(-1, null);
        max = null;
        min = null;

        // valid statuses
        STATUS_1_1 = new Pair<Integer, Integer>(1, 1);
        STATUS_1_2 = new Pair<Integer, Integer>(1, 2);
        STATUS_2_1 = new Pair<Integer, Integer>(2, 1);
        STATUS_2_2 = new Pair<Integer, Integer>(2, 2);
        // invalid statuses
        STATUS_0_1 = new Pair<Integer, Integer>(0, 1);
        STATUS_0_2 = new Pair<Integer, Integer>(0, 2);
        STATUS_1_3 = new Pair<Integer, Integer>(1, 3);
        STATUS_2_3 = new Pair<Integer, Integer>(2, 3);
    }


    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        return (root == EXTERNAL_NODE);
    }

    /**
     * public String search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    public String search(int k) {
        WAVLNode retrievedNode = innerSearch(k, new ArrayList<>());
        if (retrievedNode == null) {
            return null;
        }
        return retrievedNode.value;
    }

    /**
     * public int insert(int k, String i)
     * <p>
     * inserts an item with key k and info i to the WAVL tree. the tree must
     * remain valid (keep its invariants). returns the number of rebalancing
     * operations, or 0 if no rebalancing operations were necessary. returns -1
     * if an item with key k already exists in the tree.
     */
    public int insert(int k, String i) {
        if (root == EXTERNAL_NODE) {
            insertBST(k, i);
            size++;
            return 0;
        }
        WAVLNode insertionPoint = insertBST(k, i);
        if (insertionPoint != INSERT_ERROR) {
            size++;
            // 1+ cause the insertion itself is considered as a rebalancing step
            // also
            return 1 + rebalance(insertionPoint);
        }

        // if we reached here the key is already in the tree
        return -1;
    }

    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there; the tree
     * must remain valid (keep its invariants). returns the number of
     * rebalancing operations, or 0 if no rebalancing operations were needed.
     * returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k) {
        if (size == 0) {
            return -1;
        }
        WAVLNode deletionPoint = deleteBST(k);
        if (deletionPoint != DELETE_ERROR) {
            size--;
            // 1+ cause the deletion itself is considered as a rebalancing step also
            return 1 + rebalance(deletionPoint);
        }

        return -1;
    }

    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree, or null
     * if the tree is empty
     */
    public String min() {
        return min == null ? null : min.value;
    }

    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree, or null if
     * the tree is empty
     */
    public String max() {
        return max == null ? null : max.value;
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree, or an empty
     * array if the tree is empty.
     */
    public int[] keysToArray() {
        int[] arr = new int[size];
        recInsertToSortedKeysArray(arr, root, 0);
        return arr;
    }

    /**
     * public String[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree, sorted by their
     * respective keys, or an empty array if the tree is empty.
     */
    public String[] infoToArray() {
        String[] arr = new String[size];
        recInsertToSortedInfoArray(arr, root, 0);
        return arr;
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     * <p>
     * precondition: none postcondition: none
     */
    public int size() {
        return size;
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root WAVL node, or null if the tree is empty
     * <p>
     * precondition: none postcondition: none
     */
    public IWAVLNode getRoot() {
        return root;
    }

    /**
     * public int select(int i)
     * <p>
     * Returns the value of the i'th smallest key (return -1 if tree is empty)
     * Example 1: select(1) returns the value of the node with minimal key
     * Example 2: select(size()) returns the value of the node with maximal key
     * Example 3: select(2) returns the value 2nd smallest minimal node, i.e the
     * value of the node minimal node's successor
     * <p>
     * precondition: size() >= i > 0 postcondition: none
     */
    public String select(int i) {
        return null;
    }

    public IWAVLNode findbyKey(int k, IWAVLNode current) {
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
     * public interface IWAVLNode ! Do not delete or modify this - otherwise all
     * tests will fail !
     */
    public interface IWAVLNode {
        public int getKey(); // returns node's key (for virtuval node return -1)

        public String getValue(); // returns node's value [info] (for virtuval
        // node return null)

        public IWAVLNode getLeft(); // returns left child (if there is no left
        // child return null)

        public IWAVLNode getRight(); // returns right child (if there is no
        // right child return null)

        public boolean isRealNode(); // Returns True if this is a non-virtual
        // WAVL node (i.e not a virtual leaf or
        // a sentinal)

        public int getSubtreeSize(); // Returns the number of real nodes in this
        // node's subtree (Should be implemented
        // in O(1))
    }

    // ***********private functions****************

    /*
     * inner function that returns WAVLNode with key 'k', if there is no node
     * with key 'k' the function will return null.
     */
    private WAVLNode innerSearch(int k, List<WAVLNode> visitedNodes) {
        WAVLNode currentNode = root;
        while (currentNode != EXTERNAL_NODE) {
            if (k == currentNode.key) {
                break;
            }
            visitedNodes.add(currentNode);
            if (k > currentNode.key) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }
        return currentNode;
    }

    /*
     * The function fills sortedArray recursively with keys in the WAVLTree.
     */
    private int recInsertToSortedKeysArray(int[] sortedArray, WAVLNode node,
                                           int index) {
        if (node == EXTERNAL_NODE) {
            return index;
        }
        index = recInsertToSortedKeysArray(sortedArray, node.getSon(LEFT),
                index);
        sortedArray[index] = node.key;
        index++;
        index = recInsertToSortedKeysArray(sortedArray, node.getSon(RIGHT),
                index);
        return index;
    }

    /*
     * The function fills sortedArray recursively with values in the WAVLTree.
     */
    private int recInsertToSortedInfoArray(String[] sortedArray, WAVLNode node,
                                           int index) {
        if (node == EXTERNAL_NODE) {
            return index;
        }
        index = recInsertToSortedInfoArray(sortedArray, node.getSon(LEFT),
                index);
        sortedArray[index] = node.value;
        index++;
        index = recInsertToSortedInfoArray(sortedArray, node.getSon(RIGHT),
                index);
        return index;
    }

    /*
     * The function inserts new node with key 'k' and info 'info' to the
     * WAVLTree and returns the father of new inserted node. If there already is
     * a node in the tree with key 'k' the return value will be
     * KEY_ALREADY_EXIST, and the tree will remain as it was before calling he
     * function The function will return INSERT_ERROR for an error that occurred
     * while searching for an insertion point for new node.
     */
    private WAVLNode insertBST(int k, String value) {
        List<WAVLNode> lstNodes = new ArrayList<WAVLNode>();
        WAVLNode currentNode = root;
        if (root == EXTERNAL_NODE) {
            WAVLNode newNode = new WAVLNode(k, value);
            root = newNode;
            updateMinMax(newNode, true);
            return newNode.parent;
        }
        boolean nextStepInTree = true;
        while (nextStepInTree) {
            lstNodes.add(currentNode);
            if (k == currentNode.key) {
                return KEY_ALREADY_EXIST;
            }
            if (k > currentNode.key) {
                if (currentNode.right == EXTERNAL_NODE) {
                    WAVLNode newNode = new WAVLNode(k, value);
                    newNode.parent = currentNode;
                    currentNode.right = newNode;

                    updateMinMax(newNode, true);
                    for (WAVLNode node : lstNodes) {
                        node.size++;
                    }
                    return newNode.parent;
                }
                currentNode = currentNode.right;
            } else {
                if (currentNode.left == EXTERNAL_NODE) {
                    WAVLNode newNode = new WAVLNode(k, value);
                    newNode.parent = currentNode;
                    currentNode.left = newNode;
                    updateMinMax(newNode, true);
                    for (WAVLNode node : lstNodes) {
                        node.size++;
                    }
                    return newNode.parent;
                }
                currentNode = currentNode.left;
            }
        }
        return INSERT_ERROR;
    }

    /*
     * The function updates min and max members of the class after insertion or
     * deletion according to 'node''s key if 'isNewNode' is true, then 'node' is
     * a new node in the tree, otherwise node was recently deleted from the tree
     */
    private void updateMinMax(WAVLNode node, boolean isNewNode) {
        if (isNewNode == true) {
            if (min == null) {
                min = node;
            } else {
                if (node.key < min.key) {
                    min = node;
                }
            }
            if (max == null) {
                max = node;
            } else {
                if (node.key > max.key) {
                    max = node;
                }
            }
        } else {
            if (node.key == min.key) {
                min = successor(min);
            }

            if (node.key == max.key) {
                max = predecessor(max);
            }
        }
    }

    /*
     * The function returns the node with the closest smaller key comparing to
     * 'currentNode''s key
     */
    private WAVLNode predecessor(WAVLNode currentNode) {
        if (currentNode.left != null) {
            return lateralBranche(RIGHT, currentNode.left);
        }

        WAVLNode parent = currentNode.parent;
        while (parent != null) {
            if (parent.left == EXTERNAL_NODE) {
                break;
            }
            if (currentNode.key == parent.left.key) {
                currentNode = parent;
                parent = currentNode.parent;
            } else {
                break;
            }
        }
        return parent;
    }

    /*
     * The function returns the node with the closest bigger key comparing to
     * 'currentNode''s key
     */
    private WAVLNode successor(WAVLNode currentNode) {
        if (currentNode.right != null) {
            return lateralBranche(LEFT, currentNode.right);
        }

        WAVLNode parent = currentNode.parent;
        while (parent != null) {
            if (parent.right == EXTERNAL_NODE) {
                break;
            }

            if (currentNode.key == parent.right.key) {
                currentNode = parent;
                parent = currentNode.parent;
            } else {
                break;
            }
        }
        return parent;
    }

    /*
     * The function returns the node with min/max key from 'rootNode''s children
     */
    private WAVLNode lateralBranche(boolean side, WAVLNode rootNode) {
        WAVLNode currentNode = rootNode;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.getSon(side) != null) {
            currentNode = currentNode.getSon(side);
        }
        return currentNode;
    }

    /*
     * The function assures the tree is a valid WAVL tree, and if not handles
     * it's nodes that are in an invalid WAVL status. the function will return
     * the amount of rebalancing steps taken.
     */
    private int rebalance(WAVLNode rebalancePoint) {
        if (rebalancePoint == null) {
            // first insertion or last deletion
            return 0;
        }

        Pair<Integer, Integer> status = rebalancePoint.getRanksState();

        // insertion cases
        if (status.specializedEquals(STATUS_0_1, true)) {
            return handle01Status(rebalancePoint);
        }
        if (status.specializedEquals(STATUS_0_2, true)) {

            boolean side = getStatusSide(status, STATUS_0_2);
            return handle02Status(side, rebalancePoint);
        }

        // deletion cases
        if (status.specializedEquals(STATUS_2_2, false)
                && rebalancePoint.rank == 1) {
            return handle22Status(rebalancePoint);
        }

        if (status.specializedEquals(STATUS_2_3, true)) {
            return handle23Status(rebalancePoint);
        }

        if (status.specializedEquals(STATUS_1_3, true)) {
            boolean side = getStatusSide(status, STATUS_1_3);
            return handle31Status(side, rebalancePoint);
        }

        return 0;
    }

    /*
     * the function gets a status and returns the orientation of 'status' status
     * comparing with 'statusToCompare' status
     *
     * @pre: the two statuses are of equality type ORDERED or DIS-ORDERED
     */
    private boolean getStatusSide(Pair<Integer, Integer> status,
                                  Pair<Integer, Integer> statusToCompare) {
        boolean side;
        if (status.specializedEquals(statusToCompare, false)) {
            side = LEFT;
        } else {
            side = RIGHT;
        }
        return side;
    }

    /*
     * The function makes continues rebalancing of the tree starting from
     * 'currentNode's parent. return value is number of rebalancing steps taken
     */
    private int bubbleRebalancing(int lastSteps, WAVLNode currentNode) {
        int steps = 0;
        WAVLNode parentNode = currentNode.parent;
        // stop if last rebalancing took 0 steps or if we rebalanced all nodes
        // to the root
        if (lastSteps == 0 || currentNode == null) {
            return steps;
        }

        return steps + rebalance(parentNode);
    }

    /*
     * the function gets a the EqualityTypes type : if the statuses are the same
     * ORDERED will be returned if the 'sonRankDiff' is the same flipped
     * 'status' (flip(1_2 status) equals 2_1 status) DIS_ORDERED will be
     * returned if the statuses are non of the above DIFFERENT will be returned
     */
    private EqualityTypes getEqualityType(Pair<Integer, Integer> aStatus,
                                          Pair<Integer, Integer> bStatus) {
        if (aStatus.specializedEquals(bStatus, false)) {
            return EqualityTypes.ORDERED;
        }

        if (aStatus.specializedEquals(bStatus, true)) {
            return EqualityTypes.DIS_ORDERED;
        }

        return EqualityTypes.DIFFERENT;
    }

    /*
     * after rebalancing we need to assure root is updated. the function will
     * set the root to new value if needed
     */
    private void checkChangeRoot(WAVLNode nodeToCheck, WAVLNode nodeReplacement) {
        if (nodeToCheck.key == root.key) {
            root = nodeReplacement;
        }
    }

    /*
     * The function handles 0_1 status for given node return value is number of
     * rebalancing steps required for the status handling
     */
    private int handle01Status(WAVLNode node) {
        int steps = 0;
        steps += node.promote();
        steps += bubbleRebalancing(steps, node);
        return steps;
    }

    /*
     * The function handles 0_2 status for given node return value is number of
     * rebalancing steps required for the status handling
     */
    private int handle02Status(boolean side, WAVLNode node) {
        int steps = 0;
        WAVLNode son = node.getSon(side);
        Pair<Integer, Integer> sonRankDiff = son.getRanksState();
        EqualityTypes equalityType = getEqualityType(sonRankDiff, STATUS_1_2);

        if (equalityType == EqualityTypes.DIFFERENT) {
            return steps;
        }

        if ((side == LEFT && equalityType == EqualityTypes.ORDERED)
                || (side == RIGHT && equalityType == EqualityTypes.DIS_ORDERED)) {
            node.demote();
            checkChangeRoot(node, node.getSon(side));
            steps += node.rotate(!side);
            return steps;
        }

        if (((side == LEFT && equalityType == EqualityTypes.DIS_ORDERED) || (side == RIGHT && equalityType == EqualityTypes.ORDERED))) {
            node.demote();
            node.getSon(side).demote();
            node.getSon(side).getSon(!side).promote();
            checkChangeRoot(node, node.getSon(side).getSon(!side));
            steps += node.doubleRotate(!side);
            return steps;
        }

        return steps;
    }

    /*
     * The function handles 2_2 status for given node
     * return value is number of rebalancing steps required for the status handling
     */
    private int handle22Status(WAVLNode node) {
        int steps = 0;
        steps += node.demote();
        steps += bubbleRebalancing(steps, node);
        return steps;
    }

    /*
     * The function handles 2_3 status for given node
     * return value is number of rebalancing steps required for the status handling
     */
    private int handle23Status(WAVLNode node) {
        int steps = 0;
        steps += node.demote();
        steps += bubbleRebalancing(steps, node);
        return steps;

    }

    /*
     * The function handles 3_1 status for given node
     * return value is number of rebalancing steps required for the status handling
     */
    private int handle31Status(boolean side, WAVLNode node) {
        int steps = 0;
        WAVLNode son = node.getSon(side);
        Pair<Integer, Integer> sonRankDiff = son.getRanksState();
        EqualityTypes equalityType = getEqualityType(sonRankDiff, STATUS_1_2);

        if (equalityType == EqualityTypes.DIFFERENT) {
            if (sonRankDiff.specializedEquals(STATUS_2_2, false)) {
                steps += son.demote() + node.demote();
                steps += bubbleRebalancing(steps, node);
                return steps;
            }

            if (sonRankDiff.specializedEquals(STATUS_1_1, false)) {
                node.demote();
                son.promote();
                checkChangeRoot(node, son);
                steps += node.rotate(!side);
                if (node.isLeaf()) {
                    node.demote();
                }
                return steps;
            }
            return steps;
        }

        if ((side == LEFT && equalityType == EqualityTypes.ORDERED)
                || (side == RIGHT && equalityType == EqualityTypes.DIS_ORDERED)) {
            node.demote();
            son.promote();
            checkChangeRoot(node, son);
            steps += node.rotate(!side);

            //the special case of leaf with rank 1 after rotate
            if (node.getRanksState().specializedEquals(STATUS_2_2, false) && node.rank == 1) {
                node.demote();
            }

            return steps;
        }

        if (((side == LEFT && equalityType == EqualityTypes.DIS_ORDERED)
                || (side == RIGHT && equalityType == EqualityTypes.ORDERED))) {
            node.demote();
            node.demote();
            son.demote();
            son.getSon(!side).promote();
            son.getSon(!side).promote();
            checkChangeRoot(node, son.getSon(!side));
            steps += node.doubleRotate(!side);
            return steps;
        }

        return steps;
    }

    /*
    * The function deletes a node with key 'k' from the WAVLTree.
    * If there is no node in the tree with key 'k' the return value will be KEY_ALREADY_EXIST,
    *  	and the tree will remain as it was before calling he function
    * The function will return INSERT_ERROR for an error that occurred while searching for an insertion point for new node.
    */
    private WAVLNode deleteBST(int k) {
        List<WAVLNode> visited = new ArrayList<WAVLNode>();
        WAVLNode retrievedNode = innerSearch(k, visited);
        if (retrievedNode == null) {
            return DELETE_ERROR;
        }
        for (WAVLNode node : visited) {
            node.size--;
        }
        updateMinMax(retrievedNode, false);
        return innerDeleteNode(retrievedNode);
    }

    /*
    * the function gets a node and actually performs the deletion itself.
    */
    private WAVLNode innerDeleteNode(WAVLNode node) {
        WAVLNode deletionPoint = node.parent;
        NodeTypes type = node.nodeType();

        switch (type) {
            case ROOT_LEAF:
                root = EXTERNAL_NODE;
                break;
            case RIGHT_LEAF:
                node.parent.right = EXTERNAL_NODE;
                break;
            case LEFT_LEAF:
                node.parent.left = EXTERNAL_NODE;
                break;
            case LEFT_UNARY:
                handleUnaryNodeDeletion(node, LEFT);
                break;
            case RIGHT_UNARY:
                handleUnaryNodeDeletion(node, RIGHT);
                break;
            case INTERNAL:
                deletionPoint = handleInternalNodeDeletion(node);
                break;
            default:
                System.out.println("***%#@DELETE_ERROR**********");
                return DELETE_ERROR;
        }

        return deletionPoint;
    }

    /*
    * the function handles a deletion of an unary node of 'side' type (different handling if the node is 'LEFT_UNARY' and 'RIGHT_UNARY')
    */
    private void handleUnaryNodeDeletion(WAVLNode unaryNode, boolean side) {
        WAVLNode son = unaryNode.getSon(side);
        son.parent = unaryNode.parent;
        if (unaryNode == root) {
            root = son;
            return;
        }

        // update parent node
        if (unaryNode.parent.getSon(RIGHT) != EXTERNAL_NODE) {
            if (unaryNode.parent.getSon(RIGHT).key == unaryNode.key) {
                unaryNode.parent.setSon(RIGHT, son);
                return;
            }
        }

        unaryNode.parent.setSon(LEFT, son);
    }

    /*
    * The function handles a deletion of an internal node.
    * searches for a successor, switches between successor and the node to delete,
    * and then deletes the node to delete.
    */
    private WAVLNode handleInternalNodeDeletion(WAVLNode nodeToDelete) {
        WAVLNode successor = successor(nodeToDelete);
        switchNodes(successor, nodeToDelete);
        return innerDeleteNode(nodeToDelete);
    }

    /*
     * the function switches between two node.
     * the function will not update parent pointers of new sons of the switched nodes.
     */
    private void switchNodes(WAVLNode aNode, WAVLNode bNode) {
        boolean originalASide = getNodeParentSide(aNode);
        boolean originalBSide = getNodeParentSide(bNode);

        switchParents(aNode, bNode);
        switchRanksAndSizes(aNode, bNode);
        switchSons(aNode, bNode);
        // if the successor and retrieved node are parent and son, special case
        if (aNode.parent == bNode) {
            // RIGHT set cause successor has to be the right son of node to node to delete
            bNode.setSon(RIGHT, aNode);
        } else if (bNode.parent == aNode) {
            // RIGHT set cause successor has to be the right son of node to node to delete
            aNode.setSon(RIGHT, bNode);
        }

        // re assign parent pointers to updated nodes
        if (aNode.parent != null) {
            aNode.parent.setSon(originalBSide, aNode);
        }
        if (bNode.parent != null) {
            bNode.parent.setSon(originalASide, bNode);
        }

        updateSonParents(aNode);
        updateSonParents(bNode);
    }

    /*
     * the function sets the parent of the sons to 'node' value.
     */
    private void updateSonParents(WAVLNode node) {
        if (node.getSon(LEFT) != EXTERNAL_NODE) {
            node.getSon(LEFT).parent = node;
        }
        if (node.getSon(RIGHT) != EXTERNAL_NODE) {
            node.getSon(RIGHT).parent = node;
        }
    }

    /*
     * The function switches the sons of the two nodes
     */
    private void switchSons(WAVLNode aNode, WAVLNode bNode) {
        // save the original sons
        WAVLNode aNodeLeftChild = aNode.getSon(LEFT);
        WAVLNode aNodeRightChild = aNode.getSon(RIGHT);
        WAVLNode bNodeLeftChild = bNode.getSon(LEFT);
        WAVLNode bNodeRightChild = bNode.getSon(RIGHT);

        //set a node new sons
        switchSon(aNode, bNodeLeftChild, LEFT);
        switchSon(aNode, bNodeRightChild, RIGHT);

        //set b node new sons
        switchSon(bNode, aNodeLeftChild, LEFT);
        switchSon(bNode, aNodeRightChild, RIGHT);
    }

    /*
     * the function sets new 'sideNodeToSet' value to the 'side' son of 'node'.
     */
    private void switchSon(WAVLNode node, WAVLNode sideNodeToSet, boolean side) {
        node.setSon(side, sideNodeToSet);
    }

    /*
     * The function switches between the parents of the two nodes.
     */
    private void switchParents(WAVLNode aNode, WAVLNode bNode) {
        WAVLNode tmpAParent = aNode.parent;
        WAVLNode tmpBParent = bNode.parent;

        if (aNode.parent != bNode && bNode.parent != aNode) {
            aNode.parent = bNode.parent;
            bNode.parent = tmpAParent;
        } else {
            if (aNode.parent == bNode) {
                bNode.parent = aNode;
                aNode.parent = tmpBParent;
            } else {
                aNode.parent = bNode;
                bNode.parent = tmpAParent;
            }
        }
    }

    /*
     * The function switches the ranks between the two nodes.
     */
    private void switchRanksAndSizes(WAVLNode aNode, WAVLNode bNode) {
        int tmpARank = aNode.rank;
        int tmpASize = aNode.size;

        aNode.rank = bNode.rank;
        bNode.rank = tmpARank;

        aNode.size = bNode.size;
        bNode.size = tmpASize;

        if (aNode.key == root.key) {
            root = bNode;
        } else if ((bNode.key == root.key)) {
            root = aNode;
        }
    }

    /*
     * The function gets the side current node is connected to it's parents
     * @pre: node has a parent != null
     */
    private boolean getNodeParentSide(WAVLNode node) {
        boolean originalSide = LEFT;
        if (node.parent != null) {
            if (node.parent.getSon(RIGHT) != EXTERNAL_NODE) {
                if (node.parent.getSon(RIGHT).key == node.key) {
                    originalSide = RIGHT;
                }
            }
        }
        return originalSide;
    }

    /**
     * public class WAVLNode
     * <p>
     * If you wish to implement classes other than WAVLTree (for example
     * WAVLNode), do it in this file, not in another file. This class can and
     * must be modified. (It must implement IWAVLNode)
     */
    public class WAVLNode implements IWAVLNode {

        // class members
        WAVLNode right;
        WAVLNode left;
        WAVLNode parent;
        int rank;
        int key;
        int size;
        String value;

        public WAVLNode(int nodeKey, String nodeInfo) {
            // set initial values
            right = EXTERNAL_NODE;
            left = EXTERNAL_NODE;
            parent = null;
            rank = 0;
            key = nodeKey;
            size = 1;
            value = nodeInfo;

        }

        public WAVLNode() {
            rank = -1;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public IWAVLNode getLeft() {
            return getSon(LEFT);
        }

        public IWAVLNode getRight() {
            return getSon(RIGHT);
        }

        // Returns True if this is a non-virtual WAVL node (i.e not a virtual
        // leaf or a sentinal)
        public boolean isRealNode() {
            return getRank() != -1;
        }

        public int getSubtreeSize() {
            return size;
        }

        /*
         * The function returns 'side' son of the WAVLNode.
         */
        public WAVLNode getSon(boolean side) {
            if (side == RIGHT) {
                return (right);
            }
            return (left);
        }

        /*
         * The function sets 'side' son to 'newSon'.
         */
        public void setSon(boolean side, WAVLNode newSon) {
            if (side == RIGHT) {
                right = newSon;
                return;
            }
            left = newSon;
        }

        /*
           * the function checks if current node is a leaf and returns true if so, false otherwise.
           */
        public boolean isLeaf() {
            if (right == EXTERNAL_NODE && left == EXTERNAL_NODE) {
                return true;
            }
            return false;
        }

        /*
         * The function returns a pair representing the rank difference between
         * the node and both it's children. return value will be <left child's
         * rank difference, right child's rank difference>
         */
        public Pair<Integer, Integer> getRanksState() {
            // case of an external right child
            int rightRank = rank + 1;
            if (right != EXTERNAL_NODE) {
                rightRank = right.getRankDifference();
            }
            int leftRank = rank + 1;
            if (left != EXTERNAL_NODE) {
                leftRank = left.getRankDifference();
            }
            Pair<Integer, Integer> diffs = new Pair<>(leftRank, rightRank);
            return diffs;
        }

        /*
         * The function returns difference between the rank of the WAVLNode and
         * it's parent.
         */
        public int getRankDifference() {
            if (parent == null) {
                return INVALID_RANK_DIFFERENCE;
            }
            return (parent.rank - rank);
        }

        public int promote() {
            rank++;
            size++;
            return 1;
        }

        /*
         * The function decreases the WAVLNode's rank by 1. return value is
         * number of rebalancing steps demote takes
         */
        public int demote() {
            rank--;
            size--;
            return 1;
        }

        public int getSize(WAVLNode node) {
            return node == null ? 0 : node.size;
        }

        public void rotateRight() {
            WAVLNode y = this;
            WAVLNode x = y.left;
            WAVLNode c = y.right;
            WAVLNode a = x.left;
            WAVLNode b = x.right;

            // after rotate
            x.right = y;
            x.parent = y.parent;
            y.left = b;
            y.parent = x;

            if (b != null) {
                b.parent = y;
            }

            if (y == root) {
                root = x;
            } else {
                if (y.parent.left == y) {
                    y.parent.left = x;
                } else {
                    y.parent.right = x;
                }
            }

            //update sizes
            y.size = getSize(b) + getSize(c) + 1;
            x.size = getSize(a) + getSize(y) + 1;
        }

        public void rotateLeft() {
            WAVLNode x = this;
            WAVLNode y = x.right;
            WAVLNode a = x.left;
            WAVLNode c = y.right;
            WAVLNode b = y.left;

            //after rotate
            y.left = x;
            y.parent = x.parent;
            x.right = b;
            x.parent = y;

            if (b != null) {
                b.parent = x;
            }

            if (x == root) {
                root = y;
            } else {
                if (x.parent.left == x) {
                    x.parent.left = y;
                } else {
                    x.parent.right = y;
                }
            }

            // update sizes
            x.size = getSize(a) + getSize(b) + 1;
            y.size = getSize(x) + getSize(c) + 1;
        }


        /*
         * The function rotates to given 'side' to get more balanced state of
         * the tree under WAVLNode.
         */
        public int rotate(boolean side) {
            if (side) {
                rotateRight();
            } else {
                rotateLeft();
            }
            return 1;
        }

        /*
         * The function rotates two times to given 'side' to get more balanced state of the tree under WAVLNode.
         */
        public int doubleRotate(boolean side) {
            int steps = 0;
            steps += getSon(!side).rotate(!side);
            steps += rotate(side);
            return steps;
        }

        /*
       * The function returns current state of the WAVLNode mapped to NodeTypes enum.
       */
        public NodeTypes nodeType() {
            if (right == EXTERNAL_NODE && left == EXTERNAL_NODE) {
                if (parent == null)
                    return NodeTypes.ROOT_LEAF;
                if (parent.right == this)
                    return NodeTypes.RIGHT_LEAF;
                return NodeTypes.LEFT_LEAF;
            }

            if (right == EXTERNAL_NODE && left != EXTERNAL_NODE)
                return NodeTypes.LEFT_UNARY;
            if (right != EXTERNAL_NODE && left == EXTERNAL_NODE)
                return NodeTypes.RIGHT_UNARY;
            return NodeTypes.INTERNAL;
        }

        public int getRank() {
            return rank;
        }
    }

    // use generic Pair class as a Tuple, to encapsulate tree insertion/deletion
    // point
    // (the node parent node of affected node as first element of the Pair and
    // the chile sed as the second element)
    public class Pair<L, R> {

        private final L left;
        private final R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public R getRight() {
            return right;
        }

        @Override
        public int hashCode() {
            return left.hashCode() ^ right.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair))
                return false;
            Pair pairo = (Pair) o;
            return this.left.equals(pairo.getLeft())
                    && this.right.equals(pairo.getRight());
        }

        /*
         * the function compares the pair to another pair. if 'neglectOrder' is
         * true, flipped order pair will be considered equal to current pair
         */
        public boolean specializedEquals(Pair p, boolean neglectedOrder) {
            if (this.left.equals(p.getLeft())
                    && this.right.equals(p.getRight())) {
                return true;
            }
            if (neglectedOrder) {
                return (this.left.equals(p.getRight()) && this.right.equals(p
                        .getLeft()));
            }
            return false;
        }
    }

}
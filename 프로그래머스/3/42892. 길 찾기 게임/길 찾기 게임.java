import java.util.Arrays;
class Solution {
    int[][] answer;
    int idx = 0;
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        Node[] nodes = new Node[nodeinfo.length];
        for(int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }

        Arrays.sort(nodes, (o1, o2) -> o2.y - o1.y);

        Node root = nodes[0];
        for(int i = 1; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }

        preorder(root);
        idx = 0;
        postorder(root);

        return answer;
    }

    private void insert(Node root, Node node) {
        if(root.x > node.x) {
            if(root.left == null) {
                root.left = node;
                return;
            }
            insert(root.left, node);
            return;
        }
        if(root.right == null) {
            root.right = node;
            return;
        }
        insert(root.right, node);
    }

    private void preorder(Node root) {
        if(root == null) return;
        answer[0][idx++] = root.val;
        preorder(root.left);
        preorder(root.right);
    }

    private void postorder(Node root) {
        if(root == null) return;
        postorder(root.left);
        postorder(root.right);
        answer[1][idx++] = root.val;
    }

    static class Node {
        int x, y, val;
        Node left, right;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
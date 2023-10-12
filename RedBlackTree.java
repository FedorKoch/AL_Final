class RedBlackTree {
     Node root;


     void insert(int key, String value) {
          Node newNode = new Node();
          newNode.key = key;
          newNode.value = value;
          newNode.isRed = true;
          if (root == null) {
               root = newNode;
          } else {
               Node current = root;
               Node parent;
               while (true) {
                    parent = current;
                    if (key < current.key) {
                         current = current.left;
                         if (current == null) {
                              parent.left = newNode;
                              newNode.parent = parent;
                              break;
                         }
                    } else {
                         current = current.right;
                         if (current == null) {
                              parent.right = newNode;
                              newNode.parent = parent;
                              break;
                         }
                    }
               }
          }
          balance(newNode);
     }
     void balance(Node newNode) {
          while (newNode.parent != null && newNode.parent.isRed) {
               if (newNode.parent == newNode.parent.parent.left) {
                    Node uncle = newNode.parent.parent.right;
                    if (uncle != null && uncle.isRed) {
                         newNode.parent.isRed = false;
                         uncle.isRed = false;
                         newNode.parent.parent.isRed = true;
                         newNode = newNode.parent.parent;
                    } else {
                         if (newNode == newNode.parent.right) {
                              newNode = newNode.parent;
                              rotateLeft(newNode);
                         }
                         newNode.parent.isRed = false;
                         newNode.parent.parent.isRed = true;
                         rotateRight(newNode.parent.parent);
                    }
               } else {
                    Node uncle = newNode.parent.parent.left;
                    if (uncle != null && uncle.isRed) {
                         newNode.parent.isRed = false;
                         uncle.isRed = false;
                         newNode.parent.parent.isRed = true;
                         newNode = newNode.parent.parent;
                    } else {
                         if (newNode == newNode.parent.left) {
                              newNode = newNode.parent;
                              rotateRight(newNode);
                         }
                         newNode.parent.isRed = false;
                         newNode.parent.parent.isRed = true;
                         rotateLeft(newNode.parent.parent);
                    }
               }
          }
          root.isRed = false;
     }
     void rotateLeft(Node node) {
          Node temp = node.right;
          node.right = temp.left;
          if (temp.left != null) {
               temp.left.parent = node;
          }
          temp.parent = node.parent;
          if (node.parent == null) {
               root = temp;
          } else if (node == node.parent.left) {
               node.parent.left = temp;
          } else {
               node.parent.right = temp;
          }
          temp.left = node;
          node.parent = temp;
     }
     void rotateRight(Node node) {
          Node temp = node.left;
          node.left = temp.right;
          if (temp.right != null) {
               temp.right.parent = node;
          }
          temp.parent = node.parent;
          if (node.parent == null) {
               root = temp;
          } else if (node == node.parent.right) {
               node.parent.right = temp;
          } else {
               node.parent.left = temp;
          }
          temp.right = node;
          node.parent = temp;
     }
     void print(Node node, String indent) {
          if (node != null) {
               String color = node.isRed ? "Red" : "Black";
               System.out.println(indent + node.key + " " + node.value + " " + color);
               print(node.left, indent + " ");
               print(node.right, indent + " ");
          }
     }
     void printTree() {
          print(root, "");
     }
}
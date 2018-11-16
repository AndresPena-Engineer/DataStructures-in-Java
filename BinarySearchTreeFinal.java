/*
 * Java Program to Implement Binary Search Tree
 * ANDRES PENA
 * 10/2/2017
 * Lab 1
 */
 
 import java.util.Scanner;
 import java.util.ArrayList;
 import java.util.Iterator;
 
 /* Class Binary Search Tree Leafs */
 class BinarySearchTreeLeafs
 {
     BinarySearchTreeLeafs left, right;
     int data;
 
     /* Constructor */
     public BinarySearchTreeLeafs()
     {
         left = null;
         right = null;
         data = 0;
     }
     /* Constructor */
     public BinarySearchTreeLeafs(int n)
     {
         left = null;
         right = null;
         data = n;
     }
     /* Function to set left node */
     public void setLeft(BinarySearchTreeLeafs n)
     {
         left = n;
     }
     /* Function to set right node */ 
     public void setRight(BinarySearchTreeLeafs n)
     {
         right = n;
     }
     /* Function to get left node */
     public BinarySearchTreeLeafs getLeft()
     {
         return left;
     }
     /* Function to get right node */
     public BinarySearchTreeLeafs getRight()
     {
         return right;
     }
     /* Function to set data to leafs */
     public void setData(int d)
     {
         data = d;
     }
     /* Function to get data from leafs */
     public int getData()
     {
         return data;
     }     
 }
 
 /* Class Binary Search Tree */
 class BinarySearchTree
 {
     private BinarySearchTreeLeafs root;
 
     /* Constructor */
     public BinarySearchTree()
     {
         root = null;
     }
     /* Function to check if tree is empty */
     public boolean isEmpty()
     {
         return root == null;
     }
     /* Functions to insert data */
     public void insert(int data)
     {
         root = insert(root, data);
     }
     /* Function to insert data recursively */
     private BinarySearchTreeLeafs insert(BinarySearchTreeLeafs leaf, int data)
     {
         if (leaf == null)
             leaf = new BinarySearchTreeLeafs(data);
         else
         {
             if (data <= leaf.getData())
                 leaf.left = insert(leaf.left, data);
             else
                 leaf.right = insert(leaf.right, data);
         }
         return leaf;
     }
     /* Functions to delete data */
     public void delete(int k)
     {
         if (isEmpty())
             System.out.println("Tree Empty");
         else if (search(k) == false)
             System.out.println("Sorry "+ k +" is not in the tree");
         else
         {
             root = delete(root, k);
             System.out.println(k+ " it has been deleted from the tree");
         }
     }
     private BinarySearchTreeLeafs delete(BinarySearchTreeLeafs root, int k)
     {
         BinarySearchTreeLeafs p, p2, n;
         if (root.getData() == k)
         {
             BinarySearchTreeLeafs lt, rt;
             lt = root.getLeft();
             rt = root.getRight();
             if (lt == null && rt == null)
                 return null;
             else if (lt == null)
             {
                 p = rt;
                 return p;
             }
             else if (rt == null)
             {
                 p = lt;
                 return p;
             }
             else
             {
                 p2 = rt;
                 p = rt;
                 while (p.getLeft() != null)
                     p = p.getLeft();
                 p.setLeft(lt);
                 return p2;
             }
         }
         if (k < root.getData())
         {
             n = delete(root.getLeft(), k);
             root.setLeft(n);
         }
         else
         {
             n = delete(root.getRight(), k);
             root.setRight(n);             
         }
         return root;
     }
     /* Functions to count number of leafs */
     public int countLeafs()
     {
         return countLeafs(root);
     }
     /* Function to count number of leafs recursively */
     private int countLeafs(BinarySearchTreeLeafs r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countLeafs(r.getLeft());
             l += countLeafs(r.getRight());
             return l;
         }
     }
     //FROM HERE TO 
     /* Functions to count number of leafs */
     public int heightOfBinaryTree()
     {
         return heightOfBinaryTree(root);
     }
     /* Function to count the height of the tree recursively */
     public int heightOfBinaryTree(BinarySearchTreeLeafs root)
{
    if (root == null)
    {
        return -1;
    }
    else
    {
        return 1 +
        Math.max(heightOfBinaryTree(root.left),
            heightOfBinaryTree(root.right));
    }
}// HERE IS THE CODE I FOUND ONLINE
     
     /* Functions to search for an element */
     public boolean search(int val)
     {
         return search(root, val);
     }
     /* Function to search for an element recursively */
     private boolean search(BinarySearchTreeLeafs r, int val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.getData();
             if (val < rval)
                 r = r.getLeft();
             else if (val > rval)
                 r = r.getRight();
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
     /* Function for inorder traversal */
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(BinarySearchTreeLeafs r)
     {
         if (r != null)
         {
             inorder(r.getLeft());
             System.out.print(r.getData() +" ");
             inorder(r.getRight());
         }
     }
     /* Function for preorder traversal */
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(BinarySearchTreeLeafs r)
     {
         if (r != null)
         {
             System.out.print(r.getData() +" ");
             preorder(r.getLeft());             
             preorder(r.getRight());
         }
     }
     /* Function for postorder traversal */
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(BinarySearchTreeLeafs r)
     {
         if (r != null)
         {
             postorder(r.getLeft());             
             postorder(r.getRight());
             System.out.print(r.getData() +" ");
         }
     }     
 }
 
 /* Class BinarySearchTree */
 public class BinarySearchTreeFinal
 {
     public static void main(String[] args)
    {                 
        Scanner scan = new Scanner(System.in);
        /* Creating object of Binary Search Tree */
        BinarySearchTree bst = new BinarySearchTree();           
        char ch;
        
        /* Iterator to throw my name*/
         ArrayList names;
         names = new ArrayList();
         names.add("ANDRES PENA");
         names.add("LAB 1");
         names.add("CS 302");
 
    Iterator it = names.iterator();
 
    while(it.hasNext()) {
      String obj = (String)it.next();
      System.out.println(obj);
    }
      /*  Perform tree operations  */
        do    
        {
            System.out.println("\nBinary Search Tree Operations\n");
            System.out.println("1. Insert a leaf number ");
            System.out.println("2. Delete a leaf");
            System.out.println("3. Search for a number");
            System.out.println("4. Number of leafs");
            System.out.println("5. Calculate the height"); // It is not going to work because I dont have the code for the height figured out
            System.out.println("6. Check if its empty\n"); 
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter integer element to insert");
                bst.insert( scan.nextInt() );                     
                break;                          
            case 2 : 
                System.out.println("Enter integer element to delete");
                bst.delete( scan.nextInt() );                     
                break;                         
            case 3 : 
                System.out.println("Enter integer element to search");
                System.out.println("Search result : "+ bst.search( scan.nextInt() ));
                break;                                          
            case 4 : 
                System.out.println("Leafs = "+ bst.countLeafs());
                break; 
            case 5 : 
                System.out.println("Height = "+ bst.heightOfBinaryTree());
                break;
            case 6 :  
                System.out.println("Empty status = "+ bst.isEmpty());
                break;            
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            /*  Display tree  */ 
            System.out.print("\nPost order : ");
            bst.postorder();
            System.out.print("\nPre order : ");
            bst.preorder();
            System.out.print("\nIn order : ");
            bst.inorder();
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');               
    }
 } 
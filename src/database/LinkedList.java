package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class LinkedList {
	private int N;
	private int NumberColumns;
	private Node[] min;
	private Node[] max;
	private static Datebase base = new Datebase("monitors");
	private static String[] fields = {"model", "weight", "size", "resolution"};
		
	private static class Node {
		private long id;
		//private String column;
		private Node left;
		private Node right;
	}
	

	
	public LinkedList(Path file){
		Charset charset = Charset.forName("US-ASCII");
		
		try {
			BufferedReader in = Files.newBufferedReader(file, charset);
			N = Integer.parseInt(in.readLine());
			NumberColumns = Integer.parseInt(in.readLine());
			min = new Node[NumberColumns];
			max = new Node[NumberColumns];
			
			for (int i = 0; i < NumberColumns; i++) {
				min[i] = new Node();
				max[i] = new Node();
				min[i].left = null;
				max[i].right = null;
 
			}
			
			for (int i = 0; i < NumberColumns; i++) {
				min[i].id = Long.parseLong(in.readLine());
				Node temp = new Node();
				min[i].right = temp;
				temp.left = min[i];
								
				//min[i].right = temp;
				
				for (int j = 0; j < N-1; j++) {
					temp.id = Long.parseLong(in.readLine());
					Node next = new Node();
					temp.right = next;
					next.left = temp;
					temp = next;
				
				}
				temp = temp.left;
				max[i] = temp;
				max[i].right = null;
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public long getMax (String field) {
		int ColumnNumber = 0;
		for (int i = 0; i < NumberColumns; i++)
			if (field.equals(fields[i])) { ColumnNumber = i; break;}
		return max[ColumnNumber].id;
		
	}
	
	public long getMin (String field) {
		int ColumnNumber = 0;
		for (int i = 0; i < NumberColumns; i++)
			if (field.equals(fields[i])) { ColumnNumber = i; break;}
		return min[ColumnNumber].id;
		
	}
	private boolean lessThan (Node n1, Node n2, String field, int ColumnNumber) {
		if (base.getElement(field, n1.id) == null || base.getElement(field, n1.id) == "") return true;
		if (ColumnNumber != 0)
			if (Integer.parseInt(base.getElement(field, n1.id)) <= Integer.parseInt(base.getElement(field, n2.id)))
				return true;
		if (ColumnNumber == 0)
			if (base.getElement(field, n1.id).compareTo(base.getElement(field, n2.id)) <= 0)
				return true;
		return false;
	}
	
	private int lessThan (String value, Node n2, String field, int ColumnNumber) {
		if (base.getElement(field, n2.id) == null || base.getElement(field, n2.id) == "") return Integer.MAX_VALUE;
		if (ColumnNumber != 0)
			return Integer.parseInt(value) - Integer.parseInt(base.getElement(field, n2.id));
				
		if (ColumnNumber == 0)
			return value.compareTo(base.getElement(field, n2.id));
				
		return 0;
	}
	public void print() {
		for (int i = 0; i < NumberColumns; i++){
			Node temp = min[i];
			while (temp.right != null) {
				System.out.print(temp.id+" ");
				temp = temp.right;			
			} 
			System.out.print(temp.id+" ");
			System.out.println();
			assert (temp == max[i]);

		}
		
	}
	
	public long goNext (long id, String field) {
		int ColumnNumber = 0;
		for (int i = 0; i < NumberColumns; i++)
			if (field.equals(fields[i])) { ColumnNumber = i; break;}
		Node temp = min[ColumnNumber];
		while (temp.right != null) {
			if (id == temp.id)
				break;
			temp = temp.right;
		}
		
		if (temp.right == null) return -1;
		return temp.right.id;
	}
	public long goPrev (long id, String field){
		int ColumnNumber = 0;
		for (int i = 0; i < NumberColumns; i++)
			if (field.equals(fields[i])) { ColumnNumber = i; break;}
		Node temp = max[ColumnNumber];
		while (temp.left != null) {
			if (id == temp.id)
				break;
			temp = temp.left;
		}
		
		if (temp.left == null) return -1;
		return temp.left.id;
	}
		
	
	public long add(String field, String value) {

		long id = base.add(field, value);
		Node n = new Node();
		n.id = id;
		N++;
		Node temp;
		
		for (int i = 0; i < NumberColumns; i++) {
			temp = min[i];
			min[i] = new Node();
			min[i].id = id;
			temp.left = n;
			min[i].right = temp;
		}
						
		set(id, field, value);
		
		return id;
		

				
	}
		
	public void save () {
		Charset charset = Charset.forName("US-ASCII");
		Path path = Paths.get("LinkedList.txt"); 
		try (BufferedWriter out = Files.newBufferedWriter(path, charset)) {
			//PrintWriter pw = new PrintWriter("LinkedList.txt");
			//pw.close();
			
			out.write(Integer.toString(N));
			out.newLine();
			out.write(Integer.toString(NumberColumns));
			out.newLine();
			for (int i = 0; i < NumberColumns; i++){
				Node temp = min[i];
				while (temp.right != null) {
					out.write(Long.toString(temp.id));
					//System.out.println(temp.id);
					out.newLine();
					temp = temp.right;			
				} 
				
				out.write(Long.toString(temp.id));
				out.newLine();
				assert (temp == max[i]);

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	public void delete (long id) {
		N--;
		base.delete(id);
		for (int i = 0; i < NumberColumns; i++) {
			Node temp = min[i];
			if (temp.id == id) {
				min[i] = temp.right;
				min[i].left = null;
				continue;
			}
			while (temp.right != null) {
				if (temp.id == id) {
					temp.left.right = temp.right;
					temp.right.left = temp.left;
					break;
				}
				temp = temp.right;
			}
			
			if (max[i] == temp && temp.id == id) {
				max[i] = temp.left;
				max[i].right = null;
			}
			
		}
	}
	
	public long getMinID (String field) {
		int ColumnNumber = 0;
		for (int i = 0; i < NumberColumns; i++)
			if (field.equals(fields[i])) { ColumnNumber = i; break;}
		return min[ColumnNumber].id;
	}
	
	public long search (String field, String value) {
		long result = 0;
		int comparator;
		
		int ColumnNumber = 0;
		for (int i = 0; i < NumberColumns; i++)
			if (field.equals(fields[i])) { ColumnNumber = i; break;}
		Node temp = min[ColumnNumber];
		comparator = Math.abs(lessThan(value, temp, field, ColumnNumber));
		result = temp.id;
		do {
			if (Math.abs(lessThan(value, temp, field, ColumnNumber)) < Math.abs(comparator)) {
				comparator = Math.abs(lessThan(value, temp, field, ColumnNumber));
				result = temp.id;
			}
			if (comparator <= 0)
				return result;
						
			temp = temp.right;
		} while (temp != null);
		
		return result;
	}
	public void set (long id, String field, String value) {
		int ColumnNumber = 0;
		for (int i = 0; i < NumberColumns; i++)
			if (field.equals(fields[i])) { ColumnNumber = i; break;}
		
		base.change(id, field, value);
		
		Node n = new Node();
		n.id = id;
		
		Node temp = min[ColumnNumber];
		if (temp.id == id) {
			min[ColumnNumber] = temp.right;
			min[ColumnNumber].left = null;
			
		}
		else {
			temp = temp.right;
			while (temp.right != null){
				System.out.println(temp.id);
				if (temp.id == id) {
					temp.left.right = temp.right;
					temp.right.left = temp.left;
					break;
			}
				temp = temp.right;
			}
			if (temp.right == null) {
				System.out.println("m");
				assert(temp == max[ColumnNumber] && max[ColumnNumber].id == id);
				max[ColumnNumber] = temp.left;
				max[ColumnNumber].right = null;
			}
		}
		
				
		
			temp = min[ColumnNumber];
			//System.out.println(field);
			if (lessThan(n, temp, field, ColumnNumber))  {
				System.out.println("b");
				min[ColumnNumber] = n;
				n.left = null;
				n.right = temp;
				return;
			
			}
			while (temp.right != null)  {
				System.out.println("yes");
				temp = temp.right;
				if (lessThan(n, temp, field, ColumnNumber))  {
					System.out.println("m");
					n.right = temp;
					n.left = temp.left;
					temp.left.right = n;
					temp.left = n;
					return;
				} 				
		
			} 
			
			assert (temp == max[ColumnNumber]);
			n.left = temp;
			temp.right = n;
			n.right = null;
			max[ColumnNumber] = n;
			return;
			
	}
	
	

	public static void main(String[] args) {
		Path file = Paths.get("LinkedList.txt");
		LinkedList a = new LinkedList(file);
		a.print();
		//a.delete(92);
		//a.add("size", "80");
		//long id = a.add("size", "50");
		System.out.println(a.goNext(99, "model"));
		//a.set(89, "size", "null");
		//int a = null;
		a.print();
		//a.set(89, "model", "null");
		
		//a.set(12, "weight", "40");
		//a.set(92, "resolution", "7");
		//a.save();
		
		//a.set(92, "resolution", "20");
		//a.print();

		
		
		// TODO Auto-generated method stub

	}

}

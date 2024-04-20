

public class Split {
    
    public static void main(String[] args) {
    	// Sie koennen diese Methode aendern
    	
        SpecialLinkedIntList list = new SpecialLinkedIntList();
        
        list.addLast(1);
        list.addLast(5);
        list.addLast(4);
        list.addLast(7);
        list.addLast(2);
        list.addLast(5);
        
        SpecialLinkedIntList other = list.split(4);
        
        list.printList();
        other.printList();
        list.printOldList();
        other.printOldList();
    }
}

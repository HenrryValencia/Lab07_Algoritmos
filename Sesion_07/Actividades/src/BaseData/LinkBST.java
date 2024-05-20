package BaseData;

public interface LinkBST <E>{
	
	void insert(E x) throws Exception;
	void remove(E x) ;
	E search (E x) throws Exception;
	boolean isEmpty();

}

package model;


	import javax.persistence.EntityManagerFactory;
	import javax.persistence.Persistence;



	public class DBUtil {
		
	private static final EntityManagerFactory emf =
	Persistence.createEntityManagerFactory("People_Finder");


	 public static EntityManagerFactory getEmFactory()
	 {  
		 return emf; 
	} 
}

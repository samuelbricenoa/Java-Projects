package compositeclass;
public class compositeuml {

	public static void main(String[] args) {
		//initialize names 
		Names name1 = new Names("Gabriela", "Parra");
		Names name2 = new Names("Jackson", "Danny");
		//this initialization shows that code converts to sentence case.
		Names name3 = new Names("samuEl", "Andres", "brIceno");

		Person person1 = new Person(name1, 'F', 25, 55000.55);

		Person person2 = new Person(name2, 'M', 27, 2500);

		Person person3 = new Person(name3 , 'M', 20, 17000);
		
		System.out.println(person1);
		System.out.println(person2);
		System.out.println(person3);

		}
		

	}
	

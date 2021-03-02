package compositeclass;

public class Person {

//attributes for this class.
	private Names name;
	private char gender;
	private int age;
	private double salary;


//constructor taking in name, gender age and salary.
	public Person(Names name, char gender, int age, double salary) {

		setName(name);
		setGender(gender);
		setAge(age);
		setSalary(salary);

	}


//get method to get name.
	public Names getName() {

		return name;

	}

	public void setName(Names name) {

		this.name = name;

	}
	//get method to get age.
	public int getAge() {

		return age;

	}
//set method to set age.
	public void setAge(int age) {
		//if statement checks if age is between 1 and 120.
		if (age >= 1 && age <= 120)

			this.age = age;

	}
	//get method to get salary.
	public double getSalary() {

		return salary;

	}
	//set method to set salary
	public void setSalary(double salary) {
		//if statement to ensure salary is non negative.
		if (salary >= 0)

			this.salary = salary;

	}
	//get method to get gender.
	public char getGender() {

		return gender;

	}
	//set method to set gender.
	public void setGender(char gender) {
		//if statement to make sure gender choices are M F or O.
		if (gender == 'M' || gender == 'F' || gender == 'O')

			this.gender = gender;

	}

//tostring method to print out name.
	public String toString() {

		// returns a String to represent the whole data of a person

		return "Name:" + name + "   Gender: " + gender + "   Age: " + age + "   Salary: $" + salary;

	}

}

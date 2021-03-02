package compositeclass;

public class Names {
	//attributes for class.
    private String firstName;
    private String middleName;
    private String lastName;


//constructor incase of name with middle name.
    public Names(String firstName, String middleName, String lastName) {

    	setFirstName(firstName);
    	setMiddleName(middleName);	
    	setLastName(lastName);

    }


//constructor incase of name with no middle name.
	public Names(String firstName, String lastName) {
		//replace middle name attribute with empty string in case of no middle name.
		setFirstName(firstName);
		setMiddleName("");
		setLastName(lastName);

	}


//get method of first name.
    public String getFirstName() {

    	return firstName;

    }
//set method of first name.
    public void setFirstName(String firstName) {
    	//retrieve first letter of first name.
    	char c = firstName.charAt(0);
    	//convert first letter to upper case.
    	c = Character.toUpperCase(c);
    	//convert rest of string to lower case.
    	firstName = firstName.toLowerCase().substring(1);
    	//return sentence case name.
    	this.firstName = c + firstName;

                    

    }
  //get method of middle name
    public String getMiddleName() {

    	return middleName;

    }
  //set method of middle name.
    public void setMiddleName(String middleName) {

    	this.middleName = middleName;

    }
  //get method of last name.
    public String getLastName() {

    	return lastName;

    }
  //set method of last name.
    public void setLastName(String lastName) {
		//retrieve first letter of last name
    	char c = lastName.charAt(0);
    	//convert first letter to upper case.
    	c = Character.toUpperCase(c);
    	//converts rest of last name to lower case for sentence case.
    	lastName = lastName.toLowerCase().substring(1);

    	this.lastName = c + lastName;


    }
//to string method to print out name.
    public String toString() {
    	
    	if (middleName != "")

    		return firstName + " " + middleName + " " + lastName;

    	else

    		return firstName + " " + lastName;

    }

}

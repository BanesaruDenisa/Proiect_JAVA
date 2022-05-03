package Person;

public class Person implements Comparable<Person> {

    protected String firstname;
    protected String lastname;
    protected String phoneNumber;

    public Person (){

    }

    public Person(String firstname, String lastname, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }
    public Person(Person p) {
        this.firstname = p.firstname;
        this.lastname = p.lastname;
        this.phoneNumber = p.phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phonenumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        String fullname1 = this.firstname + this.lastname;
        String fullname2 = o.firstname + o.lastname;

        return fullname1.compareTo(fullname2);
    }
}

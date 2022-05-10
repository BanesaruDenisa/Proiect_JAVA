package order;

import person.Person;

public class Client extends Person {

    private String email;
    private String address;

    public Client()
    {}

    public Client(String firstname, String lastname, String phoneNumber, String email, String address) {
        super(firstname, lastname, phoneNumber);
        this.email = email;
        this.address = address;
    }
    public Client(Client cl){
        super(cl);
        this.email = cl.email;
        this.address = cl.address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                " firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}

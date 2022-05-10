package courier;

import person.Person;

public class Courier extends Person {

    private int idCourier = 0;

    public Courier() {

    }

    public Courier(String firstname, String lastname, String phoneNumber, int idCourier) {
        super(firstname, lastname, phoneNumber);
        this.idCourier = idCourier;
    }

    public Courier(Courier c, Integer idCourier) {
        super(c);
        this.idCourier = idCourier;
    }

    public Courier(Courier courier)  {
        super(courier);
        this.idCourier = courier.idCourier;

    }



    public Integer getIdCourier() {
        return idCourier;
    }

    public void setIdCourier(Integer idCourier) {
        this.idCourier = idCourier;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "idCourier=" + idCourier +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

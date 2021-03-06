package order;

public class Order {

    private static int nrOrder = 0;
    private Integer idOrder;
    private Integer idBill;
    private Integer idDelComp;
    private Client client;



    public Order(Integer idOrder, Integer idBill, Integer idDelComp, Client client) {
        nrOrder++;
        this.idOrder = idOrder;
        this.idBill = idBill;
        this.idDelComp = idDelComp;
        this.client = client;
    }
    public Order(){
        nrOrder++;
        this.idOrder = nrOrder;
    }

    public Order(Order order) {
        nrOrder++;
        this.idOrder = order.idOrder;
        this.idBill = order.idBill;
        this.idDelComp = order.idDelComp;
        this.client = order.client;
    }

    public Order(int idbill, int idDelc, Client cl) {
        this.idBill = idBill;
        this.idDelComp = idDelComp;
        this.client = cl;
    }

    public Order(int id_o, int id_b, int id_d, String email) {
    }

    public void setIdOrder(Integer idOrd){
        this.idOrder = idOrd;
    }
    public Integer getIdOrder(){
        return idOrder;
    }
    public void setClient(Client client){
        this.client = client;
    }
    public Client getClient(){
        return client;
    }
    public void setIdBill(Integer idBill){
        this.idBill = idBill;
    }
    public Integer getIdBill(){
        return idBill;
    }
    public void setIdDelComp(Integer idDelComp){
        this.idDelComp = idDelComp;
    }
    public Integer getIdDelComp(){
        return idDelComp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idBill=" + idBill +
                ", idDelComp=" + idDelComp +
                ", client='" + client + '\'' +
                '}';
    }

}

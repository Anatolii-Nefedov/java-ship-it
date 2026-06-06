import org.w3c.dom.ls.LSOutput;
import java.util.Scanner;

public  abstract class Parcel {
    Scanner scanner = new Scanner(System.in);
    private String description;
    private Integer weight;
    private String deliveryAddress;
    private int sendDay;

    public Parcel(String description, Integer weight, String deliveryAddress, Integer sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public String getDescription() {
        return description;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    public void setSendDay(int sendDay) {
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> упакована.");
    }

    public void deliver() {
        System.out.println("Посылка <<" + description + ">> доставлена по адресу " + deliveryAddress);
    }

    protected abstract int getBaseCost();

    public Integer calculateDeliveryCost() {
        if (weight == null || weight <= 0) {
            return 0; // или выбросить исключение
        }
        return weight * getBaseCost();
    }
}



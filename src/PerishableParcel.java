public class PerishableParcel extends Parcel {
    private int timeToLive;
    private static final int BASE_COST = 3;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay,
                            int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }

    public boolean isExpired(int currentDay) {
        return currentDay > (this.sendDay + this.timeToLive);
    }
}

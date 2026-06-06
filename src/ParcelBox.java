import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private final List<T> parcels;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
        this.currentWeight = 0;
    }
    public void addParcel(T parcel) {
        int parcelWeight = parcel.getWeight();
        if (currentWeight + parcelWeight > maxWeight) {
            System.out.println("Превышен максимальный вес коробки! Посылка <<" +
                    parcel.getDescription() + ">> не добавлена.");
            return;
        }
        parcels.add(parcel);
        currentWeight += parcelWeight;
        System.out.println("Посылка <<" + parcel.getDescription() + ">> добавлена в коробку.");
    }

    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int size() {
        return parcels.size();
    }

    public boolean isEmpty() {
        return parcels.isEmpty();
    }

    public void showContents() {
        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.");
            return;
        }
        System.out.println("Содержимое коробки (макс. вес коробки" + maxWeight + " кг, загружено " + currentWeight +
                " кг):");
        for (T p : parcels) {
            System.out.println("  - " + p.getDescription() + " (вес: " + p.getWeight() + " кг)");
        }
    }
}
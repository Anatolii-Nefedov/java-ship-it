import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Parcel> parcelsOnTracking = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(125);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(75);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(50);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels(allParcels);
                    break;
                case 3:
                    calculateCosts(allParcels);
                    break;
                case 4:
                    trackParcels();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Уточнить статус");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Укажите тип посылки: Стандарт, Хрупкая, Экспресс");
        String parcelType = scanner.nextLine();

        if (!parcelType.equals("Стандарт") && !parcelType.equals("Хрупкая") && !parcelType.equals("Экспресс")) {
            System.out.println("Неизвестный тип посылки");
            return;
        }

        System.out.println("Что отправляете?");
        String description = scanner.nextLine();

        System.out.println("Укажите вес посылки в кг.");
        Integer weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Напишите адрес доставки");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Какого числа отправить посылку?");
        Integer sendDay = Integer.parseInt(scanner.nextLine());

        if (parcelType.equals("Стандарт")) {
            StandardParcel parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
            allParcels.add(parcel);
            standardBox.addParcel(parcel);
        } else if (parcelType.equals("Хрупкая")) {
            FragileParcel parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
            allParcels.add(parcel);
            fragileBox.addParcel(parcel);
            parcelsOnTracking.add(parcel);
        } else if (parcelType.equals("Экспресс")) {
            System.out.println("Укажите срок годности посылки (в днях):");
            Integer timeToLive = Integer.parseInt(scanner.nextLine());
            System.out.println("Укажите сегодняшнее число:");
            int currentDay = Integer.parseInt(scanner.nextLine());

            PerishableParcel parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);

            if (parcel.isExpired(currentDay)) {
                System.out.println("Извините, не успеем доставить, товар испортится.");
                return;
            }
            allParcels.add(parcel);
            perishableBox.addParcel(parcel);
        }
    }

    private static  void sendParcels(List<Parcel> allParcels) {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static  Integer calculateCosts(List<Parcel> allParcels) {
        int totalSum = 0;
        for (Parcel parcel : allParcels) {
            totalSum += parcel.calculateDeliveryCost();
        }
        System.out.println("Всего посылок на сумму: " + totalSum + " USD");
        return totalSum;
    }

    private static void trackParcels() {
        if (parcelsOnTracking.isEmpty()) {
            System.out.println("Нет отслеживаемых посылок.");
            return;
        }
        System.out.println("Введите новое местоположение:");
        String newLocation = scanner.nextLine();

        for (Parcel p : parcelsOnTracking) {
            if (p instanceof Trackable) {
                ((Trackable) p).reportStatus(newLocation);
            }
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите тип коробки:");
        System.out.println("1 — Стандартные посылки");
        System.out.println("2 — Хрупкие посылки");
        System.out.println("3 — Экспресс посылки");
        int type = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1:
                standardBox.showContents();
                break;
            case 2:
                fragileBox.showContents();
                break;
            case 3:
                perishableBox.showContents();
                break;
            default:
                System.out.println("Неизвестная команда!");
        }
    }
}

//Извини, сначала залил не в тот репозиторий код !
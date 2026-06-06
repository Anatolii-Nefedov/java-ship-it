import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryAppTest {

    @Test
    void testStandardParcelCost() {
        StandardParcel parcel = new StandardParcel("Книга", 5, "Москва", 10);
        assertEquals(10, parcel.calculateDeliveryCost());
    }

    @Test
    void testFragileParcelCost() {
        FragileParcel parcel = new FragileParcel("Ваза", 4, "Воронеж", 12);
        assertEquals(16, parcel.calculateDeliveryCost());
    }

    @Test
    void testPerishableParcelCost() {
        PerishableParcel parcel = new PerishableParcel("Торт", 2, "Уфа", 5,
                3);
        assertEquals(6, parcel.calculateDeliveryCost());
    }

    @Test
    void testZeroWeightCost() {
        StandardParcel parcel = new StandardParcel("Пусто", 0, "Адрес", 1);
        assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    void testIsExpired_NotExpired() {
        assertFalse(PerishableParcel.isExpired(10, 5, 14));
    }

    @Test
    void testIsExpired_Expired() {
        assertTrue(PerishableParcel.isExpired(10, 5, 16));
    }

    @Test
    void testIsExpired_LastDay() {
        assertFalse(PerishableParcel.isExpired(10, 5, 15));
    }

    @Test
    void testAddParcel_WithinWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel p = new StandardParcel("Книги", 30, "Адрес", 1);
        box.addParcel(p);
        assertEquals(1, box.size());
        assertEquals(30, box.getCurrentWeight());
    }

    @Test
    void testAddParcel_ExceedsWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(50);
        StandardParcel p1 = new StandardParcel("Книги", 40, "Адрес", 1);
        StandardParcel p2 = new StandardParcel("Одежда", 20, "Адрес", 2);
        box.addParcel(p1);
        box.addParcel(p2); // 40+20=60 > 50, не добавится
        assertEquals(1, box.size());
        assertEquals(40, box.getCurrentWeight());
    }

    @Test
    void testAddParcel_ExactWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel p = new StandardParcel("Груз", 100, "Адрес", 1);
        box.addParcel(p);
        assertEquals(1, box.size());
        assertEquals(100, box.getCurrentWeight());
    }
}
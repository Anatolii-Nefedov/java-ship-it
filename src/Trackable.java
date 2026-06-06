public interface Trackable {

    void reportStatus(String newLocation);
    //не использовал default т.к. реализация только для хрупких посылок пока
}

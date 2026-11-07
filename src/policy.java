public class policy {

    String polNo;
    String cusId;
    String veId;
    String startDate;
    String endDate;
    int cAmount;
    enum status{
        ACTIVE,
        EXPIRED,
        CANCELLED
    };
}

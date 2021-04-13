package by.epam.lukyanau.rentService.service.util.comparator;

import by.epam.lukyanau.rentService.entity.Order;

import java.util.Comparator;

public enum OrderingComparator {

    DATE_FROM((order1, order2) -> {
        if (order1.getArrivalDate().before(order2.getArrivalDate())) {
            return 1;
        } else if (order1.getArrivalDate().after(order2.getArrivalDate())) {
            return -1;
        } else {
            return 0;
        }
    });

    private final Comparator<Order> comparator;

    OrderingComparator(Comparator<Order> comparator) {
        this.comparator = comparator;
    }


    public Comparator<Order> getComparator() {
        return comparator;
    }
}

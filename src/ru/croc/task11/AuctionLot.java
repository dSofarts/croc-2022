package ru.croc.task11;

import java.time.LocalDateTime;

public class AuctionLot {

    private static final Object LOCK = new Object();

    private volatile int nowPrice;
    private volatile Buyer buyer;
    private final LocalDateTime END_OF_BIDDING;

    public AuctionLot(int nowPrice, LocalDateTime endOfBidding) {
        this.nowPrice = nowPrice;
        this.END_OF_BIDDING = endOfBidding;
    }

    public void placeBet(int nowPrice, Buyer buyer) {
        synchronized (LOCK) {
            if (nowPrice > this.nowPrice && END_OF_BIDDING.isAfter(LocalDateTime.now())) {
                this.nowPrice = nowPrice;
                this.buyer = buyer;
            } else {
                System.out.println("Ваша ставка меньше предыдущей или торги уже закончены");
            }
        }
    }

    public String getBuyerName() {
        if (LocalDateTime.now().isAfter(END_OF_BIDDING) && buyer != null) {
            return buyer.getName();
        }
        return "Торги еще идут!";
    }
}

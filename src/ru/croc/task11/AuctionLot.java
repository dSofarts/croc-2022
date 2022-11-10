package ru.croc.task11;

import java.time.LocalDateTime;

public class AuctionLot {

    private volatile int nowPrice;
    private volatile Buyer buyer;
    private LocalDateTime endOfBidding;

    public AuctionLot(int nowPrice, LocalDateTime endOfBidding) {
        this.nowPrice = nowPrice;
        this.endOfBidding = endOfBidding;
    }

    public void placeBet(int nowPrice, Buyer buyer) {
        if (nowPrice > this.nowPrice && endOfBidding.isAfter(LocalDateTime.now())) {
            this.nowPrice = nowPrice;
            this.buyer = buyer;
        } else {
            System.out.println("Ваша ставка меньше предыдущей или торги уже закончены");
        }
    }

    public String getBuyerName() {
        if (buyer != null) {
            return buyer.getName();
        }
        return "Покупателя еще нет!";
    }
}

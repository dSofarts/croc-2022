package ru.croc.task11;

import java.time.LocalDateTime;

public class Task11 {

    public static void main(String[] args) {

        Buyer buyer1 = new Buyer("Sergey");
        Buyer buyer2 = new Buyer("Michael");

        LocalDateTime localDateTime = LocalDateTime.of(2022, 12, 31, 23, 59);
        AuctionLot auctionLot = new AuctionLot(12, localDateTime);

        auctionLot.placeBet(13, buyer1);
        System.out.println(auctionLot.getBuyerName());
        auctionLot.placeBet(14, buyer2);
        System.out.println(auctionLot.getBuyerName());

    }
}

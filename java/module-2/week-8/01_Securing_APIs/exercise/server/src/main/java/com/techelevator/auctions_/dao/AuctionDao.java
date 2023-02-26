package com.techelevator.auctions_.dao;

import com.techelevator.auctions_.model.Auction;

import java.util.List;

public interface AuctionDao {

    List<Auction> list();

    Auction get(int id);

    Auction create(Auction auction);

    List<Auction> searchByTitle(String title_like);

    List<Auction> searchByPrice(double currentBid_lte);

    Auction update(Auction auction, int id);

    void delete(int id);
}

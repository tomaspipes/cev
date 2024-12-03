package src.coinMarket;

import src.Game;

public class CoinMarket implements Game{

    public CoinMarket(){
        start();
    }

    @Override
    public void start(){
       System.out.println("CoinMarket"); 
    } 

}

package plays;

public class Play {
    protected static int calcExp(int min, int max) {
        int randExp = getRandomInteger((int) Math.pow(min, 2), (int) Math.pow(max, 2));
        int roundRand = (int) Math.ceil(Math.sqrt(randExp));
        int signedRound = roundRand * (Math.abs(max)/max);
        return (max + min) - signedRound;
    }
    protected static int getRandomInteger(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
}

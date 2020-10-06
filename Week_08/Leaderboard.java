package test20201006;

public class Leaderboard {
    public Leaderboard() {

    }
    private int[][] array = new int[12000][];
    int index = 0;
    public void addScore(int playerId, int score) {
        int[] arrT = new int[2];
        arrT[0] = playerId;
        arrT[1] = score;
        int pot = search(playerId);
        if(pot == -1)
            pot = index;
        insert(arrT,pot);
    }

    private void insert(int[] arrT,int pot) {
        int i = pot;
        if(pot != index){
            arrT[1] = array[pot][1]+ arrT[1];
        }
        if(index != 0){
            for (i = pot; i > 0 && arrT[1] > array[i-1][1]; i--) {
                array[i] = array[i-1];
            }
        }
        array[i] = arrT;
        if(pot == index){
            index++;
        }
    }

    public int top(int K) {
        int tempInd = K > index?index:K;
        int sum = 0;
        for(int i= 0;i < tempInd;i++){
            sum += array[i][1];
        }

        return sum;
    }

    public void reset(int playerId) {
        int ret = search(playerId);
        int[] orign = array[ret];
        orign[1] = 0;
        for(int i = ret+1;i<index;i++){
            array[i-1] = array[i];
        }
        array[index-1] = orign;
    }
    public int search(int playerId){
        for(int i = 0; i < index;i++){
            if(array[i][0] == playerId) return i;
        }
        return -1;
    }
}

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculated = StringUtil.applySha256(
                previousHash +
                        Long.toBinaryString(timeStamp) +
                        Integer.toString(nonce) +
                        data);
        return calculated;
    }

    public void mineBlock(int difficulty) {
        String target = StringUtil.getDifficulty(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
    }
}

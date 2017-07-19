package combinatorial.optimizer;

public class Bit extends Gene {
    public byte bitValue;

    public Bit() {
        bitValue = (byte)Math.round(Math.random());
    }

    public Bit(String character) {
        bitValue = Byte.parseByte(character);
    }

    public void mutate() {
        bitValue = (byte)Math.round(Math.random());
    }
}

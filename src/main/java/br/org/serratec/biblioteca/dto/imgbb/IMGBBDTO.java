package br.org.serratec.biblioteca.dto.imgbb;

public class IMGBBDTO {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IMGBBDTO [data=" + data + "]";
    }
}

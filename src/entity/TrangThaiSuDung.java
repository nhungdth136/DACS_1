
package entity;


public enum TrangThaiSuDung {
    AVAILABLE(1),
    UNAVAILABLE(2),
    EXPIRED(3);

    private final int tenTrangThaiSuDung;

    private TrangThaiSuDung(int tenTrangThaiSuDung) {
        this.tenTrangThaiSuDung = tenTrangThaiSuDung;
    }

    public int getTentrangThaiSuDung() {
        return tenTrangThaiSuDung;
    }
}
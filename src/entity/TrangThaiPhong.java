
package entity;


public enum TrangThaiPhong {
    BOOKED(1),
    OCCUPIED(2),
    AVAILABLE(3),
    UNAVAILABLE(4);

    private final int tenTrangThai;

    private TrangThaiPhong(int tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public int getTenTrangThai() {
        return tenTrangThai;
    }
    public static TrangThaiPhong fromInt(int value) {
        for (TrangThaiPhong trangThai : TrangThaiPhong.values()) {
            if (trangThai.getTenTrangThai() == value) {
                return trangThai;
            }
        }
        throw new IllegalArgumentException("Giá trị không hợp lệ: " + value);
    }
    

    
    public static TrangThaiPhong fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Giá trị không hợp lệ: " + value);
        }
        try {
            return TrangThaiPhong.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Giá trị không hợp lệ: " + value);
        }
    }
}
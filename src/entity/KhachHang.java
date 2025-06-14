
package entity;

import java.time.LocalDate;
import java.util.Objects;


public class KhachHang {

    private String maKhachHang;
    private String hoTenKhachHang;
    private String emai;
    private boolean gioiTinh;
    private String CCCD;
    private LocalDate ngaySinh;
    private boolean trangThaiKhachHang;

    public KhachHang(String maKhachHang, String hoTenKhachHang, String emai, boolean gioiTinh, String CCCD, LocalDate ngaySinh, boolean trangThaiKhachHang) {
        this.maKhachHang = maKhachHang;
        this.hoTenKhachHang = hoTenKhachHang;
        this.emai = emai;
        this.gioiTinh = gioiTinh;
        this.CCCD = CCCD;
        this.ngaySinh = ngaySinh;
        this.trangThaiKhachHang = trangThaiKhachHang;
    }
    
    public KhachHang(String maKhachHang, String hoTenKhachHang, boolean gioiTinh, String CCCD, LocalDate ngaySinh) {
        this.maKhachHang = maKhachHang;
        this.hoTenKhachHang = hoTenKhachHang;
        this.gioiTinh = gioiTinh;
        this.CCCD = CCCD;
        this.ngaySinh = ngaySinh;
    }
    
    
    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public KhachHang() {
    }

    public KhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTenKhachHang() {
        return hoTenKhachHang;
    }

    public void setHoTenKhachHang(String hoTenKhachHang) {
        this.hoTenKhachHang = hoTenKhachHang;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isTrangThaiKhachHang() {
        return trangThaiKhachHang;
    }

    public void setTrangThaiKhachHang(boolean trangThaiKhachHang) {
        this.trangThaiKhachHang = trangThaiKhachHang;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKhachHang=" + maKhachHang + ", hoTenKhachHang=" + hoTenKhachHang + ", emai=" + emai + ", gioiTinh=" + gioiTinh + ", CCCD=" + CCCD + ", ngaySinh=" + ngaySinh + ", trangThaiKhachHang=" + trangThaiKhachHang + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.maKhachHang);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final KhachHang other = (KhachHang) obj;
        return Objects.equals(this.maKhachHang, other.maKhachHang);
    }

}

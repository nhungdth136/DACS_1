
package entity;


public class TaiKhoan {

    private NhanVien nhanVien;
    private String matKhau;

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public TaiKhoan(NhanVien nhanVien, String matKhau) {
        this.nhanVien = nhanVien;
        this.matKhau = matKhau;
    }

    public TaiKhoan(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    
    
    @Override
    public String toString() {
        return "TaiKhoan{" + "nhanVien=" + nhanVien + ", matKhau=" + matKhau + '}';
    }
    
}
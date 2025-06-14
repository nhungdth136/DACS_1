package entity;

import java.time.LocalDate;
import java.util.Objects;


public class KhuyenMai {

    private String maKhuyenMai;
    private boolean trangThaiKhuyenMai;
    private double giaTri;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String noiDung;

    public KhuyenMai(String maKhuyenMai,boolean trangThaiKhuyenMai, double giaTri, LocalDate ngayBatDau, LocalDate ngayKetThuc, String noiDung) {
        this.maKhuyenMai = maKhuyenMai;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.noiDung = noiDung;
        this.trangThaiKhuyenMai = trangThaiKhuyenMai;
    }

    public KhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public KhuyenMai() {
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public boolean isTrangThaiKhuyenMai() {
        return trangThaiKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setTrangThaiKhuyenMai(boolean trangThaiKhuyenMai) {
        this.trangThaiKhuyenMai = trangThaiKhuyenMai;
    }

    public boolean isHetHan() {
        return LocalDate.now().isBefore(ngayKetThuc);
    }

    @Override
    public String toString() {
        return "KhuyenMai{" + "maKhuyenMai=" + maKhuyenMai + ", giaTri=" + giaTri + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", noiDung=" + noiDung + ", trangThaiKhuyenMai=" + trangThaiKhuyenMai + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.maKhuyenMai);
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
        final KhuyenMai other = (KhuyenMai) obj;
        return Objects.equals(this.maKhuyenMai, other.maKhuyenMai);
    }

}

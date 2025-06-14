
package dao;

import connectDB.ConnectDB;
import entity.NhanVien;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVien_DAO {

    /**
     *
     * @param getAllTableNhanVien
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<NhanVien> getAllTableNhanVien() throws SQLException {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM NhanVien";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String maNhanVien = rs.getString("maNhanVien");
            String hoTenNhanVien = rs.getString("hoTenNhanVien");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String diaChi = rs.getString("diaChi");
            boolean trangThaiLamViec = rs.getBoolean("trangThaiLamViec");
            String soDienThoai = rs.getString("soDienThoai");
            String hinhAnh = rs.getString("hinhAnh");
            boolean chucVu = rs.getBoolean("chucVu");

            NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, diaChi, trangThaiLamViec, soDienThoai, hinhAnh, chucVu);
            dsNhanVien.add(nhanVien);
        }

        return dsNhanVien;
    }

    /**
     *
     * @param getNhanVienTheoMa
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<NhanVien> getNhanVienTheoMa(String maNhanVien) throws IOException, SQLException {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maNhanVien);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String hoTenNhanVien = rs.getString("hoTenNhanVien");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String diaChi = rs.getString("diaChi");
            boolean trangThaiLamViec = rs.getBoolean("trangThaiLamViec");
            String soDienThoai = rs.getString("soDienThoai");
            String hinhAnh = rs.getString("hinhAnh");
            boolean chucVu = rs.getBoolean("chucVu");

            NhanVien nv = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, diaChi, trangThaiLamViec, soDienThoai, hinhAnh, chucVu);
            dsNhanVien.add(nv);
        }
        return dsNhanVien;
    }

    public ArrayList<NhanVien> getNhanVienTheoTen(String ten) throws IOException, SQLException {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        NhanVien nv = new NhanVien();
        ConnectDB.getInstance();
        
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM NhanVien WHERE hoTenNhanVien COLLATE SQL_Latin1_General_CP1_CI_AS LIKE '%' + ? + '%';";
        statement = con.prepareStatement(sql);
        statement.setString(1, ten);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maNhanVien = rs.getString("maNhanVien");
            String hoTenNhanVien = rs.getString("hoTenNhanVien");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String diaChi = rs.getString("diaChi");
            boolean trangThaiLamViec = rs.getBoolean("trangThaiLamViec");
            String soDienThoai = rs.getString("soDienThoai");
            String hinhAnh = rs.getString("hinhAnh");
            boolean chucVu = rs.getBoolean("chucVu");
            nv = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, diaChi, trangThaiLamViec, soDienThoai, hinhAnh, chucVu);
            dsNhanVien.add(nv);
        }
        return dsNhanVien;
    }

    public String getHoTenNhanVienTheoMa(String maNhanVien) throws IOException, SQLException {
        String hoTenNhanVien = null;
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maNhanVien);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            hoTenNhanVien = rs.getString("hoTenNhanVien");

        }
        return hoTenNhanVien;
    }

    public String getNhanVienDangNhap() {
        String tenNhanVien = TaiKhoan_DAO.getTenNhanVien();

        return tenNhanVien;
    }

    public boolean createNhanVien(NhanVien nv) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO NhanVien VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, nv.getMaNhanVien());
            statement.setString(2, nv.getHoTenNhanVien());
            statement.setBoolean(3, nv.isChucVu());
            statement.setBoolean(4, nv.isGioiTinh());
            statement.setBoolean(5, nv.isTrangThaiLamViec());
            statement.setString(6, nv.getDiaChi());
            statement.setString(7, nv.getSoDIenThoai());
            statement.setString(8, nv.getHinhAnh());

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public void updateNhanVien(NhanVien nv) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE NhanVien SET maNhanVien = ?, hoTenNhanVien = ?, chucVu=?, gioiTinh=?,"
                + " trangThaiLamViec = ?, diaChi = ?, soDienThoai=?, hinhAnh=? WHERE maNhanVien=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, nv.getMaNhanVien());
            statement.setString(2, nv.getHoTenNhanVien());
            statement.setBoolean(3, nv.isChucVu());
            statement.setBoolean(4, nv.isGioiTinh());
            statement.setBoolean(5, nv.isTrangThaiLamViec());
            statement.setString(6, nv.getDiaChi());
            statement.setString(7, nv.getSoDIenThoai());
            statement.setString(8, nv.getHinhAnh());
            statement.setString(9, nv.getMaNhanVien());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTrangThaiLamViec(String ma) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE NhanVien SET trangThaiLamViec = ? WHERE maNhanVien=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setBoolean(1, false);
            statement.setString(2, ma);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNhanVien(String ma) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from NhanVien where maNhanVien = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ma);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int demSoLuongNhanVienTheoMaMau(int nam) {
        int soLuong = 0;
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String year = String.format("%02d", nam % 100);
        String mau = "NV" + year + "%";
        System.out.println(year);
        String sql = "SELECT COUNT(*) AS soLuong FROM NhanVien WHERE maNhanVien LIKE ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, mau);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("soLuong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soLuong;
    }
}

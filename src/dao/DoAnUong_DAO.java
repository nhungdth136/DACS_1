
package dao;

import connectDB.ConnectDB;
import entity.DoAnUong;
import entity.TrangThaiSuDung;
import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DoAnUong_DAO {

    /**
     *
     * @param getAllTableDoAnUong
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<DoAnUong> getAllTableDoAnUong() throws SQLException {
        ArrayList<DoAnUong> dsDoAnUong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM DoAnUong";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maDoAnUong = rs.getString("maDoAnUong");
            String tenDoAnUong = rs.getString("tenDoAnUong");
            boolean loai = rs.getBoolean("loai");
            double giaNhap = rs.getDouble("giaNhap");
            double giaBan = rs.getDouble("giaBan");
            boolean hoanTra = rs.getBoolean("hoanTra");
            int soLuong = rs.getInt("soLuong");
            LocalDate ngaySanXuat = rs.getDate("ngaySanXuat").toLocalDate();
            LocalDate hanSuDung = rs.getDate("HanSuDung").toLocalDate();
            String moTa = rs.getString("moTa");
            TrangThaiSuDung trangThaiSuDung = TrangThaiSuDung.valueOf(rs.getString("trangThaiSuDung"));
            
            DoAnUong doAnUong = new DoAnUong(maDoAnUong, tenDoAnUong, loai, giaNhap, giaBan, hoanTra, soLuong, ngaySanXuat, hanSuDung, moTa, trangThaiSuDung);
            dsDoAnUong.add(doAnUong);
        }
        return dsDoAnUong;
    }

    /**
     *
     * @param maDoAnUong
     * @param getPhongTheoMaDoAnUong
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<DoAnUong> getDAUTheoMaDoAnUong(String maDoAnUong) throws IOException, java.sql.SQLException {
        ArrayList<DoAnUong> dsDoAnUong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM DoAnUong WHERE maDoAnUong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maDoAnUong);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String tenDoAnUong = rs.getString("tenDoAnUong");
            boolean loai = rs.getBoolean("loai");
            double giaNhap = rs.getDouble("giaNhap");
            double giaBan = rs.getDouble("giaBan");
            boolean hoanTra = rs.getBoolean("hoanTra");
            int soLuong = rs.getInt("soLuong");
            LocalDate ngaySanXuat = rs.getDate("ngaySanXuat").toLocalDate();
            LocalDate hanSuDung = rs.getDate("HanSuDung").toLocalDate();
            String moTa = rs.getString("moTa");
            TrangThaiSuDung trangThaiSuDung = TrangThaiSuDung.valueOf(rs.getString("trangThaiSuDung"));

            DoAnUong doAnUong = new DoAnUong(maDoAnUong, tenDoAnUong, loai, giaNhap, giaBan, hoanTra, soLuong, ngaySanXuat, hanSuDung, moTa, trangThaiSuDung);
            dsDoAnUong.add(doAnUong);

        }
        return dsDoAnUong;
    }

    public ArrayList<DoAnUong> getPhongTheoTenDoAnUong(String maDoAnUong) throws IOException, java.sql.SQLException {
        ArrayList<DoAnUong> dsDoAnUong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM DoAnUong WHERE maDoAnUong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maDoAnUong);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String tenDoAnUong = rs.getString("tenDoAnUong");
            boolean loai = rs.getBoolean("loai");
            double giaNhap = rs.getDouble("giaNhap");
            double giaBan = rs.getDouble("giaBan");
            boolean hoanTra = rs.getBoolean("hoanTra");
            int soLuong = rs.getInt("soLuong");
            LocalDate ngaySanXuat = rs.getDate("ngaySanXuat").toLocalDate();
            LocalDate hanSuDung = rs.getDate("HanSuDung").toLocalDate();
            String moTa = rs.getString("moTa");
            TrangThaiSuDung trangThaiSuDung = TrangThaiSuDung.valueOf(rs.getString("trangThaiSuDung"));

            DoAnUong doAnUong = new DoAnUong(maDoAnUong, tenDoAnUong, loai, giaNhap, giaBan, hoanTra, soLuong, ngaySanXuat, hanSuDung, moTa, trangThaiSuDung);
            dsDoAnUong.add(doAnUong);
        }
        return dsDoAnUong;
    }

    public boolean createDoAnUong(DoAnUong sp) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO DoAnUong VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, sp.getMaDoAnUong());
            statement.setString(2, sp.getTenDoAnUong());
            statement.setBoolean(3, sp.isLoai());
            statement.setDouble(4, sp.getGiaNhap());
            statement.setDouble(5, sp.getGiaBan());
            statement.setBoolean(6, sp.isHoanTra());
            statement.setInt(7, sp.getSoLuong());
            statement.setDate(8, java.sql.Date.valueOf(sp.getNgaySanXuat()));
            statement.setDate(9, java.sql.Date.valueOf(sp.getHanSuDung()));
            statement.setString(10, sp.getMoTa());
            statement.setInt(11, sp.getTrangThaiSuDung().getTentrangThaiSuDung());

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public void updateDoAnUong(DoAnUong sp) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE DoAnUong SET maDoAnUong = ?, tenDoAnUong = ?, loai=?, giaNhap=?,"
                + " giaBan = ?, hoanTra = ?, soLuong=?, ngaySanXuat=? ,"
                + "hanSuDung = ?, moTa = ?,trangThaiSuDung = ? WHERE maDoAnUong=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, sp.getMaDoAnUong());
            statement.setString(2, sp.getTenDoAnUong());
            statement.setBoolean(3, sp.isLoai());
            statement.setDouble(4, sp.getGiaNhap());
            statement.setDouble(5, sp.getGiaBan());
            statement.setBoolean(6, sp.isHoanTra());
            statement.setInt(7, sp.getSoLuong());
            statement.setDate(8, java.sql.Date.valueOf(sp.getNgaySanXuat()));
            statement.setDate(9, java.sql.Date.valueOf(sp.getHanSuDung()));
            statement.setString(10, sp.getMoTa());
            statement.setString(11, sp.getTrangThaiSuDung().name());
            statement.setString(12, sp.getMaDoAnUong());

            statement.executeUpdate();
            System.out.println("update oke!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoAnUong(DoAnUong p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from DoAnUong where maDoAnUong = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaDoAnUong());
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
}

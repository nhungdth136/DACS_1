
package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import entity.NhanVien;
import java.awt.CardLayout;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Desktop;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 *  ADMIN
 */
public class JFrame_TrangChu extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form JFrame_TrangChu
     */
    private JPanel_QuanLyNhanVien qlnv;
    private JPanel_QuanLyPhong qlp;
    private JPanel_QuanLyDoAnUong qlsp;
    private JPanel_QuanLyKhuyenMai qlkm;
    private JPanel_QuanLyKhachHang qlkh;
    private JPanel_QuanLyHoaDon qlhd;
    private JPanel_QuanLyPhongO qlpo;
    private TrangChu trangChu;
    private NhanVien nv;
    private JPopupMenu menuThongKe, menuTrangChu;
    private JMenuItem thongKeDoanhThu, thongKeKhachHang;//, trangMain, help
    private JPanel_GiaoDienThongKeDoanhThu tkdt;
    private JPanel_GiaoDienThongKeKhachHang tkkh;

    public JFrame_TrangChu(NhanVien nv) throws SQLException {
        initComponents();
        this.nv = nv;
        fix_ui();
        if (!nv.isChucVu()) {
            btn_quanLyNhanVien.setEnabled(false);
            btn_datPhong.setEnabled(false);
        }

        addEvents();
        lbltenNhanVien.setText(nv.getHoTenNhanVien());

        if (nv.isChucVu()) {
            lblChucVu.setText("Quản lý");
        } else {
            lblChucVu.setText("Lễ Tân");
        }
        trangChu = new TrangChu();
        qlnv = new JPanel_QuanLyNhanVien();
        qlp = new JPanel_QuanLyPhong(nv);
        qlsp = new JPanel_QuanLyDoAnUong(nv);
        qlkh = new JPanel_QuanLyKhachHang();
        qlkm = new JPanel_QuanLyKhuyenMai(nv);
        qlhd = new JPanel_QuanLyHoaDon(nv);
        qlpo = new JPanel_QuanLyPhongO();
        tkdt = new JPanel_GiaoDienThongKeDoanhThu();
        tkkh = new JPanel_GiaoDienThongKeKhachHang();

        CardLayout cardLayout = (CardLayout) MainContent.getLayout();
        MainContent.add(trangChu,"trangChu");
        MainContent.add(qlnv, "qlnv");
        MainContent.add(qlp, "qlp");
        MainContent.add(qlsp, "qlsp");
        MainContent.add(qlkh, "qlkh");
        MainContent.add(qlkm, "qlkm");
        MainContent.add(qlhd, "qlhd");
        MainContent.add(qlpo, "qlpo");
        MainContent.add(tkdt, "tkdt");
        MainContent.add(tkkh, "tkkh");

        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }

    private void fix_ui() {
        btn_quanLyPhong.setText("Quản lý Thuê Phòng");
        btn_datPhong.setText("Quản Lý Phòng");
        btn_trangChu.setText("Hệ Thống");
        btn_datPhong.setIcon(new FlatSVGIcon(getClass().getResource("/icon/customer_1.svg")));
        // Đặt căn lề trái cho icon
        btn_datPhong.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_datPhong.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_datPhong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_datPhong.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_datPhong.setPreferredSize(new java.awt.Dimension(250, 23));

        btn_hoaDon.setIcon(new FlatSVGIcon(getClass().getResource("/icon/export.svg")));
        // Đặt căn lề trái cho icon
        btn_hoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_hoaDon.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_hoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_hoaDon.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_hoaDon.setPreferredSize(new java.awt.Dimension(250, 23));

        btn_thongKe.setIcon(new FlatSVGIcon(getClass().getResource("/icon/statistical.svg")));
        // Đặt căn lề trái cho icon
        btn_thongKe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_thongKe.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_thongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_thongKe.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_thongKe.setPreferredSize(new java.awt.Dimension(250, 23));
        menuThongKe = new JPopupMenu();
        thongKeDoanhThu = new JMenuItem("Thống Kê Doanh Thu");
        thongKeKhachHang= new JMenuItem("Thống Kê Khách Hàng");
        menuThongKe.add(thongKeDoanhThu);
        menuThongKe.add(thongKeKhachHang);

        
//      menuTrangChu = new JPopupMenu();
//        trangMain = new JMenuItem("Trang Chủ");
//        help = new JMenuItem("Trợ Giúp");
  //      menuTrangChu.add(trangMain);
   //     menuTrangChu.add(help);
    }

    private void loadQuanLyNhanVien() {
        MainContent.removeAll();
        MainContent.add(qlnv, "qlnv");
        MainContent.revalidate();
        MainContent.repaint();
    }

    private void loadQuanLyKhachHang() {
        MainContent.removeAll();
        MainContent.add(qlkh, "qlkh");
        MainContent.revalidate();
        MainContent.repaint();
    }

    public void loadQuanLyPhong() {
        MainContent.removeAll();
        MainContent.add(qlp, "qlp");
        MainContent.revalidate();
        MainContent.repaint();
    }

    private void loadQuanLyDoAnUong() {
        MainContent.removeAll();
        MainContent.add(qlsp, "qlsp");
        MainContent.revalidate();
        MainContent.repaint();
    }

    private void loadQuanLyKhuyenMai() {
        MainContent.removeAll();
        MainContent.add(qlkm, "qlkm");
        MainContent.revalidate();
        MainContent.repaint();
    }

    private void loadQuanLyHoaDon() {
        MainContent.removeAll();
        MainContent.add(qlhd, "qlhd");
        MainContent.revalidate();
        MainContent.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuTaskBar = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        bar1 = new javax.swing.JPanel();
        bar2 = new javax.swing.JPanel();
        info = new javax.swing.JPanel();
        pnlIcon = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        lbltenNhanVien = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        Content10 = new javax.swing.JPanel();
        btn_trangChu = new javax.swing.JButton();
        Content2 = new javax.swing.JPanel();
        btn_quanLyPhong = new javax.swing.JButton();
        Content7 = new javax.swing.JPanel();
        btn_quanLyNhanVien = new javax.swing.JButton();
        Content8 = new javax.swing.JPanel();
        btn_quanLyKhachHang = new javax.swing.JButton();
        Content9 = new javax.swing.JPanel();
        btn_quanLyDoAnUong = new javax.swing.JButton();
        Content11 = new javax.swing.JPanel();
        btn_khuyenMai = new javax.swing.JButton();
        Content12 = new javax.swing.JPanel();
        btn_datPhong = new javax.swing.JButton();
        Content13 = new javax.swing.JPanel();
        btn_hoaDon = new javax.swing.JButton();
        Content14 = new javax.swing.JPanel();
        btn_thongKe = new javax.swing.JButton();
        bar3 = new javax.swing.JPanel();
        pnlBottom = new javax.swing.JPanel();
        DangXuat = new javax.swing.JPanel();
        btn_dangXuat = new javax.swing.JButton();
        bar4 = new javax.swing.JPanel();
        btn_datPhong1 = new javax.swing.JButton();
        MainContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lý đặt phòng khách sạn");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(250, 250, 250));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setForeground(java.awt.Color.white);
        setSize(new java.awt.Dimension(1400, 800));

        MenuTaskBar.setBackground(new java.awt.Color(250, 250, 250));
        MenuTaskBar.setPreferredSize(new java.awt.Dimension(250, 1400));
        MenuTaskBar.setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(250, 250, 250));
        pnlTop.setPreferredSize(new java.awt.Dimension(250, 80));
        pnlTop.setLayout(new java.awt.BorderLayout());

        bar1.setBackground(new java.awt.Color(204, 214, 219));
        bar1.setOpaque(false);
        bar1.setPreferredSize(new java.awt.Dimension(1, 0));

        javax.swing.GroupLayout bar1Layout = new javax.swing.GroupLayout(bar1);
        bar1.setLayout(bar1Layout);
        bar1Layout.setHorizontalGroup(
            bar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        bar1Layout.setVerticalGroup(
            bar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnlTop.add(bar1, java.awt.BorderLayout.EAST);

        bar2.setBackground(new java.awt.Color(204, 214, 219));
        bar2.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout bar2Layout = new javax.swing.GroupLayout(bar2);
        bar2.setLayout(bar2Layout);
        bar2Layout.setHorizontalGroup(
            bar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        bar2Layout.setVerticalGroup(
            bar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        pnlTop.add(bar2, java.awt.BorderLayout.SOUTH);

        info.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15));
        info.setOpaque(false);
        info.setLayout(new java.awt.BorderLayout());

        pnlIcon.setOpaque(false);
        pnlIcon.setPreferredSize(new java.awt.Dimension(60, 0));

        lblIcon.setIcon(new FlatSVGIcon(getClass().getResource("/icon/man.svg")));
        lblIcon.setPreferredSize(new java.awt.Dimension(50, 70));
        pnlIcon.add(lblIcon);

        info.add(pnlIcon, java.awt.BorderLayout.WEST);

        pnlInfo.setBackground(new java.awt.Color(250, 250, 250));
        pnlInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 0, 0, 15));
        pnlInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        lbltenNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbltenNhanVien.setText("Chưa Xác Định");
        pnlInfo.add(lbltenNhanVien);

        lblChucVu.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblChucVu.setText("Quản lý");
        pnlInfo.add(lblChucVu);

        info.add(pnlInfo, java.awt.BorderLayout.CENTER);

        pnlTop.add(info, java.awt.BorderLayout.CENTER);

        MenuTaskBar.add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlCenter.setBackground(new java.awt.Color(250, 250, 250));
        pnlCenter.setToolTipText("");
        pnlCenter.setPreferredSize(new java.awt.Dimension(230, 600));
        pnlCenter.setLayout(new java.awt.BorderLayout());

        jScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 0, 10));
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(225, 45));

        Content10.setBackground(new java.awt.Color(255, 255, 255));
        Content10.setForeground(new java.awt.Color(255, 255, 255));
        Content10.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_trangChu.setBackground(new java.awt.Color(250, 250, 250));
        btn_trangChu.setIcon(new FlatSVGIcon(getClass().getResource("/icon/home.svg")));
        btn_trangChu.setText("Trang Chủ");
        // Đặt căn lề trái cho icon
        btn_trangChu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_trangChu.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_trangChu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_trangChu.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_trangChu.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_trangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_trangChuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content10Layout = new javax.swing.GroupLayout(Content10);
        Content10.setLayout(Content10Layout);
        Content10Layout.setHorizontalGroup(
            Content10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Content10Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btn_trangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        Content10Layout.setVerticalGroup(
            Content10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_trangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content10);

        Content2.setBackground(new java.awt.Color(255, 255, 255));
        Content2.setForeground(new java.awt.Color(255, 255, 255));
        Content2.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_quanLyPhong.setBackground(new java.awt.Color(250, 250, 250));
        btn_quanLyPhong.setIcon(new FlatSVGIcon(getClass().getResource("/icon/ticket.svg")));
        btn_quanLyPhong.setText("Quản Lý Phòng");
        // Đặt căn lề trái cho icon
        btn_quanLyPhong.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_quanLyPhong.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_quanLyPhong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_quanLyPhong.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_quanLyPhong.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_quanLyPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quanLyPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content2Layout = new javax.swing.GroupLayout(Content2);
        Content2.setLayout(Content2Layout);
        Content2Layout.setHorizontalGroup(
            Content2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Content2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btn_quanLyPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        Content2Layout.setVerticalGroup(
            Content2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_quanLyPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content2);

        Content7.setBackground(new java.awt.Color(255, 255, 255));
        Content7.setForeground(new java.awt.Color(255, 255, 255));
        Content7.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_quanLyNhanVien.setBackground(new java.awt.Color(250, 250, 250));
        btn_quanLyNhanVien.setText("Quản Lý Nhân Viên");
        btn_quanLyNhanVien.setIcon(new FlatSVGIcon(getClass().getResource("/icon/employee.svg")));
        // Đặt căn lề trái cho icon
        btn_quanLyNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_quanLyNhanVien.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_quanLyNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_quanLyNhanVien.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_quanLyNhanVien.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_quanLyNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quanLyNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content7Layout = new javax.swing.GroupLayout(Content7);
        Content7.setLayout(Content7Layout);
        Content7Layout.setHorizontalGroup(
            Content7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Content7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btn_quanLyNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        Content7Layout.setVerticalGroup(
            Content7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_quanLyNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content7);

        Content8.setBackground(new java.awt.Color(255, 255, 255));
        Content8.setForeground(new java.awt.Color(255, 255, 255));
        Content8.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_quanLyKhachHang.setBackground(new java.awt.Color(250, 250, 250));
        btn_quanLyKhachHang.setText("Quản Lý Khách Hàng");
        btn_quanLyKhachHang.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_quanLyKhachHang.setIcon(new FlatSVGIcon(getClass().getResource("/icon/customer_1.svg")));
        // Đặt căn lề trái cho icon
        btn_quanLyKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_quanLyKhachHang.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_quanLyKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_quanLyKhachHang.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_quanLyKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quanLyKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content8Layout = new javax.swing.GroupLayout(Content8);
        Content8.setLayout(Content8Layout);
        Content8Layout.setHorizontalGroup(
            Content8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Content8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btn_quanLyKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        Content8Layout.setVerticalGroup(
            Content8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_quanLyKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content8);

        Content9.setBackground(new java.awt.Color(255, 255, 255));
        Content9.setForeground(new java.awt.Color(255, 255, 255));
        Content9.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_quanLyDoAnUong.setBackground(new java.awt.Color(250, 250, 250));
        btn_quanLyDoAnUong.setText("Quản Lý Đồ Ăn Uống");
        btn_quanLyDoAnUong.setIcon(new FlatSVGIcon(getClass().getResource("/icon/bill.svg")));
        // Đặt căn lề trái cho icon
        btn_quanLyDoAnUong.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_quanLyDoAnUong.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_quanLyDoAnUong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_quanLyDoAnUong.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_quanLyDoAnUong.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_quanLyDoAnUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quanLyDoAnUongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content9Layout = new javax.swing.GroupLayout(Content9);
        Content9.setLayout(Content9Layout);
        Content9Layout.setHorizontalGroup(
            Content9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Content9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btn_quanLyDoAnUong, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        Content9Layout.setVerticalGroup(
            Content9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_quanLyDoAnUong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content9);

        Content11.setBackground(new java.awt.Color(255, 255, 255));
        Content11.setForeground(new java.awt.Color(255, 255, 255));
        Content11.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_khuyenMai.setBackground(new java.awt.Color(250, 250, 250));
        btn_khuyenMai.setIcon(new FlatSVGIcon(getClass().getResource("/icon/statistics.svg")));
        btn_khuyenMai.setText("Quản Lý Khuyến Mãi");
        // Đặt căn lề trái cho icon
        btn_khuyenMai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_khuyenMai.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_khuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_khuyenMai.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btn_khuyenMai.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_khuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khuyenMaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content11Layout = new javax.swing.GroupLayout(Content11);
        Content11.setLayout(Content11Layout);
        Content11Layout.setHorizontalGroup(
            Content11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Content11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btn_khuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        Content11Layout.setVerticalGroup(
            Content11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_khuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content11);

        Content12.setBackground(new java.awt.Color(255, 255, 255));
        Content12.setForeground(new java.awt.Color(255, 255, 255));
        Content12.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_datPhong.setBackground(new java.awt.Color(250, 250, 250));
        btn_datPhong.setText("Quản Lý Đặt Phòng");
        btn_datPhong.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_datPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content12Layout = new javax.swing.GroupLayout(Content12);
        Content12.setLayout(Content12Layout);
        Content12Layout.setHorizontalGroup(
            Content12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Content12Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btn_datPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        Content12Layout.setVerticalGroup(
            Content12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_datPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content12);

        Content13.setBackground(new java.awt.Color(255, 255, 255));
        Content13.setForeground(new java.awt.Color(255, 255, 255));
        Content13.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_hoaDon.setBackground(new java.awt.Color(250, 250, 250));
        btn_hoaDon.setText("Quản Lý Hóa Đơn");
        btn_hoaDon.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_hoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content13Layout = new javax.swing.GroupLayout(Content13);
        Content13.setLayout(Content13Layout);
        Content13Layout.setHorizontalGroup(
            Content13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Content13Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(btn_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        Content13Layout.setVerticalGroup(
            Content13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_hoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content13);

        Content14.setBackground(new java.awt.Color(255, 255, 255));
        Content14.setForeground(new java.awt.Color(255, 255, 255));
        Content14.setPreferredSize(new java.awt.Dimension(300, 50));

        btn_thongKe.setBackground(new java.awt.Color(250, 250, 250));
        btn_thongKe.setText("Thống Kê");
        btn_thongKe.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_thongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Content14Layout = new javax.swing.GroupLayout(Content14);
        Content14.setLayout(Content14Layout);
        Content14Layout.setHorizontalGroup(
            Content14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Content14Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(btn_thongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        Content14Layout.setVerticalGroup(
            Content14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_thongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Content14);

        jScrollPane.setViewportView(jPanel1);

        pnlCenter.add(jScrollPane, java.awt.BorderLayout.CENTER);

        MenuTaskBar.add(pnlCenter, java.awt.BorderLayout.CENTER);

        bar3.setBackground(new java.awt.Color(204, 214, 219));
        bar3.setPreferredSize(new java.awt.Dimension(1, 1));
        bar3.setLayout(new java.awt.BorderLayout());
        MenuTaskBar.add(bar3, java.awt.BorderLayout.EAST);

        pnlBottom.setBackground(new java.awt.Color(250, 250, 250));
        pnlBottom.setPreferredSize(new java.awt.Dimension(250, 50));
        pnlBottom.setLayout(new java.awt.BorderLayout());

        DangXuat.setBackground(new java.awt.Color(255, 255, 255));
        DangXuat.setPreferredSize(new java.awt.Dimension(200, 30));

        btn_dangXuat.setBackground(new java.awt.Color(250, 250, 250));
        btn_dangXuat.setIcon(new FlatSVGIcon(getClass().getResource("/icon/logout.svg")));
        btn_dangXuat.setText("Đăng xuất");
        // Đặt căn lề trái cho icon
        btn_dangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_dangXuat.setIconTextGap(10); // Khoảng cách giữa icon và văn bản

        // Đặt căn lề phải cho văn bản
        btn_dangXuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_dangXuat.setVerticalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout DangXuatLayout = new javax.swing.GroupLayout(DangXuat);
        DangXuat.setLayout(DangXuatLayout);
        DangXuatLayout.setHorizontalGroup(
            DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_dangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        DangXuatLayout.setVerticalGroup(
            DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_dangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlBottom.add(DangXuat, java.awt.BorderLayout.CENTER);

        bar4.setBackground(new java.awt.Color(204, 214, 219));
        bar4.setPreferredSize(new java.awt.Dimension(1, 1));

        btn_datPhong1.setBackground(new java.awt.Color(250, 250, 250));
        btn_datPhong1.setText("Đặt Phòng");
        btn_datPhong1.setPreferredSize(new java.awt.Dimension(250, 23));
        btn_datPhong1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datPhongActionPerformed(evt);
            }
        });
        bar4.add(btn_datPhong1);

        pnlBottom.add(bar4, java.awt.BorderLayout.EAST);

        MenuTaskBar.add(pnlBottom, java.awt.BorderLayout.SOUTH);

        getContentPane().add(MenuTaskBar, java.awt.BorderLayout.WEST);

        MainContent.setBackground(new java.awt.Color(250, 250, 250));
        MainContent.setBorder(new javax.swing.border.MatteBorder(null));
        MainContent.setLayout(new java.awt.CardLayout());
        getContentPane().add(MainContent, java.awt.BorderLayout.CENTER);
        trangChu = new TrangChu();
        MainContent.add(trangChu).setVisible(true);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_quanLyNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quanLyNhanVienActionPerformed

    }//GEN-LAST:event_btn_quanLyNhanVienActionPerformed

    private void btn_quanLyKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quanLyKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_quanLyKhachHangActionPerformed

    private void btn_quanLyDoAnUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quanLyDoAnUongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_quanLyDoAnUongActionPerformed

    private void btn_khuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khuyenMaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_khuyenMaiActionPerformed

    private void btn_trangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_trangChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_trangChuActionPerformed

    private void btn_quanLyPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quanLyPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_quanLyPhongActionPerformed


    private void btn_thongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_thongKeActionPerformed


    private void btn_datPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_datPhongActionPerformed


    private void btn_hoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hoaDonActionPerformed

    /**
     * @param args the command line arguments
     */
    //    public static void main(String args[]) {
    //        /* Set the Nimbus look and feel */
    //        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    //        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
    //         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
    //         */
    //        try {
    //            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    //                if ("Nimbus".equals(info.getName())) {
    //                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
    //                    break;
    //                }
    //            }
    //        } catch (ClassNotFoundException ex) {
    //            java.util.logging.Logger.getLogger(JFrame_TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (InstantiationException ex) {
    //            java.util.logging.Logger.getLogger(JFrame_TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (IllegalAccessException ex) {
    //            java.util.logging.Logger.getLogger(JFrame_TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //            java.util.logging.Logger.getLogger(JFrame_TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        }
    //        //</editor-fold>
    //
    //        /* Create and display the form */
    //        java.awt.EventQueue.invokeLater(new Runnable() {
    //            public void run() {
    //                new JFrame_TrangChu(new NhanVien()).setVisible(true);
    //            }
    //        });
    //    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content10;
    private javax.swing.JPanel Content11;
    private javax.swing.JPanel Content12;
    private javax.swing.JPanel Content13;
    private javax.swing.JPanel Content14;
    private javax.swing.JPanel Content2;
    private javax.swing.JPanel Content7;
    private javax.swing.JPanel Content8;
    private javax.swing.JPanel Content9;
    private javax.swing.JPanel DangXuat;
    private javax.swing.JPanel MainContent;
    private javax.swing.JPanel MenuTaskBar;
    private javax.swing.JPanel bar1;
    private javax.swing.JPanel bar2;
    private javax.swing.JPanel bar3;
    private javax.swing.JPanel bar4;
    private javax.swing.JButton btn_dangXuat;
    private javax.swing.JButton btn_datPhong;
    private javax.swing.JButton btn_datPhong1;
    private javax.swing.JButton btn_hoaDon;
    private javax.swing.JButton btn_khuyenMai;
    private javax.swing.JButton btn_quanLyDoAnUong;
    private javax.swing.JButton btn_quanLyKhachHang;
    private javax.swing.JButton btn_quanLyNhanVien;
    private javax.swing.JButton btn_quanLyPhong;
    private javax.swing.JButton btn_thongKe;
    private javax.swing.JButton btn_trangChu;
    private javax.swing.JPanel info;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lbltenNhanVien;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlIcon;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
    public void addEvents() {
        btn_trangChu.addActionListener(this);
        btn_quanLyNhanVien.addActionListener(this);
        btn_quanLyPhong.addActionListener(this);
        btn_quanLyKhachHang.addActionListener(this);
        btn_datPhong.addActionListener(this);
        btn_dangXuat.addActionListener(this);
        btn_quanLyDoAnUong.addActionListener(this);
        btn_khuyenMai.addActionListener(this);
        btn_hoaDon.addActionListener(this);
        btn_thongKe.addActionListener(this);
        thongKeDoanhThu.addActionListener(this);
        thongKeKhachHang.addActionListener(this);
      //  trangMain.addActionListener(this);
      //  help.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_trangChu) {
          //  menuTrangChu.show(btn_trangChu, 7, 48);
          
           MainContent.removeAll();
//            // Thêm JPanel mới vào MainContent
            MainContent.add(trangChu,"trangChu");
//            // Cập nhật giao diện
            MainContent.revalidate();
            MainContent.repaint();
        }

        if (e.getSource() == btn_quanLyNhanVien) {
            // Xóa bỏ tất cả các component hiện tại trong MainContent (nếu cần)
            MainContent.removeAll();
            // Thêm JPanel mới vào MainContent
            MainContent.add(qlnv);
            // Cập nhật giao diện
            MainContent.revalidate();
            MainContent.repaint();

        }
        if (e.getSource() == btn_quanLyPhong) {
            MainContent.removeAll();
            // Thêm JPanel mới vào MainContent
            MainContent.add(qlp);
            // Cập nhật giao diện
            MainContent.revalidate();
            MainContent.repaint();
        }

        if (e.getSource() == btn_quanLyDoAnUong) {
            MainContent.removeAll();
            MainContent.add(qlsp, "qlsp");
            MainContent.revalidate();
            MainContent.repaint();
        }

        if (e.getSource() == btn_khuyenMai) {
            MainContent.removeAll();
            MainContent.add(qlkm, "qlkm");
            MainContent.revalidate();
            MainContent.repaint();
        }

        if (e.getSource() == btn_hoaDon) {
            MainContent.removeAll();
            try {
                qlhd = new JPanel_QuanLyHoaDon(nv);
                MainContent.add(qlhd, "qlhd");
                MainContent.revalidate();
                MainContent.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(JFrame_TrangChu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == btn_quanLyKhachHang) {
            MainContent.removeAll();
            try {
                qlkh = new JPanel_QuanLyKhachHang();
                MainContent.add(qlkh);
                MainContent.revalidate();
                MainContent.repaint();
            } catch (SQLException ex) {
                Logger.getLogger(JFrame_TrangChu.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == btn_datPhong) {
            MainContent.removeAll();
            MainContent.add(qlpo,"qlpo");
            MainContent.revalidate();
            MainContent.repaint();
        }
        if (e.getSource() == btn_thongKe) {
            menuThongKe.show(btn_thongKe, 7, 48);
        }
        if (e.getSource() == thongKeDoanhThu){
            MainContent.removeAll();
            MainContent.add(tkdt,"tkdt");
            MainContent.revalidate();
            MainContent.repaint();
        }
        if (e.getSource() == thongKeKhachHang){
            MainContent.removeAll();
            MainContent.add(tkkh,"tkkh");
            MainContent.revalidate();
            MainContent.repaint();
        }
        
        if (e.getSource() == btn_dangXuat) {
            this.setVisible(false);
        }
    }

}

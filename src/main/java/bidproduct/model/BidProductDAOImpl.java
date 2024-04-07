package bidproduct.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BidProductDAOImpl implements BidProductDAO {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/mmdf?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "root";

    private static final String INSERT_STMT = "INSERT INTO bid_product (seller_id, category_id, name, description, start_price, start_time, end_time, review_status, bid_status)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT * FROM bid_product order by bid_product_id";
    private static final String GET_ONE_STMT = "SELECT * FROM bid_product where bid_product_id = ?";
    private static final String DELETE = "DELETE FROM bid_product where bid_product_id = ?";
    private static final String UPDATE = "UPDATE bid_product SET bid_product_id=?, seller_id=?, category_id=?, name=?, description=?, start_price=?, start_time=?, end_time=?, review_status=?, bid_status=? WHERE bid_product_id=?";

    @Override
    public void insert(BidProductVO bidProductVO) {
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
        ) {
            pstmt.setInt(1, bidProductVO.getSellerId());
            pstmt.setInt(2, bidProductVO.getCategoryId());
            pstmt.setString(3, bidProductVO.getName());
            pstmt.setString(4, bidProductVO.getDescription());
            pstmt.setInt(5, bidProductVO.getStartPrice());
            pstmt.setDate(6, bidProductVO.getStartTime());
            pstmt.setDate(7, bidProductVO.getEndTime());
            pstmt.setInt(8, bidProductVO.getReviewStatus());
            pstmt.setInt(9, bidProductVO.getBidStatus());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        }
    }

    @Override
    public void update(BidProductVO bidProductId) {
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(UPDATE);
        ) {
            pstmt.setInt(1, bidProductId.getBidProductId());
            pstmt.setInt(2, bidProductId.getSellerId());
            pstmt.setInt(3, bidProductId.getCategoryId());
            pstmt.setString(4, bidProductId.getName());
            pstmt.setString(5, bidProductId.getDescription());
            pstmt.setInt(6, bidProductId.getStartPrice());
            pstmt.setDate(7, bidProductId.getStartTime());
            pstmt.setDate(8, bidProductId.getEndTime());
            pstmt.setInt(9, bidProductId.getReviewStatus());
            pstmt.setInt(10, bidProductId.getBidStatus());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        }
    }

    @Override
    public void delete(Integer bidProductId) {
        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(DELETE);
        ) {
            pstmt.setInt(1, bidProductId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        }
    }

    @Override
    public BidProductVO findByPrimaryKey(Integer bidProductId) {

        BidProductVO bidProductVO = null;

        try (Connection con = DriverManager.getConnection(url, userid, passwd);
             PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
        ) {
            pstmt.setInt(1, bidProductId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bidProductVO = new BidProductVO();
                bidProductVO.setBidProductId(rs.getInt("bid_product_id"));
                bidProductVO.setSellerId(rs.getInt("seller_id"));
                bidProductVO.setName(rs.getString("name"));
                bidProductVO.setCategoryId(rs.getInt("category_id"));
                bidProductVO.setDescription(rs.getString("description"));
                bidProductVO.setStartPrice(rs.getInt("start_price"));
                bidProductVO.setStartTime(rs.getDate("start_time"));
                bidProductVO.setEndTime(rs.getDate("end_time"));
                bidProductVO.setReviewStatus(rs.getInt("review_status"));
                bidProductVO.setBidStatus(rs.getInt("bid_status"));
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        }
        return bidProductVO;
    }

    @Override
    public List<BidProductVO> getAll() {
        List<BidProductVO> list = new ArrayList<BidProductVO>();
        BidProductVO bidProductVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // bidProductVO 也稱為 Domain objects
                bidProductVO = new BidProductVO();
                bidProductVO.setBidProductId(rs.getInt("bid_product_id"));
                bidProductVO.setSellerId(rs.getInt("seller_id"));
                bidProductVO.setName(rs.getString("name"));
                bidProductVO.setCategoryId(rs.getInt("category_id"));
                bidProductVO.setDescription(rs.getString("description"));
                bidProductVO.setStartPrice(rs.getInt("start_price"));
                bidProductVO.setStartTime(rs.getDate("start_time"));
                bidProductVO.setEndTime(rs.getDate("end_time"));
                bidProductVO.setReviewStatus(rs.getInt("review_status"));
                bidProductVO.setBidStatus(rs.getInt("bid_status"));

                list.add(bidProductVO); // Store the row in the list
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;

    }

    public static void main(String[] args) {
        BidProductDAOImpl dao = new BidProductDAOImpl();

//        // 新增
//        BidProductVO bidProductVO1 = new BidProductVO();
//        bidProductVO1.setBidProductId(101);
//        bidProductVO1.setSellerId(5);
//        bidProductVO1.setName("Kobe 球員卡");
//        bidProductVO1.setCategoryId(5);
//        bidProductVO1.setDescription("Kobe Bryant 球員卡 2002年 狀態良好");
//        bidProductVO1.setStartPrice(3000);
//        bidProductVO1.setStartTime(java.sql.Date.valueOf("2002-01-01"));
//        bidProductVO1.setEndTime(java.sql.Date.valueOf("2002-01-01"));
//        bidProductVO1.setReviewStatus(0);
//        bidProductVO1.setBidStatus(0);
//        dao.insert(bidProductVO1);
//
//        // 修改
//        BidProductVO bidProductVO2 = new BidProductVO();
//        bidProductVO1.setBidProductId(101);
//        bidProductVO1.setSellerId(5);
//        bidProductVO1.setName("Lebron 球員卡");
//        bidProductVO1.setCategoryId(5);
//        bidProductVO1.setDescription("Lebron James 球員卡 2002年 狀態良好");
//        bidProductVO1.setStartPrice(3000);
//        bidProductVO1.setStartTime(java.sql.Date.valueOf("2002-01-01"));
//        bidProductVO1.setEndTime(java.sql.Date.valueOf("2002-01-01"));
//        bidProductVO1.setReviewStatus(0);
//        bidProductVO1.setBidStatus(0);
//        dao.update(bidProductVO2);
//
//        // 刪除
//        dao.delete(101);

        // 查詢單筆
        BidProductVO bidProductVO3 = dao.findByPrimaryKey(1);
        System.out.print(bidProductVO3.getBidProductId() + ",");
        System.out.print(bidProductVO3.getSellerId() + ",");
        System.out.print(bidProductVO3.getName() + ",");
        System.out.print(bidProductVO3.getCategoryId() + ",");
        System.out.print(bidProductVO3.getDescription() + ",");
        System.out.print(bidProductVO3.getStartPrice() + ",");
        System.out.print(bidProductVO3.getStartTime() + ",");
        System.out.print(bidProductVO3.getEndTime() + ",");
        System.out.print(bidProductVO3.getReviewStatus() + ",");
        System.out.print(bidProductVO3.getBidStatus() + ",");
        System.out.println();
        System.out.println("---------------------");
        // 查詢
        List<BidProductVO> list = dao.getAll();
        for (BidProductVO aEmp : list) {
            System.out.print(aEmp.getBidProductId() + ",");
            System.out.print(aEmp.getSellerId() + ",");
            System.out.print(aEmp.getName() + ",");
            System.out.print(aEmp.getCategoryId() + ",");
            System.out.print(aEmp.getDescription());
            System.out.print(aEmp.getStartPrice() + ",");
            System.out.print(aEmp.getStartTime() + ",");
            System.out.print(aEmp.getEndTime() + ",");
            System.out.print(aEmp.getReviewStatus() + ",");
            System.out.print(aEmp.getBidStatus() + ",");
            System.out.println();
        }
    }
}

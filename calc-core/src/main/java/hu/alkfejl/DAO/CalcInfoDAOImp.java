package hu.alkfejl.DAO;

import hu.alkfejl.model.CalcInfo;

import java.sql.*;

public class CalcInfoDAOImp implements CalcInfoDAO {

    public CalcInfoDAOImp(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    private static final String CONN = "jdbc:sqlite:I:/Downloads/ALKFEJL/calc/calc-core/calc.db";
    private static final String UPDATE = "UPDATE CalcInfo SET mentettErtek=?";
    private static final String GET_MENTETT_ERTEK = "SELECT mentettErtek FROM CalcInfo";
    private static final String GET_ARFOLYAM = "SELECT arfolyam FROM CalcInfo";

    public boolean setInfo(CalcInfo ci) {
        try(Connection conn = DriverManager.getConnection(CONN); PreparedStatement st = conn.prepareStatement(UPDATE)) {
            st.setDouble(1, ci.getMentettErtek());

            if(st.executeUpdate() == 1) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public CalcInfo getInfo() {
        try(Connection conn = DriverManager.getConnection(CONN); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(GET_MENTETT_ERTEK);

            if(rs.next()){
                CalcInfo calcInfo = new CalcInfo(rs.getDouble(1));
                return calcInfo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public double getArfolyam() {
        try(Connection conn = DriverManager.getConnection(CONN); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(GET_ARFOLYAM);

            if(rs.next()){
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

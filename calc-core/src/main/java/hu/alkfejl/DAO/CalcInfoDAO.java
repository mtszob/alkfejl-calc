package hu.alkfejl.DAO;

import hu.alkfejl.model.CalcInfo;

public interface CalcInfoDAO {
    boolean setInfo(CalcInfo ci);
    CalcInfo getInfo();
    double getArfolyam();
}

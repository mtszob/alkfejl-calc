package hu.alkfejl.controller;

import hu.alkfejl.DAO.CalcInfoDAO;
import hu.alkfejl.DAO.CalcInfoDAOImp;
import hu.alkfejl.model.CalcInfo;

public class CalcInfoControllerImp implements CalcInfoController {
    CalcInfoDAO dao = new CalcInfoDAOImp();

    @Override
    public boolean setInfo(CalcInfo ci) {
        return dao.setInfo(ci);
    }

    @Override
    public CalcInfo getInfo() {
        return dao.getInfo();
    }

    @Override
    public double getArfolyam() {
        return dao.getArfolyam();
    }
}

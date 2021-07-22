package hu.alkfejl.controller;

import hu.alkfejl.model.CalcInfo;

public interface CalcInfoController {
    boolean setInfo(CalcInfo ci);
    CalcInfo getInfo();
    double getArfolyam();
}

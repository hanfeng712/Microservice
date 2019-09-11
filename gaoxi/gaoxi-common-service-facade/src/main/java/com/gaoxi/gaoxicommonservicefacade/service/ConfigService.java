package com.gaoxi.gaoxicommonservicefacade.service;

import com.gaoxi.gaoxicommonservicefacade.model.Config;

public interface ConfigService {
    public Config get();
    public boolean update(double managesalary, double staffsalary, double cleanerssalary, double manage, double staff, double cleaner, double totalmoney, double totalroom);
}

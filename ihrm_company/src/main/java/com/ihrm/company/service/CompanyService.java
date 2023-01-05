package com.ihrm.company.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.day.CompanyDao;
import com.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;

    public void add(Company company){
        //基本属性的设置
        String id = idWorker.nextId()+"";
        company.setId(id);
        //默认的状态
        company.setAuditState("0");//0：未审核，1：已审核
        company.setState(1); //0.未激活，1：已激活
        companyDao.save(company);
    }

    public void update(Company company) {
        Company temp = companyDao.findById(company.getId()).get();
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        companyDao.save(temp);
    }

    public void deleteById(String id) {
        companyDao.deleteById(id);
    }

    public Company findById(String id) {
        return companyDao.findById(id).get();
    }

    public List<Company> findAll() {
        return companyDao.findAll();
    }
}

package com.dusto.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dusto.bos.dao.IDecidedzoneDao;
import com.dusto.bos.dao.INoticebilDao;
import com.dusto.bos.domain.Decidedzone;
import com.dusto.bos.domain.Noticebill;
import com.dusto.bos.domain.Staff;
import com.dusto.bos.domain.User;
import com.dusto.bos.domain.Workbill;
import com.dusto.bos.utils.BOSUtils;
import com.dusto.crm.ICustomerService;

@Service
@Transactional
public class NoticebilServiceImpl implements com.dusto.bos.service.INoticebilService {

    @Autowired
    private INoticebilDao noticebilDao;
    
    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    
    @Autowired
    private ICustomerService customerService;
    
    /**
     * 保存一个业务通知单，并尝试自动分单
     */
    public void save(Noticebill model) {
        User loginUser = BOSUtils.getLoginUser();
        model.setUser(loginUser);//设置为当前用户
        noticebilDao.save(model);
        //获取客户的取件地址
        String pickaddress = model.getPickaddress();
        //调用远程crm服务，根据取件地址查询定区id
        String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickaddress);
        if(decidedzoneId!=null){
            //查询到了定去id，可以自动分单
            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
            Staff staff = decidedzone.getStaff();
            model.setStaff(staff);
            //设置分单类型为自动分单
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            //为取派员产生一个工单
            Workbill workbill = new Workbill();
        }else{
           //设置分单类型为人工分单
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
    }

}

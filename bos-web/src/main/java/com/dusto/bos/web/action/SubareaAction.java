package com.dusto.bos.web.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Region;
import com.dusto.bos.domain.Subarea;
import com.dusto.bos.service.ISubareaService;
import com.dusto.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    /**
     * 添加分区
     * 
     * @return
     */
    public String add() {
        subareaService.save(model);
        return LIST;
    }

    /**
     * 分页查询
     * 
     * @return
     */
    public String pageQuery() {
        DetachedCriteria db = pageBean.getDetachedCriteria();
        //动态添加过滤条件
        String addresskey = model.getAddresskey();
        if(StringUtils.isNoneBlank(addresskey)){
            //添加过滤条件，根据地址关键字模糊查询
            db.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
        }
        Region region = model.getRegion();
        if(region!=null){
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            if(StringUtils.isNoneBlank(province)){
                //添加过滤条件，根据省份关键字模糊查询
                db.add(Restrictions.like("addresskey", "%"+province+"%"));
            }
            if(StringUtils.isNoneBlank(city)){
                  //添加过滤条件，根据地址关键字模糊查询
            }
            if(StringUtils.isNoneBlank(district)){
                  //添加过滤条件，根据地址关键字模糊查询
            }
        }
        subareaService.pageQuery(pageBean);
        this.java2Json(pageBean,
                new String[] { "currentPage", "pageSize", "detachedCriteria", "decidedzone", "subareas" });
        return NONE;
    }
}

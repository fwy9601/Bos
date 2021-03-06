package com.dusto.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Region;
import com.dusto.bos.domain.Staff;
import com.dusto.bos.domain.Subarea;
import com.dusto.bos.service.ISubareaService;
import com.dusto.bos.utils.FileUtils;
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
        DetachedCriteria dc = pageBean.getDetachedCriteria();
        // 动态添加过滤条件
        String addresskey = model.getAddresskey();
        if (StringUtils.isNoneBlank(addresskey)) {
            // 添加过滤条件，根据地址关键字模糊查询
            dc.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }
        Region region = model.getRegion();
        if (region != null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            // 参数一分区对象关联区域对象中的属性名称，参数二别名可以随意
            dc.createAlias("region", "r");
            if (StringUtils.isNotBlank(province)) {
                // 添加过滤条件，根据省份关键字模糊查询
                dc.add(Restrictions.like("r.province", "%" + province + "%"));
            }
            if (StringUtils.isNotBlank(city)) {
                // 添加过滤条件，根据市关键字模糊查询
                dc.add(Restrictions.like("r.city", "%" + city + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                // 添加过滤条件，根据区关键字模糊查询
                dc.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }
        subareaService.pageQuery(pageBean);
        this.java2Json(pageBean,
                new String[] { "currentPage", "pageSize", "detachedCriteria", "decidedzone", "subareas" });
        return NONE;
    }

    /**
     * 分区导出功能
     * 
     * @throws IOException
     */
    public String exportXls() throws IOException {

        // 第一步：查出所有数据
        List<Subarea> list = subareaService.findAll();
        // 第二步：使用poi将数据写到excel中
        // 在内存中创建一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建标签页
        HSSFSheet sheet = workbook.createSheet("分区数据");
        // 创建标题行
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("开始编号");
        headRow.createCell(2).setCellValue("结束编号");
        headRow.createCell(3).setCellValue("位置信息");
        headRow.createCell(4).setCellValue("省市区");
        for (Subarea subarea : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getStartnum());
            dataRow.createCell(2).setCellValue(subarea.getEndnum());
            dataRow.createCell(3).setCellValue(subarea.getPosition());
            dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }
        // 第三步：使用输出流(一个流，两个头)进行文件下载
        String filename = "分区数据.xls";
        String ContentType = ServletActionContext.getServletContext().getMimeType(filename);
        ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(ContentType);
        filename = FileUtils.encodeDownloadFilename(filename,
                ServletActionContext.getRequest().getHeader("User-Agent"));
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
        workbook.write(out);
        return NONE;
    }

    /**
     * 查询所有未关联到定区的分区，返回json
     * 
     * @return
     */
    public String listajax() {
        List<Subarea> list = subareaService.findListNotAssociation();
        this.java2Json(list, new String[] { "region", "decidedzone" });
        return NONE;
    }

    // 属性驱动，接受定去id
    private String decidedzoneId;

    /**
     * 根据定去id查询关联的分区信息
     */
    public String findListByDecidedzoneId() {
        List<Subarea> list = subareaService.findListByDecidedzoneId(decidedzoneId);
        java2Json(list, new String[] {"decidedzone","subareas"});
        return NONE;
    }

    /**
     * 查询区域分区分布数据
     * @return
     */
    public String findsubareasGroupByProvince(){
        List<Object> list = subareaService.findsubareasGroupByProvince();
        this.java2Json(list, new String[]{});
        return NONE;
    }
    
    
    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }
    
}

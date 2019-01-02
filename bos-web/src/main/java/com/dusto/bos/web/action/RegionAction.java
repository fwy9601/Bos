package com.dusto.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dusto.bos.domain.Region;
import com.dusto.bos.service.IRegionService;
import com.dusto.bos.utils.PageBean;
import com.dusto.bos.utils.PinYin4jUtils;
import com.dusto.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 区域管理
 * 
 * @author dusto
 *
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

    @Autowired
    public IRegionService regionService;

    // 属性驱动，接受上传文件
    private File regionFile;

    /**
     * 区域导入
     * 
     * @return
     * @throws Exception
     */
    /**
     * @return
     * @throws Exception
     */
    public String importXls() throws Exception {
        System.out.println(regionFile);
        // 使用poi解析excel文件
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        // 根据名称读取sheet
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        // 遍历标签页中所有的行
        List<Region> regionList = new ArrayList<>();
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            if (rowNum == 0)
                continue;
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            // 包装一个区域对象
            Region region = new Region(id, province, city, district, postcode, null, null, null);
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortcode = StringUtils.join(headByString);
            // 城市编码-->shijianzhuang
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");
            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            regionList.add(region);
        }
        // 批量保存
        regionService.saveBatch(regionList);

        return NONE;
    }

    /**
     * 分页查询
     * 
     * @throws IOException
     */
    public String pageQuery() throws IOException {
        regionService.pageQuery(pageBean);
        this.java2Json(pageBean, new String[] { "currentPage", "pageSize", "detachedCriteria" });
        return NONE;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

}

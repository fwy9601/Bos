package com.dusto.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.dusto.bos.dao.IWorkbillDao;
import com.dusto.bos.domain.Workbill;
import com.dusto.bos.utils.PageBean;

@Repository
public class WorkbillDaoImpl extends BaseDaoImpl<Workbill> implements IWorkbillDao {

}

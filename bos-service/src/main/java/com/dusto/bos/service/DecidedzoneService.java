package com.dusto.bos.service;

import com.dusto.bos.domain.Decidedzone;
import com.dusto.bos.utils.PageBean;

public interface DecidedzoneService {

    public void add(Decidedzone model, String[] subareaid);

    public void pageQuery(PageBean pageBean);

}

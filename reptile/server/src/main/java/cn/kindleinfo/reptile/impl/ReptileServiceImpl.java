package cn.kindleinfo.reptile.impl;

import cn.kindleinfo.reptile.invoker.ReptileInvokerFactory;
import cn.kindleinfo.reptile.service.ReptileService;

/**
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
public class ReptileServiceImpl implements ReptileService {

    private ReptileInvokerFactory reptileInvokerFactory;
    @Override
    public void startReptile() {
        //TODO  从数据库获取爬的key值
        String site = "ireadweek";
        reptileInvokerFactory.getReptileInvokerService(site).start();
    }
}

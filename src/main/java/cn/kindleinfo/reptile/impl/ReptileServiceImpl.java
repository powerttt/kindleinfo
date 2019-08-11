package cn.kindleinfo.reptile.impl;

import cn.kindleinfo.api.service.ReptileService;
import cn.kindleinfo.reptile.invoker.ReptileInvokerFactory;

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

package cn.kindleinfo.reptile.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@Component
public class ReptileInvokerFactory {

    @Autowired
    private Map<String, ReptileInvokerService> map;

    public ReptileInvokerService getReptileInvokerService(String site){
        return map.get(site);
    }

}

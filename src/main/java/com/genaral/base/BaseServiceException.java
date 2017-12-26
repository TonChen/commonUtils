/**
 *
 */
package com.genaral.base;

import com.genaral.http.HttpCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

/**
 * @author ShenHuaJie
 * @version 2016年6月7日 下午8:43:02
 */
@SuppressWarnings("serial")
public abstract class BaseServiceException extends RuntimeException {
    public BaseServiceException() {
    }

    public BaseServiceException(Throwable ex) {
        super(ex);
    }

    public BaseServiceException(String message) {
        super(message);
    }

    public BaseServiceException(String message, Throwable ex) {
        super(message, ex);
    }

    public void handler(ModelMap modelMap) {
        modelMap.put("httpCode", getHttpCode().value());
        if (StringUtils.isNotBlank(getMessage())) {
            modelMap.put("msg", getMessage());
        } else {
            modelMap.put("msg", getHttpCode().msg());
        }
        modelMap.put("timestamp", System.currentTimeMillis());
    }

    protected abstract HttpCode getHttpCode();
}

package com.jit.iot.service;

import org.apache.mina.core.session.IoSession;

/**
 * @className: AnalyzeService
 * @author: kay
 * @date: 2019/7/22 15:08
 * @packageName: com.jit.iot.service
 */
public interface AnalyzeService {
    void AnalyzeData(IoSession session, Object message)throws Exception;
}

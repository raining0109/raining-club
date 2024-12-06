package com.raining.circle.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.raining.circle.api.req.RemoveShareCircleReq;
import com.raining.circle.api.req.SaveShareCircleReq;
import com.raining.circle.api.req.UpdateShareCircleReq;
import com.raining.circle.api.vo.ShareCircleVO;
import com.raining.circle.server.entity.po.ShareCircle;

import java.util.List;

/**
 * <p>
 * 圈子信息 服务类
 * </p>
 *
 * @author raining
 * @since 2024/05/16
 */
public interface ShareCircleService extends IService<ShareCircle> {

    List<ShareCircleVO> listResult();

    Boolean saveCircle(SaveShareCircleReq req);

    Boolean updateCircle(UpdateShareCircleReq req);

    Boolean removeCircle(RemoveShareCircleReq req);
}

package com.raining.circle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.raining.circle.api.common.PageResult;
import com.raining.circle.api.req.GetShareMomentReq;
import com.raining.circle.api.req.RemoveShareMomentReq;
import com.raining.circle.api.req.SaveMomentCircleReq;
import com.raining.circle.api.vo.ShareMomentVO;
import com.raining.circle.server.entity.po.ShareMoment;

/**
 * <p>
 * 动态信息 服务类
 * </p>
 *
 * @author raining
 * @since 2024/05/16
 */
public interface ShareMomentService extends IService<ShareMoment> {

    Boolean saveMoment(SaveMomentCircleReq req);

    PageResult<ShareMomentVO> getMoments(GetShareMomentReq req);

    Boolean removeMoment(RemoveShareMomentReq req);

    void incrReplyCount(Long id, int count);

}

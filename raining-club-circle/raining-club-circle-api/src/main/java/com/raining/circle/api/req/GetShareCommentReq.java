package com.raining.circle.api.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 鸡圈内容信息
 * </p>
 *
 * @author raining
 * @since 2024/05/16
 */
@Getter
@Setter
public class GetShareCommentReq implements Serializable {

    private Long id;

}

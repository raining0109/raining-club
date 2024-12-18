package com.raining.subject.domain.cache.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheMassage implements Serializable {
    private static final long serialVersionUID = -3574997636829868400L;

    private String cacheName;
    private CacheMsgType type;  //标识更新或删除操作
    private Object key;
    private Object value;
    private String msgSource;   //源主机标识，用来避免重复操作
}

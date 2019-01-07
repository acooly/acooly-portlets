package com.acooly.portlets.comment.core.service;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.portlets.comment.client.dto.CommentBaseInfo;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2019-01-06 19:47
 */
@Component
public interface CommentService {


    /**
     * 评论
     *
     * @param commentBaseInfo
     * @return
     */
    CommentInfo comment(CommentBaseInfo commentBaseInfo);

    /**
     * 点赞
     *
     * @param commentId
     * @return
     */
    Integer thumbsup(Long commentId);

    /**
     * 有效评论分页查询
     * <p>
     * 1、查询有效评论
     * 2、根据业务类型和业务key查询
     * 3、根据置顶和时间倒叙排序
     * 4、预留其他查询参数和排序参数（都可为空）
     *
     * @param pageInfo   [必选] 分页参数
     * @param busiKey    [必选] 被评论的业务对象ID
     * @param busiType   [可选] 业务类型（默认内置: DEFAULT）
     * @param map        [可选] 扩展条件（可覆盖内置）
     * @param sortMap    [可选] 扩展排序（可覆盖内置）
     * @param queryChild [可选] 是否查询子评论（默认为：true）
     * @return
     */
    PageInfo<CommentInfo> query(PageInfo<CommentInfo> pageInfo, String busiKey, String busiType,
                                Map<String, Object> map, Map<String, Boolean> sortMap, Boolean queryChild);

    default PageInfo<CommentInfo> query(PageInfo<CommentInfo> pageInfo, String busiKey, String busiType) {
        return query(pageInfo, busiKey, busiType, null, null, null);
    }
}

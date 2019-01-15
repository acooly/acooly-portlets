package com.acooly.portlets.comment.openapi;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiNote;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import com.acooly.portlets.comment.client.message.CommentListApiRequest;
import com.acooly.portlets.comment.client.message.CommentListApiResponse;
import com.acooly.portlets.comment.core.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 发表评论
 *
 * @author zhangpu
 * @date 2019-01-07 01:04
 */
@Slf4j
@OpenApiNote("评论查询接口。" +
        "<li>1、查询有效评论（被管理员屏蔽外）</li>" +
        "<li>2、默认以顶层的评论进行分页，同时也默认会查询所有的子评论并放置到顶层评论的children中（目前只支持两层）</li>" +
        "<li>3、如果传入actionUserNo(表示当前登录的用户)，列表会返回每条记录是否被该用户点赞（actionThumbsup=yes）")
@ApiDocType(code = "Portlets", name = "前端组件")
@OpenApiService(name = "commentList", desc = "评论查询", owner = "zhangpu")
public class CommentListApiService extends BaseApiService<CommentListApiRequest, CommentListApiResponse> {

    @Autowired
    private CommentService commentService;

    @Override
    protected void doService(CommentListApiRequest request, CommentListApiResponse response) {
        PageInfo<CommentInfo> pageInfo = new PageInfo(request.getLimit(), request.getStart());
        commentService.query(pageInfo, request.getActionUserNo(), request.getBusiKey(), request.getBusiType());
        response.setTotalPages(pageInfo.getTotalPage());
        response.setTotalRows(pageInfo.getTotalCount());
        response.setRows(pageInfo.getPageResults());
    }
}

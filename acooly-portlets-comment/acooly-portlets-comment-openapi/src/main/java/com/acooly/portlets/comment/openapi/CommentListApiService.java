package com.acooly.portlets.comment.openapi;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiNote;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import com.acooly.portlets.comment.client.message.CommentListApiRequest;
import com.acooly.portlets.comment.client.message.CommentListApiResponse;
import com.acooly.portlets.comment.core.service.CommentService;
import com.acooly.portlets.common.PortletsContants;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 发表评论
 *
 * @author zhangpu
 * @date 2019-01-07 01:04
 */
@Slf4j
@OpenApiNote("评论查询接口。<br>接口参数说明" +
        "<li>1、查询有效评论（被管理员屏蔽外），busiType和busiKey为必选参数，表示针对的业务层busiType这业务类型的busiKey这个业务数据的评论</li>" +
        "<li>2、默认以顶层的评论进行分页，同时也默认会查询所有的子评论并放置到顶层评论的children中（目前只支持两层）,可以设置childrenInclude=no不查询子评论</li>" +
        "<li>3、如果传入actionUserNo(表示当前登录的用户)，列表会返回每条记录是否被该用户点赞（actionThumbsup=yes）</li>" +
        "<li>4、attachQuery是查询条件，表示查询是否有附件（图片和视频），默认为空表示查询全部，yes：表示查询有附件的评论，no：查询没有附件的评论</li>" +
        "<li>5、attachInclude是查询结果是否包含附件，请注意区别attachQuery查询条件</li>" +
        "<p>场景说明" +
        "<li>1. 最小化数据查询，不返回子评论（childrenInclude=no）和不返回附件列表（attachInclude=no），获得最简列表后进行界面列表显示，然后点击界面列表后，按commentId单个再次查询详情（接口：commentInfo）。</li>" +
        "<li>1. 最大化数据查询，childrenInclude=yes,attachInclude=yes,直接返回所有数据，消耗较大（三次数据库查询），但方便。</li>" +
        "</p>")
@ApiDocType(code = PortletsContants.APIDOC_TYPE_CODE, name = PortletsContants.APIDOC_TYPE_NAME)
@OpenApiService(name = "commentList", desc = "评论查询", owner = "zhangpu")
public class CommentListApiService extends BaseApiService<CommentListApiRequest, CommentListApiResponse> {

    @Autowired
    private CommentService commentService;

    @Override
    protected void doService(CommentListApiRequest request, CommentListApiResponse response) {
        PageInfo<CommentInfo> pageInfo = new PageInfo(request.getLimit(), request.getStart());

        Map<String, Object> map = Maps.newHashMap();
        if (request.getAttachQuery() != null) {
            if (request.getAttachQuery() == WhetherStatus.yes) {
                map.put("GT_attachCount", 0);
            } else {
                map.put("EQ_attachCount", 0);
            }
        }
        Boolean childrenInclude = request.getChildrenInclude() == null ? true :
                (request.getChildrenInclude() == WhetherStatus.yes ? true : false);
        Boolean attachInclude = request.getAttachInclude() == null ? false :
                (request.getAttachInclude() == WhetherStatus.yes ? true : false);
        commentService.query(pageInfo, request.getActionUserNo(), request.getBusiKey(), request.getBusiType(),
                map, null, childrenInclude, attachInclude);

        response.setTotalPages(pageInfo.getTotalPage());
        response.setTotalRows(pageInfo.getTotalCount());
        response.setRows(pageInfo.getPageResults());
    }
}

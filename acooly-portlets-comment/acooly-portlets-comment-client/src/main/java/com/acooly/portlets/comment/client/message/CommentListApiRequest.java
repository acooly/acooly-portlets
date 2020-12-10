package com.acooly.portlets.comment.client.message;

import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.portlets.comment.client.enums.CommentBusiType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author zhangpu
 * @date 2019-01-07 00:56
 */
@Getter
@Setter
public class CommentListApiRequest extends PageApiRequest {


    /**
     * 自定义业务分类
     */
    @Size(max = 32)
    @NotBlank
    @OpenApiField(desc = "业务分类", constraint = "自定义业务分类", demo = "DEFAULT", ordinal = 1)
    private String busiType = CommentBusiType.DEFAULT.code();

    /**
     * 自定义业务ID（绑定ID），可选
     */
    @Size(min = 1, max = 32)
    @OpenApiField(desc = "业务KEY", constraint = "标记评论的业务,是业务的唯一标志", demo = "1", ordinal = 2)
    private String busiKey;

    /**
     * 用户编码
     * 用于标志查询的结果记录中，该用户是否已点赞
     */
    @Size(max = 64)
    @OpenApiField(desc = "操作用户标志", constraint = "操作用户编码", demo = "201111112121212", ordinal = 3)
    private String actionUserNo;


    @OpenApiField(desc = "是否返回子评论", constraint = "查询结果是否包含子评论", demo = "no", ordinal = 5)
    private WhetherStatus childrenInclude;

    @OpenApiField(desc = "是否有附件", constraint = "查询条件：查询是否有附件的评论，为空表示所有", demo = "no", ordinal = 4)
    private WhetherStatus attachQuery;

    @OpenApiField(desc = "是否返回附件", constraint = "查询结果是否包含附件列表", demo = "no", ordinal = 6)
    private WhetherStatus attachInclude = WhetherStatus.no;

    @OpenApiField(desc = "是否只查询当前用户数据", constraint = "是否只查询当前用户数据，当actionUserNo不为空时有效", demo = "no", ordinal = 7)
    private WhetherStatus userInclude = WhetherStatus.no;

}

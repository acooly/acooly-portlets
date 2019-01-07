package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.portlets.comment.client.enums.CommentBusiType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @author zhangpu
 * @date 2019-01-07 00:56
 */
@Data
public class CommentListApiRequest extends PageApiRequest {


    /**
     * 自定义业务分类
     */
    @Size(max = 32)
    @NotEmpty
    @OpenApiField(desc = "业务分类", constraint = "自定义业务分类", demo = "DEFAULT")
    private String busiType = CommentBusiType.DEFAULT.code();

    /**
     * 自定义业务ID（绑定ID），可选
     */
    @Size(min = 1, max = 32)
    @NotEmpty
    @OpenApiField(desc = "业务KEY", constraint = "标记评论的业务,是业务的唯一标志", demo = "1")
    private String busiKey;

}

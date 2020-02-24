package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpu
 * @date 2019-01-07 00:56
 */
@Getter
@Setter
public class CommentListApiResponse extends PageApiResponse<CommentInfo> {

}

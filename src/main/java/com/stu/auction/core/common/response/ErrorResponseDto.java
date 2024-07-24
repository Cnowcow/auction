package com.stu.auction.core.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String message ;  // 사용자들에게 보여줄 메시지
    private Object object ; // 개발자 참고용 오브젝트
}

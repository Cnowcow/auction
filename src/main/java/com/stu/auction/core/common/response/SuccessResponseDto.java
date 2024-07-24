package com.stu.auction.core.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 성공했을떄 사용하는 응답 Dto
 * */
@Data
@AllArgsConstructor
public class SuccessResponseDto<T> {

    private String message ;
    private T data;
    private int innerCode = 200 ;    // 로직상 문제는 없어서 httpStatus 는 정상응답(200) 처리를 하고 , 프론트에서 자세한
                                     // 실패 사유를 확인하고 싶을떄 사용하기위함  * 초기값은 200

}

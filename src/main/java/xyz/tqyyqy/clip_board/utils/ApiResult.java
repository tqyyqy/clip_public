
package xyz.tqyyqy.clip_board.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 前后端交互对象
 *
 * @author TaoYu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ApiResult SUCCESS_RESULT = new ApiResult();

    private static final ApiResult ERROR_RESULT = new ApiResult(0, false, "操作失败");

    private Integer code = 200;

    /**
     * 是否成功，默认true
     */
    private boolean success = Boolean.TRUE;
    /**
     * 提示信息，默认"操作成功"
     */
    private String msg = "操作成功";
    /**
     * 返回数据
     */
    private T data;

    public ApiResult(T data) {
        this.data = data;
    }

    public ApiResult(Integer code, boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

//  public ApiResult(Integer code, boolean success, String msg, T data) {
//    this.code = code;
//    this.success = success;
//    this.msg = msg;
//    this.data = data;
//  }

    public static <T> ApiResult ok() {
        return SUCCESS_RESULT;
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> okmsg(String message) {
        return new ApiResult(200, true, message);
    }

    //  返回信息及参数
    public static <T> ApiResult<T>  okdataAndmsg(String message, T data) {
        return new ApiResult(200, true, message, data);
    }

    public static <T> ApiResult<T> failed() {
        return ERROR_RESULT;
    }


    public static <T> ApiResult<T> failed(String message) {
        System.out.println("接口错误信息  >>>  >>> " + message);
        return new ApiResult<>(0, false, message);
    }

    public static <T> ApiResult<T> failed(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> failed(Integer code, String message) {
        return new ApiResult<>(code, false, message);
    }

}

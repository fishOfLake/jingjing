package com.example.util;

/**
 * @author weylan
 */
public enum ErrorEnum {
    /*
     * 错误信息
     * */
    E_400(400, "请求处理异常，请稍后再试"),
    E_500(500, "服务器内部错误"),
    E_501(501, "请求路径不存在"),
    E_502(502, "权限不足"),
    E_10008(10008, "角色删除失败,尚有用户属于此角色"),
    E_10009(10009, "账户已存在"),
    E_10011(10011, "数据已存在"),
    E_10012(10012, "数据不存在"),

    E_403(403,"拒绝访问"),
    E_20011(20011, "登陆已过期,请重新登陆"),

    E_90003(90003, "缺少必填参数"),
    E_90004(90004, "参数长度过长");


    private Integer errorCode;

    private String errorMsg;

    ErrorEnum() {
    }

    ErrorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

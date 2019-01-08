package io.github.cd871127.hodgepodge.cloud.lib.web.server.response;

public enum GeneralHodgepodgeResponse implements HodgepodgeResponse {
    SUCCESSFUL("000000", "成功"),

    UNKNOWN_ERROR("999998","UNKNOWN_ERROR"),
    FAILED("999999", "失败");

    private String code;
    private String message;

    GeneralHodgepodgeResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

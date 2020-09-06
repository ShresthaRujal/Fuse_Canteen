package com.fuse.canteen.utils;

public class BaseController {
    protected GlobalApiResponse globalApiResponse = new GlobalApiResponse();
    protected final boolean API_SUCCESS_STATUS = true;

    /**
     * Funtion that sends only Success Message as Response
     * @param message
     * @return
     */
    protected GlobalApiResponse successResponse(String message) {
        globalApiResponse.setStatus(API_SUCCESS_STATUS);
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(null);
        return globalApiResponse;
    }

    /**
     * Function that also pass data as Response
     * @param message
     * @param data
     * @return
     */
    protected GlobalApiResponse successResponse(String message, Object data) {
        globalApiResponse.setStatus(API_SUCCESS_STATUS);
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(data);
        return globalApiResponse;
    }

}

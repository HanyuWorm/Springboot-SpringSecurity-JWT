package com.petronas.fetchtechexam.config;

public class Constant {
    private String CustomErrorMessage ="1";
    public enum MESSAGE
    {
        SUCCESS("SUCCESS"),
        ERROR("ERROR"),
        FIELD_MISSING("ALL FIELD MISSING"),
        COMMENT_EXITS("Unable to create. A comment already exist."),
        FORMAT_DATE("Unable to create. Format date wrong."),
        MISSSING_ID("MISSSING_ID"),
        MISSSING_COMMENT("MISSSING_COMMENT"),
        MISSSING_DATE("MISSSING_DATE"),
        COMMENT_ID_NOT_FOUND("COMMENT_ID_NOT_FOUND"),
        RECORD_NOT_FOUND("RECORD_NOT_FOUND"),
        JWT_NOT_FOUND("CAN_NOT_AUTHORIZATION"),
        COMMENT_CREATED("Comment Created");

        private String message;

        MESSAGE(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}

package com.darkestapp.exceptions;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class PostmanEnvironmentGeneratorException extends Exception {

    private final String exceptionName;
    private final PostmanEnvironmentGeneratorException cause;
    private final String context;
    private final String possibleReason;
    private final Integer depth;

    public static final String CONTEXT_CONTENT_SEPARATOR = "&&";
    public static final String LINE_SEPARATOR = "---------------------------------------------------------------------------------\n";

    /**
     * Private constructor.
     * @param exceptionName
     * @param message
     * @param cause
     * @param context
     * @param possibleReason
     */
    private PostmanEnvironmentGeneratorException(
            final String exceptionName,
            final String message,
            final Exception cause,
            final String context,
            final String possibleReason) {
        super(message, cause);
        this.exceptionName = exceptionName;

        if(cause != null) {
            if(cause instanceof PostmanEnvironmentGeneratorException) {
                this.cause = (PostmanEnvironmentGeneratorException) cause;
            } else {
                this.cause = wrapException(cause);
            }
        } else {
            this.cause = null;
        }

        if(context == null || context.isEmpty()) {
            this.context = "N/A";
        } else {
            this.context = context;
        }

        if(possibleReason == null || possibleReason.isEmpty()) {
            this.possibleReason = "N/A";
        } else {
            this.possibleReason = possibleReason;
        }

        if(this.cause == null) {
            this.depth = Integer.valueOf(1);
        } else {
            this.depth = Integer.valueOf(this.cause.getDepth() + 1);
        }
    }

    /**
     * Default constructor.
     * @param message
     * @param cause
     * @param context
     * @param possibleReason
     */
    public PostmanEnvironmentGeneratorException(
            final String message,
            final Exception cause,
            final String context,
            final String possibleReason) {
        super(message, cause);
        this.exceptionName = getClass().toString();

        if(cause != null) {
            if(cause instanceof PostmanEnvironmentGeneratorException) {
                this.cause = (PostmanEnvironmentGeneratorException) cause;
            } else {
                this.cause = wrapException(cause);
            }
        } else {
            this.cause = null;
        }

        if(context == null || context.isEmpty()) {
            this.context = "N/A";
        } else {
            this.context = context;
        }

        if(possibleReason == null || possibleReason.isEmpty()) {
            this.possibleReason = "N/A";
        } else {
            this.possibleReason = possibleReason;
        }

        if(this.cause == null) {
            this.depth = Integer.valueOf(1);
        } else {
            this.depth = Integer.valueOf(this.cause.getDepth() + 1);
        }
    }

    /**
     * This method wraps an exception and adjusts to PostmanEnvironmentGeneratorException template
     * @param exception
     * @return
     */
    private PostmanEnvironmentGeneratorException wrapException(final Exception exception){
        if(exception instanceof PostmanEnvironmentGeneratorException) {
            return (PostmanEnvironmentGeneratorException) exception;
        }
        PostmanEnvironmentGeneratorException fermatException = new PostmanEnvironmentGeneratorException(
                exception.getClass().toString(),
                exception.getMessage(),
                null,
                "External exception",
                "Please, review the stacktrace");
        fermatException.setStackTrace(exception.getStackTrace());
        return fermatException;
    }

    /**
     * Returns the exception cause.
     * @return
     */
    @Override
    public PostmanEnvironmentGeneratorException getCause(){
        return cause;
    }

    /**
     * Returns the context.
     * @return
     */
    public String getContext(){
        return context;
    }

    /**
     * Returns the possible reason.
     * @return
     */
    public String getPossibleReason(){
        return possibleReason;
    }

    /**
     * Returns the exception depth.
     * @return
     */
    public int getDepth(){
        return depth.intValue();
    }

    /**
     * Returns the formatted context
     * @return
     */
    public String getFormattedContext() {
        StringBuilder builder = new StringBuilder("");
        for(String contextPart : context.split(CONTEXT_CONTENT_SEPARATOR))
            if(!contextPart.isEmpty())
                builder.append(contextPart).append("\n");
        return builder.toString();
    }

    /**
     * Returns the formatted trace.
     * @return
     */
    public String getFormattedTrace() {
        StringBuilder builder = new StringBuilder();
        for(StackTraceElement element : getStackTrace()) {
            builder.append("Class: ")
                    .append(element.getClassName())
                    .append(" - Line: ")
                    .append(element.getLineNumber())
                    .append("\n");
        }

        return builder.toString();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        if(cause != null) {
            builder.append(cause.toString());

            builder.append("Exception Number: ").append(depth.toString()).append("\n");
            builder.append("Exception Type: ").append(exceptionName).append("\n");
            builder.append("Exception Message: ").append(getMessage()).append("\n");
            builder.append("Exception Possible Cause: ");
            builder.append(getPossibleReason()).append("\n");
            builder.append("Exception Context: ");
            builder.append("\n---------------------------------------------------------------------------------\n");
            builder.append(getFormattedContext());
            builder.append(LINE_SEPARATOR);

        } else {

            builder.append("Exception Number: ").append(depth.toString()).append("\n");
            builder.append("Exception Type: ").append(exceptionName).append("\n");
            builder.append("Exception Message: ").append(getMessage()).append("\n");
            builder.append("Exception Possible Cause: ");
            builder.append(getPossibleReason()).append("\n");
            builder.append("Exception Context: ");
            builder.append("\n---------------------------------------------------------------------------------\n");
            builder.append(getFormattedContext());
            builder.append(LINE_SEPARATOR);
            builder.append("Exception Stack Trace: \n");
            builder.append(LINE_SEPARATOR);
            builder.append(getFormattedTrace());
            builder.append(LINE_SEPARATOR);

        }

        return builder.toString();
    }
}

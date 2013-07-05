package com.framework.common.mail.param;

public class Attachment {
    private String name;

    private String contentType;

    private byte[] content;

    public String getName() {
        return name;
    }

    public String getContentType() {
        if ((this.contentType == null) || (this.contentType.trim().length() == 0))
            return "application/octet-stream";

        String contentTypeStr = this.contentType;
        int index = contentType.indexOf(";");

        if (index > -1) {
            contentTypeStr = contentType.substring(0, index);
        }
        return contentTypeStr;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public Attachment() {}

    public Attachment(String name, String contentType, byte[] content) {
        this.name = name;
        this.contentType = contentType;
        this.content = content;
    }

    public Attachment(String name, byte[] content) {
        this(name, null, content);
    }

    public void writeToHttpServletResponseWithDownloadFile(javax.servlet.http.HttpServletResponse response) throws java.io.IOException {
        response.setContentType(this.getContentType());
        response.addHeader("Accept-Ranges", "bytes");
        response.addHeader("Accept-Length", String.valueOf(this.content.length));
        response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(name.getBytes(), "ISO-8859-1") + "\"");
        response.getOutputStream().write(this.content, 0, content.length);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    public void writeToHttpServletResponseWithStream(javax.servlet.http.HttpServletResponse response) throws java.io.IOException {
        response.setContentType(this.getContentType());
        response.setContentLength(this.content.length);
        response.getOutputStream().write(this.content);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}

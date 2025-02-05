package edu.escuelaing;

public class HttpRequest {

    private String query;
    private String path;

    public HttpRequest(String path, String query){
        this.query = query;
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public String getPath(){
        return path;
    }

    public void setQuery(String query){
        this.query = query;
    }

    public void setPath(String path){
        this.path = path;
    }

}

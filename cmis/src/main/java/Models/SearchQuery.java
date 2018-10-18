package Models;

import java.util.List;

public class SearchQuery {
    private String query;

    public SearchQuery(String query){
        this.query = query;
    }

    public String getQuery(){
        return query;
    }
    public void setQuery(String query){
        this.query = query;
    }
}
package ng.codeinn.oaunet.data.network;

import ng.codeinn.oaunet.data.network.model.ListWrapper;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    String BASE_URL = "https://www.oauife.edu.ng";

    @GET("/news-events/itemlist/category/7-events?format=json")
    Call<ListWrapper> getEvents();

    @GET("/news-events/itemlist/category/6-news?format=json")
    Call<ListWrapper> getNews();

    @GET("/news-events/itemlist/category/8-research?format=json")
    Call<ListWrapper> getResearches();

}

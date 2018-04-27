package ng.codeinn.oaunet.networkUtils;

import ng.codeinn.oaunet.model.ItemsModel;
import ng.codeinn.oaunet.model.ListWrapper;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    String BASE_URL = "https://oauife.edu.ng";

    @GET("/news-events/itemlist/category/7-events")
    Call<ListWrapper<ItemsModel>> getEvents();

    @GET("/news-events/itemlist/category/6-news")
    Call<ListWrapper<ItemsModel>> getNews();

    @GET("/news-events/itemlist/category/8-research")
    Call<ListWrapper<ItemsModel>> getResearches();

}

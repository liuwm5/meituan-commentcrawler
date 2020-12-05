import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PostTest {
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    @Test
    public void meituanScenicReviewInfoCrawler1() {
        HttpPost httpPost = new HttpPost("http://localhost:8080/map/meituanScenicReviewInfoCrawler1");
        StringEntity reqEntity = null;
        try {
            reqEntity = new StringEntity("meituanId=" + 93311902);
            reqEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(reqEntity);
            httpClient.execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

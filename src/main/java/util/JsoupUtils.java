package util;

import module.SheepFund;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * 功能：网络解析工具类
 * -------------------------------------------------------------------------------------------------
 * 创建者：@author 林钦宏
 * -------------------------------------------------------------------------------------------------
 * 创建日期：2019-03-26
 * -------------------------------------------------------------------------------------------------
 * 更新历史
 * 编号|更新日期|更新人|更新内容
 */
public class JsoupUtils {

    /**
     * 请求单页
     *
     * @param url
     * @return
     * @throws IOException
     */
    private static String getPage(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpGet
        HttpGet httpGet = new HttpGet(url);
        // 执行 get 请求
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            return EntityUtils.toString(httpEntity, "UTF-8");
        }
        return null;
    }

    public static SheepFund getSheepFund(String url) {
        SheepFund sheepFund = new SheepFund();
        try {
            String html = getPage(url);
            if (StringUtils.isBlank(html)) {
                return null;
            }

            Document doc = Jsoup.parse(html);

            // 开始解析页面
            // 基金名称和基金代码
            Element fundDetailTitle = doc.select("div.fundDetail-tit").first();
            if (fundDetailTitle == null) {
                sheepFund.setCode(SheepFund.DEFAULT_CODE);
                sheepFund.setName(SheepFund.DEFAULT_NAME);
            } else {
                Element title = fundDetailTitle.selectFirst("div");
                if (title != null) {
                    if (title.text().contains("(")) {
                        sheepFund.setName(title.text().substring(0, title.text().indexOf("(")));
                    } else {
                        sheepFund.setName(title.text());
                    }
                    Element code = title.selectFirst("span.ui-num");
                    if (code != null) {
                        sheepFund.setCode(code.text());
                    } else {
                        sheepFund.setCode(SheepFund.DEFAULT_CODE);
                    }
                } else {
                    sheepFund.setCode(SheepFund.DEFAULT_CODE);
                    sheepFund.setName(SheepFund.DEFAULT_NAME);
                }
            }

            // 单位净值和升幅
            Element dataItem02 = doc.selectFirst("dl.dataItem02");
            if (dataItem02 == null) {
                sheepFund.setIncrease(SheepFund.DEFAULT_INCREASE);
                sheepFund.setNetWorth(SheepFund.DEFAULT_NET_WORTH);
            } else {
                // 单位净值
                Element netWorth = dataItem02.selectFirst("span.ui-font-large.ui-color-green.ui-num");
                if (netWorth != null) {
                    sheepFund.setNetWorth(netWorth.text());
                } else {
                    sheepFund.setNetWorth(SheepFund.DEFAULT_NET_WORTH);
                }
                // 升幅
                Element increase = dataItem02.selectFirst("span.ui-font-middle.ui-color-green.ui-num");
                if (increase != null) {
                    sheepFund.setIncrease(increase.text());
                } else {
                    sheepFund.setIncrease(SheepFund.DEFAULT_INCREASE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheepFund;
    }

}

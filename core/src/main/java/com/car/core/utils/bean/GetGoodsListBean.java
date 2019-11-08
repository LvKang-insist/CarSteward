package com.car.core.utils.bean;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.bean
 * @time 2019/11/8 21:26
 * @description
 */
public class GetGoodsListBean {

    /**
     * currPage : 1
     * data : [{"goodsId":"1","goodsName":"PAPAGO行车记录仪","goodsThums":"Upload/selfGoods/2018-04/5ad416dc1e171_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"10.00","shopType":"1"},{"goodsId":"2","goodsName":"铁将军3160倒车雷达","goodsThums":"Upload/selfGoods/2018-04/5ad41b9d0ca8d_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"3","goodsName":"任我游os系统智能后视镜导航","goodsThums":"Upload/selfGoods/2018-04/5ad420c8833d0_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"4","goodsName":"UNIT 车载吸尘器","goodsThums":"Upload/selfGoods/2018-04/5ad43a77a6a85_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"5","goodsName":"YOOCAR 车载电源适配器","goodsThums":"Upload/selfGoods/2018-04/5ad43e014f34d_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"6","goodsName":"文丰五座座垫","goodsThums":"Upload/selfGoods/2018-04/5ad4415d00258_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"7","goodsName":"陆人行汽车摆件竹炭仿真狗","goodsThums":"Upload/selfGoods/2018-04/5ad446f697836_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"8","goodsName":"车丽友全包围丝圈双层汽车脚垫","goodsThums":"Upload/selfGoods/2018-04/5ad44a4041daa_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"9","goodsName":"吉吉太空记忆棉腰靠垫","goodsThums":"Upload/selfGoods/2018-04/5ad44c81492de_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"10","goodsName":"吉艺鹿 车载U盘","goodsThums":"Upload/selfGoods/2018-04/5ad44f96162ea_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.02","shopType":"1"},{"goodsId":"11","goodsName":"久仕牛车载手机支架","goodsThums":"Upload/selfGoods/2018-04/5ad453dbd1162_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"12","goodsName":"麦车饰汽车用品载储物","goodsThums":"Upload/selfGoods/2018-04/5ad456aa89946_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"13","goodsName":"盛贝除尘掸子","goodsThums":"Upload/selfGoods/2018-04/5ad45aa1be931_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"14","goodsName":"安美盛车载小毛巾","goodsThums":"Upload/selfGoods/2018-04/5ad45c5dc40ad_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"},{"goodsId":"15","goodsName":"卡马斯汽车摆件貔貅","goodsThums":"Upload/selfGoods/2018-04/5ad45ece7462f_thumb.jpg","goodsType":1,"isSelf":"1","shopId":"1","shopName":"车榜样自营商城","shopPrice":"0.01","shopType":"1"}]
     * msg : 商品列表
     * status : 1
     * totalPage : 1
     */

    private int currPage;
    private String msg;
    private int status;
    private int totalPage;
    private List<DataBean> data;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goodsId : 1
         * goodsName : PAPAGO行车记录仪
         * goodsThums : Upload/selfGoods/2018-04/5ad416dc1e171_thumb.jpg
         * goodsType : 1
         * isSelf : 1
         * shopId : 1
         * shopName : 车榜样自营商城
         * shopPrice : 10.00
         * shopType : 1
         */

        private String goodsId;
        private String goodsName;
        private String goodsThums;
        private int goodsType;
        private String isSelf;
        private String shopId;
        private String shopName;
        private String shopPrice;
        private String shopType;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsThums() {
            return goodsThums;
        }

        public void setGoodsThums(String goodsThums) {
            this.goodsThums = goodsThums;
        }

        public int getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(int goodsType) {
            this.goodsType = goodsType;
        }

        public String getIsSelf() {
            return isSelf;
        }

        public void setIsSelf(String isSelf) {
            this.isSelf = isSelf;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopPrice() {
            return shopPrice;
        }

        public void setShopPrice(String shopPrice) {
            this.shopPrice = shopPrice;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }
    }
}

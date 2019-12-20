package com.car.core.utils.bean;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.core.utils.bean
 * @time 2019/11/16 21:04
 * @description 首页主题
 */
public class GetStylesBean {

    /**
     * data : {"adGallerys":[{"adFile":"Upload/adspic/2019-06/5cf5da8812054.png","adId":"58","adName":"端午首页轮播1","adPositionId":"-5","adURL":"","goodsId":"0","goodsType":"0","isBidding":"0"},{"adFile":"Upload/adspic/2019-06/5cf5da97476d2.png","adId":"59","adName":"端午首页轮播2","adPositionId":"-5","adURL":"","goodsId":"0","goodsType":"0","isBidding":"0"},{"adFile":"Upload/adspic/2019-06/5cf5daa3ae6c5.png","adId":"60","adName":"端午首页轮播3","adPositionId":"-5","adURL":"","goodsId":"0","goodsType":"0","isBidding":"0"},{"adFile":"Upload/adspic/2019-06/5cf5dab23089c.png","adId":"61","adName":"端午首页轮播4","adPositionId":"-5","adURL":"","goodsId":"0","goodsType":"0","isBidding":"0"}],"arbImg":"Upload/styles/2019-06/5cf5d9e17533e.png","arbThums":"Upload/styles/2019-06/5cf5d9e17533e_thumb.png","backgroundImg":"Upload/styles/2019-06/5cf5d9a71d76f.png","backgroundThums":"Upload/styles/2019-06/5cf5d9a71d76f_thumb.png","changeBottleImg":"Upload/styles/2019-06/5cf5d9c83fcc0.png","changeBottleThums":"Upload/styles/2019-06/5cf5d9c83fcc0_thumb.png","changeTireImg":"Upload/styles/2019-06/5cf5d9bf7cd50.png","changeTireThums":"Upload/styles/2019-06/5cf5d9bf7cd50_thumb.png","changingGlassImg":"Upload/styles/2019-06/5cf5d9e652ded.png","changingGlassThums":"Upload/styles/2019-06/5cf5d9e652ded_thumb.png","cheapCarImg":"Upload/styles/2019-06/5cf5d9d879047.png","cheapCarThums":"Upload/styles/2019-06/5cf5d9d879047_thumb.png","cosmetologyImg":"Upload/styles/2019-06/5cf5d9e3eb755.png","cosmetologyThums":"Upload/styles/2019-06/5cf5d9e3eb755_thumb.png","decorateImg":"Upload/styles/2019-06/5cf5d9e9dc331.png","decorateThums":"Upload/styles/2019-06/5cf5d9e9dc331_thumb.png","fontColor":"333333","id":"34","isDaily":"1","isDefault":"0","isUse":"1","maintainImg":"Upload/styles/2019-06/5cf5d9b6a6cb3.png","maintainThums":"Upload/styles/2019-06/5cf5d9b6a6cb3_thumb.png","moreImg":"Upload/styles/2019-06/5cf5d9ebeb755.png","moreThums":"Upload/styles/2019-06/5cf5d9ebeb755_thumb.png","renderingImg":"Upload/styles/2019-06/5cf5d9eed0c16.jpg","renderingThums":"Upload/styles/2019-06/5cf5d9eed0c16_thumb.jpg","rescueImg":"Upload/styles/2019-06/5cf5d9ccb60d7.png","rescueThums":"Upload/styles/2019-06/5cf5d9ccb60d7_thumb.png","styleFlag":"1","styleSort":"0","styleTheme":"端午","usedCarImg":"Upload/styles/2019-06/5cf5d9d512054.png","usedCarThums":"Upload/styles/2019-06/5cf5d9d512054_thumb.png","valuationCarImg":"Upload/styles/2019-06/5cf5d9ddb60d7.png","valuationCarThums":"Upload/styles/2019-06/5cf5d9ddb60d7_thumb.png","violationInquiryImg":"Upload/styles/2019-06/5cf5d9d179047.png","violationInquiryThums":"Upload/styles/2019-06/5cf5d9d179047_thumb.png","washcarImg":"Upload/styles/2019-06/5cf5d9b0f3167.png","washcarThums":"Upload/styles/2019-06/5cf5d9b0f3167_thumb.png"}
     * msg : 主题信息
     * status : 1
     */

    private DataBean data;
    private String msg;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * adGallerys : [{"adFile":"Upload/adspic/2019-06/5cf5da8812054.png","adId":"58","adName":"端午首页轮播1","adPositionId":"-5","adURL":"","goodsId":"0","goodsType":"0","isBidding":"0"},{"adFile":"Upload/adspic/2019-06/5cf5da97476d2.png","adId":"59","adName":"端午首页轮播2","adPositionId":"-5","adURL":"","goodsId":"0","goodsType":"0","isBidding":"0"},{"adFile":"Upload/adspic/2019-06/5cf5daa3ae6c5.png","adId":"60","adName":"端午首页轮播3","adPositionId":"-5","adURL":"","goodsId":"0","goodsType":"0","isBidding":"0"},{"adFile":"Upload/adspic/2019-06/5cf5dab23089c.png","adId":"61","adName":"端午首页轮播4","adPositionId":"-5","adURL":"","goodsId":"0","goodsType":"0","isBidding":"0"}]
         * arbImg : Upload/styles/2019-06/5cf5d9e17533e.png
         * arbThums : Upload/styles/2019-06/5cf5d9e17533e_thumb.png
         * backgroundImg : Upload/styles/2019-06/5cf5d9a71d76f.png
         * backgroundThums : Upload/styles/2019-06/5cf5d9a71d76f_thumb.png
         * changeBottleImg : Upload/styles/2019-06/5cf5d9c83fcc0.png
         * changeBottleThums : Upload/styles/2019-06/5cf5d9c83fcc0_thumb.png
         * changeTireImg : Upload/styles/2019-06/5cf5d9bf7cd50.png
         * changeTireThums : Upload/styles/2019-06/5cf5d9bf7cd50_thumb.png
         * changingGlassImg : Upload/styles/2019-06/5cf5d9e652ded.png
         * changingGlassThums : Upload/styles/2019-06/5cf5d9e652ded_thumb.png
         * cheapCarImg : Upload/styles/2019-06/5cf5d9d879047.png
         * cheapCarThums : Upload/styles/2019-06/5cf5d9d879047_thumb.png
         * cosmetologyImg : Upload/styles/2019-06/5cf5d9e3eb755.png
         * cosmetologyThums : Upload/styles/2019-06/5cf5d9e3eb755_thumb.png
         * decorateImg : Upload/styles/2019-06/5cf5d9e9dc331.png
         * decorateThums : Upload/styles/2019-06/5cf5d9e9dc331_thumb.png
         * fontColor : 333333
         * id : 34
         * isDaily : 1
         * isDefault : 0
         * isUse : 1
         * maintainImg : Upload/styles/2019-06/5cf5d9b6a6cb3.png
         * maintainThums : Upload/styles/2019-06/5cf5d9b6a6cb3_thumb.png
         * moreImg : Upload/styles/2019-06/5cf5d9ebeb755.png
         * moreThums : Upload/styles/2019-06/5cf5d9ebeb755_thumb.png
         * renderingImg : Upload/styles/2019-06/5cf5d9eed0c16.jpg
         * renderingThums : Upload/styles/2019-06/5cf5d9eed0c16_thumb.jpg
         * rescueImg : Upload/styles/2019-06/5cf5d9ccb60d7.png
         * rescueThums : Upload/styles/2019-06/5cf5d9ccb60d7_thumb.png
         * styleFlag : 1
         * styleSort : 0
         * styleTheme : 端午
         * usedCarImg : Upload/styles/2019-06/5cf5d9d512054.png
         * usedCarThums : Upload/styles/2019-06/5cf5d9d512054_thumb.png
         * valuationCarImg : Upload/styles/2019-06/5cf5d9ddb60d7.png
         * valuationCarThums : Upload/styles/2019-06/5cf5d9ddb60d7_thumb.png
         * violationInquiryImg : Upload/styles/2019-06/5cf5d9d179047.png
         * violationInquiryThums : Upload/styles/2019-06/5cf5d9d179047_thumb.png
         * washcarImg : Upload/styles/2019-06/5cf5d9b0f3167.png
         * washcarThums : Upload/styles/2019-06/5cf5d9b0f3167_thumb.png
         */

        private String arbImg;
        private String arbThums;
        private String backgroundImg;
        private String backgroundThums;
        private String changeBottleImg;
        private String changeBottleThums;
        private String changeTireImg;
        private String changeTireThums;
        private String changingGlassImg;
        private String changingGlassThums;
        private String cheapCarImg;
        private String cheapCarThums;
        private String cosmetologyImg;
        private String cosmetologyThums;
        private String decorateImg;
        private String decorateThums;
        private String fontColor;
        private String id;
        private String isDaily;
        private String isDefault;
        private String isUse;
        private String maintainImg;
        private String maintainThums;
        private String moreImg;
        private String moreThums;
        private String renderingImg;
        private String renderingThums;
        private String rescueImg;
        private String rescueThums;
        private String styleFlag;
        private String styleSort;
        private String styleTheme;
        private String usedCarImg;
        private String usedCarThums;
        private String valuationCarImg;
        private String valuationCarThums;
        private String violationInquiryImg;
        private String violationInquiryThums;
        private String washcarImg;
        private String washcarThums;
        private List<AdGallerysBean> adGallerys;

        public String getArbImg() {
            return arbImg;
        }

        public void setArbImg(String arbImg) {
            this.arbImg = arbImg;
        }

        public String getArbThums() {
            return arbThums;
        }

        public void setArbThums(String arbThums) {
            this.arbThums = arbThums;
        }

        public String getBackgroundImg() {
            return backgroundImg;
        }

        public void setBackgroundImg(String backgroundImg) {
            this.backgroundImg = backgroundImg;
        }

        public String getBackgroundThums() {
            return backgroundThums;
        }

        public void setBackgroundThums(String backgroundThums) {
            this.backgroundThums = backgroundThums;
        }

        public String getChangeBottleImg() {
            return changeBottleImg;
        }

        public void setChangeBottleImg(String changeBottleImg) {
            this.changeBottleImg = changeBottleImg;
        }

        public String getChangeBottleThums() {
            return changeBottleThums;
        }

        public void setChangeBottleThums(String changeBottleThums) {
            this.changeBottleThums = changeBottleThums;
        }

        public String getChangeTireImg() {
            return changeTireImg;
        }

        public void setChangeTireImg(String changeTireImg) {
            this.changeTireImg = changeTireImg;
        }

        public String getChangeTireThums() {
            return changeTireThums;
        }

        public void setChangeTireThums(String changeTireThums) {
            this.changeTireThums = changeTireThums;
        }

        public String getChangingGlassImg() {
            return changingGlassImg;
        }

        public void setChangingGlassImg(String changingGlassImg) {
            this.changingGlassImg = changingGlassImg;
        }

        public String getChangingGlassThums() {
            return changingGlassThums;
        }

        public void setChangingGlassThums(String changingGlassThums) {
            this.changingGlassThums = changingGlassThums;
        }

        public String getCheapCarImg() {
            return cheapCarImg;
        }

        public void setCheapCarImg(String cheapCarImg) {
            this.cheapCarImg = cheapCarImg;
        }

        public String getCheapCarThums() {
            return cheapCarThums;
        }

        public void setCheapCarThums(String cheapCarThums) {
            this.cheapCarThums = cheapCarThums;
        }

        public String getCosmetologyImg() {
            return cosmetologyImg;
        }

        public void setCosmetologyImg(String cosmetologyImg) {
            this.cosmetologyImg = cosmetologyImg;
        }

        public String getCosmetologyThums() {
            return cosmetologyThums;
        }

        public void setCosmetologyThums(String cosmetologyThums) {
            this.cosmetologyThums = cosmetologyThums;
        }

        public String getDecorateImg() {
            return decorateImg;
        }

        public void setDecorateImg(String decorateImg) {
            this.decorateImg = decorateImg;
        }

        public String getDecorateThums() {
            return decorateThums;
        }

        public void setDecorateThums(String decorateThums) {
            this.decorateThums = decorateThums;
        }

        public String getFontColor() {
            return fontColor;
        }

        public void setFontColor(String fontColor) {
            this.fontColor = fontColor;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsDaily() {
            return isDaily;
        }

        public void setIsDaily(String isDaily) {
            this.isDaily = isDaily;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public String getIsUse() {
            return isUse;
        }

        public void setIsUse(String isUse) {
            this.isUse = isUse;
        }

        public String getMaintainImg() {
            return maintainImg;
        }

        public void setMaintainImg(String maintainImg) {
            this.maintainImg = maintainImg;
        }

        public String getMaintainThums() {
            return maintainThums;
        }

        public void setMaintainThums(String maintainThums) {
            this.maintainThums = maintainThums;
        }

        public String getMoreImg() {
            return moreImg;
        }

        public void setMoreImg(String moreImg) {
            this.moreImg = moreImg;
        }

        public String getMoreThums() {
            return moreThums;
        }

        public void setMoreThums(String moreThums) {
            this.moreThums = moreThums;
        }

        public String getRenderingImg() {
            return renderingImg;
        }

        public void setRenderingImg(String renderingImg) {
            this.renderingImg = renderingImg;
        }

        public String getRenderingThums() {
            return renderingThums;
        }

        public void setRenderingThums(String renderingThums) {
            this.renderingThums = renderingThums;
        }

        public String getRescueImg() {
            return rescueImg;
        }

        public void setRescueImg(String rescueImg) {
            this.rescueImg = rescueImg;
        }

        public String getRescueThums() {
            return rescueThums;
        }

        public void setRescueThums(String rescueThums) {
            this.rescueThums = rescueThums;
        }

        public String getStyleFlag() {
            return styleFlag;
        }

        public void setStyleFlag(String styleFlag) {
            this.styleFlag = styleFlag;
        }

        public String getStyleSort() {
            return styleSort;
        }

        public void setStyleSort(String styleSort) {
            this.styleSort = styleSort;
        }

        public String getStyleTheme() {
            return styleTheme;
        }

        public void setStyleTheme(String styleTheme) {
            this.styleTheme = styleTheme;
        }

        public String getUsedCarImg() {
            return usedCarImg;
        }

        public void setUsedCarImg(String usedCarImg) {
            this.usedCarImg = usedCarImg;
        }

        public String getUsedCarThums() {
            return usedCarThums;
        }

        public void setUsedCarThums(String usedCarThums) {
            this.usedCarThums = usedCarThums;
        }

        public String getValuationCarImg() {
            return valuationCarImg;
        }

        public void setValuationCarImg(String valuationCarImg) {
            this.valuationCarImg = valuationCarImg;
        }

        public String getValuationCarThums() {
            return valuationCarThums;
        }

        public void setValuationCarThums(String valuationCarThums) {
            this.valuationCarThums = valuationCarThums;
        }

        public String getViolationInquiryImg() {
            return violationInquiryImg;
        }

        public void setViolationInquiryImg(String violationInquiryImg) {
            this.violationInquiryImg = violationInquiryImg;
        }

        public String getViolationInquiryThums() {
            return violationInquiryThums;
        }

        public void setViolationInquiryThums(String violationInquiryThums) {
            this.violationInquiryThums = violationInquiryThums;
        }

        public String getWashcarImg() {
            return washcarImg;
        }

        public void setWashcarImg(String washcarImg) {
            this.washcarImg = washcarImg;
        }

        public String getWashcarThums() {
            return washcarThums;
        }

        public void setWashcarThums(String washcarThums) {
            this.washcarThums = washcarThums;
        }

        public List<AdGallerysBean> getAdGallerys() {
            return adGallerys;
        }

        public void setAdGallerys(List<AdGallerysBean> adGallerys) {
            this.adGallerys = adGallerys;
        }

        public static class AdGallerysBean {
            /**
             * adFile : Upload/adspic/2019-06/5cf5da8812054.png
             * adId : 58
             * adName : 端午首页轮播1
             * adPositionId : -5
             * adURL :
             * goodsId : 0
             * goodsType : 0
             * isBidding : 0
             */

            private String adFile;
            private String adId;
            private String adName;
            private String adPositionId;
            private String adURL;
            private String goodsId;
            private String goodsType;
            private String isBidding;

            public String getAdFile() {
                return adFile;
            }

            public void setAdFile(String adFile) {
                this.adFile = adFile;
            }

            public String getAdId() {
                return adId;
            }

            public void setAdId(String adId) {
                this.adId = adId;
            }

            public String getAdName() {
                return adName;
            }

            public void setAdName(String adName) {
                this.adName = adName;
            }

            public String getAdPositionId() {
                return adPositionId;
            }

            public void setAdPositionId(String adPositionId) {
                this.adPositionId = adPositionId;
            }

            public String getAdURL() {
                return adURL;
            }

            public void setAdURL(String adURL) {
                this.adURL = adURL;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }

            public String getIsBidding() {
                return isBidding;
            }

            public void setIsBidding(String isBidding) {
                this.isBidding = isBidding;
            }
        }
    }
}
